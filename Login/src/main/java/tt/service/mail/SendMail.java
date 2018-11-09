package tt.service.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tt.tools.Uid;


public class SendMail {
	
	public void sendmail(HttpServletRequest req,String host,String port,String email,String password,String recipient,String subject,String content,String token) throws Exception{
	 	 Properties prop = new Properties();
		 prop.setProperty("mail.host", host);
		 prop.setProperty("mail.transport.protocol", "smtp");
		 prop.setProperty("mail.smtp.port", port);
		 prop.setProperty("mail.smtp.auth", "true");
		//使用JavaMail发送邮件的5个步骤 
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect(host,email,password);
		//4、创建邮件
		List<String> lContent=new ArrayList<String>();
		Map<String,String> data=imgJudge(req,content,lContent);
		File[] file = fileJudge(req,content,token);
		Message message=null;
		if(data==null && file==null){
		 message = createSimpleMail(session,email,recipient,subject,content);
		}else if(file==null){
		 message = createImageMail(session,email,recipient,subject,lContent.get(0),data);
		}else if(data==null && file!=null){
			message = createAttachMail(session,email,recipient,subject,lContent.get(0),file);
		}else if(data!=null && file!=null){
			message = createMixedMail(session,email,recipient,subject,lContent.get(0),file,data);
		}
		//5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		 }
	//在发邮件前判断内容是否含有图片
	public static Map<String,String> imgJudge(HttpServletRequest request,String content,List<String> conList){
		String src="";
		Map<String,String>imgdata = new HashMap<String, String>();
		String basepath=request.getScheme()+":\\"+request.getServerName()+":"+request.getServerPort()+"\\";
		String phypath="D:\\apache-tomcat-7.0.41\\webapps\\";
		Document doc = Jsoup.parse(content);
		Elements images = doc.getElementsByTag("img");
		if(images==null||images.size()==0){
			return null;
		}else{
			for(Element image : images){
				src=image.attr("src");//此时得到的路径是http:\localhost:8081\Login\static\plugin\ ueditor\jsp\ upload1\20160930\37301475224639549.png
				//需要对此路径进行处理
				src=src.substring(basepath.length());
				src=phypath+src;
				String ext=src.substring(src.lastIndexOf("."));
				String cid=Uid.getUid()+"."+ext;
				image.attr("src", "cid:"+cid);//这只是改的document的image元素，还需要修改content的内容
				imgdata.put(cid,src);
			}
			conList.add(doc.body().html());//这里相当于修改的content的内容（实际是改了content的内容然后赋给lcontent）
//			Map<String,Object> map= new HashMap<String, Object>();
//			map.put("imgdata", imgdata);
//			map.put("lcontent", doc.body().html());
			
			return imgdata;
		}
	}
	//判断是否有附件
	public static File[] fileJudge(HttpServletRequest request,String content,String token){
		String userFolderPath = request.getSession().getServletContext().getRealPath("\\");
		//当前邮件所在路径
	    String attPath = userFolderPath +"\\"+"emailfile\\"+token;
	    File attachFolder = new File(attPath);
	    File[] children = attachFolder.listFiles();
	    if(children==null||children.length<1){
	    	return null;
	    }else{
	    	return children;
	    }
	}
	/*创建普通邮件*/
	public static MimeMessage createSimpleMail(Session session,String email,String recipient,String subject,String content)
	             throws Exception {
	         //创建邮件对象
	         MimeMessage message = new MimeMessage(session);
	         //指明邮件的发件人
	         message.setFrom(new InternetAddress(email));
	         //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
	         message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	         //邮件的标题
	         message.setSubject(subject);
	         //邮件的文本内容
	         message.setContent(content, "text/html;charset=UTF-8");
	         //返回创建好的邮件对象
	         message.setSentDate(new Date());
	         return message;
	     }
	
	
	/*带图片的邮件发送*/
	 public static MimeMessage createImageMail(Session session,String email,String recipient,String subject,String content,Map<String,String> data) throws Exception {
		          //创建邮件
		          MimeMessage message = new MimeMessage(session);
		          // 设置邮件的基本信息
		          //发件人
		          message.setFrom(new InternetAddress(email));
		          //收件人
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		          //邮件标题
		          message.setSubject(subject);
		  
		          // 准备邮件数据
		          // 准备邮件正文数据
		          Iterator<String> keyIterator = data.keySet().iterator();
					while(keyIterator.hasNext()){
						String key = keyIterator.next();
						String value = data.get(key);
		          MimeBodyPart text = new MimeBodyPart();
		          //text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
		          text.setContent(content, "text/html;charset=UTF-8");
		          // 准备图片数据
		          MimeBodyPart image = new MimeBodyPart();
		          DataHandler dh = new DataHandler(new FileDataSource(value));
		          image.setDataHandler(dh);
		         // image.setContentID(key);
		          image.setHeader("Content-ID", "<"+key+">");
		          image.setHeader("Content-Type", "application/octet-stream;name=\""+key+"\"");
					
		          // 描述数据关系
		          MimeMultipart mm = new MimeMultipart();
		          MimeMultipart mm2 = new MimeMultipart();
		          MimeBodyPart mmb2 = new MimeBodyPart();
		          mm2.addBodyPart(text);
		          mm2.setSubType("alternative");
		          mmb2.setContent(mm2);
		          mm.addBodyPart(image);
		          mm.addBodyPart(mmb2);
		          mm.setSubType("related");
		  
		          message.setContent(mm);
		          message.setSentDate(new Date());
		          message.saveChanges();
					}
		          //将创建好的邮件写入到E盘以文件的形式进行保存
		          //message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
		          //返回创建好的邮件
		          return message;
		      }
	 
	 /*创建有附件的邮件*/
	 public static MimeMessage createAttachMail(Session session,String email,String recipient,String subject,String content,File[] file) throws Exception{
		          MimeMessage message = new MimeMessage(session);
		          
		          //设置邮件的基本信息
		          //发件人
		          message.setFrom(new InternetAddress(email));
		          //收件人
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		          //邮件标题
		          message.setSubject(subject);
		          
		          for(File ifile:file){
			        	 String filepath=ifile.getPath();
		          //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		          MimeBodyPart text = new MimeBodyPart();
		          text.setContent(content, "text/html;charset=UTF-8");
		          
		          //创建邮件附件
		          
			          
		          MimeBodyPart attach = new MimeBodyPart();
		          DataHandler dh = new DataHandler(new FileDataSource(filepath));
		          attach.setDataHandler(dh);
		          attach.setFileName(dh.getName());  //
		          
		         //创建容器描述数据关系
		          MimeMultipart mp = new MimeMultipart();
		          mp.addBodyPart(text);
		          mp.addBodyPart(attach);
		          mp.setSubType("mixed");
		          
		          
		          message.setContent(mp);
		          message.setSentDate(new Date());
		          message.saveChanges();
		          }
		          //将创建的Email写入到E盘存储
		          //message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		          //返回生成的邮件
		          return message;
		      }
	 /*发送包含内嵌图片和附件的复杂邮件*/
	 public static MimeMessage createMixedMail(Session session,String email,String recipient,String subject,String content,File[] file,Map<String,String> data) throws Exception {
		          //创建邮件
		          MimeMessage message = new MimeMessage(session);
		          //设置邮件的基本信息
		          message.setFrom(new InternetAddress(email));
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		          message.setSubject(subject);
		         //正文
		          MimeMultipart mp1 = new MimeMultipart();
		          MimeMultipart mp2 = new MimeMultipart();
		          Iterator<String> keyIterator = data.keySet().iterator();
					while(keyIterator.hasNext()){
						String key = keyIterator.next();
						String value = data.get(key);
						MimeBodyPart text = new MimeBodyPart();
						MimeBodyPart image = new MimeBodyPart();
		          text.setContent(content,"text/html;charset=UTF-8");
		        //描述关系:正文和图片
		         
		          mp1.addBodyPart(text);
		          mp1.addBodyPart(image);
		          mp1.setSubType("related");
		         //图片
		          
		          /*image.setDataHandler(new DataHandler(new FileDataSource(value)));
		          image.setContentID(key);*/
		          image.setDataHandler(new DataHandler(new FileDataSource(value)));
		          image.setHeader("Content-ID", "<"+key+">");
		          image.setHeader("Content-Type", "application/octet-stream;name=\""+key+"\"");
					}
		          //附件1
					
					 for(File ifile:file){
			        	 String filepath=ifile.getPath();
			        	 MimeBodyPart attach = new MimeBodyPart();
				          DataHandler dh = new DataHandler(new FileDataSource(filepath));
				          attach.setDataHandler(dh);
				          attach.setFileName(dh.getName());
					 
		         
		          //描述关系:正文和附件
		          
		          mp2.addBodyPart(attach);
		         //代表正文的bodypart
		         MimeBodyPart co = new MimeBodyPart();
		         co.setContent(mp1);
		         mp2.addBodyPart(co);
		         mp2.setSubType("mixed");
		         
		         message.setContent(mp2);
		         message.setSentDate(new Date());
		         message.saveChanges();
					 }
		         
		        // message.writeTo(new FileOutputStream("D:\\MixedMail.eml"));
		         //返回创建好的的邮件
		         return message;
		     }
	}


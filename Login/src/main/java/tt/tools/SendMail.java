package tt.tools;

import java.io.FileOutputStream;
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
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import tt.entity.User;


public class SendMail {
	public void sendmail(User user,HttpServletRequest req) throws Exception{
		/*String username=new BASE64Encoder().encode("givking@sina.com".getBytes()); 
		String password=new BASE64Encoder().encode("g125311899+".getBytes());
		System.out.println(username);
		System.out.println(password);*/
		Properties prop = new Properties();
		 prop.setProperty("mail.host", "smtp.sina.com");
		 prop.setProperty("mail.transport.protocol", "smtp");
		 prop.setProperty("mail.smtp.port", "25");
		 prop.setProperty("mail.smtp.auth", "true");
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect("smtp.sina.com", "gigjtest@sina.com","der123456");
			//	new BASE64Encoder().encode("gigjtest@sohu.com".getBytes()), 
			//	new BASE64Encoder().encode("der123456".getBytes()));
		//4、创建邮件
		Message message = createSimpleMail(session,user,req);
		//5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		 }
	
	/*创建普通邮件*/
	public static MimeMessage createSimpleMail(Session session,User user,HttpServletRequest req)
	             throws Exception {
	         //创建邮件对象
	         MimeMessage message = new MimeMessage(session);
	         //指明邮件的发件人
	         message.setFrom(new InternetAddress("gigjtest@sina.com"));
	         //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
	         message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
	         String user_id=user.getUser_id();
	         String path = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/";
	         String url = path + "login/edit?USER_ID="+user_id;
	         //邮件的标题
	         message.setSubject("账号激活");
	         //邮件的文本内容
	         message.setContent("<a href='"+url+"'>你好啊！点击激活</a>,如无法自动转入，请输入以下地址"+url, "text/html;charset=UTF-8");
	         //返回创建好的邮件对象
	         return message;
	     }
	
	
	/*带图片的邮件发送*/
	 public static MimeMessage createImageMail(Session session) throws Exception {
		          //创建邮件
		          MimeMessage message = new MimeMessage(session);
		          // 设置邮件的基本信息
		          //发件人
		          message.setFrom(new InternetAddress("gigjtest@sina.com"));
		          //收件人
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress("125311899@qq.com"));
		          //邮件标题
		          message.setSubject("带图片的邮件");
		  
		          // 准备邮件数据
		          // 准备邮件正文数据
		          MimeBodyPart text = new MimeBodyPart();
		          text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
		          // 准备图片数据
		          MimeBodyPart image = new MimeBodyPart();
		          DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
		          image.setDataHandler(dh);
		          image.setContentID("xxx.jpg");
		          // 描述数据关系
		          MimeMultipart mm = new MimeMultipart();
		          mm.addBodyPart(text);
		          mm.addBodyPart(image);
		          mm.setSubType("related");
		  
		          message.setContent(mm);
		          message.saveChanges();
		          //将创建好的邮件写入到E盘以文件的形式进行保存
		          message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
		          //返回创建好的邮件
		          return message;
		      }
	 
	 /*创建有附件的邮件*/
	 public static MimeMessage createAttachMail(Session session) throws Exception{
		          MimeMessage message = new MimeMessage(session);
		          
		          //设置邮件的基本信息
		          //发件人
		          message.setFrom(new InternetAddress("gacl@sohu.com"));
		          //收件人
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
		          //邮件标题
		          message.setSubject("JavaMail邮件发送测试");
		          
		          //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		          MimeBodyPart text = new MimeBodyPart();
		          text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
		          
		          //创建邮件附件
		          MimeBodyPart attach = new MimeBodyPart();
		          DataHandler dh = new DataHandler(new FileDataSource("src\\2.jpg"));
		          attach.setDataHandler(dh);
		          attach.setFileName(dh.getName());  //
		          
		         //创建容器描述数据关系
		          MimeMultipart mp = new MimeMultipart();
		          mp.addBodyPart(text);
		          mp.addBodyPart(attach);
		          mp.setSubType("mixed");
		          
		          message.setContent(mp);
		          message.saveChanges();
		          //将创建的Email写入到E盘存储
		          message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		          //返回生成的邮件
		          return message;
		      }
	 /*发送包含内嵌图片和附件的复杂邮件*/
	 public static MimeMessage createMixedMail(Session session) throws Exception {
		          //创建邮件
		          MimeMessage message = new MimeMessage(session);
		          //设置邮件的基本信息
		          message.setFrom(new InternetAddress("gacl@sohu.com"));
		          message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
		          message.setSubject("带附件和带图片的的邮件");
		         //正文
		          MimeBodyPart text = new MimeBodyPart();
		          text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");
		         //图片
		          MimeBodyPart image = new MimeBodyPart();
		          image.setDataHandler(new DataHandler(new FileDataSource("src\\3.jpg")));
		          image.setContentID("aaa.jpg");
		          //附件1
		          MimeBodyPart attach = new MimeBodyPart();
		          DataHandler dh = new DataHandler(new FileDataSource("src\\4.zip"));
		          attach.setDataHandler(dh);
		          attach.setFileName(dh.getName());
		          //附件2
		          MimeBodyPart attach2 = new MimeBodyPart();
		          DataHandler dh2 = new DataHandler(new FileDataSource("src\\波子.zip"));
		          attach2.setDataHandler(dh2);
		          attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
		         //描述关系:正文和图片
		          MimeMultipart mp1 = new MimeMultipart();
		          mp1.addBodyPart(text);
		          mp1.addBodyPart(image);
		          mp1.setSubType("related");
		          //描述关系:正文和附件
		          MimeMultipart mp2 = new MimeMultipart();
		          mp2.addBodyPart(attach);
		          mp2.addBodyPart(attach2);
		         //代表正文的bodypart
		         MimeBodyPart content = new MimeBodyPart();
		         content.setContent(mp1);
		         mp2.addBodyPart(content);
		         mp2.setSubType("mixed");
		         
		         message.setContent(mp2);
		         message.saveChanges();
		         
		         message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
		         //返回创建好的的邮件
		         return message;
		     }
	}


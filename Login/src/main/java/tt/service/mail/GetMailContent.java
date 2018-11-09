package tt.service.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class GetMailContent {
	private StringBuffer content=null;
	private Message msg=null;

	//构造函数
	public GetMailContent() {
		this.content = new StringBuffer();
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}
	//解析html内容
	public void getMailContent(Part part) throws Exception{
		
		if(part==null) part = msg;
		String contentType = part.getContentType();
		boolean isNameContain = StringUtils.contains(contentType, "name");
		if(part.isMimeType("text/plain")&&!isNameContain){
			String str=(String)part.getContent();
			Pattern p = Pattern.compile("\r\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("<br/>");
			content.append(str);
		}else if(part.isMimeType("text/html")&&!isNameContain){
			content.append((String)part.getContent());
		}else if(part.isMimeType("multipart/*")){
			Multipart multipart = (Multipart)part.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		}else if(part.isMimeType("message/rfc822")){
			getMailContent((Part)part.getContent());
		}
	}
	/**
	 * 将邮件中内嵌的图片保存到本地<br/>
	 * 该方法解析邮件得到邮件正文中内嵌图片,保存到本地接收邮件的目录下(具体路径:接收根路径/用户id/邮件id/images/contentID)
	 */
	public List<String> saveInlineImg(Part part) throws Exception {
		List<String> inlineImages = new ArrayList<String>();
		if(part.isMimeType("application/octet-stream")||part.isMimeType("image/jpeg")){
			MimeBodyPart bodyPart = (MimeBodyPart)part;
			String contentID = bodyPart.getContentID();
			if(StringUtils.isNotBlank(contentID)){
				contentID = contentID.substring(1, contentID.length()-1);
				saveFile(contentID, bodyPart.getInputStream(),"image");
				inlineImages.add(contentID);
			}
		}else if(part.isMimeType("multipart/*")){
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				inlineImages.addAll(saveInlineImg(mp.getBodyPart(i)));
			}
		}else if(part.isMimeType("message/rfc882")){
			inlineImages.addAll(saveInlineImg((Part)part.getContent()));
		}
		return inlineImages;
	}
	/**
	 * 将邮件附件保存到本地目录中<br/>
	 * 该方法解析邮件得到邮件的附件,保存到本地接收邮件的目录下(具体路径:接收根路径/用户id/邮件id/attaches/附件名)
	 */ 
	public List<String> saveAttach(Part part) throws MessagingException, IOException{
		List<String> filePaths = new ArrayList<String>();
		if(part.isMimeType("multipart/*")){
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if(disposition!=null&&disposition.equals(Part.ATTACHMENT)){
					String fileName = mpart.getFileName();
					if(StringUtils.isNotBlank(fileName)){
						fileName = MimeUtility.decodeText(fileName);
						try {
							String fp = saveFile(fileName, mpart.getInputStream(),"attach");
							filePaths.add(fp);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else if(mpart.isMimeType("multipart/*")){
					saveAttach(mpart);
				}
			}
		}else if(part.isMimeType("message/rfc882")){
			saveAttach((Part)part.getContent());
		}
		return filePaths;
	} 
	//下载文件到本地
	private String saveFile(String fileName, InputStream in,String type) throws Exception {
		StringBuilder savePath = new StringBuilder();
		savePath.append("D:\\apache-tomcat-7.0.41\\webapps\\downloadfile\\Login");
		if("image".equals(type)){
			savePath.append("\\images\\");
		}else{
			savePath.append("\\attaches\\");
		}
		savePath.append(fileName);
		FileUtils.copyInputStreamToFile(in, new File(savePath.toString()));
		return fileName;
	}
	//html代码修改
	public String parsecontent(String content){
		Document doc1=Jsoup.parse(content);
		Elements images= doc1.getElementsByTag("img");
		if(images==null||images.size()==0){
			return content;
		}else{
			for(Element image : images){
				String path="http:\\\\localhost:8081\\downloadfile\\Login\\images\\";
				String src=image.attr("src");
				if(src.startsWith("cid")){
				path=path+src.substring(src.indexOf(":")+1, src.length());
				image.attr("src", path);
				}
			}
			String realcontent=doc1.body().toString();//邮件内容修改src后的内容
			return realcontent;
		}
	}
}

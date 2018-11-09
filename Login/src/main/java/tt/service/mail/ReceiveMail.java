package tt.service.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import com.sun.mail.pop3.POP3Folder;

import tt.entity.Mail_receive;
import tt.tools.Uid;
@Service("receivemail")
public class ReceiveMail {
	@Resource(name="mail_receiveService")
	private Mail_receiveService mail_receiveService;
	
	public void receivemail() throws Exception{
		Properties prop = new Properties();
		 prop.setProperty("mail.pop3.host","pop.sina.com");
		 prop.setProperty("mail.store.protocol", "pop3");
		 prop.setProperty("mail.pop3.port", "110");
	
	// 使用Properties对象获得Session对象  
    Session session = Session.getInstance(prop);  
    session.setDebug(true);  
      
    // 利用Session对象获得Store对象，并连接pop3服务器  
    Store store = session.getStore();  
    store.connect("pop.sina.com", "gigjtest@sina.com", "der123456");  
      
    // 获得邮箱内的邮件夹Folder对象，以"只读"打开  
    Folder folder = store.getFolder("inbox");  
    folder.open(Folder.READ_ONLY);  
      
    POP3Folder popinbox = (POP3Folder)folder;
    
    // 获得邮件夹Folder内的所有邮件Message对象  
    Message [] messages = popinbox.getMessages(); 
    List<Mail_receive> varlist=new ArrayList<Mail_receive>();
    int mailCounts = messages.length;  
    for(int i = 0; i < mailCounts; i++) {  
    	String mailuid=popinbox.getUID(messages[i]);
    	Mail_receive data=new Mail_receive();
        String subject = messages[i].getSubject();
        String from = (messages[i].getFrom()[0]).toString();
        from=MimeUtility.decodeText(from);
        Date sendDate = messages[i].getSentDate();
        //邮件内容
        GetMailContent mm = new GetMailContent();
        mm.getMailContent(messages[i]);
        if(mm.getContent()!=null){
        data.setContent(mm.parsecontent(mm.getContent().toString()));
        }
        mm.saveInlineImg(messages[i]);
        List<String> attaches=mm.saveAttach(messages[i]);
        //判断是否有附件
        if(attaches!=null){
        	for(String attach: attaches){
        		String path ="D:\\apache-tomcat-7.0.41\\webapps\\downloadfile\\Login\\attaches\\"+attach;
        	}
        }
        data.setSubject(subject);
        data.setMail_uid(mailuid);
        data.setAddresser(from);
        data.setCreate_time(sendDate);
        
        //判断邮件是否重复
        if(mail_receiveService.findbyuid(data)==null){
        	data.setMail_receive_id(Uid.getUid());
        	mail_receiveService.save(data);
        }
       
        
        //varlist.add(data);
        //GetMailContent mm = new GetMailContent();
        //mm.getMailContent(messages[i]);
        
              
    	}  
    
    folder.close(false);  
    store.close(); 
   // Collections.sort(varlist,new Timecomparator());
	 }  
	 
	 
}
//排序类
class Timecomparator implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		Mail_receive m1=(Mail_receive)o1;
		Mail_receive m2=(Mail_receive)o2;
		if(m1.getCreate_time().after(m2.getCreate_time())){
			return -1;
		}else if(m1.getCreate_time().before(m2.getCreate_time())){
			return 1;
		}else{
			return 0;
		}
	}
}

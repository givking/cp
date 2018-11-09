package tt.entity;

import java.util.Date;

public class Mail_receive {
	private String mail_receive_id;
	private String mail_uid;
	private String addresser;
	private String recipient;
	private String subject;
	private String content;
	private Date create_time;
	
	public String getMail_receive_id() {
		return mail_receive_id;
	}
	public void setMail_receive_id(String mail_recevie_id) {
		this.mail_receive_id = mail_recevie_id;
	}
	public String getMail_uid() {
		return mail_uid;
	}
	public void setMail_uid(String mail_uid) {
		this.mail_uid = mail_uid;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getAddresser() {
		return addresser;
	}
	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}
}

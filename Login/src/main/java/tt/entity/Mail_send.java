package tt.entity;

public class Mail_send {
	private String mail_send_id;
	private String recipient;
	private String subject;
	private String content;
	private String create_time;
	public String getMail_send_id() {
		return mail_send_id;
	}
	public void setMail_send_id(String mail_send_id) {
		this.mail_send_id = mail_send_id;
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}

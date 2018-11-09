package tt.entity;

public class Mail {
	private String user_id;
	private String nick_name;
	private String email;
	private String password;
	private String smtp_host;
	private String smtp_port;
	private String is_smtp_ssl;
	private String pop_host;
	private String pop_port;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtp_host() {
		return smtp_host;
	}
	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}
	public String getSmtp_port() {
		return smtp_port;
	}
	public void setSmtp_port(String smtp_port) {
		this.smtp_port = smtp_port;
	}
	public String getIs_smtp_ssl() {
		return is_smtp_ssl;
	}
	public void setIs_smtp_ssl(String is_smtp_ssl) {
		this.is_smtp_ssl = is_smtp_ssl;
	}
	public String getPop_host() {
		return pop_host;
	}
	public void setPop_host(String pop_host) {
		this.pop_host = pop_host;
	}
	public String getPop_port() {
		return pop_port;
	}
	public void setPop_port(String pop_port) {
		this.pop_port = pop_port;
	}
}

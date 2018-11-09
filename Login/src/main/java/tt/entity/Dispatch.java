package tt.entity;

public class Dispatch {
	private String name;
	private String drafter;//起草人
	private String content;//内容
	private String checker;//校核人id
	private String approver;//审核人id
	private String create_time;
	private String update_time;
	private String dispatch_id;
	private String checker_name;
	private String approver_name;
	private String status;//审核状态
	private String suggestion;//修改意见
	private String user_id;
	private String distribute;//分发
	private String transfer;//传阅
	private String propose;//拟办
	private String sign;//签收
	private String recipient;//签收人（收件人）
	public String getDrafter() {
		return drafter;
	}
	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getDispatch_id() {
		return dispatch_id;
	}
	public void setDispatch_id(String dispatch_id) {
		this.dispatch_id = dispatch_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecker_name() {
		return checker_name;
	}
	public void setChecker_name(String checker_name) {
		this.checker_name = checker_name;
	}
	public String getApprover_name() {
		return approver_name;
	}
	public void setApprover_name(String approver_name) {
		this.approver_name = approver_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDistribute() {
		return distribute;
	}
	public void setDistribute(String distribute) {
		this.distribute = distribute;
	}
	public String getTransfer() {
		return transfer;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getPropose() {
		return propose;
	}
	public void setPropose(String propose) {
		this.propose = propose;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}

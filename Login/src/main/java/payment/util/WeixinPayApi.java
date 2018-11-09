package payment.util;


public class WeixinPayApi {
	// 账号信息  
	public static final String appid = "wxfac4acc0523fd395";  // appid  
    //String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
	public static final String mch_id = "1483363632"; // 商户号  
	public static final String apikey = "nSuYhd95M6JOdtGwFKIijcYRFh4BuU5R"; // key
	//public static final String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";//沙箱接口链接
	public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//接口链接
	public static final String notify_url="http://www.nccszh.com/weixin/notify_url";
    
}
    

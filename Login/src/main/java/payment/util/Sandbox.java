package payment.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Sandbox {
	/// <summary>
    /// 获取沙箱签名密钥。
    /// </summary>
    /// <returns></returns>
    public static String GetSignKey(Map<String,Object> map) throws ParserConfigurationException, IOException, SAXException, IllegalAccessException {
        //String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
        Map<String,Object> signParam = new HashMap<String,Object>();
        //signParam.put("mch_id", WeixinPayApi.mch_id);
        //signParam.put("nonce_str", nonceStr);
        //signParam.put("sign", "A");
        signParam.putAll(map);
        String sign = Signature.getSign(signParam);
        signParam.put("sign", sign);
        String requestXML = XMLParser.getRequestXml(signParam);
        String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
        String resXml = HttpUtil.postData(url, requestXML);
        Map items = XMLParser.getMapFromXML(resXml);
        if(items.get("sandbox_signkey")!=null) {
            return (String)items.get("sandbox_signkey");
        } /*else if(items.TryGetValue("return_msg", out signKey)) {
            throw new WxPayException(signKey.ToString());
        }*/ else{
        	return "FAIL!!!";
        }
            /*throw new WxPayException("获取沙箱密钥失败！");*/
    }
}

package payment.alipay.com;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import payment.util.AliPayApi;




@Controller
@RequestMapping("/myalipay")
public class MyAlipayController {
	private static AlipayClient alipayClient;
	static {
		alipayClient = new DefaultAlipayClient(
				"https://openapi.alipaydev.com/gateway.do",
				AliPayApi.APP_ID,
				AliPayApi.PRIVATE_KEY,
				"json",
				AliPayApi.CHARSET,
				AliPayApi.ALIPAY_PUBLIC_KEY,
				AliPayApi.SIGN_TYPE
				);
	}
	
	@RequestMapping("/alipay")
	public void ali(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws Exception {
		String total_amount = httpRequest.getParameter("buyer_pay_amount");
		String subject = httpRequest.getParameter("project_name");
		Date date = new Date();
		String out_trade_no= Long.toString(date.getTime());
		
		 AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
		    //alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		    alipayRequest.setReturnUrl("http://localhost/cszh/index.jsp");
		    alipayRequest.setNotifyUrl("http://givking.free.ngrok.cc/FHM/myalipay/notify_url");//外网映射地址
		    //alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
		    alipayRequest.setBizContent("{" +
		        "    \"out_trade_no\":\"20150320010101001\"," +
		        "    \"out_trade_no\":\""+out_trade_no+"\"," +
		        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		        "    \"total_amount\":88.88," +
		        "    \"total_amount\":"+total_amount+"," +
		        "    \"subject\":\""+subject+"\"," +
		        "    \"body\":\""+subject+"\"," +
		        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
		        "    \"extend_params\":{" +
		        "    \"sys_service_provider_id\":\"2088511833207846\"" +
		        "    }"+
		        "  }");//填充业务参数
		    String form="";
		    try {
		        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		    }
		    httpResponse.setContentType("text/html;charset=utf-8");
		    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
		    httpResponse.getWriter().flush();
		    httpResponse.getWriter().close();
	}
	
	@RequestMapping("/notify_url")
	public void notify_url(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		 boolean verify_result = AlipaySignature.rsaCheckV1(params, AliPayApi.ALIPAY_PUBLIC_KEY, AliPayApi.CHARSET,
                 AliPayApi.SIGN_TYPE);

         if (verify_result) {// 验证成功
             // TODO 请在这里加上商户的业务逻辑程序代码 异步通知可能出现订单重复通知 需要做去重处理
        	 System.out.println("notify_url 验证成功succcess");
        	 //response.getWriter().print("success");
        	 response.getOutputStream().write("success".getBytes());//返回success后不再通知，否则持续通知
             return ;
         } else {
             System.out.println("notify_url 验证失败");
             // TODO
             return ;
         }
	}
}

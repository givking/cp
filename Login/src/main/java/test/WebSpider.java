package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class WebSpider {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url;
		if(args.length==0){
			url = "https://www.w3.org/";
			System.out.println("USING"+url);
		}else{
			url = args[0];
		}
		DefaultHandler handler = new DefaultHandler(){
			@Override
			public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {
				// TODO Auto-generated method stub
				if(lname.equals("a")&&attrs!=null){
					for(int i=0;i<attrs.getLength();i++){
						String aname = attrs.getLocalName(i);
						if(aname.equals("href")){
							System.out.println(attrs.getValue(i));
							//File img = new File(attrs.getValue(i));
						}
					}
				}
			}
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		//factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		SAXParser parser = factory.newSAXParser();
		InputStream in = new URL(url).openStream();
		File file = new File("C:\\Users\\admin\\Desktop\\MyXml.xml");
		//parser.parse(file, handler);
		parser.parse(in, handler);
		

	}
	public String connectWeb(){
		
	 // 定义即将访问的链接
	  String url = "http://www.w3.org";
	  // 定义一个字符串用来存储网页内容
	  String result = "";
	  // 定义一个缓冲字符输入流
	  BufferedReader in = null;
	  try {
	   // 将string转成url对象
	   URL realUrl = new URL(url);
	   // 初始化一个链接到那个url的连接
	   URLConnection connection = realUrl.openConnection();
	   // 开始实际的连接
	   connection.connect();
	   // 初始化 BufferedReader输入流来读取URL的响应
	   in = new BufferedReader(new InputStreamReader(
	     connection.getInputStream()));
	   // 用来临时存储抓取到的每一行的数据
	   String line;
	   while ((line = in.readLine()) != null) {
	    //遍历抓取到的每一行并将其存储到result里面
	    result += line;
	   }
	  } catch (Exception e) {
	   System.out.println("发送GET请求出现异常！" + e);
	   e.printStackTrace();
	  }
	  // 使用finally来关闭输入流
	  finally {
	   try {
	    if (in != null) {
	     in.close();
	    }
	   } catch (Exception e2) {
	    e2.printStackTrace();
	   }
	  }
	  System.out.println(result);
	  return result;
	 }

}

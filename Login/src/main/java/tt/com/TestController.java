package tt.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.User;
import tt.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/test1")//ueditor测试
	public ModelAndView test1(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/test1");
		return mv;
	}
	@RequestMapping("/test2")//ztree测试
	public ModelAndView test2(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		System.out.println(req.getParameter("DISTRIBUTE"));
		
		mv.setViewName("test/test2");
		return mv;
	}
	@RequestMapping("/testfun")//ztree的async配置，设置父节点无勾选框
	@ResponseBody
	public Object testfun(HttpServletRequest req) throws Exception{
		User user = new User();
		List<User> varlist = userService.fulltree(user);
		for(User u : varlist){
			if("USER".equals(u.getType())){
				u.setNocheck(false);
			} else {
				u.setNocheck(true);
			}
		}
		return varlist;
	}
	@RequestMapping("/test3")//Jsoup测试
	public ModelAndView test3(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		//后台获取html代码
		String html="<html><head><title>jsoup</title></head>"+"<body><p>this is jsoup</p></body></html>";
		Document doc1=Jsoup.parse(html);
		String jsoup1=doc1.body().toString();
		//获取url的html代码
		String check = "";
		String path = req.getContextPath();
		String url=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/"+"test/test1";
		Document doc2=Jsoup.connect(url).get();
		Elements image = doc2.getElementsByTag("img");
		if(image==null||image.size()==0){  //泛型集合用size()来判断里面元素是否为空
			check="f";
		}else{
			check="s";
		}
		String jsoup2=doc2.body().toString();
		mv.addObject("JSOUP1", jsoup1);
		mv.addObject("JSOUP2", jsoup2);
		mv.addObject("ISIMG", check);
		mv.setViewName("test/test3");
		return mv;
	}
	@RequestMapping("/test4")//JqueryUI测试
	public ModelAndView test4(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/test4");
		return mv;
	}
	@RequestMapping("/test5")//Dom4j测试
	public ModelAndView test5(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		 //将src下面的xml转换为输入流  
        //InputStream inputStream = new FileInputStream(new File("D:/project/dynamicWeb/src/resource/module01.xml"));   
        //InputStream inputStream = this.getClass().getResourceAsStream("/module01.xml");//也可以根据类的编译文件相对路径去找xml  
        //创建SAXReader读取器，专门用于读取xml  
        SAXReader saxReader = new SAXReader();  
        //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取   
        //Document document = saxReader.read(inputStream);    
        org.dom4j.Document document = saxReader.read(new File("D:/apache-tomcat-7.0.55/webapps/Login\\WEB-INF\\classes\\spring/ApplicationContext.xml"));//必须指定文件的绝对路径  
        //另外还可以使用DocumentHelper提供的xml转换器也是可以的。  
        //org.dom4j.Document document = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><modules id=\"123\"><module> 这个是module标签的文本信息</module></modules>");  
		//获取根节点对象  
        org.dom4j.Element rootElement = document.getRootElement();    
        System.out.println("根节点名称：" + rootElement.getName());//获取节点的名称  
        System.out.println("根节点有多少属性：" + rootElement.attributeCount());//获取节点属性数目  
        System.out.println("根节点id属性的值：" + rootElement.attributeValue("id"));//获取节点的属性id的值  
        System.out.println("根节点内文本：" + rootElement.getText());//如果元素有子节点则返回空字符串，否则返回节点内的文本  
        //rootElement.getText() 之所以会换行是因为 标签与标签之间使用了tab键和换行符布局，这个也算是文本所以显示出来换行的效果。  
        System.out.println("根节点内文本(1)：" + rootElement.getTextTrim());//去掉的是标签与标签之间的tab键和换行符等等，不是内容前后的空格  
        System.out.println("根节点子节点文本内容：" + rootElement.getStringValue()); //返回当前节点递归所有子节点的文本信息。  
          
        //获取子节点  
        org.dom4j.Element element = rootElement.element("module");  
        if(element != null){  
            System.out.println("子节点的文本：" + element.getText());//因为子节点和根节点都是Element对象所以它们的操作方式都是相同的  
        }  
        //但是有些情况xml比较复杂，规范不统一，某个节点不存在直接java.lang.NullPointerException，所以获取到element对象之后要先判断一下是否为空  
          
        rootElement.setName("root");//支持修改节点名称  
        System.out.println("根节点修改之后的名称：" + rootElement.getName());  
        rootElement.setText("text"); //同样修改标签内的文本也一样  
        System.out.println("根节点修改之后的文本：" + rootElement.getText());  
         
		mv.setViewName("test/test4");
		return mv;
	}
}

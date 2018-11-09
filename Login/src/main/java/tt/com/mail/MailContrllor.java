package tt.com.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Dispatch;
import tt.entity.Mail;
import tt.entity.Mail_receive;
import tt.entity.Mail_send;
import tt.entity.Menu;
import tt.entity.Myfile;
import tt.entity.Role_menu;
import tt.entity.User;
import tt.service.MenuService;
import tt.service.RoleService;
import tt.service.mail.MailService;
import tt.service.mail.Mail_receiveService;
import tt.service.mail.Mail_sendService;
import tt.service.mail.ReceiveMail;
import tt.service.mail.SendMail;
import tt.tools.FileUpload;
import tt.tools.PageUtil;
import tt.tools.Uid;

@Controller
@RequestMapping("/mail")
public class MailContrllor {
	@Resource(name="mailService")
	private MailService mailService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="mail_sendService")
	private Mail_sendService mail_sendService;
	@Resource(name="mail_receiveService")
	private Mail_receiveService mail_receiveService;
	@Resource(name="receivemail")
	private ReceiveMail receivemail;
	
	//进入邮箱管理页面
	@RequestMapping("/gomainpage")
	public ModelAndView goadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mail/mail");
		return mv;
	}
	/*写邮件*/
	@RequestMapping("/write")
	public ModelAndView write(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mail/mail_edit");
		return mv;
	}
	
	/*邮件发送*/
	@RequestMapping("/send")
	public ModelAndView send(HttpServletRequest req,MultipartFile file) throws Exception{
		ModelAndView mv=new ModelAndView();
		String token=Uid.getUid();
		req.getSession().setAttribute("email_token", token);
		//上传附件
		if(file!=null && !file.isEmpty()){
			String filepath=req.getSession().getServletContext().getRealPath("/") +"emailfile/"+token;
			FileUpload.fileUp(file, file.getOriginalFilename(), filepath);
		}
		User user = new User();
		
		user=(User)req.getSession().getAttribute("session_user");
		Mail mail=new Mail();
		mail.setUser_id(user.getUser_id());
		mail=mailService.findbyid(mail);//找到邮件配置信息
		String host = mail.getSmtp_host();
		String port = mail.getSmtp_port();
		String email= mail.getEmail();
		String password=mail.getPassword();
		String recipient=req.getParameter("RECIPIENT");
		String subject = req.getParameter("SUBJECT");
		String content=req.getParameter("content");
		SendMail sm=new SendMail();//简单邮件发送
		sm.sendmail(req,host, port, email, password, recipient, subject, content,token);
		Mail_send mail_send=new Mail_send();
		mail_send.setContent(content);
		mail_send.setMail_send_id(Uid.getUid());
		mail_send.setRecipient(recipient);
		mail_send.setSubject(subject);
		mail_sendService.save(mail_send);
		mv.setViewName("mail/mail_edit");
		return mv;
	}
	/*进到邮箱设置页面*/
	@RequestMapping("/goSetting")
	public ModelAndView gosetting(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		User user = new User();
		user=(User)req.getSession().getAttribute("session_user");
		Mail mail=new Mail();
		mail.setUser_id(user.getUser_id());
		mail=mailService.findbyid(mail);
		if(mail!=null){
		mv.addObject("NICK_NAME", mail.getNick_name());
		mv.addObject("EMAIL", mail.getEmail());
		mv.addObject("PASSWORD", mail.getPassword());
		mv.addObject("SMTP_HOST", mail.getSmtp_host());
		mv.addObject("SMTP_PORT", mail.getSmtp_port());
		mv.addObject("IS_SMTP_SSL", mail.getIs_smtp_ssl());
		mv.addObject("POP_HOST", mail.getPop_host());
		mv.addObject("POP_PORT", mail.getPop_port());
		}
		mv.setViewName("mail/setting");
		return mv;
	}
	/*保存邮箱设置*/
	@RequestMapping("/saveSetting")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Mail mail_setting=new Mail();
		User user = new User();
		user=(User)req.getSession().getAttribute("session_user");
		mail_setting.setUser_id(user.getUser_id());
		mail_setting.setNick_name(req.getParameter("NICK_NAME"));
		mail_setting.setEmail(req.getParameter("EMAIL"));
		mail_setting.setPassword(req.getParameter("PASSWORD"));
		mail_setting.setSmtp_host(req.getParameter("SMTP_HOST"));
		mail_setting.setSmtp_port(req.getParameter("SMTP_PORT"));
		mail_setting.setIs_smtp_ssl(req.getParameter("IS_SMTP_SSL"));
		mail_setting.setPop_host(req.getParameter("POP_HOST"));
		mail_setting.setPop_port(req.getParameter("POP_PORT"));
		if(mailService.findbyid(mail_setting)!=null){
			mailService.edit(mail_setting);
		}else{
		mailService.save(mail_setting);
		}
		mv.setViewName("forward:goSetting");
		return mv;
	}
	//进入发件箱
	@RequestMapping("/sendlist")
	public ModelAndView sendlist(PageUtil page,HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Mail_send mail_send=new Mail_send();
		if(req.getParameter("page")!=null){
		page.setCurrentpage(Integer.valueOf(req.getParameter("page")));
		}
		page.setObj(mail_send);
		List<Mail_send> varlist = mail_sendService.list(page);
		mv.addObject("varlist",varlist);
		mv.addObject("page", page);
		mv.setViewName("mail/mail_send_list");
		return mv;
		
	}
	//收邮件
	@RequestMapping("/doreceive")
	public ModelAndView dorecevie(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		receivemail.receivemail();
		mv.setViewName("forward:receivelist");
		return mv;
		
	}
	//进入收件箱
		@RequestMapping("/receivelist")
		public ModelAndView recevielist(PageUtil page,HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			Mail_receive mail_receive = new Mail_receive();
			if(req.getParameter("page")!=null){
				page.setCurrentpage(Integer.valueOf(req.getParameter("page")));
				}
				page.setObj(mail_receive);
			List<Mail_receive> varlist = (List<Mail_receive>)mail_receiveService.list(page);
			mv.addObject("varlist",varlist);
			mv.addObject("page", page);
			mv.setViewName("mail/mail_receive_list");
			return mv;
			
		}
		//查看邮件
		@RequestMapping("/check")
		public ModelAndView check(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			Mail_receive mail_receive=new Mail_receive();
			String mail_receive_id=req.getParameter("MAIL_RECEIVE_ID");
			mail_receive.setMail_receive_id(mail_receive_id);
			mail_receive=mail_receiveService.findbyid(mail_receive);
			mv.addObject("RECIPIENT", mail_receive.getRecipient());
			mv.addObject("ADDRESSER", mail_receive.getAddresser());
			mv.addObject("CONTENT", mail_receive.getContent());
			mv.addObject("CREATE_TIME", mail_receive.getCreate_time());
			mv.addObject("SUBJECT", mail_receive.getSubject());
			mv.setViewName("mail/mail_check");
			return mv;
		}
	//下载邮件中的附件
		@RequestMapping("/downattach")
		public ModelAndView downattach(HttpServletRequest req,HttpServletResponse res) throws Exception{
			ModelAndView mv=new ModelAndView();
			Myfile myfile=new Myfile();
			myfile.setFile_id(req.getParameter("FILE_ID"));
			//获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
	       // String path = req.getSession().getServletContext().getRealPath("/")+"uploadFile";  
	  
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        res.setContentType("multipart/form-data");  
	        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
	        res.setHeader("Content-Disposition", "attachment;fileName="+myfile.getFile_name());  
	        ServletOutputStream out;  
	        //通过文件路径获得File对象(假如此路径中有一个icon.png文件)  
	        File dfile = new File(myfile.getFile_path());  
	  
	        try {  
	            FileInputStream inputStream = new FileInputStream(dfile);  
	  
	            //3.通过response获取ServletOutputStream对象(out)  
	            out = res.getOutputStream();  
	  
	            int b = 0;  
	            byte[] buffer = new byte[512];  
	            while (b != -1){  
	                b = inputStream.read(buffer);  
	                //4.写到输出流(out)中  
	                out.write(buffer,0,b);  
	            }  
	            inputStream.close();  
	            out.close();  
	            out.flush();  
	  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
			mv.setViewName("forward:receivelist");
			return mv;
			
		}	
		@RequestMapping("/del")
		public ModelAndView del(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			//删除菜单列表里的菜单
			Menu menu=new Menu();
			menu.setMenu_id(req.getParameter("MENU_ID"));
			menuService.del(menu);
			Role_menu role_menu=new Role_menu();
			role_menu.setMenu_id(req.getParameter("MENU_ID"));
			//删除菜单对应的权限关联表数据
			List<Role_menu> varlist = new ArrayList<Role_menu>();
			varlist=(List<Role_menu>) roleService.findbymenuid(role_menu);
			if(!varlist.isEmpty()){
			for(Role_menu rolemenu:varlist){
				roleService.del(rolemenu);
				}
			};
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/goedit")
		public ModelAndView goedit(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			Menu menu=new Menu();
			menu.setMenu_id(req.getParameter("MENU_ID"));
			Menu emenu = (Menu)menuService.findbyid(menu);
			mv.addObject("MENU_NAME", emenu.getMenu_name());
			mv.addObject("MENU_URL", emenu.getMenu_url());
			mv.addObject("MENU_ID", req.getParameter("MENU_ID"));
			mv.setViewName("menu/menu_edit");
			return mv;
		}
		@RequestMapping("/edit")
		public ModelAndView edit(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			Menu menu=new Menu();
			menu.setMenu_id(req.getParameter("MENU_ID"));
			menu.setMenu_name(req.getParameter("MENU_NAME"));
			menu.setMenu_url(req.getParameter("MENU_URL"));
			menuService.edit(menu);
			mv.setViewName("forward:list");
			return mv;
		}
}

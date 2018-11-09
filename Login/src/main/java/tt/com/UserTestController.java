package tt.com;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Menu;
import tt.entity.Role;
import tt.entity.Role_menu;
import tt.entity.User;
import tt.service.MenuService;
import tt.service.RoleService;
import tt.service.UserService;
import tt.tools.SendMail;
import tt.tools.Uid;

@Controller
@RequestMapping("/login")
public class UserTestController {
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;
	//登录界面
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		req.getSession().removeAttribute("session_user");
		//req.getSession().invalidate();
		mv.setViewName("login");
		return mv;
	}
	//主页映射
	@RequestMapping("/mainpage")
	public ModelAndView mainpage(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		User user=new User();
		user=(User)req.getSession().getAttribute("session_user");
		Role role=new Role();
		role.setRole_id(user.getRole_id());
		
		List<Role_menu> rlist =(List<Role_menu>) roleService.findbyroleid(role);
		List<Menu> varlist = new ArrayList<Menu>();
		for(Role_menu obj:rlist){
		Menu menu = new Menu();
		menu.setMenu_id(obj.getMenu_id());
		varlist.add(menuService.findbyid(menu));
		};
		mv.addObject("varlist",varlist);
		mv.setViewName("mainpage");
		return mv;
	}
	//点击注册按钮
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	//点击登录按钮
	@RequestMapping("/clicklogin")
	public ModelAndView clicklogin(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		User user = new User();
		String userName = req.getParameter("NAME");
		user.setName(userName);
		req.getSession().setAttribute("session_user", userService.findbyname(user));
		mv.addObject("NAME", userName);
		mv.setViewName("welcomlogin");
		return mv;
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		User user = new User();
		String name = req.getParameter("NAME");
		String password = req.getParameter("PASSWORD");
		String email = req.getParameter("EMAIL");
		user.setUser_id(Uid.getUid());
		user.setEmail(email);
		user.setName(name);
		user.setEmailstatus("0");
		user.setPassword(password);
		userService.save(user);
		SendMail smail=new SendMail();
		smail.sendmail(user,req);
		mv.setViewName("loginsuccess");
		return mv;
	}
	//邮箱激活
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		User user = new User();
		user.setUser_id(req.getParameter("USER_ID"));
		user=(User)userService.findbyid(user);
		user.setEmailstatus("1");
		userService.edit(user);
		mv.setViewName("forward:login");
		return mv;
	}
	//验证用户是否存在
	@RequestMapping("/hasName")
	@ResponseBody
	public Object hasName(HttpServletRequest req) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String log = "success";
		User user = new User();
		user.setName(req.getParameter("NAME"));
		if(userService.findbyname(user) != null){
			log = "fail";
		}
		map.put("result", log);
		return map;
	}
	@RequestMapping("/checkCode")
	@ResponseBody
	public Object checkCode(HttpServletRequest req) throws Exception{
		String randCheckCode=(String)req.getSession().getAttribute("randCheckCode");
		String checkcode=req.getParameter("CHECKCODE");
		String log = "success";
		User user=new User();
		User user2=new User();
		user.setName(req.getParameter("NAME"));
		user.setPassword(req.getParameter("PASSWORD"));
		user2=(User) userService.findbyname(user);
		if(userService.findbyname(user) == null){
			log="errorname";
		}else if(!user2.getPassword().equals(req.getParameter("PASSWORD"))){
				log="errorpass";
		}else if(!checkcode.equals(randCheckCode)){
				log = "errorcode";
		}
		
		
		//if(userService.findbyname(user).toString())
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("result", log);
		return map;
	}
}


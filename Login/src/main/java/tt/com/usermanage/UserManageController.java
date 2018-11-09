package tt.com.usermanage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Role;
import tt.entity.User;
import tt.service.RoleService;
import tt.service.UserService;
import tt.tools.Uid;

@Controller
@RequestMapping("/user")
public class UserManageController {
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	//进入角色分配页面
	@RequestMapping("/goadd")
	public ModelAndView goadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Role role = new Role();
		List<Role> varlist=roleService.list(role);
		User user = new User();
		List<User> varlist2 = userService.list(user);
		mv.addObject("varlist",varlist);
		mv.addObject("varlist2",varlist2);
		mv.setViewName("usermanage/usermanage");
		return mv;
		
	}
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		User user = new User();
		user.setUser_id(Uid.getUid());
		user.setName(req.getParameter("user_NAME"));
		user.setRole_id(req.getParameter("MENU_URL"));
		userService.save(user);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("menu/menu");
		return mv;
		
	}
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("menu/menu");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		
		/*//分配角色
		User ruser = new User();
		ruser.setUser_id(req.getParameter("USER_ID"));
		User rruser=(User)userService.findbyid(ruser);
		rruser.setRole_id(req.getParameter("ROLE_ID"));*/
		
		User user = new User();
		List<User> varlist = userService.list(user);
		mv.addObject("varlist",varlist);
		mv.setViewName("usermanage/usermanage_list");
		return mv;
		
	}
	@RequestMapping("/rolelist")
	public ModelAndView rolelist(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		
		//分配角色
		User ruser = new User();
		ruser.setUser_id(req.getParameter("USER_ID"));
		User rruser=(User)userService.findbyid(ruser);
		rruser.setRole_id(req.getParameter("ROLE_ID"));
		userService.edit(rruser);
		mv.setViewName("forward:list");
		return mv;
	}
}

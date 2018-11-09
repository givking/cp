package tt.com.role;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Menu;
import tt.entity.Role;
import tt.entity.Role_menu;
import tt.service.MenuService;
import tt.service.RoleService;
import tt.tools.Uid;

@Controller
@RequestMapping("/role")
public class RoleContrllor {
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	//进入增加菜单页面
	@RequestMapping("/goadd")
	public ModelAndView goadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("menu/menu");
		return mv;
		
	}
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Menu menu = new Menu();
		menu.setMenu_id(Uid.getUid());
		menu.setMenu_name(req.getParameter("MENU_NAME"));
		menu.setMenu_url(req.getParameter("MENU_URL"));
		menuService.save(menu);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Menu menu=new Menu();
		menu.setMenu_id(req.getParameter("MENU_ID"));
		menuService.del(menu);
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
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Role role = new Role();
		List<Role> varlist = roleService.list(role);
		Menu menu = new Menu();
		List<Menu> varlist2 = menuService.list(menu);
		Role_menu role_menu = new Role_menu();
		List<Role_menu> varlist3 = roleService.rmlist(role_menu);
		StringBuffer MENU_ID = new StringBuffer();
		for(Role_menu var:varlist3){
			String role_id=var.getRole_id();
			String menu_id=var.getMenu_id();
			String role_menu_id=role_id+"_"+menu_id+",";
			MENU_ID.append(role_menu_id);
		}
		mv.addObject("MENU_ID", MENU_ID.toString());
		mv.addObject("varlist",varlist);
		mv.addObject("varlist2",varlist2);
		mv.setViewName("role/role_list");
		return mv;
		
	}
	@RequestMapping("/auth")
	@ResponseBody
	public Object auth(HttpServletRequest req) throws Exception{
		JSONArray jsonArray = JSONArray.fromObject(req.getParameter("MENU_ID"));
		
		for(Object cObj : jsonArray){
			String cId = (String)cObj;
			String[] ids = cId.split("_");
			Role_menu role_menu=new Role_menu();
			role_menu.setRole_menu_id(Uid.getUid());
			role_menu.setMenu_id(ids[1]);
			role_menu.setRole_id(ids[0]);
			roleService.saveauth(role_menu);
		}
		return null;
	}
	@RequestMapping("/delauth")
	@ResponseBody
	public Object delauth(HttpServletRequest req) throws Exception{
		JSONArray jsonArray = JSONArray.fromObject(req.getParameter("MENU_ID"));
		
		for(Object cObj : jsonArray){
			String cId = (String)cObj;
			String[] ids = cId.split("_");
			Role_menu role_menu=new Role_menu();
			role_menu.setMenu_id(ids[1]);
			role_menu.setRole_id(ids[0]);
			roleService.delauth(role_menu);
		}
		return null;
	}
}

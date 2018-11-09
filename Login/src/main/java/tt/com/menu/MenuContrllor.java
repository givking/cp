package tt.com.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Menu;
import tt.entity.Role_menu;
import tt.service.MenuService;
import tt.service.RoleService;
import tt.tools.Uid;

@Controller
@RequestMapping("/menu")
public class MenuContrllor {
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
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Menu menu = new Menu();
		List<Menu> varlist = menuService.list(menu);
		mv.addObject("varlist",varlist);
		mv.setViewName("menu/menu_list");
		return mv;
		
	}
}

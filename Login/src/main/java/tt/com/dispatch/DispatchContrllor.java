package tt.com.dispatch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Dispatch;
import tt.entity.Menu;
import tt.entity.Role_menu;
import tt.entity.User;
import tt.service.DispatchService;
import tt.service.MenuService;
import tt.service.RoleService;
import tt.service.UserService;
import tt.tools.PageUtil;
import tt.tools.Uid;

@Controller
@RequestMapping("/dispatch")
public class DispatchContrllor {
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="dispatchService")
	private DispatchService dispatchService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="userService")
	private UserService userService;
	
	//进入增加菜单页面
	@RequestMapping("/goadd")
	public ModelAndView goadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		User user=new User();
		List<User> varlist=userService.list(user);
		mv.addObject("varlist", varlist);
		mv.setViewName("dispatch/dispatch");
		return mv;
		
	}
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Dispatch dispatch = new Dispatch();
		User user= new User();
		dispatch.setDispatch_id(Uid.getUid());
		dispatch.setApprover(req.getParameter("APPROVER"));
		dispatch.setChecker(req.getParameter("CHECKER"));
		dispatch.setContent(req.getParameter("content"));
		dispatch.setCreate_time(req.getParameter("CREATE_TIME"));
		user=(User)req.getSession().getAttribute("session_user");
		dispatch.setDrafter(user.getName());
		dispatch.setName(req.getParameter("NAME"));
		dispatch.setUpdate_time(req.getParameter("UPDATE_TIME"));
		dispatch.setStatus("0");//设置状态为等待审核
		dispatchService.save(dispatch);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Dispatch dispatch = new Dispatch();
		dispatch.setDispatch_id((String)req.getParameter("DISPATCH_ID"));
		dispatchService.del(dispatch);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/goedit")
	public ModelAndView goedit(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Dispatch dispatch = new Dispatch();
		dispatch.setDispatch_id((String)req.getParameter("DISPATCH_ID"));
		dispatch = dispatchService.findbyid(dispatch);
		mv.addObject("DISPATCH_ID", dispatch.getDispatch_id());
		mv.addObject("NAME",dispatch.getName());
		mv.addObject("DRAFTER",dispatch.getDrafter());
		mv.addObject("CONTENT",dispatch.getContent());
		mv.addObject("CHECKER",dispatch.getChecker());
		mv.addObject("APPROVER",dispatch.getApprover());
		mv.addObject("CREATE_TIME",dispatch.getCreate_time());
		mv.addObject("UPDATE_TIME",dispatch.getUpdate_time());
		mv.addObject("STATUS",dispatch.getStatus());
		mv.addObject("SUGGESTION", dispatch.getSuggestion());
		User user=new User();
		List<User> varlist=userService.list(user);
		mv.addObject("varlist", varlist);
		mv.setViewName("dispatch/dispatch_edit");
		return mv;
	}
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Dispatch dispatch = new Dispatch();
		dispatch.setApprover(req.getParameter("APPROVER"));
		dispatch.setChecker(req.getParameter("CHECKER"));
		dispatch.setContent(req.getParameter("content"));
		dispatch.setCreate_time(req.getParameter("CREATE_TIME"));
		dispatch.setDrafter(req.getParameter("DRAFTER"));
		dispatch.setName(req.getParameter("NAME"));
		dispatch.setStatus("0");//修改保存后更改状态为未审核
		dispatch.setUpdate_time(req.getParameter("UPDATE_TIME"));
		dispatch.setDispatch_id(req.getParameter("DISPATCH_ID"));
		dispatchService.edit(dispatch);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(PageUtil page,HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Dispatch dispatch = new Dispatch();
		if(req.getParameter("page")!=null){
		page.setCurrentpage(Integer.valueOf(req.getParameter("page")));
		}
		page.setObj(dispatch);
		List<Dispatch> varlist = dispatchService.pagelist(page);
		/*//分页  当数据量过大时，容易卡死
		Integer listcount=varlist.size();
		PageUtil pageutil=new PageUtil();
		pageutil.setListcount(listcount);
		pageutil.setPagelistcount(8);
		pageutil.getPagecount();
		if(req.getParameter("page")!=null){
		pageutil.setCurrentpage(Integer.valueOf(req.getParameter("page")));
		}
		Integer startindex=pageutil.getStartindex();
		Integer endindex=pageutil.getEndindex();
		List<Dispatch> rvarlist = new ArrayList<Dispatch>();
		for(int i=startindex;i<=endindex;i++){
			rvarlist.add(varlist.get(i));
		}
		mv.addObject("page", pageutil);*/
		mv.addObject("varlist",varlist);
		mv.addObject("page", page);
		mv.setViewName("dispatch/dispatch_list");
		return mv;
		
	}
}

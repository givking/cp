package tt.com.receipt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/inbox")
public class InboxContrllor {
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
		mv.setViewName("inbox/inbox");
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
		User approver=new User();
		approver.setUser_id(dispatch.getApprover());
		approver=(User)userService.findbyid(approver);
		mv.addObject("APPROVER_NAME",approver.getName());
		mv.addObject("CREATE_TIME",dispatch.getCreate_time());
		mv.addObject("UPDATE_TIME",dispatch.getUpdate_time());
		mv.addObject("STATUS",dispatch.getStatus());
		mv.addObject("SUGGESTION", dispatch.getSuggestion());
		mv.addObject("RECIPIENT", dispatch.getRecipient());
		mv.addObject("DISTRIBUTE", dispatch.getDistribute());
		User user=new User();
		List<User> varlist=userService.list(user);
		mv.addObject("varlist", varlist);
		mv.setViewName("inbox/inbox_edit");
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
		dispatch.setStatus(req.getParameter("STATUS"));
		dispatch.setUpdate_time(req.getParameter("UPDATE_TIME"));
		dispatch.setDispatch_id(req.getParameter("DISPATCH_ID"));
		dispatch.setDistribute(req.getParameter("DISTRIBUTE"));
		dispatch.setRecipient(req.getParameter("RECIPIENT"));
		dispatch.setSign(req.getParameter("SIGN"));
		dispatch.setPropose(req.getParameter("PROPOSE"));
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
		User user=(User)req.getSession().getAttribute("session_user");
		List<Dispatch> varlist = dispatchService.list(page);
		List<Dispatch> varlist2=new ArrayList<Dispatch>();
		for(Dispatch var : varlist){
			if(var.getDistribute()!=null){
				String[] distribute=var.getDistribute().split(",");
				for(int i=0;i<distribute.length;i++){
					if(distribute[i].equals(user.getName())){
						varlist2.add(dispatchService.findbyid(var));
					}
				}
			}
		}
		mv.addObject("varlist",varlist2);
		mv.addObject("page", page);
		mv.setViewName("inbox/inbox_list");
		return mv;
		
	}
	@RequestMapping("/tree")
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
}

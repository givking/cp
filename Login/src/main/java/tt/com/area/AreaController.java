package tt.com.area;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Area;
import tt.entity.User;
import tt.service.AreaService;
import tt.service.RoleService;
import tt.service.UserService;
import tt.tools.Uid;
@Controller
@RequestMapping("/area")
public class AreaController {
	@Resource(name="areaService")
	private AreaService areaService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	@RequestMapping("/goadd")
	public ModelAndView goadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("area_mannage/area");
		return mv;
	}
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		Area area=new Area();
		area.setArea_id(Uid.getUid());
		area.setArea_name(req.getParameter("AREA_NAME"));
		area.setCreate_time(time);
		area.setNo(req.getParameter("NO"));
		area.setParent_id("0");
		areaService.save(area);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		area.setArea_id(req.getParameter("AREA_ID"));
		areaService.del(area);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		area.setArea_id(req.getParameter("AREA_ID"));
		area.setArea_name(req.getParameter("AREA_NAME"));
		area.setCreate_time(req.getParameter("CREATE_TIME"));
		area.setNo(req.getParameter("NO"));
		areaService.edit(area);
		mv.setViewName("forward:list");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		List<Area> varlist = areaService.list(area);
		mv.addObject("varlist",varlist);
		mv.setViewName("area_mannage/area_list");
		return mv;
	}
	/*下级单位*/
	@RequestMapping("/cgoadd")
	public ModelAndView cgoadd(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("area_mannage/carea/area");
		return mv;
	}
	@RequestMapping("/csave")
	public ModelAndView csave(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		Area area=new Area();
		area.setArea_id(Uid.getUid());
		area.setArea_name(req.getParameter("AREA_NAME"));
		area.setCreate_time(time);
		area.setNo(req.getParameter("NO"));
		area.setParent_id(req.getParameter("LEVEL_ID"));
		areaService.save(area);
		mv.setViewName("forward:clist");
		return mv;
	}
	@RequestMapping("/cdel")
	public ModelAndView cdel(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		area.setArea_id(req.getParameter("AREA_ID"));
		areaService.del(area);
		mv.setViewName("forward:clist");
		return mv;
	}
	@RequestMapping("/cedit")
	public ModelAndView cedit(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		area.setArea_id(req.getParameter("AREA_ID"));
		area.setArea_name(req.getParameter("AREA_NAME"));
		area.setCreate_time(req.getParameter("CREATE_TIME"));
		area.setNo(req.getParameter("NO"));
		areaService.edit(area);
		mv.setViewName("forward:clist");
		return mv;
	}
	@RequestMapping("/clist")
	public ModelAndView clist(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Area area=new Area();
		List<Area> varlist = areaService.list(area);
		mv.addObject("varlist",varlist);
		mv.setViewName("area_mannage/carea/area_list");
		return mv;
	}
	@RequestMapping("/tree")//ztree的async配置，设置父节点无勾选框
	@ResponseBody
	public Object tree(HttpServletRequest req) throws Exception{
		Area area=new Area();
		List<Area> varlist = areaService.list(area);
		/*for(Area u : varlist){
			if("USER".equals(u.getType())){
				u.setNocheck(false);
			} else {
				u.setNocheck(true);
			}
		}*/
		return varlist;
	}
}

package tt.com.activiti;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.FormValue;
import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.FormType;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Menu;
import tt.entity.Role_menu;
import tt.entity.User;
import tt.tools.Uid;
@Controller
@RequestMapping("/activ")
public class ActivController {
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	RepositoryService rs = pe.getRepositoryService();
	FormService fs = pe.getFormService();
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
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/del")
		public ModelAndView del(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/goedit")
		public ModelAndView goedit(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("menu/menu_edit");
			return mv;
		}
		@RequestMapping("/edit")
		public ModelAndView edit(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/list")
		public ModelAndView list(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			
			List<ProcessDefinition> pd = rs.createProcessDefinitionQuery().list();
			mv.addObject("varlist", pd);
			mv.setViewName("activ/activ_list");
			return mv;
		}
		@RequestMapping("/godeploy")
		public ModelAndView godeploy(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("activ/activ_deploy");
			return mv;
		}
		@RequestMapping("/deploy")
		public ModelAndView deploy(HttpServletRequest req,MultipartFile file) throws Exception{
			ModelAndView mv=new ModelAndView();
			String filename=file.getOriginalFilename();
			InputStream fileip = file.getInputStream();
			DeploymentBuilder deployment = rs.createDeployment();
			deployment.addInputStream(filename,fileip);
			deployment.deploy();
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/read_resource")
		public void read_resource(HttpServletRequest req,HttpServletResponse res) throws Exception{
			String processDefinitionId = req.getParameter("pdid");
			String resourcename = req.getParameter("resourcename");
			ProcessDefinitionQuery pdq= rs.createProcessDefinitionQuery();
			ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();
			InputStream resourcestream = rs.getResourceAsStream(pd.getDeploymentId(),resourcename);
			byte[] b = new byte[1024];
			int len=-1;
			while((len=resourcestream.read(b,0,1024))!=-1){
				res.getOutputStream().write(b, 0, len);
			}
		}
		@RequestMapping("/del_deploy")
		public ModelAndView del_deploy(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			String deploymentId=req.getParameter("deploymentId");
			rs.deleteDeployment(deploymentId,true);
			mv.setViewName("forward:list");
			return mv;
		}
		@RequestMapping("/runpro")
		public ModelAndView runpro(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			String pdid=req.getParameter("pid");
			StartFormData sfdata = fs.getStartFormData(pdid);
			List<FormProperty>varlist = sfdata.getFormProperties();
			mv.addObject("pid", pdid);
			mv.addObject("varlist", varlist);
			mv.setViewName("activ/activ_form");
			return mv;
		}
		@RequestMapping("/startProcess") //启动流程
		public ModelAndView startProcess(HttpServletRequest req) throws Exception{
			ModelAndView mv=new ModelAndView();
			String pdid=req.getParameter("pid");
			StartFormData sfdata = fs.getStartFormData(pdid);
			List<FormProperty>varlist = sfdata.getFormProperties();
			Map<String,String>formvalues=new HashMap<String,String>();
			for(FormProperty var :varlist){
				String value=req.getParameter(var.getId());
				formvalues.put(var.getId(), value);
			}
			//User user=(User)req.getSession().getAttribute("session_user");
			ProcessInstance pi = fs.submitStartFormData(pdid, formvalues);
			System.out.println("pid="+pi.getId());
			mv.addObject("varlist", varlist);
			mv.setViewName("forward:list");
			return mv;
		}
}

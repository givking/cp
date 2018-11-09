package test;

	import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

	import org.activiti.engine.RuntimeService;

	import org.activiti.engine.TaskService;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class Second {
	    public static void main(String[] args) {
	    //加载配置文件activiti.cfg.xml，创建引擎，如果出现null，多半是加载路径不是根目录。
	    //获取配置文件后，引擎开始创建数据库。
	        ProcessEngine engine=ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
	    //获取流程储存服务组件
	        RepositoryService rs=engine.getRepositoryService();
	    //获取运行时服务组件
	        RuntimeService rse=engine.getRuntimeService();
	    //获取流程中的任务TASK组件
	        TaskService ts=engine.getTaskService();
	    //部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
	        rs.createDeployment().addClasspathResource("diagrams/MyProcess2.bpmn").deploy();
	    //开启流程，myprocess是流程的ID 
	        Map<String,Object> user1=new HashMap<String,Object>();
	        user1.put("applyUser", "employee1");
	        user1.put("days", 3);
	        ProcessInstance pi=rse.startProcessInstanceByKey("myProcess",user1);
	        System.out.println("pid="+pi.getId());
	    //利用taskservice进行任务查询，查询第一个任务，查询后完成
	        Task task=ts.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
	        ts.claim(task.getId(), "leaderUser");
	        user1.put("approved", true);
	        ts.complete(task.getId(),user1);
	    }
	}

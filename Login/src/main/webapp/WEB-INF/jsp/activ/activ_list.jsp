<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table>
				
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">流程定义ID</th>
						<th class="center">部署ID</th>
						<th class="center">流程定义名字</th>
						<th class="center">流程定义 KEY</th>
						<th class="center">版本号</th>
						<th class="center">XML资源名称</th>
						<th class="center">图片资源名称</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
							<tr>
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.id}</td>
										<td>${var.deploymentId}</td>
										<td>${var.name}</td>
										<td>${var.key}</td>
										<td>${var.version}</td>
										<td>${var.resourceName}</td>
										<td><a href="activ/read_resource?pdid=${var.id}&&resourcename=${var.diagramResourceName}">${var.diagramResourceName}</a></td>
										<!-- <td><input type="button" value="查看/修改" onclick=""></td> -->
										<td><input type="button" value="删除" onclick="del('${var.deploymentId}');"></td>
										<td><input type="button" value="启动" onclick="running('${var.id}');"></td>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	<input type="button" value="新增" onclick="add();">
	<input type="button" value="取消" onclick="cancel();">
	<div class="list_page">
  
  <table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="background-color: #e6e7e8">
										<tr>
											<td width="40%" style="height:30px;">
												共
												<span class="right-text09">${page.pagecount}</span>
												页 | 第
												<span class="right-text09">${page.currentpage}</span>
												页
											</td>
											<td width="60%" align="right">
												[
												<a href="inbox/list?page=1"
													class="right-font08">首页</a> |
												<a
													href="inbox/list?page=${page.pagebefore}"
													class="right-font08">上一页</a> |
												<a
													href="inbox/list?page=${page.pageafter}"
													class="right-font08">下一页</a> |
												<a
													href=""
													class="right-font08">末页</a>       转到:<input id="yema" type="text" name="yema" style="width:30px;height:16px"/><input type="button" name="go" value="GO" onclick=""/>] 
											</td>
										</tr>
									</table>
  
  </div>
  </body>
  <script>
  	function add(){
  		location.href="<%=basePath%>activ/godeploy";
  	}
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}	
  	function del(Id){
  		
  			if(confirm("确认删除吗？")){
  				location.href="<%=basePath%>activ/del_deploy?deploymentId="+Id;
  			}
  		
  	}
  	function running(Id){
  				location.href="<%=basePath%>activ/runpro?pid="+Id;
  	}
  	function edit(Id){
  		location.href="<%=basePath%>inbox/goedit?DISPATCH_ID="+Id;
  	}
  </script>
</html>

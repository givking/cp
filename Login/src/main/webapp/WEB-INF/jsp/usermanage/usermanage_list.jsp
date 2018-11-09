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
						<th class="center">用户名字</th>
						<th class="center">用户角色</th>
						<th class="center">激活状态</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
							<tr>
								
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.name}</td>
										<td>${var.role_name}</td>
										<td><c:choose>
										<c:when test="${var.emailstatus=='0'}">未激活 </c:when>
										<c:when test="${var.emailstatus=='1'}">已激活 </c:when>
										</c:choose>
										</td>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	<input type="button" value="新增用户" onclick="add();">
	<input type="button" value="设置用户角色" onclick="addrole();">
	<input type="button" value="取消" onclick="cancel();">
  </body>
  <script>
  	function add(){
  		location.href="<%=basePath%>login/register";
  	}
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}	
  	function addrole(){
  		location.href="<%=basePath%>user/goadd";
  	}
  </script>
</html>

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
    
    <title>用户管理</title>
    
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
  <h3>这是用户角色分配页面</h3>
    <form action="user/rolelist" method="post">
    	用户名：
		<select name="USER_ID">
    	<c:forEach items="${varlist2}" var="var2" varStatus="vs2">
    		<option value="${var2.user_id}">${var2.name}</option>
    	</c:forEach>
    	</select>
    	分配角色：
    	<select name="ROLE_ID">
    	<c:forEach items="${varlist}" var="var" varStatus="vs">
    		<option value="${var.role_id}">${var.role_name}</option>
    	</c:forEach>
    	</select>
    	
    	<input type="submit" value="提交">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	
  </script>
</html>

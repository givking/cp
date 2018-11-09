<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  </head>
  
  <body>
  <h3>这是菜单管理页面</h3>
    <form action="menu/edit" method="post" name="Form" id="Form">
    	<input type="hidden" name="MENU_ID" id="MENU_ID" value="${MENU_ID}">
    	菜单名称：<input type="text" name="MENU_NAME" id="MENU_NAME" value="${MENU_NAME}">
    	菜单地址：<input type="text" name="MENU_URL" id="MENU_URL" value="${MENU_URL}">
    	<input type="button" value="提交" onclick="sub()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	function cancel(){
  		location.href="<%=basePath%>menu/list";
  	}
  	function sub(){
  		$("#Form").submit();
  	}
  </script>
</html>

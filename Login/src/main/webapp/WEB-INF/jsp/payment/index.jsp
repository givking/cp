<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  	<script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.all.min.js"> </script>
  </head>
  
  <body>
    <h1>这是一个支付功能测试首界面 </h1>
    <button>支付宝支付</button>
    <button>微信支付</button>
  </body>
	
</html>

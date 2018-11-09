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
    
    <title>My JSP 'mainpage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
  </head>
  
  <body>
  	<iframe name="FRAME" id="FRAME" src="user/list" width="90%" height="100%" style="float:right;"> 
	</iframe> 
  		  <h3>这是首页</h3>
  		  <ul>
  		  <li><input type="button" value="写邮件" onclick="mailwrite();"></li>
  		  <li><input type="button" value="收邮件" onclick="doreceive();"></li>
  		  <li><input type="button" value="收件箱" onclick="mailreceive();"></li>
  		  <li><input type="button" value="发件箱" onclick="mailsend();"></li>
  		  <li><input type="button" value="草稿箱" onclick=""></li>
  		  <li><input type="button" value="邮箱设置" onclick="mailsetting();"></li>
  		  <li><input type="button" value="返回" onclick="bb();"></li>
  		  </ul>
  		  
  </body>
  <script type="text/javascript">
  	function mailwrite(){
  		//document.getElementById('FRAME').src="mail/write";
  		$("#FRAME").attr("src","mail/write");
  	}
  	function mailsetting(){
  		$("#FRAME").attr("src","mail/goSetting");
  	}
  	function mailsend(){
  		$("#FRAME").attr("src","mail/sendlist");
  	}
  	function mailreceive(){
  		$("#FRAME").attr("src","mail/receivelist");
  	}
  	function doreceive(){
  		$("#FRAME").attr("src","mail/doreceive");
  	}
  	function bb(){
  		location.href="<%=basePath%>login/mainpage";
  	}
  </script>
</html>

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
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="static/css/ztree/demo.css">
	<link rel="stylesheet" type="text/css" href="static/css/ztree/zTreeStyle/zTreeStyle.css">
	
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<script type="text/javascript" charset="utf-8" src="static/plugin/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/plugin/ueditor/ueditor.all.min.js"> </script> 
 	<script type="text/javascript" src="static/js/ztree/jquery.ztree.all.js"></script>
  </head>
  
  <body>
  <h3>这是邮件接收页面</h3>
    <form action="" method="post" name="Form" id="Form" enctype="multipart/form-data">
    <!-- 发件人需要进行处理 -->
    	发件人：<input readonly style="width:300px" type="text" name="ADDRESSER" id=""ADDRESSER"" value="${ADDRESSER}" >
    	抄送：<input type="text" name="CC" id="CC">
    	密件抄送：<input type="text" name="BCC" id="BCC">
    	主题：<input readonly style="width:300px" type="text" name="SUBJECT" id="SUBJECT" value="${SUBJECT}"><br><br>
    	<div>${CONTENT}</div><br><br>
    	回复<input type="text" name="REPLY" id="REPLY">
    	<input type="submit" value="发送">
    </form>
  </body>
  <script>
    function su(){
    	$("#Form").submit();
    }
    function agree(){
    	$("#STATUS").val("3");//审批通过
    	$("#Form").submit();
    }
    function disagree(){
    	$("#STATUS").val("4");//审批不通过
    	$("#Form").submit();
    }
  	function cancel(){
  		location.href="<%=basePath%>inbox/list";
  	}
  
  </script>
</html>

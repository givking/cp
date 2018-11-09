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
  <h3>这是写邮件页面</h3>
    <form action="mail/send" method="post" name="Form" id="Form" enctype="multipart/form-data">
    	收件人：<input type="text" name="RECIPIENT" id="RECIPIENT" >
    	抄送：<input type="text" name="CC" id="CC">
    	密件抄送：<input type="text" name="BCC" id="BCC">
    	主题：<input type="text" name="SUBJECT" id="SUBJECT">
    	<input type="file" name="file" id="file" value="添加附件">
    	<script id="container" name="content" type="text/plain" ></script>
    	<input type="submit" value="发送">
    </form>
  </body>
  <script>
  	window.onload=function(){
  	 /* 实例化编辑器 */
  	var ue = UE.getEditor('container');
  	/* 保存路径 */
  	window.UEDITOR_CONFIG.savePath = ['Uploads'];
  	/* ue.addListener("ready", function () {
        // editor准备好之后才可以使用
        ue.setContent("${CONTENT}");
        }); */
    };
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

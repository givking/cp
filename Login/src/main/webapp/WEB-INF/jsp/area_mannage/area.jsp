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
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.all.min.js"> </script> 
  </head>
  
  <body>
  <h3>这是发文管理页面</h3>
    <form action="area/save" method="post" name="Form" id="Form">
    	<input type="hidden" name="AREA_ID" id="AREA_ID" value="${AREA_ID}">
    	<table>
    	<tr>
    	<td>市级名称：<input type="text" name="AREA_NAME" id="AREA_NAME" value="${AREA_NAME}"></td>
    	</tr>
    	<tr>
    	<td>级别：
    	<select>
    		<option></option>
    		<option></option>
    		<option></option>
    	</select></td>
    	</tr>
    	<tr>
    	<td>序号：<input type="text" name="NO" id="NO" value="${NO}"></td>
    	</tr>
    	</table>
    	<div id="xx"></div>
    	<input type="button" id="SUB" value="提交" onclick="sub()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	function cancel(){
  		location.href="<%=basePath%>area/list";
  	}
  	function sub(){
  		$("#Form").submit();
  	}
  </script>
</html>

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
    <form action="dispatch/edit" method="post" name="Form" id="Form">
    	<input type="hidden" name="DRAFTER" id="DRAFTER" value="${DRAFTER}">
    	<input type="hidden" name="DISPATCH_ID" id="DISPATCH_ID" value="${DISPATCH_ID}">
    	<input type="hidden" name="STATUS" id="STATUS" value="${STATUS}">
    	<input type="hidden" name="SUGGESTION" id="SUGGESTION" value="${SUGGESTION}">
    	<table>
    	<tr>
    	<td>文件名称：<input type="text" name="NAME" id="NAME" value="${NAME}">
    	校核人：<select name="CHECKER" id="CHECKER">
    	<c:forEach items="${varlist}" var="var" varStatus="vs">
    	<option <c:if test="${var.user_id==CHECKER}">selected="selected"</c:if> value="${var.user_id}">${var.name}</option>
    	</c:forEach>
    	</select>
    	</td>
    	</tr>
    	<tr>
    	<td>
    	审签人：<select name="APPROVER" id="APPROVER">
    	<c:forEach items="${varlist}" var="var" varStatus="vs">
    	<option <c:if test="${var.user_id==APPROVER}">selected="selected"</c:if> value="${var.user_id}">${var.name}</option>
    	</c:forEach>
    	</select>
    	创建时间：<input type="text" name="CREATE_TIME" id="CREATE_TIME"></td>
    	</tr>
    	<tr><td>
    	<script id="container" name="content" type="text/plain" >
    	</script></td></tr>
    	</table>
    	<div id="xx"></div>
    	<input type="button" id="SUB" value="提交" onclick="sub()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	 /* 实例化编辑器 */
  	var ue = UE.getEditor('container');
  	ue.addListener("ready", function () {
        // editor准备好之后才可以使用
        ue.setContent("${CONTENT}");
        });
  	function cancel(){
  		location.href="<%=basePath%>dispatch/list";
  	}
  	function sub(){
  		$("#Form").submit();
  	}
  	$(document).ready(function(){
  	var n=$("#SUGGESTION").val();
  	var s=$("#STATUS").val();
  	if(s=="2"||s=="4"){
  		var node=document.getElementById("node");
  		var newnode=document.createElement("textarea");
  		newnode.value=n;
  		$("#xx").append("修改意见：<textarea>"+n+"</textarea>");
  		}
  	});
  </script>
</html>

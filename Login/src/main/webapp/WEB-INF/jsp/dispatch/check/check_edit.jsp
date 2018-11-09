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
  <h3>这是校核管理页面</h3>
    <form action="check/edit" method="post" name="Form" id="Form">
    	<input type="hidden" name="DRAFTER" id="DRAFTER" value="${DRAFTER}">
    	<input type="hidden" name="STATUS" id="STATUS" >
    	<input type="hidden" name="DISPATCH_ID" id="DISPATCH_ID" value="${DISPATCH_ID}">
    	<input type="hidden" name="CHECKER" id="CHECKER" value="${CHECKER}">
    	<table>
    	<tr>
    	<td>文件名称：<input readonly="readonly" type="text" name="NAME" id="NAME" value="${NAME}">
    	起草人：<input readonly="readonly" name="DRAFTER" id="DRAFTER" value="${DRAFTER}">
    	</td>
    	</tr>
    	<tr>
    	<td>
    	审签人：<select name="APPROVER" id="APPROVER">
    	<option selected="selected" value="${APPROVER}">${APPROVER_NAME}</option>
    	</select>
    	创建时间：<input readonly="readonly" type="text" name="CREATE_TIME" id="CREATE_TIME"></td>
    	</tr>
    	<tr><td>
    	<script id="container" name="content" type="text/plain" >
    	</script></td></tr>
    	<tr>
    	<td>修改意见:<textarea name="SUGGESTION" id="SUGGESTION" placeholder="如校核不通，请填入意见"></textarea></td>
    	</tr>
    	</table>
    	<input type="button" value="校核通过" onclick="agree()">
    	<input type="button" value="校核不通过" onclick="disagree()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	 /* 实例化编辑器 */
  	var ue = UE.getEditor('container',{readonly:true});
  	ue.addListener("ready", function () {
        // editor准备好之后才可以使用
        ue.setContent("${CONTENT}");
        });
    function agree(){
    	$("#STATUS").val("1");//校核通过
    	$("#Form").submit();
    }
    function disagree(){
    	$("#STATUS").val("2");//校核不通过
    	$("#Form").submit();
    }
  	function cancel(){
  		location.href="<%=basePath%>check/list";
  	}
  </script>
</html>

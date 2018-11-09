<%@page import="org.apache.commons.lang.ObjectUtils"%>
<%@page import="org.activiti.engine.form.FormProperty"%>
<%@page import="org.activiti.engine.form.FormType"%>
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
	
  	<link rel="stylesheet" href="static/css/jquery-ui.css" /><!-- 日期框 -->
  	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="static/js/jquery-ui.js"></script>
  </head>
  
  <body>
  <h3>这是流程表单页面</h3>
    <form action="activ/startProcess" method="post" name="Form" id="Form" >
    <input type="hidden" name="pid" id="pid" value="${pid}">
    	<table>
    	<tbody>
			<c:forEach items="${varlist}" var="var" varStatus="vs">
			<c:set var="fpo" value="${var}"></c:set>
			<% FormType type = ((FormProperty)pageContext.getAttribute("fpo")).getType();
				String[] keys = {"datePattern"};
				for(String key : keys){
					pageContext.setAttribute(key,ObjectUtils.toString(type.getInformation(key)));
				}
			 %>
				<c:if test="${var.type.name=='date'}">
					<tr><td>${var.name}<input type="text" name="${var.id}" id="${var.id}" class="datepicker" data-data-format="yyyy-MM-dd"></td></tr>
				</c:if>
				<c:if test="${var.type.name=='string'}">
					<tr><td>${var.name}<input type="text" name="${var.id}" id="${var.id}" ></td></tr>
				</c:if>
				</c:forEach>
				<tr>
					<td><input type="button" value="删除" onclick=""></td>
					<td><input type="submit" value="启动" ></td>
				</tr>
			
		</tbody>
    	
       	</table>
    </form>
  </body>
  <script>
  $(document).ready(function() { 
  //日期框
		$(".datepicker" ).datepicker();
		$(".datepicker" ).datepicker("option", "dateFormat", "yy-mm-dd");
	});
  	function cancel(){
  		location.href="<%=basePath%>activ/list";
  	}
  	function sub(){
  		if($("#file").val()){
  			var ext=$("#file").val().substring($("#file").val().lastIndexOf(".")+1);
  			if(!(ext =="bpmn" || ext =="png")){
  				alert("请选择正确的文件");
  				return false;
  			}/* if(${result}=="fail"){
  				alert("文件过大");
  				return false;
  			} */
  			if(!$("#file").val()){
  			alert("请选择文件");
  			return false;
  		}else{
  			$("#Form").submit();
  		}; 
  	};
  	}
  	
  </script>
</html>

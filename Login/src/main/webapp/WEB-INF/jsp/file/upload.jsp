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
  <h3>这是文件上传页面</h3>
    <form action="file/upload" method="post" name="Form" id="Form" enctype="multipart/form-data">
    	<table>
    	<tr>
    	<td><input type="file" name="file" id="file"></td>
    	</tr>
    	<tr><td><p>上传格式：jpg,bmp,swf,png</p><p>上传大小不超过2M</p></td></tr>
    	<tr><td><input type="button" value="上传" onclick="sub();"></td>
    	<td><input type="button" value="取消" onclick="cancel()"></td></tr>
    	</table>
    </form>
  </body>
  <script>
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}
  	function sub(){
  		if($("#file").val()){
  			var ext=$("#file").val().substring($("#file").val().lastIndexOf(".")+1);
  			if(!(ext =="jpg" || ext =="bmp" || ext =="swf" || ext =="png")){
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

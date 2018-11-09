<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>



  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
    <h1>这是一个注册页面 <br></h1>
    <form action="/Login/login/save" name="FORM" id="FORM" method="post">
    <input type="hidden" name="USER_ID" id="USER_ID">
    	<table>
    	<tr><td>
    	<span>用户名：</span><input type="text" name="NAME" id="NAME"></td></tr>
    	<tr><td>
    	<span>密码：</span><input type="password" name="PASSWORD" id="PASSWORD"></td></tr>
    	<tr><td>
    	<span>确认密码：</span><input type="password" name="CPASSWORD" id="CPASSWORD"></td></tr>
    	<tr><td>
    	<span>邮箱：</span><input type="text" name="EMAIL" id="EMAIL"></td></tr>
    	<tr>
    	<td><input type="button" name="SUB" id="SUB" value="提交" onclick="sub();"></td>
    	<td><input type="button" name="CANCEL" id="CANCEL" value="取消" onclick="cancel();"></td>
    	</tr>
    	</table>
    </form>
    <%=basePath %>
  </body>
  <script>
  	function sub(){
  		var name=$("#NAME").val();
  		if(document.getElementById("NAME").value==""){
  			alert("用户名不能为空");
  			document.getElementById("NAME").focus();
  			return ;
  		}
  		if(document.getElementById("PASSWORD").value==""){
  			alert("密码不能为空");
  			document.getElementById("PASSWORD").focus();
  			return ;
  		}
  		if(document.getElementById("CPASSWORD").value!=document.getElementById("PASSWORD").value){
  			alert("密码不一致");
  			document.getElementById("CPASSWORD").focus();
  			return ;
  		}
  		//验证用户是否存在
  		$.post("login/hasName",{
  			'NAME':name
  		},function(data){
			if ("success" != data.result) {
				alert("用户名已存在");
			}else {
				$("#FORM").submit();
			}
  		});
  	}
  	function cancel(){
  		window.location.href="<%=basePath%>user/list";
  	}
  	
  		
  </script>
</html>

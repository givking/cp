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
  </head>
  
  <body>
    这是一个登陆界面 <br>
    <form action="login/clicklogin" name="LOGIN" id="LOGIN" method="post">
    	<table>
    		<tr>
    			<td>用户名：<input type="text" name="NAME" id="NAME"></td>
    		</tr>
    		<tr>
    		<td>密码：<input type="password" name="PASSWORD" id="PASSWORD"></td>
    		</tr>
    		<tr>
    		<td>验证码：<input type="text" name="CHECKCODE" id="CHECKCODE"></td>
    		<td><img src="PictureCheckCode" id="CreateCheckCode" align="middle"></td>  
            <td><a href="javascript:void()" onclick="myReload()"> 看不清,换一个</a></td>
    		</tr>
    		<tr>
    			<td><input type="button" name="LOGIN_BUTTON" id="LOGIN_BUTTON" value="登录" onclick="login()"/></td>
    			<!-- <td><input type="button" name="REGI" id="REGI" value="注册" onclick="regi();"/></td> -->
    		</tr>
    	</table>
    </form>
  </body>
  <script>
	  document.onkeydown=keyListener;
	function keyListener(e){
	    e = e ? e : event;// 兼容FF
	    if(e.keyCode == 13){
	        login();
	    }
	}
  
  function regi(){
	  	window.location.href="<%=basePath%>login/register";
  }
  function login(){
  		var name = $("#NAME").val();
  		var password = $("#PASSWORD").val();
  		var checkcode=$("#CHECKCODE").val();
  		//校验验证码
  		$.post("login/checkCode",{
  			'CHECKCODE':checkcode,
  			'NAME':name,
  			'PASSWORD':password
  		},
  			function(data){
				if ("errorcode" == data.result) {
					alert("验证码输入错误");
			}else if("errorpass" == data.result) {
					alert("密码错误");
			}else if("errorname" == data.result) {
					alert("用户不存在");
			}else{
				$("#LOGIN").submit();
			}
  		});
  		
  		
  		<%-- location.href='<%=basePath%>login/clicklogin?NAME='+name; --%>
  		
            
  };
  function myReload() {  
    document.getElementById("CreateCheckCode").src = document.getElementById("CreateCheckCode").src+ "?nocache=" + new Date().getTime();
            };
  </script>
</html>

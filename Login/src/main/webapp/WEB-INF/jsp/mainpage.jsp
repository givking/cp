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
  
  <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">首页</a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-right"  >
        <li><a href="#">你好，${session_user.name}</a></li>
        <li><a href="javascript:void" onclick="logout();">注销 </a></li>
          
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
  
  
  	<iframe src="user/list" width="500" height="500" style="float:right;"> 
	</iframe> 
  		  <h3>这是首页</h3>
  		  <input type="button" value="菜单管理" onclick="addmenu()">
  		  <input type="button" value="用户管理" onclick="adduser()">
  		  <ul>
  		  	
  		  	<c:forEach items="${varlist}" var="var" varStatus="vs">
  		  		<li><a href="${var.menu_url}">${var.menu_name}</a></li>
  		  	</c:forEach>
  		  </ul>
  </body>
  <script type="text/javascript">
  	function addmenu(){
  		location.href="<%=basePath%>menu/list";
  	}
  	function adduser(){
  		location.href="<%=basePath%>user/list";
  	}
  	function logout(){
  		alert(1);
  		location.href="<%=basePath%>login/logout";
  	}
  </script>
</html>

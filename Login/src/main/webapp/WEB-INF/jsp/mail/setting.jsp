<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>邮箱设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="static/css/bootstrap.min.css" rel="stylesheet" />
	
	 <script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="static/jquery.tips.js"></script>
	
  </head>
  
  <body>
	  <form  action="mail/saveSetting" method="post">
	  
	  		<div style="width:900px;height: 250px;border-bottom: 1px dashed grey;">
	  			<div style="padding-left: 30px;padding-top: 10px;">
	  				<span style="font-size:14px;font-weight: bold;">账号信息</span>
	  			</div>
	  			<div style="margin-left: 40px;padding-top:10px;">
	  			
				    <label for="nickName">用户别名 <small>(示例 : 张三,李四,小明)</small></label>
				    <input name="NICK_NAME" type="text" value="${NICK_NAME }" style="width:500px;height: 30px;" class="form-control" id="nickName" >
				    
				    <label for="email">邮箱地址</label>
				    <input type="email" name="EMAIL" value="${EMAIL }" style="width:500px;height: 30px;" class="form-control" id="email" >
				    
				    <label for="password">邮箱登录密码</label>
				    <input type="password" name="PASSWORD" value="${PASSWORD }" style="width:500px;height: 30px;" class="form-control" id="password" placeholder="Password">
				 </div>
	  		 </div>
	  		<div style="width:900px;height: 250px;border-bottom: 1px dashed grey;">
	  			<div style="padding-left: 30px;padding-top: 10px;">
	  				<span style="font-size:14px;font-weight: bold;">SMTP/POP3服务器配置</span>
	  				<a href="<%=basePath %>mail/configList.do" target="_blank">常用邮箱配置一览表</a>
	  			</div>
	  			<div style="margin-left: 40px;padding-top:10px;">
				    <label class="inline" for="smtpHost">smtp服务器地址及端口 <small>(示例:smtp.sina.cn:25   可从邮件服务提供商处获得)</small></label>
				    <input type="text" style="width:500px;height: 30px;" value="${SMTP_HOST}" name="SMTP_HOST" class="form-control text-inline" id="smtpHost" >
				    &nbsp;&nbsp; 端口&nbsp;&nbsp;
				    <input type="text" style="width:50px;height: 30px;" value="${SMTP_PORT}" name="SMTP_PORT" class="form-control text-inline" id="smtpPort" >
				    <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <div class="checkbox">
					        <label>
					          <input type="checkbox" name="IS_SMTP_SSL" ${IS_SMTP_SSL=="0"?"":"checked='checked'" }> 该邮件服务器是否使用SSL加密访问方式
					        </label>
					      </div>
					    </div>
					  </div>
				 </div>
	  			<div style="margin-left: 40px;padding-top:10px;">
				    <label class="inline" for="popHost">pop3服务器地址及端口 <small>(示例:pop.sina.com:110   可从邮件服务提供商处获得)</small></label>
				    <input type="text" style="width:500px;height: 30px;" value="${POP_HOST }" name="POP_HOST"  class="form-control text-inline" id="popHost" >
				    &nbsp;&nbsp; 端口&nbsp;&nbsp;
				    <input type="text" style="width:50px;height: 30px;" value="${POP_PORT }" name="POP_PORT" class="form-control text-inline" id="popPort" >
				    <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <div class="checkbox">
					        <label>
					          <input type="checkbox" name="IS_POP_SSL" ${IS_POP_SSL=="1"?"checked='checked'":"" }> 该邮件服务器是否使用SSL加密访问方式
					        </label>
					      </div>
					    </div>
					  </div>
				 </div>
	  		 </div>
  			<br/>
	 		 <input class="btn btn-default"  type="submit" value="保存设置">
	  </form>
  </body>
</html>

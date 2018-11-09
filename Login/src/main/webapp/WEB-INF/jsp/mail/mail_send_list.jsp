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
    
    <title>My JSP 'menu_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table>
				
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">邮件主题</th>
						<th class="center">收件人</th>
						<th class="center">创建时间</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
				<input type="hidden" name="id" value="${var.mail_send_id}">
							<tr>
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.subject}</td>
										<td>${var.recipient}</td>
										<td>${var.create_time}</td>
										<td><input type="button" value="查看/修改" <%-- onclick="edit('${var.mail_send_id}' );"--%>></td>
							</tr>
				</c:forEach>
				</tbody>
	</table>
  </body>
  <script>
  	function add(){
  		location.href="<%=basePath%>dispatch/goadd";
  	}
  	function cancel(){
  		location.href="<%=basePath%>mail/gomainpage";
  	}	
  	function del(Id){
  		
  			if(confirm("确认删除吗？")){
  				location.href="<%=basePath%>receipt/del?DISPATCH_ID="+Id;
  			}
  		
  	}
  	function edit(Id){
  		location.href="<%=basePath%>inbox/goedit?DISPATCH_ID="+Id;
  	}
  </script>
</html>

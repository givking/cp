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
						<th class="center">菜单名字</th>
						<th class="center">菜单地址</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
				<input type="hidden" name="id" value="${var.menu_id}">
							<tr>
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.menu_name}</td>
										<td>${var.menu_url}</td>
										<td><input type="button" value="修改" onclick="edit('${var.menu_id}');"></td>
										<td><input type="button" value="删除" onclick="del('${var.menu_id}');"></td>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	<input type="button" value="新增" onclick="add();">
	<input type="button" value="取消" onclick="cancel();">
  </body>
  <script>
  	function add(){
  		location.href="<%=basePath%>menu/goadd";
  	}
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}	
  	function del(Id){
  		
  			if(confirm("确认删除吗？")){
  				location.href="<%=basePath%>menu/del?MENU_ID="+Id;
  			}
  		
  	}
  	function edit(Id){
  		location.href="<%=basePath%>menu/goedit?MENU_ID="+Id;
  	}
  </script>
</html>

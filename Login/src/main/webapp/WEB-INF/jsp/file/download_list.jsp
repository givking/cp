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
	<script src="static/jquery-1.7.2.js"></script>
	<script src="static/js/bootstrap.min.js"></script>

  </head>
  <form action="downfile/search" name="form" id="form" method="post">
  <table>
  	<tr>
		<td><input type="text" name="SEARCH" id="SEARCH" ></td>
		<td><input type="submit" name="SEARCHBUT" id="SEARCHBUT" value="搜索" "></td>	
	</tr>	
  </table>
  </form>
			<!-- 检索  -->
  <body>
   <table>
				
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">文件名字</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
							<tr>
								
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.file_name}</td>
										<td><a href="javascript:void(0)" onclick="del('${var.file_id}')">删除</a></td>
										<td><a href="javascript:void(0)" onclick="load('${var.file_id}')">下载</a></td>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	
	<input type="button" value="返回" onclick="cancel();">
  </body>
  <script>
  	
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}
  	function load(Id){
  		location.href="<%=basePath%>downfile/download?FILE_ID="+Id;
  	}
  	function del(Id){
  	if(confirm("确认删除吗？")){
  		location.href="<%=basePath%>file/del?FILE_ID="+Id;
  		}
  	};
  </script>
</html>

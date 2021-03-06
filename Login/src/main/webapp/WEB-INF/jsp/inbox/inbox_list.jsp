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
						<th class="center">文件名字</th>
						<th class="center">起草人</th>
						<th class="center">校核人</th>
						<th class="center">审批人</th>
						<th class="center">创建时间</th>
						<th class="center">签收状态</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
				<input type="hidden" name="id" value="${var.dispatch_id}">
							<tr>
								<td  style="width: 30px;">${vs.index+1}</td>
										<td>${var.name}</td>
										<td>${var.drafter}</td>
										<td>${var.checker_name}</td>
										<td>${var.approver_name}</td>
										<td>${var.create_time}</td>
										<td><c:choose>
										<c:when test="${var.sign=='0'}">未查看</c:when>
										<c:when test="${var.sign=='1'}">已签收</c:when>
										<c:when test="${var.sign=='2'}">拒签收</c:when>
										</c:choose></td>
										<td><input type="button" value="查看/修改" onclick="edit('${var.dispatch_id}');"></td>
										<%-- <td><input type="button" value="删除" onclick="del('${var.dispatch_id}');"></td> --%>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	<input type="button" value="新增" onclick="add();">
	<input type="button" value="取消" onclick="cancel();">
	<div class="list_page">
  
  <table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="background-color: #e6e7e8">
										<tr>
											<td width="40%" style="height:30px;">
												共
												<span class="right-text09">${page.pagecount}</span>
												页 | 第
												<span class="right-text09">${page.currentpage}</span>
												页
											</td>
											<td width="60%" align="right">
												[
												<a href="inbox/list?page=1"
													class="right-font08">首页</a> |
												<a
													href="inbox/list?page=${page.pagebefore}"
													class="right-font08">上一页</a> |
												<a
													href="inbox/list?page=${page.pageafter}"
													class="right-font08">下一页</a> |
												<a
													href=""
													class="right-font08">末页</a>       转到:<input id="yema" type="text" name="yema" style="width:30px;height:16px"/><input type="button" name="go" value="GO" onclick=""/>] 
											</td>
										</tr>
									</table>
  
  </div>
  </body>
  <script>
  	function add(){
  		location.href="<%=basePath%>dispatch/goadd";
  	}
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
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

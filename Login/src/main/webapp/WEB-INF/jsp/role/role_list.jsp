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
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  </head>
  
  <body>
  <form action="role/dolist" name="Form" method="post">
   <table>
				
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">角色名字</th>
						<th class="center">角色菜单权限</th>
					</tr>
				</thead>
										
				<tbody>
				<c:forEach items="${varlist}" var="var" varStatus="vs">
							
							<tr>
								
								<td  style="width: 30px;">${vs.index+1}</td>
								
										<td>${var.role_name}</td>
									<c:forEach items="${varlist2}" var="var2" varStatus="vs2">
										<td><label><input type="checkbox" name="MENU_ID" id="MENU_ID" value="${var.role_id}_${var2.menu_id}">${var2.menu_name}</label></td>
									</c:forEach>
							</tr>
				</c:forEach>
				</tbody>
	</table>
	<input type="button" value="新增用户" onclick="add();">
	<input type="button" value="设置角色菜单权限" onclick="addauth();">
	<input type="button" value="取消" onclick="cancel();">
	</form>
  </body>
  <script>
  $(document).ready(function(){
  	 var cvalue = "${MENU_ID}"; 
  	 cvalue = cvalue.substring(0, cvalue.length - 1);
  	 var cvalueArray = cvalue.split(",");
  	for(i=0;i<cvalueArray.length;i++){
  		$("input[value='" + cvalueArray[i] + "']").prop("checked", true);
  		//alert($("#MENU_ID").attr("value"));
  		/* if(cvalueArray[i]==$("#MENU_ID").value()){
  			$("#MENU_ID").prop("checked");
  		}; */
  	};
  });
  $("input[name='MENU_ID']").click(function(){
  	  var checkedValues=[];
  	  checkedValues.push($(this).val());
  	  var json = JSON.stringify(checkedValues);
  	  if($(this).prop("checked")){
  	  	//add	
  	  	$.post(
	  "role/auth",
	  {'MENU_ID':json}
	  );
  	  } else {
  	  	//delete
  	  	 $.post("role/delauth",
	  {'MENU_ID':json});
	  };
  	  
  	 /*  var MENU_ID=$("input[name='MENU_ID']");
  	  var checkedValues = [];
  	  for(var i = 0; i < MENU_ID.length; i ++){
  	  	if($(MENU_ID[i]).prop("checked")){
  	  		checkedValues.push($(MENU_ID[i]).val());
  	  	}
  	  } */
  	 
  	  
  	 /*  if(json!=null){
	  $.post(
	  "role/auth",
	  {'MENU_ID':json}
	  );
	  }else{
	  $.post("role/delauth",
	  {'ROLE_ID'})
	  }; */
	  });
	  
  	
  	function cancel(){
  		location.href="<%=basePath%>login/mainpage";
  	}	
  	function addauth(){
  		location.href="<%=basePath%>role/goadd";
  	};
  </script>
</html>

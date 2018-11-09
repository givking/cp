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
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="static/css/ztree/demo.css">
	<link rel="stylesheet" type="text/css" href="static/css/ztree/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="static/js/ztree/jquery.ztree.all.js"></script>
  </head>
  
  <body>
  <h3>这是发文管理页面</h3>
    <form action="area/csave" method="post" name="Form" id="Form">
    	<input type="hidden" name="AREA_ID" id="AREA_ID" value="${AREA_ID}">
    	<table>
    	<tr>
    	<td>名称：<input type="text" name="AREA_NAME" id="AREA_NAME" value="${AREA_NAME}"></td>
    	</tr>
    	<tr>
    	<td>级别：<input readonly type="text" name="LEVEL" id="LEVEL">
    	<input readonly type="text" name="LEVEL_ID" id="LEVEL_ID">
    	<div>
   		<ul id="treeDemo" class="ztree"></ul>
		</div></td>
    	</tr>
    	<tr>
    	<td>序号：<input type="text" name="NO" id="NO" value="${NO}"></td>
    	</tr>
    	</table>
    	<div id="xx"></div>
    	<input type="button" id="SUB" value="提交" onclick="sub()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script LANGUAGE="JavaScript">
   var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {
   			async : {
				enable : true,
				type : 'post',
				url : "area/tree"
			},
	   		check: {
				enable: true,
				chkStyle:"radio",
				radioType:"all",
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				key : {
					name : "area_name"
				},
				simpleData : {
					enable : true,
					idKey : "area_id",
					pIdKey : "parent_id",
					rootPId : '0'
				//根节点  
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
   };
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   /* var zNodes = [
   {name:"test1", open:true, children:[
      {name:"test1_1"}, {name:"test1_2"}]},
   {name:"test2", open:true, children:[
      {name:"test2_1"}, {name:"test2_2"}]}
   ]; */
   function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			var id="";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].area_name + ",";
				id +=nodes[i].area_id + ",";
			}
			/* 去掉最后的逗号 */
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (id.length > 0 ) id = id.substring(0, id.length-1);
			var cityObj = $("#LEVEL");
			cityObj.attr("value", v);
			var cityObj = $("#LEVEL_ID");
			cityObj.attr("value", id);
		}
	   $(document).ready(function(){
	      $.fn.zTree.init($("#treeDemo"), setting);
	     /*  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	      zTree.checkAllNodes(true); */
	   });
  /*  $(document).ready(function(){
      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
   }); */
  </script>
  <script>
  	function cancel(){
  		location.href="<%=basePath%>area/clist";
  	}
  	function sub(){
  		$("#Form").submit();
  	}
  </script>
</html>

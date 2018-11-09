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
    
    <title>My JSP 'test.jsp' starting page</title>
    
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
  	<script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.all.min.js"> </script>
  	<script type="text/javascript" src="static/js/ztree/jquery.ztree.all.js"></script>
  </head>
  
  <body> 
    这是一个ztree测试界面 <br>
    <form action="test/test2" method="post" id="form" name="form">
	 <div>
	   <ul >
	   <li>分发：<input id="DISTRIBUTE" name="DISTRIBUTE" type="text" readonly onclick="showmenu();"/></li>
	   </ul>
	 </div>
	 <div id="menuContent" class="menuContent" style="display:none;">
     <ul id="treeDemo" class="ztree" ></ul>
     </div>
     <input type="submit" value="提交">
     <input type="button" value="返回" onclick="back();">
     </form>
    </body>
	<script type="text/javascript">
	function back(){
		window.location.href="<%=basePath%>login/mainpage";
	}
	   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	   var setting = {
	   		async : {
				enable : true,
				type : 'post',
				url : "test/testfun"
			},
	   		check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				key : {
					name : "name"
				},
				simpleData : {
					enable : true,
					idKey : "user_id",
					pIdKey : "role_id",
					rootPId : '0'
				//根节点  
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
	   };
	   /* // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
	   var zNodes = [
	   {name:"test1", open:false,nocheck:true, children:[
	      {name:"test1_1"}, {name:"test1_2"}]},
	   {name:"test2", open:true,nocheck:true, children:[
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
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#DISTRIBUTE");
			cityObj.attr("value", v);
		}
	   $(document).ready(function(){
	      $.fn.zTree.init($("#treeDemo"), setting);
	     /*  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	      zTree.checkAllNodes(true); */
	   });
	   function showmenu(){
	   var cityObj = $("#DISTRIBUTE");
			var cityOffset = $("#DISTRIBUTE").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
			}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!( event.target.id == "DISTRIBUTE" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
	</script>
</html>

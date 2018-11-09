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
	<script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/js/ueditor/ueditor.all.min.js"> </script> 
 	<script type="text/javascript" src="static/js/ztree/jquery.ztree.all.js"></script>
  </head>
  
  <body>
  <h3>这是审批管理页面</h3>
    <form action="inbox/edit" method="post" name="Form" id="Form">
    	<input type="hidden" name="DRAFTER" id="DRAFTER" value="${DRAFTER}">
    	<input type="hidden" name="STATUS" id="STATUS" value="${STATUS}">
    	<input type="hidden" name="DISPATCH_ID" id="DISPATCH_ID" value="${DISPATCH_ID}">
    	<input type="hidden" name="CHECKER" id="CHECKER" value="${CHECKER}">
    	<input type="hidden" name="RECIPIENT" id="RECIPIENT" value="${RECIPIENT}">
    	<table>
    	<tr>
    	<td>文件名称：<input readonly="readonly" type="text" name="NAME" id="NAME" value="${NAME}">
    	起草人：<input readonly="readonly" name="DRAFTER" id="DRAFTER" value="${DRAFTER}">
    	</td>
    	</tr>
    	<tr>
    	<td>
    	审批人：<select name="APPROVER" id="APPROVER">
    	<option selected="selected" value="${APPROVER}">${APPROVER_NAME}</option>
    	</select>
    	创建时间：<input readonly="readonly" type="text" name="CREATE_TIME" id="CREATE_TIME"></td>
    	</tr>
    	<tr><td>
    	<script id="container" name="content" type="text/plain" >
    	</script></td></tr>
    	
    	</table>
		   
		   分发：<input id="DISTRIBUTE" name="DISTRIBUTE" type="text" readonly value="${DISTRIBUTE}"/>
		   传阅：<input id="TRANSFER" name="TRANSFER" type="text" readonly value="${TRANSFER}"/>
    	拟办意见:<textarea name="PROPOSE" id="PROPOSE" placeholder="请填入拟办意见"></textarea>
    	承办人：<select name="CHECKER" id="CHECKER">
    	<c:forEach items="${varlist}" var="var" varStatus="vs">
    	<option value="${var.user_id}">${var.name}</option>
    	</c:forEach>
    	</select>
    	签收：<select name="SIGN" id="SIGN">
    		<option value="0" selected="selected"></option>
    		<option value="1">已签收</option>
    		<option value="2">拒签收</option>
    	</select>
    	
    	<div id="menuContent" class="menuContent" style="display:none;">
	     <ul id="treeDemo" class="ztree" ></ul>
	    </div>
	    <div id="menuContent2" class="menuContent" style="display:none;">
	     <ul id="treeDemo2" class="ztree" ></ul>
	    </div>
    	<input type="button" value="提交" onclick="su();" >
    	<input type="button" value="审批通过" onclick="agree()">
    	<input type="button" value="审批不通过" onclick="disagree()">
    	<input type="button" value="取消" onclick="cancel()">
    </form>
  </body>
  <script>
  	 /* 实例化编辑器 */
  	var ue = UE.getEditor('container',{readonly:true});
  	ue.addListener("ready", function () {
        // editor准备好之后才可以使用
        ue.setContent("${CONTENT}");
        });
    function su(){
    	$("#Form").submit();
    }
    function agree(){
    	$("#STATUS").val("3");//审批通过
    	$("#Form").submit();
    }
    function disagree(){
    	$("#STATUS").val("4");//审批不通过
    	$("#Form").submit();
    }
  	function cancel(){
  		location.href="<%=basePath%>inbox/list";
  	}
  	/* //树结构搭建
  	var setting = {
	   		async : {
				enable : true,
				type : 'post',
				url : "receipt/tree"
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
	   //树2结构搭建
  	var setting2 = {
	   		async : {
				enable : true,
				type : 'post',
				url : "receipt/tree"
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
				beforeClick: beforeClick2,
				onCheck: onCheck2
			}
	   };
	    function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		function beforeClick2(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo2");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			//分发
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#DISTRIBUTE");
			cityObj.attr("value", v);
		}
		function onCheck2(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
			nodes = zTree.getCheckedNodes(true),
			//传阅
			v2 = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v2 += nodes[i].name + ",";
			}
			if (v2.length > 0 ) v2 = v2.substring(0, v2.length-1);
			var cityObj2 = $("#TRANSFER");
			cityObj2.attr("value", v2);
		}
  	$(document).ready(function(){
  		$.fn.zTree.init($("#treeDemo"), setting);
  		$.fn.zTree.init($("#treeDemo2"), setting2);
  	});
	  	function showmenu(){
		   var cityObj = $("#DISTRIBUTE");
				var cityOffset = $("#DISTRIBUTE").offset();
				$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown);
		}
		function showmenu2(){
			  var cityObj = $("#TRANSFER");
				var cityOffset = $("#TRANSFER").offset();
				$("#menuContent2").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			
				$("body").bind("mousedown", onBodyDown2);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function hideMenu2() {
			$("#menuContent2").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown2);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "DISTRIBUTE" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		function onBodyDown2(event) {
			if (!( event.target.id == "TRANSFER" || event.target.id == "menuContent2" || $(event.target).parents("#menuContent2").length>0)) {
				hideMenu2();
			}
		} */
  </script>
</html>

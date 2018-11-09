<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="financial_review/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="FINANCIAL_REVIEW_ID" id="FINANCIAL_REVIEW_ID" value="${pd.FINANCIAL_REVIEW_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">项目名称:</td>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入项目名称" title="项目名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">项目类型:</td>
				<td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="32" placeholder="这里输入项目类型" title="项目类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">审核人:</td>
				<td><input type="text" name="APPROVER" id="APPROVER" value="${pd.APPROVER}" maxlength="32" placeholder="这里输入审核人" title="审核人"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">评审机构:</td>
				<td><input type="text" name="ORG" id="ORG" value="${pd.ORG}" maxlength="32" placeholder="这里输入评审机构" title="评审机构"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">经办人:</td>
				<td><input type="text" name="DRAFTER" id="DRAFTER" value="${pd.DRAFTER}" maxlength="32" placeholder="这里输入经办人" title="经办人"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">送审时间:</td>
				<td><input type="text" name="TIME" id="TIME" value="${pd.TIME}" maxlength="32" placeholder="这里输入送审时间" title="送审时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">任务分类查看:</td>
				<td><input type="text" name="VIEW_TYPE" id="VIEW_TYPE" value="${pd.VIEW_TYPE}" maxlength="32" placeholder="这里输入任务分类查看" title="任务分类查看"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">未送审原因:</td>
				<td><input type="text" name="SUGGESTION" id="SUGGESTION" value="${pd.SUGGESTION}" maxlength="32" placeholder="这里输入未送审原因" title="未送审原因"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">附件上传:</td>
				<td>
				<div id="domadd">
					<input  type="file" name="file" id="file0" accept=""/>
					<a id="btn_upload" href="javascript:upload(0)" class="btn btn-mini btn-primary">上传</a>
				</div>
				<a id="btn_upload" href="javascript:add()" class="btn btn-mini btn-primary">添加</a>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="plugins/upload2/jquery.upload2.js"></script>
		<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		var i=0;
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			$('#file').ace_file_input({
				no_file:'请选择文件 ...',
				btn_choose:'选择',
				btn_change:'更改'
			});$('#file0').ace_file_input({
				no_file:'请选择文件 ...',
				btn_choose:'选择',
				btn_change:'更改'
			});
			
		});
		
		 function upload(Id){
		 alert(Id);
		 var fileid="#file"+Id;
		 var filename="input[name=file"+Id+"]";
		 alert(filename);
			$("input[name=file]").upload({
		      url: 'financial_review/upload.do',
		      // 其他表单数据
		      params: {},
		      // 上传完成后, 返回json, text
		      dataType: 'json',
		      onSend: function (obj, str) { return true; },
		      // 上传之后回调
		      onComplate: function (data) {
		      	if(data.RESULT=="repeat"){
		      		alert("文件已存在");
		      	}else{
		      		alert("上传成功");
		      	}
		      }
		    });
		    $("input[name=file]").upload("ajaxSubmit");
		} 
		function add(){
			i++;
			var addFileId='#file'+i;
			$("#domadd").append('<input  type="file" name="file" id=file'+i+' accept=""/>'+'<a id="btn_upload" href="javascript:upload()" class="btn btn-mini btn-primary">上传</a>');
		    $(addFileId).ace_file_input({
				no_file:'请选择文件 ...',
				btn_choose:'选择',
				btn_change:'更改'
			});
		}
		<%-- var diag;
		function openUploadWindow(){
			top.jzts();
			diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="上传";
			 diag.URL = "<%=basePath%>financial_review/goUpload.do";
			 diag.Width = 300;
			 diag.Height = 200;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		} --%>
		
		</script>
</body>
</html>
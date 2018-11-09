<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="static/css/jquery-ui.css">
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
  	<script type="text/javascript" charset="utf-8" src="static/js/jquery-ui.js"></script>
  </head>
  
  <body>
    这是一个jqueryUI测试界面 <br>
 <!-- progressbar -->
     <div  id="probar"></div>
     <input type="button" onclick="test();" value="测试按钮">
    </body>
	<script>
	/* function test(){
		$("#probar").progressbar("value",50);
	} */
	$(function(){
		$.widget( "custom.progressbar", {
 
    options: {
        value: 0
    },
 
    _create: function() {
        var progress = this.options.value + "%";
        this.element
            .addClass( "progressbar" )
            .text( progress );
    },
 
    // Create a public method.
    value: function( value ) {
 
        // No value passed, act as a getter.
        if ( value === undefined ) {
            return this.options.value;
        }
 
        // Value passed, act as a setter.
        this.options.value = this._constrain( value );
        var progress = this.options.value + "%";
        this.element.text( progress );
    },
 
    // Create a private method.
    _constrain: function( value ) {
        if ( value > 100 ) {
            value = 100;
        }
        if ( value < 0 ) {
            value = 0;
        }
        return value;
    }
});
	});
		/* $("#probar").progressbar();//进度条初始化
		$("#probar").progressbar("value",80);//调用其方法并设置属性
		$("#probar").progressbar({
			change:function(){
			alert("progressbar haven been changed");
			}
		}); */
		
		
	</script>
</html>

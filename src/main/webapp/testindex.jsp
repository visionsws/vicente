<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/test/jsp/test-common.jsp" %>
<%@ page import="com.fusio.tag.commons.prop.ConfigProperties" %>
<%@ page import="com.fusio.tag.commons.prop.TimestampConfig" %>
<%@ page import="com.fusio.tag.commons.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){ 
		$("a[name='controllerName']").each(function(i){
			$(this).attr("href", "${ctx_path }/debugController/forward/" + $(this).html());
		});
	}); 
</script>
<style type="text/css">
	a {
		line-height: 25px;
		display: block;
		width: 1%;
	}
	a #refresh {
		display: inline;
	}
</style>
</head>
<body>

<%
	String time = TimestampConfig.get("package.timestamp");
	try {
		Date gmtDate = DateUtil.parseDateTime(time);
		Date now = DateUtil.addHours(gmtDate, 8);
		time = DateUtil.getYearMonDayHrMinStr_(now);
	} catch(Exception e) {
	}
	
	
%>

<h3><span style="color: red;"></span>datatransfer</span>
</h3>
<h4>maven打包时间戳: <span style="color: red;"><%=time %></span></h4>
<b>刷新配置文件</b><a href="${ctx_path}/debugController/refreshConfig" target="_blank">刷新</a>
	
<p><b><!-- 写上前端Controller的名字, 访问对应的Controller测试页面 --></b></p>
<a name="controllerName">tag</a>
<a name="controllerName">test</a>

</body>

<script type="text/javascript">
function refreshConfig() {
	$.ajax({
		type : "POST",
		url : "${ctx_path}/debugController/refreshConfig",
		success : function(jsonResult) {
			if (jsonResult.flag == true) {
				alert("刷新成功!");
			} else {
				alert("刷新失败,请重试");
			} 
		}
	});
}
</script>
</html>
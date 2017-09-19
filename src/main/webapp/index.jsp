<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/test/jsp/test-common.jsp"%>
<%@ page import="com.fusio.tag.commons.prop.ConfigProperties"%>
<%@ page import="com.fusio.tag.commons.prop.TimestampConfig"%>
<%@ page import="com.fusio.tag.commons.utils.DateUtil"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标签树</title>
</head>
<body>
	<br>
	<br>
	<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/tagFront/toTagTreePage">点击查看标签树</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/tagFront/getFullTagTree" title="调试用" style="text-decoration: none;">.</a>

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
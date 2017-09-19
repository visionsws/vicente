<%-- 测试页面的公共头 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx_path" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx_path }/resources/js/jquery/1.11.1/jquery.js"></script>
<%-- <link href="${ctx_path }/resources/css/style.css" rel="stylesheet" type="text/css"/> --%>
<script type="text/javascript">
	function ajaxPost() {
		$.ajax({
			type : "POST",
			url : _context + "/debugController/refreshConfig",
			success : function(jsonResult) {
				if (jsonResult.flag == "1") {
					alert("刷新成功!");
				} else {
					alert("刷新失败,请重试");
				} 
			}
		});
	}
</script>
<style type="text/css">
	.ap {
		font-size: 10px;
		display: block;
		line-height: 25px;
		margin-bottom: 10px;
	}
	.intf_desc {
		font-weight: bold;
		font-size: 17px;
	}
	.intf {
		margin-top: 30px;
	}
</style>
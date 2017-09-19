<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="test-common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){ 
		$("form").each(function(i){
			$(this).attr("action", $(this).attr("action") + "?forceJson");
		});
	}); 
</script>
</head>
<body>
	<p><b>${page }</b></p>
	<c:set var="base_path" value="${ctx_path }/${page }"></c:set>

	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">插入t_test并查询所有</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/test"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	
	
	
	
	
	
	<script type="text/javascript">
	</script>
</body>
</html>
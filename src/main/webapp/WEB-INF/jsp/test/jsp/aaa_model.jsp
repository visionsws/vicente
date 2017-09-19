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
	<span class="intf_desc">上传文件</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/uploadFile"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post" enctype="multipart/form-data"><!-- 修改 -->
		0图片1音频2视频<input name="materialType" value="0"><br>
		图片上传<input type="file" name="file"><br>
		素材组<input name="materialGroupId" value="0"><br>
		素材名<input name="materialName" value="xxxx的素材名"><br>
		素材描述<input name="materialDesc" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">初始化素材上传列表</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/toPicPage"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		pageSize<input name="pageSize" value="10"><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	
	
	
	
	
	
	
	
	<script type="text/javascript">
		
	</script>
</body>
</html>
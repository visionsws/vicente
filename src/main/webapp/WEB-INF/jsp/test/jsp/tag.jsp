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
	<span class="intf_desc">分类,查询所有分类</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryAllCatg"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询某个标签的信息</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryTagInfo"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		标签id<input name="id" value="00223d4f9fc746179b3e3b2b079e1357"><br>
		标签code<input name="code" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询所有标签信息</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryAllTagInfo"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询直接父标签</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryDirectParentTag"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		标签id<input name="id" value="00223d4f9fc746179b3e3b2b079e1357"><br>
		标签code<input name="code" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询所有父标签(数组排序是关系由近到远)</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryAllParentTags"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		标签id<input name="id" value="00223d4f9fc746179b3e3b2b079e1357"><br>
		标签code<input name="code" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询所属的分类(直接分类)</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryCatgBelongedTo"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		标签id<input name="id" value="00223d4f9fc746179b3e3b2b079e1357"><br>
		标签code<input name="code" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询所有子标签(不包括自身)</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryAllChildrenTags"></c:set><!-- 修改 -->
	<span class="ap">${action_path }</span>
	<form action="${action_path }" method="post"><!-- 修改 -->
		标签id<input name="id" value="00223d4f9fc746179b3e3b2b079e1357"><br>
		标签code<input name="code" value=""><br>
		<input type="submit">
	</form>
	</div>
	<!-- end -->
	
	
	<!-- begin -->
	<div class="intf">
	<span class="intf_desc">查询标签树(层级图谱)</span><!-- 修改 -->
	<c:set var="action_path" value="${base_path }/qryTagGraph"></c:set><!-- 修改 -->
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
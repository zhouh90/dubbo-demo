<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>定时任务列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
	</head>
	<body class="templatemo-bg-gray">
		<div class="container">
			<h1 class="margin-bottom-15">定时任务列表</h1>
			<div id="search" class="row">
				<div class="col-md-3">
					<input type="text" id="s-taskName" class="form-control" placeholder="请输入任务类名">
				</div>
				<div class="col-md-3">
					<input type="text" id="s-groupName" class="form-control" placeholder="请输入分组名">
				</div>
				<div class="col-md-3">
	       			<select id="s-isWork" class="form-control">
	       				<option value="1">执行中</option>
	       				<option value="2">已停止</option>
	       			</select>
	 			</div>
	 			<div class="col-md-3">
	 				<button class="btn btn-info btn-search" id="search-button">查找</button>
	 			</div>
 			</div>
 			<hr>
			<table class="table">  
		        <thead>  
		            <tr>
		            	<th>任务类名</th>
		            	<th>分组</th>
		            	<th>cron表达式</th>
		            	<th>状态</th>
		            	<th>操作</th>
		            </tr>  
		        </thead>  
		        <tbody id="tasks-body">  
		            <tr><td>Task1</td><td>分组1</td><td>0 30 15 * * ?</td><td>执行中</td><td><a href="#">修改</a></td></tr>  
		            <tr><td>Task2</td><td>分组1</td><td>*/20 * * * * ?</td><td>执行中</td><td><a href="#">修改</a></td></tr>  
		            <tr><td>Task3</td><td>分组2</td><td>0 0 9,15,21 * * ?</td><td>已停止</td><td><a href="#">修改</a></td></tr>  
		        </tbody>  
    	</table> 
		</div>		
		<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
		<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/common/js/toast.js"></script>
		<script src="${contextPath}/resources/common/js/md5.js"></script>
		<script src="${contextPath}/resources/common/common.js"></script>
		<script src="${contextPath}/resources/common/canvas-particle.js"></script>
		<script src="${contextPath}/resources/js/schedule/scheduleList.js" ></script>
	</body>
</html>
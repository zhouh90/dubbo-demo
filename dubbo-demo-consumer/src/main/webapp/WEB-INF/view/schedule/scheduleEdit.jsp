<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>定时任务修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
	</head>
	<body class="templatemo-bg-gray">
		<div class="container">
			<h1 class="margin-bottom-15">定时任务修改</h1>
			<input id="p-taskName" value="${taskName }" type="text" hidden="true">
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="schedule-edit-form" role="form" action="#" method="post">				
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="taskName" class="control-label fa-label"><i class="fa fa-tag fa-medium"></i></label>
		            	<input type="text" class="form-control" readOnly id="taskName" name="taskName" placeholder="任务名称（即：执行类名）">
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="groupName" class="control-label fa-label"><i class="fa fa-tags fa-medium"></i></label>
		            	<input type="text" class="form-control" id="groupName" name="groupName" placeholder="任务组名">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="cronExpression" class="control-label fa-label"><i class="fa fa-clock-o fa-medium"></i></label>
		            	<input type="text" class="form-control" id="cronExpression" name="cronExpression" placeholder="cron表达式">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-6">
		          	<div class="control-wrapper">
		            	<label for="isWork" class="control-label fa-label"><i class="fa fa-send fa-medium"></i></label>
		            	<select class="form-control" id="isWork" name="isWork">
		            		<option id="1" value="1">执行</option>
		            		<option id="2" value="2">停止</option>
		            	</select>
		            </div>
		          </div>
		          <div class="col-md-3">
		          	<div class="control-wrapper">
		          		<input type="button" value="修改任务" class="btn btn-danger" id="edit-button">
		          	</div>
		          </div>
		          <div class="col-md-3">
		          	<div class="control-wrapper">
		          		<input type="button" value="取消" class="btn btn-info" id="cancel-button">
		          	</div>
		          </div>
		        </div>
		    </form>
		</div>
		
		
		<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
		<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/common/js/toast.js"></script>
		<script src="${contextPath}/resources/common/js/md5.js"></script>
		<script src="${contextPath}/resources/common/common.js"></script>
		<script src="${contextPath}/resources/common/canvas-particle.js"></script>
		<script src="${contextPath}/resources/js/schedule/scheduleEdit.js" ></script>
	</body>
</html>
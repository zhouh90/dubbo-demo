<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>定时任务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
	</head>
	<body class="templatemo-bg-gray">
		<div class="container">
			<div class="col-md-12">
				<h1 class="margin-bottom-15">创建定时任务</h1>
				<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="schedule-form" role="form" action="#" method="post">				
			        <div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="taskName" class="control-label fa-label"><i class="fa fa-tag fa-medium"></i></label>
			            	<input type="text" class="form-control" id="taskName" name="taskName" placeholder="任务名称（即：执行类名）">
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
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="replaceExist" class="control-label fa-label"><i class="fa fa-compress fa-medium"></i></label>
			            	<!-- <input type="text" class="form-control" id="groupName" name="groupName" placeholder="任务组名"> -->
			            	<select class="form-control" id="replaceExist" name="replaceExist">
			            		<option value="true">若任务存在，执行替换</option>
			            		<option value="false">若任务存在，不做处理</option>
			            	</select>
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="button" value="创建任务" class="btn btn-info" onClick="return createTask();">
			          	</div>
			          </div>
			        </div>
			        <hr>
			    </form>
			</div>
		</div>
		<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
		<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/common/js/toast.js"></script>
		<script src="${contextPath}/resources/common/js/md5.js"></script>
		<script src="${contextPath}/resources/common/common.js"></script>
		<script src="${contextPath}/resources/common/canvas-particle.js"></script>
		<script src="${contextPath}/resources/js/schedule/schedule.js" ></script>
	</body>
</html>
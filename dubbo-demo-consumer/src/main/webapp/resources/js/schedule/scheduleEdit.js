$(function(){
	initEditPage($('#p-taskName').val());
	
	$('#edit-button').click(function(){
		updateTask();
	});

	$('#cancel-button').click(function(){
		goTo("/schedule/list");
	});
	
	function initEditPage(taskName){
		$.ajax({  
	        cache: true,  
	        type: "POST",  
	        url:"/schedule/listTasks",
	        async: false,
	        data:{
	        	taskName:taskName
	        },
	        error: function(request) {  
	            showtoastFromDiv("schedule-edit-form","请求服务器失败，请重试！","inline-block",1000);
	        },  
	        success: function(data) {
	        	if (!data) {
	        		showtoastFromDiv("schedule-edit-form","系统未知错误，请重试！","inline-block",1000);
	        	} else {
	        		if (data.code == 0) {
	        			var tasks = data.data;
	        			if(!tasks || tasks.length == 0){
	        				showtoastFromDiv("schedule-edit-form","该任务不存在，即将返回任务列表","inline-block",2000);
	            			window.setInterval(function(){ 
	            				goTo("/schedule/list"); 
	    					},2000);
	            			return;
	        			}
	        			
	        			var task = tasks[0];
	        			$('#taskName').val(task.taskName);
	        			$('#groupName').val(task.groupName);
	        			$('#cronExpression').val(task.cronExpression);
	        			$('#isWork').val(task.isWork);
	        			
	            	} else {
	            		showtoastFromDiv("schedule-edit-form",data.msg,"inline-block",1000);
	            	}
	        	}
	        }  
	    });
	}
	
	
	function updateTask(){
		var taskName = $('#taskName').val();
		
		var groupName = $('#groupName').val();
		if(!groupName){
			$('#groupName').focus();
			showtoastFromDiv("schedule-edit-form","任务组名不能为空","inline-block",1000);
			return;
		}
		var cron = $('#cronExpression').val();
		if(!cron){
			$('#cronExpression').focus();
			showtoastFromDiv("schedule-edit-form","cron表达式不能为空","inline-block",1000);
			return;
		}
		if(!validateCronExpression(cron)){
			$('#cronExpression').focus();
			showtoastFromDiv("schedule-edit-form","cron表达式不合法","inline-block",1000);
			return;
		}
		var isWork = $('#isWork').val();
		$.ajax({  
	        cache: true,  
	        type: "POST",  
	        url:"/schedule/updateTask",
	        async: false,
	        data:{
	        	taskName:taskName,
	        	groupName:groupName,
	        	cronExpression:cron,
	        	isWork:isWork
	        },
	        error: function(request) {  
	            showtoastFromDiv("schedule-edit-form","请求服务器失败，请重试！","inline-block",1000);
	        },  
	        success: function(data) {
	        	if (!data) {
	        		showtoastFromDiv("schedule-edit-form","系统未知错误，请重试！","inline-block",1000);
	        	} else {
	        		if (data.code == 0) {
	        			showtoastFromDiv("schedule-edit-form","定时任务修改成功","inline-block",1000);
	        			window.setInterval(function(){ 
            				goTo("/schedule/list"); 
    					},1000);
	            	} else {
	            		showtoastFromDiv("schedule-edit-form",data.msg,"inline-block",1000);
	            	}
	        	}
	        }  
	    });
		return false;
	}
});
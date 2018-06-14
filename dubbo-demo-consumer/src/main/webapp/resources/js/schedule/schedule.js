$(document).ready(function() {
	//监听docuemnt的onkeydown事件看是不是按了回车键
	$(document).keydown(function(event){
		event = event ? event : window.event;
		if (event.keyCode === 13){
			createTask();
		}
	});
});

function createTask(){
	var taskName = $('#taskName').val();
	if(!taskName){
		$('#taskName').focus();
		showtoastFromDiv("schedule-form","任务名称不能为空","inline-block",1000);
		return;
	}
	var groupName = $('#groupName').val();
	if(!groupName){
		$('#groupName').focus();
		showtoastFromDiv("schedule-form","任务组名不能为空","inline-block",1000);
		return;
	}
	var cron = $('#cronExpression').val();
	if(!cron){
		$('#cronExpression').focus();
		showtoastFromDiv("schedule-form","cron表达式不能为空","inline-block",1000);
		return;
	}
	if(!validateCronExpression(cron)){
		$('#cronExpression').focus();
		showtoastFromDiv("schedule-form","cron表达式不合法","inline-block",1000);
		return;
	}
	var replaceExist = $('#replaceExist').val();
	$.ajax({  
        cache: true,  
        type: "POST",  
        url:"/schedule/createTask",
        async: false,
        data:{
        	taskName:taskName,
        	groupName:groupName,
        	cronExpression:cron,
        	replace:replaceExist
        },
        error: function(request) {  
            showtoastFromDiv("schedule-form","请求服务器失败，请重试！","inline-block",1000);
        },  
        success: function(data) {
        	if (!data) {
        		showtoastFromDiv("schedule-form","系统未知错误，请重试！","inline-block",1000);
        	} else {
        		if (data.code == 0) {//成功登录
        			showtoastFromDiv("schedule-form","定时任务创建成功","inline-block",1000);
            		location.href = "/schedule/list";
            	} else {
            		showtoastFromDiv("schedule-form",data.msg,"inline-block",1000);
            	}
        	}
        }  
    });
	return false;
}



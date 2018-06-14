$(function(){
	listTask( $('#s-taskName').val(),$('#s-groupName').val(),$('#s-isWork').val());
	
	$('#search-button').click(function(){
		listTask( $('#s-taskName').val(),$('#s-groupName').val(),$('#s-isWork').val());
	});
	
	function listTask(taskName,groupName,isWork){
		$.ajax({  
	        cache: true,  
	        type: "POST",  
	        url:"/schedule/listTasks",
	        async: false,
	        data:{
	        	taskName:taskName,
	        	groupName:groupName,
	        	isWork:isWork
	        },
	        error: function(request) {  
	            showtoastFromDiv("search","请求服务器失败，请重试！","inline-block",1000);
	        },  
	        success: function(data) {
	        	if (!data) {
	        		showtoastFromDiv("search","系统未知错误，请重试！","inline-block",1000);
	        	} else {
	        		if (data.code == 0) {
	        			listTable(data.data);
	            	} else {
	            		showtoastFromDiv("search",data.msg,"inline-block",1000);
	            	}
	        	}
	        }  
	    });
	}
	
	function listTable(data){
		if(!data || data.length == 0){
			$('#tasks-body').html('');
			showtoastFromDiv("search","没有定时任务","inline-block",1000);
			return;
		}
		var len = data.length;
		var tbodyContent = '';
		for(var i = 0;i < len;i++){
			var item = data[i];
			tbodyContent += '<tr>';
			tbodyContent += '<td>' + item.taskName + '</td>';
			tbodyContent += '<td>' + item.groupName + '</td>';
			tbodyContent += '<td>' + item.cronExpression + '</td>';
			tbodyContent += '<td>' + parseStatus(item.isWork) + '</td>';
			tbodyContent += '<td><a href="/schedule/edit/' + item.taskName + '" >修改</a></td>';
			tbodyContent += '</tr>';
		}
		$('#tasks-body').html('');
		$('#tasks-body').html(tbodyContent);
		showtoastFromDiv("search","定时任务查询成功","inline-block",1000);
		return;
	}
	
	function parseStatus(isWork){
		if(isWork == 1){
			return '执行中';
		}else{
			return '已停止';
		}
	}
});
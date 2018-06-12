package com.qaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaf.schedule.beans.Res;
import com.qaf.schedule.beans.SchedulerTask;
import com.qaf.schedule.service.SchedulerService;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年6月1日 下午4:09:55
 * @描述
 */
@Controller
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	private SchedulerService schedulerService;

	@ResponseBody
	@RequestMapping("createTask")
	public Res createTask(@RequestBody SchedulerTask task) {
//		Res res = Res.ok();
//		SchedulerTask task = new SchedulerTask();
		System.out.println("taskname:" + task.getTaskName());
		System.out.println("groupname:" + task.getGroupName());
		System.out.println("cron:" + task.getCronExpression());
		System.out.println("replace" + task.getReplace());
		Res res = schedulerService.createSchedulerTask(task);
		return res;
	}

}

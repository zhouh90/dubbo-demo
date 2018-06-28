package com.qaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/home")
	public String scheduleHome() {
		return "/schedule/schedule";
	}

	@RequestMapping("/list")
	public String scheduleListPage() {
		return "/schedule/scheduleList";
	}

	@RequestMapping("/edit/{taskName}")
	public String editPage(Model model, @PathVariable String taskName) {
		model.addAttribute("taskName", taskName);
		return "/schedule/scheduleEdit";
	}

	@RequestMapping("createTask")
	@ResponseBody
	public Res createTask(SchedulerTask task) {
		task.setIsWork(SchedulerTask.TASK_WORK_ON);
		Res res = schedulerService.saveScheduleTask(task);
		return res;
	}

	@RequestMapping("listTasks")
	@ResponseBody
	public Res listTask(SchedulerTask task) {
		Res res = schedulerService.listTasks(task);
		return res;
	}

	@RequestMapping("updateTask")
	@ResponseBody
	public Res updateTask(SchedulerTask task) {
		Res res = schedulerService.updateTask(task);
		return res;
	}

	@RequestMapping("off")
	@ResponseBody
	public Res shutdown() {
		SchedulerTask task = new SchedulerTask();
		task.setIsWork(2);
		task.setTaskName("Task1");
		Res res = schedulerService.updateTask(task);
		return res;
	}

	@RequestMapping("createTaskOnly")
	@ResponseBody
	public Res createTaskOnly() {
		SchedulerTask task = new SchedulerTask();
		task.setTaskName("Task1");
		task.setGroupName("taskgroup1");
		task.setCronExpression("*/10 * * * * ?");
		Res res = schedulerService.createSchedulerTaskOnly(task);
		return res;
	}

}

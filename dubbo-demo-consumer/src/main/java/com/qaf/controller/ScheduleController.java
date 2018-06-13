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

	@ResponseBody
	@RequestMapping("createTask")
	public Res createTask(SchedulerTask task) {
		Res res = schedulerService.createSchedulerTask(task);
		return res;
	}

	@ResponseBody
	@RequestMapping("listTasks")
	public Res listTask(SchedulerTask task) {
		System.out.println("taskname:" + task.getTaskName());
		System.out.println("groupname:" + task.getGroupName());
		System.out.println("cron:" + task.getCronExpression());
		System.out.println("replace:" + task.getReplace());
		System.out.println("isWork:" + task.getIsWork());
		Res res = schedulerService.listTasks(task);
//		Res res = new Res();
		return res;
	}

}

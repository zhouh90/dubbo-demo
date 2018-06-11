package com.qaf.schedule.service.impl;
/**
 * @author 周 浩 
 * @email zhou_eric90@163.com 
 * @date 2018年5月31日 上午10:09:42 
 * @描述 
 */

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.qaf.schedule.beans.Res;
import com.qaf.schedule.beans.SchedulerTask;
import com.qaf.schedule.service.SchedulerService;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public Res createSchedulerTask(SchedulerTask task) {
		System.out.println("schedulerFactoryBean:" + schedulerFactoryBean);
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getGroupName());
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger != null && SchedulerTask.NOT_REPLACE_EXIST_TASK.equals(task.getReplace())) {
				System.out.println("定时任务已经存在");
				return Res.error().put("-1", "定时任务已经存在");
			}
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
			if (trigger == null) {
				System.out.println("新增定时任务");
				Class c = Class.forName("com.qaf.schedule.task." + task.getTaskName());
				JobDetail jobDetail = JobBuilder.newJob(c).withIdentity(task.getTaskName(), task.getGroupName()).build();
				jobDetail.getJobDataMap().put("schedule", task);
				scheduler.scheduleJob(jobDetail, cronTrigger);
				return Res.ok().put("1", "定时任务创建成果");
			}
			if (trigger != null && SchedulerTask.REPLACE_EXIST_TASK.equals(task.getReplace())) {
				System.out.println("更新已有任务");
				CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
				scheduler.rescheduleJob(triggerKey, newTrigger);
				return Res.ok().put("1", "定时任务已更新");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Res.error().put("-1", "定时任务创建异常");
	}

	public Res listTasks() {
		// TODO
		return null;
	}

}

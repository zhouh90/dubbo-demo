package com.qaf.schedule.service.impl;
/**
 * @author 周 浩 
 * @email zhou_eric90@163.com 
 * @date 2018年5月31日 上午10:09:42 
 * @描述 
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.qaf.schedule.beans.Res;
import com.qaf.schedule.beans.SchedulerTask;
import com.qaf.schedule.dao.ScheduleDao;
import com.qaf.schedule.service.SchedulerService;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	private static final String UPDATE = "UPDATE";
	private static final String NEWADD = "NEWADD";

	@Autowired
	private ScheduleDao scheduleDao;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

//	public Res createSchedulerTask(SchedulerTask task) {
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName());
//		try {
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			if (trigger != null && SchedulerTask.NOT_REPLACE_EXIST_TASK.equals(task.getReplace())) {
//				logger.info("定时任务已经存在");
//				return Res.error("定时任务已经存在");
//			}
//			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
//			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
//			if (trigger == null) {
//				Class c = Class.forName("com.qaf.schedule.task." + task.getTaskName());
//				JobDetail jobDetail = JobBuilder.newJob(c).withIdentity(task.getTaskName(), task.getGroupName()).build();
//				jobDetail.getJobDataMap().put("schedule", task);
//				scheduler.scheduleJob(jobDetail, cronTrigger);
//				logger.info("保存定时任务---task:{}", task);
//				try {
//					scheduleDao.saveScheduleTask(task);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return Res.error("定时任务保存失败");
//				}
//				return Res.ok("定时任务创建成果");
//			}
//			if (trigger != null && SchedulerTask.REPLACE_EXIST_TASK.equals(task.getReplace())) {
//				CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
//				scheduler.rescheduleJob(triggerKey, newTrigger);
//				try {
//					scheduleDao.updateScheduleTask(task);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return Res.error("定时任务更新失败");
//				}
//				logger.info("更新已有任务");
//				return Res.ok("定时任务已更新");
//			}
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return Res.error("定时任务创建异常");
//	}

	public Res listTasks(SchedulerTask task) {
		List<SchedulerTask> list = new ArrayList<SchedulerTask>();
		if (task == null) {
			list = scheduleDao.listAllTask();
		} else {
			task.setTaskName((StringUtils.isBlank(task.getTaskName()) ? null : task.getTaskName()));
			task.setGroupName((StringUtils.isBlank(task.getGroupName()) ? null : task.getGroupName()));
			list = scheduleDao.listTaskByCondition(task);
		}
		return Res.ok().data(list);
	}

	public Res updateTask(SchedulerTask task) {
		if (task == null) {
			Res.error("更新任务参数为空");
		}
		try {
			scheduleDao.updateScheduleTask(task);
		} catch (Exception e) {
			e.printStackTrace();
			return Res.error("更新任务失败");
		}
		return Res.ok("任务更新成功");
	}

	public Res listAllTasks() {
		return this.listTasks(null);
	}

	public Res saveScheduleTask(SchedulerTask task) {
		if (task == null || StringUtils.isBlank(task.getTaskName())) {
			return Res.error("任务实体不能为空");
		}
		SchedulerTask param = new SchedulerTask();
		param.setTaskName(task.getTaskName());
		List<SchedulerTask> existTask = scheduleDao.listTaskByCondition(param);
		if (existTask != null && existTask.size() > 0) {
			if (SchedulerTask.NOT_REPLACE_EXIST_TASK.equals(task.getReplace())) {
				return Res.error("该任务已存在");
			} else {
				scheduleDao.updateScheduleTask(task);
				return executeScheduleTask(task, UPDATE, SchedulerTask.TASK_WORK_ON);
			}
		} else {
			try {
				scheduleDao.saveScheduleTask(task);
			} catch (Exception e) {
				e.printStackTrace();
				return Res.error("定时任务保存失败");
			}
			return executeScheduleTask(task, NEWADD, SchedulerTask.TASK_WORK_ON);
		}
	}

	private Res executeScheduleTask(SchedulerTask task, String execType, int workStatus) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName());
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger != null && SchedulerTask.NOT_REPLACE_EXIST_TASK.equals(execType)) {
				logger.info("定时任务已经存在");
				return Res.error("定时任务已经存在");
			}

			if (trigger == null && SchedulerTask.REPLACE_EXIST_TASK.equals(execType)) {
				logger.info("定时任务不存在");
				return Res.error("定时任务不存在");
			}

			if (workStatus == SchedulerTask.TASK_WORK_ON) {
				if (trigger != null && SchedulerTask.REPLACE_EXIST_TASK.equals(execType)) {
					CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
					CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
					scheduler.rescheduleJob(triggerKey, newTrigger);
					logger.info("更新已有任务");
					return Res.ok("定时任务已更新");
				}

				if (trigger == null && SchedulerTask.NOT_REPLACE_EXIST_TASK.equals(execType)) {
					CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
					CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getGroupName()).withSchedule(cronScheduleBuilder).build();
					Class c = Class.forName("com.qaf.schedule.task." + task.getTaskName());
					JobDetail jobDetail = JobBuilder.newJob(c).withIdentity(task.getTaskName(), task.getGroupName()).build();
					jobDetail.getJobDataMap().put("schedule", task);
					scheduler.scheduleJob(jobDetail, cronTrigger);
					return Res.ok("定时任务创建成功");
				}
			} else {
				// TODO
				return Res.error("尚不支持终止任务");
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Res.error("定时任务创建异常");
	}

}

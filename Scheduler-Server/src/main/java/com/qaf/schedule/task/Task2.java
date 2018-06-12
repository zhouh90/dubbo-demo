package com.qaf.schedule.task;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qaf.schedule.beans.SchedulerTask;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年6月12日 下午1:54:39
 * @描述
 */
public class Task2 implements Job {

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		SchedulerTask task = (SchedulerTask) jobExecutionContext.getMergedJobDataMap().get("schedule");
		if (task == null) {
			System.out.println("不存在该任务----【schedule】");
			return;
		}
		startTask(task);
	}

	private void startTask(SchedulerTask task) {
		System.out.println(new Date() + "----> Task2 == doing schedule task....");
	}

}
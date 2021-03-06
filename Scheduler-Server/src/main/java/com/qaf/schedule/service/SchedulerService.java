package com.qaf.schedule.service;
/**
 * @author 周 浩 
 * @email zhou_eric90@163.com 
 * @date 2018年6月1日 下午4:05:59 
 * @描述 
 */

import com.qaf.schedule.beans.Res;
import com.qaf.schedule.beans.SchedulerTask;

public interface SchedulerService {

	Res createSchedulerTaskOnly(SchedulerTask task);

	Res saveScheduleTask(SchedulerTask task);

	Res listAllTasks();

	Res listTasks(SchedulerTask task);

	Res updateTask(SchedulerTask task);

	Res restartRunningTasks(SchedulerTask task);

}

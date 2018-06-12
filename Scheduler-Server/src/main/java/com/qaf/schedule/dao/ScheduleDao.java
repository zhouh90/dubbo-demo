package com.qaf.schedule.dao;

import java.util.List;

import com.qaf.schedule.beans.SchedulerTask;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年6月12日 下午2:08:28
 * @描述
 */
public interface ScheduleDao {

	public void saveScheduleTask(SchedulerTask task) throws Exception;

	public List<SchedulerTask> listAllTask();

	public List<SchedulerTask> listTaskByCondition(SchedulerTask task);

	public int updateScheduleTask(SchedulerTask task);

}

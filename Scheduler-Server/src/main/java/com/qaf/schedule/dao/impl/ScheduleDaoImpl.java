package com.qaf.schedule.dao.impl;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaf.schedule.beans.SchedulerTask;
import com.qaf.schedule.dao.ScheduleDao;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年6月12日 下午2:08:55
 * @描述
 */
@Service
public class ScheduleDaoImpl implements ScheduleDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void saveScheduleTask(SchedulerTask task) throws Exception {
		if (task == null) {
			throw new Exception("SchedulerTask不能为NULL");
		}
		if (task.getCreateTime() == null) {
			task.setCreateTime(new Date());
		}
		sqlSession.insert("saveScheduleTask", task);
	}

	public List<SchedulerTask> listAllTask() {
		return sqlSession.selectList("listAllTask");
	}

	public int updateScheduleTask(SchedulerTask task) {
		return sqlSession.update("updateScheduleTask", task);
	}

	public List<SchedulerTask> listTaskByCondition(SchedulerTask task) {
		return sqlSession.selectList("listTaskByCondition", task);
	}

}

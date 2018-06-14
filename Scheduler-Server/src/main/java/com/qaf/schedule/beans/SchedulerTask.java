package com.qaf.schedule.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年5月31日 上午11:41:59
 * @描述
 */
@Getter
@Setter
@ToString
public class SchedulerTask implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NOT_REPLACE_EXIST_TASK = "false";
	public static final String REPLACE_EXIST_TASK = "true";

	public static final int TASK_WORK_ON = 1;
	public static final int TASK_WORK_OFF = 2;

	private int id;
	private String taskName;
	private String groupName;
	private String cronExpression;
	private String replace;
	private int isWork = 0;
	private Date createTime;
	private Date updateTime;

}

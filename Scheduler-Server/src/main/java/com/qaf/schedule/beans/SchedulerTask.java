package com.qaf.schedule.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年5月31日 上午11:41:59
 * @描述
 */
@Getter
@Setter
public class SchedulerTask implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NOT_REPLACE_EXIST_TASK = "false";
	public static final String REPLACE_EXIST_TASK = "true";

	private String taskName;
	private String groupName;
	private String cronExpression;
	private String replace;

}

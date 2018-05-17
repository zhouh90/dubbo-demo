package com.qaf.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.qaf.service.HelloService;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年5月17日 上午11:06:16
 * @描述
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

	public String sayHello() {
		return "Hello,the time is " + (new Date());
	}

}

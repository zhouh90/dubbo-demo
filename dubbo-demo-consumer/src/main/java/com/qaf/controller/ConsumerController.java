package com.qaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaf.service.HelloService;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年5月17日 上午10:51:32
 * @描述
 */
@Controller
public class ConsumerController {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private HelloService helloService;

	@RequestMapping("hello")
	@ResponseBody
	public String sayHello() {
		logger.info("调用远程接口 sayHello");
		return helloService.sayHello();
	}

	@RequestMapping("hi")
	@ResponseBody
	public String sayHi() {
		return "hi";
	}

}

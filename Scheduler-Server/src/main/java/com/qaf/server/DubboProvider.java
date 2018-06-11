package com.qaf.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			System.out.print("================start finished!==========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}

package com.unitcase.srv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-runtime.xml");
		wait(context);
		System.exit(1);
	}
	
	private static void wait(ClassPathXmlApplicationContext context) throws InterruptedException {
		synchronized (context) {
			while (context.isActive()) {
				context.wait();
			}
		}
	}
}

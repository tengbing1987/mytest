package com.wondersgroup.thread;

import java.lang.Thread.UncaughtExceptionHandler;

class TestExceptionHandler implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("线程出现异常：");
		e.printStackTrace();
		System.out.printf("\n当前线程栈：");
		new Exception().printStackTrace();
	}
}
public class ExceptionHandlerTest {

	public static void main(String []args) {
		Thread t = new Thread() {
			public void run() {
				Integer.parseInt("ABC");
			}
		};
		t.setUncaughtExceptionHandler(new TestExceptionHandler());
		t.start();
	}
}
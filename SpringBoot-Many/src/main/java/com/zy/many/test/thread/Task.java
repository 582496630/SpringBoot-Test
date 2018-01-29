package com.zy.many.test.thread;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class Task implements Runnable {

	volatile static boolean running = true;
	int i = 0;

	@Override
	public void run() {
		while (running) {
			i++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.start();
		Thread.sleep(10);
		running = false;
		Thread.sleep(1);
		System.out.println(task.i);
		System.out.println(running);
		System.out.println("程序停止");
	}

}

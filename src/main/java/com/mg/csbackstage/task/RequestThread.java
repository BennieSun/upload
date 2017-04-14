package com.mg.csbackstage.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestThread {

	private static ExecutorService pool  = Executors.newFixedThreadPool(10);
	
	public static ExecutorService getThreadPool() {
		return pool;
	}
	
	public static void runTask(Runnable task) {
		pool.execute(task);
	}
}

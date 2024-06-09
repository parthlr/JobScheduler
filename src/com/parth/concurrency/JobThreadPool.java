package com.parth.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JobThreadPool {
	
	int threadCount;
	int queueSize;
	BlockingQueue<Runnable> taskQueue;
	
	public JobThreadPool(int threadCount, int queueSize) {
		this.threadCount = threadCount;
		this.queueSize = queueSize;
		taskQueue = new LinkedBlockingQueue<Runnable>(queueSize);
		
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new JobTaskExecutor(taskQueue));
			thread.start();
		}
	}
	
	public void submitTask(Runnable runnable) {
		taskQueue.add(runnable);
	}
	
	public int getThreadCount() {
		return threadCount;
	}
	
	public int getQueueSize() {
		return queueSize;
	}

}

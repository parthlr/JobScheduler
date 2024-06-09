package com.parth.concurrency;

import java.util.concurrent.BlockingQueue;

public class JobTaskExecutor implements Runnable {
	
	BlockingQueue<Runnable> taskQueue;
	
	public JobTaskExecutor(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Runnable runnable = taskQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				
			}
		}
	}

}

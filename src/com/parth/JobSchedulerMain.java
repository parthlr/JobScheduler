package com.parth;

import com.parth.concurrency.JobThreadPool;
import com.parth.job.runner.JobRunner;

public class JobSchedulerMain {
	
	public static void main(String[] args) {
		JobThreadPool pool = new JobThreadPool(3, 3);
		JobRunner runner = new JobRunner(pool);
		runner.initJobs("test_jobs.json");
		
		runner.start();
	}

}

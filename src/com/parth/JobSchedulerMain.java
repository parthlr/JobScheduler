package com.parth;

import com.parth.job.runner.JobRunner;

public class JobSchedulerMain {
	
	public static void main(String[] args) {
		JobRunner runner = new JobRunner();
		runner.initJobs("C:\\Users\\parth\\OneDrive\\Documents\\test_jobs.json");
		
		runner.start();
	}

}

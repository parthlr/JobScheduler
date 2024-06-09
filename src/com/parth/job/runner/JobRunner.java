package com.parth.job.runner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import com.parth.concurrency.JobTask;
import com.parth.concurrency.JobThreadPool;
import com.parth.job.Job;
import com.parth.job.JobRun;
import com.parth.job.comparator.JobComparator;
import com.parth.util.JobUtil;

public class JobRunner {
	
	JobThreadPool pool;
	
	PriorityQueue<JobRun> jobQueue;
	List<JobRun> runHistory;
	
	int nextRunID;
	
	public JobRunner(JobThreadPool pool) {
		this.pool = pool;
		
		Comparator<JobRun> jobComparator = new JobComparator();
		jobQueue = new PriorityQueue<JobRun>(jobComparator);
		runHistory = new ArrayList<JobRun>();
		
		nextRunID = 0;
		
		System.out.println("Started job runner");
	}
	
	public void initJobs(String configPath) {
		List<Job> jobs = JobUtil.importJobsFromFile(configPath);
		
		for (int i = 0; i < jobs.size(); i++) {
			Job job = jobs.get(i);
			
			JobRun run = new JobRun(i, job);
			
			jobQueue.add(run);
		}
		
		nextRunID = jobs.size();
	}
	
	public boolean killJob(String jobName) {
		JobRun[] jobRuns = (JobRun[]) jobQueue.toArray();
		
		for (JobRun run : jobRuns) {
			if (run.getJob().getName().equals(jobName)) {
				Date currentTime = new Date();
				run.setEndTime(currentTime);
				run.setStatus(JobRun.RunStatus.TERMINATED);
				return jobQueue.remove(run.getJob());
			}
		}
		
		return false;
	}
	
	public void forceStartJob(Job job) {
		JobRun newRun = new JobRun(nextRunID, job);
		nextRunID++;
		
		runJob(newRun);
	}
	
	private void runJob(JobRun run) {
		pool.submitTask(new JobTask(run));
	}
	
	public void start() {
		while (jobQueue.size() > 0) {
			JobRun nextRun = jobQueue.peek();
			
			Date currentTime = new Date();
			
			if (currentTime.after(nextRun.getJob().getStartDate())) {
				runJob(nextRun);
				jobQueue.poll();
			}
		}
		System.out.println("Job runner ended");
	}

}

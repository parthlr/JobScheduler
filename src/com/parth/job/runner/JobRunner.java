package com.parth.job.runner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import com.parth.job.Job;
import com.parth.job.JobRun;
import com.parth.job.comparator.JobComparator;
import com.parth.util.JobUtil;

public class JobRunner {
	
	PriorityQueue<JobRun> jobQueue;
	List<JobRun> runHistory;
	
	int nextRunID;
	
	public JobRunner() {
		Comparator<JobRun> jobComparator = new JobComparator();
		jobQueue = new PriorityQueue<JobRun>(jobComparator);
		runHistory = new ArrayList<JobRun>();
		
		nextRunID = 0;
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
		System.out.println("---------------------");
		System.out.println("Starting Job: " + run.getJob().getName());
		
		Date startTime = new Date();
		run.setStartTime(startTime);
		run.setStatus(JobRun.RunStatus.RUNNING);
		
		System.out.println("Start Time: " + run.getStartTime().toString());
		
		try {
			System.out.println(run.getJob().getProcess());
			
			Date endTime = new Date();
			run.setEndTime(endTime);
			run.setStatus(JobRun.RunStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			
			Date endTime = new Date();
			run.setEndTime(endTime);
			run.setStatus(JobRun.RunStatus.FAILED);
		}
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
	}

}

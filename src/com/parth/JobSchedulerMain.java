package com.parth;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import com.parth.job.Job;
import com.parth.job.JobRun;
import com.parth.job.comparator.JobComparator;
import com.parth.util.JobUtil;

public class JobSchedulerMain {
	
	public static void main(String[] args) {
		Comparator<JobRun> jobComparator = new JobComparator();
		PriorityQueue<JobRun> jobQueue = new PriorityQueue<JobRun>(jobComparator);
		
		List<Job> jobs = JobUtil.importJobsFromFile("C:\\Users\\parth\\OneDrive\\Documents\\test_jobs.json");
		
		for (int i = 0; i < jobs.size(); i++) {
			Job job = jobs.get(i);
			
			JobRun run = new JobRun(i, job);
			
			jobQueue.add(run);
			
			System.out.println(run.getJob().getName() + " current status is " + run.getStatus());
		}
		
		while (jobQueue.size() > 0) {
			JobRun nextRun = jobQueue.peek();
			
			Date currentTime = new Date();
			
			if (currentTime.after(nextRun.getJob().getStartDate())) {
				nextRun.setStartTime(currentTime);
				
				System.out.println("---------------------");
				System.out.println("Starting Job: " + nextRun.getJob().getName());
				System.out.println("Start Time: " + nextRun.getStartTime().toString());
				
				nextRun.setStatus(JobRun.RunStatus.RUNNING);
				System.out.println("Job Status: " + nextRun.getStatus());
				
				System.out.println(nextRun.getJob().getProcess());
				
				Date endTime = new Date();
				nextRun.setEndTime(endTime);
				
				System.out.println("Finished Job: " + nextRun.getJob().getName());
				System.out.println("End Time: " + nextRun.getEndTime().toString());
				
				nextRun.setStatus(JobRun.RunStatus.SUCCESS);
				System.out.println("Job Status: " + nextRun.getStatus());
				
				jobQueue.poll();
			}
		}
	}

}

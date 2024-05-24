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
		
		List<Job> jobs = JobUtil.importJobsFromFile("test_jobs.json");
		
		for (int i = 0; i < jobs.size(); i++) {
			Job job = jobs.get(i);
			
			JobRun run = new JobRun(i, job, job.getStartDate());
			
			jobQueue.add(run);
		}
		
		while (jobQueue.size() > 0) {
			JobRun nextRun = jobQueue.peek();
			
			Date currentTime = new Date();
			
			if (currentTime.after(nextRun.getJob().getStartDate())) {
				System.out.println("---------------------");
				System.out.println("Starting Job: " + nextRun.getJob().getName());
				System.out.println(nextRun.getJob().getProcess());
				System.out.println("Finished Job: " + nextRun.getJob().getName());
				
				jobQueue.poll();
			}
		}
	}

}

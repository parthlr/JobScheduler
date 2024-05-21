package com.parth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

import com.parth.job.Job;
import com.parth.job.JobRun;
import com.parth.job.comparator.JobComparator;

public class JobSchedulerMain {
	
	public static Date stringToDate(String s){

	    Date result = null;
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        result  = dateFormat.parse(s);
	    }

	    catch(ParseException e){
	        e.printStackTrace();

	    }
	    return result ;
	}
	
	public static void main(String[] args) {
		Comparator<JobRun> jobComparator = new JobComparator();
		PriorityQueue<JobRun> jobQueue = new PriorityQueue<JobRun>(jobComparator);
		
		Job job1 = new Job(0, "JOB_1", stringToDate("2024-05-20 23:20:00"), "This is job 1!"); // 4
		Job job2 = new Job(1, "JOB_2", stringToDate("2024-05-20 23:17:00"), "This is job 2!"); // 1
		Job job3 = new Job(2, "JOB_3", stringToDate("2024-05-20 23:21:00"), "This is job 3!"); // 5
		Job job4 = new Job(3, "JOB_4", stringToDate("2024-05-20 23:18:00"), "This is job 4!"); // 2
		Job job5 = new Job(4, "JOB_5", stringToDate("2024-05-20 23:19:00"), "This is job 5!"); // 3
		
		JobRun run1 = new JobRun(0, job1, job1.getStartDate());
		JobRun run2 = new JobRun(0, job2, job2.getStartDate());
		JobRun run3 = new JobRun(0, job3, job3.getStartDate());
		JobRun run4 = new JobRun(0, job4, job4.getStartDate());
		JobRun run5 = new JobRun(0, job5, job5.getStartDate());
		
		jobQueue.add(run1);
		jobQueue.add(run2);
		jobQueue.add(run3);
		jobQueue.add(run4);
		jobQueue.add(run5);
		
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

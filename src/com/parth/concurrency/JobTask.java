package com.parth.concurrency;

import java.util.Date;

import com.parth.job.Job;
import com.parth.job.JobRun;
import com.parth.util.JobUtil;

public class JobTask implements Runnable {
	
	JobRun jobRun;
	
	public JobTask(JobRun jobRun) {
		this.jobRun = jobRun;
	}

	@Override
	public void run() {
		Job currentJob = jobRun.getJob();
		while (currentJob != null) {
			Date startTime = new Date();
			jobRun.setStartTime(startTime);
			jobRun.setStatus(JobRun.RunStatus.RUNNING);
			jobRun.setOutLogPath(JobUtil.getFullLogPath(startTime, currentJob));
			
			JobUtil.logInfo(jobRun.getLogStream(), "---------------------");
			JobUtil.logInfo(jobRun.getLogStream(), "Starting Job: " + currentJob.getName());
			JobUtil.logInfo(jobRun.getLogStream(), "Start Time: " + jobRun.getStartTime().toString());
			
			try {
				JobUtil.logInfo(jobRun.getLogStream(), currentJob.getProcess());
				
				Date endTime = new Date();
				jobRun.setEndTime(endTime);
				jobRun.setStatus(JobRun.RunStatus.SUCCESS);
			} catch (Exception e) {
				StackTraceElement[] stack = e.getStackTrace();
				String errorString = "";
				
				for (StackTraceElement element : stack) {
					errorString += element.toString() + "\n";
				}
				
				JobUtil.logError(jobRun.getLogStream(), errorString);
				
				Date endTime = new Date();
				jobRun.setEndTime(endTime);
				jobRun.setStatus(JobRun.RunStatus.FAILED);
			}
			
			JobUtil.logInfo(jobRun.getLogStream(), "End Time: " + jobRun.getEndTime().toString());
			JobUtil.logInfo(jobRun.getLogStream(), "Run Status: " + jobRun.getStatus());
			
			currentJob = currentJob.getSuccessorJob();
		}
	}

}

package com.parth.job;

import java.util.Date;

public class JobRun {
	
	int runID;
	Job job;
	Date startTime;
	Date endTime;
	
	public JobRun(int runID, Job job, Date startTime) {
		this.runID = runID;
		this.job = job;
		this.startTime = startTime;
		this.endTime = null;
	}
	
	public int getRunID() {
		return runID;
	}
	
	public Job getJob() {
		return job;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date newEndTime) {
		this.endTime = newEndTime;
	}

}

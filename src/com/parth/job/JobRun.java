package com.parth.job;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

public class JobRun {
	
	public enum RunStatus {
		INACTIVE,
		RUNNING,
		SUCCESS,
		FAILED,
		TERMINATED
	}
	
	int runID;
	Job job;
	Date startTime;
	Date endTime;
	RunStatus status;
	
	PrintStream outLog;
	
	public JobRun(int runID, Job job) {
		this.runID = runID;
		this.job = job;
		this.startTime = null;
		this.endTime = null;
		this.status = RunStatus.INACTIVE;
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
	
	public void setStartTime(long timestamp) {
		startTime = new Date(timestamp);
	}
	
	public void setStartTime(Date newTime) {
		startTime = newTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(long timestamp) {
		endTime = new Date(timestamp);
	}
	
	public void setEndTime(Date newEndTime) {
		this.endTime = newEndTime;
	}
	
	public RunStatus getStatus() {
		return status;
	}
	
	public void setStatus(RunStatus newStatus) {
		status = newStatus;
	}
	
	public void setOutLogPath(String path) {
		try {
			outLog = new PrintStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PrintStream getLogStream() {
		return outLog;
	}

}

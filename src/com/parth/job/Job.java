package com.parth.job;

import java.util.Date;

import com.parth.util.Util;

public class Job {
	
	int id;
	String name;
	Date startDate;
	String process;
	String logPath;
	Job successorJob;
	
	public Job(int id, String name, String startTime, String process, String logPath) {
		this.id = id;
		this.name = name;
		this.startDate = Util.stringToDate(startTime);
		this.process = process;
		this.logPath = logPath;
		this.successorJob = null;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public String getProcess() {
		return process;
	}
	
	public String getLogPath() {
		return logPath;
	}
	
	public void setSuccessorJob(Job job) {
		successorJob = job;
	}
	
	public Job getSuccessorJob() {
		return successorJob;
	}
	
	public boolean hasNextJob() {
		return successorJob != null;
	}

}

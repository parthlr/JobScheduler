package com.parth.job;

import java.util.Date;

import com.parth.util.Util;

public class Job {
	
	int id;
	String name;
	Date startDate;
	String process;
	
	public Job(int id, String name, String startTime, String process) {
		this.id = id;
		this.name = name;
		this.startDate = Util.stringToDate(startTime);
		this.process = process;
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

}

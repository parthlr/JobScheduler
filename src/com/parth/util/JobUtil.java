package com.parth.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.parth.job.Job;

public class JobUtil {
	
	public static List<Job> importJobsFromFile(String path) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jobsJson = (JSONObject) parser.parse(new FileReader(path));
			JSONArray jobs = (JSONArray) jobsJson.get("jobs");
			
			List<Job> jobList = new ArrayList<Job>();
			
			for (Object jobObj : jobs) {
				JSONObject jsonJob = (JSONObject) jobObj;
				
				Long jobID = (Long) jsonJob.get("id");
				String jobName = (String) jsonJob.get("name");
				String jobStartTime = (String) jsonJob.get("startTime");
				String jobProcess = (String) jsonJob.get("process");
				String jobLogPath = (String) jsonJob.get("log");
				
				Job job = new Job(jobID.intValue(), jobName, jobStartTime, jobProcess, jobLogPath);
				
				jobList.add(job);
			}
			
			return jobList;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getFullLogPath(Date startTime, Job job) {
		String jobLogPath = job.getLogPath();
		
		if (jobLogPath.charAt(jobLogPath.length() - 1) != '/' && jobLogPath.charAt(jobLogPath.length() - 1) != '\\') {
			if (jobLogPath.contains("/")) {
				jobLogPath += "/";
			} else if (jobLogPath.contains("\\")) {
				jobLogPath += "\\";
			}
		}
		
		String fullPath = jobLogPath + job.getName() + "_" + startTime.getTime() + ".log";
		
		return fullPath;
	}
	
	public static void logInfo(PrintStream stream, String log) {
		Date currentDate = new Date();
		stream.println("[INFO] " + currentDate.toString() + ": " + log);
	}
	
	public static void logError(PrintStream stream, String log) {
		Date currentDate = new Date();
		stream.println("[ERROR] " + currentDate.toString() + ": " + log);
	}

}

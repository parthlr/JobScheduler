package com.parth.job.comparator;

import java.util.Comparator;

import com.parth.job.JobRun;

public class JobComparator implements Comparator<JobRun> {

	@Override
	public int compare(JobRun job1, JobRun job2) {
		if (job1.getStartTime().getTime() < job2.getStartTime().getTime()) {
			return -1;
		} else if (job1.getStartTime().getTime() > job2.getStartTime().getTime()) {
			return 1;
		}
		return 0;
	}

}

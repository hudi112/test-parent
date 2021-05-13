package com.z1.runner.job;

import com.z1.runner.job.Job;
import com.z1.runner.job.JobType;

public class JavaJob implements Job {

	@Override
	public JobType jobType() {
		return JobType.JAVA_JOB;
	}

	@Override
	public void init() {
	}

	@Override
	public void postProcessBefore() {
	}

	@Override
	public void postProcess() {
	}

	@Override
	public void postProcessAfter() {
	}

}

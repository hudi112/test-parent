package com.z1.runner.job;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.support.SpringFactoriesLoader;

import com.z1.runner.job.Job;
import com.z1.runner.job.JobException;

public class JobContext {
	
	private Map<String, Job> jobMapping = new ConcurrentHashMap<>();
	
	public JobContext() {
		//加载支持的任务类型
		List<Job> jobs = SpringFactoriesLoader.loadFactories(Job.class, Thread.currentThread().getContextClassLoader());
		if (null != jobs) {
			jobs.forEach(job -> {
				final String name = job.jobType().getName();
				Job containsJob = this.jobMapping.get(name);
				if (null != containsJob) {
					throw new JobException(job.getClass() + " is the same of job " + containsJob.getClass());
				}
				this.jobMapping.put(name, job);
			});
		}
	}
	
	/**
	 * 获取可用任务类型列表
	 * @return
	 */
	public Set<String> listAvailableTaskType() {
		return this.jobMapping.keySet();
	}
	
}

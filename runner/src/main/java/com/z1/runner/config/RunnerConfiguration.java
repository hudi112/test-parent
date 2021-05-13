package com.z1.runner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.z1.runner.job.JobContext;

@Configuration
public class RunnerConfiguration {

	@Bean
	public JobContext taskContext() {
		return new JobContext();
	}
	
}

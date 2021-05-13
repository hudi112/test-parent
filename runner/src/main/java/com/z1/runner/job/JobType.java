package com.z1.runner.job;

import lombok.Getter;

public enum JobType {

	JAVA_JOB("Java")
	
	,UNKOWN("Unkown");
	
	@Getter
	private String name;
	
	private JobType(String name) {
		this.name = name;
	}
	
	public static JobType get(String name) {  
        for (JobType jobType : JobType.values()) {  
            if (jobType.getName().equals(name)) {  
                return jobType;  
            }  
        }  
        return UNKOWN;  
    }
	
}

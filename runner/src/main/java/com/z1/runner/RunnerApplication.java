package com.z1.runner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.z1.runner.mapper")
@ComponentScan
@SpringBootApplication
public class RunnerApplication {
	
    public static void main( String[] args ) {
    	
    	SpringApplication.run(RunnerApplication.class, args);
    	
    }
    
}

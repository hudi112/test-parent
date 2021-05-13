package com.z1.runner.mybatisplus;

import junit.framework.TestCase;

public class MybatisPlusGeneratorTest extends TestCase {
	
	public void testGenerator() {
		MybatisPlusGenerator generator = new MybatisPlusGenerator();
		try {
			generator.generator();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

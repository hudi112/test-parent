package com.z1.runner.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z1.runner.Const;
import com.z1.runner.entity.Job;
import com.z1.runner.job.JobType;
import com.z1.runner.mapper.JobMapper;
import com.z1.runner.service.IJobService;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

/**
 * <p>
 * 工作表 服务实现类
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

	@Autowired
	private SnowflakeGenerator snowflakeGenerator;
	
	public boolean save(Job job) {
		//名称
		if (StringUtils.isBlank(job.getName())) {
			throw new ApiException("工作名称为空.");
		}
		//类型
		JobType jobType = JobType.get(job.getType());
		if (jobType == JobType.UNKOWN) {
			throw new ApiException("不支持的工作类型.");
		}
		//执行参数
		if (StringUtils.isBlank(job.getArguments())) {
			throw new ApiException("工作参数为空.");
		}
		//执行周期
		if (null == job.getCycle()) {
			job.setCycle("");
		}
		//并发数
		if (job.getConcurrentNum() < Job.CONCURRENT_NUM_MIN && 
			job.getConcurrentNum() > Job.CONCURRENT_NUM_MAX
		) {
			job.setConcurrentNum(Job.CONCURRENT_NUM_DEFAULT);
		}
		//状态
		switch (job.getEnabled()) {
		case Const.TRUE : 
		case Const.FALSE : break;
		default : job.setEnabled(Const.TRUE);
		}
		//编号
		job.setId(Const.JOB_ID_PREFIX + this.snowflakeGenerator.next());
		return super.save(job);
	}

	@Override
	public Boolean enable(String id) {
		return null;
	}

	@Override
	public Boolean disable(String id) {
		return null;
	}
	
}

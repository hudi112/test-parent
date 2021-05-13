package com.z1.runner.controller;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z1.runner.entity.Job;
import com.z1.runner.service.IJobService;
import com.z1.web.Const;
import com.z1.web.model.R;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 工作表 前端控制器
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

	@Autowired
	private IJobService jobService;
	
	@PostMapping("/save")
	@ApiOperation(notes = "新增工作", value = "新增工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "工作名称", dataTypeClass = String.class, required = true)
		,@ApiImplicitParam(name = "type", value = "工作类型", dataTypeClass = String.class, required = true)
		,@ApiImplicitParam(name = "arguments", value = "工作参数", dataTypeClass = String.class, required = true)
		,@ApiImplicitParam(name = "cycle", value = "工作周期", dataTypeClass = String.class, required = false, defaultValue = "")
		,@ApiImplicitParam(name = "concurrentNum", value = "并发数", dataTypeClass = Integer.class, required = true, defaultValue = "1")
		,@ApiImplicitParam(name = "enabled", value = "是否启用(0-否/1-是)，默认为是(1)", dataTypeClass = Integer.class, required = false, defaultValue = "" + Const.TRUE)
	})
	public R<Boolean> save(
		@RequestParam(name = "name", required = true) String name
		,@RequestParam(name = "type", required = true) String type
		,@RequestParam(name = "arguments", required = true) String arguments
		,@RequestParam(name = "cycle", required = false, defaultValue = "") String cycle
		,@RequestParam(name = "concurrentNum", required = false, defaultValue = "1") Integer concurrentNum
		,@RequestParam(name = "enabled", required = false, defaultValue = "" + Const.TRUE) Integer enabled
	) {
		return new R.Builder<Boolean>()
			.success(jobService.save(
				Job.builder()
					.name(name.trim())
					.type(type.trim())
					.arguments(arguments.trim())
					.cycle(cycle.trim())
					.concurrentNum(concurrentNum)
					.enabled(enabled)
					.build()
			))
			.build();
	}
	
	@PostMapping("/remove")
	@ApiOperation(notes = "删除工作", value = "删除工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工作编号", dataTypeClass = String.class, required = true)
	})
	public R<Boolean> remove(
		@RequestParam(name = "id", required = true) String id 
	) {
		return new R.Builder<Boolean>()
			.success(jobService.removeById(id.trim()))
			.build();
	}
	
	@PostMapping("/modify")
	@ApiOperation(notes = "编辑工作", value = "编辑工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工作编号", dataTypeClass = String.class, required = true)
		,@ApiImplicitParam(name = "name", value = "工作名称", dataTypeClass = String.class, required = false)
		,@ApiImplicitParam(name = "path", value = "工作路径", dataTypeClass = String.class, required = false)
	})
	public R<Boolean> modify(
		@RequestParam(name = "id", required = true) String id 
		,@RequestParam(name = "name", required = true) String name
		,@RequestParam(name = "type", required = true) String type
		,@RequestParam(name = "arguments", required = true) String arguments
		,@RequestParam(name = "cycle", required = false, defaultValue = "") String cycle
		,@RequestParam(name = "concurrentNum", required = false, defaultValue = "1") Integer concurrentNum
	) {
		Job job = Job.builder().id(id.trim()).build();
		if (StringUtils.isNotBlank(name)) { job.setName(name.trim()); }
		
		return new R.Builder<Boolean>()
			.success(jobService.updateById(job))
			.build();
	}
	
	@PostMapping("/enable")
	@ApiOperation(notes = "启用工作", value = "启用工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工作编号", dataTypeClass = String.class, required = true) 
	})
	public R<Boolean> enable(
		@RequestParam(name = "id", required = true) String id
	) {
		return new R.Builder<Boolean>()
			.success(jobService.enable(id.trim()))
			.build();
	}
	
	@PostMapping("/disable")
	@ApiOperation(notes = "禁用工作", value = "禁用工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工作编号", dataTypeClass = String.class, required = true)
	})
	public R<Boolean> disable(
		@RequestParam(name = "id", required = true) String id
	) {
		return new R.Builder<Boolean>()
			.success(jobService.disable(id.trim()))
			.build();
	}
	
	@GetMapping("/find")
	@ApiOperation(notes = "查询工作", value = "查询工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",  value = "工作编号", dataTypeClass = String.class, required = false)
		,@ApiImplicitParam(name = "name", value = "工作名称", dataTypeClass = String.class, required = false)
		,@ApiImplicitParam(name = "enabled", value = "是否启用(0-否/1-是)", dataTypeClass = Integer.class, required = false)
	})
	public R<List<Job>> find(
		@RequestParam(name = "id", required = false) String id 
		,@RequestParam(name = "name", required = false) String name 
		,@RequestParam(name = "enabled", required = false) Integer enabled
	) {
		Job module = new Job();
		if (StringUtils.isNotBlank(id)) { module.setId(id.trim()); }
//		if (StringUtils.isNotBlank(name)) { module.setName(name.trim()); }
		if (null != enabled) { module.setEnabled(enabled); }
		
		QueryWrapper<Job> wrapper = new QueryWrapper<Job>(module);
		if (StringUtils.isNotBlank(name)) {
			wrapper.like(Job.FIELD_NAME_NAME, name.trim());
		}
		
		return new R.Builder<List<Job>>()
			.success(jobService.list(wrapper))
			.build();
	}
	
	@GetMapping("/page")
	@ApiOperation(notes = "分页查询工作", value = "分页查询工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",  value = "工作编号", dataTypeClass = String.class, required = false)
		,@ApiImplicitParam(name = "name", value = "工作名称", dataTypeClass = String.class, required = false)
		,@ApiImplicitParam(name = "enabled", value = "是否启用(0-否/1-是)", dataTypeClass = Integer.class, required = false)
		,@ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Long.class, required = false, defaultValue = "" + Const.PAGE_NUM_DEFAULT)
		,@ApiImplicitParam(name = "pageSize", value = "每页条数", dataTypeClass = Long.class, required = false, defaultValue = "" + Const.PAGE_SIZE_DEFAULT)
	})
	public R<Page<Job>> page(
		@RequestParam(name = "id", required = false) String id 
		,@RequestParam(name = "name", required = false) String name 
		,@RequestParam(name = "enabled", required = false) Integer enabled
		,@RequestParam(name = "pageNum", required = false, defaultValue = "" + Const.PAGE_NUM_DEFAULT) Long pageNum
		,@RequestParam(name = "pageSize", required = false, defaultValue = "" + Const.PAGE_SIZE_DEFAULT) Long pageSize
	) {
		Job job = new Job();
		if (StringUtils.isNotBlank(id)) { job.setId(id.trim()); }
//		if (StringUtils.isNotBlank(name)) { module.setName(name.trim()); }
		if (null != enabled) { job.setEnabled(enabled); }
		
		QueryWrapper<Job> wrapper = new QueryWrapper<Job>(job);
		if (StringUtils.isNotBlank(name)) {
			wrapper.like(Job.FIELD_NAME_NAME, name.trim());
		}
		
		return new R.Builder<Page<Job>>()
			.success(jobService.page(new Page<Job>(pageNum, pageSize), wrapper))
			.build();
	}
	
	@GetMapping("/get")
	@ApiOperation(notes = "获取工作", value = "获取工作")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",  value = "工作编号", dataTypeClass = String.class, required = true)
	})
	public R<Job> get(
		@RequestParam(name = "id", required = true) String id
	) {
		Job Job = jobService.getById(id.trim());
		if (null == Job) {
			return new R.Builder<Job>()
				.failed("没有找到指定工作")
				.build();
		}
		else {
			return new R.Builder<Job>()
				.success(Job)
				.build();
		}
	}
	
}

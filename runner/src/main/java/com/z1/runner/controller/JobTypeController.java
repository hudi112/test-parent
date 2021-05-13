package com.z1.runner.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.z1.runner.job.JobContext;
import com.z1.web.model.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 工作类型  前端控制器
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */

@RestController
@RequestMapping("/jobType")
@Api(tags = "工作类型管理", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
public class JobTypeController extends BaseController {

	@Autowired
	private JobContext jobContext;
	
	@PostMapping("/availableList")
	@ApiOperation(notes = "可用工作类型列表", value = "可用工作类型列表")
	public R<Set<String>> availableList() {
		return new R.Builder<Set<String>>()
			.success(jobContext.listAvailableTaskType())
			.build();
	}
	
}

package com.z1.runner.service;

import com.z1.runner.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工作表 服务类
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */
public interface IJobService extends IService<Job> {

	Boolean enable(String id);

	Boolean disable(String id);

}

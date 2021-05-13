package com.z1.runner.service.impl;

import com.z1.runner.entity.User;
import com.z1.runner.mapper.UserMapper;
import com.z1.runner.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

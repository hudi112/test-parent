package com.z1.runner.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.z1.runner.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
public class UserController extends BaseController {

}

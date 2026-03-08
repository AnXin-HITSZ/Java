package com.anxin_hitsz.controller;

import cn.hutool.core.io.IoUtil;
import com.anxin_hitsz.pojo.User;
import com.anxin_hitsz.service.UserService;
import com.anxin_hitsz.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: UserController
 * Package: com.anxin_hitsz.controller
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 20:46
 * @Version 1.0
 */

/**
 * 用户信息 Controller
 */
@RestController // @ResponseBody - 作用：将 Controller 的返回值直接作为响应体的数据直接响应；如果返回值是对象 / 集合 -> JSON 后响应
public class UserController {

    // 方式一：属性注入
    @Resource(name = "userServiceImpl2")
    private UserService userService;

    // 方式二：构造器注入
//    private final UserService userService;
//    //@Autowired    -> 如果当前类中只存在一个构造函数，@Autowired 可以省略
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    // 方式三：setter 注入
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping("/list")
    public List<User> list() throws Exception {
        // 1. 调用 Service，获取数据
        List<User> userList = userService.findAll();

        // 2. 返回数据（JSON 格式）
        return userList;
    }

}

package com.anxin_hitsz.service.impl;

import com.anxin_hitsz.dao.UserDao;
import com.anxin_hitsz.pojo.User;
import com.anxin_hitsz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Package: com.anxin_hitsz.service.impl
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 22:22
 * @Version 1.0
 */
//@Primary
@Service
//@Component  // 将当前类交给 IOC 容器管理
public class UserServiceImpl2 implements UserService {

    @Autowired  // 应用程序运行时，会自动地查询该类型的 Bean 对象，并赋值给该成员变量
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        // 1. 调用 Dao，获取数据
        List<String> lines = userDao.findAll();

        // 2. 解析用户信息，封装为 User 对象 -> list 集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id + 200, username, password, name, age, updateTime);
        }).toList();

        return userList;
    }
}

package com.anxin_hitsz;

import com.anxin_hitsz.mapper.UserMapper;
import com.anxin_hitsz.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // SpringBoot 单元测试的注解 - 当前测试类中的测试方法运行时，会启动 SpringBoot 项目 - IOC 容器
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }

}

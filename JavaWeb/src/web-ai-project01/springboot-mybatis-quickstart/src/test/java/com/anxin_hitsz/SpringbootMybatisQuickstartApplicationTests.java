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

    /**
     * 测试删除
     */
    @Test
    public void testDeleteById() {
        Integer cnt = userMapper.deleteById(4);
        System.out.println("执行完毕，影响的记录数：" + cnt);
    }

    /**
     * 测试新增
     */
    @Test
    public void testInsert() {
        User user = new User(null, "Tom", "123456", "汤姆", 20);
        userMapper.insert(user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = new User(1, "Tom", "123456", "汤姆", 20);
        userMapper.update(user);
    }

    /**
     * 测试查询
     */
    @Test
    public void testFindByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("Tom", "123456");
        System.out.println(user);
    }

}

package com.anxin_hitsz.mapper;

import com.anxin_hitsz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: UserMapper
 * Package: com.anxin_hitsz.mapper
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/6 22:04
 * @Version 1.0
 */
@Mapper // 应用程序在运行时，会自动地为该接口创建一个实现类对象（代理对象），并且会自动地将该实现类对象存入 IOC 容器 - Bean 对象
public interface UserMapper {

    /**
     * 查询所有用户
     */
    @Select("select id, username, password, name, age from user")
    public List<User> findAll();

}

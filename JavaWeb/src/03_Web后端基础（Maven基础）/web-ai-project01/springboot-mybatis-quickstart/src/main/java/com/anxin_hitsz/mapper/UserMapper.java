package com.anxin_hitsz.mapper;

import com.anxin_hitsz.pojo.User;
import org.apache.ibatis.annotations.*;

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
//    @Select("select id, username, password, name, age from user")
    public List<User> findAll();

    /**
     * 根据 id 删除用户
     */
    @Delete("delete from user where id = #{id}")
//    public void deleteById(Integer id);
    public Integer deleteById(Integer id);

    /**
     * 新增用户
     */
    @Insert("insert into user (username, password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void update(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

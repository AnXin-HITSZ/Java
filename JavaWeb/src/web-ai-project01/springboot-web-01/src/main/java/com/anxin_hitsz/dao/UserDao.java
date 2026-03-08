package com.anxin_hitsz.dao;

import com.anxin_hitsz.pojo.User;

import java.util.List;

/**
 * ClassName: UserDao
 * Package: com.anxin_hitsz.dao
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 22:20
 * @Version 1.0
 */
public interface UserDao {

    /**
     * 加载用户数据
     */
    public List<String> findAll();

}

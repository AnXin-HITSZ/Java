package com.anxin_hitsz.service;

import com.anxin_hitsz.pojo.User;

import java.util.List;

/**
 * ClassName: UserService
 * Package: com.anxin_hitsz.service
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 22:21
 * @Version 1.0
 */
public interface UserService {

    /**
     * 查询所有用户信息
     */
    public List<User> findAll();

}

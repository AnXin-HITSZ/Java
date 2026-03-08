package com.anxin_hitsz.service;

import com.anxin_hitsz.pojo.Dept;

import java.util.List;

/**
 * ClassName: DeptService
 * Package: com.anxin_hitsz.service
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/8 13:22
 * @Version 1.0
 */
public interface DeptService {
    /**
     * 查询所有部门
     */
    List<Dept> findAll();

    /**
     * 根据 ID 删除部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    void add(Dept dept);

    /**
     * 根据 ID 查询部门
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     */
    void update(Dept dept);
}

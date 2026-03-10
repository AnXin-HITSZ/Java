package com.anxin_hitsz.service.impl;

import com.anxin_hitsz.mapper.EmpMapper;
import com.anxin_hitsz.pojo.Emp;
import com.anxin_hitsz.pojo.PageResult;
import com.anxin_hitsz.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: EmpServiceImpl
 * Package: com.anxin_hitsz.service.impl
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/10 16:31
 * @Version 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 1. 调用 Mapper 接口，查询总记录数
        Long total = empMapper.count();

        // 2. 调用 Mapper 接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        // 3. 封装结果 PageResult
        return new PageResult<Emp>(total, rows);
    }
}

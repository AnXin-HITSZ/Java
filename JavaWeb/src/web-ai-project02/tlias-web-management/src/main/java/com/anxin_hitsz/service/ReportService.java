package com.anxin_hitsz.service;

import com.anxin_hitsz.pojo.JobOption;

import java.util.List;
import java.util.Map;

/**
 * ClassName: ReportService
 * Package: com.anxin_hitsz.service
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/14 13:26
 * @Version 1.0
 */
public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();
}

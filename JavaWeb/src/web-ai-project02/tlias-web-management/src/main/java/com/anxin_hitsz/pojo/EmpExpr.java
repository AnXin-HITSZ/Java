package com.anxin_hitsz.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * ClassName: EmpExpr
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/10 16:28
 * @Version 1.0
 */
@Data
public class EmpExpr {
    private Integer id; // ID
    private Integer empId;  // 员工 ID
    private LocalDate begin;    // 开始时间
    private LocalDate end;  // 结束时间
    private String company; // 公司名称
    private String job; // 职位
}

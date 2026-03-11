package com.anxin_hitsz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * ClassName: EmpQueryParam
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/10 21:39
 * @Version 1.0
 */
@Data
public class EmpQueryParam {
    private Integer page = 1;   // 页码
    private Integer pageSize = 10;  // 每页展示记录数
    private String name;    // 姓名
    private Integer gender; // 性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;    // 入职时间 - 开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;  // 入职时间 - 结束
}

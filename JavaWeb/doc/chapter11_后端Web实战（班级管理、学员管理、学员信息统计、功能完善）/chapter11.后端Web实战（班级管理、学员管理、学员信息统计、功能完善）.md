# 第十一章：后端 Web 实战（班级管理、学员管理、学员信息统计、功能完善）

**目录：**

[TOC]

---

## 一、数据准备

在数据库中，创建学生表 clazz、student。

实体类准备：

1). 实体类 `Clazz`

```java
/* pojo/Clazz.java */

package com.anxin_hitsz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: Clazz
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/14 14:24
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; // ID
    private String name;    // 班级名称
    private String room;    // 班级教室
    private LocalDate beginDate;    // 开课时间
    private LocalDate endDate;  // 结课时间
    private Integer masterId;   // 班主任
    private Integer subject;    // 学科
    private LocalDateTime createTime;   // 创建时间
    private LocalDateTime updateTime;   // 修改时间

    private String masterName;  // 班主任姓名
    private String status;  // 班级状态 - 未开班、在读、已结课
}

```

2). 实体类 `Student`

```java
/* pojo/Student.java */

package com.anxin_hitsz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/14 14:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id; // ID
    private String name;    // 姓名
    private String no;  // 序号
    private Integer gender; // 性别：1 - 男；2 - 女
    private String phone;   // 手机号
    private String idCard;  // 身份证号
    private Integer isCollege;  // 是否来自于院校：1 - 是；0 - 否
    private String address; // 联系地址
    private Integer degree; // 最高学历：1 - 初中；2 - 高中；3 - 大专；4 - 本科；5 - 硕士；6 - 博士
    private LocalDateTime graduationDate;   // 毕业时间
    private Integer clazzId;    // 班级 ID
    private Short violationCount;   // 违纪次数
    private Short violationScore;   // 违纪扣分
    private LocalDateTime createTime;   // 创建时间
    private LocalDateTime updateTime;   // 修改时间

    private String clazzName;   // 班级名称
}

```
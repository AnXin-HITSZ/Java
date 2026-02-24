package com.anxin_hitsz_02.selfdefine.apply;

import java.sql.Date;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 *      ORM（object relational mapping）思想
 *      数据库中的一个表与 Java 中的一个类对应
 *      表中的一条记录与 Java 类的一个对象对应
 *      表中的一个字段（或列）与 Java 类的一个属性（或字段）对应
 *
 * @Author AnXin
 * @Create 2026/2/24 19:54
 * @Version 1.0
 */
public class Customer {
    int id;
    String name;
    String email;
    Date birth;

}

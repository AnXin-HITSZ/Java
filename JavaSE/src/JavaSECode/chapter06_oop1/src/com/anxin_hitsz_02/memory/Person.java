package com.anxin_hitsz_02.memory;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_02.memory
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/5 11:19
 * @Version 1.0
 */
public class Person {

    // 属性
    String name;    // 姓名
    int age;    // 年龄
    char gender;    // 性别


    // 方法
    public void eat(){
        System.out.println("人吃饭");
    }

    public void sleep(int hour){
        System.out.println("人至少保证每天 " + hour + " 小时的睡眠");
    }

    public void interests(String hobby){
        System.out.println("我的爱好是：" + hobby);
    }
}

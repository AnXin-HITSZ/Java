package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: Graduate
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:58
 * @Version 1.0
 */
class Graduate extends Student {
    public String major="IT";
    public String getInfo()
    {
        return  "Name: "+ name + "\nage: "+ age
                + "\nschool: "+ school+"\nmajor:"+major;
    }
}

package com.anxin_hitsz_04.example.exer1;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_04.example.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:03
 * @Version 1.0
 */
public class Person {
    String name;
    int age;
    char gender;

    public void study(){
        System.out.println("studying");
    }

    public int showAge(){
        return age;
    }

    public void addAge(int addAge){
        age += addAge;
    }
}

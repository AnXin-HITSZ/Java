package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:57
 * @Version 1.0
 */
class Person {
    protected String name="person";
    protected int age=50;
    public String getInfo() {
        return "Name: "+ name + "\n" +"age: "+ age;
    }
}

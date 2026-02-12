package com.anxin_hitsz_03.list;

import java.util.Objects;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_01.collection
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/11 20:45
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals() ...");
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(name);
//        result = 31 * result + age;
//        return result;
//    }

}

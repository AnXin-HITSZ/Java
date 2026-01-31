package com.anxin_hitsz_08._interface.exer3;

/**
 * ClassName: Developer
 * Package: com.anxin_hitsz_08._interface.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:15
 * @Version 1.0
 */
public class Developer {
    private String name;
    private int age;

    public Developer() {
    }

    public Developer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void takingVehicle(Vehicle vehicle) {
        vehicle.run();
    }
}

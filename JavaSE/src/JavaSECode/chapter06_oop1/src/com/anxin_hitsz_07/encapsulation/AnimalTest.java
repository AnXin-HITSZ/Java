package com.anxin_hitsz_07.encapsulation;

/**
 * ClassName: AnimalTest
 * Package: com.anxin_hitsz_07.encapsulation
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/19 15:41
 * @Version 1.0
 */
public class AnimalTest {
    public static void main(String[] args) {
        Animal animal1 = new Animal();

        animal1.name = "金蟾";
        // 因为 legs 声明为 private ，是私有的，出了 Animal 类之外就不能调用了
//        animal1.legs = 4;
//        animal1.legs = -4;

        // 只能通过 setLegs()，间接地对 legs 属性进行赋值
        animal1.setLegs(2);
        animal1.setLegs(-2);

//        System.out.println("name = " + animal1.name + "，legs = " + animal1.legs);

        System.out.println("name = " + animal1.name + "，legs = " + animal1.getLegs());

        animal1.eat();

    }



}

class Animal {  // 动物
    // 属性
    String name;    // 名字
    private int legs;   // 腿的个数

    // 方法
    // 设置 legs 的属性值
    public void setLegs(int l) {
        if (l >= 0 && l % 2 == 0) {
            legs = l;
        } else {
            System.out.println("你输入的数据非法");
        }
    }

    // 获取 legs 的属性值
    public int getLegs() {
        return legs;
    }

    public void eat() {
        System.out.println("动物觅食");
    }
}

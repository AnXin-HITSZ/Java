package com.anxin_hitsz_08._interface.exer1;

/**
 * ClassName: EatableTest
 * Package: com.anxin_hitsz_08._interface.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 14:57
 * @Version 1.0
 */
public class EatableTest {
    public static void main(String[] args) {
        Eatable[] eatables = new Eatable[3];

        eatables[0] = new Chinese();    // 多态性
        eatables[1] = new American();
        eatables[2] = new Indian();

        for (int i = 0; i < eatables.length; i++) {
            eatables[i].eat();
        }
    }
}

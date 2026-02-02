package com.anxin_hitsz_10._enum.exer3;

/**
 * ClassName: ColorTest
 * Package: com.anxin_hitsz_10._enum.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/2 14:47
 * @Version 1.0
 */
public class ColorTest {
    public static void main(String[] args) {
        System.out.println(Color.GREEN);
    }
}

enum Color {
    RED(255, 0, 0, "红色"),
    ORABGE(255, 128, 0, "橙色"),
    YELLOW(255, 255, 0, "黄色"),
    GREEN(0, 255, 0, "绿色"),
    CYAN(0, 255, 255, "青色"),
    BLUE(0, 0, 255, "蓝色"),
    PURPLE(128, 0, 255, "紫色");

    private final int red;
    private final int greed;
    private final int blue;
    private final String description;   // 颜色的描述

    Color(int red, int greed, int blue, String description) {
        this.red = red;
        this.greed = greed;
        this.blue = blue;
        this.description = description;
    }

    public int getRed() {
        return red;
    }

    public int getGreed() {
        return greed;
    }

    public int getBlue() {
        return blue;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
//        return name() + "(" + red + ", " + greed + ", " + blue + ") -> " + description;
        return super.toString() + "(" + red + ", " + greed + ", " + blue + ") -> " + description;
    }
}

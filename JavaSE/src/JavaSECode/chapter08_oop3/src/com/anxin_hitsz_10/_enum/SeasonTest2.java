package com.anxin_hitsz_10._enum;

/**
 * ClassName: SeasonTest2
 * Package: com.anxin_hitsz_10._enum
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/2 14:14
 * @Version 1.0
 */
public class SeasonTest2 {
    public static void main(String[] args) {

        Season2[] values = Season2.values();
        for (int i = 0; i < values.length; i++) {
            values[i].show();
        }

    }
}

interface Info1 {
    void show();
}

// jdk 5.0 中使用 enum 关键字定义枚举类
enum Season2 implements Info {
    //  1. 必须在枚举类的开头声明多个对象，对象之间使用 “,” 隔开
    SPRING("春天", "春暖花开") {
        public void show() {
            System.out.println("春天");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天", "白雪皑皑") {
        public void show() {
            System.out.println("冬天");
        }
    };

    // 2. 声明当前类的对象的实例变量，使用 private final 修饰
    private final String seasonName;  // 季节的名称
    private final String seasonDesc;  // 季节的描述

    // 3. 私有化类的构造器
    private Season2(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 3. 提供实例变量的 get 方法
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season2{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

}

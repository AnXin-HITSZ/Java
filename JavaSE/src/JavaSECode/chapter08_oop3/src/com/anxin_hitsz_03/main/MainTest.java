package com.anxin_hitsz_03.main;

/**
 * ClassName: MainTest
 * Package: com.anxin_hitsz_03.main
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:48
 * @Version 1.0
 */
public class MainTest {
    public static void main(String[] args) {    // 程序的入口
        String[] arr = new String[] {"AA", "BB", "CC"};
        Main.main(arr);
    }
}

class Main {

    public static void main(String[] args) {    // 看做是普通的静态方法
        System.out.println("Main 的 main() 方法的调用");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

}

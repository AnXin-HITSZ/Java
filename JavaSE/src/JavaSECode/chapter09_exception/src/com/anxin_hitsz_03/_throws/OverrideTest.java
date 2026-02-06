package com.anxin_hitsz_03._throws;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: OverrideTest
 * Package: com.anxin_hitsz_03._throws
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 15:55
 * @Version 1.0
 */
public class OverrideTest {
    public static void main(String[] args) {

        Father f = new Son();

        try {
            f.method1();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Number n = f.method4();


    }
}

class Father {
    public void method1() throws IOException {

    }

    public void method2() {

    }

    public void method3() {

    }

    public Number method4() {
        return null;
    }

}

class Son extends Father {
    public void method1() throws FileNotFoundException {

    }

//    public void method2() throws FileNotFoundException {
//
//    }

    public void method3() throws RuntimeException {

    }

    public Integer method4() {
        return null;
    }

}

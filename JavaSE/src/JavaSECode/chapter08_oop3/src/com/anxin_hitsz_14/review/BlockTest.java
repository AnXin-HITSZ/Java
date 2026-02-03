package com.anxin_hitsz_14.review;

/**
 * ClassName: BlockTest
 * Package: com.anxin_hitsz_14.review
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/3 20:55
 * @Version 1.0
 */
public class BlockTest {

    static {
        System.out.println("abc");
    }

    {
        System.out.println("hello");
    }

    public BlockTest() {

    }

    public BlockTest(int id) {}
}

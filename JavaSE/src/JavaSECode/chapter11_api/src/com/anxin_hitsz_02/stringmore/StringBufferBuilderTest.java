package com.anxin_hitsz_02.stringmore;

import org.junit.Test;

/**
 * ClassName: StringBufferBuilderTest
 * Package: com.anxin_hitsz_02.stringmore
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 17:02
 * @Version 1.0
 */
public class StringBufferBuilderTest {

    /*
    * 常用 API
    * */

    @Test
    public void test1() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("abc").append("123").append("def"); // 方法链的调用
        System.out.println(sBuilder);

    }

    @Test
    public void test2() {
        StringBuilder sBuilder = new StringBuilder("hello");
        sBuilder.insert(2, 1);
        sBuilder.insert(2, "abc");
        System.out.println(sBuilder);

        StringBuilder sBuilder1 = sBuilder.reverse();

        System.out.println(sBuilder);
        System.out.println(sBuilder1);

        System.out.println(sBuilder == sBuilder1);

        System.out.println(sBuilder.length());  // 实际存储的字符的个数

    }

    /*
    * 其它 API
    * */

    @Test
    public void test3() {
        StringBuilder sBuilder = new StringBuilder("hello");
        sBuilder.setLength(2);

        System.out.println(sBuilder);

        sBuilder.append("c");
        System.out.println(sBuilder);

        sBuilder.setLength(10);
        System.out.println(sBuilder);
        System.out.println(sBuilder.charAt(6) == 0);    // true

    }

    /*
    * 测试 String、StringBuffer、StringBuilder 在操作数据方面的效率
    * */
    @Test
    public void test4() {
        //初始设置
        long startTime = 0L;
        long endTime = 0L;


        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");

        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));
    }

}

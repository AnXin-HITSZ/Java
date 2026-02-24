package com.anxin_hitsz_03.more;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:30
 * @Version 1.0
 */
public class GenericTest {
    @Test
    public void test1() {
        // 1.
        Object obj = null;
        String str = "AA";

        obj = str;  // 基于继承性的多态的使用

        // 2.
        Object[] arr = null;
        String[] arr1 = null;

        arr = arr1; // 基于继承性的多态的使用

    }

    /*
    * 类 SuperA 是类 A 的父类，则 G<SuperA> 与 G<A> 的关系：
    *   G<SuperA> 和 G<A> 是并列的两个类，没有任何子父类的关系。
    * */
    @Test
    public void test2() {
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

//        list1 = list2;
        /*
        * 反证法：
        * 假设 list1 = list2 是可以的。则根据以下推理即可得出矛盾：
        * 1. list2.add("AA");
        * 2. list1.add(123);
        * 3. String str = list2.get(1);  // 相当于取出的 123 赋值给了 str，是错误的。
        *
        * */

        method(list1);
//        method(list2);  // 错误的

    }

    @Test
    public void test3() {
        Person<Object> per = null;
        Person<String> per1 = null;
//        per = per1;

    }

    public void method(ArrayList<Object> list) {

    }

    /*
    * 类 SuperA 是类 A 的父类或接口，SuperA<G> 与 A<G> 的关系：
    *   SuperA<G> 与 A<G> 有继承或实现的关系；即 A<G> 的实例可以赋值给 SuperA<G> 类型的引用（或变量）。
    * */
    @Test
    public void test4() {
        List<String> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

        list1 = list2;

        list1.add("AA");

        method(list2);

    }

    public void method(List<String> list) {

    }

}

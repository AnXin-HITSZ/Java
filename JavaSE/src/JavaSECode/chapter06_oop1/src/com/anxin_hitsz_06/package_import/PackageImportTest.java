package com.anxin_hitsz_06.package_import;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;

import com.anxin_hitsz_05.method_more._04recursion.RecursionTest;
import com.anxin_hitsz_05.method_more._04recursion.exer2.RabbitExer;

import java.lang.reflect.Field;
import java.util.*;

import static java.lang.System.out;
import static java.lang.Math.PI;

/**
 * ClassName: PackageImportTest
 * Package: com.anxin_hitsz_06.package_import
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/19 14:06
 * @Version 1.0
 */
public class PackageImportTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList list = new ArrayList();

        HashMap map = new HashMap();

        HashSet set = new HashSet();

        String str = "hello";
        System.out.println("str");

        Person p =  new Person();

        Field field = null;

        RecursionTest test = null;

        RabbitExer exer = null;

        // Date 可以使用 import 的方式指明
        Date date = new Date();

        // 使用全类名的方式
        java.sql.Date date1 = new java.sql.Date(121231231L);

        out.println("hello");

        out.println(PI);

    }
}

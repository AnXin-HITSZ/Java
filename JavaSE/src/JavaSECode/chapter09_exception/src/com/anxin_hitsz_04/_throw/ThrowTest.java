package com.anxin_hitsz_04._throw;

/**
 * ClassName: ThrowTest
 * Package: com.anxin_hitsz_04._throw
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 16:35
 * @Version 1.0
 */
public class ThrowTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        try {
            s1.regist(10);
            s1.regist(-10);
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        }

    }
}

class Student {
    int id;

//    public void regist(int id) {
//        if (id > 0) {
//            this.id = id;
//        } else {
////            System.out.println("输入的 id 非法");
//            // 手动抛出异常类的对象
////            throw new RuntimeException("输入的 id 非法");
//        }
//    }

    public void regist(int id) throws Exception {
        if (id > 0) {
            this.id = id;
        } else {
//            System.out.println("输入的 id 非法");
            // 手动抛出异常类的对象
//            throw new RuntimeException("输入的 id 非法");

//            throw new Exception("输入的 id 非法"); // 非法

            throw new BelowZeroException("输入的 id 非法");

//            System.out.println("此语句不能被执行");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}

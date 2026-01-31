package com.anxin_hitsz_09.inner;

/**
 * ClassName: OuterClassTest2
 * Package: com.anxin_hitsz_09.inner
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 18:51
 * @Version 1.0
 */
public class OuterClassTest2 {
    public static void main(String[] args) {

        SubA a = new SubA();
        a.method();

        // 举例 1：提供接口匿名实现类的对象
        A a1 = new A() {
            public void method() {
                System.out.println("匿名实现类重写的方法 method()");
            }
        };

        a1.method();

        // 举例 2：提供接口匿名实现类的匿名对象
        new A() {
            public void method() {
                System.out.println("匿名实现类重写的方法 method()");
            }
        }.method();

        // 举例 3：
        SubB s1 = new SubB();
        s1.method1();

        // 举例 4：提供了继承于抽象类的匿名子类的对象
        B b = new B() {
            public void method1() {
                System.out.println("继承于抽象类的子类调用的方法");
            }
        };

        b.method1();
        System.out.println(b.getClass());
        System.out.println(b.getClass().getSuperclass());

        // 举例 5：
        new B() {
            public void method1() {
                System.out.println("继承于抽象类的子类调用的方法 1");
            }
        }.method1();

        // 举例 6：
        C c = new C();
        c.method2();

        // 举例 7：提供了一个继承于 C 的匿名子类的对象
        C c1 = new C() {};
        c1.method2();
        System.out.println(c1.getClass());
        System.out.println(c1.getClass().getSuperclass());


        C c2 = new C() {
            public void method2() {
                System.out.println("SubC");
            }
        };
        c2.method2();

    }
}

interface A {
    public void method();
}

class SubA implements A {
    @Override
    public void method() {
        System.out.println("SubA");
    }
}

abstract class B {

    public abstract void method1();

}

class SubB extends B {

    @Override
    public void method1() {
        System.out.println("SubB");
    }
}


class C {
    public void method2() {
        System.out.println("C");
    }
}

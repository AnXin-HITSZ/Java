# 第八章：面向对象编程（高级）

**目录：**

[TOC]

---

本章专题与脉络：
![第 2 阶段：Java 面向对象编程 - 第 08 章](./images/第2阶段：Java面向对象编程-第08章.png "第 2 阶段：Java 面向对象编程 - 第 08 章")

## 一、关键字：`static`

**回顾类中的实例变量（即非 `static` 的成员变量）：**

```java
class Circle {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double findArea() {
        return Math.PI * radius * radius;
    }
}
```

创建两个 `Circle` 对象：
```java
Circle c1 = new Circle(2.0);    // c1.radius = 2.0
Circle c2 = new Circle(3.0);    // c2.radius = 3.0
```

`Circle` 类中的变量 `radius` 是一个实例变量（instance variable），它属于类的每一个对象，`c1` 中的 `radius` 变化不会影响 `c2` 的 `radius`，反之亦然。

**如果想让一个成员变量被类的所有实例所共享，就用 `static` 修饰即可，称为类变量（或类属性）！**

### 1.1 类属性、类方法的设计思想

当我们编写一个类时，其实就是在描述其对象的属性和行为，而并没有产生实质上的对象；只有通过 new 关键字才会产出对象，这时系统才会分配内存空间给对象，其方法才可以供外部调用。我们有时候希望无论是否产生了对象或无论产生了多少对象的情况下，**某些特定的数据在内存空间里只有一份**。例如，所有的中国人都有个国家名称，每一个中国人都共享这个国家名称，不必在每一个中国人的实例对象中都单独分配一个用于代表国家名称的变量。

![类属性的设计思想示例](./images/image-20220325213629311.png "类属性的设计思想示例")

此外，在类中声明的实例方法，在类的外面必须要先创建对象，才能调用；但是有些方法的调用者和当前类的对象无关，这样的方法通常被声明为**类方法**。由于不需要创建对象就可以调用类方法，从而简化了方法的调用。

这里的类变量、类方法，只需要使用 `static` 修饰即可，所以也被称为静态变量、静态方法。

### 1.2 `static` 关键字

> static：静态的。

使用范围：
* 在 Java 类中，可用 `static` 修饰属性、方法、代码块、内部类。

> 注意：构造器不能被 `static` 修饰。

被修饰后的成员具备以下特点：
* 随着类的加载而加载。
* 优先于对象存在。
* 修饰的成员，被所有对象所共享。
* 访问权限允许时，可不创建对象，直接被类调用。

### 1.3 静态变量

> 复习：
>
> 变量的分类：
> * 方式 1：按照数据类型。
>   * 基本数据类型；
>   * 引用数据类型。
> * 方式 2：按照类中声明的位置。
>   * 成员变量：按照是否使用 `static` 修饰进行分类。
>       * 使用 `static` 修饰的成员变量：静态变量（类变量）。
>       * 不使用 `static` 修饰的成员变量：非静态变量（实例变量）。
>   * 局部变量：方法内、方法形参、构造器内、构造器形参、代码块内等。

#### 1.3.1 语法格式

类中使用 `static` 修饰的成员变量就是静态变量（或类变量、类属性）。

其语法格式如下：
```java
[修饰符] class 类名 {
    [其他修饰符] static 数据类型 变量名;
}
```

#### 1.3.2 静态变量的特点

静态变量的默认值规则和实例变量一样。

静态变量值是所有对象共享。

静态变量在本类中，可以在任意方法、代码块、构造器中直接使用。

如果权限修饰符允许，在其他类中可以通过 “`类名.静态变量`” 直接访问，也可以通过 “`对象.静态变量`” 的方式访问（但是更推荐使用 “`类名.静态变量`” 的方式）。

静态变量的 `get` / `set` 方法也是静态的。当局部变量与静态变量重名时，使用 “`类名.静态变量`” 进行区分。

> 对比静态变量与实例变量：
> 1. 个数：
>   * 静态变量：在内存空间中只有一份，被类的多个对象所共享。
>   * 实例变量：类的每一个实例（或对象）都保存着一份实例变量。
> 2. 内存位置：
>   * 静态变量：JDK 6 及之前存放在方法区；JDK 7 及之后存放在堆空间。
>       * JDK 6：
>           ![JDK 6 - 静态变量的内存位置](./images/20260129144443.png "JDK 6 - 静态变量的内存位置")
>       * JDK 7：
>           ![JDK 7 - 静态变量的内存位置](./images/20260129144515.png "JDK 7 - 静态变量的内存位置")
>       * JDK 8：
>           ![JDK 8 - 静态变量的内存位置](./images/20260129144549.png "JDK 8 - 静态变量的内存位置")
>   * 实例变量：存放在堆空间的对象实体中。
> 3. 加载时机：
>   * 静态变量：随着类的加载而加载；由于类只会加载一次，所以静态变量也只有一份。
>   * 实例变量：随着对象的创建而加载；每个对象拥有一份实例变量。
> 4. 调用者：
>   * 静态变量：可以被类直接调用，也可以使用对象调用。
>   * 实例变量：只能使用对象进行调用。
> 5. 判断是否可以调用 -> 从生命周期的角度解释：
>   * 类可以调用类变量，但不可以调用实例变量。
>   * 对象可以调用类变量，也可以调用实例变量。
> 6. 消亡时机：
>   * 静态变量：随着类的卸载而消亡。
>   * 实例变量：随着对象的消亡而消亡。

> 注意：
>
> 开发中，什么时候需要将属性声明为静态的？
> * 判断当前类的多个实例是否能共享此成员变量，且此成员变量的值是相同的。
> * 开发中，常将一些常量声明为静态的；比如 `Math` 类中的 `PI`。

#### 1.3.3 举例

示例代码：
```java
/* ChineseTest.java */

package com.anxin_hitsz_01._static;

/**
 * ClassName: ChineseTest
 * Package: com.anxin_hitsz_01._static
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 14:26
 * @Version 1.0
 */
public class ChineseTest {
    public static void main(String[] args) {

        System.out.println(Chinese.nation); // 中国


        Chinese c1 = new Chinese();
        c1.name = "姚明";
        c1.age = 40;
        c1.nation = "China";

        Chinese c2 = new Chinese();
        c2.name = "刘翔";
        c2.age = 39;

        System.out.println(c1);
        System.out.println(c2);

        System.out.println(c1.nation);  // China
        System.out.println(c2.nation);  // China

        c2.nation = "CHN";

        System.out.println(c1.nation);  // CHN
        System.out.println(c2.nation);  // CHN

    }
}

class Chinese { // 中国人类
    // 非静态变量（实例变量）
    String name;
    int age;

    // 静态变量（类变量）
    static String nation = "中国";

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

#### 1.3.4 内存解析

静态变量、实例变量与局部变量的内存解析：
![静态变量、实例变量与局部变量的内存解析示意图](./images/image-20220104100145059.png "静态变量、实例变量与局部变量的内存解析示意图")

静态变量的内存解析：
![静态变量的内存解析示意图](./images/static_静态变量的内存解析.png "静态变量的内存解析示意图")

### 1.4 静态方法

#### 1.4.1 语法格式

用 `static` 修饰的成员方法就是静态方法（或称为类方法）。

其语法格式如下：
```java
[修饰符] class 类名 {
    [其他修饰符] static 返回值类型 方法名(形参列表) {
        方法体
    }
}
```

#### 1.4.2 静态方法的特点

静态方法随着类的加载而加载。

静态方法在本类的任意方法、代码块、构造器中都可以直接被调用。

只要权限修饰符允许，静态方法在其他类中可以通过 “`类名.静态方法`” 的方式调用；也可以通过 “`对象.静态方法`” 的方式调用（但是更推荐使用 “`类名.静态方法`” 的方式）。

在 `static` 方法内部只能访问类的 `static` 修饰的属性或方法，不能访问类的非 `static` 的结构。
> 静态方法内可以调用静态的属性或静态的方法（属性和方法的前缀使用的是当前类，可以省略），不可以调用非静态的结构（比如：属性、方法）。
> * 类可以调用类方法，但不可以调用实例方法。
> * 对象可以调用类方法，也可以调用实例方法。

> 补充：在类的非静态方法中，可以调用当前类的静态结构（属性、方法）或非静态结构（属性、方法）。

静态方法可以被子类继承，但不能被子类重写。

静态方法的调用都只看编译时类型。

因为不需要实例就可以访问 `static` 方法，因此 `static` 方法内部不能有 `this`，也不能有 `super`。由于静态的属性和静态的方法的前缀省略的是当前类，因此如果有重名问题，应使用 “`类名.`” 进行区别。

> 注意：
>
> 什么时候需要将方法声明为静态的？
> * 方法内操作的变量如果都是静态变量（而非实例变量）的话，则此方法建议声明为静态方法。
> * 开发中，常常将工具类中的方法声明为静态方法；比如 `Arrays` 类、`Math` 类。

#### 1.4.3 举例

示例代码：
```java
/* ChineseTest.java */

package com.anxin_hitsz_01._static;

/**
 * ClassName: ChineseTest
 * Package: com.anxin_hitsz_01._static
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 14:26
 * @Version 1.0
 */
public class ChineseTest {
    public static void main(String[] args) {

        System.out.println(Chinese.nation); // 中国
        Chinese.show();


        Chinese c1 = new Chinese();
        c1.name = "姚明";
        c1.age = 40;
        c1.nation = "China";

        Chinese c2 = new Chinese();
        c2.name = "刘翔";
        c2.age = 39;

        System.out.println(c1);
        System.out.println(c2);

        System.out.println(c1.nation);  // China
        System.out.println(c2.nation);  // China

        c2.nation = "CHN";

        System.out.println(c1.nation);  // CHN
        System.out.println(c2.nation);  // CHN

        c1.show();

        ChineseTest.test();
    }

    public static void test() {
        System.out.println("我是 static 的测试方法");
    }
}

class Chinese { // 中国人类
    // 非静态变量（实例变量）
    String name;
    int age;

    // 静态变量（类变量）
    static String nation = "中国";

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void eat(String food) {
        System.out.println("我喜欢吃 " + food);
    }

    public static void show() {
        System.out.println("我是一个中国人");

        // 调用静态的结构
        System.out.println("nation = " + nation);
        method1();

        // 调用非静态的结构
//        System.out.println("name = " + this.name);
//        this.eat("饺子");
    }

    public static void method1() {
        System.out.println("我是静态的测试方法");
    }

    public void method2() {
        System.out.println("我是非静态的测试方法");
        // 调用非静态的结构
        System.out.println("name = " + this.name);
        this.eat("饺子");

        // 调用静态的结构
        System.out.println("nation = " + nation);
        method1();
    }

    public static String getNation() {
        return nation;
    }

    public static void setNation(String nation) {
        Chinese.nation = nation;
    }
}

```

### 1.5 应用举例

示例代码：
```java
/* CircleTest.java */

package com.anxin_hitsz_01._static.apply;

/**
 * ClassName: CircleTest
 * Package: com.anxin_hitsz_01._static.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 15:35
 * @Version 1.0
 */
public class CircleTest {
    public static void main(String[] args) {

        Circle c1 = new Circle();
        System.out.println(c1);

        Circle c2 = new Circle();
        System.out.println(c2);

        Circle c3 = new Circle();
        System.out.println(c3);

        Circle c4 = new Circle(2.3);
        System.out.println(c4);

        System.out.println(Circle.total);

    }
}

class Circle {

    double radius;  // 实例变量

    int id; // 编号

    static int total;  // 创建的 Circle 实例的个数


    public Circle() {
        this.id = init;
        init++;
        total++;
    }

    public Circle(double radius) {
        this();
        this.radius = radius;
    }

    private static int init = 1001; // 自动给 id 赋值的基数

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", id=" + id +
                '}';
    }
}

```

### 1.6 练习

**练习 1：**
> 题目：
>
> 编写一个类实现银行账户的概念，包含的属性有 “账号”、“密码”、“存款余额”、“利率”、“最小余额”；定义封装这些属性的方法。要求账号要自动生成。
>
> 编写主类，使用银行账户类，输入、输出 3 个储户的上述信息。
>
> 考虑：哪些属性可以设计成 `static` 属性。

示例代码：
```java
/* Account.java */

package com.anxin_hitsz_01._static.exer1;

/**
 * ClassName: Account
 * Package: com.anxin_hitsz_01._static.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 15:44
 * @Version 1.0
 */
public class Account {

    private int id; // 账号

    private String password;    // 密码

    private double balance; // 余额

    private static double interestRate; // 利率

    private static double minBalance = 1.0; // 最小余额

    private static int init = 1001; // 用于自动生成 id 的基数

    public Account() {
        this.id = init;
        init++;
        password = "000000";
    }

    public Account(String password, double balance) {
        this.password = password;
        this.balance = balance;
        this.id = init;
        init++;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    public static void setInterestRate(double interestRate) {
        Account.interestRate = interestRate;
    }

    public static double getMinBalance() {
        return minBalance;
    }

    public static void setMinBalance(double minBalance) {
        Account.minBalance = minBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}


/* AccountTest.java */

package com.anxin_hitsz_01._static.exer1;

/**
 * ClassName: AccountTest
 * Package: com.anxin_hitsz_01._static.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 15:48
 * @Version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {

        Account acct1 = new Account();
        System.out.println(acct1);

        Account acct2 = new Account("123456", 2000);
        System.out.println(acct2);

        Account.setInterestRate(0.0123);
        Account.setMinBalance(10);

        System.out.println("银行存款的利率为：" + Account.getInterestRate());
        System.out.println("银行最小存款额度为：" + Account.getMinBalance());
    }
}

```

**练习 2：**
> 题目：
>
> 自定义一个数组的工具类，封装常用的数组算法。

示例代码：
```java
/* MyArrays.java */

package com.anxin_hitsz_01._static.exer2;

/**
 * ClassName: MyArrays
 * Package: com.anxin_hitsz_04.example.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:28
 * @Version 1.0
 */
public class MyArrays {

    /**
     * 获取 int[] 数组的最大值
     * @param arr 要获取最大值的数组
     * @return 数组的最大值
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 获取 int[] 数组的最小值
     * @param arr 要获取最小值的数组
     * @return 数组的最小值
     */
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int getAvg(int[] arr) {

        return getSum(arr) / arr.length;
    }

    public static void print(int[] arr) {  // [12, 231, 34, ...]
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print(arr[i]);
            } else {
                System.out.print(", " + arr[i]);
            }
        }

        System.out.println("]");
    }

    public static int[] copy(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1 /* 尾索引 */; i < j; i++, j--) {
            // 交互 arr[i] 与 arr[j] 位置的元素
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void sort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    // 交互 arr[i] 和 arr[i + 1]
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }

    /**
     * 使用线性查找的算法，查找指定的元素
     * @param arr 待查找的数组
     * @param target 要查找的元素
     * @return target 元素在 arr 数组中的索引位置。若未找到，则返回 -1
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }

        // 只要代码执行到此位置，一定是没找到
        return -1;
    }
}


/* MyArraysTest.java */

package com.anxin_hitsz_01._static.exer2;

/**
 * ClassName: MyArraysTest
 * Package: com.anxin_hitsz_04.example.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:31
 * @Version 1.0
 */
public class MyArraysTest {
    public static void main(String[] args) {

        int[] arr = new int[] {34, 56, 223, 2, 56, 24, 56, 67, 778, 45};

        // 求最大值
        System.out.println("最大值为：" + MyArrays.getMax(arr));
        // 求平均值
        System.out.println("平均值为：" + MyArrays.getAvg(arr));

        // 遍历
        MyArrays.print(arr);

        // 查找
        int index = MyArrays.linearSearch(arr, 24);
        if (index >= 0) {
            System.out.println("找到了，位置：" + index);
        } else {
            System.out.println("未找到");
        }

        // 排序
        MyArrays.sort(arr);
        // 遍历
        MyArrays.print(arr);

    }
}

```

### 1.7 补充说明

静态变量和静态方法与实例对象无关。

示例代码：
```java
/* StaticTest.java */

package com.anxin_hitsz_01._static.interview;

/**
 * ClassName: StaticTest
 * Package: com.anxin_hitsz_01._static.interview
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 16:01
 * @Version 1.0
 */
public class StaticTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();  // hello!
        System.out.println(order.count);    // 1
    }
}

class Order {
    public static int count = 1;

    public static void hello() {
        System.out.println("hello!");
    }
}

```

在上述代码中，尽管对象 `order` 为空（`null`），但因为静态变量和静态方法与实例对象无关，因此 `order` 仍然可以正常调用静态方法 `hello()` 并输出静态变量 `count`，不会出现空指针异常的情况。

## 二、单例（Singleton）设计模式

### 2.1 设计模式概述

**设计模式**是在大量的**实践中总结**和**理论化**之后优选的代码结构、编程风格、以及解决问题的思考方式。设计模式免去我们自己再思考和摸索，就像是经典的棋谱，面对不同的棋局我们用不同的棋谱，本质是一种“套路”。

经典的设计模式共有 23 种，每个设计模式均是特定环境下特定问题的处理方法。如下图所示：
![23 种经典的设计模式](./images/image-20220520174508815.png "23 种经典的设计模式")

> 注意：
>
> 简单工厂模式并不是 23 种经典模式的一种，是其中工厂方法模式的简化版。

### 2.2 何为单例模式？

所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类**只能存在一个对象实例**，并且该类只提供一个取得其对象实例的方法。

### 2.3 实现思路

如果我们要让类在一个虚拟机中只能产生一个对象，我们首先必须将**类的构造器的访问权限设置为 `private`**；这样，就不能用 `new` 操作符在类的外部产生类的对象了，但在类内部仍可以产生该类的对象。因为在类的外部开始还无法得到类的对象，**只能调用该类的某个静态方法**以返回类内部创建的对象，静态方法只能访问类中的静态成员变量，所以指向类内部产生的**该类对象的变量也必须定义成静态的**。

### 2.4 单例模式的两种实现方式

#### 2.4.1 饿汉式

饿汉式 语法格式：
```java
class Singleton {
    // 1. 私有化构造器
    private Singleton() {
    }

    // 2. 内部提供一个当前类的实例
    // 4. 此实例也必须静态化
    private static Singleton single = new Singleton();

    // 3. 提供公共的静态的方法，返回当前类的对象
    public static Singleton getInstance() {
        return single;
    }
}

```

示例代码：
```java
/* BankTest.java */

package com.anxin_hitsz_02.singleton;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_02.singleton
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:18
 * @Version 1.0
 */
public class BankTest {
    public static void main(String[] args) {
//        Bank bank1 = new Bank();
//        Bank bank2 = new Bank();

        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();

        System.out.println(bank1 == bank2);
    }
}

// 饿汉式
class Bank {

    // 1. 类的构造器私有化
    private Bank() {

    }

    // 2. 在类的内部创建当前类的实例
    // 4. 此属性也必须声明为 static 的
    private static Bank instance = new Bank();

    // 3. 使用 getXxx() 方法获取当前类的实例，必须声明为 static 的
    public static Bank getInstance() {
        return instance;
    }

}

```

#### 2.4.2 懒汉式

懒汉式 语法格式：
```java
class Singleton {
    // 1. 私有化构造器
    private Singleton() {
    }
    // 2. 内部提供一个当前类的实例
    // 4. 此实例也必须静态化
    private static Singleton single;
    // 3. 提供公共的静态的方法，返回当前类的对象
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
}

```

示例代码：
```java
/* GirlFriendTest.java */

package com.anxin_hitsz_02.singleton;

/**
 * ClassName: GirlFriendTest
 * Package: com.anxin_hitsz_02.singleton
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:24
 * @Version 1.0
 */
public class GirlFriendTest {
    public static void main(String[] args) {

    }
}

// 懒汉式
class GirlFriend {

    // 1. 类的构造器私有化
    private GirlFriend() {

    }

    // 2. 声明当前类的实例
    // 4. 此属性也必须声明为 static 的
    private static GirlFriend instance = null;

    // 3. 通过 getXxx() 获取当前类的实例，如果未创建对象，则在方法内部进行创建
    public static GirlFriend getInstance() {
        if (instance == null) {
            instance = new GirlFriend();
        }

        return instance;

    }
}

```

#### 2.4.3 饿汉式 vs 懒汉式

**饿汉式：**
* 特点：**立即加载**，即在使用类的时候已经将对象创建完毕。（随着类的加载，当前的唯一实例就创建了。）
* 优点：实现起来**简单**；由于内存中较早加载，使用更方便、更快；没有多线程安全问题，即是线程安全的。
* 缺点：当类被加载的时候，会初始化 `static` 的实例，静态变量被创建并分配内存空间；从这以后，这个 `static` 的实例便一直占着这块内存，直到类被卸载时，静态变量被摧毁，并释放所占有的内存。因此在某些特定条件下会**耗费内存**。（即内存中占用时间较长。）

**懒汉式：**
* 特点：**延迟加载**，即在调用静态方法时实例才被创建。（在需要使用的时候，进行创建。）
* 优点：实现起来比较简单；当类被加载的时候，`static` 的实例未被创建并分配内存空间，当静态方法第一次被调用时，初始化实例变量，并分配内存，因此在某些特定条件下会**节约内存**。（即在需要的时候进行创建，节省内存空间。）
* 缺点：在多线程环境中，这种实现方法是完全错误的，**线程不安全**，根本不能保证单例的唯一性。
  * 说明：在多线程章节，会将懒汉式改造成线程安全的模式。

### 2.5 单例模式的优点及应用场景

由于单例模式只生成一个实例，减少了**系统性能开销**，当一个对象的产生需要比较多的资源时（如读取配置、产生其他依赖对象时），则可以通过在应用启动时直接产生一个单例对象、然后永久驻留内存的方式来解决。

举例：
![单例模式举例](./images/image-20220325222541203.png "单例模式举例")

**应用场景：**

* Windows 的 Task Manager（任务管理器）就是很典型的单例模式。
* Windows 的 Recycle Bin（回收站）也是典型的单例应用；在整个系统运行过程中，回收站一直维护者仅有的一个实例。
* Application 也是单例的典型应用。
* 应用程序的日志应用，一般都使用单例模式实现，这一般是由于共享的日志文件一直处于打开状态，因为只能有一个实例去操作，否则内容不方便追加。
* 数据库连接池的设计一般也是采用单例模式，因为数据库连接是一种数据库资源。

## 三、理解 `main` 方法的语法

`main()` 方法的剖析：
```java
public static void main(String[] args[]) {}
```

由于 JVM 需要调用类的 `main()` 方法，所以该方法的访问权限必须是 `public`；又因为 JVM 在执行 `main()` 方法时不必创建对象，所以该方法必须是 `static` 的。该方法接收一个 `String` 类型的数组参数，该数组中保存执行 Java 命令时传递给所运行的类的参数。

又因为 `main()` 方法是静态的，我们不能直接访问该类中的非静态成员，必须创建该类的一个实例对象后，才能通过这个对象去访问类中的非静态成员。这种情况，我们在之前的例子中多次碰到。

> `main()` 方法的理解：
> * 理解 1：看做是一个普通的静态方法。
> * 理解 2：看做是程序的入口，格式是固定的。

示例代码：
```java
/* MainTest.java */

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

```

应用：进行与控制台的交互时，如何从键盘获取数据？
* 方式 1：使用 `Scanner`。
* 方式 2：使用 `main()` 的形参进行传值。

示例代码：
```java
/* MainDemo.java */

package com.anxin_hitsz_03.main;

/**
 * ClassName: MainDemo
 * Package: com.anxin_hitsz_03.main
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:53
 * @Version 1.0
 */
public class MainDemo {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("hello: " + args[i]);
        }
    }
}

```

**命令行参数用法举例：**

首先创建 `main()` 方法：
```java
public class CommandPara {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
    }
}

```

之后运行程序 `CommandPara.java`：
```bash
// 运行程序 CommandPara.java
java CommandPara "Tom" Jerry "Shkstart" 89 true
```

输出结果：
```java
// 输出结果
args[0] = Tom
args[1] = Jerry
args[2] = Shkstart
args[3] = 89
args[4] = true
```

运行命令解析：
![运行命令解析](./images/image-20220325223215924.png "运行命令解析")

**IDEA 工具配置及运行：**
1. 配置运行参数：
![1. 配置运行参数 - 1](./images/image-20211228101828718.png "1. 配置运行参数 - 1")
![1. 配置运行参数 - 2](./images/image-20211228102022216.png "1. 配置运行参数 - 2")
2. 运行程序：
![2. 运行程序](./images/image-20211228102059327.png "2. 运行程序")

## 四、类的成员之四：代码块

> 回顾：
>
> 类中可以声明的结构：属性、方法、构造器；代码块（或初始化块）、内部类。

如果成员变量想要初始化的值不是一个硬编码的常量值，而是需要通过复杂的计算或读取文件、或读取运行环境信息等方式才能获取的一些值，该怎么办呢？此时，可以考虑代码块（或初始化块）。

代码块（或初始化块）的作用：
* 对 Java 类或对象进行初始化。
  * 即：用来初始化类或对象的信息（亦即：初始化类或对象的成员变量）。

代码块（或初始化块）的分类：
* 一个类中代码块若有修饰符，则只能被 `static` 修饰，称为静态代码块（static block）。
* 没有使用 `static` 修饰的，为非静态代码块。

### 4.1 静态代码块

如果想要为静态变量初始化，可以直接在静态变量的声明后面直接赋值，也可以使用静态代码块。

#### 4.1.1 语法格式

在代码块的前面加 `static`，就是静态代码块。

语法格式：
```java
[修饰符] class 类名 {
    static {
        静态代码块
    }
}
```

#### 4.1.2 静态代码块的特点

静态代码块具有以下特点：
1. 可以有输出语句。
2. 可以对类的属性、类的声明进行初始化操作。
3. 不可以对非静态的属性初始化；即：不可以调用非静态的属性和方法。
4. 若有多个静态的代码块，那么按照从上到下的顺序依次执行。
5. 静态代码块的执行要先于非静态代码块。
6. 静态代码块随着类的加载而加载，且只执行一次。

> 静态代码块的具体使用说明：
> * 随着类的加载而执行（或调用）。
> * 由于类的加载只会执行一次，进而静态代码块的执行也只会执行一次。
> * 作用：用来初始化类的信息。
> * 内部可以声明变量、调用属性或方法、编写输出语句等操作。
> * 静态代码块的执行要先于非静态代码块的执行。
> * 如果声明有多个静态代码块，则按照声明的先后顺序执行。
> * 静态代码块内部只能调用静态的结构（即静态的属性、方法），不能调用非静态的结构（即非静态的属性、方法）。

示例代码：
```java

```

### 4.2 非静态代码块

#### 4.2.1 语法格式

非静态代码块的语法格式如下所示：
```java
[修饰符] class 类名 {
    {
        非静态代码块
    }
    [修饰符] 构造器名() {
        // 实例初始化代码
    }
    [修饰符] 构造器名(参数列表) {
        // 实例初始化代码
    }
}
```

#### 4.2.2 非静态代码块的作用

和构造器一样，也是用于实例变量的初始化等操作。

#### 4.2.3 非静态代码块的意义

如果多个重载的构造器有公共代码，并且这些代码都是先于构造器其他代码执行的，那么可以将这部分代码抽取到非静态代码块中，减少冗余代码。

#### 4.2.4 非静态代码块的执行特点

非静态代码块具有以下执行特点：
1. 可以有输出语句。
2. 可以对类的属性、类的声明进行初始化操作。
3. 除了调用非静态的结构外，还可以调用静态的变量或方法。
4. 若有多个非静态的代码块，那么按照从上到下的顺序依次执行。
5. 每次创建对象的时候，都会执行一次；且先于构造器执行。

> 非静态代码块的具体使用说明：
> * 随着对象的创建而执行。
> * 每创建当前类的一个实例，就会执行一次非静态代码块。
> * 作用：用来初始化对象的信息。
> * 内部可以声明变量、调用属性或方法、编写输出语句等操作。
> * 如果声明有多个非静态代码块，则按照声明的先后顺序执行。
> * 非静态代码块内部可以调用静态的结构（即静态的属性、方法），也可以调用非静态的结构（即非静态的属性、方法）。

### 4.3 举例

示例代码：
```java
/* BlockTest.java */

package com.anxin_hitsz_04.block;

/**
 * ClassName: BlockTest
 * Package: com.anxin_hitsz_04.block
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 19:34
 * @Version 1.0
 */
public class BlockTest {
    public static void main(String[] args) {
        System.out.println(Person.info);
        System.out.println(Person.info);


        Person p1 = new Person();
        Person p2 = new Person();
        System.out.println(p1.age);
//        p1.eat();
    }
}

class Person {

    String name;
    int age;

    static String info = "我是一个人";

    public void eat() {
        System.out.println("人吃饭");
    }

    public Person() {}

    // 非静态代码块
    {
        System.out.println("非静态代码块 2");
    }
    {
        System.out.println("非静态代码块 1");
        age = 1;
        System.out.println("info = " + info);
    }

    // 静态代码块
    static {
        System.out.println("静态代码块 2");
    }
    static {
        System.out.println("静态代码块 1");
        System.out.println("info = " + info);
//        System.out.println("age = " + age);
//        eat();
    }

}

```

以上示例中代码块的作用可以被构造器代替。而下述情况中，代码块是必须的：
```java
private static DataSource dataSource = null;

static {
    InputStream is = null;
    try {
        is = DBCPTest.class.getClassLoader().getResourceAsStream("dbcp.properties");
        Properties pros = new Properties();
        pros.load(is);
        // 调用 BasicDataSourceFactory 的静态方法，获取数据源
        dataSource = BasicDataSourceFactory.createDataSource(pros);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 4.4 小结：实例变量赋值顺序

实例变量赋值顺序如下：
![实例变量赋值顺序示意图](./images/image-20220325230208941.png "实例变量赋值顺序示意图")

### 4.5 练习

练习：
> 题目：
>
> 1. 声明 `User` 类：
> * 包含属性：`userName`（`String` 类型）、`password`（`String` 类型）、`registrationTime`（`long` 类型），且要求私有化。
> * 包含 `get` / `set` 方法，其中 `registrationTime` 没有 `set` 方法。
> * 包含无参构造：
>   * 输出“`新用户注册`”；
>   * `registrationTime` 赋值为当前系统时间；
>   * `userName` 默认为当前系统时间值；
>   * `password` 默认为“`123456`”。
> * 包含有参构造（即 `(String userName, String password)`）：
>   * 输出“`新用户注册`”；
>   * `registrationTime` 赋值为当前系统时间；
>   * `userName` 和 `password` 由参数赋值。
> * 包含 `public String getInfo()` 方法，返回：“`用户名：XX，密码：XX，注册时间：XX`”。
>
> 2. 编写测试类，测试类 `main()` 方法的代码。

示例代码：
```java
/* User.java */

package com.anxin_hitsz_04.block.exer;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_04.block.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 19:53
 * @Version 1.0
 */
public class User {
    private String userName;
    private String password;
    private long registrationTime;  // 注册时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public User() {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
        userName = System.currentTimeMillis() + "";
        password = "123456";
    }

    public User(String userName, String password) {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
        this.userName = userName;
        this.password = password;
    }

    public String getInfo() {
        return "用户名：" + userName + "，密码：" + password + "，注册时间：" + registrationTime;
    }
}


/* User1.java */

package com.anxin_hitsz_04.block.exer;

/**
 * ClassName: User1
 * Package: com.anxin_hitsz_04.block.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 19:59
 * @Version 1.0
 */
public class User1 {
    private String userName;
    private String password;
    private long registrationTime;  // 注册时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    // 代码块的使用
    {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
    }

    public User1() {
        userName = System.currentTimeMillis() + "";
        password = "123456";
    }

    public User1(String userName, String password) {
//        System.out.println("新用户注册");
//        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
        this.userName = userName;
        this.password = password;
    }

    public String getInfo() {
        return "用户名：" + userName + "，密码：" + password + "，注册时间：" + registrationTime;
    }
}


/* UserTest.java */

package com.anxin_hitsz_04.block.exer;

/**
 * ClassName: UserTest
 * Package: com.anxin_hitsz_04.block.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 19:58
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {
        User u1 = new User();
        System.out.println(u1.getInfo());

        User u2 = new User("Tom", "654321");
        System.out.println(u2.getInfo());

        System.out.println();
        User1 u3 = new User1();
        System.out.println(u3.getInfo());

    }
}

```

### 4.6 小结：类中属性赋值的位置及过程

可以给类的非静态的属性（即实例变量）赋值的位置有：
① 默认初始化；
② 显式初始化； 或 ⑤ 代码块中初始化；
③ 构造器中初始化；
④ 有了对象以后，通过 “`对象.属性`” 或 “`对象.方法`” 的方法进行赋值。

执行的先后顺序：① -> ②/⑤ -> ③ -> ④。

关于字节码文件中的 `<init>` 的简单说明（通过插件 jclasslib bytecode viewer 查看）：
* `<init>` 方法在字节码文件中可以看到；每个 `<init>` 方法都对应着一个类的构造器。（类中声明了几个构造器就会有几个 `<init>`。）
* 编写的代码中的构造器在编译以后就会以 `<init>` 方法的方式呈现。
* `<init>` 方法内部的代码包含了实例变量的显式赋值、代码块中的赋值和构造器中的代码。
* `<init>` 方法是用来初始化当前创建的对象的信息的。

给实例变量赋值的位置很多，开发中如何选择？
* 显式赋值：比较适合于每个对象的属性值相同的场景。
* 构造器中赋值：比较适合于每个对象的属性值不相同的场景。

示例代码：
```java
/* FieldTest.java */

package com.anxin_hitsz_05.field;

/**
 * ClassName: FieldTest
 * Package: com.anxin_hitsz_05.field
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 20:42
 * @Version 1.0
 */
public class FieldTest {
    public static void main(String[] args) {
        Order o1 = new Order();
        System.out.println(o1.orderId); // 1
    }
}

class Order {

    int orderId = 1;

    {
        orderId = 2;
    }


    public Order() {
        super();
//        orderId = 3;
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void eat() {
        sleep();
    }

    public void sleep() {

    }
}

```

技巧：**由父及子，静态先行**。示例代码如下：
```java
/* LeafTest.java */

package com.anxin_hitsz_05.field.exer;

//技巧：由父及子，静态先行。

class Root{
	static{
		System.out.println("Root的静态初始化块");
	}
	{
		System.out.println("Root的普通初始化块");
	}
	public Root(){
		super();
		System.out.println("Root的无参数的构造器");
	}
}
class Mid extends Root{
	static{
		System.out.println("Mid的静态初始化块");
	}
	{
		System.out.println("Mid的普通初始化块");
	}
	public Mid(){
		System.out.println("Mid的无参数的构造器");
	}
	public Mid(String msg){
		//通过this调用同一类中重载的构造器
		this();
		System.out.println("Mid的带参数构造器，其参数值："
			+ msg);
	}
}
class Leaf extends Mid{
	static{
		System.out.println("Leaf的静态初始化块");
	}
	{
		System.out.println("Leaf的普通初始化块");
	}	
	public Leaf(){
		//通过super调用父类中有一个字符串参数的构造器
		super("尚硅谷");
		System.out.println("Leaf的构造器");
	}
}
public class LeafTest{
	public static void main(String[] args){
		new Leaf(); 
//		System.out.println();
//		new Leaf();
	}
}

```
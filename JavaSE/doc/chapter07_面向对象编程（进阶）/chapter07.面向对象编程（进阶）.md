# 第七章：面向对象编程（进阶）

**目录：**

[TOC]

---

本章专题与脉络：
![第 2 阶段：面向对象编程 - 第 07 章](./images/第2阶段：Java面向对象编程-第07章.png "第 2 阶段：面向对象编程 - 第 07 章")

## 一、关键字：`this`

### 1.1 `this` 是什么？

在 Java 中，`this` 关键字不算难理解，它的作用和其词义很接近。
* 它在方法（准确地说是实例方法或非 `static` 的方法）内部使用，表示调用该方法的对象。
* 它在构造器内部使用，表示该构造器正在初始化的对象。

具体而言，`this` 调用属性和方法：
* 针对方法内的使用情况（准确地说是非 `static` 修饰的方法）：
  * 一般情况：我们通过对象 `a` 调用方法，可以在方法内调用当前对象 `a` 的属性或其他方法；此时，我们可以在属性和其他方法前使用 “`this`”，表示当前属性或方法所属的对象 `a`。但是，一般情况下，我们都选择省略 “`this.`” 结构。
  * 特殊情况：如果方法的形参与对象的属性同名了，我们必须使用 “`this.`” 进行区分；使用 “`this.`” 修饰的变量即为属性（或成员变量），没有使用 “`this.`” 修饰的变量，即为局部变量。
* 针对构造器内的使用情况：
  * 一般情况：我们通过构造器创建对象时，可以在构造器内调用当前正在创建的对象的属性或方法；此时，我们可以在属性和方法前使用 “`this`”，表示当前属性或方法所属的对象。但是，一般情况下，我们都选择省略 “`this.`” 结构。
  * 特殊情况：如果构造器的形参与正在创建的对象的属性同名了，我们必须使用 “`this.`” 进行区分；使用 “`this.`” 修饰的变量即为属性（或成员变量），没有使用 “`this.`” 修饰的变量，即为局部变量。

### 1.2 什么时候使用 `this`？

#### 1.2.1 实例方法或构造器中使用当前对象的成员

在实例方法或构造器中，如果使用当前类的成员变量或成员方法可以在其前面添加 `this`，增强程序的可读性。不过，通常我们都习惯省略 `this`。

但是，当形参与成员变量同名时，如果在方法内或构造器内需要使用成员变量，必须添加 `this` 来表明该变量是类的成员变量。即：我们可以用 `this` 来区分成员变量和局部变量。比如：
![使用 this 关键字来区分成员变量和局部变量](./images/image-20220503102947013.png "使用 this 关键字来区分成员变量和局部变量")

具体来讲，使用 `this` 修饰的变量，表示的是属性；没有用 `this` 修饰的，表示的是形参。

另外，使用 `this` 访问属性和方法时，如果在本类中未找到，会从父类中查找。这个在继承中会讲到。

> 注意：
>
> **就近原则**：当形参与成员变量同名时，如果在方法内或构造器内使用该变量名，则根据“**就近原则**”，该变量名所指的是**形参**。

示例代码：
```java
/* PersonTest.java */

package com.anxin_hitsz_01._this;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_01._this
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 15:06
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(10);
        System.out.println(p1.age);

        Person p2 = new Person("Tom", "tom@126.com");
        System.out.println("name = " + p2.name + ", email = " + p2.email);
    }
}

class Person {
    String name;
    int age;

    String email;

    public Person() {

    }

    public Person(String n) {
        name = n;
        eat();
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return this.age;
    }

    public void eat() {
        System.out.println("人吃饭");

        this.sleep();
    }

    public void sleep() {
        System.out.println("人睡觉");
    }
}

```

> 注意：
> * `this` 可以调用的结构：成员变量、方法、构造器。
> * `this` 的理解：当前对象（在方法中调用时）或当前正在创建的对象（在构造器中调用时）。

#### 1.2.2 同一个类中构造器互相调用

`this` 可以作为一个类中构造器相互调用的特殊格式；即：我们可以在类的构造器中，调用当前类中指定的其它构造器。
* `this()`：调用本类的无参构造器。
* `this(实参列表)`：调用本类的有参构造器。

示例代码：
```java
/* UserTest.java */

package com.anxin_hitsz_01._this;

/**
 * ClassName: UserTest
 * Package: com.anxin_hitsz_01._this
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 15:30
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {

        // 只创建了 User 类的 1 个对象
        User u1 = new User("Tom", 12);
    }
}

class User {
    String name;
    int age;

    public User() {
        // 模拟对象创建时，需要初始化 50 行代码
    }
    public User(String name) {
        this();
        this.name = name;
    }
    public User(String name, int age) {
        this(name);
//        this.name = name;
        this.age = age;
    }

//    private void init() {
//        // 模拟对象创建时，需要初始化 50 行代码
//    }

}

```

需要注意的是，不论同一个类中构造器的互相调用关系是怎样的，在搭配 `new` 关键字创建对象时只会创建 1 个对象。例如：
```java
public class UserTest {
    public static void main(String[] args) {

        // 只创建了 User 类的 1 个对象
        User u1 = new User("Tom", 12);
    }
}

```

> 注意：
> * 不能出现递归调用；比如，调用自身构造器。
>   * 推论：如果一个类中声明了 n 个构造器，则最多有 n - 1 个构造器中使用了 “`this(形参列表)`”；即其调用关系不成环即可。
> * `this()` 和 `this(形参列表)` 只能声明在**构造器首行**。
>   * 推论：在类的一个构造器中，最多只能声明一个 “`this(参数列表)`”。

### 1.3 练习

> 注意：
> * 使用 `alt + insert` 组合键可以快速生成 `Constructor`、`Getter` 和 `Setter` 方法。
> * 使用 `Ctrl + F12` 组合键可以列出当前 `.java` 文件中所实现的类的所有成员方法和成员属性。

练习 1：
> 题目：
>
> 根据图示，添加必要的构造器，综合应用构造器的重载、`this` 关键字。
> ![练习 1 图示](./images/this_exer1.png "练习 1 图示")

示例代码：
```java
/* Boy.java */

package com.anxin_hitsz_01._this.exer1;

/**
 * ClassName: Boy
 * Package: com.anxin_hitsz_01._this.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 16:21
 * @Version 1.0
 */
public class Boy {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boy() {
    }

    public Boy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void marry(Girl girl) {
        System.out.println("我想娶 " + girl.getName());
    }

    public void shout() {
        if (this.age >= 22) {
            System.out.println("我终于可以结婚了！");
        } else {
            System.out.println("我只能多谈恋爱了");
        }
    }

}


/* Girl.java */

package com.anxin_hitsz_01._this.exer1;

/**
 * ClassName: Girl
 * Package: com.anxin_hitsz_01._this.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 16:25
 * @Version 1.0
 */
public class Girl {
    private String name;
    private int age;

    public Girl() {
    }

    public Girl(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void marry(Boy boy) {
        System.out.println("我想嫁给 " + boy.getName());

        boy.marry(this);
    }

    /**
     * 比较两个 Girl 对象的大小
     * @param girl
     * @return 正数：当前对象大；负数：当前对象小（或形参 girl 大）；0：相等
     */
    public int compare(Girl girl) {
        if (this.age > girl.age) {
            return 1;
        } else if (this.age < girl.age) {
            return -1;
        } else {
            return 0;
        }

    }

}


/* BoyGirlTest.java */

package com.anxin_hitsz_01._this.exer1;

/**
 * ClassName: BoyGirlTest
 * Package: com.anxin_hitsz_01._this.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 16:33
 * @Version 1.0
 */
public class BoyGirlTest {
    public static void main(String[] args) {

        Boy boy1 = new Boy("杰克", 24);
        Girl girl1 = new Girl("朱丽叶", 20);

        girl1.marry(boy1);

        boy1.shout();

        Girl girl2 = new Girl("露丝", 18);
        int compare = girl1.compare(girl2);
        if (compare > 0) {
            System.out.println(girl1.getName() + " 大");
        } else if (compare < 0) {
            System.out.println(girl2.getName() + " 大");
        } else {
            System.out.println("一样大");
        }
    }
}

```

练习 2：
> 题目：
>
> 1. 按照 UML 类图，创建 `Account` 类，提供必要的结构。
> ![UML - Account 类](./images/this_exer2_UML_Account.png "UML - Account 类")
>   * 在提款方法 `withdraw()` 中，需要判断用户余额是否能够满足提款数额的要求；如果不能，应给出提示。
>   * `deposit()` 方法表示存款。
> 2. 按照 UML 类图，创建 `Customer` 类，提供必要的结构。
> ![UML - Customer 类](./images/this_exer2_UML_Customer.png "UML - Customer 类")
> 3. 按照 UML 类图，创建 `Bank` 类，提供必要的结构。
> ![UML - Bank 类](./images/this_exer2_UML_Bank.png "UML - Bank 类")
>   * `addCustomer` 方法必须依照参数（`姓, 名`）构造一个新的 `Customer` 对象，然后把它放到 `customer` 数组中；还必须把 `numberOfCustomer` 属性的值加 1。
>   * `getNumOfCustomers` 方法返回 `numberOfCustomer` 属性值。
>   * `getCustomer` 方法返回与给出的 `index` 参数相关的客户。
> 4. 创建 `BankTest` 类，进行测试。

示例代码：
```java
/* Account.java */

package com.anxin_hitsz_01._this.exer2;

/**
 * ClassName: Account
 * Package: com.anxin_hitsz_01._this.exer2
 * Description:
 *      账户类
 * @Author AnXin
 * @Create 2026/1/23 16:39
 * @Version 1.0
 */
public class Account {
    private double balance; // 余额

    public Account(double init_balance) {
        this.balance = init_balance;
    }

    public double getBalance() {
        return balance;
    }

    // 存钱
    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            System.out.println("成功存入：" + amt);
        }
    }

    // 取钱
    public void withdraw(double amt) {
        if (balance >= amt && amt > 0) {
            balance -= amt;
            System.out.println("成功取出：" + amt);
        } else {
            System.out.println("取款数额有误或余额不足");
        }
    }
}


/* Customer.java */

package com.anxin_hitsz_01._this.exer2;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_01._this.exer2
 * Description:
 *      客户类
 * @Author AnXin
 * @Create 2026/1/23 16:42
 * @Version 1.0
 */
public class Customer {
    private String firstName;   // 名
    private String lastName;    // 姓

    private Account account;    // 账户

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}


/* Bank.java */

package com.anxin_hitsz_01._this.exer2;

/**
 * ClassName: Bank
 * Package: com.anxin_hitsz_01._this.exer2
 * Description:
 *      银行类
 * @Author AnXin
 * @Create 2026/1/23 16:49
 * @Version 1.0
 */
public class Bank {
    private Customer[] customers;   // 用于保存多个客户
    private int numberOfCustomer;   // 用户记录存储的客户的个数

    public Bank() {
        customers = new Customer[10];
    }

    /**
     * 将指定姓名的客户保存在银行的客户列表中
     * @param f
     * @param l
     */
    public void addCustomer(String f, String l) {
        Customer cust = new Customer(f, l);
//        customers[numberOfCustomer] = cust;
//        numberOfCustomer++;
        // 或：
        customers[numberOfCustomer++] = cust;
    }

    /**
     * 获取客户列表中存储的客户的个数
     * @return
     */
    public int getNumOfCustomers() {
        return numberOfCustomer;
    }

    /**
     * 获取指定索引位置上的客户
     * @param index
     * @return
     */
    public Customer getCustomer(int index) {
        if (index < 0 || index >= numberOfCustomer) {
            return null;
        }

        return customers[index];

    }
}


/* BankTest.java */

package com.anxin_hitsz_01._this.exer2;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_01._this.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 16:52
 * @Version 1.0
 */
public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addCustomer("三", "张");
        bank.addCustomer("四", "李");

        bank.getCustomer(0).setAccount(new Account(1000));
        bank.getCustomer(0).getAccount().withdraw(50);

        System.out.println("账户余额为：" + bank.getCustomer(0).getAccount().getBalance());
    }
}

```

内存解析：
![练习 2 - 内存解析](./images/this_exer2_内存解析.png "练习 2 - 内存解析")

## 二、面向对象特征二：继承（Inheritance）

### 2.1 继承的概述

> 继承有延续（下一代延续上一代的基因、财富）、扩展（下一代和上一代又有所不同）的意思。

#### 2.1.1 Java 中的继承

**角度一：从上而下**

为描述和处理**个人**信息，定义类 `Person`：
![定义类 `Person`](./images/image-20220323220923386.png "定义类 `Person`")

为描述和处理**学生**信息，定义类 `Student`：
![定义类 `Student`](./images/image-20220323221001495.png "定义类 `Student`")

发现类 `Student` 的功能与类 `Person` 相似，考虑类 `Student` 继承于类 `Person`。

通过继承，简化 `Student` 类的定义：
![通过继承简化 `Student` 类的定义](./images/image-20220323221050791.png "通过继承简化 `Student` 类的定义")

> 说明：`Student` 类继承了父类 `Person` 的所有属性和方法，并增加了一个属性 `school`；`Person` 中的属性和方法，`Student` 都可以使用。

**角度二：从下而上**

![猫狗继承 1](./images/猫狗继承1.jpg "猫狗继承 1")

多个类中存在相同属性和行为时，将这些内容抽取到单独一个类中，那么多个类中无需再定义这些属性和行为，只需要和抽取出来的类构成**继承关系**。如图所示：
![猫狗继承 2](./images/猫狗继承2.jpg "猫狗继承 2")

再举例：
![`Person` 类及其子类](./images/image-20220323221436571.png "`Person` 类及其子类")

#### 2.1.2 继承的好处

继承的出现减少了代码冗余，提高了代码的复用性。

继承的出现，更有利于功能的扩展。

继承的出现让类与类之间产生了 `is - a` 的关系，为多态的使用提供了前提。
* 继承描述事物之间的所属关系，这种关系是 `is - a` 的关系；可见，**父类更通用、更一般，子类更具体**。

> 注意：
> 
> 不要仅为了获取其他类中某个功能去继承！
> 
> 在继承之前，判断以下是否有 `is - a` 的关系。

### 2.2 继承的语法

#### 2.2.1 继承中的语法格式

通过 `extends` 关键字，可以声明一个类 `B` 继承另外一个类 `A`。定义格式如下：
```java
[修饰符] class 类A {
    // 属性、方法
    ...
}

[修饰符] class 类B extends 类A {
    ...
}
```

#### 2.2.2 继承中的基本概念

类 `B`，称为子类、派生类（derived class）、SubClass。

类 `A`，称为父类、超类、基类（base class）、SuperClass。

### 2.3 代码举例

**1. 父类**

```java
/* Person.java */

package com.anxin_hitsz_03._extends;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:52
 * @Version 1.0
 */
public class Person {
    // 属性
    String name;
    private int age;

    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }
    public void sleep() {
        System.out.println("人睡觉");
    }

    public void show() {
        System.out.println("age：" + age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

```

**2. 子类**

```java
/* Student.java */

package com.anxin_hitsz_03._extends;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:57
 * @Version 1.0
 */
public class Student extends Person {
    // 属性
//    String name;
//    int age;

    String school;

    // 方法
//    public void eat() {
//        System.out.println("人吃饭");
//    }
//    public void sleep() {
//        System.out.println("人睡觉");
//    }

    public void study() {
        System.out.println("学生学习");
    }

    public void show1() {
//        System.out.println("age：" + age);
        System.out.println("age：" + getAge());
    }
}

```

**3. 测试类**

```java
/* ExtendsTest.java */

package com.anxin_hitsz_03._extends;

/**
 * ClassName: ExtendsTest
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:58
 * @Version 1.0
 */
public class ExtendsTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "Tony";
//        p1.age = 12;
        p1.eat();

        System.out.println(p1.toString());

        Student s1 = new Student();
        s1.name = "Tom";
//        s1.age = 12;
        s1.eat();

        // 超纲：获取 s1 所属类的父类
        System.out.println(s1.getClass().getSuperclass());  // Person
        // 超纲：获取 p1 所属类的父类
        System.out.println(p1.getClass().getSuperclass());  // java.lang.Object

    }
}

```

### 2.4 继承性的细节说明

**1. 子类会继承父类所有的实例变量和实例方法。**

从类的定义来看，类是一类具有相同特性的事物的抽象描述；父类是所有子类共同特征的抽象描述。而实例变量和实例方法就是事物的特征，那么父类中声明的实例变量和实例方法代表子类事物也有这个特征。
* 当子类对象被创建时，在堆中给对象申请内存时，就要看子类和父类都声明了什么实例变量，这些实例变量都要分配内存。
* 当子类对象调用方法时，编译器会先在子类模板中看该类是否有这个方法；如果没找到，会看它的父类甚至父类的父类是否声明了这个方法，遵循**从下往上**找的顺序，找到了就停止，一直到根父类都没有找到，就会报编译错误。

所以继承意味着子类的对象除了看子类的类模板还要看父类的类模板。

![子类会继承父类所有的实例变量和实例方法](./images/image-20211230090255997.png "子类会继承父类所有的实例变量和实例方法")

**2. 子类不能直接访问父类中私有（`private`）的成员变量和方法。**

> 由于封装性的影响，可能子类不能直接调用父类中声明的属性或方法。

子类虽会继承父类私有（`private`）的成员变量，但子类不能对继承的私有成员变量直接进行访问，可通过继承的 `get` / `set` 方法进行访问。如图所示：
![子类对继承的私有成员变量的访问情况](./images/image-20220323224757212.png "子类对继承的私有成员变量的访问情况")

**3. 在 Java 中，继承的关键字用的是 “`extends`”，即子类不是父类的子集，而是对父类的 “扩展”。**

> extends：延展、扩展、延伸。

子类在继承父类以后，还可以扩展自己特有的功能（体现：增加特有的属性、方法），这就可以看做是对父类功能上的扩展。

子类和父类的理解，要区别于集合和子集。

**4. Java 支持多层继承（继承体系）。**

![多层继承（继承体系）](./images/image-20220323225441417.png "多层继承（继承体系）")

示例代码：
```java
class A {}
class B extends A {}
class C extends B {}
```

其中，类 A 是类 B 的**直接父类**，而类 A 是类 C 的**间接父类**。

> 说明：
> * 子类和父类是一种相对的概念。
> * 顶层父类是 `Object` 类；所有的类默认继承 `Object` 作为其父类。
>   * Java 中声明的类，如果没有显式地声明其父类时，则默认继承于 `java.lang.Object`。

**5. 一个父类可以同时拥有多个子类。**

示例代码：
```java
class A {}
class B extends A {}
class D extends A {}
class E extends A {}
```

**6. Java 只支持单继承，不支持多重继承。**

![Java 不支持多重继承](./images/image-20220514162507692.png "Java 不支持多重继承")

Java 中一个父类可以声明多个子类；反之，一个子类只能有一个父类（Java 的单继承性）。

> 注意：Java 的**单继承性是针对类而言的**，而 Java 中的**接口是支持多重继承的**。

示例代码：
```java
public class A {}
class B extends A {}

// 一个类只能有一个父类，不可以有多个直接父类
class C extends B {}    // OK
class C extends A, B ...    // error
```

### 2.5 练习

**练习 1：**
> 题目：
>
> 1. 定义一个 `ManKind` 类，包括：
>   * 成员变量 `int sex` 和 `int salary`；
>   * 方法 `void manOrWoman()`：根据 `sex` 的值显示“`man`”（`sex == 1`）或者“`woman`”（`sex == 0`）；
>   * 方法 `void employeed()`：根据 `salary` 的值显示“`no job`”（`salary == 0`）或者“`job`”（`salary != 0`）。
> 2. 定义类 `Kids` 继承 `ManKind`，并包括：
>   * 成员变量 `int yearsOld`；
>   * 方法 `printAge()`：打印 `yearsOld` 的值。
> 3. 定义类 `KidsTest`，在类的 `main` 方法中实例化 `Kids` 的对象 `someKid`，用该对象访问其父类的成员变量及方法。

示例代码：
```java
/* ManKind.java */

package com.anxin_hitsz_03._extends.exer1;

/**
 * ClassName: ManKind
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 15:56
 * @Version 1.0
 */
public class ManKind {
    private int sex;
    private int salary;

    public ManKind() {
    }

    public ManKind(int sex, int salary) {
        this.sex = sex;
        this.salary = salary;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void manOrWoman() {
        if (sex == 1) {
            System.out.println("Man");
        } else if (sex == 0) {
            System.out.println("Woman");
        }
    }

    public void employeed() {
        if (salary == 0) {
            System.out.println("no job!");
        } else {
            System.out.println("job!");
        }
    }
}


/* Kids.java */

package com.anxin_hitsz_03._extends.exer1;

/**
 * ClassName: Kids
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 15:59
 * @Version 1.0
 */
public class Kids extends ManKind {
    private int yearsOld;

    public Kids() {

    }

    public Kids(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public Kids(int sex, int salary, int yearsOld) {
        this.yearsOld = yearsOld;
        setSex(sex);
        setSalary(salary);
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void printAge() {
        System.out.println("I am " + yearsOld + " years old.");
    }
}

/* KidsTest.java */

package com.anxin_hitsz_03._extends.exer1;

/**
 * ClassName: KidsTest
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:02
 * @Version 1.0
 */
public class KidsTest {
    public static void main(String[] args) {

        Kids kid = new Kids();

        kid.setSex(1);
        kid.setSalary(100);
        kid.setYearsOld(12);

        // 来自于父类中声明的方法
        kid.employeed();
        kid.manOrWoman();

        // Kids 类自己声明的方法
        kid.printAge();

    }
}

```

**练习 2：**
> 题目：
>
> 根据下图实现相关的类：
> ![继承 - 练习 2](./images/Circle-Cylinder.png "继承 - 练习 2")
>
> 在 CylinderTest 类中创建 Cylinder 类的对象，设置圆柱的底面半径和高，并输出圆柱的体积。

示例代码：
```java
/* Circle.java */

package com.anxin_hitsz_03._extends.exer2;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:06
 * @Version 1.0
 */
public class Circle {
    private double radius;  // 半径

    public Circle() {
        this.radius = 1;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // 求圆的面积
    public double findArea() {
        return 3.14 * radius * radius;
    }
}


/* Cylinder.java */

package com.anxin_hitsz_03._extends.exer2;

/**
 * ClassName: Cylinder
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *      圆柱类
 * @Author AnXin
 * @Create 2026/1/26 16:07
 * @Version 1.0
 */
public class Cylinder extends Circle {

    private double length;  // 高

    public Cylinder() {
        length = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // 求圆柱的体积
    public double findVolume() {
//        return 3.14 * getRadius() * getRadius() * getLength();

        return findArea() * getLength();
    }
}


/* CylinderTest.java */

package com.anxin_hitsz_03._extends.exer2;

/**
 * ClassName: CylinderTest
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:10
 * @Version 1.0
 */
public class CylinderTest {
    public static void main(String[] args) {

        Cylinder cy =  new Cylinder();

        cy.setRadius(2.3);
        cy.setLength(1.4);

        System.out.println("圆柱的体积为：" + cy.findVolume());

        System.out.println("圆柱的底面积：" + cy.findArea());

    }
}

```

### 2.6 测试 4 种权限修饰

在 com.anxin_hitsz_04.override 包下创建两个 package：test1 和 test2。

测试 Java 中提供的 4 中权限修饰符：
```java
/* com.anxin_hitsz_04.override.test1.Order.java */

package com.anxin_hitsz_04.override.test1;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_04.override.test1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:15
 * @Version 1.0
 */
public class Order {
    private int orderPrivate;
    int orderDefault;
    protected int orderProtected;
    public int orderPublic;

    private void methodProivate() {}
    void methodDefault() {}
    protected void methodProtected() {}
    public void methodPublic() {}

    // 类内部
    public void show() {
        orderPrivate = 1;
        orderDefault = 1;
        orderProtected = 1;
        orderPublic = 1;

        methodProivate();
        methodDefault();
        methodProtected();
        methodPublic();
    }
}


/* com.anxin_hitsz_04.override.test1.OrderTest.java */

package com.anxin_hitsz_04.override.test1;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_04.override.test1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:18
 * @Version 1.0
 */
public class OrderTest {

    public void method1() {

        Order order = new Order();

        // 通过对象调用 Order 类中声明的属性、方法
        order.orderDefault = 1;
        order.orderProtected = 1;
        order.orderPublic = 1;

        order.methodDefault();
        order.methodProtected();
        order.methodPublic();


        // 受封装性的影响，不能调用
//        order.orderPrivate = 1;
//        order.methodPrivate();

    }
}


/* com.anxin_hitsz_04.override.test2.SubOrder.java */

package com.anxin_hitsz_04.override.test2;

import com.anxin_hitsz_04.override.test1.Order;

/**
 * ClassName: SubOrder
 * Package: com.anxin_hitsz_04.override.test2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:21
 * @Version 1.0
 */
public class SubOrder extends Order {

    public void method() {

        orderProtected = 1;
        orderPublic = 1;

        methodProtected();
        methodPublic();



        // 不能访问
//        orderPrivate = 1;
//        orderDefault = 1;

//        methodPrivate();
//        methodDefault();

    }

}


/* com.anxin_hitsz_04.override.test2.OrderTest.java */

package com.anxin_hitsz_04.override.test2;

import com.anxin_hitsz_04.override.test1.Order;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_04.override.test2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:24
 * @Version 1.0
 */
public class OrderTest {

    public void method() {

        Order order = new Order();

        order.orderPublic = 1;
        order.methodPublic();


        // 不能访问
//        order.orderPrivate = 1;
//        order.orderDefault = 1;
//        order.orderProtected = 1;
//
//        order.methodPrivate();
//        order.methodDefault();
//        order.methodProtected();

    }

}

```

实际开发中，各权限修饰的使用频率是怎样的？
* `public`、`private` 是使用频率最高的！

## 三、方法的重写（override / overwrite）

> 为什么需要方法的重写？
>
> 子类在继承父类以后，就获取了父类中声明的所有的方法。但是，父类中的方法可能不太适用于子类；换句话说，子类需要对父类中继承过来的方法进行覆盖、覆写的操作。

父类的所有方法子类都会继承，但是当某个方法被继承到子类之后，子类觉得父类原来的实现不适合于自己当前的类，该怎么办呢？子类可以对从父类中继承来的方法进行改造，我们称为方法的**重写（override / overwrite）**，也称为方法的**重置**、**覆盖**。

**即：子类对父类继承过来的方法进行的覆盖、覆写的操作，就称为方法的重写。**

在程序执行时，子类的方法将覆盖父类的方法。

### 3.1 方法重写举例

举例 1 - 新的手机增加来电显示姓名与头像的功能：
```java
/* Phone.java */

package com.atguigu.inherited.method;

public class Phone {
    public void sendMessage() {
        System.out.println("发短信");
    }
    public void call() {
        System.out.println("打电话");
    }
    public void showNum() {
        System.out.println("来电显示号码");
    }
}


/* SmartPhone.java */

package com.atguigu.inherited.method;

// SmartPhone：智能手机
public class SmartPhone extends Phone {
    // 重写父类的来电显示号码功能的方法
    @Override
    public void showNum() {
        // 来电显示姓名和图片功能
        System.out.println("显示来电姓名");
        System.out.println("显示头像");
    }
    // 重写父类的通话功能的方法
    @Override
    public void call() {
        System.out.println("语音通话 或 视频通话");
    }
}


/* TestOverride.java */

package com.atguigu.inherited.method;

public class TestOverride {
    public static void main(String[] args) {
        // 创建子类对象
        SmartPhone sp = new SmartPhone();

        // 调用父类继承而来的方法
        sp.call();

        // 调用子类重写的方法
        sp.showNum();
    }
}

```

> `@Override` 使用说明：
>
> 写在方法上面，用来检测是不是满足重写方法的要求。这个注解就算不写，只要满足要求，也是正确的方法覆盖重写。
> 建议保留，这样编译器可以帮助我们检查格式，另外也可以让阅读源代码的程序员清晰地知道这是一个重写的方法。

举例 2 - 银行账户：
```java
/* Account.java */

class Account { // 账户
    double balance; // 余额

    // 取钱
    public void withdraw(double amt) {
        // 判断 balance 余额是否够 amt 取钱的额度
    }
}


/* CheckAccount.java */

class CheckAccount extends Account {    // 信用卡
    double protectedBy; // 透支额度

    public void withdraw(double amt) {
        // 判断 balance 余额是否够 amt 取钱的额度
        // 如果不够，还可以考虑从 protectedBy 额度里取
    }
}


/* AccountTest.java */

class AccountTest {
    public static void main(String[] args) {
        CheckAccount acct = new CheckAccount();
        acct.withdraw();    // 执行的是子类重写父类的方法
    }
}

```

### 3.2 方法重写的要求

> 【复习】方法声明的格式：
```java
权限修饰符 返回值类型 方法名(形参列表) [throws 异常类型] {
    // 方法体
}
```

方法重写需要满足以下要求：
1. 子类重写的方法**必须**和父类被重写的方法具有相同的**方法名称**、**形参列表**。
2. 子类重写的方法的返回值类型**不能大于**父类被重写的方法的返回值类型。（例如：`Student` < `Person`。）

> 注意：
> 
> 如果返回值类型是基本数据类型和 `void`，那么子类重写的方法的返回值类型必须与父类被重写的方法的返回值类型相同。
> 
> 即关于返回值类型：
> 1. 父类被重写的方法的返回值类型是 `void`，则子类重写的方法的返回值类型必须是 `void`。
> 2. 父类被重写的方法的返回值类型是基本数据类型，则子类重写的方法的返回值类型必须与被重写的方法的返回值类型相同。
> 3. 父类被重写的方法的返回值类型是引用数据类型（比如 类），则子类重写的方法的返回值类型可以与被重写的方法的返回值类型相同，或是被重写的方法的返回值类型的子类。

3. 子类重写的方法使用的访问权限**不能小于**父类被重写的方法的访问权限。（`public` > `protected` > `缺省` > `private`。）

> 注意：
> 1. 父类私有方法（即声明为 `private` 权限修饰的方法）不能重写。
> 2. 跨包的父类缺省的方法也不能重写。

4. （超纲）子类方法抛出的异常不能大于父类被重写方法的异常；即子类重写的方法抛出的异常类型可以与父类被重写的方法抛出的异常类型相同，或是父类被重写的方法抛出的异常类型的子类。

此外，子类与父类中同名同参数的方法必须同时声明为非 `static` 的（即为重写），或者同时声明为 `static` 的（不是重写）。因为 `static` 方法是属于类的，子类无法覆盖父类的方法。

> 补充说明：重写对于方法体没有要求，但是子类重写的方法的方法体必然与父类被重写的方法体不同。

### 3.3 小结：方法的重载与重写

方法的重载：方法名相同，形参列表不同；不看返回值类型。

方法的重写：见上面。

> 面试题：区分方法的重载（overload）与重写（override / overwrite）。
> * 重载：“两同一不同”。
> * 重写：继承以后，子类覆盖父类中同名同参数的方法。
>
> 【类比】相同类型的面试题：
> * `throws` / `throw`；
> * `final` / `finally` / `finalize`；
> * `Collection` / `Collections`；
> * `String` / `StringBuffer` / `StringBuilder`；
> * `ArrayList` / `LinkedList`；
> * `HashMap` / `LinkedHashMap` / `Hashtable`；
> * ...
>
> 【类比】不同类型的面试题：
> * `sleep()` / `wait()`；
> * `==` / `equals()`；
> * 同步 / 异步；
> * ...

### 3.4 示例代码

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_04.override;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:52
 * @Version 1.0
 */
public class Person {
    // 属性
    String name;
    private int age;

    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }
    public void sleep() {
        System.out.println("人睡觉");
    }

    public void show() {
        System.out.println("age：" + age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int info() {
        return 1;
    }

    public Person info1() {
        return null;
    }
}


/* Student.java */

package com.anxin_hitsz_04.override;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:57
 * @Version 1.0
 */
public class Student extends Person {

    String school;


    public void study() {
        System.out.println("学生学习");
    }

    public void eat() {
        System.out.println("学生应该多吃有营养的食物");
    }

    public void show1() {
        System.out.println("age：" + getAge());
    }

    // 重写的针对于返回值的测试
    public int info() {
        return 1;
    }

    public Student info1() {
        return null;
    }

//    public void sleep() {
//        System.out.println("学生应该多睡，有利于大脑发育");
//    }


    @Override
    public void sleep() {
        System.out.println("学生应该多睡，有利于大脑发育");

    }
}


/* OverrideTest.java */

package com.anxin_hitsz_04.override;

/**
 * ClassName: OverrideTest
 * Package: com.anxin_hitsz_04.override
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 18:14
 * @Version 1.0
 */
public class OverrideTest {
    public static void main(String[] args) {

        Student s1 = new Student();

        s1.eat();
        s1.sleep();

    }
}

```

### 3.5 练习

练习 1：
> 题目：
>
> 1. 修改继承内容的练习 1 中定义的类 `Kids`，在 `Kids` 中重新定义 `employeed()` 方法。
> 2. 覆盖父类 `ManKind` 中定义的 `employeed()` 方法，输出“`Kids should study and no job.`”。

示例代码：
```java
/* ManKind.java */

package com.anxin_hitsz_04.override.exer1;

/**
 * ClassName: ManKind
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 15:56
 * @Version 1.0
 */
public class ManKind {
    private int sex;
    private int salary;

    public ManKind() {
    }

    public ManKind(int sex, int salary) {
        this.sex = sex;
        this.salary = salary;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void manOrWoman() {
        if (sex == 1) {
            System.out.println("Man");
        } else if (sex == 0) {
            System.out.println("Woman");
        }
    }

    public void employeed() {
        if (salary == 0) {
            System.out.println("no job!");
        } else {
            System.out.println("job!");
        }
    }
}


/* Kids.java */

package com.anxin_hitsz_04.override.exer1;

/**
 * ClassName: Kids
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 15:59
 * @Version 1.0
 */
public class Kids extends ManKind {
    private int yearsOld;

    public Kids() {

    }

    public Kids(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public Kids(int sex, int salary, int yearsOld) {
        this.yearsOld = yearsOld;
        setSex(sex);
        setSalary(salary);
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void printAge() {
        System.out.println("I am " + yearsOld + " years old.");
    }

    public void employeed() {
        System.out.println("Kids should study and no job.");
    }
}


/* KidsTest.java */

package com.anxin_hitsz_04.override.exer1;

/**
 * ClassName: KidsTest
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:02
 * @Version 1.0
 */
public class KidsTest {
    public static void main(String[] args) {

        Kids kid = new Kids();

        kid.setSex(1);
        kid.setSalary(100);
        kid.setYearsOld(12);

        // 来自于父类中声明的方法
        kid.employeed();
        kid.manOrWoman();

        // Kids 类自己声明的方法
        kid.printAge();

    }
}

```

练习 2：
> 题目：
>
> 修改继承内容的练习 2 中定义的类 `Cylinder`，在 `Cylinder` 中重写父类方法 `findArea()`，用于计算圆柱的表面积。

示例代码：
```java
/* Circle.java */

package com.anxin_hitsz_04.override.exer2;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:06
 * @Version 1.0
 */
public class Circle {
    private double radius;  // 半径

    public Circle() {
        this.radius = 1;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // 求圆的面积
    public double findArea() {
        return 3.14 * radius * radius;
    }
}


/* Cylinder.java */

package com.anxin_hitsz_04.override.exer2;

/**
 * ClassName: Cylinder
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *      圆柱类
 * @Author AnXin
 * @Create 2026/1/26 16:07
 * @Version 1.0
 */
public class Cylinder extends Circle {

    private double length;  // 高

    public Cylinder() {
        length = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // 求圆柱的体积
    public double findVolume() {
        return 3.14 * getRadius() * getRadius() * getLength();
        // 错误的
//        return findArea() * getLength();
    }

    // 求表面积
    public double findArea() {
        return 3.14 * getRadius() * getRadius() * 2 +
                2 * 3.14 * getRadius() * getLength();
    }
}


/* CylinderTest.java */

package com.anxin_hitsz_04.override.exer2;

/**
 * ClassName: CylinderTest
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:10
 * @Version 1.0
 */
public class CylinderTest {
    public static void main(String[] args) {

        Cylinder cy =  new Cylinder();

        cy.setRadius(2.3);
        cy.setLength(1.4);

        System.out.println("圆柱的体积为：" + cy.findVolume());

        System.out.println("圆柱的表面积：" + cy.findArea());

    }
}

```

## 四、再谈封装性中的 4 种权限修饰

权限修饰符：`public`、`protected`、`缺省`、`private`。

| 修饰符 | 本类 | 本包 | 其他包子类 | 其他包非子类 |
| :--: | :--: | :--: | :--: | :--: |
| `private` | √ | × | × | × |
| `缺省` | √ | √（本包子类、非子类都可见） | × | × |
| `protected` | √ | √（本包子类、非子类都可见） | √（其他包仅限于子类中可见） | × |
| `public` | √ | √ | √ | √ |

其中，权限修饰符的作用对象存在限制：
* 外部类：`public` 和 `缺省`。
* 成员变量、成员方法等：`public`、`protected`、`缺省`、`private`。

**1. 外部类要跨包使用必须是 `public`，否则仅限于本包使用。**
1. 外部类的权限修饰符如果缺省，本包使用没问题：
    ![外部类的权限修饰符缺省且在本包使用的情况](./images/image-20211230093627763.png "外部类的权限修饰符缺省且在本包使用的情况")
2. 外部类的权限修饰符如果缺省，跨包使用有问题：
    ![外部类的权限修饰符缺省且跨包使用的情况](./images/image-20211230094236974.png "外部类的权限修饰符缺省且跨包使用的情况")

**2. 成员的权限修饰符问题。**
1. 本包下使用：成员的权限修饰符可以是 public、protected、缺省。
    ![本包下使用的情况](./images/image-20211230095320646.png "本包下使用的情况")
2. 跨包下使用：要求严格。
    ![跨包下使用的情况](./images/image-20211230095817784.png "跨包下使用的情况")
3. 跨包使用时，如果类的权限修饰符缺省，即使 `成员权限修饰符 > 类的权限修饰符` 也没有意义。
    ![跨包使用且类的权限修饰符缺省的情况](./images/image-20211230100219840.png "跨包使用且类的权限修饰符缺省的情况")

## 五、关键字：`super`

### 5.1 `super` 的理解

在 Java 类中使用 `super` 来调用父类中的指定操作：
* `super` 可用于访问父类中定义的属性。
* `super` 可用于调用父类中定义的成员方法。
* `super` 可用于在子类构造器中调用父类的构造器。

> 注意：
> * 尤其当子父类出现同名成员时，可以用 `super` 表明调用的是父类中的成员。
> * `super` 的追溯不仅限于直接父类。
> * `super` 和 `this` 的用法相像：`this` 代表本类对象的引用，`super` 代表父类的内存空间的标识。

### 5.2 `super` 的使用场景

#### 5.2.1 子类中调用父类被重写的方法

在子类中调用父类被重写的方法，分以下两种情况讨论：
* 如果子类没有重写父类的方法，只要权限修饰符允许，在子类中完全可以直接调用父类的方法。
* 如果子类重写了父类的方法，在子类中需要通过 `super.` 才能调用父类被重写的方法，否则默认调用的是子类重写的方法。

示例代码：
```java

```

总结：
* 方法前面没有 `super.` 和 `this.`：
  * 先从子类找匹配方法，如果没有，再从直接父类找；再没有，继续往上追溯。
* 方法前面有 `this.`：
  * 先从子类找匹配方法，如果没有，再从直接父类找；再没有，继续往上追溯。
* 方法前面有 `super.`：
  * 从当前子类的直接父类找，如果没有，继续往上追溯。
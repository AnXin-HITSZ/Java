# 第七章：面向对象编程（进阶）

**目录：**

[TOC]

---

本章专题与脉络：
![第 2 阶段：Java 面向对象编程 - 第 07 章](./images/第2阶段：Java面向对象编程-第07章.png "第 2 阶段：Java 面向对象编程 - 第 07 章")

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
>
> 同理，在子父类的使用过程中，同样遵循**就近原则**。

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

为什么需要 `super`？

举例 1：子类继承父类以后，对父类的方法进行了重写；那么在子类中，是否还可以对父类中被重写的方法进行调用？
* 可以！

举例 2：子类继承父类以后，发现子类和父类中定义了同名的属性；是否可以在子类中区分两个同名的属性？
* 可以！

如何调用？使用 `super` 关键字即可。

### 5.1 `super` 的理解

> `super` 的理解：`父类的`。

在 Java 类中使用 `super` 来调用父类中的指定操作：
* `super` 可用于访问父类中定义的**属性**。
* `super` 可用于调用父类中定义的成员**方法**。
* `super` 可用于在子类构造器中调用父类的**构造器**。

> 注意：
> * 尤其当子父类出现同名成员时，可以用 `super` 表明调用的是父类中的成员。
> * `super` 的追溯不仅限于直接父类。
> * `super` 和 `this` 的用法相像：`this` 代表本类对象的引用，`super` 代表父类的内存空间的标识。

### 5.2 `super` 的使用场景

**1. `super` 调用属性、方法：**

子类继承父类以后，我们就可以在子类的方法或构造器中，调用父类中声明的属性或方法。（满足封装性的前提下。）

如何调用呢？需要使用 “`super.`” 的结构，表示调用父类的属性或方法。

一般情况下，我们可以考虑省略 “`super.`” 的结构。但是，如果出现子类重写了父类的方法或子父类中出现了同名的属性时，则必须使用 “`super.`” 的声明，显式地调用父类被重写的方法或父类中声明的同名的属性。

**2. `super` 调用构造器：**

见如下《5.2.3 子类构造器中调用父类构造器》。

> 注意：
> 
> 我们在通过子类的构造器创建对象时，一定在调用子类构造器的过程中，直接或间接地调用到父类的构造器。
> 
> 也正因为调用过父类的构造器，我们才会将父类中声明的属性或方法加载到内存中，供子类对象使用。

#### 5.2.1 子类中调用父类被重写的方法

在子类中调用父类被重写的方法，分以下两种情况讨论：
* 如果子类没有重写父类的方法，只要权限修饰符允许，在子类中完全可以直接调用父类的方法。
* 如果子类重写了父类的方法，在子类中需要通过 `super.` 才能调用父类被重写的方法，否则默认调用的是子类重写的方法。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_05._super;

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

    public void doSport() {
        System.out.println("人运动");
    }

}


/* Student.java */

package com.anxin_hitsz_05._super;

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

    String school;

    // 方法

    public void study() {
        System.out.println("学生学习");
    }

    public void eat() {
        System.out.println("学生多吃有营养的食物");
    }
    public void sleep() {
        System.out.println("学生保证每天不低于 10 个小时的睡眠");
    }

    public void show() {
        eat();  // 省略了 this.
        this.eat();

        super.eat();
    }

    public void show1() {
        doSport();
        this.doSport();
        super.doSport();
    }

}


/* StudentTest.java */

package com.anxin_hitsz_05._super;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_05._super
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 21:27
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.eat();
        s1.sleep();

        s1.show();
    }
}

```

总结：
* 方法前面没有 `super.` 和 `this.`：
  * 先从子类找匹配方法，如果没有，再从直接父类找；再没有，继续往上追溯。
* 方法前面有 `this.`：
  * 先从子类找匹配方法，如果没有，再从直接父类找；再没有，继续往上追溯。
* 方法前面有 `super.`：
  * 从当前子类的直接父类找，如果没有，继续往上追溯。

#### 5.2.2 子类中调用父类中同名的成员变量

如果实例变量与局部变量重名，可以在实例变量前面加 `this.` 进行区别。

如果子类实例变量和父类实例变量重名，并且父类的该实例变量在子类仍然可见，则在子类中要访问父类声明的实例变量需要在父类实例变量前加 `super.`，否则默认访问的是子类自己声明的实例变量。

如果父子类实例变量没有重名，只要权限修饰符允许，在子类中完全可以直接访问父类中声明的实例变量，也可以用 `this.实例变量` 访问，亦可以用 `super.实例变量` 访问。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_05._super;

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

    int id = 1001; // 身份证号

    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }
    public void sleep() {
        System.out.println("人睡觉");
    }

    public void doSport() {
        System.out.println("人运动");
    }

}


/* Student.java */

package com.anxin_hitsz_05._super;

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

    String school;

    int id = 1002; // 学号

    public void setSchool(String school) {
        this.school = school;
    }

    // 方法

    public void study() {
        System.out.println("学生学习");
    }

    public void eat() {
        System.out.println("学生多吃有营养的食物");
    }
    public void sleep() {
        System.out.println("学生保证每天不低于 10 个小时的睡眠");
    }

    // 测试 super 调用方法、属性
    public void show() {
        eat();  // 省略了 this.
        this.eat();

        super.eat();
    }

    public void show1() {
        doSport();
        this.doSport();
        super.doSport();
    }

    public void show2() {
        System.out.println(id); // 1002
        System.out.println(this.id);    // 1002

        System.out.println(super.id);   // 1001
    }

    public void show3() {
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }

}


/* StudentTest.java */

package com.anxin_hitsz_05._super;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_05._super
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 21:27
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.eat();
        s1.sleep();

        s1.show();

        System.out.println();
        s1.show2();
    }
}

```

总结 - 起点不同（就近原则）：
* 变量前面没有 `super.` 和 `this.`：
  * 在构造器、代码块、方法中如果出现使用某个变量，先查看是否是当前块声明的**局部变量**；
  * 如果不是局部变量，先从当前执行代码的**本类去找成员变量**；
  * 如果从当前执行代码的本类中没有找到，会往上找**父类声明的成员变量**（权限修饰符允许在子类中访问的）。
* 变量前面有 `this.`：
  * 通过 `this` 找成员变量时，先从当前执行代码的**本类去找成员变量**；
  * 如果从当前执行代码的本类中没有找到，会网上找**父类声明的成员变量**（权限修饰符允许在子类中访问的）。
* 变量前面 `super.`：
  * 通过 `super` 找成员变量，直接从当前执行代码的直接父类去找成员变量（权限修饰符允许在子类中访问的）；
  * 如果直接父类没有，就去父类的父类中找（权限修饰符允许在子类中访问的）。

**<font color='red'>特别说明：应该避免子类声明和父类重名的成员变量！</font>**

在阿里的开发规范等文档中都做出明确说明：
![在阿里的开发规范等文档中做出的明确说明](./images/image-20211230110411580.png "在阿里的开发规范等文档中做出的明确说明")

#### 5.2.3 子类构造器中调用父类构造器

① 子类继承父类时，不会继承父类的构造器。只能通过 “`super(形参列表)`” 的方式调用父类指定的构造器。

② 规定：“`super(形参列表)`” 必须声明在构造器的首行。

③ 我们前面讲过，在构造器的首行可以使用 “`this(形参列表)`” 调用本类中重载的构造器。结合②，得出结论：在构造器的首行，“`this(形参列表)`” 和 “`super(形参列表)`” 只能二选一。

④ 如果在子类构造器的首行既没有显式调用 “`this(形参列表)`”，也没有显式调用 “`super(形参列表)`”，则子类此构造器默认调用 “`super()`”，即调用父类中空参的构造器。

⑤ 由③和④得到结论：子类的任何一个构造器中，要么会调用本类中重载的构造器，要么会调用父类的构造器；只能是这两种情况之一。

⑥ 由⑤得到：一个类中声明有 n 个构造器，最多有 n - 1 个构造器中使用了 “`this(形参列表)`”，则剩下的那个一定使用 “`super(形参列表)`”。

> 开发中常见错误：
>
> 如果子类构造器中既未显式调用父类或本类的构造器，且父类中又没有空参的构造器，则**编译出错**。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_05._super;

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

    int id = 1001; // 身份证号

    public Person() {

        System.out.println("Person() ...");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }
    public void sleep() {
        System.out.println("人睡觉");
    }

    public void doSport() {
        System.out.println("人运动");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


/* Student.java */

package com.anxin_hitsz_05._super;

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

    String school;

    int id = 1002; // 学号

    public void setSchool(String school) {
        this.school = school;
    }

    // 方法

    public void study() {
        System.out.println("学生学习");
    }

    public void eat() {
        System.out.println("学生多吃有营养的食物");
    }
    public void sleep() {
        System.out.println("学生保证每天不低于 10 个小时的睡眠");
    }

    // 测试 super 调用方法、属性
    public void show() {
        eat();  // 省略了 this.
        this.eat();

        super.eat();
    }

    public void show1() {
        doSport();
        this.doSport();
        super.doSport();
    }

    public void show2() {
        System.out.println(id); // 1002
        System.out.println(this.id);    // 1002

        System.out.println(super.id);   // 1001
    }

    public void show3() {
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }

    // 测试 super 调用父类的构造器
    public Student() {
        super();
//        this("Tom", 12);
        System.out.println("Student() ...");
    }

    public Student(String name, int age) {
//        setAge(age);
//        super.name = name;
        super(name, age);
    }

}


/* StudentTest.java */

package com.anxin_hitsz_05._super;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_05._super
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 21:27
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.eat();
        s1.sleep();

        s1.show();

        System.out.println();
        s1.show2();

        System.out.println(s1.name);

        // *****************************************
        System.out.println();

        Student s2 = new Student();

        System.out.println("*****************************************");

        Student s3 = new Student("Tom", 12);
    }
}

```

### 5.3 小结：`this` 与 `super`

#### 5.3.1 `this` 和 `super` 的意义

`this`：当前对象。
* 在构造器和非静态代码块中，表示正在 `new` 的对象。
* 在实例方法中，表示调用当前方法的对象。

`super`：引用父类声明的成员。

#### 5.3.2 `this` 和 `super` 的使用格式

`this`：
* `this.成员变量`：表示当前对象的某个成员变量，而不是局部变量。
* `this.成员方法`：表示当前对象的某个成员方法，完全可以省略 this.。
* `this()` 或 `this(实参列表)`：调用另一个构造器协助当前对象的实例化，只能在构造器首行，只会找本类的构造器，找不到就报错。

`super`：
* `super.成员变量`：表示当前对象的某个成员变量，该成员变量在父类中声明的。
* `super.成员方法`：表示当前对象的某个成员方法，该成员方法在父类中声明的。
* `super()` 或 `super(实参列表)`：调用父类的构造器协助当前对象的实例化，只能在构造器首行，只会找直接父类的对应构造器，找不到就报错。

### 5.4 练习

**练习 1：**
> 题目：
>
> 修改方法重写的练习 2 中定义的类 `Kids` 中 `employeed()` 方法，在该方法中调用父类 `ManKind` 的 `employeed()` 方法；然后再输出 “`but Kids should study and no job.`”。

示例代码：
```java
/* ManKind.java */

package com.anxin_hitsz_05._super.exer1;

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

package com.anxin_hitsz_05._super.exer1;

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
        super.employeed();
        System.out.println("but Kids should study and no job.");
    }
}


/* KidsTest.java */

package com.anxin_hitsz_05._super.exer1;

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

//        kid.setSex(1);
//        kid.setSalary(100);
//        kid.setYearsOld(12);
//
//        // 来自于父类中声明的方法
//        kid.employeed();
//        kid.manOrWoman();
//
//        // Kids 类自己声明的方法
//        kid.printAge();

        System.out.println("******************************");
        kid.employeed();

    }
}

```

**练习 2：**
> 题目：
>
> 在 `Cylinder` 类中修改求表面积的方法 `findArea()` 和求体积的方法 `findVolume()`，并使用 `super`。

示例代码：
```java
/* Circle.java */

package com.anxin_hitsz_05._super.exer2;

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

package com.anxin_hitsz_05._super.exer2;

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
        // 错误的
        return super.findArea() * getLength();
    }

    // 求表面积
    public double findArea() {
        return 3.14 * getRadius() * getRadius() * 2 +
                2 * 3.14 * getRadius() * getLength();
    }
}


/* CylinderTest.java */

package com.anxin_hitsz_05._super.exer2;

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

**练习 3：**
> 题目：
>
> **1.**
> 
> 写一个名为 `Account` 的类模拟账户。该类的属性和方法如下图所示：
> ![UML - Account 类](./images/super_exer3_UML_Account.png "UML - Account 类")
> * 该类包括的属性：账号 `id`、余额 `balance`、年利率 `annualInterestRate`。
> * 该类包含的方法：访问器方法（`getter` 和 `setter` 方法）、返回月利率的方法 `getMonthlyInterest()`、取款方法 `withdraw()`、存款方法 `deposit()`。
> 
> 写一个用户程序测试 `Account` 类。在用户程序中，创建一个账号为 1122、余额为 20000、年利率 4.5% 的 `Account` 对象；使用 `withdraw()` 方法提款 2500 元，使用 `deposit()` 方法存款 3000 元，然后打印余额和月利率。
>
> 提示：在提款方法 `withdraw()` 中，需要判断用户余额是否能够满足提款数额的要求；如果不能，应给出提示。
>
> **2.**
>
> 创建 `Account` 类的一个子类 `CheckAccount` 代表可透支的账户，该账户中定义一个属性 `overdraft` 代表可透支限额。
> 
> 在 `CheckAccount` 类中重写 `withdraw()` 方法，其算法如下：
> ```java
> 如果 (取款金额 < 账户余额),
>     可直接取款
> 如果 (取款金额 > 账户余额),
>     计算需要透支的额度
>     判断可透支额 overdraft 是否足够支付本次透支需要；如果可以
>         将账户余额修改为 0，冲减可透支金额
>     如果不可以
>         提示用户超过可透支额的限额
> ```
>
> 要求：写一个用户程序测试 `CheckAccount` 类。
> 在用户程序中，创建一个账号为 1122、余额为 20000、年利率 4.5%、可透支限额为 5000 元的 `CheckAccount` 对象。
> 使用 `withdraw()` 方法提款 5000 元，并打印账户余额和可透支额。
> 再使用 `withdraw()` 方法提款 18000 元，并打印账户余额和可透支额。
> 再使用 `withdraw()` 方法提款 3000 元，并打印账户余额和可透支额。
>
> 提示：
> 1. 子类 `CheckAccount` 的构造方法需要将从父类继承的 3 个属性和子类自己的属性全部初始化。
> 2. 父类 `Account` 的属性 `balance` 被设置为 `private`，但在子类 `CheckAccount` 的 `withdraw()` 方法中需要修改它的值，因此应修改父类的 `balance` 属性，定义其为 `protected`。

示例代码：
```java
/* Account.java */

package com.anxin_hitsz_05._super.exer3;

/**
 * ClassName: Account
 * Package: com.anxin_hitsz_05._super.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 13:19
 * @Version 1.0
 */
public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;  // 年利率

//    public Account() {}

    public Account(int id, double balance, double annualInterestRate) {
//        super();
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

//    public void setBalance(double balance) {
//        this.balance = balance;
//    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * 获取月利率
     * @return
     */
    public double getMonthlyInterest() {
        return annualInterestRate / 12;
    }

    /**
     * 取钱操作
     * @param amount 要取的钱数
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("余额不足！");
        }
    }

    /**
     * 存钱操作
     * @param amount 要存的额度
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}


/* AccountTest.java */

package com.anxin_hitsz_05._super.exer3;

/**
 * ClassName: AccountTest
 * Package: com.anxin_hitsz_05._super.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 13:23
 * @Version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {

        Account acct = new Account(1122, 20000, 0.045);

        acct.withdraw(30000);
        System.out.println("您的账户余额为：" + acct.getBalance());

        acct.withdraw(2500);
        acct.deposit(3000);
        System.out.println("您的账户余额为：" + acct.getBalance());

        System.out.println("月利率为：" + acct.getMonthlyInterest());

    }
}


/* CheckAccount.java */

package com.anxin_hitsz_05._super.exer3;

/**
 * ClassName: CheckAccount
 * Package: com.anxin_hitsz_05._super.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 13:26
 * @Version 1.0
 */
public class CheckAccount extends Account {
    private double overdraft;   // 可透支限额

    public CheckAccount(int id, double balance, double annualInterestRate) {
        super(id, balance, annualInterestRate);
    }

    public CheckAccount(int id, double balance, double annualInterestRate, double overdraft) {
        super(id, balance, annualInterestRate);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    /**
     * 针对于可透支的账户的取钱的操作
     * @param amount 要取的钱数
     */
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            // 错误的：
//            getBalance() = getBalance() - amount;
            // 正确的：
            super.withdraw(amount);
        } else if (getBalance() + overdraft >= amount) {
            overdraft -= amount - getBalance();
            super.withdraw(getBalance());
        } else {
            System.out.println("超过可透支限额");
        }
    }
}


/* CheckAccountTest.java */

package com.anxin_hitsz_05._super.exer3;

/**
 * ClassName: CheckAccountTest
 * Package: com.anxin_hitsz_05._super.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 13:40
 * @Version 1.0
 */
public class CheckAccountTest {
    public static void main(String[] args) {

        CheckAccount checkAccount = new CheckAccount(1122, 20000, 0.045, 5000);

        checkAccount.withdraw(5000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

        checkAccount.withdraw(18000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

        checkAccount.withdraw(3000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

    }
}

```

> 注意：
>
> 以下代码：
> ```java
> public class Interview01 {
> 
>     public static void main(String[] args) {
>         new A(new B());
>     }
> }
> 
> ```
>
> 在上述代码中，首先会 `new` 类 `B` 的实例，之后 `new` 出来的该类 `B` 的实例作为参数参与类 `A` 的实例的 `new` 过程。

## 六、子类对象实例化全过程

![子类对象实例化流程图](./images/image-20220324003713230.png "子类对象实例化流程图")

示例：
```java
Dog dog = new Dog("旺财", "汪汪");
```

内存解析：
![示例内存解析示意图](./images/image-20220324003735416.png "示例内存解析示意图")

构造器调用关系：
![示例构造器调用关系示意图](./images/image-20220324003813163.png "示例构造器调用关系示意图")

> 注意：
> 
> 加载顺序为：先加载父类，再加载子类；即在上图中，加载顺序为由上至下加载。

示例代码：
```java
class Creature {    // 生物类
    // 声明属性、方法、构造器
}

class Animal extends Creature { // 动物类
    // 声明属性、方法、构造器
}

class Dog extends Animal {  // 狗类
    // 声明属性、方法、构造器
}

class DogTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.xxx();
        dog.yyy = ...;
    }
}

```

**总结：**
**1. 从结果的角度来看：体现为类的继承性。**

当我们创建子类对象后，子类对象就获取了其父类中声明的所有属性和方法。在权限允许的情况下，可以直接调用。

**2. 从过程的角度来看：**

当我们通过子类的构造器创建对象时，子类的构造器一定会直接或间接地调用到其父类的构造器；而父类的构造器同样会直接或间接地调用到其父类的父类的构造器，……；直到调用了 `Object` 类中的构造器为止。

正因为我们调用过子类所有的父类的构造器，所以我们就会将父类中声明的属性、方法加载到内存中，供子类的对象使用。

> 问题：
> * 在创建子类对象的过程中，一定会调用父类中的构造器吗？
>   * Yes！
> * 创建子类的对象时，内存中到底有多少个对象？
>   * 就只有一个对象！即为当前 `new` 后面构造器对应的类的对象。

## 七、面向对象特征三：多态性

> 一千个读者眼中有一千个哈姆雷特。

多态性的理解：理解为一个事物的多种形态。

### 7.1 多态的形式和体现

#### 7.1.1 对象的多态性

多态性，是面向对象中最重要的概念。在 Java 中的体现为 **子类对象的多态性：父类的引用指向子类的对象（或子类的对象赋给父类的引用）**。

比如：
```java
Person p2 = new Man();
```

格式：
```java
父类类型 变量名 = 子类对象;
```
其中，**父类类型**指子类继承的父类类型，或者实现的接口类型。

举例：
```java
Person p = new Student();

Object o = new Person();    // Object 类型的变量 o，指向 Person 类型的对象

o = new Student();  // Object 类型的变量 o，指向 Student 类型的对象
```

对象的多态：在 Java 中，子类的对象可以替代父类的对象使用；所以，一个引用类型变量可能指向（引用）多种不同类型的对象。

#### 7.1.2 多态的理解

Java 引用变量有两个类型：**编译时类型**和**运行时类型**。编译时类型由**声明**该变量时使用的类型决定，运行时类型由**实际赋给该变量的对象**决定。简称：**编译时，看左边；运行时，看右边。（针对方法而言！）**
* 若编译时类型和运行时类型不一致，就出现了对象的多态性（Polymorphosm）。
* 多态情况下：
  * “看左边”：看的是父类的引用（父类中不具备子类特有的方法）。
  * “看右边”：看的是子类的对象（实际运行的是子类重写父类的方法）。

> 注意：
>
> 问题：多态是编译时行为还是运行时行为？
> * **多态是运行时行为！**

多态的使用前提：
1. 要有类的继承关系；
2. 要有方法的重写。

多态的适用性：适用于方法，不适用于属性。
> 注意：对于属性，多态性不适用；即“编译看左边，运行看左边”。

#### 7.1.3 举例

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:27
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    int id = 1001;

    public void eat() {
        System.out.println("人吃饭");
    }

    public void walk() {
        System.out.println("人走路");
    }
}


/* Man.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Man
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:28
 * @Version 1.0
 */
public class Man extends Person {

    boolean isSmoking;

    int id = 1002;

    public void eat() {
        System.out.println("男人吃饭");
    }

    public void walk() {
        System.out.println("男人走路");
    }

    public void earnMoney() {
        System.out.println("挣钱 ...");
    }

}


/* Woman.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Woman
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:29
 * @Version 1.0
 */
public class Woman extends Person {

    boolean isBeauty;

    public void eat() {
        System.out.println("女人吃饭");
    }

    public void walk() {
        System.out.println("女人走路");
    }

    public void goShopping() {
        System.out.println("逛街 ...");
    }

}


/* PersonTest.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:33
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {

        // 多态性之前的场景：
        Person p1 = new Person();
        Man m1 = new Man();

        // 多态性：子类对象的多态性
        Person p2 = new Man();

        /*
        * 多态性的应用：虚拟方法调用（虚方法调用）
        *
        * 在多态的场景下，调用方法时。
        *   编译时，认为方法是左边声明的父类的类型的方法（即被重写的方法）。
        *   执行时，实际执行的是子类重写父类的方法。
        * 简称为：编译看左边，运行看右边。
        *
        * */
        p2.eat();
        p2.walk();

        // 测试属性是否满足多态性？不满足！
        System.out.println(p2.id);  // 1001

        /*
        * 多态的弊端？
        * 问题：Person p2 = new Man();
        *   针对于创建的对象，在内存中是否加载了 Man 类中声明的特有的属性和方法？加载了！
        * 问题：能不能直接调用 Man 类中加载的特有的属性和方法？不能！
        * */
//        p2.earnMoney();
//        System.out.println(p2.isSmoking);
//        Man m2 = new Man();

    }
}

```

### 7.2 为什么需要多态性（polymorphism）？

开发中，有时我们在设计一个数组、或一个成员变量、或一个方法的形参、返回值类型时，无法确定它具体的类型，只能确定它是某个系列的类型。

### 7.3 多态的好处和弊端

**好处**：变量引用的子类对象不同，执行的方法就不同，实现动态绑定；代码编写更灵活、功能更强大，可维护性和扩展性更好了。
即：极大地减少了代码的冗余，不需要定义多个重载的方法。

**弊端**：一个引用类型变量如果声明为父类的类型，但实际引用的是子类对象，那么该变量就不能再访问子类中添加的属性和方法。
即：在多态的场景下，我们创建了子类的对象，也加载了子类特有的属性和方法；但是由于声明为父类的引用，导致我们没有办法直接调用子类特有的属性和方法。

示例代码：
```java
Student m = new Student();
m.school = "pku";   // 合法，Student 类有 school 成员变量
Person e = new Student();
e.school = "pku";   // 非法，Person 类没有 school 成员变量

// 属性是在编译时确定的，编译时 e 为 Person 类型，没有 school 成员变量，因而编译错误。
```

> 开发中：
>
> 使用父类做方法的形参，是多态使用最多的场合。即使增加了新的子类，方法也无需改变，提高了扩展性，符合开闭原则。
>
> 【开闭原则 OCP（Open Close Principle）】：
> * 对扩展开放，对修改关闭。
> * 通俗解释：软件系统中的各种组件，如模块（Modules）、类（Classes）以及功能（Functions）等，应该在不修改现有代码的基础上，引入新功能。

**举例 1：**
```java
class Account {
    public void withdraw() {}   // 取钱
}

class CheckAccount extends Account {    // 信用卡
    // 存在方法的重写
    public void withdraw() {}   // 取钱
}
class SavingAccount extends Account {   // 储蓄卡
    // 存在方法的重写
}

class Customer {
    Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}

class CustomerTest {
    public static void main(String[] args) {
        Customer cust = new Customer();
        cust.setAccount(new CheckAccount());

        cust.getAccount().withdraw();

    }
}
```
举例 2：
```java
/* AnimalTest.java */

package com.anxin_hitsz_06.polymorphism.apply;

/**
 * ClassName: AnimalTest
 * Package: com.anxin_hitsz_06.polymorphism.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:54
 * @Version 1.0
 */
public class AnimalTest {

    public static void main(String[] args) {
        AnimalTest test = new AnimalTest();

        test.adopt(new Dog());
        test.adopt(new Cat());
    }

    public void adopt(Animal animal) {  // Animal animal = new Dog();
        animal.eat();
        animal.jump();

//        animal.watchDoor();

    }

//    public void adopt(Dog dog) {
//        dog.eat();
//        dog.jump();
//
//    }
//
//    public void adopt(Cat cat) {
//        cat.eat();
//        cat.jump();
//
//    }

}

class Animal {
    public void eat() {
        System.out.println("动物进食");
    }

    public void jump() {
        System.out.println("动物跳");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void jump() {
        System.out.println("狗跳");
    }

    public void watchDoor() {
        System.out.println("狗能看家");
    }
}

class Cat extends Animal {
    public void eat() {
        System.out.println("猫吃鱼");
    }

    public void jump() {
        System.out.println("猫跳");
    }

    public void catchMouse() {
        System.out.println("猫抓老鼠");
    }
}

```

### 7.4 虚方法调用（Virtual Method Invocation）

在 Java 中虚方法是在编译阶段不能确定方法的调用入口地址，在运行阶段才能确定的方法；即可能被重写的方法。

示例代码：
```java
Person e = new Student();
e.getInfo();    // 调用 Student 类的 getInfo() 方法
```

子类中定义了与父类同名同参数的方法，在多态情况下，将此时父类的方法称为虚方法，父类根据赋给它的不同子类对象，动态调用属于子类的该方法。这样的方法调用在编译期是无法确定的。

举例：
![虚方法调用举例](./images/image-20220324234208997.png "虚方法调用举例")
* 前提：`Person` 类中定义了 `welcome()` 方法，各个子类重写了 `welcome()`。
* 执行：多态的情况下，调用对象的 `welcome()` 方法，实际执行的是子类重写的方法。

> 拓展：
>
> **静态链接（或早期绑定）**：当一个字节码文件被装载进 JVM 内部时，如果被调用的目标方法在编译期可知，且运行期保持不变时。这种情况下将调用方法的符号引用转换为直接引用的过程称之为静态链接；那么调用这样的方法，就称为非虚方法调用。比如调用静态方法、私有方法、`final` 方法、父类构造器、本类重载构造器等。
>
> **动态链接（或晚期绑定）**：如果被调用的方法在编译期无法被确定下来，也就是说，只能够在程序运行期将调用方法的符号引用转换为直接引用。由于这种引用转换过程具备动态性，因此也就被称之为动态链接；调用这样的方法，就称为虚方法调用。比如调用重写的方法（针对父类）、实现的额方法（针对接口）。

### 7.5 成员变量没有多态性

若子类重写了父类方法，就意味着子类里定义的方法彻底覆盖了父类里的同名方法，系统将不可能把父类里的方法转移到子类中。

对于实例变量则不存在这样的现象，即使子类里定义了与父类完全相同的实例变量，这个实例变量依然不可能覆盖父类中定义的实例变量。

### 7.6 向上转型与向下转型

首先，一个对象在 `new` 的时候创建的是哪个类型的对象，它从头至尾都不会变；即这个对象的运行时类型、本质的类型永远不会变。但是，把这个对象赋值给不同类型的变量时，这些变量的编译时类型却不同。

#### 7.6.1 为什么要类型转换？

因为多态，就一定会有把子类对象赋值给父类变量的时候。这个时候，在**编译期间**，就会出现类型转换的现象。

但是，使用父类变量接收了子类对象之后，我们就**不能调用**子类拥有而父类没有的方法了，这也是多态给我们带来的一点“小麻烦”。所以，想要调用子类特有的方法，必须做类型转换，使得**编译通过**。

> 类型转换与转型的对比：
> 
> ![类型转换与转型的对比示意图](./images/image-20220324235337563.png "类型转换与转型的对比示意图")

转型分为向上转型与向下转型：
* **向上转型**：当 `左边的变量的类型（父类） > 右边对象 / 变量的类型（子类）`，我们就称为向上转型。
  * 此时，编译时按照左边变量的类型处理，就只能调用父类中有的变量和方法，不能调用子类特有的变量和方法了。
  * 但是，**运行时仍然是对象本身的类型**，所以执行的方法是子类重写的方法体。
  * 此时，一定是安全的，而且也是自动完成的。
* **向下转型**：当 `左边的变量的类型（子类） < 右边对象 / 变量的编译时类型（父类）`，我们就称为向下转型。
  * 此时，编译时按照左边变量的类型处理，就可以调用子类特有的变量和方法了。
  * 但是，运行时仍然是对象本身的类型。
  * 不是所有通过编译的向下转型都是正确的，可能会发生 `ClassCastException`；为了安全，可以通过 `instanceof` 关键字进行判断。

#### 7.6.2 如何向上或向下转型

向上转型：自动完成。

向下转型：`(子类类型)父类变量`。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:27
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    int id = 1001;

    public void eat() {
        System.out.println("人吃饭");
    }

    public void walk() {
        System.out.println("人走路");
    }
}


/* Man.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Man
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:28
 * @Version 1.0
 */
public class Man extends Person {

    boolean isSmoking;

    int id = 1002;

    public void eat() {
        System.out.println("男人吃饭");
    }

    public void walk() {
        System.out.println("男人走路");
    }

    public void earnMoney() {
        System.out.println("挣钱 ...");
    }

}


/* Woman.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Woman
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:29
 * @Version 1.0
 */
public class Woman extends Person {

    boolean isBeauty;

    public void eat() {
        System.out.println("女人吃饭");
    }

    public void walk() {
        System.out.println("女人走路");
    }

    public void goShopping() {
        System.out.println("逛街 ...");
    }

}


/* PersonTest1.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: PersonTest1
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:17
 * @Version 1.0
 */
public class PersonTest1 {
    public static void main(String[] args) {
        Person p1 = new Man();

        // 不能直接调用子类特有的结构
//        p1.earnMoney();
//        System.out.println(p1.isSmoking);

        // 向下转型：使用强转符
        Man m1 = (Man)p1;
        m1.earnMoney();
        System.out.println(m1.isSmoking);

        System.out.println(p1 == m1);   // true，p1 和 m1 指向堆空间中的同一个对象

        /*
        * 向下转型可能会出现：类型转换异常（ClassCastException）
        * */
        Person p2 = new Woman();
//        Man m2 = (Man)p2;
//        m2.earnMoney();

        /*
        * 1. 建议在向下转型之前，使用 instanceof 进行判断，避免出现类型转换异常
        * 2. 格式：a instanceof A：判断对象 a 是否是类 A 的实例
        * 3. 如果 a instanceof A 返回 true，则：
        *       a instanceof superA 返回也是 true，其中 A 是 superA 的子类。
        * */
        if (p2 instanceof Man) {
            Man m2 = (Man)p2;
            m2.earnMoney();
        }

        if (p2 instanceof Woman) {
            System.out.println("Woman");
        }

        if (p2 instanceof Person) {
            System.out.println("Person");
        }

        if (p2 instanceof Object) {
            System.out.println("Object");
        }
    }
}

```

#### 7.6.3 `instanceof` 关键字

为了避免 `ClassCastException` 的发生，Java 提供了 `instanceof` 关键字，给引用变量做类型的校验。如下代码格式：
```java
// 检验对象 a 是否是数据类型 A 的对象，返回值为 boolean 型
对象a instanceof 数据类型A
```

> 说明：
> * 只要用 `instanceof` 判断返回 `true` 的，那么强转为该类型就一定是安全的，不会报 `ClassCastException` 异常。
> * 如果对象 `a` 属于类 `A` 的子类 `B`，`a instanceof A` 值也为 `true`。
> * 要求对象 `a` 所属的类与类 `A` 必须是子类和父类的关系，否则编译错误。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:27
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    int id = 1001;

    public void eat() {
        System.out.println("人吃饭");
    }

    public void walk() {
        System.out.println("人走路");
    }
}


/* Man.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Man
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:28
 * @Version 1.0
 */
public class Man extends Person {

    boolean isSmoking;

    int id = 1002;

    public void eat() {
        System.out.println("男人吃饭");
    }

    public void walk() {
        System.out.println("男人走路");
    }

    public void earnMoney() {
        System.out.println("挣钱 ...");
    }

}


/* Woman.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Woman
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:29
 * @Version 1.0
 */
public class Woman extends Person {

    boolean isBeauty;

    public void eat() {
        System.out.println("女人吃饭");
    }

    public void walk() {
        System.out.println("女人走路");
    }

    public void goShopping() {
        System.out.println("逛街 ...");
    }

}


/* PersonTest1.java */

package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: PersonTest1
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:17
 * @Version 1.0
 */
public class PersonTest1 {
    public static void main(String[] args) {
        Person p1 = new Man();

        // 不能直接调用子类特有的结构
//        p1.earnMoney();
//        System.out.println(p1.isSmoking);

        // 向下转型：使用强转符
        Man m1 = (Man)p1;
        m1.earnMoney();
        System.out.println(m1.isSmoking);

        System.out.println(p1 == m1);   // true，p1 和 m1 指向堆空间中的同一个对象

        /*
        * 向下转型可能会出现：类型转换异常（ClassCastException）
        * */
        Person p2 = new Woman();
//        Man m2 = (Man)p2;
//        m2.earnMoney();

        /*
        * 1. 建议在向下转型之前，使用 instanceof 进行判断，避免出现类型转换异常
        * 2. 格式：a instanceof A：判断对象 a 是否是类 A 的实例
        * 3. 如果 a instanceof A 返回 true，则：
        *       a instanceof superA 返回也是 true，其中 A 是 superA 的子类。
        * */
        if (p2 instanceof Man) {
            Man m2 = (Man)p2;
            m2.earnMoney();
        }

        if (p2 instanceof Woman) {
            System.out.println("Woman");
        }

        if (p2 instanceof Person) {
            System.out.println("Person");
        }

        if (p2 instanceof Object) {
            System.out.println("Object");
        }
    }
}

```

### 7.7 练习

**练习 1：**
> 题目：
>
> 定义三个类，父类 `GeometricObject` 代表几何形状，子类 `Circle` 代表圆形，`MyRectangle` 代表矩形。UML 类图如下：
> ![练习 1 - UML](./images/polymorphism_exer1_UML.png "练习 1 - UML")
> 
> 定义一个测试类 `GeometricTest`：
> * 编写 `equalsArea` 方法测试两个对象的面积是否相等（注意方法的参数类型）。
> * 编写 `displayGeometricObject` 方法显示对象的面积（注意方法的参数类型）。

示例代码：
```java
/* GeometricObject.java */

package com.anxin_hitsz_06.polymorphism.exer1;

/**
 * ClassName: GeometricObject
 * Package: com.anxin_hitsz_06.polymorphism.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:31
 * @Version 1.0
 */
public class GeometricObject {
    protected String color;
    protected double weight;

    protected GeometricObject(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double findArea() {
        return 0.0;
    }
}


/* Circle.java */

package com.anxin_hitsz_06.polymorphism.exer1;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_06.polymorphism.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:35
 * @Version 1.0
 */
public class Circle extends GeometricObject {

    private double radius;  // 半径

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double findArea() {
        return 3.14 * radius * radius;
    }
}


/* MyRectangle.java */

package com.anxin_hitsz_06.polymorphism.exer1;

/**
 * ClassName: MyRectangle
 * Package: com.anxin_hitsz_06.polymorphism.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:37
 * @Version 1.0
 */
public class MyRectangle extends GeometricObject {
    private double width;   // 宽
    private double height;  // 高

    public MyRectangle(String color, double weight, double width, double height) {
        super(color, weight);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double findArea() {

        return width * height;
    }
}

```

**练习 2：**
> 题目：
>
> 修改 com.anxin_hitsz_06.polymorphism.apply 包下的 `AnimalTest` 类的方法，在判断 `Animal` 是 `Dog` 或 `Cat` 时向下转型，并调用各自特有的方法。

示例代码：
```java
/* AnimalTest.java */

package com.anxin_hitsz_06.polymorphism.exer2;

/**
 * ClassName: AnimalTest
 * Package: com.anxin_hitsz_06.polymorphism.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:54
 * @Version 1.0
 */
public class AnimalTest {

    public static void main(String[] args) {
        AnimalTest test = new AnimalTest();

        test.adopt(new Dog());
        test.adopt(new Cat());
    }

    public void adopt(Animal animal) {  // Animal animal = new Dog();
        animal.eat();
        animal.jump();

        if (animal instanceof Dog) {
            Dog dog = (Dog)animal;
            dog.watchDoor();
        }
        if (animal instanceof Cat) {
            Cat cat = (Cat)animal;
            cat.catchMouse();
        }

    }

//    public void adopt(Dog dog) {
//        dog.eat();
//        dog.jump();
//
//    }
//
//    public void adopt(Cat cat) {
//        cat.eat();
//        cat.jump();
//
//    }

}

class Animal {
    public void eat() {
        System.out.println("动物进食");
    }

    public void jump() {
        System.out.println("动物跳");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void jump() {
        System.out.println("狗跳");
    }

    public void watchDoor() {
        System.out.println("狗能看家");
    }
}

class Cat extends Animal {
    public void eat() {
        System.out.println("猫吃鱼");
    }

    public void jump() {
        System.out.println("猫跳");
    }

    public void catchMouse() {
        System.out.println("猫抓老鼠");
    }
}

```

**练习 3：**
> 题目：
>
> 已知代码如下：
> ```java
> class Person {
>     protected String name = "person";
>     protected int age = 50;
>     public String getInfo() {
>         return "Name: " + name + "\n" + "age: " + age;
>     }
> }
> class Student extends Person {
>     protected String school = "pku";
>     public String getInfo() {
>         return "Name: " + name + "\nage: " + age 
>         + "\nschool: " + school;
>     }
> }
> class Graduate extends Student {
>     public String major = "IT";
>     public String getInfo() {
>         return "Name: " + name + "\nage: " + age 
>         + "\nschool: " + school + "\nmajor: " + major;
>     }
> }
> ```
>
> 建立 `InstanceTest` 类，在类中定义方法 `method(Person e)`；在 `method` 中：
> 1. 根据 `e` 的类型调用相应类的 `getInfo()` 方法。
> 2. 根据 `e` 的类型执行：
> * 如果 `e` 为 `Person` 类的对象，输出：
>   ```java
>   a person
>   ```
> * 如果 `e` 为 `Student` 类的对象，输出：
>   ```java
>   a student
>   a person
>   ```
> * 如果 `e` 为 `Graduate` 类的对象，输出：
>   ```java
>   a graduate student
>   a student
>   a person
>   ```
>
> 以上为题目描述。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:57
 * @Version 1.0
 */
class Person {
    protected String name="person";
    protected int age=50;
    public String getInfo() {
        return "Name: "+ name + "\n" +"age: "+ age;
    }
}


/* Student.java */

package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:58
 * @Version 1.0
 */
class Student extends Person {
    protected String school="pku";
    public String getInfo() {
        return  "Name: "+ name + "\nage: "+ age
                + "\nschool: "+ school;
    }
}


/* Graduate.java */

package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: Graduate
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:58
 * @Version 1.0
 */
class Graduate extends Student {
    public String major="IT";
    public String getInfo()
    {
        return  "Name: "+ name + "\nage: "+ age
                + "\nschool: "+ school+"\nmajor:"+major;
    }
}


/* InstanceTest.java */

package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: InstanceTest
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:56
 * @Version 1.0
 */
public class InstanceTest {

    public static void main(String[] args) {

        InstanceTest test = new InstanceTest();

        test.method(new Student());

    }

    public void method(Person e) {

        System.out.println(e.getInfo());

        // 方式 1：
//        if (e instanceof Graduate) {
//            System.out.println("a graduated student");
//            System.out.println("a student");
//            System.out.println("a person");
//        } else if (e instanceof Student) {
//            System.out.println("a student");
//            System.out.println("a person");
//        } else {
//            System.out.println("a person");
//        }
        // 方式 2：
        if (e instanceof Graduate) {
            System.out.println("a graduated student");
        }

        if (e instanceof Student) {
            System.out.println("a student");
        }

        if (e instanceof Person) {
            System.out.println("a person");
        }
    }
}

```

**练习 4：**
> 题目：
>
> **1. 在包中声明人 `Person`、男人 `Man`、女人 `Woman` 类。**
> 1. 在 `Person` 类中，包含：
> * `public void eat()`：打印吃饭。
> * `public void toilet()`：打印上洗手间。
> 2. 在 `Man` 类中，包含：
> * 重写上面的方法。
> * 增加 `public void smoke()`：打印吸烟。
> 3. 在 `Woman` 类中，包含：
> * 重写上面的方法。
> * 增加 `public void makeup()`：打印化妆。
>
> **2. 在包中声明测试类 `Exer4`。**
> 1. `public static void meeting(Person ... ps)`：
> 在该方法中，每一个人先吃饭，然后上洗手间。然后如果是男人，随后抽根烟；如果是女人，随后化个妆。
> 2. `public static void main(String[] args)`：
> 在主方法中，创建多个男人和女人对象，并调用 `meeting()` 方法进行测试。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_06.polymorphism.exer4;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 18:07
 * @Version 1.0
 */
public class Person {
    public void eat() {
        System.out.println("人吃饭");
    }

    public void toilet() {
        System.out.println("人去洗手间");
    }

}


/* Man.java */

package com.anxin_hitsz_06.polymorphism.exer4;

/**
 * ClassName: Man
 * Package: com.anxin_hitsz_06.polymorphism.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 18:09
 * @Version 1.0
 */
public class Man extends Person {
    public void eat() {
        System.out.println("男人吃饭");
    }

    public void toilet() {
        System.out.println("男人去洗手间");
    }

    public void smoke() {
        System.out.println("吸烟");
    }

}


/* Woman.java */

package com.anxin_hitsz_06.polymorphism.exer4;

/**
 * ClassName: Woman
 * Package: com.anxin_hitsz_06.polymorphism.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 18:10
 * @Version 1.0
 */
public class Woman extends Person {
    public void eat() {
        System.out.println("女人吃饭");
    }

    public void toilet() {
        System.out.println("女人去洗手间");
    }

    public void makeup() {
        System.out.println("化妆");
    }

}


/* Exer4.java */

package com.anxin_hitsz_06.polymorphism.exer4;

/**
 * ClassName: Exer4
 * Package: com.anxin_hitsz_06.polymorphism.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 18:12
 * @Version 1.0
 */
public class Exer4 {
    public static void main(String[] args) {

        Exer4 exer4 = new Exer4();
        exer4.meeting(new Man(), new Woman(), new Man());

    }

    public void meeting(Person ... ps) {

        for (int i = 0; i < ps.length; i++) {
            ps[i].eat();
            ps[i].toilet();

            if (ps[i] instanceof Woman) {

                Woman woman = (Woman)ps[i];
                woman.makeup();

            } else if (ps[i] instanceof Man) {
                Man man = (Man)ps[i];
                man.smoke();
            }

//            if (ps[i] instanceof Object) {
//
//            }

            // 编译不通过
//            if (ps[i] instanceof String) {
//
//            }

            System.out.println();
        }

    }
}

```

## 八、`Object` 类的使用

### 8.1 如何理解根父类

类 `java.lang.Object` 是类层次结构的根类，即所有其它类的父类。每个类都使用 `Object` 作为超类。因此，`Object` 类称为 Java 类的根父类。

类的继承树关系示例：
![类的继承树关系示例](./images/image-20220503104750655.png "类的继承树关系示例")

`Object` 类型的变量与除 `Object` 以外的任意引用数据类型的对象都存在多态引用。例如：
```java
method(Object obj) { ... }  // 可以接收任何类作为其参数

Person o = new Person();
method(o);
```
即任何一个 Java 类（除 `Object` 类）都直接或间接地继承于 `Object` 类。

`Object` 类中声明的结构（属性、方法等）就具有通用性。
* `Object` 类中没有声明属性。
* `Object` 类提供了一个空参的构造器。
* 重点关注：`Object` 类中声明的方法。

所有对象（包括数组）都实现这个类的方法。

如果一个类没有特别指定父类，那么默认则继承自 `Object` 类。例如：
```java
public class Person {
    ...
}
// 等价于：
public class Person extends Object {
    ...
}
```

### 8.2 `Object` 类的方法

根据 JDK 源代码及 `Object` 类的 API 文档，`Object` 类当中包含的方法有 11 个。

> 注意：
>
> Object 类当中包含的方法可以分为以下层级：
> * 重点方法：`equals()` / `toString()`。
> * 了解方法：`clone()` / `finalize()`。
> * 目前不需要关注：`getClass()` / `hashCode()` / `notify()` / `notifyAll()` / `wait(xx)` / `wait(xx, yy)`。

这里我们主要关注其中的 6 个。

#### 8.2.1 （重点）`equals()`

##### 8.2.1.1 `==`

基本类型比较值：只要两个变量的值相等，即为 `true`。例如：
```java
int a = 5;
if (a == 6) {
    ...
}
```

引用类型比较引用（是否指向同一个对象）：只有指向同一个对象时，`==` 才返回 `true`。例如：
```java
Person p1 = new Person();
Person p2 = new Person();
if (p1 == p2) {
    ...
}
```

> 注意：当使用 “`==`” 进行比较时，符号两边的**数据类型必须兼容**（可自动转换的基本数据类型除外），否则编译出错。

##### 8.2.1.2 `equals()`

任何引用数据类型都可以使用 `equals()`。

所有类都继承了 `Object`，也就获得了 `equals()` 方法；还可以重写。

`equals()` 只能比较引用类型；`Object` 类源码中 `equals()` 的作用与 “`==`” 相同，均为比较是否指向同一个对象。如下图所示：
![`Object` 类源码中 `equals()` 的作用与 “`==`” 相同](./images/image-20220226101655293.png "`Object` 类源码中 `equals()` 的作用与 “`==`” 相同")

`java.lang.Object` 类中 `equals()` 方法的定义：
```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

`equals()` 方法的格式如下：
```java
obj1.equals(obj2)
```

特例：当用 `equals()` 方法进行比较时，对类 `File`、`String`、`Date` 及包装类（`Wrapper Class`）来说，是比较类型及内容而不考虑引用的是否是同一个对象。
* 原因：在这些类中重写了 `Object` 类的 `equals()` 方法。

> 子类使用说明：
> * 自定义的类在没有重写 `Object` 类中 `equals()` 方法的情况下，调用的就是 `Object` 类中声明的 `euqals()` 方法，比较两个对象的引用地址是否相同（或比较两个对象是否指向了堆空间中的同一个对象实体）。
> * 对于像 `String`、`File`、`Date` 和包装类等，它们都重写了 `Object` 类中的 `equals()` 方法，用于比较两个对象的实体内容是否相等。

当自定义使用 `equals()` 时，可以重写；用于比较两个对象的“内容”是否都相等。重写 `equals()` 方法的原则如下：
* **对称性**：如果 `x.equals(y)` 返回的是 “`true`”，那么 `y.euqals(x)` 也应该返回的是 “`true`”。
* **自反性**：`x.equals(x)` 必须返回的是 “`true`”；
* **传递性**：如果 `x.equals(y)` 返回的是 “`true`”，而且 `y.equals(z)` 返回的是 “`true`”，那么 `z.equals(x)` 也应该返回的是 “`true`”。
* **一致性**：如果 `x.equals(y)` 返回的是 “`true`”，只要 `x` 和 `y` 的内容一直不变，不管重复执行 `x.equals(y)` 多少次，返回的都是 “`true`”。
* 任何情况下，`x.equals(null)` 永远返回的是 “`false`”，且 `x.equals(和 x 不同类型的对象)` 永远返回的是 “`false`”。

实际开发中，针对于自定义的类，常常会判断两个对象是否 `equals()`，而此时主要是判断两个对象的属性值是否相等。所以，我们要重写 `Object` 类的 `equals()` 方法。如何重写：
* 手动自己实现。
* 调用 IDEA 自动实现（推荐）。

> 注意：
>
> 在 IDEA 中，通过 `Alt + Insert` 组合键可调出 Generate 面板实现 `equals()` 方法和 `hashCode()` 方法的快速自动重写。

重写举例：
```java
/* UserTest.java */

package com.anxin_hitsz_07.object.equals;

import java.io.File;
import java.util.Objects;

/**
 * ClassName: UserTest
 * Package: com.anxin_hitsz_07.object.equals
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 14:34
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {

        User u1 = new User("Tom", 12);
        User u2 = new User("Tom", 12);

        System.out.println(u1.equals(u2));  // false -> true

        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println(str1 == str2);   // false
        System.out.println(str1.equals(str2));  // true

        File file1 = new File("d:\\abc.txt");
        File file2 = new File("d:\\abc.txt");
        System.out.println(file1.equals(file2));    // true

        // 数组上使用 equals()
        int[] arr = new int[10];
        System.out.println(arr.equals(new int[10]));    // false

    }
}

class User {
    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 重写equals()：手动实现
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//
//        if (obj instanceof User) {
//            User user = (User)obj;
//            // 方式 1：
////            if (this.age == user.age && this.name.equals(user.name)) {
////                return true;
////            } else {
////                return false;
////            }
//            // 方式 2：
//            return this.age == user.age && this.name.equals(user.name);
//        }
//
//        return false;
//
//    }

    // IDEA 自动实现
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

}

```

> **高频面试题：**
> 
> 区分 `==` 和 `equals()` 的区别？
> * `==` 既可以比较基本类型也可以比较引用类型：
>   * 对于基本数据类型变量即判断数据值是否相等：
>   ```java
>   int i1 = 10;
>   int i2 = 10;
>   sout(i1 == i2); // true
>   
>   char c1 = 'A';
>   int i3 = 65;
>   sout(c1 == i3); // true
>   
>   float f1 = 12.0F;
>   int i4 = 12;
>   sout(f1 == i4); // true
>   ```
>   * 对于引用数据类型变量即比较两个引用变量的地址值是否相等（或比较两个引用是否指向同一个对象实体）：
> * `equals()` 是属于 `java.lang.Object` 类里面的方法。
>   * 使用范围：只能使用在引用数据类型上。
>   * 具体使用：对于类来说，重写 `equals()` 和不重写 `equals()` 存在区别。如果该方法没有被重写过，则默认也是 `==`；我们可以看到 `String` 等类的 `equals()` 方法是被重写过的，而且 `String` 类在日常开发中用的比较多，久而久之就形成了 `equals()` 是比较值的错误观点。
> 
> 因此，具体要看自定义类里有没有重写 `Object` 类的 `equals()` 方法来判断。
> 
> 通常情况下，重写 `equals()` 方法，会比较类中的相应属性是否都相等。

##### 8.2.1.3 应用

示例代码：
```java
/* Account.java */

package com.anxin_hitsz_07.object.apply;

/**
 * ClassName: Account
 * Package: com.anxin_hitsz_07.object.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:18
 * @Version 1.0
 */
public class Account {

    private double balance; // 余额

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0;
    }

}


/* Customer.java */

package com.anxin_hitsz_07.object.apply;

import java.util.Objects;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_07.object.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:18
 * @Version 1.0
 */
public class Customer {

    private String name;
    private int age;
    private Account acct;

    public Customer() {
    }

    public Customer(String name, int age, Account acct) {
        this.name = name;
        this.age = age;
        this.acct = acct;
    }

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

    public Account getAcct() {
        return acct;
    }

    public void setAcct(Account acct) {
        this.acct = acct;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name) && Objects.equals(acct, customer.acct);
    }
    
}


/* CustomerTest.java */

package com.anxin_hitsz_07.object.apply;

/**
 * ClassName: CustomerTest
 * Package: com.anxin_hitsz_07.object.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:19
 * @Version 1.0
 */
public class CustomerTest {
    public static void main(String[] args) {
        Customer c1 = new Customer("Tom", 12, new Account(2000));
        Customer c2 = new Customer("Tom", 12, new Account(2000));

        System.out.println(c1.equals(c2));  // false -> true

    }
}

```

> 说明：
>
> 判断两个 `Customer` 对象是否 `equals()`，除了 `Customer` 类需要重写 `equals()` 之外，其内部的类类型的属性所在的类也需要重写 `equals()`。

##### 8.2.1.4 练习

**练习 1：**
> 题目：
>
> 编写 `Order` 类，包括 `int orderId`、`String orderName` 两个属性及其相应的 `getter()` 和 `setter()` 方法，以及两个参数的构造器。
> 要求重写父类的 `equals()` 方法：`public boolean equals(Object obj)`。并判断测试类中创建的两个对象是否相等。

示例代码：
```java
/* Order.java */

package com.anxin_hitsz_07.object.equals.exer1;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_07.object.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:25
 * @Version 1.0
 */
public class Order {
    private int orderId;
    private String orderName;

    public Order() {
    }

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    // 手写 equals()：
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Order) {
            Order order = (Order)obj;
            return this.orderId == order.orderId && this.orderName.equals(order.orderName);
        }

        return false;

    }
}


/* OrderTest.java */

package com.anxin_hitsz_07.object.equals.exer1;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_07.object.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:28
 * @Version 1.0
 */
public class OrderTest {
    public static void main(String[] args) {
        Order order1 = new Order(1001, "orderAA");
        Order order2 = new Order(1001, "orderAA");
        System.out.println(order1.equals(order2));  // true

        Order order3 = new Order(1002, new String("orderBB"));
        Order order4 = new Order(1002, new String("orderBB"));
        System.out.println(order3.equals(order4));  // true

        String str1 = "AA";
        String str2 = "AA";
        System.out.println(str1 == str2);   // true
    }
}

```

**练习 2：**
> 题目：
>
> 请根据以下代码自行定义能满足需要的 `MyDate` 类。
> ```java
> public class EqualsTest {
>     public static void main(String[] args) {
>         MyDate m1 = new MyDate(14, 3, 1976);
>         MyDate m2 = new MaDate(14, 3, 1976);
>         if (m1 == m2) {
>             System.out.println("m1 == m2");
>         } else {
>             System.out.println("m1 != m2"); // m1 != m2
>         }
> 
>         if (m1.equals(m2)) {
>             System.out.println("m1 is equal to m2");    // m1 is equal to m2
>         } else {
>             System.out.println("m1 is not equal to m2");
>         }
>     }
> }
> ```
> 
> 在 `MyDate` 类中覆盖 `equals()` 方法，使其判断当两个 `MyDate` 类型对象的年月日都相同时，结果为 `true`，否则为 `false`。其方法声明为：`public boolean equals(Object o)`。

示例代码：
```java
/* MyDate.java */

package com.anxin_hitsz_07.object.equals.exer2;

/**
 * ClassName: MyDate
 * Package: com.anxin_hitsz_07.object.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:30
 * @Version 1.0
 */
public class MyDate {
    private int day;
    private int month;
    private int year;

    public MyDate() {
    }

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // 手写 equals()：
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof MyDate) {
            MyDate myDate = (MyDate) obj;

            return this.year == myDate.year && this.month == myDate.month &&
                    this.day == myDate.day;
        }
        return false;

    }
}


/* EqualsTest.java */

package com.anxin_hitsz_07.object.equals.exer2;

/**
 * ClassName: EqualsTest
 * Package: com.anxin_hitsz_07.object.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:31
 * @Version 1.0
 */
public class EqualsTest {
    public static void main(String[] args) {
        MyDate m1 = new MyDate(14, 3, 1976);
        MyDate m2 = new MyDate(14, 3, 1976);
        if (m1 == m2) {
            System.out.println("m1==m2");
        } else {
            System.out.println("m1!=m2"); // m1 != m2
        }

        if (m1.equals(m2)) {
            System.out.println("m1 is equal to m2");// m1 is equal to m2
        } else {
            System.out.println("m1 is not equal to m2");
        }
    }
}

```

#### 8.2.2 （重点）`toString()`

##### 8.2.2.1 `toString()` 的定义及使用

方法签名：`public String toString()`。

`Object` 类中 `toString()` 方法的定义：
```java
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

因此，默认情况下，`toString()` 返回的是 “`对象的运行时类型@对象的 hashCode 值的十六进制形式`”。

在进行 `String` 与其它类型数据的连接操作时，自动调用 `toString()` 方法。示例如下：
```java
Date now = new Date();
System.out.println("now = " + now); // 相当于
System.out.println("now = " + now.toString());
```

开发中的使用场景：
* 平时我们在调用 `System.out.println()` 打印对象引用变量时，其实就调用了对象的 `toString()`。

如果我们直接 `System.out.println(对象)`，默认会自动调用这个对象的 `toString()`。
> 因为 Java 的引用数据类型的变量中存储的实际上是对象的内存地址，但是 Java 对程序员隐藏内存地址信息，所以不能直接将内存地址显示出来；所以当你打印对象时，JVM 帮你调用了对象的 `toString()`。

子类使用说明：
* 自定义的类，在没有重写 `Object` 类的 `toString()` 方法的情况下，默认返回的是当前对象的地址值。
* 像 `String`、`File`、`Date` 或包装类等 `Object` 的子类，它们都重写了 `Object` 类的 `toString()` 方法，在调用 `toString()` 方法时，返回当前对象的实体内容。

开发中使用说明：
* 习惯上，开发中对于自定义的类在调用 `toString()` 方法时，也希望显示其对象的实体内容，而非地址值；这时候，就需要重写 `Object` 类中的 `toString()`。

可以根据需要在用户自定义类型中重写 `toString()` 方法。
* 如 `String` 类重写了 `toString()` 方法，返回字符串的值：
    ```java
    s1 = "hello";
    System.out.println(s1); // 相当于 System.out.println(s1.toString());
    ```

例如自定义的 `Person` 类：
```java
public class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
```

> 注意：
>
> 在 IDAE 中，通过 `Alt + Insert` 组合键可调出 Generate 面板实现 `toString()` 方法的快速自动重写。

重写示例：
```java
/* ToStringTest.java */

package com.anxin_hitsz_07.object.toString;

import java.io.File;
import java.util.Date;

/**
 * ClassName: ToStringTest
 * Package: com.anxin_hitsz_07.object.toString
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:31
 * @Version 1.0
 */
public class ToStringTest {
    public static void main(String[] args) {
        User u1 = new User("Tom", 12);
        System.out.println(u1.toString());  // com.anxin_hitsz_07.object.toString.User@8efb846
        System.out.println(u1); // com.anxin_hitsz_07.object.toString.User@8efb846

        String s1 = new String("hello");
        System.out.println(s1.toString());  // hello

        File file = new File("d:\\abc.txt");
        System.out.println(file);   // d:\abc.txt

        Date date = new Date();
        System.out.println(date);   // Wed Jan 28 16:42:38 CST 2026

    }
}

class User {

    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 手动实现 toString()：
//    @Override
//    public String toString() {
//        return "User{ name = " + name + ", age = " + age + " }";
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

##### 8.2.2.2 练习

练习：
> 题目：
>
> 定义两个类，父类 `GeometricObject` 代表几何形状，子类 `Circle` 代表圆形。其 UML 图如下所示：
> ![练习 - UML](./images/toString_exer_UML.png "练习 - UML")
>
> 写一个测试类，创建两个 `Circle` 对象，判断其颜色是否相等；利用 `equals()` 方法判断其半径是否相等，并利用 `toString()` 方法输出其半径。

示例代码：
```java
/* GeometricObject.java */

package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: GeometricObject
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:50
 * @Version 1.0
 */
public class GeometricObject {
    protected String color;
    protected double weight;

    public GeometricObject() {
        color = "white";
        weight = 1.0;
    }

    public GeometricObject(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}


/* Circle.java */

package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:52
 * @Version 1.0
 */
public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
//        color = "white";
//        weight = 1.0;
        radius = 1.0;
    }

    public Circle(double radius) {
//        color = "white";
//        weight = 1.0;
        this.radius = radius;
    }

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
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

    // 重写 equals()
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Circle) {
            Circle c = (Circle) obj;
            return this.radius == c.radius;
        }
        return false;
    }

    // 重写 toString()

    @Override
    public String toString() {
        return "Circle[radius = " + radius + "]";
    }
}


/* CircleTest.java */

package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: CircleTest
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:57
 * @Version 1.0
 */
public class CircleTest {
    public static void main(String[] args) {

        Circle c1 = new Circle(2.3);

        Circle c2 = new Circle("red", 2.0, 3.4);

        System.out.println("颜色是否相等？" + c1.getColor().equals(c2.getColor()));

        System.out.println("半径是否相等？" + c1.equals(c2));

        System.out.println(c1);
        System.out.println(c1.toString());

    }
}

```

#### 8.2.3 `clone()`

`clone()` 方法将会创建并返回当前对象的一个“复制品”。

> 注意：
> 
> 复制的并不是地址，而是重新造的对象。
>
> 即：`x.clone() != x`。

示例代码：
```java
/* CloneTest.java */

package com.anxin_hitsz_07.object;
//Object类的clone()的使用
public class CloneTest {
	public static void main(String[] args) {
		Animal a1 = new Animal("小花");
		try {
			Animal a2 = (Animal) a1.clone();
			a2.setName("毛毛");

			System.out.println("原始对象：" + a1);
			System.out.println("a1[name = " + a1.getName() + "]");
			System.out.println("clone之后的对象：" + a2);
			System.out.println("a2[name = " + a2.getName() + "]");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}

class Animal implements Cloneable{
	private String name;

	public Animal() {
		super();
	}

	public Animal(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

```

#### 8.2.4 `finalize()`

当对象被回收时，系统自动调用该对象的 `finalize()` 方法。（不是垃圾回收器调用的，是本类对象调用的。）
* 永远不要主动调用某个对象的 `finalize()` 方法，应该交给垃圾回收机制调用。

什么时候被回收：当某个对象没有任何引用时，JVM 就认为这个对象是垃圾对象，就会在之后不确定的时间使用垃圾回收机制来销毁该对象；在销毁该对象前，会先调用 `finalize()` 方法。

子类可以重写该方法，目的是在对象被清理之前执行必要的清理操作。比如，在方法内断开相关连接资源。
* 如果重写该方法，让一个新的引用变量重新引用该对象，则会重新激活对象。

在 JDK 9 中此方法已经被**标记为过时**的。

示例代码：
```java
/* FinalizeTest.java */

package com.anxin_hitsz_07.object;

public class FinalizeTest {
	public static void main(String[] args) {
		Person p = new Person("Peter", 12);
		System.out.println(p);
		p = null;//此时对象实体就是垃圾对象，等待被回收。但时间不确定。
		System.gc();//强制性释放空间

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}

class Person{
	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
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
	//此方法调用的时机：当GC要回收此对象时，调用如下的方法：
	//子类重写此方法，可在释放对象前进行某些操作
	//finalize()可能导致内部出现循环引用，导致此对象不能被回收。
	@Override
	protected void finalize() throws Throwable {
		System.out.println("对象被释放--->" + this);
	}
}

```

> 面试题：
> 
> `final`、`finally`、`finalize` 的区别？

#### 8.2.5 `getClass()`

`public final Class<?> getClass()`：获取对象的运行时类型。
> 因为 Java 有多态现象，所以一个引用数据类型的变量的编译时类型与运行时类型可能不一致，因此如果需要查看这个变量实际指向的对象的类型，需要用 `getClass()` 方法。

示例代码：
```java
public static void main(String[] args) {
    Object obj = new Person();
    System.out.println(obj.getClass()); // 运行时类型
}

```

输出结果：
```java
class com.atguigu.java.Person
```

#### 8.2.6 `hashCode()`

`public int hashCode()`：返回每个对象的 `hash` 值。（后续在集合框架章节重点讲解。）

示例代码：
```java
public static void main(String[] args) {
    System.out.println("AA".hashCode());    // 2080
    System.out.println("BB".hashCode());    // 2112
}
```

### 8.3 `native` 关键字的理解

使用 `native` 关键字说明这个方法是原生函数，也就是这个方法是用 C / C++ 等非 Java 语言实现的，并且**被编译成了 DLL**，由 Java 去调用。
* 本地方法是有方法体的，用 C 语言编写。由于本地方法的方法体源码没有对我们开源，所以我们看不到方法体。
* 在 Java 中定义一个 `native` 方法时，并不提供实现体。

**1. 为什么要用 `native` 方法？**

Java 使用起来非常方便，然而有些层次的任务用 Java 实现起来不容易，或者我们对程序的效率很在意时；例如 Java 需要与一些底层操作系统或某些硬件交换信息时的情况。

`native` 方法正式这样一种交流机制：它为我们提供了一个非常简洁的接口，而且我们无需去了解 Java 应用之外的繁琐的细节。

**2. `native` 声明的方法，对于调用者，可以当做和其他 Java 方法一样使用。**

`native method` 的存在并不会对其他类调用这些本地方法产生任何影响；实际上调用这些方法的其他类甚至不知道它所调用的是一个本地方法。JVM 将控制调用本地方法的所有细节。
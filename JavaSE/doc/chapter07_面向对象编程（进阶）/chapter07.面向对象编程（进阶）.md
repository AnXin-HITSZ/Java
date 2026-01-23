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
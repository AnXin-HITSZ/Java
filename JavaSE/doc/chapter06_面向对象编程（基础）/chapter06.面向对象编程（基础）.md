# 第六章：面向对象编程（基础）

**目录：**

[TOC]

---

本章专题与脉络：
![第 2 阶段：面向对象编程 - 第 06 章](./images/20251203193538.png "第 2 阶段：面向对象编程 - 第 06 章")

学习面向对象内容的三条主线：
* Java 类及类的成员：（重点）属性、方法、构造器；（熟悉）代码块、内部类；
* 面向对象的特征：封装、继承、多态（、抽象）；
* 其他关键字的使用：`this`、`super`、`package`、`import`、`static`、`final`、`interface`、`abstract` 等。

## 一、面向对象编程概述（了解）

### 1.1 程序设计的思路

面向对象，是软件开发中的一类编程风格、开发范式。除了面向对象，还有面向过程、指令式编程和函数式编程。在所有的编程范式中，我们接触最多的还是面向过程和面向对象两种。

> 类比 - 史书类型：
> * 纪传体：以人物传记为中心，“本纪”叙述帝王、“世家”记叙王侯封国和特殊人物，“列传”记叙民间人物。
> * 编年体：按年、月、日顺序编写。
> * 国别体：是一部分国记事的历史散文，分载多国历史。

早期先有面向过程思想，随着软件规模的扩大、问题复杂性的提高，面向过程的弊端越来越明显，出现了面向对象思想并成为目前主流的方式。

1. **面向过程的程序设计思想（Process-Oriented Programming）**，简称 `POP`：
   * 关注的焦点是**过程**：过程就是操作数据的步骤。如果某个过程的实现代码重复出现，那么就可以把这个过程抽取为一个**函数**。这样就可以大大简化冗余代码，便于维护。
   * 典型的语言：C 语言。
   * 代码结构：以**函数**为组织单位。
   * 是一种“**执行者思维**”，适合解决简单问题。扩展能力差、后期维护难度较大。
2. **面向对象的程序设计思想（Object Oriented Programming）**，简称 `OOP`：
   * 关注的焦点是**类**：在计算机程序设计过程中，参照现实中事物，将事物的属性特征、行为特征抽象出来，用类来表示。
   * 典型的语言：Java、C#、C++、Python、Ruby 和 PHP 等。
   * 代码结构：以**类**为组织单位。每种事物都具备自己的**属性**和**行为 / 功能**。
   * 是一种“**设计者思维**”，适合解决复杂问题。代码扩展性强、可维护性高。

### 1.2 由实际问题考虑如何设计程序

**思考 1：如何开车？**

面向过程思想思考问题时，我们首先思考“**怎么按步骤实现？**”并将步骤对应成方法，一步一步，最终完成。这个适合**简单任务**，不需要**过多协作**的情况。针对如何开车，可以列出步骤：
![针对“如何开车”的步骤](./images/20251203195458.png "针对“如何开车”的步骤")

面向过程适合简单、不需要协作的事务，重点关注如何执行。

**思考 2：如何造车？**

造车太复杂，需要**很多协作**才能完成。此时我们思考的是“**车怎么设计？**”，而不是“怎么按特定步骤造车的问题”。这就是思维方式的转变，前者就是面向对象思想。所以，面向对象（Oriented-Object）思想更契合人的思维模式。

用面向对象思想思考“如何设计车”：
![“如何设计车” - 采用面向对象思想思考](./images/20251203195806.png "“如何设计车” - 采用面向对象思想思考")

自然地，我们就会从“车由什么组成”开始思考。发现，车由如下结构组成：
![“车由什么组成”](./images/20251203195920.png "“车由什么组成”")

我们找轮胎厂完成制造轮胎的步骤，发动机厂完成制造发动机的步骤，……；这样，大家可以同时进行车的制造，最终进行组装，大大提高了效率。但是，具体到轮胎厂的一个流水线操作，仍然是有步骤的，还是离不开面向过程思维！

因此，**面向对象可以帮助我们从宏观上把握、从整体上分析整个系统。**但是，具体到实现部分的微观操作（就是一个个方法），仍然需要面向过程的思路去处理。

> 注意：我们千万不要把面向过程和面向对象对立起来。它们是相辅相成的。面向对象离不开面向过程！

**类比举例 1：**
![事务处理 - 创业中员工的从少到多](./images/20251203203604.png "事务处理 - 创业中员工的从少到多")
> 当需求单一，或者简单时，我们一步步去操作没问题，并且效率也挺高。
>
> 可随着需求的更改、功能的增多，发现需要面对每一个步骤很麻烦了，这时就开始思索，**能不能把这些步骤和功能进行封装，封装时根据不同的功能，进行不同的封装，功能类似的封装在一起。**这样结构就清晰了很多。用的时候，找到对应的类就可以了。这就是面向对象的思想。

**类比举例 2 - 人把大象装进冰箱：**
* 面向过程：
    ```java
    1. 打开冰箱

    2. 把大象装进冰箱

    3. 把冰箱门关住

    ```
* 面向对象：
    ```java
    人{
        打开(冰箱){
            冰箱.开门();
        }
        操作(大象){
            大象.进入(冰箱);
        }
        关闭(冰箱){
            冰箱.关门();
        }
    }

    冰箱{
        开门(){ }
        关门(){ }
    }

    大象{
        进入(冰箱){ }
    }

    ```

练习：抽象出下面系统中的“类”及其关系。
![练习 - 抽象出该系统中的“类”及其关系](./images/20251203210031.png "练习 - 抽象出该系统中的“类”及其关系")

## 二、Java 语言的基本元素：类和对象

### 2.1 引入

人认识世界，其实就是面向对象的。比如，我们认识一下美人鱼（都没见过）：
![美人鱼](./images/20251203200715.png "美人鱼")

经过“仔细学习”，发现美人鱼通常具备一些特征：
* 女孩；
* 有鱼尾；
* 美丽。

这个总结的过程，其实是**抽象化**的过程。抽象出来的美人鱼的特征，可以归纳为一个**美人鱼类**。而图片中的都是这个类呈现出来的**具体的对象**。

### 2.2 类和对象概述

**类（Class）**和**对象（Object）**是面向对象的核心概念。

**1. 什么是类？**
**类**：具有相同特征的事物的抽象描述，是**抽象的**、概念上的定义。

**2. 什么是对象？**
**对象**：实际存在的该类事物的**每个个体**，是**具体的**，因而也称为**实例（instance）**。

![“抽象概念的人”和“实实在在的某个人”](20251203201147.png "“抽象概念的人”和“实实在在的某个人”")
可以理解为：`类 => 抽象概念的人` 而 `对象 => 实实在在的某个人`。

![“手机的设计图”和“真正的手机”](./images/20251203201342.png "“手机的设计图”和“真正的手机”")

![“车”和“我的爱车”](./images/20251203201423.png "“车”和“我的爱车”")

**3. 类与对象的关系错误理解**

> 曰：“白马非马，可乎？”
> 曰：“可。”
> 曰：“何哉？”
> 曰：“马者，所以命形也。白者，所以命色也。命色者，非命形也，故曰白马非马。”

![“白马非马”](./images/20251203201755.png "“白马非马”")

### 2.3 类的成员概述

> 面向对象程序设计的重点是**类的设计**。
>
> 类的设计，其实就是**类的成员的设计**。

现实世界的生物体，大到鲸鱼，小到蚂蚁，都是由最基本的**细胞**构成的。同理，Java 代码世界是由诸多个不同功能的**类**构成的。
![细胞](./images/20251203202044.png "细胞")

现实生物世界中的细胞又是由什么构成的呢？细胞核、细胞质、……
Java 中用类 class 来描述事物也是如此。类，是一组相关**属性**和**行为**的集合，这也是类最基本的两个成员。
* **属性**：该类事物的状态信息。对应类中的**成员变量**。
  * `成员变量 <=> 属性 <=> Field（字段、域）`。
* **行为**：该类事物要做什么操作，或者基于事物的状态能做什么。对应类中的**成员方法**。
  * `（成员）方法 <=> 函数（、过程） <=> Method`。

![简历中的“属性”与“行为”](./images/20251203202439.png "简历中的“属性”与“行为”")

举例：
![具体代码中的属性和方法示例](/images/0251203202548.png "具体代码中的属性和方法示例")

### 2.4 面向对象完成功能的三步骤（重要）

**步骤 1：类的定义**

类的定义使用关键字：`class`。格式如下：
```java
[修饰符] class 类名{
    属性声明;
    方法声明;
}
```

**步骤 2：对象的创建**

![对象的创建](./images/20251205102939.png "对象的创建")

创建对象，使用关键字：`new`。

创建对象语法：
```java
// 方式 1：给创建的对象命名
// 把创建的对象用一个引用数据类型的变量保存起来，这样就可以反复使用这个对象了
类名 对象名 = new 类名();

// 方式 2：
new 类名(); // 也称为匿名对象
```

**步骤 3：对象调用属性或方法**

对象是类的一个**实例**，必然具备该类事物的属性和行为（即方法）。

使用 “`对象名.属性`” 或 “`对象名.方法`” 的方式访问对象成员（包括属性和方法）。

示例代码：
```java
/* Phone.java */

public class Phone {

    // 属性
    String name;    // 品牌
    double price;   // 价格


    // 方法
    public void call(){
        System.out.println("手机能够拨打电话");
    }

    public void sendMessage(String message){
        System.out.println("发送信息：" + message);
    }

    public void playGame(){
        System.out.println("手机可以玩游戏");
    }
}


/* PhoneTest.java */

public class PhoneTest {    // 是 Phone 类的测试类
    public static void main(String[] args) {

        // 复习：数据类型 变量名 = 变量值
//        Scanner scann = new Scanner(System.in);

        // 创建 Phone 的对象
        Phone p1 = new Phone();

        // 通过 Phone 的对象，调用其内部声明的属性或方法
        // 格式："对象.属性" 或 "对象.方法"
        p1.name = "huawei mate50";
        p1.price = 6999;

        System.out.println("name = " + p1.name + "，price = " + p1.price);

        // 调用方法
        p1.call();
        p1.sendMessage("有内鬼，终止交易");
        p1.playGame();
    }
}

```

### 2.5 匿名对象（anonymous object）

我们也可以不定义对象的句柄，而直接调用这个对象的方法。这样的对象叫做匿名对象。
  * 如：`new Person().shout();`。

使用情况：
* 如果一个对象只需要进行一次方法调用，那么就可以使用匿名对象。
* 我们经常将匿名对象作为实参传递给一个方法调用。

## 三、对象的内存解析

### 3.1 JVM 内存结构划分

HotSpot（Oracle 自己发布的 Java 虚拟机，即官方发布的虚拟机） Java 虚拟机的架构图如下。其中我们主要关心的是运行时数据区部分（Runtime Data Area）。
![HotSpot Java 虚拟机架构图](./images/20251205103829.png "HotSpot Java 虚拟机架构图")

其中：
* **堆（Heap）**：此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。这一点在 Java 虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配。
  * 即：`new` 出来的结构（比如：数组实体、对象的实体），包括对象中的属性。
* **栈（Stack）**：是指虚拟机栈。虚拟机栈用于存储局部变量等。局部变量表存放了编译器可知长度的各种基本数据类型（`boolean`、`byte`、`char`、`short`、`int`、`float`、`long`、`double`）、对象引用（`reference` 类型，它不等同于对象本身，是对象在堆内存的首地址）。方法执行完，自动释放。
  * 即：方法内定义的变量，存储在栈中。
* **方法区（Method Area）**：用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
  * 即：存放类的模板，比如 `Person` 类的模板。

### 3.2 对象的内存解析

举例：
```java
class Person {  // 类：人
    String name;
    int age;
    boolean isMale;
}

public static PersonTest {  // 测试类
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "赵同学";
        p1.age = 20;
        p1.isMale = true;

        Person p2 = new Person();
        p2.age = 10;

        Person p3 = p1;
        p3.name = "郭同学";
    }
}

```
内存解析图：
![内存解析图](./images/20251205104733.png "内存解析图")

> 说明：
> * 堆：凡是 `new` 出来的结构（对象、数组）都存放在堆空间中。
> * 对象的属性存放在堆空间中。
> * 创建一个类的多个对象（比如 `p1`、`p2`），则每个对象都拥有当前类的一套“副本”（即属性）。当通过一个对象修改其属性时，不会影响其他对象此属性的值。
> * 当声明一个新的变量使用现有的对象进行赋值时（比如 `p3 = p1`），此时并没有在堆空间中创建新的对象，而是两个变量共同指向了堆空间中同一个对象；当通过一个对象修改属性时，会影响另外一个对象对此属性的调用。

**1. 创建类的一个对象，属性赋值：**

示例代码：
```java
public static void main(String[] args) {
    Person p1 = new Person();
    p1.name = "杰克";
    p1.age = 24;
    p1.gender = '男';
}
```

内存解析：
![示例代码内存解析](./images/20251205114058.png "示例代码内存解析")

**2. 创建类的多个对象，属性赋值：**

示例代码 1：
```java
public static void main(String[] args) {
    Person p1 = new Person();
    p1.name = "杰克";
    p1.age = 24;

    // 创建 Person 的对象 2
    Person p2 = new Person();
    p2.name = "露丝";
    p2.age = 20;

    p1.age = 26;
    System.out.println(p2.age); // 20
}
```
内存解析 1：
![示例代码 1 内存解析](./images/20251205114438.png "示例代码 1 内存解析")

示例代码 2：
```java
public static void main(String[] args) {
    Person p1 = new Person();
    p1.name = "杰克";
    p1.age = 24;

    // 创建 Person 的对象 2
    Person p2 = new Person();
    p2.name = "露丝";
    p2.age = 20;

    p1.age = 26;
    System.out.println(p2.age); // 20

    Person p3 = p1;
    p3.age = 28;
}
```
内存解析 2：
![示例代码 2 内存解析](./images/20251205115508.png "示例代码 2 内存解析")

强调：
**1. 创建了 `Person` 类的两个对象：**

```java
Person p1 = new Person();
Person p2 = new Person();
```
说明：创建类的多个对象时，每个对象在堆空间中有一个对象实体，每个对象实体中保存着一份类的属性；如果修改某一个对象的某属性值时，不会影响其他对象此属性的值。

示例代码：
```java
p1.age = 10;
p2.age = 20;

p1.age = 30;
System.out.println(p2.age); // 20
```
**2. 声明类的两个变量：**

```java
Person p1 = new Person();
Person p3 = p1;
```
说明：此时的 `p1`、`p3` 两个变量指向了堆空间中的同一个对象实体（或 `p1`、`p3` 保存的地址值相同）；如果通过其中某一个对象变量修改对象的属性时，会影响另一个对象变量此属性的值。

示例代码：
```java
p1.age = 10;
p3.age = 20;
System.out.println(p1.age); // 20
```

> **面试题：对象名中存储的是什么呢？**
>
> 答：对象地址。
>
> ```java
> public class StudentTest{
>     public static void main(String[] args){
>         System.out.println(new Student());  // Student@7852e922
> 
>         Student stu = new Student();
>         System.out.println(stu);    // Student@4e25154f
> 
>         int[] arr = new int[5];
>         System.out.println(arr);    // [I@70dea4e
>     }
> }
> 
> ```
> 直接打印对象名和数组名都是显示 “`类型@对象的 hashCode 值`”，所以说**类、数组都是引用数据类型，引用数据类型的变量中存储的是对象的地址，或者说指向堆中对象的首地址。**
> ![类、数组的内存解析](./images/20251205105827.png "类、数组的内存解析")

## 四、类的成员之一：成员变量（field）

### 4.1 如何声明成员变量

语法格式：
```java
[修饰符 1] class 类名{
    [修饰符 2] 数据类型 成员变量名 [= 初始化值];
}
```

说明：
* 位置要求：必须在类中，方法外。
* 修饰符 2（暂不考虑）：
  * 常用的权限修饰符有：`private`、缺省、`protected`、`public`（用于表明所修饰的结构可调用的范围的大小）；
  * 其他修饰符：`static`、`final`。
* 数据类型：
  * 任何基本数据类型（如 `int`、`Boolean`）或任何引用数据类型。
* 成员变量名：
  * 属于标识符，符合命名规则和规范即可。
* 初始化值：
  * 根据情况，可以显式赋值；也可以不赋值，使用默认值。

### 4.2 成员变量 vs 局部变量

#### 4.2.1 变量的分类：成员变量与局部变量

在方法体外、类体内声明的变量称为成员变量；在方法体内部（、方法形参、构造器内、构造器形参、代码块内）等位置声明的变量称为局部变量。

> 属性的几个称谓：成员变量、属性、field（字段、域）。

示例图：
![成员变量与局部变量示例图](./images/20251205194636.png "成员变量与局部变量示例图")

所有变量的分类结构图：
![所有变量的分类结构图](./images/20251205194731.png "所有变量的分类结构图")

> 其中，`static` 可以将成员变量分为两大类，分别为静态变量和非静态变量。其中静态变量又称为类变量，非静态变量又称为实例变量或者属性。接下来先学习实例变量。

#### 4.2.2 成员变量与局部变量的对比

相同点：
* 变量声明的格式相同：`数据类型 变量名 = 初始化值`；
* 变量必须先声明、后初始化、再使用；
* 变量都有其对应的作用域，只在其作用域内是有效的。

不同点：
* 声明位置和方式：
  * 实例变量：在类中方法外。
  * 局部变量：在方法体 `{}` 中、构造器内部或方法的形参列表、代码块中。
* 在内存中存储的位置不同：
  * 实例变量：随着对象的创建，存储在堆空间中。
  * 局部变量：存储在栈空间中。
* 生命周期：
  * 实例变量：和对象的生命周期一样，随着对象的创建而存在，随着对象被 GC 回收而消亡，而且每一个对象的实例变量是独立的；
    * 即：随着对象的创建而创建，随着对象的消亡而消亡。
  * 局部变量：和方法调用的生命周期一样，每一次方法被调用而存在，随着方法执行的结束而消亡，而且每一次方法调用都是独立；
    * 即：随着方法对应的栈帧入栈，局部变量会在栈中分配；随着方法对应的栈帧出栈，局部变量消亡。
* 作用域：
  * 实例变量：通过对象就可以使用，本类中直接调用，其他类中通过 “`对象.实例变量`” 调用；
    * 即：在整个类的内部都是有效的。
  * 局部变量：出了作用域就不能使用；
    * 即：仅限于声明此局部变量所在的方法（或构造器、代码块）中。
* 是否可以有权限修饰符进行修饰：
  * 实例变量：`public`、`protected`、`private`、`final`、`volatile`、`transient` 等；
    * 即：属性是可以使用权限修饰符进行修饰的。
  * 局部变量：`final`；
    * 即：局部变量不能使用任何权限修饰符进行修饰的。
* 是否有默认值：
  * 实例变量：都有默认初始化值；
    * 即：意味着，如果没有给属性进行显式初始化赋值，则会有默认初始化值。
  * 局部变量：都没有默认初始化值，必须手动初始化；其中的形参比较特殊，靠实参给它初始化；
    * 即：意味着，在使用局部变量之前，必须要显式的赋值，否则报错。

> 注意：对于方法的形参而言，在调用方法时，给此形参赋值即可。

#### 4.2.3 对象属性的默认初始化赋值

当一个对象被创建时，会对其中各种类型的成员变量自动进行初始化赋值。
![对象属性的默认初始化赋值情况表](./images/20251205200100.png "对象属性的默认初始化赋值情况表")

#### 4.2.4 举例

**举例 1：**

示例代码：
```java
class Person {  // 人类
    // 1. 属性
    String name;    // 姓名
    int age = 1;    // 年龄
    boolean isMale; // 是否是男性

    public void show(String nation) {
        // nation：局部变量
        String color;   // color：局部变量
        color = "yellow";
    }
}

// 测试类
class PersonTest {
    public static void main(String[] args) {
        Person p = new Person();
        p.show("CHN");
    }
}

```

上述示例代码对应内存解析如下图所示：
![示例代码对应内存解析示意图](./images/20251205200602.png "示例代码对应内存解析示意图")

**举例 2：**
> 声明员工类 `Employee`，包含属性、编号 `id`、姓名 `name`、年龄 `age`、薪资 `salary`。
>
> 声明 `EmployeeTest` 测试类，并在 `main` 方法中，创建 2 个员工变量，并为属性赋值，并打印两个员工的信息。

示例代码：
```java
/* Employee.java */

public class Employee {

    // 属性（或成员变量）
    int id; // 编号
    String name;    // 姓名
    int age;    // 年龄
    double salary;  // 薪资


}


/* EmployeeTest.java */

public class EmployeeTest {
    public static void main(String[] args) {
        // 创建类的实例（或 创建类的对象、类的实例化）
        Employee emp1 = new Employee();

        System.out.println(emp1);   // 类型@地址值

        emp1.id = 1001;
        emp1.name = "Tom";
        emp1.age = 24;
        emp1.salary = 7800;

        System.out.println("id = " + emp1.id + "，name = " + emp1.name +
                "，age = " + emp1.age + "，salary = " + emp1.salary);

        // 创建 Employee 的第 2 个对象
//        Employee emp3 = emp1;   // 错误写法
        Employee emp2 = new Employee();

        System.out.println("id = " + emp2.id + "，name = " + emp2.name +
                "，age = " + emp2.age + "，salary = " + emp2.salary);

    }
}

```

**举例 3：**
> 1. 声明一个 `MyDate` 类型，有属性：年 `year`、月 `month`、日 `day`；
> 2. 声明一个 `Employee` 类型，包含属性：编号、姓名、年龄、薪资、生日（`Mydate` 类型）；
> 3. 在 `EmployeeTest` 测试类中的 `main()` 中，创建两个员工对象，并为他们的姓名和生日赋值，并显示。

示例代码：
```java
/* MyDate.java */

public class MyDate {
    int year;   // 年
    int month;  // 月
    int day;    // 日
}


/* Employee.java */

public class Employee {

    int id; // 编号
    String name;    // 姓名
    int age;    // 年龄
    double salary;  // 薪资
    MyDate birthday;    // 生日
}


/* EmployeeTest.java */

public class EmployeeTest {
    public static void main(String[] args) {

        // 创建 Employee 的一个实例
        Employee emp1 = new Employee();

        emp1.id = 1001;
        emp1.name = "杰克";   // emp1.name = new String("杰克");
        emp1.age = 24;
        emp1.salary = 8900;
        emp1.birthday = new MyDate();
        emp1.birthday.year = 1998;
        emp1.birthday.month = 2;
        emp1.birthday.day = 28;
        /*
        另一种写法：
        * MyDate mydate1 = new MyDate();
        * emp1.birthday = mydate1;
        * */

        // 打印员工信息
        System.out.println("id = " + emp1.id + "，name = " + emp1.name +
                "，age = " + emp1.age + "，salary = " + emp1.salary +
                "，birthday = [" + emp1.birthday.year + " 年 " + emp1.birthday.month +
                " 月 " + emp1.birthday.day + " 日]");

    }
}

```

## 五、类的成员之二：方法（method）

### 5.1 方法的引入

![《街霸》与《超级玛丽》](./images/20251207105019.png "《街霸》与《超级玛丽》")

《街霸》游戏中，每次人物出拳、出脚或跳跃等动作都需要编写 50 - 80 行的代码，在每次出拳、出脚或跳跃的地方都需要重复地编写这 50 - 80 行代码，这样程序会变得**很臃肿**，可读性也非常差。为了解决代码重复编写的问题，可以将出拳、出脚或跳跃的代码提取出来放在一个 `{}` 中，并为这段代码起个名字，这样在每次的出拳、出脚或跳跃的地方通过这个名字来调用这个 `{}` 的代码就可以了。

上述过程中，所提取出来的代码可以被看作是程序中定义的一个方法，程序在需要出拳、出脚或跳跃时调用该方法即可。

### 5.2 方法（method、函数）的理解

**方法**是类或对象行为特征的抽象，用来完成某个功能操作。在某些语言中也称为**函数**或**过程**。

将功能封装为方法的目的是：可以**实现代码重用，减少冗余，简化代码**。

Java 里的方法**不能独立存在**，所有的方法必须定义在类里。

举例 1：
* `Math.random()` 的 `random()` 方法；
* `Math.sqrt(x)` 的 `sqrt(x)` 方法；
* `System.out.println(x)` 的 `println(x)` 方法；
* `new Scanner(System.in).nextInt()` 的 `nextInt()` 方法；
* `Arrays` 类中的 `binarySearch()` 方法、`sort()` 方法、`equals()` 方法。

举例 2：
```java
public class MethodTest {
    public static void main(String[] args) {

    }
}

class Person{

    // 属性
    String name;
    int age;
    char gender;

    // 方法
    public void eat(){
        System.out.println("人吃饭");
    }

    public void sleep(int hour){
        System.out.println("人至少每天睡眠 " + hour + " 小时");
    }

    public String interests(String hobby){
        String info = "我的爱好是" + hobby;
        System.out.println(info);
        return info;
    }

    public int getAge(){
        return age;
    }
}

```

### 5.3 如何声明方法

#### 5.3.1 声明方法的语法格式

声明方法的语法格式如下所示：
```java
权限修饰符 [其他修饰符] 返回值类型 方法名([形参列表]) [throws 异常列表]{  // 方法头
  方法体的功能代码  // 方法体
}
```
> 注意：`[]` 中的内容不是必须的，以后再讲。

**1. 一个完整的方法 = 方法头 + 方法体：**
* 方法头就是 `[修饰符] 返回值类型 方法名([形参列表])[throws 异常列表]`，也称为**方法签名**。通常调用方法时只需要关注方法头就可以，从方法头可以看出这个方法的功能和调用格式。
* 方法体就是方法被调用后要执行的代码。对于调用者来说，不了解方法体如何实现的，并不影响方法的使用。

**2. 方法头可能包含 5 个部分：**
* 修饰符：可选的。方法的修饰符也有很多，例如 `public`、`protected`、`private`、`static`、`abstract`、`native`、`final`、`synchronized` 等，后面会一一学习。
  * 其中，权限修饰符有 `private`、缺省、`protected`、`public`；在讲封装性之前，我们先默认使用 `public` 修饰方法。
  * 其中，根据是否有 `static`，可以将方法分为静态方法和非静态方法。其中静态方法又称为类方法，非静态方法又称为实例方法。咱们在讲 `static` 前先学习实例方法。
* 返回值类型：表示方法运行的结果的数据类型，方法执行后将结果返回到调用者。
  * 无返回值，则声明 `void`。比如 `System.out.println(x)` 的 `println(x)` 方法、`Arrays` 的 `sort()`。
  * 有返回值，则声明出返回值类型（可以是任意类型）；与方法体中 `return 返回值（即返回值类型的变量或常量）` 搭配使用。比如 `Math.random()`、`new Scanner(System.in).nextInt()` 等。

> [经验] 我们在声明方法时，要不要提供返回值类型呢？
> * 根据方法具体实现的功能来决定；换句话说，具体问题具体分析。
> * 根据题目要求。

* 方法名：属于标识符，命名时遵循标识符命名规则和规范，“见名知意”。
* 形参列表：表示完成方法体功能时需要外部提供的数据列表，可以包含零个、一个或多个参数。
  * 格式：`(形参类型1 形参1, 形参类型2 形参2, ...)`。
  * 分类：无形参列表、有形参列表。
    * 无形参列表：不能省略一对 `()`；比如 `Math.random()`、`new Scanner(System.in).nextInt()`。
    * 有形参列表：根据方法调用时需要的不确定的变量的类型和个数，确定形参的类型和个数；比如 `Arrays` 类中的 `binarySearch()` 方法、`sort()` 方法、`equals()` 方法。
  * 无论是否有参数，`()` 不能省略。
  * 如果有参数，每一个参数都要指定数据类型和参数名，多个参数之间使用逗号分隔，例如：
    * 一个参数：`(数据类型 参数名)`；
    * 二个参数：`(数据类型1 参数1, 数据类型2 参数2)`。
  * 参数的类型可以是基本数据类型、引用数据类型。
* `throws` 异常列表：可选。

> [经验] 我们在声明方法时，是否需要形参列表呢？
> * 根据方法具体实现的功能来决定；换句话说，具体问题具体分析。
> * 根据题目要求。

**3. 方法体：**
* 方法体必须有 `{}` 括起来，在 `{}` 中编写完成方法功能的代码。

**4. 关于方法体中 `return` 语句的说明：**
* `return` 语句的作用是结束方法的执行，并将方法的结果返回去。
* 如果返回值类型不是 `void`，方法体中必须保证一定有 `return 返回值;` 语句，并且要求该返回值结果的类型与声明的返回值类型一致或兼容。
* 如果返回值类型为 `void` 时，方法体中可以没有 `return` 语句；如果要用 `return` 语句提前结束方法的执行，那么 `return` 后面不能跟返回值，直接写 `return;` 就可以。
* `return` 语句后面就不能再写其他代码了，否则会报错：`Unreachable code`。

> 补充：
>
> 方法的分类 - 按照是否有形参及返回值：
> ![方法的分类 - 按照是否有形参及返回值](./images/20251207111720.png "方法的分类 - 按照是否有形参及返回值")

#### 5.3.2 类比举例

![方法的类比举例](./images/20251207111810.png "方法的类比举例")

#### 5.3.3 代码示例

案例：
> 将属性测试的 exer1 中关于员工信息的输出内容放到方法中，通过调用方法显示。

示例代码：
```java
/* Employee.java */

public class Employee {

    // 属性（或成员变量）
    int id; // 编号
    String name;    // 姓名
    int age;    // 年龄
    double salary;  // 薪资

    // 定义一个方法，用于显示员工的信息
    public void show(){
        System.out.println("id = " + id + "，name = " + name +
                "，age = " + age + "，salary = " + salary);
    }

    public String show1(){
        return "id = " + id + "，name = " + name +
                "，age = " + age + "，salary = " + salary;
    }
}


/* EmployeeTest.java */

public class EmployeeTest {
    public static void main(String[] args) {
        // 创建类的实例（或 创建类的对象、类的实例化）
        Employee emp1 = new Employee();

        System.out.println(emp1);   // 类型@地址值

        emp1.id = 1001;
        emp1.name = "Tom";
        emp1.age = 24;
        emp1.salary = 7800;

//        System.out.println("id = " + emp1.id + "，name = " + emp1.name +
//                "，age = " + emp1.age + "，salary = " + emp1.salary);

        // 替换为：
        emp1.show();

//        System.out.println(emp1.show());  // 编译报错

        System.out.println(emp1.show1());   // 编译通过

        // 创建 Employee 的第 2 个对象
//        Employee emp3 = emp1;   // 错误写法
        Employee emp2 = new Employee();

//        System.out.println("id = " + emp2.id + "，name = " + emp2.name +
//                "，age = " + emp2.age + "，salary = " + emp2.salary);

        // 替换为：
        emp2.show();

    }
}

```

### 5.4 如何调用实例方法

方法通过方法名被调用，且只有被调用才会执行。

方法调用语法格式：
```java
对象.方法名([实参列表])
```

### 5.5 使用的注意点

方法使用的注意点如下：
1. 必须先声明后使用，且方法必须定义在类的内部。
2. 调用一次就执行一次，不调用不执行。
3. 方法中可以调用本类中的（其他）方法或属性。
4. 不可以在方法内部定义方法。

正确示例：
```java
类{
  方法 1(){

  }
  方法 2(){

  }
}
```
错误示例：
```java
类{
  方法 1(){
    方法 2(){ // 位置错误

    }
  }
}
```

### 5.6 关键字 `return` 的使用

`return` 在方法中的作用：
* 作用 1：结束一个方法。
* 作用 2：结束一个方法的同时，可以返回数据给方法的调用者（方法声明中如果有返回值类型，则方法内需要搭配 `return` 使用）。

注意点：在 `return` 关键字的直接后面不能声明执行语句。

### 5.7 方法调用内存分析

方法**没有被调用**的时候，都在**方法区**中的字节码文件（`.class`）中存储。

方法**被调用**的时候，需要进入到**栈内存**中运行。方法每调用一次就会在栈中有一个**入栈**动作，即给当前方法开辟一块独立的内存区域，用于存储当前方法的局部变量的值。

当方法执行结束后，会释放该内存，称为**出栈**。如果方法有返回值，就会把结果返回调用处；如果没有返回值，就直接结束，回到调用处继续执行下一条指令。

> 栈结构：先进后出，后进先出。

形参：方法在声明时，一对 `()` 内声明的一个或多个形式参数，简称为形参。
实参：方法在被调用时，实际传递给形参的变量或常量，就称为实际参数，简称实参。

对于对象调用方法的过程，示例代码如下所示：
```java
public static void main(String[] args) {
  Person p1 = new Person();
  p1.name = "杰克";
  p1.age = 24;
  p1.gender = '男';

  p1.eat();

  p1.sleep(6);
  p1.interests("编程");
}
```

内存解析：
![示例代码内存解析](./images/20251207163937.png "示例代码内存解析")

> 注意：栈中不存在 GC，只存在入栈和出栈；GC 存在于堆。

### 5.8 练习

**案例 1：**
> 1. 创建 `Person` 类的对象，设置该对象的 `name`、`age` 和 `gender` 属性。调用 `study` 方法，输出字符串“`studying`”；调用 `showAge()` 方法，返回 `age` 值；调用 `addAge(int addAge)` 方法给对象的 `age` 属性值增加 `addAge` 岁，比如 `2` 岁。
> 2. 创建第二个对象，执行上述操作，体会同一个类的不同对象之间的关系。

示例代码：
```java
/* Person.java */

public class Person {
    String name;
    int age;
    char gender;

    public void study(){
        System.out.println("studying");
    }

    public int showAge(){
        return age;
    }

    public void addAge(int addAge){
        age += addAge;
    }
}


/* PersonTest.java */

public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();

        // 调用属性
        p1.name = "Tom";
        p1.age = 12;
        p1.gender = '男';

        // 调用方法
        p1.study();

        p1.addAge(2);

        int age = p1.showAge();
        System.out.println("age = " + age); // 12 -> 14


        Person p2 = new Person();

        System.out.println(p2.age); // 0

        p2.addAge(10);

        System.out.println(p2.showAge());   // 10

        System.out.println(p1.showAge());   // 14
    }
}

```

**案例 2：**
> 1. 编写程序，声明一个 `method1` 方法，在方法中打印一个 10 * 8 的 `*` 型矩阵，在 `main` 方法中调用该方法。
> 2. 编写程序，声明一个 `method2` 方法，除打印一个 10 * 8 的 `*` 型矩阵外，再计算该矩形的面积，并将其作为方法的返回值。在 `main` 方法中调用该方法，接收返回的面积值并打印。
> 3. 编写程序，声明一个 `method3` 方法，在 `method3` 方法提供 `m` 和 `n` 两个参数，方法中打印一个 m * n 的 `*` 型矩形，并计算该矩形的面积，将其作为方法返回值。在 `main` 方法中调用该方法，接收返回的面积值并打印。

示例代码：
```java
/* Exer02.java */

public class Exer02 {

    public void method1(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public int method2(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        return 10 * 8;
    }

    public int method3(int m, int n){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        return m * n;
    }
}


/* Exer02Test.java */

public class Exer02Test {
    public static void main(String[] args) {

        // 创建 Exer02 的对象
        Exer02 exer = new Exer02();
//        exer.method1();

//        int area = exer.method2();
//        System.out.println("面积为：" + area);

        int area1 = exer.method3(3, 8);
        System.out.println("面积为：" + area1);
    }
}

```

**案例 3：**
> 利用面向对象的编程方法，设计类 `Circle` 计算圆的面积。

示例代码：
```java
/* Circle.java */

public class Circle {
    // 属性
    double radius;  // 半径

    // 方法
    public void findArea() {
        System.out.println("面积为：" + 3.14 * radius * radius);
    }
    // 或：
    public double findArea2() {
        return 3.14 * radius * radius;
    }

    // 错误的：
//    public void findArea1(double r) {
//        System.out.println("面积为：" + 3.14 * r * r);
//    }

}


/* CircleTest.java */

public class CircleTest {
    public static void main(String[] args) {
        Circle c1 = new Circle();

        c1.radius = 2.3;
        c1.findArea();

        System.out.println("面积为：" + c1.findArea2());
    }
}

```

**案例 4：**
> 根据上一章数组中常用的算法操作，自定义一个操作 `int[]` 的工具类。
>
> 涉及到的方法有：求最大值、最小值、总和、平均数、遍历数组、复制数组、数组反转、数组排序（默认从小到大排序）、查找等。

示例代码：
```java
/* MyArrays.java */

public class MyArrays {

    /**
     * 获取 int[] 数组的最大值
     * @param arr 要获取最大值的数组
     * @return 数组的最大值
     */
    public int getMax(int[] arr) {
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
    public int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public int getAvg(int[] arr) {

        return getSum(arr) / arr.length;
    }

    public void print(int[] arr) {  // [12, 231, 34, ...]
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

    public int[] copy(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1 /* 尾索引 */; i < j; i++, j--) {
            // 交互 arr[i] 与 arr[j] 位置的元素
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void sort(int[] arr) {
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
    public int linearSearch(int[] arr, int target) {
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

public class MyArraysTest {
    public static void main(String[] args) {

        MyArrays arrs = new MyArrays();
        int[] arr = new int[] {34, 56, 223, 2, 56, 24, 56, 67, 778, 45};

        // 求最大值
        System.out.println("最大值为：" + arrs.getMax(arr));
        // 求平均值
        System.out.println("平均值为：" + arrs.getAvg(arr));

        // 遍历
        arrs.print(arr);

        // 查找
        int index = arrs.linearSearch(arr, 24);
        if (index >= 0) {
            System.out.println("找到了，位置：" + index);
        } else {
            System.out.println("未找到");
        }

        // 排序
        arrs.sort(arr);
        // 遍历
        arrs.print(arr);
    }
}

```

## 六、对象数组

数组的元素可以是基本数据类型，也可以是引用数据类型。当元素是引用类型中的类时，我们称为对象数组。

### 6.1 案例

定义类 `Student`，包含三个属性：学号 `number`（`int`）、年级 `state`（`int`）、成绩 `score`（`int`）。创建 20 个学生对象，学号为 1 到 20，年级和成绩都由随机数确定。

问题一：打印出 3 年级（`state` 值为 `3`）的学生信息。

问题二：使用冒泡排序按学生成绩排序，并遍历所有学生信息。

> 提示：
> 1. 生成随机数：`Math.random()`，返回值类型为 `double`。
>   * 年级 `[1, 6]`：`(int)(Math.random() * 6 + 1);`。
>   * 分数 `[0, 100]`：`(int)(Math.random() * 101);`。
> 2. 四舍五入取整：`Math.round(double d)`，返回值类型为 `long`。

示例代码：
```java
/* Student.java */

package com.anxin_hitsz_04.example.exer5_objarr;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_04.example.exer5_objarr
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:19
 * @Version 1.0
 */
public class Student {

    // 属性
    int number; // 学号
    int state;  // 年级
    int score;  // 成绩

    // 声明一个方法，显示学生的属性信息
    public String show() {
        return "number：" + number + "，state：" +
                state + "，score：" +  score;
    }
}


/* StudentTest.java */

package com.anxin_hitsz_04.example.exer5_objarr;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_04.example.exer5_objarr
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:19
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {

        // 创建 Student[]
        Student[] students = new Student[20];    // String[] strs = new String[20];

        // 使用循环，给数组的元素赋值
        for  (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            // 给每一个学生对象的 number、state、score 属性赋值
            students[i].number = i;
            students[i].state = (int)(Math.random() * 6 + 1);
            students[i].score = (int)(Math.random() * 101);
        }
        // 需求 1：打印出 3 年级（state 值为 3）的学生信息
        for (int i = 0; i< students.length; i++) {
            if (3 == students[i].state) {
                Student stu = students[i];
//                System.out.println("number：" + stu.number + "，state：" +
//                        stu.state + "，score：" +  stu.score);

                System.out.println(stu.show());
            }
        }

        System.out.println("********************************************");


        // 需求 2 ：使用冒泡排序按学生成绩排序，并遍历所有学生信息
        // 排序前遍历
        for (int i = 0; i< students.length; i++) {
            System.out.println(students[i].show());
        }

        System.out.println("********************************************");

        for (int i = 0; i< students.length; i++) {
            for (int j = 0; j< students.length - 1 - i; j++) {
                if (students[j].score > students[j + 1].score) {
                    // 错误的，不满足实际需求！
//                    int temp = students[j].score;
//                    students[j].score = students[j + 1].score;
//                    students[j].score = temp;

                    // 正确的
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        // 排序后遍历
        for (int i = 0; i< students.length; i++) {
            System.out.println(students[i].show());
        }
    }
}

```

拓展：
> 提供封装 Student 相关操作的工具类，如何实现？

示例代码：
```java
/* Student.java */

package com.anxin_hitsz_04.example.exer5_objarr1;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_04.example.exer5_objarr
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:19
 * @Version 1.0
 */
public class Student {

    // 属性
    int number; // 学号
    int state;  // 年级
    int score;  // 成绩

    // 声明一个方法，显示学生的属性信息
    public String show() {
        return "number：" + number + "，state：" +
                state + "，score：" +  score;
    }
}


/* StudentUtil.java */

package com.anxin_hitsz_04.example.exer5_objarr1;

/**
 * ClassName: StudentUtil
 * Package: com.anxin_hitsz_04.example.exer5_objarr1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:39
 * @Version 1.0
 */
public class StudentUtil {
    /*
    * 打印出指定年级的学生信息
    * */
    public void printStudentsWithIndex(Student[] students, int state) {
        for (int i = 0; i< students.length; i++) {
            if (state == students[i].state) {
                Student stu = students[i];
                System.out.println(stu.show());
            }
        }
    }

    /*
    * 遍历指定多个学生数组
    * */
    public void printStudents(Student[] students) {
        for (int i = 0; i< students.length; i++) {
            System.out.println(students[i].show());
        }
    }

    /*
    * 针对于学生数组，按照 score属性从低到高排列
    * */
    public void sortStudents(Student[] students) {
        for (int i = 0; i< students.length; i++) {
            for (int j = 0; j< students.length - 1 - i; j++) {
                if (students[j].score > students[j + 1].score) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}


/* StudentTest.java */

package com.anxin_hitsz_04.example.exer5_objarr1;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_04.example.exer5_objarr
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:19
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {

        // 创建 Student[]
        Student[] students = new Student[20];    // String[] strs = new String[20];

        // 使用循环，给数组的元素赋值
        for  (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            // 给每一个学生对象的 number、state、score 属性赋值
            students[i].number = i;
            students[i].state = (int)(Math.random() * 6 + 1);
            students[i].score = (int)(Math.random() * 101);
        }
        // 需求 1：打印出 3 年级（state 值为 3）的学生信息
        StudentUtil util = new StudentUtil();
        util.printStudentsWithIndex(students, 3);

        System.out.println("********************************************");

        // 需求 2 ：使用冒泡排序按学生成绩排序，并遍历所有学生信息
        // 排序前遍历
        util.printStudents(students);

        System.out.println("********************************************");
        util.sortStudents(students);

        // 排序后遍历
        util.printStudents(students);
    }
}

```

内存解析：
* 示例代码：
    ```java
    public static void main(String[] args) {
        Student[] stus = new Student[3];
        for (int i = 0; i < stus.length; i++) {
            stus[i] = new Student();
        }

        stus[0].number = 1;
        stus[0].state = 5;
        stus[0].score = 78;

    }
    ```
* 内存解析：
    ![示例代码 - 内存解析示意图](./images/20260113205519.png "示例代码 - 内存解析示意图")

### 6.2 注意点

对象数组，首先要创建数组对象本身，即确定数组的长度，然后再创建每一个元素对象。如果不创建，数组的元素的默认值就是 `null`，所以很容易出现 `空指针异常 NullPointerException`。

## 七、再谈方法

### 7.1 方法的重载（overload）

#### 7.1.1 概念及特点

**方法重载**：在同一个类中，允许存在一个以上的同名方法，只要它们的参数列表不同即可；满足这样特征的多个方法，彼此之间构成方法的重载。
* 参数列表不同，意味着参数个数或参数类型的不同。

例如，`Arrays` 类中 `sort(xxx[] arr)`、`binarySearch(xxx[] arr, xxx)`、`equals(xxx[], yyy[])`。

> 总结为：“两同一不同”。
> * 两同：同一个类、相同的方法名。
> * 一不同：参数列表不同（1. 参数个数不同；2. 参数类型不同）。
>
> 注意：此处的“2. 参数类型不同”可指参数的顺序不同。

**重载的特点**：与修饰符、返回值类型无关，只看参数列表，且参数列表必须不同（参数个数或参数类型）。调用时，根据方法参数列表的不同来区别。

> 注意：方法的重载与形参的名、权限修饰符、返回值类型都没有关系。

**重载方法调用**：JVM 通过方法的参数列表，调用匹配的方法。
* 先找个数、类型最匹配的；
* 再找个数和类型可以兼容的，如果同时多个方法可以兼容将会报错。

> 注意：在同一个类中不允许定义两个相同的方法。
>
> 如何判断两个方法是相同的呢？（换句话说，编译器是如何确定调用的某个具体的方法呢？）
> * 方法名相同，且形参列表相同（形参列表相同指的是参数个数和类型都相同，与形参名没关系）。
>
> 要求：在一个类中，允许存在多个相同名字的方法，只要它们的形参列表不同即可。
>
> 编译器是如何确定调用的某个具体的方法呢？
> * 先通过方法名确定了某个或某些重载的方法，进而通过不同的形参列表，确定具体的某一个方法。

> 面试题：
>
> 问以下代码的执行结果是什么？
> ```java
> public class InterviewTest {
>     public static void main(String[] args) {
> 
>         int[] arr = new int[]{1, 2, 3};
>         System.out.println(arr);    // 地址值
> 
>         char[] arr1 = new char[]{'a', 'b', 'c', 'd', 'e'};
>         System.out.println(arr1);   // abcde
> 
>         boolean[] arr2 = new boolean[]{false, true, true};
>         System.out.println(arr2);   //地址值
> 
>     }
> }
> ```
>
> `System.out.println(arr1);` 的执行结果与其余两个 `sout` 的执行结果不同的原因在于，`System.out.println(arr1);` 对函数 `println()` 进行了重载。

#### 7.1.2 示例

示例代码：
```java
package com.anxin_hitsz_05.method_more._01overload;

/**
 * ClassName: OverloadTest
 * Package: com.anxin_hitsz_05.method_more._01overload
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/16 20:04
 * @Version 1.0
 */
public class OverloadTest {
    public static void main(String[] args) {

        OverloadTest test = new OverloadTest();

        test.add(1, 2, 3);

        test.add(10, 20);   // 优先调用 public void add(int i, int j)

        test.add(10, 20.0);

    }

    public void add(int i, int j) {
        System.out.println("1111");
    }

    public void add(double d1, double d2) {
        System.out.println("3333");
    }

    public void add(int i, int j, int k) {

    }

    public void add(String s1, String s2) {

    }

    public void add(int i, String s) {

    }

    public void add(String s, int i) {

    }

//    public void add(int m, int n) {
//        System.out.println("2222");
//    }

//    public int add(int m, int n) {
//        return m + n;
//    }
}

```

#### 7.1.3 练习

**练习 1：**
> 题目：判断与 void show(int a, char b, double c){} 构成重载的有：
> a) void show(int x, char y, double z){}
> b) int show(int a, double c, char b){}
> c) void show(int a, double c, char b){}
> d) boolean show(int c, char b){}
> e) void show(double c){}
> f) double show(int x, char y, double z){}
> g) void shows(){double c}

a) no；
b) yes；
c) yes；
d) yes；
e) yes；
f) no；
g) no。

**练习 2：**
> 题目：
>
> 编写程序，定义三个重载方法并调用。
> * 方法名为 `mOL`。
> * 三个方法分别接收一个 `int` 参数、两个 `int` 参数、一个字符串参数；分别执行平方运算并输出结果、相乘并输出结果、输出字符串信息。
> * 在主类的 `main()` 方法中分别用参数区别调用三个方法。

示例代码：
```java
package com.anxin_hitsz_05.method_more._01overload.exer;

/**
 * ClassName: OverLoadExer
 * Package: com.anxin_hitsz_05.method_more._01overload.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/16 20:18
 * @Version 1.0
 */
public class OverLoadExer {
    // 练习二：
    public void mOL(int num) {
        System.out.println(num * num);
    }
    public void mOL(int num1, int num2) {
        System.out.println(num1 * num2);
    }
    public void mOL(String message) {
        System.out.println(message);
    }

    // 练习三：
    public int max(int i, int j) {
        return (i >= j) ? i : j;
    }
    public double max(double d1, double d2) {
        return (d1 >= d2) ? d1 : d2;
    }
    public double max(double d1, double d2, double d3) {
//        double tempMax = max(d1, d2);
//        return max(tempMax, d3);

        return (max(d1, d2) > d3) ? max(d1, d2) : d3;
    }
}

```

**练习 3：**
> 题目：
>
> 定义三个重载方法 `max()`，第一个方法中求两个 `int` 值中的最大值，第二个方法求两个 `double` 值中的最大值，第三个方法求三个 `double` 值中的最大值，并分别调用三个方法。

示例代码：
```java
package com.anxin_hitsz_05.method_more._01overload.exer;

/**
 * ClassName: OverLoadExer
 * Package: com.anxin_hitsz_05.method_more._01overload.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/16 20:18
 * @Version 1.0
 */
public class OverLoadExer {
    // 练习二：
    public void mOL(int num) {
        System.out.println(num * num);
    }
    public void mOL(int num1, int num2) {
        System.out.println(num1 * num2);
    }
    public void mOL(String message) {
        System.out.println(message);
    }

    // 练习三：
    public int max(int i, int j) {
        return (i >= j) ? i : j;
    }
    public double max(double d1, double d2) {
        return (d1 >= d2) ? d1 : d2;
    }
    public double max(double d1, double d2, double d3) {
//        double tempMax = max(d1, d2);
//        return max(tempMax, d3);

        return (max(d1, d2) > d3) ? max(d1, d2) : d3;
    }
}

```

### 7.2 可变个数的形参

在 JDK 5.0 中提供了 Varargs（Variable number of arguments）机制。即当定义一个方法时，形参的类型可以确定，但是形参的个数不确定，那么可以考虑使用可变个数的形参。

#### 7.2.1 语法格式及特点

可变个数形参的格式如下：
```java
方法名(参数的类型名 ... 参数名)
```

举例：
```java
// JDK 5.0 以前，采用数组形参来定义方法，传入多个同一类型变量
public static void test(int a, String[] books);

// JDK 5.0：采用可变个数形参来定义方法，传入多个同一类型变量
public static void test(int a, String ... books);
```

特点：
1. 可变参数：方法参数部分指定类型的参数个数是可变多个：0 个、1 个或多个。
2. 可变个数形参的方法与同名的方法之间，彼此构成重载。
3. 可变参数方法的使用与方法参数部分使用数组是一致的，二者不能同时声明，否则报错。
4. 方法的参数部分有可变形参，需要放在形参声明的最后。
5. 在一个方法的形参中，最多只能声明一个可变个数的形参。

> 注意：可变个数形参的方法与同名的多个方法之间彼此构成重载，且优先调用形参个数对应的非可变个数形参的方法。

示例代码：
```java
package com.anxin_hitsz_05.method_more._02args;

/**
 * ClassName: ArgsTest
 * Package: com.anxin_hitsz_05.method_more._02args
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/16 21:02
 * @Version 1.0
 */
public class ArgsTest {

    public static void main(String[] args) {
        ArgsTest test = new ArgsTest();

        test.print();
        test.print(1);
        test.print(1, 2);

        test.print(new int[]{1, 2, 3});
//        test.print(1, 2, 3);
    }

    public void print(int ... nums) {
        System.out.println("1111");

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

//    public void print(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
//    }

    public void print(int i, int ... nums) {

    }

    // 编译不通过
//    public void print(int ... nums, int i) {
//
//    }

    public void print(int i) {
        System.out.println("2222");
    }

    public void print(int i, int j) {
        System.out.println("3333");
    }

    /*
    场景举例：
    * String sql = "update customers set name = ?,email = ? where id = ?";
    *
    * String sql1 = "update customers set name = ? where id = ?";
    *
    * public void update(String sql,String ... objs);
    *
    * */

}

```

#### 7.2.2 案例分析

> 题目：
>
> `n` 个字符串进行拼接，每一个字符串之间使用某字符进行分割，如果没有传入字符串，那么返回空字符串 `""`。

示例代码：
```java
package com.anxin_hitsz_05.method_more._02args.exer;

/**
 * ClassName: StringConCatTest
 * Package: com.anxin_hitsz_05.method_more._02args.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/16 21:20
 * @Version 1.0
 */
public class StringConCatTest {

    public static void main(String[] args) {
        StringConCatTest test = new StringConCatTest();
        String info = test.concat("-", "hello", "world");
        System.out.println(info);   // hello-world

        System.out.println(test.concat("/", "hello"));

        System.out.println(test.concat("-"));
    }

    // n 个字符串进行拼接，每一个字符串之间使用某字符进行分割，如果没有传入字符串，那么返回空字符串 ""
    public String concat(String operator, String ... strs) {
        String result = "";

        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                result += strs[i];
            } else {
                result += (operator + strs[i]);
            }
        }

        return result;
    }
}

```

### 7.3 方法的参数传递机制

#### 7.3.1 形参和实参

**形参**（formal parameter）：在定义方法时，方法名后面括号 `()` 中声明的变量称为形式参数，简称形参。

**实参**（actual parameter）：在调用方法时，方法名后面括号 `()` 中的使用的值 / 变量 / 表达式称为实际参数，简称实参。

#### 7.3.2 参数传递机制：值传递

Java 里方法的参数传递方式只有一种：**值传递**（不是引用传递）。即将实际参数值的副本（复制品）传入方法内，而参数本身不受影响。
* 形参是基本数据类型：将实参基本数据类型变量的“数据值”传递给形参。
* 形参是引用数据类型：将实参引用数据类型变量的“地址值”传递给形参。

示例代码：
```java
package com.anxin_hitsz_05.method_more._03valueTransferTest;

/**
 * ClassName: ValueTransferTest
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 10:32
 * @Version 1.0
 */
public class ValueTransferTest {
    public static void main(String[] args) {
        // 1. 基本数据的局部变量
        int m = 10;
        int n = m;  // 传递的是数据值

        System.out.println("m = " + m + ", n = " + n);

        m++;

        System.out.println("m = " + m + ", n = " + n);

        // 2. 引用数据类型的局部变量
        // 2.1 数组类型
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = arr1;  // 传递的是地址值

        arr2[0] = 10;

        System.out.println(arr1[0]);    // 10

        // 2.2 对象类型
        Order order1 = new Order();
        order1.orderId = 1001;

        Order order2 = order1;  // 传递的是地址值
        order2.orderId = 1002;

        System.out.println(order1.orderId); // 1002
    }
}

class Order {
    int orderId;
}

```

#### 7.3.3 举例

**1. 形参是基本数据类型**

**案例：**
> 题目：编写方法，交换两个整型变量的值。

示例代码：
```java

```

内存解析：
* 示例代码：
    ```java
    public static void main(String[] args) {
        // ...
        int m = 10;
        int n = 20;
        test.swap(m, n);    // test 对象提前已经创建

    }
    public void swap(int m, int n) {
        int temp = m;
        m = n;
        n = temp;

    }
    ```
* 内存解析：
    ![内存解析](./images/20260117110255.png "内存解析")

**2. 形参是引用数据类型**

**案例：**
> 题目：对一个类的两个属性的值实现换序操作。

示例代码：
```java
package com.anxin_hitsz_05.method_more._03valueTransferTest;

/**
 * ClassName: ValueTransferTest3
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 11:03
 * @Version 1.0
 */
public class ValueTransferTest3 {
    public static void main(String[] args) {
        Data data =  new Data();
        ValueTransferTest3 test = new ValueTransferTest3();
        data.m = 10;
        data.n = 20;

        System.out.println("m = " + data.m + ", n = " + data.n);

        // 操作 1：
//        int temp = data.m;
//        data.m = data.n;
//        data.n = temp;

        // 操作 2：
        test.swap(data);
        System.out.println("m = " + data.m + ", n = " + data.n);
    }

    public void swap(Data data) {
        int temp = data.m;
        data.m = data.n;
        data.n = temp;
    }
}

class Data {
    int m;
    int n;
}

```

内存解析：
* 示例代码：
    ```java
    class Test {
        public static void main(String[] args) {
            // 创建 Test 对象 test（略）
            Data data = new Data;
            data.m = 10;
            data.n = 20;
            test.swap(data);
        }
        public void swap(Data data) {
            int temp = data.m;
            data.m = data.n;
            data.n = temp;
        }
    }
    class Data {
        int m;
        int n;
    }
    ```
* 内存解析：
    ![内存解析](./images/20260117111125.png "内存解析")

#### 7.3.4 练习

**练习 1：**
> 题目：定义一个 `Circle` 类，包含一个 `double` 型的 `radius` 属性代表圆的半径，一个 `findArea()` 方法返回圆的面积。

示例代码：
```java
package com.anxin_hitsz_05.method_more._03valueTransferTest.exer1;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 11:12
 * @Version 1.0
 */
public class Circle {
    double radius;  // 半径

    public double findArea() {
        return Math.PI * radius * radius;
    }
}

```

**练习 2：**
> 题目：
> 
> 定义一个类 `PassObject`，在类中定义一个方法 `printAreas()`，该方法的定义如下：
> ```java
> public void printAreas(Circle c, int time)
> ```
>
> 在 `printAreas` 方法中打印输出 `1` 到 `time` 之间的每个整数半径值，以及对应的面积。例如，`time` 为 `5`，则输出半径 `1`、`2`、`3`、`4`、`5` 以及对应的圆的面积。
>
> 在 `main` 方法中调用 `printAreas()` 方法，调用完毕后输出当前半径值。

示例代码：
```java
package com.anxin_hitsz_05.method_more._03valueTransferTest.exer1;

/**
 * ClassName: PassObject
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 11:16
 * @Version 1.0
 */
public class PassObject {
    public static void main(String[] args) {
        PassObject obj = new PassObject();
        Circle c = new Circle();
        obj.printAreas(c, 5);

        System.out.println("now radius is: " + c.radius);
    }
    public void printAreas(Circle c, int time) {
        System.out.println("Radius\t\tArea");

        int i = 1;
        for (i = 1; i <= time; i++) {
            c.radius = i;
            System.out.println(c.radius + "\t\t\t" + c.findArea());
        }
        System.out.println();

        c.radius = i;
    }
}

```

**练习 3：**
> 题目：针对 atguigu04.example.exer4 中 `MyArrays` 类的如下方法进行改进：
>
> 数组排序，可以指明排序的方式（从小到大、从大到小）。

示例代码：
```java
/* MyArrays.java */

package com.anxin_hitsz_05.method_more._03valueTransferTest.exer2;

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
    public int getMax(int[] arr) {
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
    public int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public int getAvg(int[] arr) {

        return getSum(arr) / arr.length;
    }

    public void print(int[] arr) {  // [12, 231, 34, ...]
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

    public int[] copy(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1 /* 尾索引 */; i < j; i++, j--) {
            // 交互 arr[i] 与 arr[j] 位置的元素
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void sort(int[] arr) {
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
     * 针对于数组进行排序操作
     * @param arr 待排序的数组
     * @param sortMethod    acs: 升序     desc: 降序
     */
    public void sort(int[] arr, String sortMethod) {
        if("asc".equals(sortMethod)) {//if (sortMethod.equals("asc")) { // ascend: 升序
            for (int j = 0; j < arr.length - 1; j++) {
                for (int i = 0; i < arr.length - 1 - j; i++) {
                    if (arr[i] > arr[i + 1]) {
                        // 交互 arr[i] 和 arr[i + 1]
//                        int temp = arr[i];
//                        arr[i] = arr[i + 1];
//                        arr[i + 1] = temp;
                        // 错误的：
//                        swap(arr[i], arr[i + 1]);

                        // 正确的：
                        swap(arr, i, i + 1);
                    }
                }
            }
        } else if ("desc".equals(sortMethod)) {
            for (int j = 0; j < arr.length - 1; j++) {
                for (int i = 0; i < arr.length - 1 - j; i++) {
                    if (arr[i] < arr[i + 1]) {
                        // 交互 arr[i] 和 arr[i + 1]
//                        int temp = arr[i];
//                        arr[i] = arr[i + 1];
//                        arr[i + 1] = temp;
                        // 错误的：
//                        swap(arr[i], arr[i + 1]);

                        // 正确的：
                        swap(arr, i, i + 1);
                    }
                }
            }
        } else {
            System.out.println("你输入的排序方式有误！");
        }
    }

    public void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 使用线性查找的算法，查找指定的元素
     * @param arr 待查找的数组
     * @param target 要查找的元素
     * @return target 元素在 arr 数组中的索引位置。若未找到，则返回 -1
     */
    public int linearSearch(int[] arr, int target) {
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

package com.anxin_hitsz_05.method_more._03valueTransferTest.exer2;

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

        MyArrays arrs = new MyArrays();
        int[] arr = new int[] {34, 56, 223, 2, 56, 24, 56, 67, 778, 45};

        // 求最大值
        System.out.println("最大值为：" + arrs.getMax(arr));
        // 求平均值
        System.out.println("平均值为：" + arrs.getAvg(arr));

        // 遍历
        arrs.print(arr);

        // 查找
        int index = arrs.linearSearch(arr, 24);
        if (index >= 0) {
            System.out.println("找到了，位置：" + index);
        } else {
            System.out.println("未找到");
        }

        // 排序
//        arrs.sort(arr);
        arrs.sort(arr, "asc");
        arrs.sort(arr, "desc");
        // 遍历
        arrs.print(arr);
    }
}

```

### 7.4 递归（recursion）方法

#### 7.4.1 递归方法介绍

**递归方法调用**：方法自己调用自己的现象就称为递归。

**递归的分类**：
* 直接递归：方法自身调用自己。
    ```java
    public void methodA() {
        methodA();
    }
    ```
* 间接递归：可以理解为 `A()` 方法调用 `B()` 方法，`B()` 方法调用 `C()` 方法，`C()` 方法调用 `A()` 方法。
```java
public static void A() {
    B();
}

public static void B() {
    C();
}

public static void C() {
    A();
}
```

**说明**：
* 递归方法包含了一种**隐式的循环**。
* 递归方法会**重复执行**某段代码，但这种重复执行无须循环控制。
* 递归一定要向**已知方向**递归，否则这种递归就变成了无穷递归，停不下来，类似于**死循环**，最终发生**栈内存溢出**。

#### 7.4.2 练习

**练习 1：**
> 题目：
>
> 已知一个数列：f(20) = 1，f(21) = 4，f(n + 2) = 2 * f(n + 1) + f(n)；其中 n 是大于 0 的整数。
>
> 求 f(10) 的值。

示例代码：
```java

```

**练习 2：**
> 题目：
> 
> 已知有一个数列：f(0) = 1，f(1) = 4，f(n + 2) = 2 * f(n + 1) + f(n)；其中 n 是大于 0 的整数。
>
> 求 f(10) 的值。

示例代码：
```java

```

练习 3 - 不死神兔：
> 题目：
>
> 用递归实现不死神兔。
> 故事得从西元 1202 年说起，话说有一位意大利青年，名叫斐波那契（Fibonacci）。斐波那契在他的一部著作中提出了一个有趣的问题：假设一对刚出生的小兔一个月后就能长成大兔，再过一个月就能生下一对小兔，并且此后每个月都生一对小兔，没有发生死亡，问现有一对刚出生的兔子 2 年（24 个月）后会有多少对兔子？
>
> 示例过程如下：
> 月份          1   2   3   4   5   ...
> 兔子对数      1   1   2   3   5   ...

示例代码：
```java

```

> 【奇妙的属性】随着数列的增加，斐波那契数列前一个数与后一个数的比值越来越逼近黄金分割的数值 0.618。
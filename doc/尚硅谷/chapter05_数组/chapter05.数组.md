# 第五章：数组

**目录：**

[TOC]

---

本章专题与脉络：
![第 1 阶段：Java 基本语法 - 第 05 章](./images/第1阶段：Java基本语法-第05章.png "第 1 阶段：Java 基本语法 - 第 05 章")

## 一、数组的概述

### 1.1 为什么需要数组

需求分析 1：
需要统计某公司 50 个员工的工资情况，例如计算平均工资、找到最高工资等。利用之前所学知识，首先需要声明 50 个变量来分别记录每位员工的工资，这样会很麻烦。因此我们可以将所有的数据全部存储到一个容器中统一管理，并使用容器进行计算。

需求分析 2：
![APP 中的 item 以列表方式呈现的布局](./images/snipaste_20220317_000101.jpg "APP 中的 item 以列表方式呈现的布局")

容器的概念：
* 生活中的容器：水杯（装水等液体）、衣柜（装衣服等物品）、集装箱（装货物等）。
* 程序中的容器：将多个数据存储到一起，每个数据称为该容器的元素。

Java 中的容器包括数组、集合框架（第 12 章），其功能为可完成在内存中对多个数据的存储。

### 1.2 数组的概念

数组（Array）：是多个相同类型数据按一定顺序排列的集合，并使用一个名字命名，且通过编号的方式对这些数据进行统一管理。

数组中的概念包括数组名、下标（或角标、下角标、索引、index，即找到指定数组元素所使用的编号）、元素（即数组内部存储的多个元素）、数组的长度（即数组容器中存储的元素的个数）等，其示意图如下所示：
![数组中的概念示意图](./images/image-20220317000952499.png "数组中的概念示意图")

> 注意：数组的下标（或索引）是从 `0` 开始的。

数组的特点：
* 数组本身是引用数据类型。而数组中的元素可以是任何数据类型，包括基本数据类型和引用数据类型；例如，当一维数组中的元素又为数组时，即为二维数组。
* 创建数组对象会在内存中开辟一整块连续的空间，占据的空间的大小取决于数组的长度和数组中元素的类型。
* 数组中的元素在内存中是依次紧密排列的、有序的。
* 数组一旦初始化完成，其长度就是确定的；数组的长度一旦确定，就不能修改。
* 我们可以直接通过下标（或索引）的方式调用指定位置的元素，速度很快。
* 数组名中引用的是这块连续空间的首地址。

### 1.3 数组的分类

按照元素类型分：
* 基本数据类型元素的数组：每个元素位置存储基本数据类型的值；
* 引用数据类型元素的数组：每个元素位置存储对象（本质是存储对象的首地址）。（在面向对象部分讲解）

按照维度分：
* 一维数组：存储一组数据；
* 二维数组：存储多组数据，相当于二维表，一行代表一组数据，只是这里的二维表每一行的长度不要求一样。
![一维数组与二维数组的示意图](./images/image-20211221164709624.png "一维数组与二维数组的示意图")

### 1.4 一维数组的使用（6 个基本点）

要想了解自己对一维数组的掌握程度，可以从以下 6 个基本点进行评测：
* 数组的声明和初始化；
* 调用数组的指定元素；
* 数组的属性 `length`，表示数组的长度；
* 数组的遍历；
* 数组元素的默认初始化值；
* 一维数组的内存解析（难）。

## 二、一维数组的使用

### 2.1 一维数组的声明

一维数组声明的格式：
```java
// 推荐
元素的数据类型[] 一维数组的名称;

// 不推荐
元素的数据类型 一维数组名[];
```

示例代码：
```java
int[] arr;
int arr1[];
double[] arr2;
String[] arr3;  // 引用类型变量数组
```

数组的声明需要明确：
* 数组的维度：在 Java 中数组的符号是 `[]`，例如 `[]` 表示一维、`[][]` 表示二维。
* 数组的元素类型：即创建的数组容器可以存储什么数据类型的数据；元素的类型可以是任意的 Java 的数据类型。例如 `int`、`String`、`Student` 等。
* 数组名：就是代表某个数组的标识符；数组名其实也是变量名，按照变量的命名规范来命名。数组名是个引用数据类型的变量，因为它代表一组数据。

> 注意：
>
> Java 语言中声明数组时不能指定其长度（即数组中元素的个数）。例如：
> ```java
> int a[5];   // 非法
> ```
> 以上数组的声明方式是非法的。

### 2.2 一维数组的初始化

#### 2.2.1 静态初始化

如果数组变量的初始化和数组元素的赋值操作同时进行，那就称为静态初始化。

静态初始化本质是用静态数据（编译时已知）为数组初始化。此时数组的长度由静态数据的个数决定。

一维数组声明和静态初始化格式 1：
```java
数据类型[] 数组名 = new 数据类型[]{元素 1, 元素 2, 元素 3, ...};
```
或：
```java
数据类型[] 数组名;
数组名 = new 数据类型[]{元素 1, 元素 2, 元素 3, ...};
```

其中 `new` 为创建数组使用的关键字。因为数组本身是引用数据类型，所以要用 `new` 创建数组实体。

示例代码：
> 定义存储 1、2、3、4、5 整数的数组容器。
```java
int[] arr = new int[]{1, 2, 3, 4, 5};   // 正确

// 或：

int[] arr;
arr = new int[]{1, 2, 3, 4, 5}; //正确
```

一维数组声明和静态初始化格式 2：
```java
数据类型[] 数组名 = {元素 1, 元素 2, 元素 3, ...}   // 必须在一个语句中完成，不能分成两个语句写
```

示例代码：
> 定义存储 1、2、3、4、5 整数的数组容器。
```java
int[] arr = {1, 2, 3, 4, 5};    // 正确

int[] arr;
arr = {1, 2, 3, 4, 5};  // 错误
```

#### 2.2.2 动态初始化

数组变量的初始化和数组元素的赋值操作分开进行，即为动态初始化。

动态初始化中，只确定了元素的个数（即数组的长度），而元素值此时只是默认值，还并未真正赋自己期望的值；真正期望的数据需要后续单独一个一个赋值。

动态初始化格式：
```java
数组存储的元素的数据类型[] 数组名字 = new 数组存储的元素的数据类型[长度];
```
或：
```java
数组存储的数据类型[] 数组名字;
数组名字 = new 数组存储的数据类型[长度];
```

其中，[长度] 是指数组的长度，表示数组容器中可以最多存储多少个元素。

> 注意：数组有定长特性，长度一旦指定就不可更改；和水杯道理相同，买了一个 2 升的水杯，总容量就是 2 升是固定的。

示例代码 1 - 正确写法：
```java
int[] arr = new int[5];

int[] arr;
arr = new int[5];
```

示例代码 2 - 错误写法：
```java
int[] arr = new int[5]{1, 2, 3, 4, 5};  // 错误的；如果后面有 {} 指定元素列表，就不需要在 [] 中指定元素个数了
```

#### 2.2.3 一维数组的声明与初始化

结合一维数组的声明与静态、动态初始化方法，给出以下示例代码：
```java
public class OneArrayTest {
    public static void main(String[] args) {
        // 1. 数组的声明与初始化

        // 方式 1：
        // 1.1 声明数组
        double[] prices;
        // 1.2 数组的初始化
        // 静态初始化：数组变量的赋值与数组元素的赋值操作同时进行
        prices = new double[]{20.32, 43.21, 43.22};

//        String[] foods;
//        foods = new String[]{"拌海蜇", "龙须菜", "炝冬笋", "玉兰片"};

        // 方式 2：
        // 数组的声明和初始化
        // 动态初始化：数组变量的赋值与数组元素的赋值操作分开进行
        String[] foods = new String[4];

        // 其他正确的方式
        int arr[] = new int[4]; // 动态方式
        int[] arr1 = {1, 2, 3, 4};  // 静态方式 - 类型推断

        // 错误的方式
//        int[] arr2 = new int[3]{1, 2, 3};
//        int[3] arr3 = new int[];
    }
}

```

### 2.3 一维数组的使用

#### 2.3.1 数组的长度

数组的元素总个数即为数组的长度。

每个数组都有一个属性 `length` 指明它的长度。例如 `arr.length` 指明数组 `arr` 的长度（即元素个数）。

每个数组都具有长度，而且一旦初始化，其长度就是确定且是不可变的。

示例代码：
```java
public class OneArrayTest {
    public static void main(String[] args) {
        double[] prices;
        prices = new double[]{20.32, 43.21, 43.22};

        String[] foods = new String[4];

        // 3. 数组的长度：用来描述数组容器中容量的大小
        // 使用length 属性表示
        System.out.println(foods.length);   // 4
        System.out.println(prices.length);  // 3
    }
}

```

#### 2.3.2 数组元素的引用

**如何表示数组中的一个元素？**

每一个存储到数组的元素，都会自动地拥有一个编号，且编号从 0 开始；这个自动编号称为数组索引（index）或下标，可以通过数组的索引或下标访问到数组中的元素。

语法格式：
```java
数组名[索引 / 下标]
```

**数组的下标范围？**

Java 中数组的下标从 `[0]` 开始，下标范围是 `[0, 数组的长度 - 1]`，即 `[0, 数组名.length - 1]`。

数组元素下标可以是整型常量或整型表达式。例如 `a[3]`、`b[i]`、`c[6 * i]`。

示例代码：
```java
public class OneArrayTest {
    public static void main(String[] args) {
        double[] prices;

        String[] foods = new String[4];

        // 2. 数组元素的调用
        // 通过角标的方式，获取数组的元素
        // 角标的范围从 0 开始，到 数组的长度 - 1 结束
        prices = new double[]{20.32, 43.21, 43.22};
        System.out.println(prices[0]);
        System.out.println(prices[2]);
//        System.out.println(prices[4]);  // 报异常：ArrayIndexOutOfBoundsException

        foods[0] = "拌海蜇";
        foods[1] = "龙须菜";
        foods[2] = "炝冬笋";
        foods[3] = "玉兰片";
//        foods[4] = "酱茄子";   // 报异常：ArrayIndexOutOfBoundsException
    }
}

```

### 2.4 一维数组的遍历

将数组中的每个元素分别获取出来，就是遍历。for 循环与数组的遍历是绝配。

示例代码：
```java
public class OneArrayTest {
    public static void main(String[] args) {
        double[] prices;
        prices = new double[]{20.32, 43.21, 43.22};

        String[] foods = new String[4];
        foods[0] = "拌海蜇";
        foods[1] = "龙须菜";
        foods[2] = "炝冬笋";
        foods[3] = "玉兰片";

        // 4. 如何遍历数组？
        for (int i = 0; i < foods.length; i++) {
            System.out.println(foods[i]);
        }

        for (int i = 0; i < prices.length; i++) {
            System.out.println(prices[i]);
        }
    }
}

```

### 2.5 数组元素的默认值

数组是引用类型，当我们使用动态初始化方式创建数组时，元素值只是默认值。

对于基本数据类型而言，默认初始化值各有不同；对于引用数据类型而言，默认初始化值为 `null`（注意与 `0` 不同！）。具体如下图所示：
![不同的数组元素类型所对应的元素默认初始值的情况表](./images/1561509460135.png "不同的数组元素类型所对应的元素默认初始值的情况表")

示例代码：
```java
public class OneArrayTest1 {
    public static void main(String[] args) {
        // 5. 数组元素的默认初始化值

        // 整型数组元素的默认初始化值：0
        int[] arr1 = new int[3];
        System.out.println(arr1[0]);

        short[] arr2 = new short[4];
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }

        // 浮点型数组元素的默认初始化值：0.0
        double[] arr3 = new double[5];
        System.out.println(arr3[0]);

        // 字符型数组元素的默认初始化值：0（或理解为 '\u0000'）
        char[] arr4 = new char[4];
        System.out.println(arr4[0]);

        if (arr4[0] == 0) {
            System.out.println("1111");
        }

        if (arr4[0] == '0') {
            System.out.println("2222");
        }

        if (arr4[0] == '\u0000') {
            System.out.println("3333");
        }

        System.out.println(arr4[0] + 1);

        // boolean 型数组元素的默认初始化值：false
        boolean[] ar5 = new boolean[4];
        System.out.println(ar5[0]);

        // 引用数据类型数组元素的默认初始化值：null
        String[] arr6 = new String[5];
        for (int i = 0; i < arr6.length; i++) {
            System.out.println(arr6[i]);
        }

        if (arr6[0] == null) {
            System.out.println("aaaa");
        }

        if (arr6[0] == "null") {
            System.out.println("bbbb");
        }
    }
}

```

## 三、一维数组内存分析

### 3.1 Java 虚拟机的内存划分

为了提高运算效率，就对空间进行了不同区域的划分，因为每一片区域都有特定的处理数据方式和内存管理方式。

![JVM 架构 - 简图](./images/JVM架构-简图.png "JVM 架构 - 简图")

Java 将内存区域划分为 5 个部分，如下表所示：
| 区域名称 | 作用 |
| :--: | :--: |
| 虚拟机栈 | 用于存储正在执行的每个 Java 方法的局部变量表等。局部变量表存放了编译期可知长度的各种基本数据类型、对象引用；方法执行完，自动释放。 |
| 堆内存 | 存储对象（包括数组对象）；即 `new` 来创建的，都存储在堆内存。 |
| 方法区 | 存储已被虚拟机加载的类信息、常量、（静态变量）、即时编译器编译后的代码等数据。 |
| 本地方法栈 | 当程序中调用了 `native` 的本地方法时，本地方法执行期间的内存区域。 |
| 程序计数器 | 程序计数器是 CPU 中的寄存器，它包含每一个线程下一条要执行的指令的地址。 |

与目前数组相关的内存结构：虚拟机栈、堆。
比如：
```java
int[] arr = new int[]{1, 2, 3};
```
其中：
* 虚拟机栈：用于存放方法中声明的变量，比如 `arr`。
* 堆：用于存放数组的实体（即数组中的所有元素），比如 `1`、`2`、`3`。

### 3.2 一维数组在内存中的存储

#### 3.2.1 一个一维数组内存图

示例代码 1：
```java
public static void main(String[] args) {
    int[] arr = new int[3];
    System.out.println(arr);    // [I@5f150435
}
```
代码 1 图解：
![一个一维数组内存图 1](./images/数组内存图1.jpg "一个一维数组内存图 1")

> 注意：`[I@f150435` 中，`[` 表示数组，`I` 表示 `int` 型，`@` 表示位置。

示例代码 2：
```java
public static void main(String[] args) {
    int[] arr1 = new int[4];
    arr1[0] = 10;
    arr1[2] = 20;

    String[] arr2 = new String[2];
    arr2[1] = "周杰伦";
    arr2 = new String[3];
}
```
代码 2 图解：
![一个一维数组内存图 2](./images/20251023205904.png "一个一维数组内存图 2")

> 注意：只要出现 `new`，即说明需要重新再开辟一块新的内存空间。

#### 3.2.2 数组下标为什么是从 0 开始？

数组下标从 `0` 开始的原因为：第一个元素距离数组首地址间隔 `0` 个单元格。

#### 3.2.3 两个一维数组内存图

若声明两个数组，则这两个数组相互独立。

示例代码：
```java
public static void main(String[] args) {
    int[] arr = new int[3];
    int[] arr2 = new int[2];
    System.out.println(arr);
    System.out.println(arr2);
}
```

![两个一维数组内存图](./images/数组内存图2.jpg "两个一维数组内存图")

#### 3.2.4 两个变量指向一个一维数组

若两个变量指向一个一维数组，则这两个数组变量本质上代表同一个数组。

示例代码 1：
```java
public static void main(String[] args) {
    // 定义数组，存储 3 个元素
    int[] arr = new int[3];
    // 数组索引进行赋值
    arr[0] = 5;
    arr[1] = 6;
    arr[2] = 7;
    // 输出 3 个索引上的元素值
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);
    // 定义数组变量 arr2，将 arr 的地址赋值给 arr2
    int[] arr2 = arr;
    arr2[1] = 9;
    System.out.println(arr[1]);
}
```
代码 1 图解：
![两个变量指向一个一维数组内存图 1](./images/数组内存图3.jpg "两个变量指向一个一维数组内存图 1")

示例代码 2：
```java
public static void main(String[] args) {
    int[] arr = new int[3];
    arr[0] = 5;
    arr[1] = 6;
    arr[2] = 7;

    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);

    // 定义数组变量 arr1，将 arr 的地址赋值给 arr1
    int[] arr1 = arr;
    arr1[1] = 9;
    System.out.println(arr[1]); // 9
}
```
代码 2 图解：
![两个变量指向一个一维数组内存图 2](./images/20251023211010.png "两个变量指向一个一维数组内存图 2")

## 四、一维数组的应用

**案例 1：**
> 升景坊单间短期出租 4 个月，550 元/月（水电煤公摊，网费 35 元/月），空调、卫生间、厨房齐全。屋内均是 IT 行业人士，喜欢安静，所以要求来租者最好是同行或者刚毕业的年轻人，爱干净、安静。

示例代码：
```java
public class ArrayExer {
    public static void main(String[] args) {
        int[] arr = new int[] { 8, 2, 1, 0, 3 };
        int[] index = new int[] { 2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3 };

        String tel = "";

        for (int i = 0; i < index.length; i++) {
            int value = index[i];
            tel += arr[value];
        }
        System.out.println("联系方式：" + tel);  // 18013820100

    }
}

```

**案例 2 - 输出英文星期几：**
> 用一个数组，保存星期一到星期天的 7 个英语单词。从键盘输入 1 - 7，显示对应的单词：“Monday”、“Tuesday”、“Wednesday”、“Thursday”、“Friday”、“Saturday”、“Sunday”。

示例代码：
```java
public class ArrayExer02 {
    public static void main(String[] args) {
        // 定义包含 7 个单词的数组
        String[] weeks = { "Monday", "Tuesday",  "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

        // 从键盘获取指定的数值，使用 Scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入数值（1 - 7）：");
        int day = scan.nextInt();

        // 针对获取的数据进行判断即可
        if (day < 1 || day > 7) {
            System.out.println("你输入的数据有误。");
        } else {
            System.out.println(weeks[day - 1]);
        }

        scan.close();
    }
}

```

**案例 3：**
> 从键盘读入学生成绩，找出最高分，并输出学生成绩等级：
> * 成绩 >= 最高分 - 10 -> 等级为 `A`；
> * 成绩 >= 最高分 - 20 -> 等级为 `B`；
> * 成绩 >= 最高分 - 30 -> 等级为 `C`；
> * 其余 -> 等级为 `D`。
>
> 示例：
> ![案例 3 程序示例](./images/image-20220317004637748.png "案例 3 程序示例")
> 提示：先读入学生人数，根据人数创建 `int` 数组，存放学生成绩。

示例代码 1：
```java
public class ArrayExer03 {
    public static void main(String[] args) {
        // 1. 从键盘输入学生的人数，根据人数创建数组（动态初始化）
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入学生人数：");
        int count = scan.nextInt();

        int[] scores = new int[count];

        // 2. 根据提示，依次输入学生的成绩，并将成绩保存在数组元素中
        System.out.println("请输入 " + count + " 个成绩");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scan.nextInt();
        }

        // 3. 获取学生成绩的最大值
        int maxScore = scores[0];
        for (int i = 0; i < scores.length; i++) {
            if (maxScore < scores[i]) {
                maxScore = scores[i];
            }
        }

        System.out.println("最高分是：" + maxScore);

        // 4. 遍历数组元素，根据学生成绩与最高分的差值，得到每个学生的等级，并输出成绩和等级
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= maxScore - 10) {
                System.out.println("student " + i + " score is " + scores[i] + " grade is A");
            } else if (scores[i] >= maxScore - 20) {
                System.out.println("student " + i + " score is " + scores[i] + " grade is B");
            }  else if (scores[i] >= maxScore - 30) {
                System.out.println("student " + i + " score is " + scores[i] + " grade is C");
            } else {
                System.out.println("student " + i + " score is " + scores[i] + " grade is D");
            }
        }

        scan.close();

    }
}

```

示例代码 2（针对示例代码 1 进行优化）：
```java
public class ArrayExer03_1 {
    public static void main(String[] args) {
        // 1. 从键盘输入学生的人数，根据人数创建数组（动态初始化）
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入学生人数：");
        int count = scan.nextInt();

        int[] scores = new int[count];

        // 2. 根据提示，依次输入学生的成绩，并将成绩保存在数组元素中
        int maxScore = scores[0];
        System.out.println("请输入 " + count + " 个成绩");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scan.nextInt();
            // 3. 获取学生成绩的最大值
            if (maxScore < scores[i]) {
                maxScore = scores[i];
            }
        }

        System.out.println("最高分是：" + maxScore);

        // 4. 遍历数组元素，根据学生成绩与最高分的差值，得到每个学生的等级，并输出成绩和等级
        char grade = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= maxScore - 10) {
                grade = 'A';
            } else if (scores[i] >= maxScore - 20) {
                grade = 'B';
            }  else if (scores[i] >= maxScore - 30) {
                grade = 'C';
            } else {
                grade = 'D';
            }

            System.out.println("student " + i + " score is " + scores[i] + " grade is " + grade);
        }

        scan.close();

    }
}

```

## 五、多维数组的使用

### 5.1 概述

Java 语言里提供了支持多维数组的语法。

如果说可以把一维数组当作几何中的线性图形，那么二维数组就相当于是一个表格，像 Excel 中的表格、围棋棋盘一样。如下图所示：
![Java 中二维数组的类比示意图](./images/image-20220317004810263.png "Java 中二维数组的类比示意图")

应用举例 1：
* 某公司 2022 年全年各个月份的销售额进行登记：
  * 按月份存储，可以使用一维数组，如下：
    ```java
    int[] monthData = new int[]{23, 43, 22, 34, 55, 65, 44, 67, 45, 78, 67, 66};
    ```
  * 如果改写为按“季度”为单位存储，怎么办呢？
    ```java
    int[][] quarterData = new int[][]{{23, 43, 22}, {34, 55, 65}, {44, 67, 45}, {78, 67, 66}};
    ```
应用举例 2：
* 高一年级三个班级均由多个学生姓名构成一个个数组，如下：
    ```java
    String[] class1 = new String[]{"段誉", "令狐冲", "任我行"};

    String[] class2 = new String[]{"张三丰", "周芷若"};

    String[] class3 = new String[]{"赵敏", "张无忌", "韦小宝", "杨过"};

    ```
  * 那从整个年级看，我们可以声明一个二维数组，如下：
    ```java
    String[][] grade = new String[][]{{"段誉", "令狐冲", "任我行"}, {"张三丰", "周芷若"}, {"赵敏", "张无忌", "韦小宝", "杨过"}};
    ```
应用举例 3：
![应用举例 3 示意图 1](./images/image-20220516095701345.png "应用举例 3 示意图 1")
* 蓝框的几个元素，可以使用一维数组来存储。但现在发现每个元素下还有下拉框，其内部还有元素，那就需要使用二维数组来存储：
    ![应用举例 3 示意图 2](./images/image-20220516095829526.png "应用举例 3 示意图 2")

对于二维数组的使用说明：
![对于二维数组的使用说明示意图](./images/1561524724397-1647707344971.png "对于二维数组的使用说明示意图")

> 注意：
> * 对于二维数组的理解，可以看成是一维数组 `array1` 又作为另一个一维数组 `array2` 的元素而存在。
> * 其实，从数组底层的运行机制来看，其实没有多维数组。

### 5.2 声明与初始化

#### 5.2.1 声明

二维数组声明的语法格式：
```java
// 推荐
元素的数据类型[][] 二维数组的名称;

// 不推荐
元素的数据类型 二维数组名[][];
// 不推荐
元素的数据类型[] 二维数组名[];
```

> 面试题：
>
> 请阅读以下代码：
> ```java
> int[] x, y[];
> // x 是一维数组，y 是二维数组
> ```
> 代码解释：

#### 5.2.2 静态初始化

静态初始化语法格式：
```java
int[][] arr = new int[][]{{3, 8, 2}, {2, 7}, {9, 0, 1, 6}};
```

上述代码定义了一个名称为 arr 的二维数组，二维数组中有三个一维数组。其中：
* 每一个一维数组中具体元素也都已初始化：
  * 第一个一维数组 arr[0] = {3, 8, 2}；
  * 第二个一维数组 arr[1] = {2, 7}；
  * 第三个一维数组 arr[2] = {9, 0, 1, 6}。
* 第三个一维数组的长度的表示方式：`arr[2].length`。

> 注意：
> 
> 存在以下特殊写法情况：
> ```java
> int[] x, y[];
> ```
> 其中，`x` 是一维数组，`y` 是二维数组。

#### 5.2.3 动态初始化

如果二维数组的每一个数据，甚至是每一行的列数，需要后期单独确定，那么就只能使用动态初始化方式了。

动态初始化方式分为两种格式：
* 格式 1 - 规则二维表（每一行的列数是相同的）：
  * 语法格式：
    ```java
    // 1. 确定行数和列数
    元素的数据类型[][] 二维数组名 = new 元素的数据类型[m][n];
        // 其中，m：表示这个二维数组有多少个一维数组，或者说二维表一共有多少行
        // 其中，n：表示每一个一维数组的元素有多少个，或者说每一行共有多少个单元格

    // 此时创建完数组，行数、列数确定，而且元素也都有默认值

    // 2. 再为元素赋新值
    二维数组名[行下标][列下标] = 值;
    ```
  * 示例代码：
    ```java
    int[][] arr = new int[3][2];
    ```
    * 定义了名称为 `arr` 的二维数组；
    * 二维数组中有 3 个一维数组；
    * 每一个一维数组中有 2 个元素；
    * 一维数组的名称分别为 `arr[0]`、`arr[1]`、`arr[2]`；
    * 给第一个一维数组 1 角标位赋值为 78 的写法是：`arr[0][1] = 78`。
* 格式 2 - 不规则二维表（每一行的列数不一样）：
  * 语法格式：
    ```java
    // 1. 先确定总行数
    元素的数据类型[][] 二维数组名 = new 元素的数据类型[总行数][];

    // 此时只是确定了总行数，每一行里面现在是 null

    // 2. 再确定每一行的列数，创建每一行的一维数组
    二维数组名[行下标] = new 元素的数据类型[该行的总列数];

    // 此时已经 new 完的行的元素就有默认值了，没有 new 的行还是 null

    // 3. 再为元素赋值
    二维数组名[行下标][列下标] = 值;
    ```
  * 示例代码：
    ```java
    int[][] arr = new int[3][];
    ```
    * 二维数组中有 3 个一维数组；
    * 每个一维数组都是默认初始化值 `null`（注意：区别于格式 1）；
    * 可以对这个 3 个一维数组分别进行初始化，如 `arr[0] = new int[3];`、`arr[1] = new int[1];`、`arr[2] = new int[2];`；
    * 注意此写法 `int[][] arr = new int[][3];` 是非法的。

练习：
> 题目：

示例代码：
```java

```

#### 5.2.4 二维数组的声明与初始化

结合二维数组的声明与静态、动态初始化方法，给出以下示例代码：
```java
public class TwoArrayTest {
    public static void main(String[] args) {
        // 1. 数组的声明与初始化
        // 方式 1 - 静态初始化：数组变量的赋值和数组元素的赋值同时进行
        int[][] arr2 = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };

        // 方式 2 - 动态初始化 1：数组变量的赋值和数组元素的赋值分开进行
        String[][] arr3 = new String[3][4];
        // 方式 2 - 动态初始化 2
        double[][] arr4 = new double[2][];

        // 其他正确的写法
        int arr5[][] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int[] arr6[] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int arr7[][] = { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} }; // 类型推断
        String arr8[][] = new String[3][4];

        // 错误的写法
//        int[][] arr9 = new int[3][3] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[3][3] arr10 = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[][]arr11 =new int[][10];
    }
}

```

### 5.3 数组的长度和角标

二维数组的长度 / 行数 的获取方式为 `二维数组名.length`。

二维数组的某一行的获取方式为 `二维数组名[行下标]`，行下标的范围为 `[0, 二维数组名.length - 1]`。此时相当于获取其中一组数据，获取到的数组本质上是一个一维数组。此时把二维数组看成一维数组的话，元素是行对象。

某一行的列数的获取方式为 `二维数组名[行下标].length`，因为二维数组的每一行是一个一维数组。

某一个元素的获取方式为 `二维数组名[行下标][列下标]`，即先确定行 / 组，再确定列。

示例代码：
```java
public class TwoArrayTest {
    public static void main(String[] args) {
        // 1. 数组的声明与初始化
        // 方式 1 - 静态初始化：数组变量的赋值和数组元素的赋值同时进行
        int[][] arr2 = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };

        // 方式 2 - 动态初始化 1：数组变量的赋值和数组元素的赋值分开进行
        String[][] arr3 = new String[3][4];
        // 方式 2 - 动态初始化 2
        double[][] arr4 = new double[2][];

        // 其他正确的写法
        int arr5[][] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int[] arr6[] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int arr7[][] = { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} }; // 类型推断
        String arr8[][] = new String[3][4];

        // 错误的写法
//        int[][] arr9 = new int[3][3] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[3][3] arr10 = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[][] arr11 = new int[][10];

        // 2. 数组元素的调用
        // 针对 arr2 来说，外层元素 {1, 2, 3}、{4, 5}、{6, 7, 8, 9}，内层元素：1、2、3、4、5、6、7、8、9
        // 调用内层元素
        System.out.println(arr2[0][0]); // 1
        System.out.println(arr2[2][1]); // 7

        // 调用外层元素
        System.out.println(arr2[0]);    // [I@27716f4

        // 测试 arr3、arr4
        arr3[0][1] = "Tom";
        System.out.println(arr3[0][1]);
        System.out.println(arr3[0]);    // [Ljava.lang.String;@8efb846

        arr4[0] = new double[4];
        arr4[0][0] = 1.0;

        // 3. 数组的长度
        System.out.println(arr2.length);    // 3
        System.out.println(arr2[0].length); // 3
        System.out.println(arr2[1].length); // 2
        System.out.println(arr2[2].length); // 4
    }
}

```

> 注意：
>
> 上述示例代码中，执行 `System.out.println(arr3[0]);` 后输出结果为 `// [Ljava.lang.String;@8efb846`，其中：
> * `[` 表示这是一维数组（同理，`[[` 表示这是二维数组）；
> * `L` 表示这是一个对象；
> * `java.lang.String` 表示该对象的类型；
> * `@` 后面的字符串表示该对象的 HashCode。

### 5.4 二维数组的遍历

遍历二维数组的语法格式：
```java
for (int i = 0; i < 二维数组名.length; i++) {   // 二维数组对象.length
    for (int j = 0; j < 二维数组名[i].length; j++) {    // 二维数组行对象.length
        System.out.print(二维数组名[i][j]);
    }
    System.out.println();
}
```

示例代码：
```java
public class TwoArrayTest {
    public static void main(String[] args) {
        int[][] arr2 = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };

        // 4. 如何遍历数组
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.print(arr2[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

```

### 5.5 二维数组元素的默认值

对于动态初始化方式 1（比如 `int[][] arr = new int[3][4]`）：
* 示例代码：
    ```java
    public class TwoArrayTest1 {
        public static void main(String[] args) {
            // 5. 数组元素的默认初始化值
            // 以动态初始化方式 1 说明：
            int[][] arr1 = new int[3][2];
            // 外层元素默认值：
            System.out.println(arr1[0]);    // 地址值：[I@27716f4
            System.out.println(arr1[1]);    // 地址值：[I@8efb846

            // 内层元素默认值：
            System.out.println(arr1[0][0]); // 0


            boolean[][] arr2 = new boolean[3][4];
            // 外层元素默认值：
            System.out.println(arr2[0]);    // 地址值：[Z@2a84aee7
            // 内层元素默认值：
            System.out.println(arr2[0][1]); // false

            String[][] arr3 = new String[4][2];
            // 外层元素默认值：
            System.out.println(arr3[0]);    // 地址值：[Ljava.lang.String;@a09ee92
            // 内层元素默认值：
            System.out.println(arr3[0][1]); // null
        }
    }

    ```
  * 外层元素：默认存储一维数组的类型和地址值；
  * 内层元素：默认与一维数组元素的不同类型的默认值规定相同。

对于动态初始化方式 2（比如 `int[][] arr = new int[3][]`）：
* 示例代码：
    ```java
    public class TwoArrayTest1 {
        public static void main(String[] args) {
            // 5. 数组元素的默认初始化值
            // 以动态初始化方式 2 说明：
            int[][] arr4 = new int[4][];
            // 外层元素默认值：
            System.out.println(arr4[0]);    // null
            // 内层元素默认值：
            System.out.println(arr4[0][0]); // 报错

            String[][] arr5 = new String[5][];
            // 外层元素默认值：
            System.out.println(arr5[0]);    // null
            // 内层元素默认值：
            System.out.println(arr5[0][0]); // 报错
        }
    }

    ```
  * 外层元素：默认存储 `null`；
  * 内层元素：不存在的，如果调用会报错（`NullPointerException`）。

### 5.5 内存解析

二维数组本质上是元素类型是一维数组的一维数组。

示例代码 1：
```java
int[][] arr = {
    {1},
    {2, 2},
    {3, 3, 3},
    {4, 4, 4, 4},
    {5, 5, 5, 5, 5}
};
```

对上述示例代码 1 的内存解析：
![示例代码 1 内存解析示意图](./images/1562112672215.png "示例代码 1 内存解析示意图")
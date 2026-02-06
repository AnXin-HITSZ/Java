# 第十章：多线程

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 10 章](./images/第3阶段：Java高级应用-第10章.png "第 3 阶段：Java 高级应用 - 第 10 章")

我们之前学习的程序在没有跳转语句的情况下，都是由上至下沿着一条路径依次执行。现在想要设计一个程序，可以同时有多条执行路径同时执行，应该如何设计？

要解决上述问题，需要使用**多进程**或者**多线程**来解决。

## 一、相关概念

### 1.1 程序、进程与线程

**程序（program）**：为完成特定任务，用某种语言编写的**一组指令的集合**；即指**一段静态的代码**，属于静态对象。

**进程（process）**：程序的一次执行过程，或是正在内存中运行的应用程序。
* 每个进程都有一个独立的内存空间，系统运行一个程序即是一个进程从创建、运行到消亡的过程（生命周期）。
* 程序是静态的，进程是动态的。
* 进程作为**操作系统调度和分配资源的最小单位**（亦是系统运行程序的基本单位），系统在运行时会为每个进程分配不同的内存区域。
* 现代的操作系统，大都是支持多进程的，支持同时运行多个程序。

**线程（thread）**：进程可进一步细化为线程，是程序内部的一条执行路径。一个进程中至少有一个线程。
* 一个进程同一时间若**并行**执行多个线程，就是支持多线程的。
    ![传统进程与多线程进程](./images/image-20220331233204504.png "传统进程与多线程进程")
* 线程作为 **CPU 调度和执行的最小单位**。
* 一个进程中的多个线程共享相同的内存单元，它们从同一个堆中分配对象，可以访问相同的变量和对象，这就使得线程间通信更简便、高效；但多个线程操作共享的系统资源可能就会带来**安全的隐患**。
* 下图中，红框的蓝色区域为线程独享，黄色区域为线程共享。
    ![线程独享与线程共享](./images/image-20220514175737426.png "线程独享与线程共享")

> 注意：
> * 不同的进程之间是不共享内存的。
> * 进程之间的数据交换和通信的成本很高。

通俗而言，每个应用程序的运行都是一个进程；一个应用程序的多次执行，就是多个线程；一个进程中包含多个线程。

### 1.2 线程调度

#### 1.2.1 分时调度

所有线程**轮流使用** CPU 的使用权，并且平均分配每个线程占用 CPU 的时间。

#### 1.2.2 抢占式调度

让优先级高的线程以较大的概率优先使用 CPU。如果线程的优先级相同，那么会随机选择一个（线程随机性）。

Java 使用的为抢占式调度。

### 1.3 多线程程序的优点

**背景：** 以单核 CPU 为例，只使用单个线程先后完成多个任务（调用多个方法），肯定比用多个线程来完成用的时间更短；为何仍需多线程呢？

**多线程程序的优点：**
1. 提高应用程序的相应；对图形化界面更有意义，可增强用户体验。
2. 提高计算机系统 CPU 的利用率。
3. 改善程序结构；将既长又复杂的进程分为多个线程，独立运行，利于理解和修改。

### 1.4 补充概念

#### 1.4.1 单核 CPU 和多核 CPU

单核 CPU，是指在一个时间单元内，只能执行一个线程的任务。

若想要提升系统性能，只有两个办法：要么提升 CPU 性能；要么多加几个 CPU，即为多核的 CPU。

**问题：多核的效率是单核的倍数吗？** 
譬如 4 核 A53 的 CPU，性能是单核 A53 的 4 倍吗？理论上是，但是实际不可能，因为至少有两方面的损耗：
* **一个是多个核心的其他共用资源限制**：譬如 4 核 CPU 对应的内存、Cache、寄存器并没有同步扩充 4 倍。
* **另一个是多核 CPU 之间的协调管理损耗**：譬如多个核心同时运行两个相关的任务，需要考虑任务同步，这也需要消耗额外性能。

#### 1.4.2 并行与并发

**并行（parallel）**：指两个或多个事件在**同一时刻**发生（同时发生）；即指在同一时刻，有**多条指令**在**多个 CPU** 上**同时**执行。如下图所示：
![并行（parallel）示意图](./images/image-20220401000804242.png "并行（parallel）示意图")

**并发（concurrency）**：指两个或多个事件在**同一个时间段内**发生；即在一段时间内，有**多条指令**在**单个 CPU** 上**快速轮换、交替执行**，使得在宏观上具有多个进程同时执行的效果。如下图所示：
![并发（concurrency）示意图](./images/image-20220401000515678.png "并发（concurrency）示意图")

在操作系统中，启动了多个程序。**并发**指的是在一段时间内宏观上有多个程序同时运行；这在单核 CPU 系统中，每一时刻只能有一个程序执行，即微观上这些程序是分时地交替运行，只不过是给人的感觉是同时运行，那是因为分时交替运行的时间是非常短的。

而在多核 CPU 系统中，则这些可以**并发**执行的程序便可以分配到多个 CPU 上，实现多任务并行执行；即利用每个处理器来处理一个可以并发执行的程序，这样多个程序便可以同时执行。目前电脑市场上说的多核 CPU，便是多核处理器；核越多，**并行**处理的程序越多，能大大提高电脑运行的效率。

## 二、创建和启动线程

### 2.1 概述

Java 语言的 JVM 允许程序运行多个线程，使用 `java.lang.Thread` 类代表**线程**，所有的线程对象都必须是 `Thread` 类或其子类的实例。

`Thread` 类的特性：
* 每个线程都是通过某个特定 `Thread` 对象的 `run()` 方法来完成操作的，因此把 `run()` 方法体称为**线程执行体**。
* 通过该 `Thread` 对象的 `start()` 方法来启动这个线程，而非直接调用 `run()`。
* 要想实现多线程，必须在主线程中创建新的线程对象。

### 2.2 方式 1：继承 `Thread` 类

Java 通过继承 `Thread` 类来创建并启动多线程。步骤如下：
1. 定义 `Thread` 类的子类，并重写该类的 `run()` 方法，该 `run()` 方法的方法体就代表了此线程需要完成的任务（即将此线程要执行的操作声明在此方法体中）。
2. 创建 `Thread` 子类的实例，即创建了线程对象。
3. 调用线程对象的 `start()` 方法来启动该线程：
    1. 启动线程；
    2. 调用当前线程的 `run()`。

例题：创建一个分线程 1，用于遍历 100 以内的偶数。
> 拓展：再创建一个分线程 2，用于遍历 100 以内的偶数。

示例代码：
```java
/* EvenNumberTest.java */

package com.anxin_hitsz_01.create.thread;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.create.thread
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/6 18:22
 * @Version 1.0
 */

// 1. 创建一个继承于 Thread 类的子类
class PrintNumber extends Thread {
    // 2. 重写 Thread 类的 run() -> 将此线程要执行的操作，声明在此方法体中
    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class EvenNumberTest {
    public static void main(String[] args) {
        // 3. 创建当前 Thread 的子类的对象
        PrintNumber t1 = new PrintNumber();

        // 4. 通过对象调用 start()
        t1.start();

        /*
        * 问题 1：能否使用 t1.run() 替换 t1.start() 的调用，实现分线程的创建和调用？
        *   不能！
        * */
//        t1.run();

        /*
        * 问题 2：再提供一个分线程，用于 100 以内偶数的遍历。
        *
        * 注意：不能让已经 start() 的线程再次执行 start() 操作，否则报异常 IllegalThreadStateException
        * */
//        t1.start();
        PrintNumber t2 = new PrintNumber();
        t2.start();


        // main() 所在的线程执行的操作：
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " **********");
            }
        }

    }
}

```

> 注意：
> 1. 如果自己手动调用 `run()` 方法，那么就只是普通方法，没有启动多线程模式。
> 2. `run()` 方法由 JVM 调用，什么时候调用、执行的过程控制都由操作系统的 CPU 调度决定。
> 3. 想要启动多线程，必须调用 `start()` 方法。
> 4. 一个线程对象只能调用一次 `start()` 方法启动；如果重复调用了，则将抛出异常 “`IllegalThreadStateException`”。

### 2.6 练习

**练习 1：**
> 题目：
>
> 创建两个分线程，其中一个线程遍历 100 以内的偶数，另一个线程遍历 100 以内的奇数。

示例代码：
```java
/* PrintNumberTest.java */

package com.anxin_hitsz_01.create.exer1;

/**
 * ClassName: PrintNumberTest
 * Package: com.anxin_hitsz_01.create.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/6 18:39
 * @Version 1.0
 */
class EvenNumberPrint  extends Thread { // 用于打印偶数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}

class OddNumberPrint  extends Thread { // 用于打印奇数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }
    }
}

public class PrintNumberTest {
    public static void main(String[] args) {
        // 方式 1：
//        EvenNumberPrint t1 = new EvenNumberPrint();
//        OddNumberPrint t2 = new OddNumberPrint();
//
//        t1.start();
//        t2.start();

        // 方式 2：创建 Thread 类的匿名子类的匿名对象
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                }
            }
        }.start();

    }
}

```

### 2.3 方式 2：实现 `Runnable` 接口

Java 有单继承的限制，当我们无法继承 `Thread` 类时，那么该如何做呢？在核心类库中提供了 `Runnable` 接口，我们可以实现 `Runnable` 接口，重写 `run()` 方法，然后再通过 `Thread` 类的对象代理启动和执行我们的线程体 `run()` 方法。

步骤如下：
1. 定义 `Runnable` 接口的实现类，并重写该接口的 `run()` 方法；该 `run()` 方法的方法体同样是该线程的线程执行体（即将此线程要执行的操作声明在此方法体中）。
2. 创建当前 `Runnable` 实现类的实例，并以此实例作为 `Thread` 的 `target` 参数来创建 `Thread` 对象（即将此对象作为参数传递到 `Thread` 类的构造器中，创建 `Thread` 类的实例），该 `Thread` 对象才是真正的线程对象。
3. 调用线程对象的 `start()` 方法，启动线程；调用 `Runnable` 接口实现类的 `run()` 方法。即：
    1. 启动线程；
    2. 调用当前线程的 `run()`。

示例代码：
```java

```

通过实现 `Runnable` 接口，使得该类有了多线程类的特征。所有的分线程要执行的代码都在 `run()` 方法里面。

在启动多线程的时候，需要先通过 `Thread` 类的构造方法 `Thread(Runnable target)` 构造出对象，然后调用 `Thread` 对象的 `start()` 方法来运行多线程代码。

实际上，所有的多线程代码都是通过运行 `Thread` 的 `start()` 方法来运行的。因此，不管是继承 `Thread` 类还是实现 `Runnable` 接口来实现多线程，最终还是通过 `Thread` 的对象的 API 来控制线程的，熟悉 `Thread` 类的 API 是进行多线程编程的基础。

> 说明：
>
> `Runnable` 对象仅仅作为 `Thread` 对象的 `target`，`Runnable` 实现类里包含的 `run()` 方法仅作为线程执行体；而实际的线程对象依然是 `Thread` 实例，只是该 `Thread` 线程负责执行其 `target` 的 `run()` 方法。
> 
> ![说明 - Runnable 与 Thread](./images/image-20220401222212377.png "说明 - Runnable 与 Thread")

### 2.4 变形写法

使用匿名内部类对象来实现线程的创建和启动。

示例代码 1：
```java
new Thread("新的线程！") {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "：正在执行！" + i);
        }
    }
}.start();
```

示例代码 2：
```java
new Thread(new Runnable() {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "：" + i);
        }
    }
}).start();
```

### 2.5 对比两种方式

**联系：**

`Thread` 类实际上也是实现了 `Runnable` 接口的类。即：
```java
public class Thread extends Object implements Runnable（代理模式）
```

共同点：
1. 启动线程，使用的都是 `Thread` 类中定义的 `start()`。
2. 创建的线程对象，都是 `Thread` 类或其子类的实例。

**区别：**

继承 `Thread`：线程代码存放在 `Thread` 子类的 `run()` 方法中。

实现 `Runnable`：线程代码存放在接口的子类的 `run()` 方法中。

不同点：一个是类的继承，一个是接口的实现。

> 注意：
>
> 建议使用实现 `Runnable` 接口的方式。

**实现 `Runnable` 接口比继承 `Thread` 类所具有的优势：**
* 实现的方式避免了类的单继承的局限性。
* 多个线程可以共享同一个接口实现类的对象，非常适合多个相同线程来处理同一份资源。（即更适合处理有共享数据的问题。）
* 增加程序的健壮性，实现解耦操作，代码可以被多个线程共享，代码和线程独立。（即：实现了代码和数据的分离。）
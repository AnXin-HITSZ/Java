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
/* EvenNumberTest.java */

package com.anxin_hitsz_01.create.runnable;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.create.runnable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 15:18
 * @Version 1.0
 */
// 1. 创建一个实现 Runnable 接口的类
class EvenNumberPrint implements Runnable {
//    private double money = 1000;
    // 2. 实现 接口中的 run() -> 将此线程要执行的操作，声明在此方法体中
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}

public class EvenNumberTest {
    public static void main(String[] args) {
        // 3. 创建当前实现类的对象
        EvenNumberPrint p = new EvenNumberPrint();
        // 4. 将此对象作为参数传递到 Thread 类的构造器中，创建 Thread 类的实例
        Thread t1 = new Thread(p);
        // 5. Thread 类的实例调用 start()：1. 启动线程；2. 调用当前线程的 run()
        t1.start();

        // main() 方法对应的主线程执行的操作
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

        /*
        * 拓展：再创建一个线程，用于遍历 100 以内的偶数
        * */
        Thread t2 = new Thread(p);
        t2.start();

    }
}

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

### 2.6 练习

**练习：**
> 题目：
>
> 创建两个分线程，其中一个线程遍历 100 以内的偶数，另一个线程遍历 100 以内的奇数。

示例代码 1：
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

示例代码 2：
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
//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 100; i++) {
//                    if (i % 2 == 0) {
//                        System.out.println(Thread.currentThread().getName() + " : " + i);
//                    }
//                }
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 100; i++) {
//                    if (i % 2 != 0) {
//                        System.out.println(Thread.currentThread().getName() + " -> " + i);
//                    }
//                }
//            }
//        }.start();

        // 方式 3：使用实现 Runnable 接口的方式：（提供了 Runnable 接口匿名实现类的匿名对象）
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                }
            }
        }).start();

    }
}

```

## 三、`Thread` 类的常用结构

示例代码：
```java
/* EvenNumberTest.java */

package com.anxin_hitsz_01.method_lifecycle;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.method_lifecycle
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 17:38
 * @Version 1.0
 */
class PrintNumber extends Thread {

    public PrintNumber() {

    }

    public PrintNumber(String name) {
        super(name);
    }

    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " +
                        Thread.currentThread().getPriority() + ": " + i);
            }

//            if (i % 20 == 0) {
//                Thread.yield();
//            }
        }
    }
}

public class EvenNumberTest {
    public static void main(String[] args) {

        PrintNumber t1 = new PrintNumber("线程 1");
        t1.setName("子线程 1");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);


        // main() 所在的线程执行的操作：
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " +
                        Thread.currentThread().getPriority() + ": " + i);
            }

//            if (i == 20) {
//                try {
//                    t1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

//        System.out.println("子线程 1 是否存活？" + t1.isAlive());

    }
}

```

### 3.1 构造器

`public Thread()`：分配一个新的线程对象。

`public Thread(String name)`：分配一个指定名字的新的线程对象。

`public Thread(Runnable target)`：指定创建线程的目标对象，它实现了 `Runnable` 接口中的 `run()` 方法。

`public Thread(Runnable target, String name)`：分配一个带有指定目标新的线程对象并指定名字。

### 3.2 常用方法

`public void run()`：此线程要执行的任务在此处定义代码。

`public void start()`：1. 导致此线程开始执行（启动线程）；2. Java 虚拟机调用此线程的 `run()` 方法。

`public String getName()`：获取当前线程名称。

`public void setName(String name)`：设置该线程名称。

`public static Thread currentThread()`：返回对当前正在执行的线程对象的引用（获取当前执行代码对应的线程）；在 `Thread` 子类中就是 `this`，通常用于主线程和 `Runnable` 实现类。

`public static void sleep(long millis)`：静态方法，使当前正在执行的线程以指定的毫秒数暂停（睡眠，即暂时停止执行）。

`public static void yield()`：一旦执行此方法，就释放 CPU 的执行权。`yield()` 只是让当前线程暂停一下，让系统的线程调度器重新调度一次，希望优先级与当前线程相同或更高的其他线程能够获得执行机会，但是这个不能保证；完全有可能的情况是，当某个线程调用了 `yield()` 方法暂停之后，线程调度器又将其调度出来重新执行。

`public final boolean isAlive()`：测试线程是否处于活动状态（即判断当前线程是否存活）；如果线程已经启动且尚未终止，则为活动状态。

`void join()`：等待该线程终止。在线程 `A` 中通过线程 `B` 调用 `join()` 方法，意味着线程 `A` 进入阻塞状态，直到线程 `B` 执行结束，线程 `A` 才结束阻塞状态，继续执行。
* `void join(long millis)`：等待该线程终止的时间最长为 `millis` 毫秒；如果 `millis` 时间到，将不再等待。
* `void join(long millis, int nanos)`：等待该线程终止的时间最长为 `millis 毫秒 + nanos 纳秒`。

（**已过时**，不建议使用）`public final void stop()`：强行结束一个线程的执行，直接进入死亡状态；`run()` 即刻停止，可能会导致一些清理性的工作得不到完成，如文件、数据库等的关闭；同时，会立即释放该线程所持有的所有的锁，导致数据得不到同步的处理，出现数据不一致的问题。

（**已过时**，不建议使用）`void suspend()` / `void resume()`：这两个操作就好比播放器的暂停和恢复。二者必须成对出现，否则非常容易发生死锁。`suspend()` 调用会导致线程暂停，但不会释放任何锁资源，导致其它线程都无法访问被它占用的锁，直到调用 `resume()`。

### 3.3 线程的优先级

每个线程都有一定的优先级，同优先级线程组成先进先出队列（先到先服务），使用分时调度策略。优先级高的线程采用抢占式策略，获得较多的执行机会。每个线程默认的优先级都与创建它的父线程具有相同的优先级。

`Thread` 类内部声明的三个优先级常量：
* `MAX_PRIORITY`（`10`）：最高优先级。
* `MIN_PRIORITY`（`1`）：最低优先级。
* `NORM_PRIORITY`（`5`）：普通优先级，默认情况下 `main` 线程具有普通优先级。

`public final int getPriority()`：返回线程优先级（获取线程的优先级）。

`public final void setPriority(int newPriority)`：设置线程的优先级，即改变线程的优先级，范围在 `[1, 10]` 之间。

### 3.5 守护线程

有一种线程，它是在后台运行的，它的任务是为其他线程提供服务的，这种线程被称为“守护线程”。JVM 的垃圾回收线程就是典型的守护线程。

守护线程有个特点，就是如果所有非守护线程都死亡，那么守护线程自动死亡。

调用 `setDaemon(true)` 方法可将指定线程设置为守护线程。必须在线程启动之前设置，否则会报 `IllegalThreadStateException` 异常。

调用 `isDaemon()` 可以判断线程是否是守护线程。

## 四、多线程的生命周期

Java 语言使用 `Thread` 类及其子类的对象来表示线程，在它的一个完整的生命周期中通常要经历如下一些状态。

### 4.1 JDK 1.5 之前：5 种状态

线程的生命周期有五种状态：新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）、死亡（Dead）。CPU 需要在多条线程之间切换，于是线程状态会多次在运行、阻塞、就绪之间切换。

线程状态切换示意图 1：
![线程状态切换示意图 - 1](./images/image-20220401002307038.png "线程状态切换示意图 - 1")

线程状态切换示意图 2：
![线程切换状态示意图 - 2](./images/线程的生命周期_jdk5之前.png "线程切换状态示意图 - 2")

#### 4.1.1 新建

当一个 `Thread` 类或其子类的对象被声明并创建时，新生的线程对象处于新建状态。此时它和其他 Java 对象一样，仅仅由 JVM 为其分配了内存，并初始化了实例变量的值。此时的线程对象并没有任何线程的动态特征，程序也不会执行它的线程体 `run()`。

#### 4.1.2 就绪

但是当线程对象调用了 `start()` 方法之后，就不一样了。线程就从新建状态转为就绪状态，JVM 会为其创建方法调用栈和程序计数器。当然，处于这个状态种的线程并没有开始运行，只是表示已具备了运行的条件，随时可以被调度；至于什么时候被调度，取决于 JVM 里线程调度器的调度。

> 注意：
>
> 程序只能对新建状态的线程调用 `start()`，并且只能调用一次。如果对非新建状态的线程（如已启动的线程或已死亡的线程）调用 `start()` 将会报错 `IllegalThreadStateException` 异常。

#### 4.1.3 运行

如果处于就绪状态的线程获得了 CPU 资源时，开始执行 `run()` 方法的线程体代码，则该线程处于运行状态。如果计算机只有一个 CPU 核心，在任何时刻只有一个线程处于运行状态；如果计算机有多个核心，将会有多个线程并行（Parallel）执行。

但是，CPU 讲究雨露均沾。对于抢占式策略的系统而言，系统会给每个可执行的线程一个小时间段来处理任务；当该时间用完，系统会剥夺该线程所占用的资源，让其回到就绪状态等到下一次被调度。此时其他线程将获得执行机会，而在选择下一个线程时，系统会适当考虑现成的优先级。

#### 4.1.4 阻塞

当在运行过程中的线程遇到如下情况时，会让出 CPU 并临时中止自己的执行，进入阻塞状态：
* 线程调用了 `sleep()` 方法，主动放弃所占用的 CPU 资源。
* 线程试图获取一个同步监视器，但该同步监视器正在被其他线程持有。
* 线程执行过程中，同步监视器调用了 `wait()`，让它等待某个个通知（notify）。
* 线程执行过程中，同步监视器调用了 `wait(time)`。
* 线程执行过程中，遇到了其他线程对象的加塞（join）。
* 线程被调用 `suspend()` 方法被挂起（**已过时**，因为容易发生死锁）。

当前正在执行的线程被阻塞后，其他线程就有机会执行了。针对如上情况，当发生如下情况时会解除阻塞，让该线程重新进入就绪状态，等待线程调度器再次调度它：
* 线程的 `sleep()` 时间到。
* 线程成功获得了同步监视器。
* 线程等到了通知（notify）。
* 线程 wait 的时间到了。
* 加塞的线程结束了。
* 被挂起的线程又被调用了 `resume()` 恢复方法（**已过时**，因为容易发生死锁）。

#### 4.1.5 死亡

线程会以以下三种方式之一结束，结束后的线程就处于死亡状态：
* `run()` 方法执行完成，线程正常结束。
* 线程执行过程中抛出了一个未捕获的异常（Exception）或错误（Error）。
* 直接调用该线程的 `stop()` 来结束该线程（**已过时**）。

### 4.2 JDK 1.5 及之后：6 种状态

在 java.lang.Thread.State 的枚举类中这样定义：
```java
public enum State {
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED;
}
```

其中：
* `NEW`（新建）：线程刚被创建，但是并未启动；还没调用 `start()` 方法。
* `RUNNABLE`（可运行）：这里没有区分就绪和运行状态。因为对于 Java 对象来说，只能标记为可运行，至于什么时候运行，不是 JVM 来控制的了，而是 OS 来进行调度的，而且时间非常短暂。因此对于 Java 对象的状态来说，无法区分。
* `Terminated`（被终止）：表明此线程已经结束生命周期，终止运行。

重点说明：根据 `Thread.State` 的定义，阻塞状态分为三种，分别为 `BLOCKED`、`WAITING`、`TIMED_WAITING`。
* `BLOCKED`（锁阻塞）：在 API 中的介绍为“一个正在阻塞、等待一个监视器锁（锁对象）的线程处于这一状态。”只有获得锁对象的线程才能有执行机会。
  * 比如，线程 `A` 与线程 `B` 代码中使用同一锁，如果线程 `A` 获取到锁，线程 `A` 进入到 `Runnable` 状态，那么线程 `B` 就进入到 `Blocked` 锁阻塞状态。
* `TIMED_WAITING`（计时等待）：在 API 中的介绍为“一个正在限时等待另一个线程执行一个（唤醒）动作的线程处于这一状态。”
  * 当前线程执行过程中遇到 `Thread` 类的 `sleep` 或 `join`、`Object` 类的 `wait`、`LockSupport` 类的 `park` 方法，并且在调用这些方法时，设置了时间，那么当前线程会进入 `TIMED_WAITING`，直到时间到，或被中断。
* `WAITING`（无限等待）：在 API 中介绍为“一个正在无限期等待另一个线程执行一个特别的（唤醒）动作的线程处于这一状态”。
  * 当前线程执行过程中遇到 `Object` 类的 `wait`、`Thread` 类的 `join`、`LockSupport` 类的 `park` 方法，并且在调用这些方法时，没有指定时间，那么当前线程会进入 `WAITING` 状态，直到被唤醒。
    * 通过 `Object` 类的 `wait` 进入 `WAITING` 状态的要有 `Object` 的 `notify` / `notifyAll` 唤醒。
    * 通过 `Condition` 的 `await` 进入 `WAITING` 状态的要有 `Condition` 的 `signal` 方法唤醒。
    * 通过 `LockSupport` 类的 `park` 方法进入 `WAITING` 状态的要有 `LockSupport` 类的 `unpark` 方法唤醒。
    * 通过 `Thread` 类的 `join` 进入 `WAITING` 状态，只有调用 `join` 方法的线程对象结束才能让当前线程恢复。

> 说明：
>
> 当从 `WAITING` 或 `TIMED_WAITING` 恢复到 `RUNNABLE` 状态时，如果发现当前线程没有得到监视器锁，那么会立刻转入 `BLOCKED` 状态。

线程的生命周期：
![线程的生命周期](./images/image-20220524203355448.png "[线程的生命周期")

线程的生命周期 `Thread.State`：
![线程的生命周期 Thread.State](./images/线程的生命周期Thread.State.jpg "线程的生命周期 Thread.State")

> 注意：
> 
> 我们在翻阅 API 的时候，会发现 `Time Waiting`（计时等待）与 `Waiting`（无限等待）状态联系还是很紧密的。
>
> 比如 `Waiting`（无限等待）状态中 `wait` 方法是空参的，而 `Time waiting`（计时等待）中 `wait` 方法是带参的。
>
> 这种带参的方法，其实是一种倒计时操作，相当于我们生活中的小闹钟，我们设定好时间，到时通知；可是如果提前得到（唤醒）通知，那么设定好时间再通知也就显得多此一举了。
> 
> 那么这种设计方案其实是一举两得：如果没有得到（唤醒）通知，那么线程就处于 `Time Waiting` 状态，直到倒计时完毕自动醒来；如果在倒计时期间得到（唤醒）通知，那么线程从 `Time Waiting` 状态立刻唤醒。

### 4.3 练习

练习：
> 题目 - 新年倒计时：
>
> 模拟新年倒计时，每隔 1 秒输出一个数字，依次输出 10、9、8、……、1，最后输出：新年快乐！

示例代码：
```java
/* HappyNewYear.java */

package com.anxin_hitsz_02.method_lifecycle.exer;

/**
 * ClassName: HappyNewYear
 * Package: com.anxin_hitsz_02.method_lifecycle.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 22:07
 * @Version 1.0
 */
public class HappyNewYear {
    public static void main(String[] args) {

        for (int i = 10; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i > 0) {
                System.out.println(i);
            } else {
                System.out.println("Happy New Year!");
            }

        }

    }
}

```

## 五、线程安全问题及解决

当我们使用多个线程访问**同一资源**（可以是同一个变量、同一个文件、同一条记录等）的时候，若多个线程**只有读操作**，那么不会发生线程安全问题；但是如果多个线程中对资源有**读和写**的操作，就容易出现线程安全问题。

### 5.1 同一个资源问题和线程安全问题

例题：开启三个窗口售票，总票数为 100 张。

使用实现 `Runnable` 接口的方式，实现卖票：
```java
/* WindowTest.java */

package com.anxin_hitsz_03.notsafe;

/**
 * ClassName: WindowTest
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用实现 Runnable 接口的方式，实现卖票
 * @Author AnXin
 * @Create 2026/2/7 20:31
 * @Version 1.0
 */

class SaleTicket implements Runnable {
    int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }

    }

}

public class WindowTest {
    public static void main(String[] args) {
        SaleTicket s = new SaleTicket();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.setName("窗口 1");
        t2.setName("窗口 2");
        t3.setName("窗口 3");

        t1.start();
        t2.start();
        t3.start();

    }
}

```

使用继承 `Thread` 类的方式，实现卖票：
```java
/* WindowTest1.java */

package com.anxin_hitsz_03.notsafe;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用继承 Thread 类的方式，实现卖票
 * @Author AnXin
 * @Create 2026/2/7 20:37
 * @Version 1.0
 */

class Window extends Thread {
    static int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }

    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}

```

出现了重票和错票！

什么原因导致的 ？
* 线程 `1` 操作 `ticket` 的过程中，**尚未结束**的情况下，其他线程也参与进来，对 `ticket` 进行操作。

如何解决？
* 必须保证一个线程 `A` 在操作 `ticket` 的过程中，其它线程必须等待，直到线程 `A` 操作 `ticket` 结束以后，其它线程才可以进来继续操作 `ticket`。

### 5.2 同步机制解决线程安全问题

要解决多线程并发访问一个资源的安全性问题，Java 中提供了同步机制（synchronized）来解决：
![同步机制（synchronized）解决多线程并发访问一个资源的安全性问题](./images/1563372934332.png "同步机制（synchronized）解决多线程并发访问一个资源的安全性问题")

为了保证每个线程都能正常执行原子操作，Java 引入了线程同步机制。

> 注意：
>
> 在任何时候，最多允许一个线程拥有同步锁，谁拿到锁就进入代码块，其他的线程只能在外等着（`BLOCKED`）。

#### 5.2.1 同步机制解决线程安全问题的原理

同步机制的原理，其实就相当于给某段代码加“锁”；任何线程想要执行这段代码，都要先获得“锁”，我们称它为同步锁。因为 Java 对象在堆中的数据分为对象头、实例变量、空白的填充，而对象头中包含：
* Mark Word：记录了和当前对象有关的 GC、锁标记等信息。
* 指向类的指针：每一个对象需要记录它是由哪个类创建出来的。
* 数组长度（只有数组对象才有）。

哪个线程获得了“同步锁”对象之后，“同步锁”对象就会记录这个线程的 ID，这样其他线程就只能等待了；除非这个线程“释放”了锁对象，其他线程才能重新获得 / 占用“同步锁”对象。

#### 5.2.2 同步代码块和同步方法

##### 5.2.2.1 同步代码块

`synchronized` 关键字可以用于某个区块前面，表示只对这个区块的资源实行互斥访问。

语法格式：
```java
synchronized(同步锁 /* 即：同步监视器 */) {
    需要被同步操作的代码
}
```

> 说明：
> * 需要被同步的代码，即为操作共享数据的代码。
> * 共享数据：即多个线程都需要操作的数据；比如 `ticket`。
> * 需要被同步的代码，在被 `synchronized` 包裹以后，就使得一个线程在操作这些代码的过程中，其它线程必须等待。
> * 同步监视器，俗称“锁”；哪个线程获取了锁，哪个线程就能执行需要被同步的代码。
> * 同步监视器可以使用任何一个类的对象充当；但是，多个线程必须共用同一个同步监视器。

> 注意：
> * 在实现 `Runnable` 接口的方式中，同步监视器可以考虑使用 `this`。
> * 在继承 `Thread` 类的方式中，同步监视器要慎用 `this`，可以考虑使用 `当前类.class`。

使用实现 `Runnable` 接口的方式 - 示例代码：
```java
/* WindowTest.java */

package com.anxin_hitsz_03.runnablesafe;

/**
 * ClassName: WindowTest
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用实现 Runnable 接口的方式，实现卖票 -> 存在线程安全问题的
 *      使用同步代码块解决上述卖票中的线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 20:31
 * @Version 1.0
 */

class SaleTicket implements Runnable {
    int ticket = 100;
    Object obj = new Object();
    Dog dog = new Dog();

    @Override
    public void run() {
//        synchronized (this) {
        while (true) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            synchronized (obj) {    // obj：是唯一的？yes
//            synchronized (dog) {    // dog：是唯一的？yes
            synchronized (this) {    // this：是唯一的？yes，就是题目中的 s
                if (ticket > 0) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }

        }

    }

}

public class WindowTest {
    public static void main(String[] args) {
        SaleTicket s = new SaleTicket();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.setName("窗口 1");
        t2.setName("窗口 2");
        t3.setName("窗口 3");

        t1.start();
        t2.start();
        t3.start();

    }
}

class Dog {

}

```

使用继承 `Thread` 类的方式 - 示例代码：
```java
/* WindowTest.java */

package com.anxin_hitsz_03.threadsafe;

/**
 * ClassName: WindowTest
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用继承 Thread 类的方式，实现卖票
 *      使用同步代码块的方式解决线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 20:37
 * @Version 1.0
 */

class Window extends Thread {
    static int ticket = 100;
    static Object obj = new Object();
    @Override
    public void run() {

        while (true) {
//            synchronized (this) {   // this：此时表示 w1、w2、w3，不能保证锁的唯一性
//            synchronized (obj) {   // obj：使用 static 修饰以后，就能保证其唯一性
            synchronized (Window.class) {   // 结构：Class clz = Window.class，是唯一的
                if (ticket > 0) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }
}

public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}

```

##### 5.2.2.2 同步方法

如果操作共享数据的代码完整地声明在了一个方法中，那么我们就可以将此方法声明为同步方法即可。

`synchronized` 关键字直接修饰方法，表示同一时刻只有一个线程能进入这个方法，其他线程在外面等着。

语法格式：
```java
public synchronized void method() {
    可能会产生线程安全问题的代码
}
```

> 说明：
> * 非静态的同步方法，默认同步监视器是 `this`。
> * 静态的同步方法，默认同步监视器是当前类本身。

使用实现 `Runnable` 接口的方式 - 示例代码：
```java
/* WindowTest1.java */

package com.anxin_hitsz_03.runnablesafe;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.runnablesafe
 * Description:
 *      使用同步方法解决实现 Runnable 接口的线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 21:39
 * @Version 1.0
 */

class SaleTicket1 implements Runnable {
    int ticket = 100;
    boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag) {
            show();
        }

    }

    public synchronized void show() {   // 此时的同步监视器是：this；此题目中即为 s，是唯一的
        if (ticket > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
            ticket--;
        } else {
            isFlag = false;
        }
    }

}

public class WindowTest1 {
    public static void main(String[] args) {
        SaleTicket1 s = new SaleTicket1();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.setName("窗口 1");
        t2.setName("窗口 2");
        t3.setName("窗口 3");

        t1.start();
        t2.start();
        t3.start();

    }
}

```

使用继承 `Thread` 类的方式 - 示例代码：
```java
/* WindowTest1.java */

package com.anxin_hitsz_03.threadsafe;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.threadsafe
 * Description:
 *      使用同步方法解决继承 Thread 类中的线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 21:46
 * @Version 1.0
 */

class Window1 extends Thread {
    static int ticket = 100;
    static Object obj = new Object();
    static boolean isFlag = true;

    @Override
    public void run() {

        while (isFlag) {
            show();
        }

    }

//    public synchronized void show() {    // 此时同步监视器：this；此题目中 this 为 w1、w2、w3，仍然是线程不安全的
    public static synchronized void show() {    // 此时同步监视器：当前类本身，即为 Window1.class，是唯一的
        if (ticket > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
            ticket--;
        } else {
            isFlag = false;
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();
        Window1 w2 = new Window1();
        Window1 w3 = new Window1();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}

```

#### 5.2.3 同步锁机制

在《Thinking in Java》中，是这么说的：对于并发工作，你需要某种方式来防止两个任务访问相同的资源（其实就是共享资源竞争）。防止这种冲突的方法就是当资源被一个任务使用时，在其上加锁。第一个访问某项资源的任务必须锁定这项资源，使其他任务在其被解锁之前，就无法访问它了，而在其被解锁之时，另一个任务就可以锁定并使用它了。

#### 5.2.4 `synchronized` 的锁是什么？

同步锁对象可以是任意类型，但是必须保证竞争“同一个共享资源”的多个线程必须使用同一个“同步锁对象”。

对于同步代码块来说，同步锁对象是由程序员手动指定的（很多时候也是指定为 `this` 或 `类名.class`），但是对于同步方法来说，同步锁对象只能是默认的：
* 静态方法：当前类的 `Class 对象`（`类名.class`）。
* 非静态方法：`this`。

`synchronized` 的好处：解决了线程的安全问题。
`synchronized` 的弊端：在操作共享数据时，多线程其实是串行执行的，意味着性能低。

#### 5.2.5 同步操作的思考顺序

**1. 如何找问题，即代码是否存在线程安全？（非常重要）**

1. 明确哪些代码是多线程运行的代码；
2. 明确多个线程是否有共享数据；
3. 明确多线程运行代码中是否有多条语句操作共享数据。

**2. 如何解决呢？（非常重要）**

对多条操作共享数据的语句，只能让一个线程都执行完；在执行过程中，其他线程不可以参与执行。

即所有操作共享数据的这些语句都要放在同步范围中。

> 注意：
> * 范围太小：不能解决安全问题。
> * 范围太大：因为一旦某个线程抢到锁，其他线程就只能等待；所以范围太大，效率会降低，不能合理利用 CPU 资源。

### 5.3 练习

**练习：**
> 题目：
>
> 银行有一个账户。有两个储户分别向同一个账户存 3000 元，每次存 1000元，存 3 次；每次存完打印账户余额。
>
> 问题：该程序是否有安全问题？如果有，如何解决？
>
> 提示：
> 1. 明确哪些代码是多线程运行代码，须写入 `run()` 方法。
> 2. 明确什么是共享数据。
> 3. 明确多线程运行代码中哪些语句是操作共享数据的。

示例代码：
```java
/* AccountTest.java */

package com.anxin_hitsz_03.exer;

/**
 * ClassName: AccountTest
 * Package: com.anxin_hitsz_03.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 22:15
 * @Version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {

        Account acct = new Account();

        Customer customer1 = new Customer(acct, "甲");
        Customer customer2 = new Customer(acct, "乙");

        customer1.start();
        customer2.start();


    }
}

class Account { // 账户
    private double balance; // 余额

    public synchronized void deposit(double amt) {  // this：是否唯一？即为 acct，是唯一的
        if (amt > 0) {
            balance += amt;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "存钱 1000 元，余额为：" + balance);
    }

}

class Customer extends Thread {
    Account account;

    public Customer(Account acct) {
        this.account = acct;
    }

    public Customer(Account acct, String name) {
        super(name);
        this.account = acct;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.deposit(1000);

        }

    }

}

```

## 六、再谈同步

### 6.1 单例设计模式的线程安全问题

#### 6.1.1 饿汉式没有线程安全问题

饿汉式：在类初始化时就直接创建单例对象，而类初始化过程是没有线程安全问题的。

#### 6.1.2 懒汉式线程安全问题

懒汉式：延迟创建对象，第一次调用 `getInstance` 方法再创建对象。

错误示例 - 示例代码：
```java
/* BankTest.java */

package com.anxin_hitsz_04.threadsafemore.singleton;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_04.threadsafemore.singleton
 * Description:
 *      实现线程安全的懒汉式
 * @Author AnXin
 * @Create 2026/2/8 16:15
 * @Version 1.0
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);

    }
}

class Bank {

    private Bank() {}

    private static Bank instance = null;

    public static Bank getInstance() {
        if (instance == null) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            instance = new Bank();
        }
        return instance;
    }

}

```

正确示例 - 示例代码：
```java
/* BankTest.java */

package com.anxin_hitsz_04.threadsafemore.singleton;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_04.threadsafemore.singleton
 * Description:
 *      实现线程安全的懒汉式
 * @Author AnXin
 * @Create 2026/2/8 16:15
 * @Version 1.0
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);

    }
}

class Bank {

    private Bank() {}

    private static volatile Bank instance = null;

    // 实现线程安全的方式 1
//    public static synchronized Bank getInstance() { // 同步监视器：默认为 Bank.class
//        if (instance == null) {
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            instance = new Bank();
//        }
//        return instance;
//    }

    // 实现线程安全的方式 2
//    public static Bank getInstance() { // 同步监视器：默认为 Bank.class
//        synchronized (Bank.class) {
//            if (instance == null) {
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                instance = new Bank();
//            }
//        }
//
//        return instance;
//    }

    // 实现线程安全的方式 3：相较于方式 1 和方式 2 来讲，效率更高；为了避免出现指令重排，需要讲 instance 声明为 volatile
    public static Bank getInstance() { // 同步监视器：默认为 Bank.class
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    instance = new Bank();
                }
            }
        }

        return instance;
    }

}

```

> 注意：
>
> 上述方式 3 中，存在指令重排问题。
>
> 从 JDK 2 开始，分配空间、初始化、调用构造器会在线程的工作存储区一次性完成，然后复制到主存储区；但是需要 `volatile` 关键字，避免指令重排。

### 6.2 死锁

> 线程的同步机制带来的问题：死锁。

不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁。

![线程的死锁](./images/thread-lock.png "线程的死锁")

一旦出现死锁，整个程序既不会发生异常，也不会给出任何提示，只是所有线程处于阻塞状态，无法继续。

我们编写程序时，要避免出现死锁。

示例代码：
```java
/* DeadLockTest.java */

package com.anxin_hitsz_04.threadsafemore.deadlock;

/**
 * ClassName: DeadLockTest
 * Package: com.anxin_hitsz_04.threadsafemore.deadlock
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 16:48
 * @Version 1.0
 */
public class DeadLockTest {
    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

    }
}

```

**诱发死锁的原因：**
* 条件 1：互斥条件；
* 条件 2：占用且等待；
* 条件 3：不可抢夺（或不可抢占）；
* 条件 4：循环等待。

以上四个条件，同时出现就会触发死锁。

**解决死锁：**

死锁一旦出现，基本很难人为干预，只能尽量规避。可以考虑打破上面的诱发条件。
* 针对条件 1：互斥条件基本上无法被破坏；因为线程需要通过互斥解决安全问题。
* 针对条件 2：可以考虑一次性申请所有所需的资源，这样就不存在等待的问题。
* 针对条件 3：占用部分资源的线程在进一步申请其他资源时，如果申请不到，就主动释放掉已经占用的资源。
* 针对条件 4：可以将资源改为线程顺序；申请资源时，先申请序号较小的，这样避免循环等待问题。

### 6.3 JDK 5.0 新特性：`Lock`（锁）

JDK 5.0 的新增功能，保证线程的安全。与采用 `synchronized` 相比，`Lock` 可提供多种锁方案，更灵活、更强大。`Lock` 通过显式定义同步锁对象来实现同步；同步锁使用 `Lock` 对象充当。

`java.util.concurrent.locks.Lock` 接口是控制多个线程对共享资源进行访问的工具。锁提供了对共享资源的独占访问，每次只能有一个线程对 `Lock` 对象加锁，线程开始访问共享资源之前应先获得 `Lock` 对象。

在实现线程安全的控制中，比较常用的是 `ReentrantLock`，可以显式加锁、释放锁。
* `ReentrantLock` 类实现了 `Lock` 接口，它拥有与 `synchronized` 相同的并发性和内存语义，但是添加了类似锁投票、定时锁等候和可中断锁等候的一些特性。此外，它还提供了在激烈争用情况下更佳的性能。

`Lock` 锁也称同步锁，加锁与释放锁方法如下：
* `public void lock()`：加同步锁。
* `public void unlock()`：释放同步锁。

步骤：
1. 创建 `Lock` 的实例，需要确保多个线程共用同一个 `Lock` 实例；需要考虑将此对象声明为 `static final`。
2. 执行 `lock()` 的方法，锁定对共享资源的调用。
3. `unlock()` 的调用，释放对共享数据的锁定。

代码结构：
```java
class A {
    // 1. 创建 Lock 的实例，必须确保多个线程共享同一个 Lock 实例
    private final ReentrantLock lock = new ReentrantLock();
    public void m() {
        // 2. 调动 lock()，实现需共享的代码的锁定
        lock.lock();
        try {
            // 保证线程安全的代码
        } finally {
            // 3. 调用 unlock()，释放共享代码的锁定
            lock.unlock();
        }
    }
}

```

> 注意：
>
> 如果同步代码有异常，要将 `unlock()` 写入 `finally` 语句块。

示例代码：
```java
/* LockTest.java */

package com.anxin_hitsz_04.threadsafemore.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用继承 Thread 类的方式，实现卖票
 * @Author AnXin
 * @Create 2026/2/7 20:37
 * @Version 1.0
 */

class Window extends Thread {
    static int ticket = 100;

    // 1. 创建 Lock 的实例，需要确保多个线程共用同一个 Lock 实例！需要考虑将此对象声明为 static final
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 执行 lock() 方法，锁定对共享资源的调用
                lock.lock();

                if (ticket > 0) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }  finally {
                // 3. unlock() 的调用，释放对共享数据的锁定
                lock.unlock();

            }

        }

    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}

```

`synchronized` 与 `Lock` 的对比：
1. `Lock` 是显式锁（手动开启和关闭锁，别忘记关闭锁）；`synchronized` 是隐式锁，出了作用域、遇到异常等自动解锁。
2. `Lock` 只有代码块锁，`synchronized` 有代码块锁和方法锁。
3. 使用 `Lock` 锁，JVM 将花费较少的时间来调度线程，性能更好；并且具有更好的扩展性（提供更多的子类），更体现面向对象。
4. `Lock` 锁可以对读不加锁，对写加锁；`synchronized` 不可以。
5. `Lock` 锁可以有多种获取锁的方式，可以从 `sleep` 的线程中抢到锁；`synchronized` 不可以。

> 面试题：
>
> `synchronized` 同步的方式与 `Lock` 的对比？
> * `synchronized` 不管是同步代码块还是同步方法，都需要在结束一对 `{}` 之后，释放对同步监视器的调用。
> * `Lock` 是通过两个方法控制需要被同步的代码，更灵活一些。
> * `Lock` 作为接口，提供了多种实现类，适合更多更复杂的场景，效率更高。

> 说明：
>
> 开发中建议处理线程安全问题优先使用顺序为：`Lock -> 同步代码块 -> 同步方法`。

## 七、线程的通信

### 7.1 线程间通信

**为什么要处理线程间通信：**

当我们**需要多个线程**来共同完成一件任务，并且我们希望它们**有规律地执行**，那么多线程之间需要一些通信机制，可以协调它们地工作，以此实现多线程共同操作一份数据。

涉及到三个方法的使用：
1. `wait()`：线程一旦执行此方法，就进入等待状态；同时，会释放对同步监视器的调用。
2. `notify()`：一旦执行此方法，就会唤醒被 `wait()` 的线程中优先级最高的那一个线程（如果被 `wait()` 的多个线程的优先级相同，则随机唤醒一个）；被唤醒的线程从当初被 `wait` 的位置继续执行。
3. `notifyAll()`：一旦执行此方法，就会唤醒所有被 `wait` 的线程。

### 7.2 等待唤醒机制

这是多个线程间的一种**协作机制**。谈到线程，我们经常想到的是线程间的**竞争（race）**，比如去争夺锁；但这并不是故事的全部，线程间也会有协作机制。

在一个线程满足某个条件时，就进入等待状态（`wait()` / `wait(time)`），等待其他线程执行完它们的指定代码过后再将其唤醒（`notify()`）；或可以指定 `wait` 的时间，等时间到了自动唤醒；在有多个线程进行等待时，如果需要，可以使用 `notifyAll()` 来唤醒所有的等待线程。`wait` / `notify` 就是线程间的一种协作机制。

1. `wait`：线程不再活动，不再参与调度，进入 `wait set` 中，因此不会浪费 CPU 资源，也不会去竞争锁了；这时的线程状态是 `WAITING` 或 `TIMED_WAITING`。它还要等着别的线程执行一个**特别的动作**，也即“**通知（`notify`）**”或者等待时间到，在这个对象上等待的线程从 `wait set` 中释放出来，重新进入调度队列（`ready queue`）中。
2. `notify`：选取所通知对象的 `wait set` 中的一个线程释放。
3. `notifyAll`：释放所通知对象的 `wait set` 上的全部线程。

> 注意：
>
> 被通知的线程被唤醒后也不一定能立即恢复执行，因为它当初中断的地方是在同步块内，而此刻它已经不持有锁，所以它需要再次尝试去获取锁（很可能面临其它线程的竞争），成功后才能在当初调用 `wait` 方法之后的地方恢复执行。

总结如下：
* 如果能获取锁，线程就从 `WAITING` 状态变成 `BUNNABLE`（可运行）状态。
* 否则，线程就从 `WAITING` 状态又变成 `BLOCKED`（等待锁）状态。

### 7.3 调用 `wait` 和 `notify` 需注意的细节

`wait` 方法与 `notify` 方法必须要由同一个锁对象调用，因为对应的锁对象可以通过 `notify` 唤醒使用同一个锁对象调用的 `wait` 方法后的线程。

`wait` 方法与 `notify` 方法是属于 `Object` 类的方法的，因为锁对象可以是任意对象，而任意对象的所属类都是继承了 `Object` 类的。

`wait` 方法与 `notify` 方法必须要在同步代码块或者是同步函数中使用，因为必须要通过锁对象调用这 2 个方法，否则会报 `java.lang.IllegalMonitorStateException` 异常。

> 注意：
>
> 此三个方法的使用，必须是在同步代码块或同步方法中，不能用在 `Lock` 中。（`Lock` 需要配合 `Condition` 实现线程间的通信。）
>
> 此三个方法的调用者必须是同步监视器，否则会报 `IllegalMonitorStateException` 异常。
>
> 此三个方法声明在 `Object` 类中。

### 7.4 生产者与消费者问题

等待唤醒机制可以解决经典的“生产者与消费者”的问题。生产者与消费者问题（Producer-consumer problem），也称有限缓冲问题（Bounded-buffer problem），是一个多线程同步问题的经典案例。该问题描述了两个（或多个）**共享固定大小缓冲区的线程** —— 即所谓的“生产者”和“消费者” —— 在实际运行时会发生的问题。

生产者的主要作用是生成一定量的数据放到缓冲区中，然后重复此过程；与此同时，消费者也在缓冲区消耗这些数据。**该问题的关键就是要保证生产者不会在缓冲区满时加入数据，消费者也不会在缓冲区空时消耗数据。**

**生产者与消费者问题中其实隐含了两个问题：**
* 线程安全问题：
  * 因为生产者与消费者共享数据缓冲区，产生安全问题；不过这个问题可以使用同步解决。
* 线程的协调工作问题：
  * 要解决该问题，就必须让生产者线程在缓冲区满时等待（`wait`），暂停进入阻塞状态，等到下次消费者消耗了缓冲区中的数据的时候，通知（`notify`）正在等待的线程恢复到就绪状态，重新开始往缓冲区添加数据；同样，也可以让消费者线程在缓冲区空时进入等待（`wait`），暂停进入阻塞状态，等到生产者往缓冲区添加数据之后，再通知（`notify`）正在等待的线程恢复到就绪状态。通过这样的通信机制来解决此类问题。

### 7.5 面试题：区分 `sleep()` 和 `wait()`

相同点：一旦执行，都会使得当前线程结束执行状态，进入阻塞状态。

不同点：
1. 定义方法所属的类：`sleep()` 在 `Thread` 中定义；`wait()` 在 `Object` 中定义。
2. 使用范围的不同：`sleep()` 可以在任何需要使用的位置被调用；`wait()` 必须使用在同步代码块或同步方法中。
3. 都在同步结构中使用的时候，是否释放同步监视器的操作不同：`sleep()` 不会释放同步监视器；`wait()` 会释放同步监视器。
4. 结束等待的方式不同：`sleep()` 指定时间一到就结束阻塞；`wait()` 可以指定时间也可以无限等待直到 `notify` 或 `notifyAll`。

> `wait()` 和 `sleep()` 的区别？
> * 相同点：一旦执行，当前线程都会进入阻塞状态。
> * 不同点：
>   * 声明的位置：`wait()` 声明在 `Object` 类中；`sleep()` 声明在 `Thread` 类中，静态的。
>   * 使用的场景不同：`wait()` 只能使用在同步代码块或同步方法中；`sleep()` 可以在任何需要使用的场景。
>   * 使用在同步代码块或同步方法中：`wait()` 一旦执行，会释放同步监视器；`sleep()` 一旦执行，不会释放同步监视器。
>   * 结束阻塞的方式：`wait()` 到达指定时间自动结束阻塞，或通过被 `notify` 唤醒结束阻塞；`sleep()` 到达指定时间自动结束阻塞。

### 7.6 是否释放锁的操作

任何线程进入同步代码块、同步方法之前，必须先获得对同步监视器的锁定。那么何时会释放对同步监视器的锁定呢？

#### 7.6.1 释放锁的操作

当前线程的同步方法、同步代码块执行结束。

当前线程在同步代码块、同步方法中遇到 `break`、`return` 终止了该代码块、该方法的继续执行。

当前线程在同步代码块、同步方法中出现了未处理的 `Error` 或 `Exception`，导致当前线程异常结束。

当前线程在同步代码块、同步方法中执行了锁对象的 `wait()` 方法，当前线程被挂起，并释放锁。

#### 7.6.2 不会释放锁的操作

线程执行同步代码块或同步方法时，程序调用 `Thread.sleep()`、`Thread.yield()` 方法暂停当前线程的执行。

线程执行同步代码块时，其他线程调用了该线程的 `suspend()` 方法将该线程挂起，该线程不会释放锁（同步监视器）。

> 注意：
>
> 应尽量避免使用 `suspend()` 和 `resume()` 这样的过时方法来控制线程。

### 7.7 案例

**案例 1：**
> 使用两个线程打印 1 - 100，使用线程 1 和线程 2 交替打印。

示例代码：
```java
/* PrintNumberTest.java */

package com.anxin_hitsz_05.communication;

/**
 * ClassName: PrintNumberTest
 * Package: com.anxin_hitsz_05.communication
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 18:25
 * @Version 1.0
 */

class PrintNumber implements Runnable {

    private int number = 1;
    Object obj = new Object();

    @Override
    public void run() {

        while (true) {
//            synchronized (this) {
            synchronized (obj) {

                obj.notify();

                if (number <= 100) {

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        obj.wait(); // 线程一旦执行此方法，就进入等待状态；同时，会释放对同步监视器的调用
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }

    }
}

public class PrintNumberTest {
    public static void main(String[] args) {

        PrintNumber p = new PrintNumber();

        Thread t1 = new Thread(p, "线程 1");
        Thread t2 = new Thread(p, "线程 2");

        t1.start();
        t2.start();

    }
}

```

**案例 2 - 生产者和消费者：**
> 生产者（Productor）将产品交给店员（Clerk），而消费者（Customer）从店员处取走产品，店员一次只能持有固定数量的产品（比如 20）。如果生产者试图生产更多的产品，店员会告诉生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。

分析：
1. 是否是多线程问题？
   * 是：生产者线程、消费者线程。
2. 是否有共享数据？
   * 是：共享数据是产品。
3. 是否有线程安全问题？
   * 有：因为有共享数据。
4. 是否需要处理线程安全问题？
   * 是：使用同步机制处理该线程安全问题。
5. 是否存在线程间的通信？
   * 存在。

示例代码：
```java
/* ProducerConsumerTest.java */

package com.anxin_hitsz_05.communication;

/**
 * ClassName: ProducerConsumerTest
 * Package: com.anxin_hitsz_05.communication
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 18:50
 * @Version 1.0
 */

class Clerk {   // 店员
    private int productNum = 0; // 产品的数量

    // 增加产品数量的方法
    public synchronized void addProduct() {

        if (productNum >= 20) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第 " + productNum + " 个产品");

            // 唤醒
            notifyAll();
        }

    }

    // 减少产品数量的方法
    public synchronized void minusProduct() {

        if (productNum <= 0) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "消费了第 " + productNum + " 个产品");
            productNum--;

            // 唤醒
            notifyAll();
        }

    }
}

class Producer extends Thread {    // 生产者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("生产者开始生产产品 ……");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

class Consumer extends Thread {    // 消费者

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("消费者开始消费产品 ……");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.minusProduct();
        }
    }
}


public class ProducerConsumerTest {
    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producer pro1 = new Producer(clerk);
        Consumer con1 = new Consumer(clerk);
        Consumer con2 = new Consumer(clerk);

        pro1.setName("生产者 1");
        con1.setName("消费者 1");
        con2.setName("消费者 2");

        pro1.start();
        con1.start();
        con2.start();


    }
}

```

## 八、JDK 5.0 新增线程创建方式

### 8.1 新增方式一：实现 `Callable` 接口

与使用 `Runnable` 相比，`Callable` 功能更强大些：
* `call()` 相比 `run()` 方法，可以有返回值，更灵活。
* `call()` 方法可以使用 throws 的方式抛出（处理）异常，更灵活。
* `Callable` 使用了泛型参数，支持泛型的返回值，可以指明具体的 `call()` 的返回值类型，更灵活（需要借助 `FutureTask` 类，获取返回结果）。

`Future` 接口：
* 可以对具体 `Runnable`、`Callable` 任务的执行结果进行取消、查询是否完成、获取结果等。
* `FutureTask` 是 `Future` 接口的唯一的实现类。
* `FutureTask` 同时实现了 `Runnable`、`Future` 接口，它既可以作为 `Runnable` 被线程执行，又可以作为 `Future` 得到 `Callable` 的返回值。

缺点：在获取分线程 `call()` 的执行结果（返回值）的时候，当前线程（或是主线程）受阻塞，效率较低。

示例代码：
```java
/* CallableTest.java */

package com.anxin_hitsz_06.createmore.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ClassName: CallableTest
 * Package: com.anxin_hitsz_06.createmore.callable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 20:05
 * @Version 1.0
 */

/*
* 创建多线程的方式三：实现 Callable（jdk 5.0 新增的）
* */
// 1. 创建一个实现 Callable 的实现类
class NumThread implements Callable {
    // 2. 实现 call 方法，将此线程需要执行的操作声明在 call() 中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
            Thread.sleep(1000);
        }
        return sum;
    }

}

public class CallableTest {
    public static void main(String[] args) {
        // 3. 创建 Callable 接口实现类的对象
        NumThread numThread = new NumThread();

        // 4. 将此 Callable 接口实现类的对象作为传递到 FutureTask 构造器中，创建 FutureTask 的对象
        FutureTask futureTask = new FutureTask(numThread);

        // 5. 将 FutureTask 的对象作为参数传递到 Thread 类的构造器中，创建 Thread 对象，并调用 start()
        Thread t1 = new Thread(futureTask);
        t1.start();

        System.out.println("main() 线程");

        try {
            // 6. 获取 Callable 中 call 方法的返回值
            // get() 返回值即为 FutureTask 构造器参数 Callable 实现类重写的 call() 的返回值
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

```

### 8.2 新增方式二：使用线程池

#### 8.2.1 现有问题

如果并发的线程数量很多，并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。

那么有没有一种办法使得线程可以复用，即执行完一个任务，并不销毁，而是可以继续执行其他的任务？

**思路：** 提前创建好多个线程，放入线程池中，使用时直接获取；使用完放回池中。可以避免频繁创建销毁、实现重复利用。

#### 8.2.2 线程池的理解

线程池的理解：
![线程池的理解](./images/线程池的理解.jpg "线程池的理解")

**优点：**
* 提高响应速度（减少了创建新线程的时间）。
* 降低资源消耗（重复利用线程池中的线程，不需要每次都创建）。
* 便于线程管理：
  * `corePoolSize`：核心池的大小。
  * `maximumPoolSize`：最大线程数。
  * `keepAliveTime`：线程没有任务时最多保持多长时间后会终止。
  * ……

> 使用线程池创建多线程的方式的好处：
> 1. 提高了程序执行的效率。（因为线程已经提前创建好了。）
> 2. 提高了资源的复用率。（因为执行完的线程并未销毁，而是可以继续执行其他的任务。）
> 3. 可以设置相关的参数，对线程池中的线程的使用进行管理。

#### 8.2.3 线程池相关 API

JDK 5.0 之前，我们必须手动自定义线程池。从 JDK 5.0 开始，Java 内置线程池相关的 API。在 java.util.concurrent 包下提供了线程池相关的 API：`ExecutorService` 和 `Executors`。

`ExecutorService`：真正的线程池接口；常见子类 `ThreadPoolExecutor`。
* `void execute(Runnable command)`：执行任务 / 命令，没有返回值；一般用来执行 `Runnable`。
* `<T> Future<T> submit(Callable<T> task)`：执行任务，有返回值；一般用来执行 `Callable`。
* `void shutdown()`：关闭连接池。

`Executors`：一个线程池的工厂类，通过此类的静态工厂方法可以创建多种类型的线程池对象。
* `Executors.newCachedThreadPool()`：创建一个可根据需要创建新线程的线程池。
* `Executors.newFixedThreadPool(int nThreads)`：创建一个可重用固定线程数的线程池。
* `Executors.newSingleThreadExecutor()`：创建一个只有一个线程的线程池。
* `Executors.newScheduledThreadPool(int corePoolSize)`：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。

示例代码：
```java
/* ThreadPool.java */

package com.anxin_hitsz_06.createmore.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPool
 * Package: com.anxin_hitsz_06.createmore.pool
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 20:13
 * @Version 1.0
 */

/*
* 创建并使用多线程的第四种方法：使用线程池
* */
class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // 1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        // 设置线程池的属性
        System.out.println(service.getClass()); // ThreadPoolExecutor
        service1.setMaximumPoolSize(50);    // 设置线程池中线程数的上限

        // 2. 执行指定的线程的操作；需要提供实现 Runnable 接口或 Callable 接口实现类的对象
        service.execute(new NumberThread());    // 适合适用于 Runnable
        service.execute(new NumberThread1());   // 适合适用于 Runnable

//        service.submit(Callable callable);  // 适合适用于 Callable

        // 3. 关闭连接池
        service.shutdown();
    }

}

```
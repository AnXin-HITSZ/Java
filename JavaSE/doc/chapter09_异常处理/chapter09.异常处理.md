# 第九章：异常处理

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 09 章](./images/第3阶段：Java高级应用-第09章.png "第 3 阶段：Java 高级应用 - 第 09 章")

## 一、异常概述

### 1.1 程序的异常

在使用计算机语言进行项目开发的过程中，即使程序员把代码写得**尽善尽美**，在系统的运行过程中仍然会遇到一些问题，因为很多问题不是靠代码能够避免的。比如：客户**输入数据的格式问题**、**读取文件是否存在**、**网络是否始终保持通畅**等等。

**异常**：指的是程序在执行过程中，出现的非正常情况，如果不处理最终会导致 JVM 的非正常停止。

> 注意：
>
> 异常指的并不是语法错误和逻辑错误。
> * 语法错了，编译不通过，不会产生字节码文件，根本不能运行。
> * 代码逻辑错误，只是没有得到想要的结果。例如：求 a 与 b 的和，错误写成了 a - b。

### 1.3 异常的抛出机制

Java 中是如何表示不同的异常情况，又是如何让程序员得知并处理异常的呢？

Java 中把不同的异常用不同的类表示，一旦发生某种异常，就创建该异常类型的对象，并且抛出（`throw`）。然后程序员可以捕获（`catch`）到这个异常对象并处理；如果没有捕获（`catch`）这个异常对象，那么这个异常对象将会导致程序终止。

### 1.4 如何对待异常

对于程序出现的异常，一般有两种解决方法：一是遇到错误就终止程序的运行；另一种方法是程序员在编写程序时，就充分考虑到各种可能发生的异常和错误，极力预防和避免，实在无法避免的，要编写相应的代码进行异常的检测以及**异常的处理**，保证代码的**健壮性**。

## 二、Java 异常体系

`java.lang.Throwale`：异常体系的根父类（直接继承于 `Object` 类）。
* `java.lang.Error`：错误。Java 虚拟机无法解决的严重问题，如 JVM 系统内部错误、资源耗尽等严重情况。一般不编写针对性的代码进行处理。
  * `StackOverflowError`、`OutOfMemoryError`。
* `java.lang.Exception`：异常。我们可以编写针对性的代码进行处理。
  * 编译时异常（受检异常）：在执行 javac.exe 命令时，出现的异常。
    * `ClassNotFoundException`；
    * `FileNotFoundException`；
    * `IOException`。
  * 运行时异常（非受检异常）：在执行 java.exe 命令时，出现的异常。
    * `ArrayIndexOutOfBoundsException`；
    * `NullPointerException`；
    * `ClassCastException`；
    * `NumberFormatException`；
    * `InputMismatchException`；
    * `ArithmeticException`。

### 2.1 `Throwable`

`java.lang.Throwable` 类是 Java 程序执行过程中发生的异常事件对应的类的根父类。

`Throwable` 中的常用方法：
* `public void printStackTrace()`：打印异常的详细信息。
  * 包含了异常的类型、异常的原因、异常出现的位置，在开发和调试阶段都得使用 `printStackTrace`。
* `public String getMessage()`：获取发生异常的原因。

### 2.2 `Error` 和 `Exception`

`Throwable` 可分为两类：`Error` 和 `Exception`。分别对应着 `java.lang.Error` 与 `java.lang.Exception` 两个类。

`Error`：Java 虚拟机无法解决的严重问题；如JVM 系统内部错误、资源耗尽等严重情况。一般不编写针对性的代码进行处理。
* 例如：`StackOverflowError`（栈内存溢出）和 `OutOfMemoryError`（堆内存溢出，简称 `OOM`）。

`Exception`：其它因编程错误或偶然的外在因素导致的一般性问题，需要使用针对性的代码进行处理，使程序继续运行；否则一旦发生异常，程序也会挂掉。例如：
* 空指针访问；
* 试图读取不存在的文件；
* 网络连接中断；
* 数组角标越界。

> 说明：
> 1. 无论是 `Error` 还是 `Exception`，还有很多子类，异常的类型非常丰富。当代码运行出现异常时，特别是我们不熟悉的异常时，不要紧张，把异常的简单类名拷贝到 API 中去查看和认识它即可。
> 2. 我们本章讲述的异常处理，其实针对的就是 `Exception`。

![异常类型、原因及出现位置示例](./images/image-20220511161910951.png "异常类型、原因及出现位置示例")

### 2.3 编译时异常和运行时异常

Java 程序的执行分为编译时过程和运行时过程。有的错误只有在**运行时**才会发生。比如：除数为 0、数组下标越界等。

![Java 源程序的编译与运行](./images/image-20220330002449526.png "Java 源程序的编译与运行")

因此，根据异常可能出现的阶段，可以将异常分为：
* **编译时期异常**（即 checked 异常、受检异常）：在代码编译阶段，编译器就能明确**警示**当前代码**可能发生（不是一定发生）**xx 异常，并**明确督促**程序员提前编写处理它的代码。如果程序员**没有编写**对应的异常处理代码，则编译器就会直接判定编译失败，从而不能生成字节码文件。通常，这类异常的发生不是由程序员的代码引起的，或者不是靠加简单判断就可以避免的。例如：`FileNotFoundException`（文件找不到异常）。
* **运行时期异常**（即 runtime 异常、unchecked 异常、非受检异常）：在代码编译阶段，编译器完全 不做任何检查，无论该异常是否会发生，编译器都不给出任何提示；只有等代码运行起来并确实发生了 xx 异常，它才能被发现。通常，这类异常是由程序员的代码编写不当引起的，只要稍加判断，或者细心检查就可以避免。
  * `java.lang.RuntimeException` 类及它的子类都是运行时异常。比如：`ArrayIndexOutOfBoundsException`（数组下标越界异常）、`ClassCastException`（类型转换异常）。

非受检（unchecked）异常与受检（checked）异常：
![非受检（unchecked）异常与受检（checked）异常](./images/1562771528807.png "非受检（unchecked）异常与受检（checked）异常")

> 注意：
>
> `Error` 也属于非受检（unchecked）异常。

## 三、常见的错误和异常

### 3.1 `Error`

最常见的就是 `VirtualMachineError`，它有两个经典的子类：`StackOverflowError`、`OutOfMemoryError`。

示例代码：
```java
/* ErrorTest.java */

package com.anxin_hitsz_01.throwable;

/**
 * ClassName: ErrorTest
 * Package: com.anxin_hitsz_01.throwable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/4 16:00
 * @Version 1.0
 */
public class ErrorTest {
    public static void main(String[] args) {

        // 举例 1：栈内存溢出的错误 StackOverflowError
//        main(args);

        // 举例 2：OutOfMemoryError: Java heap space
//        byte[] arr = new byte[1024 * 1024 * 100]; // 100 mb

    }
}

```

### 3.2 运行时异常

示例代码：
```java
/* ExceptionTest.java */

package com.anxin_hitsz_01.throwable;

import org.junit.Test;

import java.util.Date;
import java.util.Scanner;

/**
 * ClassName: ExceptionTest
 * Package: com.anxin_hitsz_01.throwable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/4 16:13
 * @Version 1.0
 */
public class ExceptionTest {

    /*
    * ArrayIndexOutOfBoundsException
    * */
    @Test
    public void test1() {
        int[] arr = new int[10];
        System.out.println(arr[10]);
    }

    /*
    * NullPointerException
    * */
    @Test
    public void test2() {
//        String str = "hello";
//        str = null;
//        System.out.println(str.toString());

//        int[] arr = new int[10];
//        arr = null;
//        System.out.println(arr[0]);

        int[][] arr1 = new int[10][];
        System.out.println(arr1[0][0]);
    }

    /*
    * ClassCastException
    * */
    @Test
    public void test3() {
        Object obj = new String();
//        String str = (String)obj;

        Date date = (Date)obj;
    }

    /*
    * NumberFormatException
    * */
    @Test
    public void test4() {
        String str = "123";
        str = "abc";
        int i = Integer.parseInt(str);
        System.out.println(i);
    }

    /*
    * InputMismatchException
    * */
    @Test
    public void test5() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(num);
    }

    /*
    * ArithmeticException
    * */
    @Test
    public void test6() {
        int num = 10;
        System.out.println(num / 0);
    }

}

```

### 3.3 编译时异常

示例代码：
```java
/* ExceptionTest.java */

package com.anxin_hitsz_01.throwable;

import org.junit.Test;

import java.util.Date;
import java.util.Scanner;

/**
 * ClassName: ExceptionTest
 * Package: com.anxin_hitsz_01.throwable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/4 16:13
 * @Version 1.0
 */
public class ExceptionTest {

    /*
    * ArrayIndexOutOfBoundsException
    * */
    @Test
    public void test1() {
        int[] arr = new int[10];
        System.out.println(arr[10]);
    }

    /*
    * NullPointerException
    * */
    @Test
    public void test2() {
//        String str = "hello";
//        str = null;
//        System.out.println(str.toString());

//        int[] arr = new int[10];
//        arr = null;
//        System.out.println(arr[0]);

        int[][] arr1 = new int[10][];
        System.out.println(arr1[0][0]);
    }

    /*
    * ClassCastException
    * */
    @Test
    public void test3() {
        Object obj = new String();
//        String str = (String)obj;

        Date date = (Date)obj;
    }

    /*
    * NumberFormatException
    * */
    @Test
    public void test4() {
        String str = "123";
        str = "abc";
        int i = Integer.parseInt(str);
        System.out.println(i);
    }

    /*
    * InputMismatchException
    * */
    @Test
    public void test5() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(num);
    }

    /*
    * ArithmeticException
    * */
    @Test
    public void test6() {
        int num = 10;
        System.out.println(num / 0);
    }

    // ********** 以上是运行时异常，以下是编译时异常 **********

    /*
    * ClassNotFoundException
    * */
    @Test
    public void test7() {
//        Class clz = Class.forName("java.lang.String");
    }

    /*
    * FileNotFoundException
    * IOException
    * */
    @Test
    public void test8() {
//        File file = new File("D:\\hello.txt");
//
//        FileInputStream fis = new FileInputStream(file);    // 可能报 FileNotFoundException
//
//        int data = fis.read();  // 可能报 IOException
//        while (data != -1) {
//            System.out.println((char)data);
//            data = fis.read();  // 可能报 IOException
//        }
//
//        fis.close();    // 可能报 IOException

    }

}

```

## 四、异常的处理

### 4.1 异常处理概述

在编写程序时，经常要在可能出现错误的地方加上检测的代码，如进行 `x / y` 运算时，要检测分母为 0、数据为空、输入的不是数据而是字符等。过多的 if - else 分支会导致程序的**代码加长**、**臃肿**、**可读性差**，程序员需要花费很大的精力“堵漏洞”。因此采用异常处理机制。

**Java 异常处理：**

Java 采用的异常处理机制，是**将异常处理的程序代码集中在一起**，与正常的程序代码分开，使得程序简洁、优雅，并易于维护。

Java 异常处理的方式：
* 方式一：`try` - `catch` - `finally`。
* 方式二：`throws` + `异常类型`。

### 4.2 方式 1：捕获异常（`try` - `catch` - `finally`）

Java 提供了异常处理的**抓抛模型**。
* 前面提到，Java 程序的执行过程中如出现异常，会生成一个异常类对象，该异常对象将被提交给 Java 运行时系统，这个过程称为抛出（`throw`）异常。
* 如果一个方法内抛出异常，该异常对象会被抛给调用者方法中处理；如果异常没有在调用者方法中处理，它将继续被抛给这个调用方法的上层方法。这个过程将一直继续下去，直到异常被处理。这一过程称为捕获（`catch`）异常。
* 如果一个异常回到 `main()` 方法，并且 `main()` 方法也不处理，则程序运行终止。

过程详述：
* 过程 1 - “抛”：
  * 程序在执行的过程当中，一旦出现异常，就会在出现异常的代码处，生成对应异常类的对象，并将此对象抛出。一旦抛出，此程序就不执行其后的代码了。
* 过程 2 - “抓”：
  * 针对于过程 1 中抛出的异常对象，进行捕获处理。此捕获处理的过程，就称为“抓”。一旦将异常进行了处理，代码就可以继续执行。

#### 4.2.1 `try` - `catch` - `finally` 基本格式

捕获异常语法如下：
```java
try {
    ... // 可能产生异常的代码
} catch (异常类型1 e) {
    ... // 当产生异常类型 1 型异常时的处置措施
} catch (异常类型2 e) {
    ... // 当产生异常类型 2 型异常时的处置措施
} finally {
    ... // 无论是否发生异常，都无条件执行的语句
}
```

##### 4.2.1.1 整体执行过程

当某段代码可能发生异常，不管这个异常是编译时异常（受检异常）还是运行时异常（非受检异常），我们都可以使用 `try` 块将它括起来，并在 `try` 块下面编写 `catch` 分支尝试捕获对应的异常对象。
* 如果在程序运行时，`try` 块中的代码没有发生异常，那么 `catch` 所有的分支都不执行。
* 如果在程序运行时，`try` 块中的代码发生了异常，根据异常对象的类型，将从上到下选择第一个匹配的 `catch` 分支执行。此时 `try` 中发生异常的语句下面的代码将不执行，而整个 `try ... catch` 之后的代码可以继续运行。
* 如果在程序运行时，`try` 块中的代码发生了异常，但是所有 `catch` 分支都无法匹配（捕获）这个异常，那么 JVM 将会终止当前方法的执行，并把异常对象“抛”给调用者。如果调用者不处理，程序就挂了。

整体执行过程如下：
![整体执行过程](./images/image-20220503122722605.png "整体执行过程")

##### 4.2.1.2 `try`

捕获异常的第一步是用 `try { ... }` 语句块选定捕获异常的范围，将可能出现异常的业务逻辑代码放在 `try` 语句块中。

##### 4.2.1.3 `catch (Exceptiontype e)`

`catch` 分支，分为两个部分：`catch ()` 中编写异常类型和异常参数名，而 `{}` 中编写如果发生了这个异常则要做什么处理的代码。

如果明确知道产生的是何种异常，可以用该异常类作为 `catch` 的参数；也可以用其父类作为 `catch` 的参数。
* 比如：可以用 `ArithmeticException` 类作为参数的地方，就可以用 `RuntimeException` 类作为参数，或者用所有异常的父类 `Exception` 类作为参数；但不能是与 `ArithmeticException` 类无关的异常，如 `NullPointerException`（`catch` 中的语句将不会执行）。

每个 `try` 语句块可以伴随一个或多个 `catch` 语句，用于处理可能产生的不同类型的异常对象。

如果有多个 `catch` 分支，并且多个异常类型有父子类关系，必须保证小的子异常类型在上，大的父异常类型在下；否则将报错。

`catch` 中常用异常处理的方式：
* `public String getMessage()`：获取异常的描述信息，返回字符串。
* `public void printStackTrace()`：打印异常的跟踪栈信息并输出到控制台。包含了异常的类型、异常的原因，还包括异常出现的位置，在开发和调试阶段都得使用 `printStackTrace()`。
  * 示例：![`printStackTrace()` 使用示例](./images/image-20220331180736381.png "`printStackTrace()` 使用示例")

##### 4.1.2.4 使用细节

将可能出现异常的代码声明在 `try` 语句中，一旦代码出现异常，就会自动生成一个对应异常类的对象，并将此对象抛出。

针对于 `try` 中抛出的异常类的对象，使用之后的 `catch` 语句进行匹配；一旦匹配上，就进入 `catch` 语句块进行处理。一旦处理结束，代码就可继续向下执行。

如果声明了多个 `catch` 结构，不同的异常类型在不存在子父类关系的情况下，谁声明在上面或谁声明在下面都可以。如果多个异常类型满足子父类关系，则必须将子类声明在父类结构的上面，否则将报错。

catch 中异常处理的方式：
1. 自己编写输出的语句。
2. （推荐）`printStackTrace()`：打印异常的详细信息。
3. `getMessage()`：获取发生异常的原因。

`try` 中声明的变量，出了 `try` 结构之后，就不可以进行调用了。

`try - catch` 结构是可以嵌套使用的。

##### 4.1.2.5 开发体会

对于运行时异常：
* 开发中，通常就不进行显式的处理了。
* 一旦在程序执行中，出现了运行时异常，那么就根据异常的提示信息修改代码即可。

对于编译时异常：
* 一定要进行处理，否则编译不通过。

##### 4.1.2.6 使用示例

示例代码：
```java
/* ExceptionHandleTest.java */

package com.anxin_hitsz_02.trycatchfinally;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ClassName: ExceptionHandleTest
 * Package: com.anxin_hitsz_02.trycatchfinally
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/4 17:27
 * @Version 1.0
 */
public class ExceptionHandleTest {

    @Test
    public void test1() {
        try {
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            System.out.println(num);
        } catch (NullPointerException e) {
            System.out.println("出现了 NullPointerException 的异常");
        } catch (InputMismatchException e) {
            System.out.println("出现了 InputMismatchException 的异常");
        } catch (RuntimeException e) {
            System.out.println("出现了 RuntimeException 的异常");
        }

        System.out.println("异常处理结束，代码继续执行 ……");

    }

    @Test
    public void test2() {
        try {
            String str = "123";
            str = "abc";
            int i = Integer.parseInt(str);
            System.out.println(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 或：
//            System.out.println(e.getMessage());
        }

        System.out.println("程序结束");
//        System.out.println(str);
    }

    // ********** 下面来处理编译时异常 **********
    @Test
    public void test3() {
        try {
            File file = new File("D:\\hello.txt");

            FileInputStream fis = new FileInputStream(file);    // 可能报 FileNotFoundException

            int data = fis.read();  // 可能报 IOException
            while (data != -1) {
                System.out.println((char)data);
                data = fis.read();  // 可能报 IOException
            }

            fis.close();    // 可能报 IOException
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("读取数据结束 ……");

    }

}

```

#### 4.2.2 `finally` 使用及举例

![异常捕获流程](./images/image-20220331215517077.png "异常捕获流程")

因为异常会引发程序跳转，从而会导致有些语句执行不到。而程序中有一些特定的代码无论异常是否发生都需要执行，例如数据库连接、输入流输出流、Socket 连接、Lock 锁的关闭等，这样的代码通常机会放到 `finally` 块中。所以，我们通常将一定要被执行的代码声明在 `finally` 中。
* 唯一的例外：使用 `System.exit(0)` 来终止当前正在运行的 Java 虚拟机。

> 更深刻的理解：无论 `try` 中或 `catch` 中是否存在仍未被处理的异常、无论 `try` 中或 `catch` 中是否存在 `return` 语句等，`finally` 中声明的语句都一定要被执行。

不论在 `try` 代码块中是否发生了异常事件、`catch` 语句是否执行、`catch` 语句是否有异常、`catch` 语句中是否有 `return`，`finally` 块中的语句都会被执行。

> 注意：
>
> 若 `catch` 语句中包含 `return`，则会在 `return` 前一步先执行 `finally` 块中的语句，之后再执行 `return` 语句。

`finally` 语句和 `catch` 语句是可选的，但 `finally` 不能单独使用。

语法格式：
```java
try {
    ...
} finally {
    ...
}
```

示例代码：
```java
/* FinallyTest.java */

package com.anxin_hitsz_02.trycatchfinally;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: FinallyTest
 * Package: com.anxin_hitsz_02.trycatchfinally
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/4 18:41
 * @Version 1.0
 */
public class FinallyTest {
    @Test
    public void test1() {
        try {
            String str = "123";
            str = "abc";
            int i = Integer.parseInt(str);
            System.out.println(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();

            System.out.println(10 / 0); // 在 catch 中存在异常

        }

        System.out.println("程序结束");

    }

    @Test
    public void test2() {
        try {
            String str = "123";
            str = "abc";
            int i = Integer.parseInt(str);
            System.out.println(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();

            System.out.println(10 / 0); // 在 catch 中存在异常

        } finally {
            System.out.println("程序结束");
        }

    }

    @Test
    public void test3() {
        try {
            String str = "123";
            str = "abc";
            int i = Integer.parseInt(str);
            System.out.println(i);
        } finally {
            System.out.println("程序结束");
        }

    }

    /*
    * 实际开发中，finally 的使用
    * */
    @Test
    public void test4() {
        FileInputStream fis = null;
        try {
            File file = new File("D:\\hello.txt");

            fis = new FileInputStream(file);    // 可能报 FileNotFoundException

            int data = fis.read();  // 可能报 IOException
            while (data != -1) {
                System.out.println((char)data);
                data = fis.read();  // 可能报 IOException
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 重点：将流资源的关闭操作声明在 finally 中
            try {
                if (fis != null)
                    fis.close();    // 可能报 IOException
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

```

> 注意：
>
> 什么样的代码我们一定要声明在 `finally` 中呢？
> * 我们在开发中，有一些资源（比如输入流、输出流、数据库连接、Socket 连接等资源）在使用完以后，必须显式地进行关闭操作，否则 GC 不会自动地回收这些资源，进而导致内存的泄露。
> * 为了保证这些资源在使用完以后，不管是否出现了未被处理的异常的情况下，这些资源都能被关闭，那么我们必须将这些操作声明在 `finally` 中。

#### 4.2.3 异常处理的体会

前面使用的异常都是 `RuntimeException` 类或是它的子类，这些类的异常的特点是：即使没有使用 `try` 和 `catch` 捕获，Java 自己也能捕获，并且编译通过（但运行时会发生异常使得程序运行终止）。所以，对于这类异常，可以不作处理，因为这类异常很普遍，若全处理可能会对程序的可读性和运行效率产生影响。

如果抛出的异常是 `IOException` 等类型的非运行时异常，则必须捕获，否则**编译错误**。也就是说，我们必须处理编译时异常，将异常进行捕获，转化为运行时异常。

### 4.3 方式 2：声明抛出异常类型（`throws`）

如果在编写方法体的代码时，某句代码可能发生某个**编译时异常**，不处理编译不通过，但是在当前方法体中可能**不适合处理**或**无法给出合理的处理方式**，则此方法应**显式地**声明抛出异常，表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理。

异常的抛出机制如下：
![异常的抛出机制](./images/image-20220331112000671.png "异常的抛出机制")

具体方式：在方法声明中用 `throws` 语句可以声明抛出异常的列表，`throws` 后面的异常类型可以是方法中产生的异常类型，也可以是它的父类。
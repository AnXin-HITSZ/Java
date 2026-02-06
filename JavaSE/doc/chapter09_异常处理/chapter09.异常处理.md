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

#### 4.3.1 `throws` 基本格式

声明异常格式：
```java
修饰符 返回值类型 方法名(参数) throws 异常类名1, 异常类名2, ... {
    // 可能存在编译时异常
    ...
}
```

在 `throws` 后面可以写多个异常类型，用逗号隔开。

示例代码：
```java
public void readFile(String file) throws FileNotFoundException, IOException {
    ...
    // 读文件的操作可能产生 FileNotFoundException 或 IOException 类型的异常
    FileInputStream fis = new FileInputStream(file);
    // ...
}

```

> `throws` 是否真正处理了异常？
>
> 从编译是否能通过的角度看，看成是给出了异常万一要是出现时候的解决方案，此方案就是继续向上抛出（`throws`）。
> 
> 但是，此 `throws` 的方式，仅是将可能出现的异常抛给了此方法的调用者；此调用者仍然需要考虑如何处理相关异常。从这个角度来看，`throws` 的方式不算是真正意义上处理了异常。

#### 4.3.2 `throws` 使用举例

##### 4.3.2.1 针对编译时异常

示例代码：
```java
/* ThrowsTest.java */

package com.anxin_hitsz_03._throws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: ThrowsTest
 * Package: com.anxin_hitsz_03._throws
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 15:44
 * @Version 1.0
 */
public class ThrowsTest {

    public static void main(String[] args) {
        method3();

        try {
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void method3() {
        try {
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void method2() throws FileNotFoundException, IOException {

        method1();

    }

    public static void method1() throws FileNotFoundException, IOException {
        File file = new File("D:\\hello.txt");

        FileInputStream fis = new FileInputStream(file);    // 可能报 FileNotFoundException

        int data = fis.read();  // 可能报 IOException
        while (data != -1) {
            System.out.println((char)data);
            data = fis.read();  // 可能报 IOException
        }

        fis.close();    // 可能报 IOException

    }
}

```

##### 4.3.2.2 针对运行时异常

`throws` 后面也可以写运行时异常类型，只是运行时异常类型写或不写对于编译器和程序执行来说都没有任何区别。如果写了，唯一的区别就是调用者调用该方法后，使用 `try ... catch` 结构时，IDEA 可以获得更多的信息，需要添加哪种 `catch` 分支。

#### 4.3.3 方法重写中 `throws` 的要求

方法重写时，对于方法签名是有严格要求的。

> 方法重写 - 复习：
> 1. 方法名必须相同。
> 2. 形参列表必须相同。
> 3. 返回值类型：
>   * 基本数据类型和 `void`：必须相同。
>   * 引用数据类型：`<=`。
> 4. 权限修饰符：`>=`，而且要求父类被重写方法在子类中是可见的。
> 5. 不能是 `static`、`final` 修饰的方法。

此外，对于 `throws` 异常列表要求：
* 如果父类被重写方法的方法签名后面没有 “`throws 编译时异常类型`”，那么重写方法时，方法签名后面也不能出现 “`throws 编译时异常类型`”。
* 如果父类被重写方法的方法签名后面有 “`throws 编译时异常类型`”，那么重写方法时，`throws` 的编译时异常类型必须 <= 被重写方法 `throws` 的编译时异常类型，或者不 `throws` 编译时异常。
* 方法重写，对于 “`throws 运行时异常类型`” 没有要求。

> 即：只针对于编译时异常而言，子类重写的方法抛出的异常类型可以与父类被重写的方法抛出的异常类型相同，或是父类被重写的方法抛出的异常类型的子类。

示例代码：
```java
/* OverrideTest.java */

package com.anxin_hitsz_03._throws;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: OverrideTest
 * Package: com.anxin_hitsz_03._throws
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 15:55
 * @Version 1.0
 */
public class OverrideTest {
    public static void main(String[] args) {

        Father f = new Son();

        try {
            f.method1();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Number n = f.method4();


    }
}

class Father {
    public void method1() throws IOException {

    }

    public void method2() {

    }

    public void method3() {

    }

    public Number method4() {
        return null;
    }

}

class Son extends Father {
    public void method1() throws FileNotFoundException {

    }

//    public void method2() throws FileNotFoundException {
//
//    }

    public void method3() throws RuntimeException {

    }

    public Integer method4() {
        return null;
    }

}

```

### 4.4 两种异常处理方式的选择

前提：对于异常，使用相应的处理方式；此时的异常，主要指的是编译时异常。

* 如果程序代码中涉及到资源的调用（流、数据库连接、网络连接等），则必须考虑使用 `try - catch - finally` 来处理，保证不出现内存泄漏。
* （只针对于编译时异常）如果父类被重写的方法没有 `throws` 异常类型，则子类重写的方法中如果出现异常，只能考虑使用 `try - catch - finally` 进行处理，不能 `throws`。
* 开发中，方法 `a` 中依次调用了方法 `b`、`c`、`d` 等方法，方法 `b`、`c`、`d` 之间是递进关系；此时，如果方法 `b`、`c`、`d` 中有异常，我们通常选择使用 `throws`，而方法 `a` 中通常选择使用 `try - catch - finally`。

## 五、手动抛出异常对象：`throw`

> 为什么需要手动抛出异常对象？
>
> 在实际开发中，如果出现不满足具体场景的代码问题，我们就有必要手动抛出一个指定类型的异常对象。

> 如何理解“自动 vs 手动”抛出异常对象？
> * 过程 1 - “抛”：
>   * “自动抛”：程序在执行的过程当中，一旦出现异常，就会在出现异常的代码处，自动生成对应异常类的对象，并将此对象抛出。
>   * “手动抛”：程序在执行的过程当中，不满足指定条件的情况下，我们主动地使用 “`throw + 异常类的对象`” 方式抛出异常对象。
> * 过程 2 - “抓”：
> * 狭义上讲：`try - catch` 的方式捕获异常，并处理。
> * 广义上讲：把“抓”理解为“处理”，则此时对应着异常处理的两种方式，即 `try - catch - finally` 和 `throws`。

Java 中异常对象的生成有两种方式：
* 由虚拟机**自动生成**：程序运行过程中，虚拟机检测到程序发生了问题，那么针对当前代码，就会在后台自动创建一个对应异常类的实例对象并抛出。
* 由开发人员手动创建：`new 异常类型([实参列表]);`。如果创建好的异常对象不抛出对程序没有任何影响，那么和创建一个普通对象一样；但是一旦 `throw` 抛出，就会对程序运行产生影响了。

### 5.1 使用格式

如何实现手动抛出异常？
* 在方法内部，满足指定条件的条件下，使用 “`throw 异常类的对象`” 的方式抛出。

语法格式：
```java
throw new 异常类名(参数);
```

`throw` 语句抛出的异常对象，和 JVM 自动创建和抛出的异常对象一样。
* 如果是编译时异常类型的对象，同样需要使用 `throws` 或者 `try ... catch` 处理，否则编译不通过。
* 如果是运行时异常类型的对象，编译器不提示。
* 可以抛出的异常必须是 `Throwable` 或其子类的实例。下面的语句在编译时将会产生语法错误：
    ```java
    throw new String("want to throw");
    ```

### 5.2 使用注意点

无论是编译时异常类型的对象，还是运行时异常类型的对象，如果没有被 `try ... catch` 合理地处理，都会导致程序崩溃。

`throw` 语句会导致程序执行流程被改变，`throw` 语句是明确抛出一个异常对象，因此它下面的代码将不会执行，编译不通过。

如果当前方法没有 `try ... catch` 处理这个异常对象，`throw` 语句就会代替 `return` 语句提前终止当前方法的执行，并返回一个异常对象给调用者。

> 异常处理 5 个关键字：
> ![异常处理 5 个关键字](./images/image-20220330003738109.png "异常处理 5 个关键字")

> 面试题：
>
> `throw` 和 `throws` 的区别？
> * “上游排污，下游治污”。

示例代码：
```java
/* ThrowTest.java */

package com.anxin_hitsz_04._throw;

/**
 * ClassName: ThrowTest
 * Package: com.anxin_hitsz_04._throw
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 16:35
 * @Version 1.0
 */
public class ThrowTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        try {
            s1.regist(10);
            s1.regist(-10);
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Student {
    int id;

//    public void regist(int id) {
//        if (id > 0) {
//            this.id = id;
//        } else {
////            System.out.println("输入的 id 非法");
//            // 手动抛出异常类的对象
////            throw new RuntimeException("输入的 id 非法");
//        }
//    }

    public void regist(int id) throws Exception {
        if (id > 0) {
            this.id = id;
        } else {
//            System.out.println("输入的 id 非法");
            // 手动抛出异常类的对象
//            throw new RuntimeException("输入的 id 非法");

            throw new Exception("输入的 id 非法");

//            System.out.println("此语句不能被执行");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}

```

## 六、自定义异常

### 6.1 为什么需要自定义异常类？

Java 中不同的异常类，分别表示着某一种具体的异常情况。那么在开发中总是有些异常情况是核心类库中没有定义好的，此时我们需要根据自己业务的异常情况来定义异常类。例如年龄负数问题、考试成绩负数问题、某员工已在团队中等。

> 我们其实更关心的是，通过异常的名称就能直接判断此异常出现的原因。既然如此，我们就有必要在实际开发场景中，不满足我们指定的条件时，指明我们自己特有的异常类。通过此异常类的名称，就能判断出具体出现的问题。

### 6.2 如何自定义异常类？

首先，要继承一个异常类型。
* 自定义一个编译时异常类型：自定义类继承 `java.lang.Exception`。
* 自定义一个运行时异常类型：自定义类继承 `java.lang.RuntimeException`。

通常提供数个重载的构造器，建议大家提供至少两个构造器，一个是无参构造，一个是 `(String message)` 构造器。

自定义异常需要一个全局常量，声明为 `static final long serialVersionUID;`。

### 6.3 注意点

> 如何使用自定义异常类？
>
> * 在具体的代码中，满足指定条件的情况下，需要手动地使用 “`throw + 自定义异常类的对象`” 的方式，将异常对象抛出。
> * 如果自定义异常类是非运行时异常，则必须考虑如何处理此异常类的对象（具体地：1. `try - catch - finally`；2. `throws`）。

自定义的异常只能通过 `throw` 抛出。

自定义异常最重要的是异常类的名字和 `message` 属性。当异常出现时，可以根据名字判断异常类型。比如：`TeamException("成员已满，无法添加");`、`TeamException("该成员已是某团队成员");`。

自定义异常对象只能手动抛出，抛出后由 `try ... catch` 处理，也可以通过 `throws` 抛给调用者处理。

### 6.4 举例

示例代码：
```java
/* BelowZeroException.java */

package com.anxin_hitsz_04._throw;

/**
 * ClassName: BelowZeroException
 * Package: com.anxin_hitsz_04._throw
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 17:51
 * @Version 1.0
 */
public class BelowZeroException extends Exception {
    static final long serialVersionUID = -3387516999948L;

    public BelowZeroException() {

    }

    public BelowZeroException(String name) {
        super(name);
    }

    public BelowZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}


/* ThrowTest.java */

package com.anxin_hitsz_04._throw;

/**
 * ClassName: ThrowTest
 * Package: com.anxin_hitsz_04._throw
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 16:35
 * @Version 1.0
 */
public class ThrowTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        try {
            s1.regist(10);
            s1.regist(-10);
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        }

    }
}

class Student {
    int id;

//    public void regist(int id) {
//        if (id > 0) {
//            this.id = id;
//        } else {
////            System.out.println("输入的 id 非法");
//            // 手动抛出异常类的对象
////            throw new RuntimeException("输入的 id 非法");
//        }
//    }

    public void regist(int id) throws Exception {
        if (id > 0) {
            this.id = id;
        } else {
//            System.out.println("输入的 id 非法");
            // 手动抛出异常类的对象
//            throw new RuntimeException("输入的 id 非法");

//            throw new Exception("输入的 id 非法"); // 非法

            throw new BelowZeroException("输入的 id 非法");

//            System.out.println("此语句不能被执行");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}

```

## 七、练习

**练习 1：**
> 题目：
>
> 修改 chapter08_oop3 中接口部分的 exer2，在 `ComparableCircle` 接口 `compareTo()` 中抛出异常。

示例代码：
```java
/* CompareObject.java */

package com.anxin_hitsz_05.exer.exer1;

/**
 * ClassName: CompareObject
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:01
 * @Version 1.0
 */
public interface CompareObject {
    // 若返回值是 0，代表相等；若为正数，代表当前对象大；若为负数，代表当前对象小
    public int compareTo(Object o) throws Exception;
}


/* Circle.java */

package com.anxin_hitsz_05.exer.exer1;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:02
 * @Version 1.0
 */
public class Circle {
    private double radius;  // 半径

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

}


/* ComparableCircle.java */

package com.anxin_hitsz_05.exer.exer1;

/**
 * ClassName: ComparableCircle
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:03
 * @Version 1.0
 */
public class ComparableCircle extends Circle implements CompareObject {
    public ComparableCircle() {
    }

    public ComparableCircle(double radius) {
        super(radius);
    }

    // 根据对象的半径的大小，比较对象的大小
    @Override
    public int compareTo(Object o) throws Exception {
        if (this == o) {
            return 0;
        }

        if (o instanceof ComparableCircle) {
            ComparableCircle c = (ComparableCircle) o;
            return Double.compare(this.getRadius(), c.getRadius());
        } else {
//            throw new RuntimeException("输入的类型不匹配");

            throw new Exception("输入的类型不匹配");
        }
    }
}


/* InterfaceTest.java */

package com.anxin_hitsz_05.exer.exer1;

import com.anxin_hitsz_08._interface.exer2.ComparableCircle;

/**
 * ClassName: InterfaceTest
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:10
 * @Version 1.0
 */
public class InterfaceTest {
    public static void main(String[] args) {

        ComparableCircle c1 = new ComparableCircle(2.3);
        ComparableCircle c2 = new ComparableCircle(5.3);

        try {
            int compareValue = c1.compareTo(c2);
            if (compareValue > 0) {
                System.out.println("c1 对象大");
            } else if (compareValue < 0) {
                System.out.println("c2 对象大");
            } else {
                System.out.println("c1 和 c2 一样大");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

```

**练习 2：**
> 题目 - 游戏角色：
>
> 在一款角色扮演游戏中，每一个人都会有名字和生命值，角色的生命值不能为负数。
>
> 要求：当一个人物的生命值为负数的时候需要抛出自定义的异常。
>
> 操作步骤描述：
> 1. 自定义异常类 `NoLifeValueException` 继承 `RuntimeException`。
>   * 提供空参和有参构造。
>   * 在有参构造中，需要调用父类的有参构造，把异常信息传入。
> 2. 定义 `Person` 类。
>   * 属性：名称（`name`）和生命值（`lifeValue`）。
>   * 提供 `setter` 和 `getter` 方法：在 `setLifeValue(int lifeValue)` 方法中，首先判断，如果 `lifeValue` 为负数，就抛出 `NoLifeValueException`，异常信息为“`生命值不能为负数：xx`”；然后再给成员 `lifeValue` 赋值。
>   * 提供空参构造。
>   * 提供有参构造：使用 `setXxx()` 方法给 `name` 和 `lifeValue` 赋值。
> 3. 定义测试类 `Exer2`。
>   * 使用满参构造方法创建 `Person` 对象，生命值传入一个负数；由于一旦遇到异常，后面的代码将不再执行，所以需要注释掉上面的代码。
>   * 使用空参构造创建 `Person` 对象。
>       * 调用 `setLifeValue(int lifeValue)` 方法，传入一个正数，运行程序。
>       * 调用 `setLifeValue(int lifeValue)` 方法，传入一个负数，运行程序。
>   * 分别对以上两种构造方法处理异常和不处理异常进行运行，查看效果。

示例代码：
```java
/* NoLifeValueException.java */

package com.anxin_hitsz_05.exer.exer2;

/**
 * ClassName: NoLifeValueException
 * Package: com.anxin_hitsz_05.exer.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:08
 * @Version 1.0
 */
public class NoLifeValueException extends RuntimeException {

    static final long serialVersionUID = -7034897190939L;

    public NoLifeValueException() {

    }

    public NoLifeValueException(String message) {
        super(message);
    }
}


/* Person.java */

package com.anxin_hitsz_05.exer.exer2;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_05.exer.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:10
 * @Version 1.0
 */
public class Person {
    private String name;
    private int lifeValue;

    public Person() {
    }

    public Person(String name, int lifeValue) {
//        this.name = name;
        setName(name);
        setLifeValue(lifeValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(int lifeValue) {
        if (lifeValue < 0) {
            throw new NoLifeValueException("生命值不能为负数：" + lifeValue);
        }

        this.lifeValue = lifeValue;

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lifeValue=" + lifeValue +
                '}';
    }

}


/* Exer2.java */

package com.anxin_hitsz_05.exer.exer2;

/**
 * ClassName: Exer2
 * Package: com.anxin_hitsz_05.exer.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:13
 * @Version 1.0
 */
public class Exer2 {
    public static void main(String[] args) {
        // 1. 使用有参的构造器
        try {
//            Person p1 = new Person("Tom", 10);
            Person p1 = new Person("Tom", -10);
            System.out.println(p1);

        } catch (NoLifeValueException e) {
            System.out.println(e.getMessage());
        }

        // 2. 使用空参的构造器
        Person p2 = new Person();
        p2.setName("Jerry");
        p2.setLifeValue(10);
//        p2.setLifeValue(-10);

        System.out.println(p2);
    }
}

```

**练习 3：**
> 题目：
>
> 编写应用 `DivisionDemo.java`，接收命令行的两个参数，要求不能输入负数，计算两数相除。对数据类型不一致（`NumberFormatException`）、缺少命令行参数（`ArrayIndexOutOfBoundsException`）、除 0（`ArithmeticException`）及输入负数（`BelowZeroException`，自定义的异常）进行异常处理。
>
> 提示：
> 1. 在主类（`DevisionDemo`）中定义异常方法（`divide`）完成两数相除功能。
> 2. 在 `main()` 方法中调用 `divide` 方法，使用异常处理语句进行异常处理。
> 3. 在程序中，自定义对应输入负数的异常类（`BelowZeroException`）。
> 4. 运行时接受参数 `java DivisionDemo 20 10 // args[0]="20", args[1]="10"`。
> 5. `Integer` 类的 `static` 方法 `parseInt(String s)` 将 `s` 转换成对应的 `int` 值。如：`int a = Integer.parseInt("314"); // a=314`。

示例代码：
```java
/* BelowZeroException.java */

package com.anxin_hitsz_05.exer.exer3;

/**
 * ClassName: BelowZeroException
 * Package: com.anxin_hitsz_05.exer.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:21
 * @Version 1.0
 */
public class BelowZeroException extends Exception {
    static final long serialVersionUID = -33875169939948L;

    public BelowZeroException() {
    }

    public BelowZeroException(String message) {
        super(message);
    }
}


/* DivisionTest.java */

package com.anxin_hitsz_05.exer.exer3;

/**
 * ClassName: DivisionDemo
 * Package: com.anxin_hitsz_05.exer.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:18
 * @Version 1.0
 */
public class DivisionDemo {
    public static void main(String[] args) {

        try {
            int m = Integer.parseInt(args[0]);
            int n = Integer.parseInt(args[1]);

            int result = divide(m, n);
            System.out.println("结果为：" + result);
        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("数据类型不一致");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("缺少命令行参数");
        } catch (ArithmeticException e) {
            System.out.println("除 0");
        }

    }

    public static int divide(int m, int n) throws BelowZeroException {
        if (m < 0 || n < 0) {
            // 手动抛出异常类的对象
            throw new BelowZeroException("输入负数了");
        }

        return m / n;
    }
}

```
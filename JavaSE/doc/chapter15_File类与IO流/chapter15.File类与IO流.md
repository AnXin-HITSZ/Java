# 第十五章：File 类与 IO 流

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段 - Java 高级应用 - 第 15 章](./images/第3阶段：Java高级应用-第15章.png "第 3 阶段 - Java 高级应用 - 第 15 章")

## 一、`java.io.File` 类的使用

### 1.1 概述

`File` 类及本章下的各种流，都定义在 java.io 包下。

一个 `File` 对象代表硬盘或网络中可能存在的一个文件或者文件目录（俗称文件夹），与平台无关。

`File` 能新建、删除、重命名文件和目录，但 `File` 不能访问文件内容本身；如果需要访问文件内容本身，则需要使用 输入/输出 流（I/O 流）。
* `File` 对象可以作为参数传递给流的构造器。

想要在 Java 程序中表示一个真实存在的文件或目录，那么必须有一个 `File` 对象；但是 Java 程序中的一个 `File` 对象，可能没有一个真实存在的文件或目录。

File 类的对象，通常是作为 I/O 流操作的文件的端点出现的；代码层面，将 File 类的对象作为参数传递到 I/O 流相关类的构造器中。

### 1.2 构造器

`public File(String pathname)`：以 `pathname` 为路径创建 `File` 对象，可以是绝对路径或者相对路径；如果 `pathname` 是相对路径，则默认的当前路径在系统属性 user.dir 中存储。

`public File(String parent, String child)`：以 `parent` 为父路径，`child` 为子路径创建 `File` 对象。

`public File(File parent, String child)`：根据一个父 `File` 对象和子文件路径创建 `File` 对象。

关于路径：
* **绝对路径**：从盘符开始的路径，这是一个完整的路径。
* **相对路径**：相对于**项目目录**的路径，这是一个便捷的路径，开发中经常使用。
  * IDEA 中，`main` 中的文件的相对路径，是相对于“**当前工程**”。
  * IDEA 中，单元测试方法中的文件的相对路径，是相对于“**当前 module**”。

示例代码：
```java
/* FileTest.java */

package com.anxin_hitsz_01.file;

import org.junit.Test;

import java.io.File;

/**
 * ClassName: FileTest
 * Package: com.anxin_hitsz_01.file
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 20:32
 * @Version 1.0
 */
public class FileTest {

    /*
    * 构造器
    *
    * 文件的路径表示方式：
    * * 方式 1 - 绝对路径：以 Windows 操作系统为例，包括盘符在内的文件或文件目录的完整路径。
    * * 方式 2 - 相对路径：相对于某一个文件目录来讲的相对的位置。
    *               在 IDEA 中，如果使用单元测试方法：相对于当前的 module 来讲
    *                          如果使用 main() 方法，相对于当前的 project 来讲
    * */

    @Test
    public void test1() {
        // public File(String pathname)
        File file1 = new File("d:/io\\hello.txt");

        File file2 = new File("ab");
        System.out.println(file2.getAbsolutePath());

    }

//    public static void main(String[] args) {
//        File file2 = new File("abc");
//        System.out.println(file2.getAbsolutePath());
//    }

    @Test
    public void test2() {
        // public File(String parent, String child)
        // 参数 1 - parent：一定是一个文件目录
        // 参数 2 - child：可以是一个文件，也可以是一个文件目录
        File file1 = new File("d:\\io", "abc.txt");
        File file2 = new File("abc", "a12");

        // public File(File parent, String child)
        // 参数 1 - parent：一定是一个文件目录
        // 参数 2 - child：可以是一个文件，也可以是一个文件目录
        File file3 = new File(file2, "ab.txt");

    }

}

```

> 注意：
> 
> 无论该路径下是否存在文件或者目录，都不影响 `File` 对象的创建。
> 
> Windows 的路径分割符使用 “`\`”，而 Java 程序中的 “`\`” 表示转义字符，所以在 Windows 中表示路径，需要用 “`\\`”，或者直接使用 “`/`” 也可以（Java 程序支持将 “`/`” 当成平台无关的**路径分隔符**），或者直接使用 `File.separator` 常量值表示。例如：
> ```java
> File file2 = new File("d:" + File.separator + "atguigu" + File.separator + "info.txt");
> ```
>
> 当构造路径是绝对路径时，那么 `getPath` 和 `getAbsolutePath` 结果一样；当构造路径是相对路径时，那么 `getAbsolutePath 的路径 = user.dir 的路径 + 构造路径`。

### 1.3 常用方法

示例代码：
```java
/* FileTest1.java */

package com.anxin_hitsz_01.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * ClassName: FileTest1
 * Package: com.anxin_hitsz_01.file
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 20:56
 * @Version 1.0
 */
public class FileTest1 {

    /*
    * 获取文件和目录基本信息
    * */

    @Test
    public void test1() {
        File file1  = new File("hello.txt");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

    }

    @Test
    public void test2() {
        File file1  = new File("D:\\io\\io1");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

    }

    /*
    * 列出目录的下一级
    * */

    @Test
    public void test3() {
        File file1 = new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流");
        String[] fileArr = file1.list();
        for (String s : fileArr) {
            System.out.println(s);
        }

        System.out.println();

        File[] files = file1.listFiles();
        for (File f : files) {
            System.out.println(f.getName());
        }

    }

    /*
    * File 类的重命名功能
    * */

    @Test
    public void test4() {
        File file1 = new File("hello.txt");

        File file2 = new File("d:\\io\\abc.txt");

        boolean renameSuccess = file1.renameTo(file2);
        System.out.println(renameSuccess ? "重命名成功" : "重命名失败");

    }

    /*
    * 判断功能的方法
    * */

    @Test
    public void test5() {
        File file1 = new File("d:\\io\\abc.txt");
        System.out.println(file1.exists());
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("d:\\ioo");
        System.out.println(file2.exists());
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }

    /*
    * 创建、删除功能
    * */

    @Test
    public void test6() throws IOException {
        File file1 = new File("d:\\io\\hello.txt");
        // 测试文件的创建、删除
        if (!file1.exists()) {
            boolean isSuccessed = file1.createNewFile();
            if (isSuccessed) {
                System.out.println("创建成功");
            }
        } else {
            System.out.println("此文件已存在");

            System.out.println(file1.delete() ? "文件删除成功" : "文件删除失败");
        }

    }

    @Test
    public void test7() {
        // 前提：d:\\io 文件目录存在，io2 或 io3 目录是不存在的
        File file1 = new File("d:\\io\\io2");

        System.out.println(file1.mkdir());  // true

        File file2 = new File("d:\\io\\io3");

        System.out.println(file2.mkdirs()); // true

    }

    @Test
    public void test8() {
        // 前提：d:\\io 文件目录存在，io2 或 io3 目录是不存在的
        File file1 = new File("d:\\io\\io2\\io4");

        System.out.println(file1.mkdir());  // false

        File file2 = new File("d:\\io\\io3\\io5");

        System.out.println(file2.mkdirs()); // true

    }

    @Test
    public void test9() {
        File file1 = new File("d:\\io\\io3");

        System.out.println(file1.delete());
    }

}

```

#### 1.3.1 获取文件和目录基本信息

`public String getName()`：获取名称。

`public String getPath()`：获取路径。

`public String getAbsolutePath()`：获取绝对路径。

`public File getAbsoluteFile()`：获取绝对路径表示的文件。

`public String getParent()`：获取上层文件目录路径；若无，返回 `null`。

`public long length()`：获取文件长度（即：字节数）；不能获取目录的长度。

`public long lastModified()`：获取最后一次的修改时间，毫秒值。

> 注意：
>
> 如果 `File` 对象代表的文件或目录存在，则 `File` 对象实例初始化时，就会用硬盘中对应文件或目录的属性信息（例如：时间、类型等）为 `File` 对象得到的属性赋值；否则除了路径和名称，`File` 对象的其他属性将会保留默认值。

#### 1.3.2 列出目录的下一级

`public String[] list()`：返回一个 `String` 数组，表示该 `File` 目录中的所有子文件或目录。

`public File[] listFiles()`：返回一个 `File` 数组，表示该 `File` 目录中的所有的子文件或目录。

#### 1.3.3 `File` 类的重命名功能

`public boolean renameTo(File dest)`：把文件重命名为指定的文件路径。

> 注意：
>
> `file1.renameTo(file2)` 要想此方法执行完返回 `true`，要求：`file1` 必须存在，且 `file2` 必须不存在，且 `file2` 所在的文件目录需要存在。

#### 1.3.4 判断功能的方法

`public boolean exists()`：此 `File` 表示的文件或目录是否实际存在。

`public boolean isDirectory()`：此 `File` 表示的是否为目录。

`public boolean isFile()`：此 `File` 表示的是否为文件。

`public boolean canRead()`：判断是否可读。

`public boolean canWrite()`：判断是否可写。

`public boolean isHidden()`：判断是否隐藏。

> 注意：
>
> 如果文件或目录不存在，那么 `exists()`、`isFile()` 和 `isDirectory()` 都是返回 `false`。

#### 1.3.5 创建、删除功能

`public boolean createNewFile()`：创建文件；若文件存在，则不创建，返回 `false`。

`public boolean mkdir()`：创建文件目录；如果此文件目录存在则不创建，如果此文件目录的上层目录不存在则也不创建。

`public boolean mkdirs()`：创建文件目录；如果上层文件目录不存在，一并创建。

`public boolean delete()`：删除文件或者文件夹。
* 删除注意事项：
1. Java 中的删除不走回收站。
2. 要删除一个文件目录，应注意该文件目录内不能包含文件或者文件目录。

> 注意：
>
> 对于 `delete()` 方法，如果此 `File` 表示目录，则目录必须为空才能删除。

### 1.4 练习

**练习 1：**

题目：创建一个与 hello.txt 文件在相同文件目录下的另一个名为 abc.txt 文件。

示例代码：
```java
/* Exer01.java */

package com.anxin_hitsz_01.file.exer1;

import java.io.File;

/**
 * ClassName: Exer01
 * Package: com.anxin_hitsz_01.file.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:33
 * @Version 1.0
 */
public class Exer01 {
    public static void main(String[] args) {
        // 创建一个与 hello.txt 文件在相同文件目录下的另一个名为 abc.txt 文件
        File file1 = new File("hello.txt");

        System.out.println(file1.getAbsolutePath());

        // 获取 file1 的绝对路径，获取此路径的上层文件目录
//        System.out.println(file1.getAbsoluteFile().getParent());

        File file2 = new File(file1.getAbsoluteFile().getParent(), "abc.txt");

        System.out.println(file2.getAbsolutePath());

    }
}

```

**练习 2：**

题目：判断指定目录下是否有后缀名为 .jpg 的文件；如果有，就输出该文件名称。

提示：
> `File` 类提供了文件过滤器方法：
> ```java
> public String[] list(FilenameFilter filter);
> ```
> 该方法可实现对文件的过滤。

示例代码：
```java
/* Exer02.java */

package com.anxin_hitsz_01.file.exer2;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_01.file.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:36
 * @Version 1.0
 */
public class Exer02 {
    /*
    * 判断指定目录下是否有后缀名为 .jpg 的文件；如果有，就输出该文件名称
    * */
    @Test
    public void test1() {
        File dir =  new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流\\images");

        // 方式 1：
//        String[] listFiles = dir.list();
//        for (String s : listFiles) {
//            if (s.endsWith(".jpg")) {
//                System.out.println(s);
//            }
//        }

        // 方式 2：
        // public String[] list(FilenameFilter filter)
        String[] listFiles = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {  // name：即为子文件或子文件目录的名称
//                if (name.endsWith(".jpg")) {
//                    return true;
//                } else {
//                    return false;
//                }

                return name.endsWith(".jpg");
            }
        });

        for (String s : listFiles) {
            System.out.println(s);
        }

    }
}

```

**练习 3：**

题目：
1. 遍历指定文件目录下的所有文件的名称，包括子文件目录中的文件。
    ```java
    public void printFileName(File file); // file 可能是文件，也可能是文件目录
    ```
2. 删除指定文件目录及其下的所有文件。
```java
public void deleteDirectory(File file); // file 可能是文件，也可能是文件目录
```
3. 计算指定文件目录占用空间的大小。
```java
public long getDirectorySize(File file);  // file 可能是文件，也可能是文件目录
```

示例代码：
```java
/* Exer03.java */

package com.anxin_hitsz_01.file.exer3;

import org.junit.Test;

import java.io.File;

/**
 * ClassName: Exer03
 * Package: com.anxin_hitsz_01.file.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:43
 * @Version 1.0
 */
public class Exer03 {

    /*
    * 遍历指定文件目录下的所有文件的名称，包括子文件目录中的文件
    * */

    // public void printFileName(File file); // file 可能是文件，也可能是文件目录
    @Test
    public void test1() {
        File file = new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流");
        printFileName(file);

    }

    public void printFileName(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                printFileName(f);
            }
        }

    }

}

```

## 二、IO 流原理及流的分类

### 2.1 Java IO 原理

Java 程序中，对于数据的输入 / 输出操作以 “**流（stream）**” 的方式进行，可以看做是一种数据的流动。

I/O 流中的 I/O 是 Input/Output 的缩写。I/O 技术是非常实用的技术，用于处理设备之间的数据传输，如读 / 写文件、网络通讯等。
* 输入 Input：读取外部数据（磁盘、光盘等存储设备的数据）到程序（内存）中。
* 输出 Output：将程序（内存）数据输出到磁盘、光盘等存储设备中。

### 2.2 流的分类

java.io 包下提供了各种“流”类和接口，用以获取不同种类的数据，并通过**标准的方法**输入或输出数据。

按照不同的分类标准，流可有以下分类方式：
* 按数据的流向不同分为：**输入流** 和 **输出流**。
  * **输入流**：把数据从**其他设备**上读取到**内存**中的流。
    * 以 `InputStream`、`Reader` 结尾。
  * **输出流**：把数据从**内存**中写出到**其他设备**上的流。
    * 以 `OutputStream`、`Writer` 结尾。
* 按操作数据单位的不同分为：**字节流（8 `bit`）** 和 **字符流（16 `bit`）**。
  * **字节流**：以字节为单位，读写数据的流。
    * 以 `InputStream`、`OutputStream` 结尾。
  * **字符流**：以字符为单位，读写数据的流。
    * 以 `Reader`、`Writer` 结尾。
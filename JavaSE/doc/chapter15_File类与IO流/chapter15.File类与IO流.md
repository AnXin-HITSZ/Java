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
* 根据 IO 流的角色不同分为：**节点流** 和 **处理流**。
  * **节点流**：直接从数据源或目的地读写数据。
    * ![节点流示意图](./images/image-20220412230745170.png "节点流示意图")
  * **处理流**：不直接连接到数据源或目的地，而是“连接”在已存在的流（节点流或处理流）之上，通过对数据的处理为程序提供更为强大的读写功能。
    * ![处理流示意图](./images/image-20220412230751461.png "处理流示意图")

流的分类：
![流的分类](./images/流的分类.png "流的分类")

小结 - 图解：
![流 - 小结图解](./images/image-20220412225253349.png "流 - 小结图解")

### 2.3 流的 API

Java 的 IO 流共涉及 40 多个类，实际上非常规则，都是从如下 4 个抽象基类派生的：
| （抽象基类） | 输入流 | 输出流 |
| :--: | :--: | :--: |
| 字节流 | `InputStream` | `OutputStream` |
| 字符流 | `Reader` | `Writer` |

由这四个类派生出来的子类名称都是以其父类作为子类名后缀。

![流的分类及其名称](./images/image-20220412230501953.png "流的分类及其名称")

常用的节点流：
* 文件流：`FileInputStream`、`FileOutputStream`、`FileReader`、`FileWriter`。
* 字节 / 字符数组流：`ByteArrayInputStream`、`ByteArrayOutputStream`、`CharArrayReader`、`CharArrayWriter`。
  * 对数组进行处理的节点流（对应的不再是文件，而是内存中的一个数组）。

常用的处理流：
* 缓冲流：`BufferedInputStream`、`BufferedOutputStream`、`BufferedReader`、`BufferedWriter`。
  * 作用：增加缓冲功能，避免频繁读写硬盘，进而提升读写效率。
* 转换流：`InputStreamReader`、`OutputStreamReader`。
  * 作用：实现字节流和字符流之间的转换。
* 对象流：`ObjectInputStream`、`ObjectOutputStream`。
  * 作用：提供直接读写 Java 对象功能。

## 三、节点流之一：`FileReader` / `FileWriter`

### 3.1 `Reader` 与 `Writer`

Java 提供一些字符流类，以字符为单位读写数据，专门用于处理文本文件；不能操作图片、视频等非文本文件。

常见的文本文件有如下的后缀格式：.txt、.java、.c、.cpp、.py 等。

> 注意：
>
> .doc、.xls、.ppt、.jpg、.pdf、.mp4、.avi 等都是非文本文件。

#### 3.1.1 字符输入流：`Reader`

`java.io.Reader` 抽象类是表示用于读取字符流的所有类的父类，可以读取字符信息到内存中。它定义了字符输入流的基本共性功能方法。

`public int read()`：从输入流读取一个字符。虽然读取了一个字符，但是会自动提升为 `int` 类型；返回该字符的 Unicode 编码值。如果已经到达流末尾了，则返回 `-1`。

`public int read(char[] cbuf)`：从输入流中读取一些字符，并将它们存储到字符数组 `cbuf` 中。每次最多读取 `cbuf.length` 个字符，返回实际读取的字符个数。如果已经到达流末尾，没有数据可读，则返回 `-1`。

`public int read(char[] cbuf, int off, int len)`：从输入流中读取一些字符，并将它们存储到字符数组 `cbuf` 中，从 `cbuf[off]` 开始的位置存储。每次最多读取 `len` 个字符，返回实际读取的字符个数。如果已经到达流末尾，没有数据可读，则返回 `-1`。

`public void close()`：关闭此流并释放与此流相关联的任何系统资源。

> 注意：
>
> 当完成流的操作时，必须调用 `close()` 方法，释放系统资源，否则会造成内存泄漏。

#### 3.1.2 字符输出流：`Writer`

`java.io.Writer` 抽象类是表示用于写出字符流的所有类的超类，将指定的字符信息写出到目的地。它定义了字节输出流的基本共性功能方法。

`public void write(int c)`：写出单个字符。

`public void write(char[] cbuf)`：写出字符数组。

`public void write(char[] cbuf, int off, int len)`：写出字符数组的某一部分。
* `off`：数组的开始索引。
* `len`：写出的字符个数。

`public void write(String str)`：写出字符串。

`public void write(String str, int off, int len)`：写出字符串的某一部分。
* `off`：字符串的开始索引。
* `len`：写出的字符个数。

`public void flush()`：刷新该流的缓冲。

`public void close()`：关闭此流。

> 注意：
>
> 当完成流的操作时，必须调用 `close()` 方法，释放系统资源，否则会造成内存泄漏。

### 3.2 `FileReader` 与 `FileWriter`

`FileReader` / `FileWriter` 使用时的执行步骤：
1. 创建读取或写出的 `File` 类的对象。
2. 创建输入流或输出流。
3. 具体的读入或写出的过程：
   * 读入：`read(char[] cbuffer)`。
   * 写出：`write(String str)` / `write(char[] cbuffer, 0, len)`。
4. 关闭流资源，避免内存泄漏。

> 注意：
> 1. 因为涉及到流资源的关闭操作，所以出现异常的话，需要使用 `try - catch - finally` 的方式来处理异常。
> 2. 对于输入流来讲，要求 `File` 类的对象对应的物理磁盘上的文件必须存在，否则会报 `FileNotFoundException`；对于输出流来讲，`File` 类的对象对应的物理磁盘上的文件可以不存在。
>   * 如果此文件不存在，则在输出的过程中，会自动创建此文件，并写出数据到此文件中。
>   * 如果此文件存在，使用 `FileWriter(File file)` 或 `FileWriter(File file, false)`，则在输出数据过程中，会新建同名的文件对现有的文件进行覆盖；使用 `FileWiter(File file, true)`：输出数据过程中，会在现有的文件的末尾追加写出内容。

#### 3.2.1 `FileReader`

`java.io.FileReader` 类用于读取字符文件，构造时使用系统默认的字符编码和默认字节缓冲区。

`FileReader(File file)`：创建一个新的 `FileReader`，给定要读取的 `File` 对象。

`FileReader(Stream fileName)`：创建一个新的 `FileReader`，给定要读取的文件的名称。

#### 3.2.2 `FileWriter`

`java.io.FileWriter` 类用于写出字符到文件，构造时使用系统默认的字符编码和默认字节缓冲区。

`FileWriter(File file)`：创建一个新的 `FileWriter`，给定要读取的 `File` 对象。

`FileWriter(String fileName)`：创建一个新的 `FileWriter`，给定要读取的文件的名称。

`FileWriter(File file, boolean append)`：创建一个新的 `FileWriter`，指明是否在现有文件末尾追加内容。

#### 3.2.3 小结

因为出现流资源的调用，为了避免内存泄漏，需要使用 `try - catch - finally` 处理异常。

对于输入流来说，`File` 类的对象必须在物理磁盘上存在，否则执行就会报 `FileNotFoundException` 异常；如果传入的是一个目录，则会报 `IOException` 异常。

对于输出流来说，`File` 类的对象是可以不存在的。
* 如果 `File` 类的对象不存在，则可以在输出的过程中，自动创建 `File` 类的对象。
* 如果 `File` 类的对象存在：
  * 如果调用 `FileWriter(File file) 或 FileWriter(File file, false)`，输出时会新建 `File` 文件覆盖已有的文件。
  * 如果调用 `FileWriter(File file, true)` 构造器，则在现有的文件末尾追加写出内容。

#### 3.2.4 举例

示例代码：
```java
/* FileReaderWriterTest.java */

package com.anxin_hitsz_02.filestream;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ClassName: FileReaderWriterTest
 * Package: com.anxin_hitsz_02.filestream
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 15:25
 * @Version 1.0
 */
public class FileReaderWriterTest {
    /*
    * 需求：读取 hello.txt 中的内容，显示在控制台上
    *
    * 异常使用 throws 的方式处理，不太合适；见 test2()
    * */
    @Test
    public void test1() throws IOException {
        // 1. 创建 File 类的对象，对应着 hello.txt 文件
        File file = new File("hello.txt");

        // 2. 创建输入型的字符流，用于读取数据
        FileReader fr = new FileReader(file);

        // 3. 读取数据，并显示在控制台上

        // 方式 1：
//        int data = fr.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fr.read();
//        }

        // 方式 2：
        int data;
        while ((data = fr.read()) != -1) {
            System.out.print((char) data);
        }

        // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
        fr.close();

    }

    /*
     * 需求：读取 hello.txt 中的内容，显示在控制台上
     *
     * 使用 try - catch - finally 的方式处理异常，确保流一定可以关闭，避免内存泄漏
     * */
    @Test
    public void test2() {
        FileReader fr = null;
        try {
            // 1. 创建 File 类的对象，对应着 hello.txt 文件
            File file = new File("hello.txt");

            // 2. 创建输入型的字符流，用于读取数据
            fr = new FileReader(file);

            // 3. 读取数据，并显示在控制台上

            // 方式 1：
//        int data = fr.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fr.read();
//        }

            // 方式 2：
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：读取 hello.txt 中的内容，显示在控制台上
     *
     * 对 test2() 进行优化，每次读取多个字符存放到字符数组中，减少了与磁盘交互的次数，提升效率
     * */
    @Test
    public void test3() {
        FileReader fr = null;
        try {
            // 1. 创建 File 类的对象，对应着 hello.txt 文件
            File file = new File("hello.txt");

            // 2. 创建输入型的字符流，用于读取数据
            fr = new FileReader(file);

            // 3. 读取数据，并显示在控制台上
            char[] cbuffer = new char[5];
            int len;
            while ((len =fr.read(cbuffer)) != -1) {
                // 遍历数组：错误的写法
//                for (int i = 0; i < cbuffer.length; i++) {
//                    System.out.print(cbuffer[i]);
//                }

                // 遍历数组：正确的写法
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * 需求：将内存中的数据写到指定的文件中
    * */
    @Test
    public void test4() {
        FileWriter fw = null;
        try {
            // 1. 创建 File 类的对象，指明要写出的文件的名称
            File file = new File("info.txt");

            // 2. 创建输出流
            // 覆盖文件，使用的构造器
            fw = new FileWriter(file);
//            fw = new FileWriter(file, false);
            // 在现有的文件基础上，追加内容使用的构造器
//            fw = new FileWriter(file, true);

            // 3. 写出的具体过程
            // 输出的方法：write(String str) / write(char[] cdata)
            fw.write("AA\n");
            fw.write("BB\n");
            fw.write("CC");

            System.out.println("输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * 需求：复制一份 hello.txt 文件，命名为 hello_copy.txt
    * */
    @Test
    public void test5() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1. 创建 File 类的对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello_copy.txt");

            // 2. 创建输入流、输出流
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3. 数据的读入和写出的过程
            char[] cbuffer = new char[5];
            int len;    // 记录每次读入到 cbuffer 中的字符的个数
            while ((len = fr.read(cbuffer)) != -1) {
                // write(char[] cbuffer, int fromIndex, int len)
                fw.write(cbuffer, 0, len);  // 正确的
//                fw.write(cbuffer);  // 错误的
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流资源
            // 方式 1：
//            try {
//                if (fw != null) {
//                    fw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (fr != null) {
//                        fr.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            // 方式 2：
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

```

### 3.3 关于 `flush`（刷新）

因为内置缓冲区的原因，如果 `FileWriter` 不关闭输出流，无法写出字符到文件中；但是关闭的流对象，是无法继续写出数据的。如果我们既想写出数据，又想继续使用流，就需要 `flush()` 方法了。

* `flush()`：刷新缓冲区，流对象可以继续使用。
* `close()`：先刷新缓冲区，然后通知系统释放资源；流对象不可以再被使用了。

> 注意：
>
> 即便是 `flush()` 方法写出了数据，操作的最后还是要调用 `close()` 方法，释放系统资源。

## 四、节点流之二：`FileInputStream` / `FileOutputStream`

如果我们读取或写出的数据是非文本文件，则 `Reader`、`Writer` 就无能为力了，必须使用字节流。

### 4.1 `InputStream` 和 `OutputStream`

#### 4.1.1 字节输入流：`InputStream`

`java.io.InputStream` 抽象类是表示字节输入流的所有类的超类，可以读取字节信息到内存中。它定义了字节输入流的基本共性功能方法。

`public int read()`：从输入流读取一个字节，返回读取的字节值。虽然读取了一个字节，但是会自动提升为 `int` 类型。如果已经到达流末尾，没有数据可读，则返回 `-1`。

`public int read(byte[] b)`：从输入流中读取一些字节数，并将它们存储到字节数组 `b` 中，每次最多读取 `b.length` 个字节。返回实际读取的字节个数；如果已经到达流末尾，没有数据可读，则返回 `-1`。

`public int read(byte[] b, int off, int len)`：从输入流中读取一些字节数，并将它们存储到字节数组 `b` 中，从 `b[off]` 开始存储，每次最多读取 `len` 个字节。返回实际读取的字节个数；如果已经到达流末尾，没有数据可读，则返回 `-1`。

`public void close()`：关闭此输入流并释放与此流相关联的任何系统资源。

> 说明：
>
> `close()` 方法，当完成流的操作时，必须调用此方法，释放系统资源。

#### 4.1.2 字节输出流：`OutputStream`

`java.io.OutputStream` 抽象类是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。它定义了字节输出流的基本共性功能方法。

`public void write(int b)`：将指定的字节输出流。虽然参数为 int 类型即四个字节，但是只会保留一个字节的信息写出。

`public void write(byte[] b)`：将 `b.length` 字节从指定的字节数组写入此输出流。

`public void write(byte[] b, int off, int len)`：从指定的字节数组写入 `len` 字节，从偏移量 `off` 开始输出到此输出流。

`public void flush()`：刷新此输出流并强制任何缓冲的输出字节被写出。

`public void close()`：关闭此输出流并释放与此流相关联的任何系统资源。

> 说明：
>
> `close()` 方法，当完成流的操作时，必须调用此方法，释放系统资源。

### 4.2 `FileInputStream` 与 `FileOutputStream`

`FileInputStream` / `FileOutputStream` 使用时的执行步骤：
1. 创建读取或写出的 `File` 类的对象。
2. 创建输入流或输出流。
3. 具体的读入或写出的过程。
   * 读入：`read(byte[] buffer)`。
   * 写出：`write(byte[] buffer, 0, len)`。
4. 关闭流资源，避免内存泄漏。

> 注意：
> 1. 在 3.2 注意点的基础之上，具有其他的注意点。
> 2. 对于字符流，只能用来操作文本文件，不能用来处理非文本文件；对于字节流，通常是用来处理非文本文件的，但是如果涉及到文本文件的**复制**操作，也可以使用字节流。

#### 4.2.1 `FileInputStream`

`java.io.FileInputStream` 类是文件输入流，从文件中读取字节。

`FileInputStream(File file)`：通过打开与实际文件的连接来创建一个 `FileInputStream`，该文件由文件系统中的 `File` 对象 `file` 命名。

`FileInputStream(String name)`：通过打开与实际文件的连接来创建一个 `FileInputStream`，该文件由文件系统中的路径名 `name` 命名。

#### 4.2.2 `FileOutputStream`

`java.io.FileOutputStream` 类是文件输出流，用于将数据写出到文件。

`public FileOutputStream(File file)`：创建文件输出流，写出由指定的 `File` 对象表示的文件。

`public FileOutputStream(String name)`：创建文件输出流，指定的名称为写出文件。

`public FileOutputStream(File file, boolean append)`：创建文件输出流，指明是否在现有文件末尾追加内容。

#### 4.2.3 举例

示例代码：
```java
/* FileReaderWriterTest.java */

package com.anxin_hitsz_02.filestream;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ClassName: FileReaderWriterTest
 * Package: com.anxin_hitsz_02.filestream
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 15:25
 * @Version 1.0
 */
public class FileReaderWriterTest {
    /*
    * 需求：读取 hello.txt 中的内容，显示在控制台上
    *
    * 异常使用 throws 的方式处理，不太合适；见 test2()
    * */
    @Test
    public void test1() throws IOException {
        // 1. 创建 File 类的对象，对应着 hello.txt 文件
        File file = new File("hello.txt");

        // 2. 创建输入型的字符流，用于读取数据
        FileReader fr = new FileReader(file);

        // 3. 读取数据，并显示在控制台上

        // 方式 1：
//        int data = fr.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fr.read();
//        }

        // 方式 2：
        int data;
        while ((data = fr.read()) != -1) {
            System.out.print((char) data);
        }

        // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
        fr.close();

    }

    /*
     * 需求：读取 hello.txt 中的内容，显示在控制台上
     *
     * 使用 try - catch - finally 的方式处理异常，确保流一定可以关闭，避免内存泄漏
     * */
    @Test
    public void test2() {
        FileReader fr = null;
        try {
            // 1. 创建 File 类的对象，对应着 hello.txt 文件
            File file = new File("hello.txt");

            // 2. 创建输入型的字符流，用于读取数据
            fr = new FileReader(file);

            // 3. 读取数据，并显示在控制台上

            // 方式 1：
//        int data = fr.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fr.read();
//        }

            // 方式 2：
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：读取 hello.txt 中的内容，显示在控制台上
     *
     * 对 test2() 进行优化，每次读取多个字符存放到字符数组中，减少了与磁盘交互的次数，提升效率
     * */
    @Test
    public void test3() {
        FileReader fr = null;
        try {
            // 1. 创建 File 类的对象，对应着 hello.txt 文件
            File file = new File("hello.txt");

            // 2. 创建输入型的字符流，用于读取数据
            fr = new FileReader(file);

            // 3. 读取数据，并显示在控制台上
            char[] cbuffer = new char[5];
            int len;
            while ((len =fr.read(cbuffer)) != -1) {
                // 遍历数组：错误的写法
//                for (int i = 0; i < cbuffer.length; i++) {
//                    System.out.print(cbuffer[i]);
//                }

                // 遍历数组：正确的写法
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流资源的关闭操作（必须要关闭，否则会内存泄露）
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * 需求：将内存中的数据写到指定的文件中
    * */
    @Test
    public void test4() {
        FileWriter fw = null;
        try {
            // 1. 创建 File 类的对象，指明要写出的文件的名称
            File file = new File("info.txt");

            // 2. 创建输出流
            // 覆盖文件，使用的构造器
            fw = new FileWriter(file);
//            fw = new FileWriter(file, false);
            // 在现有的文件基础上，追加内容使用的构造器
//            fw = new FileWriter(file, true);

            // 3. 写出的具体过程
            // 输出的方法：write(String str) / write(char[] cdata)
            fw.write("AA\n");
            fw.write("BB\n");
            fw.write("CC");

            System.out.println("输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * 需求：复制一份 hello.txt 文件，命名为 hello_copy.txt
    * */
    @Test
    public void test5() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1. 创建 File 类的对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello_copy.txt");

            // 2. 创建输入流、输出流
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3. 数据的读入和写出的过程
            char[] cbuffer = new char[5];
            int len;    // 记录每次读入到 cbuffer 中的字符的个数
            while ((len = fr.read(cbuffer)) != -1) {
                // write(char[] cbuffer, int fromIndex, int len)
                fw.write(cbuffer, 0, len);  // 正确的
//                fw.write(cbuffer);  // 错误的
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流资源
            // 方式 1：
//            try {
//                if (fw != null) {
//                    fw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (fr != null) {
//                        fr.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            // 方式 2：
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：复制一份 Stream.jpg 文件，命名为 Stream_copy.jpg
     *
     * 复制失败！因为字符流不适合用来处理非文本文件。
     * */
    @Test
    public void test6() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1. 创建 File 类的对象
            File srcFile = new File("Stream.jpg");
            File destFile = new File("Stream_copy.jpg");

            // 2. 创建输入流、输出流
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3. 数据的读入和写出的过程
            char[] cbuffer = new char[5];
            int len;    // 记录每次读入到 cbuffer 中的字符的个数
            while ((len = fr.read(cbuffer)) != -1) {
                // write(char[] cbuffer, int fromIndex, int len)
                fw.write(cbuffer, 0, len);  // 正确的
//                fw.write(cbuffer);  // 错误的
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流资源
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


/* FileStreamTest.java */

package com.anxin_hitsz_02.filestream;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: FileStreamTest
 * Package: com.anxin_hitsz_02.filestream
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 17:06
 * @Version 1.0
 */
public class FileStreamTest {
    /*
    * 需求：复制一份 Stream.jpg 文件，命名为 Stream_copy.jpg
    * */
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("Stream.jpg");
            File destFile = new File("Stream_copy.jpg");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[1024]; // 1 kb
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：复制一份 hello.txt 文件，命名为 hello_copy1.txt
     *
     * 可以使用字节流实现文本文件的复制。
     * */
    @Test
    public void test2() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello_copy1.txt");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[5];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：读取 hello.txt 文件，将数据显示在控制台上
     *
     * 可能出现乱码。
     * */
    @Test
    public void test3() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("hello.txt");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[5];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }

            System.out.println();

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

```

### 4.3 练习

**练习：**

题目：实现图片加密操作。

提示：
```java
int b = 0;
while ((b = fis.read()) != -1) {
    fos.write(b ^ 5);
}
```

示例代码：
```java

```

## 五、处理流之一：缓冲流

**为了提高数据读写的速度**，Java API 提供了带缓冲功能的流类：缓冲流。

缓冲流要“套接”在相应的节点流之上，根据数据操作单位可以把缓冲流分为：
* **字节缓冲流**：`BufferedInputStream`、`BufferedOutputStream`。
* **字符缓冲流**：`BufferedReader`、`BufferedWriter`。

处理**非文本文件**的字节流：
* `BufferedInputStream`：
  * `read(byte[], buffer)`。
* `BufferedOutputStream`：
  * `write(byte[] buffer, 0, len)`。
  * `flush()`。

处理**文本文件**的字符流：
* `BufferedReader`：
  * `read(char[] cBuffer)` / `String readLine()`。
* `BufferedWriter`：
  * `write(char[] cBuffer, 0, len)` / `write(String str)`。
  * `flush()`。

缓冲流的基本原理：在创建流对象时，内部会创建一个缓冲区数组（缺省使用 8192 个字节（8 Kb）的缓冲区），通过缓冲区读写，减少系统 IO 次数，从而提高读写的效率。

![缺省时缓冲区的默认大小](./images/image-20220413002314063.png "缺省时缓冲区的默认大小")

![有无缓冲区的区别示意图](./images/image-20220514183413011.png "有无缓冲区的区别示意图")

实现步骤：
1. 创建 `File` 类的对象、流的对象（包括文件流、缓冲流）。
2. 使用缓冲流实现读取数据或写出数据的过程：
   * 读取：`int read(char[] cbuf / byte[] buffer)`：每次将数据读入到 `cbuf` / `buffer` 数组中，并返回读入到数组中的字符的个数。
   * 写出：
     * `void write(String str)` / `write(char[] cbuf)`：将 `str` 或 `cbuf` 写出到文件中。
     * `void write(byte[] buffer)`：将 `buffer` 写出到文件中。
3. 关闭资源。

### 5.1 构造器

`public BufferedInputStream(InputStream in)`：创建一个新的字节型的缓冲输入流。

`public BufferedOutputStream(OutputStream out)`：创建一个新的字节型的缓冲输出流。

语法格式：
```java
// 创建字符缓冲输入流
BufferedReader br = new BufferedReader(new FileReader("br.txt"));
// 创建字符缓冲输出流
BufferedWriter bw = new BufferedWriter(new FileWriter("bw.txt"));
```

### 5.2 效率测试

示例代码：
```java
/* CopyFileTest.java */

package com.anxin_hitsz_03.buffered;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: CopyFileTest
 * Package: com.anxin_hitsz_03.buffered
 * Description:
 *      测试 FileInputStream + FileOutputStream 复制文件、BufferedInputStream + BufferedOutputStream 复制文件，测试二者的效率。
 * @Author AnXin
 * @Create 2026/2/27 18:19
 * @Version 1.0
 */
public class CopyFileTest {

    @Test
    public void testSpendTime() {
        long start = System.currentTimeMillis();

        String src = "path\\to\\src\\Video.mp4";
        String dest = "path\\to\\dest\\Video_copy.mp4";

//        copyFileWithFileStream(src, dest);
        copyFileWithBufferedStream(src, dest);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }

    /*
    * 使用 BufferedInputStream + BufferedOutputStream 复制文件
    * */
    public void copyFileWithBufferedStream(String src, String dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File(src);
            File destFile = new File(dest);

            // 2. 创建相关的字节流、缓冲流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[50];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * 使用 FileInputStream + FileOutputStream 复制文件
    * */
    public void copyFileWithFileStream(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File(src);
            File destFile = new File(dest);

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[50];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

```

### 5.3 字符缓冲流特有方法

字符缓冲流的基本方法与普通字符流调用方式一致，不再阐述。我们来看它们具备的特有方法。

`BufferedReader`：
* `public String readLine()`：读一行文字。

`BufferedWriter`：
* `public void newLine()`：写一行行分割符，由系统属性定义符号。

> 说明：
> 1. 涉及到嵌套的多个流时，如果都显式关闭的话，需要先关闭外层的流，再关闭内层的流。
> 2. 其实再开发中，只需要关闭最外层的流即可。因为在关闭外层流时，内层的流也会被关闭。

### 5.4 举例

示例代码：
```java
/* BufferedStreamTest.java */

package com.anxin_hitsz_03.buffered;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: BufferedStreamTest
 * Package: com.anxin_hitsz_03.buffered
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 18:11
 * @Version 1.0
 */
public class BufferedStreamTest {
    /*
    * 需求：使用 BufferedInputStream / BufferedOutputStream 复制一个图片
    *
    * 注意：如下的操作应该使用 try - catch - finally 处理异常
    * */
    @Test
    public void test1() throws Exception {
        // 1. 创建相关的 File 类的对象
        File srcFile = new File("Stream.jpg");
        File destFile = new File("Stream_copy1.jpg");

        // 2. 创建相关的字节流、缓冲流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        // 3. 数据的读入和写出
        byte[] buffer = new byte[1024]; // 1 kb
        int len;    // 记录每次读入到 buffer 中字节的个数
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }

        System.out.println("复制成功");

        // 4. 关闭资源
        // 外层的流的关闭
        // 由于外层流的关闭也会自动地对内层的流进行关闭操作，所以可以省略内层流的关闭
        bos.close();
        bis.close();
        // 内层的流的关闭
//        fos.close();
//        fis.close();

    }

}


/* BufferedReaderWriterTest.java */

package com.anxin_hitsz_03.buffered;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: BufferedReaderWriterTest
 * Package: com.anxin_hitsz_03.buffered
 * Description:
 *      测试 BufferedReader 和 BufferedWriter 的使用
 * @Author AnXin
 * @Create 2026/2/27 18:33
 * @Version 1.0
 */
public class BufferedReaderWriterTest {
    /*
    * 使用 BufferedReader 将 dbcp_utf-8.txt 中的内容显示在控制台上
    * */
    @Test
    public void test1() throws IOException {
        //
        File file = new File("dbcp_utf-8.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        // 读取的过程

        // 方式 1：read(char[] cBuffer)
//        char[] cBuffer = new char[1024];
//        int len;    // 记录每次读入到 cBuffer 中的字符的个数
//        while ((len = br.read(cBuffer)) != -1) {
//            // 方法 1：
////            for (int i = 0; i < len; i++) {
////                System.out.print(cBuffer[i]);
////            }
//            // 方法 2：
//            String str = new String(cBuffer, 0, len);
//            System.out.print(str);
//        }

        // 方式 2：readLine() - 每次读取一行文本中的数据，返回的字符串是不包含换行符的
        String data;
        while ((data = br.readLine()) != null) {
            System.out.print(data + "\n");
        }

        //
        br.close();

    }

    /*
    * 使用 BufferedReader 和 BufferedWriter 实现文本文件的复制
    *
    * 注意：开发中，还是需要使用 try - catch - finally 来处理流的异常
    * */
    @Test
    public void test2() throws IOException {
        // 1. 造文件、造流
        File file1 = new File("dbcp_utf-8.txt");
        File file2 = new File("dbcp_utf-8_copy1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file1));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

        // 2. 文件的读写操作
        String data;
        while ((data = br.readLine()) != null) {
            bw.write(data);
            bw.newLine();   // 表示换行操作
            bw.flush(); // 刷新的方法：每当调用此方法时，就会主动地将内存中的数据写出到磁盘文件中
        }
        System.out.println("复制成功");

        // 3. 关闭资源
        bw.close();
        br.close();

    }

}

```

## 六、处理流之二：转换流

### 6.1 问题引入

**引入情况 1：**

使用 `FileReader` 读取项目中的文本文件。由于 IDEA 设置中针对项目设置了 UTF-8 编码，当读取 Windows 系统中创建地文本文件时，如果 Windows 系统默认的是 GBK 编码，则读入内存中会出现乱码。

那么如何读取 GBK 编码的文件呢？

**引入情况 2：**

针对文本文件，现在使用一个字节流进行数据的读入，希望将数据显示在控制台上，此时针对包含中文的文本数据，可能会出现乱码。

### 6.2 转换流的理解

作用：转换流是字节与字符间的桥梁。

![转换流作为字节与字符间的桥梁示意图](./images/2_zhuanhuan.jpg "转换流作为字节与字符间的桥梁示意图")

具体来说：
![转换流的转换原理示意图](./images/image-20220412231533768.png "转换流的转换原理示意图")

### 6.3 `InputStreamReader` 与 `OutputStreamWriter`

#### 6.3.1 `InputStreamReader`

转换流 `java.io.InputStreamReader`，是 `Reader` 的子类，是从字节流到字符流的桥梁；它读取字节，并使用指定的字符集将其解码为字符。它的字符集可以由名称指定，也可以接受平台的默认字符集。

即：将一个输入型的字节流转换为输入型的字符流。

构造器：
* `InputStreamReader(InputStream in)`：创建一个使用默认字符集的字符流。
* `InputStreamReader(InputStream in, String charsetName)`：创建一个指定字符集的字符流。

语法格式：
```java
// 使用默认字符集
InputStreamReader isr1 = new InputStreamReader(new FileInputStream("in.txt"));
// 使用指定字符集
InputStreamReader isr2 = new InputStreamReader(new FileInputStream("in.txt"), "GBK");
```

#### 6.3.2 `OutputStreamWriter`

转换流 `java.io.OutputStreamWriter`，是 `Writer` 的子类，是从字符流到字节流的桥梁；可以使用指定的字符集将字符编码为字节。它的字符集可以由名称指定，也可以接受平台的默认字符集。

即：将一个输出型的字符流转换为输出型的字节流。

构造器：
* `OutputStreamWriter(OutputStream in)`：创建一个使用默认字符集的字符流。
* `OutputStreamWriter(OutputStream in, String charsetName)`：创建一个指定字符集的字符流。

语法格式：
```java
// 使用默认字符集
OutputStreamWriter osw1 = new OutputStreamWriter(new FileOutputStream("out.txt"));
// 使用指定的字符集
OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream("out.txt"), "GBK");
```

#### 6.3.3 举例

示例代码：
```java
/* InputStreamReaderTest.java */

package com.anxin_hitsz_04.inputstreamreader;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: InputStreamReaderTest
 * Package: com.anxin_hitsz_04.inputstreamreader
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 20:50
 * @Version 1.0
 */
public class InputStreamReaderTest {
    @Test
    public void test1() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_utf-8.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
//        InputStreamReader isr = new InputStreamReader(fis); // 此时使用的是 IDEA 默认的 UTF-8 的字符集
        InputStreamReader isr1 = new InputStreamReader(fis, "utf-8"); // 显式地使用 UTF-8 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    /*
    * 读取到的数据出现了乱码。
    * 因为 dbcp_utf-8.txt 文件使用的是 utf-8 的字符集进行的编码，所以在读取此文件时使用的解码集必须也是 utf-8，
    * 否则会出现乱码！
    * */
    @Test
    public void test2() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_utf-8.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
        InputStreamReader isr1 = new InputStreamReader(fis, "gbk"); // 显式地使用 gbk 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    @Test
    public void test3() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_gbk.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
        InputStreamReader isr1 = new InputStreamReader(fis, "gbk"); // 显式地使用 gbk 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    /*
    * 需求：将 gbk 格式的文件转换为 utf-8 格式的文件存储
    * */
    @Test
    public void test4() throws IOException {
        // 1. 造文件
        File file1 = new File("dbcp_gbk.txt");
        File file2 = new File("dbcp_gbk_to_utf8.txt");

        // 2. 造流
        FileInputStream fis = new FileInputStream(file1);
        // 参数 2 对应的是解码集，必须与 dbcp_gbk.txt 的解码集一致
        InputStreamReader isr = new InputStreamReader(fis, "GBK");

        FileOutputStream fos = new FileOutputStream(file2);
        // 参数 2 指明内存中的字符存储到文件中的字节过程中使用的编码集
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf8");

        // 3. 读写过程
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr.read(cBuffer)) != -1) {
            osw.write(cBuffer, 0, len);
        }

        System.out.println("操作完成");

        // 4. 关闭资源
        osw.close();
        isr.close();

    }

}

```

### 6.4 字符编码和字符集

#### 6.4.1 编码与解码

字符编码：字符、字符串、字符数组 -> 字节、字节数组，即从我们能看懂的编码为我们看不懂的。

字符解码：字节、字节数组 -> 字符、字符串、字符数组，即从我们看不懂的编码为我们能看懂的。

如果希望程序在读取文本文件时，不出现乱码，需要注意什么？
* 解码时使用的字符集必须与当初编码时使用的字符集相同或兼容。
* 拓展：解码集必须要与编码集兼容。例如：文件编码使用的是 GBK，解码时使用的是 UTF-8，如果文件中只有 a、b、c 等英文字符，此情况下也不会出现乱码；因为 GBK 和 UTF-8 都向下兼容了 ASCII（或：ascii）。

#### 6.4.2 字符集

> 注意：
>
> 在中文操作系统上，ANSI（American National Standards Institute，美国国家标准学会）编码即为 GBK；在英文操作系统上，ANSI 编码即为 ISO-8859-1。

##### 6.4.2.1 在存储的文件中的字符

**ascii**：主要用来存储 a、b、c 等英文字符和 1、2、3 等阿拉伯数字以及常用的标点符号。每个字符占用 1 个字节。

**iso-8859-1**：拉丁码表，别名 Latin-1，用于显示欧洲使用的语言，包括荷兰语、德语、意大利语、葡萄牙语等。每个字符占用 1 个字节，向下兼容 ascii。

**gbk**：用来存储中文（简体与繁体）、英文字符、阿拉伯数字以及常用的标点符号等字符。中文字符使用 2 个字节存储；向下兼容 ascii，意味着英文字符、阿拉伯数字、标点符号（英文半角）仍使用 1 个字节。

**utf-8**：可以用来存储世界范围内主要的语言的所有的字符。使用 1 - 4 个不等的字节表示一个字符。中文字符使用 3 个字节存储；向下兼容 ascii，意味着英文字符、阿拉伯数字、标点符号（英文半角）仍使用 1 个字节。

##### 6.4.2.2 在内存中的字符

在内存中，一个字符（`char`）占用 2 个字节。

在内存中使用的字符集称为 Unicode 字符集。

> Unicode 字符集：
>
> Unicode 编码为表达**任意语言的任意字符**而设计，也称为统一码、标准万国码。Unicode 将世界上所有的文字用 **2 个字节** 统一进行编码，为每个字符设定唯一的二进制编码，以满足跨语言、跨平台进行文本处理的要求。
>
> 对于 Unicode 的缺点，这里有三个问题：
> 1. 第一，英文字母只用一个字节表示就够了，如果用更多的字节存储是**极大的浪费**。
> 2. 第二，如何才能**区别 Unicode 和 ASCII**？计算机怎么知道两个字节表示一个符号，而不是分别表示两个符号呢？
> 3. 第三，如果和 GBK 等双字节编码方式一样，用最高位是 1 或 0 表示两个字节和一个字节，就少了很多值无法用于表示字符，**不够表示所有字符**。
>
> Unicode 在很长一段时间内无法推广，直到互联网的出现。为解决 Unicode 如何在网络上传输的问题，于是面向传输的众多 UTF（UCS Transfer Format）标准出现。具体来说，有三种编码方案，分别为 UTF-8、UTF-16 和 UTF-32。

### 6.5 练习

练习：

题目：把当前 module 下的 hello.txt 字符编码为 GBK，复制到电脑桌面目录下的 hello_desktop.txt，字符编码为 UTF-8。

示例代码：
```java

```
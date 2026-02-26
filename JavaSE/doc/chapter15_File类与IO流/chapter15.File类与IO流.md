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

`File` 能新建、删除、重命名文件和目录，但 `File` 不能访问文件内容本身；如果需要访问文件内容本身，则需要使用输入 / 输出流。
* `File` 对象可以作为参数传递给流的构造器。

想要在 Java 程序中表示一个真实存在的文件或目录，那么必须有一个 `File` 对象；但是 Java 程序中的一个 `File` 对象，可能没有一个真实存在的文件或目录。

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

示例代码：
```java

```

#### 1.3.2 列出目录的下一级

`public String[] list()`：返回一个 `String` 数组，表示该 `File` 目录中的所有子文件或目录。

`public File[] listFiles()`：返回一个 `File` 数组，表示该 `File` 目录中的所有的子文件或目录。

示例代码：
```java

```

#### 1.3.3 `File` 类的重命名功能

`public boolean renameTo(File dest)`：把文件重命名为指定的文件路径。

#### 1.3.4 判断功能的方法

`public boolean exists()`：此 `File` 表示的文件或目录是否实际存在。

`public boolean isDirectory()`：此 `File` 表示的是否为目录。

`public boolean isFile()`：此 `File` 表示的是否为文件。

`public boolean canRead()`：判断是否可读。

`public boolean canWrite()`：判断是否可写。

`public boolean isHidden()`：判断是否隐藏。

示例代码：
```java

```

> 注意：
>
> 如果文件或目录不存在，那么 `exists()`、`isFile()` 和 `isDirectory()` 都是返回 `true`。

#### 1.3.5 创建、删除功能

`public boolean createNewFile()`：创建文件；若文件存在，则不创建，返回 `false`。

`public boolean mkdir()`：创建文件目录；如果此文件目录存在则不创建，如果此文件目录的上层目录不存在则也不创建。

`public boolean mkdirs()`：创建文件目录；如果上层文件目录不存在，一并创建。

`public boolean delete()`：删除文件或者文件夹。
* 删除注意事项：
1. Java 中的删除不走回收站。
2. 要删除一个文件目录，应注意该文件目录内不能包含文件或者文件目录。

示例代码：
```java

```

> 注意：
>
> 对于 `delete()` 方法，如果此 `File` 表示目录，则目录必须为空才能删除。

### 1.4 练习

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
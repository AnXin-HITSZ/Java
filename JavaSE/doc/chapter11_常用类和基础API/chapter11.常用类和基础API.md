# 第十一章：常用类和基础 API

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 11 章](./images/第3阶段：Java高级应用-第11章.png "第 3 阶段：Java 高级应用 - 第 11 章")

## 一、字符串相关类之不可变字符序列：`String`

对于 `String` 类的理解，以 JDK 8 为例说明。

### 1.1 `String` 的特性

`java.lang.String` 类代表字符串，Java 程序中所有的字符串文字（例如 `"hello"`）都可以看作是实现此类的实例。

类的声明：
```java
public final class String 
    implements java.io.Serializable, Comparable<String>, CharSequence {
        ...
}
```
* `final`：意味着 `String` 是不可被继承的。
* `Serializable`：可序列化的接口，凡是实现此接口的类的对象就可以通过网络或本地流进行数据的传输。
* `Comparable`：凡是实现此接口的类，其对象都可以比较大小。

内部声明的属性：
* JDK 8 中：
    ```java
    private final char value[]; // 存储字符串数据的容器
    ```
    * `final`：指明此 `value` 数组一旦初始化，其地址就不可变。
* JDK 9 开始 - 为了节省内存空间，做了优化：
    ```java
    private final byte[] value; // 存储字符串数据的容器
    ```

字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。

字符串 `String` 类型本身是 `final` 声明的，意味着我们不能继承 `String`。

`String` 对象的字符内容是存储在一个字符数组 `value[]` 中的；`"abc"` 等效于 `char[] data = {'h', 'e', 'l', 'l', 'o'}`。

`String` 内存结构：
![String 内存结构示意图](./images/image-20220514184404024.png "String 内存结构示意图")

Java 语言提供对字符串串联符号（`+`）以及将其他对象转换为字符串的特殊支持（`toString()` 方法）。

### 1.2 字符串常量的存储位置

因为字符串对象设计为不可变，那么所以字符串有常量池来保存很多常量对象；因此，字符串常量都存储在字符串常量池（StringTable）中。

字符串常量池不允许存放两个相同的字符串常量。

JDK 7 之前，字符串常量池在方法区；JDK 7 及之后，字符串常量池存放在堆空间。

> 注意：
>
> `String`、静态变量的存储位置演进：
> * jdk 6：
>   ![存储位置 - jdk 6](./images/20260209182615.png "存储位置 - jdk 6")
> * jdk 7：
>   ![存储位置 - jdk 7](./images/20260209182738.png "存储位置 - jdk 7")
> * jdk 8 及之后：
>   ![存储位置 - jdk 8 及之后](./images/20260209183022.png "存储位置 - jdk 8 及之后")

举例内存结构分配：
![内存结构分配举例](./images/1562945799274.png "内存结构分配举例")

示例代码：
```java
/* StringDemo.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringDemo
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 18:10
 * @Version 1.0
 */
public class StringDemo {
    @Test
    public void test1() {
        String s1 = "hello";    // 字面量的定义方式
        String s2 = "hello";

        System.out.println(s1 == s2);   // true
    }
}

```

### 1.3 `String` 的不可变性的理解

当对字符串变量重新赋值时，需要重新指定一个字符串常量的位置进行赋值，不能在原有的位置修改。

当对现有的字符串进行拼接操作时，需要重新开辟空间保存拼接以后的字符串，不能在原有的位置修改。

当调用字符串的 `replace()` 替换现有的某个字符时，需要重新开辟空间保存修改以后的字符串，不能在原有的位置修改。

示例代码：
```java
/* StringDemo.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringDemo
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 18:10
 * @Version 1.0
 */
public class StringDemo {
    @Test
    public void test1() {
        String s1 = "hello";    // 字面量的定义方式
        String s2 = "hello";

        System.out.println(s1 == s2);   // true
    }

    /*
    * String 的不可变性
    *   1. 当对字符串变量重新赋值时，需要重新指定一个字符串常量的位置进行赋值，不能在原有的位置修改
    *   2. 当对现有的字符串进行拼接操作时，需要重新开辟空间保存拼接以后的字符串，不能在原有的位置修改
    *   3. 当调用字符串的 replace() 替换现有的某个字符时，需要重新开辟空间保存修改以后的字符串，不能在原有的位置修改
    * */
    @Test
    public void test2() {
        String s1 = "hello";
        String s2 = "hello";

        s2 = "hi";

        System.out.println(s1); // hello
    }

    @Test
    public void test3() {
        String s1 = "hello";
        String s2 = "hello";

        s2 += "world";

        System.out.println(s1); // hello
        System.out.println(s2); // helloworld
    }

    @Test
    public void test4() {
        String s1 = "hello";
        String s2 = "hello";

        String s3 = s2.replace('l', 'w');

        System.out.println(s1); // hello
        System.out.println(s2); // hello
        System.out.println(s3); // hewwo
    }
}

```

`String` 的内存结构：
![String 的内存结构举例](./images/20260209183341.png "String 的内存结构举例")

`String` 的不可变性举例 1：
![String 的不可变性举例 1 - 内存结构](./images/20260209183443.png "String 的不可变性举例 1 - 内存结构")

`String` 的不可变性举例 2：
![String 的不可变性举例 2 - 内存结构](./images/20260209183554.png "String 的不可变性举例 2 - 内存结构")

`String` 的不可变性举例 3：
![String 的不可变性举例 3 - 内存结构](./images/20260209183709.png "String 的不可变性举例 3 - 内存结构")

### 1.4 `String` 的实例化与连接操作

#### 1.4.1 `String` 实例化的两种方式

第一种方式：
```java
String s1 = "hello";
```

第二种方式：
```java
String s2 = new String("hello");
```

> 注意：
>
> `new` 的方式创建 `String`：
> ![new 的方式创建 String - 内存结构](./images/20260209193033.png "new 的方式创建 String - 内存结构")

示例代码：
```java
/* StringDemo1.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringDemo1
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 19:17
 * @Version 1.0
 */
public class StringDemo1 {
    @Test
    public void test1() {
        String s1 = "hello";
        String s2 = "hello";

        String s3 = new String("hello");
        String s4 = new String("hello");

        System.out.println(s1 == s2);   // true
        System.out.println(s1 == s3);   // false
        System.out.println(s1 == s4);   // false
        System.out.println(s3 == s4);   // false

        System.out.println(s1.equals(s2));  // true

    }

    /*
    * String s = new String("hello"); 的内存解析？
    *
    * String s = new String("hello"); 在内存中创建了几个对象？
    *
    * */

    @Test
    public void test2() {
        Person p1 = new Person();
        Person p2 = new Person();

        p1.name = "Tom";
        p2.name = "Tom";

        p1.name = "Jerry";

        System.out.println(p2.name);    // Tom

    }

    /*
    * 测试 String 的连接符：+
    * */
    @Test
    public void test3() {
        String s1 = "hello";
        String s2 = "world";

        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";   // 通过查看字节码文件，发现调用了 StringBuilder 的 toString() -> new String()
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);   // true
        System.out.println(s3 == s5);   // false
        System.out.println(s3 == s6);   // false
        System.out.println(s3 == s7);   // false
        System.out.println(s5 == s6);   // false
        System.out.println(s5 == s7);   // false

        System.out.println();

        String s8 = s5.intern();    // intern()：返回的是字符串常量池中字面量的地址
        System.out.println(s3 == s8);   // true

    }

    @Test
    public void test4() {
        final String s1 = "hello";
        final String s2 = "world";

        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";   // 通过查看字节码文件，发现调用了 StringBuilder 的 toString() -> new String()
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s5);   // true
        System.out.println(s3 == s6);   // true
        System.out.println(s3 == s7);   // true

    }

    @Test
    public void test5() {
        String s1 = "hello";
        String s2 = "world";

        String s3 = s1.concat(s2);
        String s4 = "hello".concat("world");
        String s5 = s1.concat("world");

        System.out.println(s3 == s4);   // false
        System.out.println(s3 == s5);   // false
        System.out.println(s4 == s5);   // false

    }
}

class Person {
    String name;

}

```

> 注意：
>
> `String` 内存分析：
> ![String 内存分析](20260209194723.png "String 内存分析")

> 面试题：
>
> 下述代码：
> ```java
> String s2 = new String("hello");
> ```
> 在内存中创建了多少个对象？
>
> 上述代码在内存中创建了两个对象：一个是堆空间中 `new` 的对象，另一个是在字符串常量池中生成的字面量。

#### 1.4.2 `String` 的连接操作：`+`

情况 1 - `常量 + 常量`：结果仍然存储在字符串常量池中，返回此字面量的地址。

> 注意：此时的常量可能是字面量，也可能是 `final` 修饰的常量。

情况 2 - `常量 + 变量` 或 `变量 + 变量`：都会通过 `new` 的方式创建一个新的字符串，返回堆空间中此字符串对象的地址。

情况 3 - 调用字符串的 `intern()`：返回的是字符串常量池中字面量的地址。

情况 4 - `concat(xxx)`：不管是常量还是变量调用此方法、不管参数是常量还是变量，总之调用完 `concat()` 方法都返回一个新 `new` 的对象。

示例代码：
```java
/* StringDemo1.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringDemo1
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 19:17
 * @Version 1.0
 */
public class StringDemo1 {
    @Test
    public void test1() {
        String s1 = "hello";
        String s2 = "hello";

        String s3 = new String("hello");
        String s4 = new String("hello");

        System.out.println(s1 == s2);   // true
        System.out.println(s1 == s3);   // false
        System.out.println(s1 == s4);   // false
        System.out.println(s3 == s4);   // false

        System.out.println(s1.equals(s2));  // true

    }

    /*
    * String s = new String("hello"); 的内存解析？
    *
    * String s = new String("hello"); 在内存中创建了几个对象？
    *
    * */

    @Test
    public void test2() {
        Person p1 = new Person();
        Person p2 = new Person();

        p1.name = "Tom";
        p2.name = "Tom";

        p1.name = "Jerry";

        System.out.println(p2.name);    // Tom

    }

    /*
    * 测试 String 的连接符：+
    * */
    @Test
    public void test3() {
        String s1 = "hello";
        String s2 = "world";

        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";   // 通过查看字节码文件，发现调用了 StringBuilder 的 toString() -> new String()
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);   // true
        System.out.println(s3 == s5);   // false
        System.out.println(s3 == s6);   // false
        System.out.println(s3 == s7);   // false
        System.out.println(s5 == s6);   // false
        System.out.println(s5 == s7);   // false

        System.out.println();

        String s8 = s5.intern();    // intern()：返回的是字符串常量池中字面量的地址
        System.out.println(s3 == s8);   // true

    }

    @Test
    public void test4() {
        final String s1 = "hello";
        final String s2 = "world";

        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";   // 通过查看字节码文件，发现调用了 StringBuilder 的 toString() -> new String()
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s5);   // true
        System.out.println(s3 == s6);   // true
        System.out.println(s3 == s7);   // true

    }

    @Test
    public void test5() {
        String s1 = "hello";
        String s2 = "world";

        String s3 = s1.concat(s2);
        String s4 = "hello".concat("world");
        String s5 = s1.concat("world");

        System.out.println(s3 == s4);   // false
        System.out.println(s3 == s5);   // false
        System.out.println(s4 == s5);   // false

    }
}

class Person {
    String name;

}

```

### 1.5 `String` 的常用 API - 1

#### 1.5.1 构造器

`public String()`：初始化新创建的 `String` 对象，以使其表示空字符序列。

`public String(String original)`：初始化一个新创建的 `String` 对象，使其表示一个与参数相同的字符序列；换句话说，新创建的字符串是该参数字符串的副本。

`public String(char[] value)`：通过当前参数中的字符数组来构造新的 `String`。

`public String(char[] value, int offset, int count)`：通过字符数组的一部分来构造新的 `String`。

`public String(byte[] bytes)`：通过使用平台的**默认字符集**解码当前参数中的字节数组来构造新的 `String`。

`public String(byte[] bytes, String charsetName)`：通过使用指定的字符集解码当前参数中的字节数组来构造新的 `String`。

示例代码：
```java
/* StringMethodTest.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringMethodTest
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 20:52
 * @Version 1.0
 */
public class StringMethodTest {
    /*
    * String 构造器的使用
    * */
    @Test
    public void test1() {
        String s1 = new String();
        String s2 = new String("");

        String s3 = new String(new char[] { 'a', 'b', 'c' });
        System.out.println(s3);
    }

}

```

#### 1.5.2 `String` 与其他结构间的转换

##### 1.5.2.1 字符串与基本数据类型、包装类

**字符串 -> 基本数据类型、包装类：**

`Integer` 包装类的 `public static int parseInt(String s)`：可以将由“数字”字符组成的字符串转换为整型。

类似地，使用 java.lang 包中的 `Byte`、`Short`、`Long`、`Float`、`Double` 类调相应的类方法可以将由“数字”字符组成的字符串转化为相应的基本数据类型。

**基本数据类型、包装类 -> 字符串：**

调用 `String` 类的 `public String valueOf(int n)` 可将 `int` 型转换为字符串。

相应的 `valueOf(byte b)`、`valueOf(long l)`、`valueOf(float f)`、`valueOf(double d)`、`valueOf(boolean b)` 可实现由参数的相应类型到字符串的转换。

##### 1.5.2.2 字符数组与字符串

**字符数组 -> 字符串：**

`String` 类的构造器 `String(char[])` 和 `String(char[], int offset, int length)` 分别用字符数组中的全部字符和部分字符创建字符串对象。

**字符串 -> 字符数组：**

`public char[] toCharArray()`：将字符串中的全部字符存放在一个字符数组中。

`public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)`：将指定索引范围内的字符串存放到数组中。

##### 1.5.2.3 字符串与字节数组

**字符串 -> 字节数组（编码）：**

`public byte[] getBytes()`：使用平台的默认字符集将此 `String` 编码为 `byte` 序列，并将结果存储到一个新的 `byte` 数组中。

`public byte[] getBytes(String charsetName)`：使用指定的字符集将此 `String` 编码到 `byte` 序列，并将结果存储到新的 `byte` 数组。

**字节数组 -> 字符串（解码）：**

`String(byte[])`：通过使用平台的默认字符集解码指定的 `byte` 数组，构造一个新的 `String`。

`String(byte[], int offset, int length)`：用指定的字节数组的一部分（即从数组起始位置 `offset` 开始取 `length` 个字节）构造一个字符串对象。

`String(byte[], String charsetName)` 或 `new String(byte[], int, int, String charsetName)`：解码，按照指定的编码方式进行解码。

##### 1.5.2.4 举例

示例代码：
```java
/* StringMethodTest.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * ClassName: StringMethodTest
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 20:52
 * @Version 1.0
 */
public class StringMethodTest {
    /*
    * String 构造器的使用
    * */
    @Test
    public void test1() {
        String s1 = new String();
        String s2 = new String("");

        String s3 = new String(new char[] { 'a', 'b', 'c' });
        System.out.println(s3);
    }

    /*
    * String 与常见的其它结构之间的转换
    *
    * 1. String 与基本数据类型、包装类之间的转换（复习）
    *
    * 2. String 与 char[] 之间的转换
    *
    * 3. String 与 byte[] 之间的转换（难度）
    *
    * */
    @Test
    public void test2() {
        int num = 10;
        // 基本数据类型 -> String
        // 方式 1：
        String s1 = num + "";
        // 方式 2：
        String s2 = String.valueOf(num);

        // String -> 基本数据类型：调用包装类的 parseXxx(String str)
        String s3 = "123";
        int i1 = Integer.parseInt(s3);

    }

    // String 与 char[] 之间的转换
    @Test
    public void test3() {
        String str = "hello";
        // String -> char[]：调用 String 的 toCharArray()
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // char[] -> String：调用 String 的构造器
        String str1 = new String(arr);
        System.out.println(str1);   // hello

    }

    // String 与 byte[] 之间的转换（难度）
    /*
    * 在 utf-8 字符集中，一个汉字占用 3 个字节，一个字母占用 1 个字节
    * 在 gbk 字符集中，一个汉字占用 2 个字节，一个字母占用 1 个字节
    *
    * utf-8 或 gbk 都向下兼容了 ascii 码
    *
    * 编码与解码：
    *   编码：String -> 字节或字节数组
    *   解码：字节或字节数组 -> String
    * 要求：解码时使用的字符集必须与编码时使用的字符集一致！不一致，就会乱码。
    *
    * */
    @Test
    public void test4() throws UnsupportedEncodingException {
        String str = new String("abc中国");

        // String -> byte[]：调用 String 的 getBytes()
        byte[] arr = str.getBytes();    // 使用默认的字符集：utf-8
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println();

        // getBytes(String charsetName)：使用指定的字符集
        byte[] arr1 = str.getBytes("gbk");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }

        // byte[] -> String：
        String str1 = new String(arr);  // 使用默认的字符集：utf-8
        System.out.println(str1);

        String str2 = new String(arr, "utf-8"); // 显式地指明解码的字符集：utf-8
        System.out.println(str2);

        // 乱码
//        String str3 = new String(arr, "gbk"); // 显式地指明解码的字符集：gbk
//        System.out.println(str3);

        String str4 = new String(arr1, "gbk");
        System.out.println(str4);

    }

}

```

### 1.6 `String` 的常用 API - 2

`String` 类包括的方法可用于检查序列的单个字符、比较字符串、搜索字符串、提取子字符串、创建字符串副本并将所有字符全部转换为大写或小写。

#### 1.6.1 系列 1：常用方法

`boolean isEmpty()`：字符串是否为空。

`int length()`：返回字符串的长度。

`String concat(xx)`：拼接。

`boolean equals(Object obj)`：比较字符串是否相等，区分大小写。

`boolean equalsIgnoreCase(Object obj)`：比较字符串是否相等，不区分大小写。

`int compareTo(String other)`：比较字符串大小，区分大小写，按照 Unicode 编码值比较大小。

`int compareToIgnoreCase(String other)`：比较字符串大小，不区分大小写。

`String toLowerCase()`：将字符串中大写字母转为小写。

`String toUpperCase()`：将字符串中小写字母转为大写。

`String trim()`：去掉字符串前后空白符。

`public String intern()`：结果在常量池中共享。

#### 1.6.2 系列 2：查找

`boolean contains(xx)`：是否包含 `xx`。

`int indexOf(xx)`：从前往后找当前字符串中 `xx`；即如果有则返回第一次出现的下标，如果没有则返回 `-1`。

`int indexOf(String str, int fromIndex)`：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始。

`int lastIndexOf(xx)`：从后往前找当前字符串中 `xx`；即如果有则返回最后一次出现的下标，如果没有则返回 `-1`。

`int lastIndexOf(String str, int fromIndex)`：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索。

#### 1.6.3 系列 3：字符串截取

`String substring(int beginIndex)`：返回一个新的字符串，它是此字符串的从 `beginIndex` 开始截取到最后的一个子字符串。

`String substring(int beginIndex, int endIndex)`：返回一个新字符串，它是此字符串从 `beginIndex` 开始截取到 `endIndex`（不包含）的一个子字符串。

#### 1.6.4 系列 4：和字符 / 字符数组相关

`char charAt(index)`：返回 `[index]` 位置的字符。

`char[] toCharArray()`：将此字符串转换为一个新的字符数组返回。

`static String valueOf(char[] data)`：返回指定数组中表示该字符序列的 `String`。

`static String valueOf(char[] data, int offset, int count)`：返回指定数组中表示该字符序列的 `String`。

`static String copyValueOf(char[] data)`：返回指定数组中表示该字符序列的 `String`。

`static String copyValueOf(char[] data, int offset, int count)`：返回指定数组中表示该字符序列的 `String`。

#### 1.6.5 系列 5：开头与结尾

`boolean startsWith(xx)`：测试此字符串是否以指定的前缀开始。

`boolean startsWith(String prefix, int toffset)`：测试此字符串从指定索引开始的子字符串是否以指定前缀开始。

`boolean endsWith(xx)`：测试此字符串是否以指定的后缀结束。

#### 1.6.6 系列 6：替换

`String replace(char oldChar, char newChar)`：返回一个新的字符串，它是通过用 `newChar` 替换此字符串中出现的所有 `oldChar` 得到的；不支持正则。

`String replace(CharSequence target, CharSequence replacement)`：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。

`String replaceAll(String regex, String replacement)`：使用给定的 `replacement` 替换此字符串所有匹配给定的正则表达式的子字符串。

`String replaceFirst(String regex, String replacement)`：使用给定的 `replacement` 替换此字符串匹配给定的正则表达式的第一个子字符串。

#### 1.6.7 举例

示例代码：
```java
/* StringMethodTest1.java */

package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringMethodTest1
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 21:17
 * @Version 1.0
 */
public class StringMethodTest1 {

    /*
    * 系列 1：常用方法
    * */

    @Test
    public void test1() {
        String s1 = "";
        String s2 = new String();
        String s3 = new String("");

        System.out.println(s1.isEmpty());
        System.out.println(s2.isEmpty());
        System.out.println(s3.isEmpty());

        String s4 = null;
        System.out.println(s4.isEmpty());   // 报空指针异常

        String s5 = "hello";
        System.out.println(s5.length());    // 5

    }

    @Test
    public void test2() {
        String s1 = "hello";
        String s2 = "HellO";
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "abcd";
        String s4 = "adef";
        System.out.println(s3.compareTo(s4));

        String s5 = "abcd";
        String s6 = "aBcd";
        System.out.println(s5.compareTo(s6));
        System.out.println(s5.compareToIgnoreCase(s6));

        String s7 = "张ab";
        String s8 = "李cd";
        System.out.println(s7.compareTo(s8));

        String s9 = " he  llo   ";
        System.out.println("*****" + s9.trim() + "*****");

    }

    /*
    * 系列 2：查找
    * */

    @Test
    public void test3() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.contains("硅谷"));

        System.out.println(s1.indexOf("教育"));
        System.out.println(s1.indexOf("教育", 1));

        System.out.println(s1.lastIndexOf("教育"));
        System.out.println(s1.lastIndexOf("教育", 4));

    }

    /*
    * 系列 3：字符串截取
    * */

    @Test
    public void test4() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.substring(2));
        System.out.println(s1.substring(2, 5)); // [2, 5)

    }

    /*
    * 系列 4：和字符 / 字符数组相关
    *
    * 系列 5：开头与结尾
    * */

    @Test
    public void test5() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.charAt(2));

        String s2 = String.valueOf(new char[] { 'a', 'b', 'c' });
        String s3 = String.copyValueOf(new char[] { 'a', 'b', 'c' });
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s2 == s3);

        System.out.println(s1.startsWith("教育a"));
        System.out.println(s1.startsWith("教育", 5));

    }

    /*
    * 系列 6：替换
    * */

    @Test
    public void test6() {
        String s1 = "hello";
        String s2 = s1.replace('l', 'w');
        System.out.println(s1);
        System.out.println(s2);

        String s3 = s1.replace("ll", "wwww");
        System.out.println(s3);

    }

}

```

### 1.7 练习

**练习 1：**
> 题目 - 算法练习：
>
> **1.** 模拟一个 `trim` 方法，去除字符串两端的空格。
> 
> **2.** 将一个字符串进行反转，将字符串中指定部分进行反转。比如将 “`abcdefg`” 反转为 “`abfedcg`”。
>
> **3.** 获取一个字符串在另一个字符串中出现的次数。比如，获取 “`ab`” 在 “`abkkcadkabkebfkabkskab`” 中出现的次数。
>
> **4.** 获取两个字符串中最大相同字串。比如：
>   ```java
>   str1 = "abcwerthelloyuiodef";
>   str2 = "cvhellobnm";
>   ```
> 提示：将短的那个串进行长度依次递减的字串与较长的串比较。
>
> **5.** 对字符串中字符进行自然顺序排序。
> 提示：
> 1. 字符串变成字符数组。
> 2. 对数组排序，选择，冒泡，`Arrays.sort()`。
> 3. 将排序后的数组变成字符串。

示例代码：
```java

```

**练习 2：**
> 题目 - 模拟用户登录：
>
> 1. 定义用户类，属性为用户名和密码，提供相关的 `getter` 和 `setter` 方法、构造器、`toString()`。
> 2. 使用数组存储多个用户对象。
> 3. 录入用户和密码，对比用户信息；匹配成功则登录成功，否则登录失败。
>   * 登录失败时，当用户名错误，提示没有该用户。
>   * 登录失败时，当密码错误时，提示密码有误。

示例代码：
```java

```
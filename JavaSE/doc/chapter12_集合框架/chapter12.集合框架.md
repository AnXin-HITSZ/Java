# 第十二章：集合框架

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 12 章]( "第 3 阶段：Java 高级应用 - 第 12 章")

## 一、集合框架概述

### 1.1 数组的特点与弊端

一方面，面向对象语言对事物的体现都是以对象的形式，为了方便对多个对象的操作，就要对对象进行存储。

另一方面，使用数组存储对象方面具有**一些弊端**，而 Java 集合就像一种容器，可以**动态地**把多个对象的引用放入容器中。

数组在内存存储方面的**特点**：
* 数组初始化以后，长度就确定了。
* 数组中的添加的元素是依次紧密排列的、有序的、可以重复的。
* （优点）数组声明的类型，就决定了进行元素初始化时的类型；不是此类型的变量，就不能添加。
* （优点）可以存储基本数据类型值，也可以存储引用数据类型的变量。

示例代码：
```java
int[] arr = new int[10];
arr[0] = 1;
arr[1] = "AA";  // 编译报错

Object arr1 = new Object[10];
arr1[0] = new String();
arr1[1] = new Date();
```

数组在存储数据方面的**弊端**：
* 数组初始化以后，长度就不可变了，不便于扩展。
* 数组中提供的属性和方法少，不便于进行添加、删除、插入、获取元素个数等操作，且效率不高。
* 数组存储数据的特点单一，只能存储有序的、可以重复的数据。

Java 集合框架中的类可以用于存储多个**对象**，还可以用于保存具有**映射关系**的关联数组。

### 1.3 Java 集合框架体系

Java 集合可分为 `Collection` 和 `Map` 两大体系：
* `java.util.Collection` 接口：用于存储一个一个的数据，也称单列数据集合。
  * `List` 子接口：用于存储有序的、可以重复的数据（主要用来替换数组，“动态”数组）。
    * 实现类：`ArrayList`（主要实现类）、`LinkedList`、`Vector`。
  * `Set` 子接口：用来存储无序的、不可重复的数据。（类似于集合。）
    * 实现类：`HashSet`（主要实现类）、`LinkedHashSet`、`TreeSet`。
* `java.util.Map` 接口：用于存储具有映射关系“`key - value` 对”的集合，即一对一对的数据，也称双列数据集合。（类似于函数、映射，$(x_1, y_1), (x_2, y_2) \rightarrow y = f(x)$。）
  * 实现类：`HashMap`（主要实现类）、`LinkedHashMap`、`TreeMap`、`Hashtable`、`Properties`。

JDK 提供的集合 API 位于 java.util 包内。

图示 - 集合框架全图：
![集合框架全图](./images/集合框架全图.png "集合框架全图")

简图 1 - `Collection` 接口继承树：
![Collection 接口继承树](./images/image-20220407203244029.png "Collection 接口继承树")

简图 2 - `Map` 接口继承树：
![Map 接口继承树](./images/image-20220407203412665.png "Map 接口继承树")

## 二、`Collection` 接口及方法

JDK 不提供此接口的任何直接实现，而是提供更具体的子接口（如 Set 和 List）去实现。

`Collection` 接口是 `List` 和 `Set` 接口的父接口，该接口里定义的方法既可用于操作 `Set` 集合，也可用于操作 `List` 集合。方法如下。

### 2.1 添加

`add(E obj)`：添加元素对象到当前集合中。

`addAll(Collection other)`：添加 `other` 集合中的所有元素对象到当前集合中，即 $this = this \cup other$。

> 注意：
>
> `coll.addAll(other);` 与 `coll.add(other);`：
> ![`coll.addAll(other);` 与 `coll.add(other);` 的区别示意图](./images/1563548078274.png "`coll.addAll(other);` 与 `coll.add(other);` 的区别示意图")

> 注意：
>
> 向 `Collection` 中添加元素的要求：
> * 要求元素所属的类一定要重写 `equals()`！
>
> 原因：因为 `Collection` 中的相关方法（比如：`contains()` / `remove()`）在使用时，要调用元素所在类的 `equals()`。

### 2.2 判断

`int size()`：获取当前集合中实际存储的元素个数。

`boolean isEmpty()`：判断当前集合是否为空集合。

`boolean contains(Object obj)`：判断当前集合中是否存在一个与 `obj` 对象 `equals` 返回 `true` 的元素。

`boolean containsAll(Collection coll)`：判断 `coll` 集合中的元素是否在当前集合中都存在；即 `coll` 集合是否是当前集合的“子集”。

`boolean equals(Object obj)`：判断当前集合与 `obj` 是否相等。

### 2.3 删除

`void clear()`：清空集合元素。

`boolean remove(Object obj)`：从当前集合中删除第一个找到的与 `obj` 对象 `equals` 返回 `true` 的元素。

`boolean removeAll(Collection coll)`：从当前集合中删除所有与 `coll` 集合中相同的元素；即 $this = this - this \cap coll$。

`boolean retainAll(Collection coll)`：从当前集合中删除两个集合中不同的元素，使得当前集合仅保留与 `coll` 集合中的元素相同的元素；即当前集合中仅保留两个集合的交集，即 $this = this \cap coll$。

### 2.4 其它

`Object[] toArray()`：返回包含当前集合中所有元素的数组。

> 集合与数组的相互转换：
> * 集合 -> 数组：`toArray()`。
> * 数组 -> 集合：调用 `Arrays` 的静态方法 `asList(Object ... objs)`。

`hashCode()`：获取集合对象的哈希值。

`iterator()`：返回迭代器对象，用于集合遍历。

### 2.5 举例

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_01.collection;

import java.util.Objects;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_01.collection
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/11 20:45
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals() ...");
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(name);
//        result = 31 * result + age;
//        return result;
//    }

}


/* CollectionTest.java */

package com.anxin_hitsz_01.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: CollectionTest
 * Package: com.anxin_hitsz_01.collection
 * Description:
 *      测试 Collection 中方法的使用
 * @Author AnXin
 * @Create 2026/2/11 20:39
 * @Version 1.0
 */
public class CollectionTest {

    /*
    * 2.1 添加
    * */
    @Test
    public void test1() {
        Collection coll = new ArrayList();

        // add()
        coll.add("AA");
        coll.add(123);  // 自动装箱
        coll.add("尚硅谷");
        coll.add(new Object());
        coll.add(new Person("Tome", 12));

        System.out.println(coll);

        // addAll(Collection other)
        Collection coll1 = new ArrayList();
        coll1.add("BB");
        coll1.add(456);

        System.out.println(coll.size());    // 5

        coll.addAll(coll1);
//        coll.add(coll1);

        System.out.println(coll);

        // size()
        System.out.println(coll.size());    // 7

    }

    /*
    * 2.2 判断
    * */
    @Test
    public void test2() {
        Collection coll = new ArrayList();

        // add()
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // isEmpty()
        System.out.println(coll.isEmpty());

        // contains(Object obj)
        System.out.println(coll.contains("AA"));    // true
        System.out.println(coll.contains(128)); // true
        System.out.println(coll.contains(new String("尚硅谷")));   // true
        System.out.println(coll.contains(new Person("Tom", 12)));   // false -> true

        // containsAll(Collection coll)
        Collection coll1 = new ArrayList();

        // add()
        coll1.add("AA");
        coll1.add(128);
//        coll1.add("BB");

        System.out.println(coll.containsAll(coll1));

    }

    /*
    * 2.3 删除
    * */
    @Test
    public void test3() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));
        System.out.println(coll);

//        coll.clear();
//        System.out.println(coll);
//        System.out.println(coll.size());    // 0

        // remove(Object obj)
        coll.remove(new Person("Tom", 12));

        coll.remove("AA");

        System.out.println(coll);

    }

    /*
    * 2.4 其它
    * */
    @Test
    public void test4() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 集合 -> 数组
        Object[] arr = coll.toArray();
        System.out.println(Arrays.toString(arr));

        // hashCode()
        System.out.println(coll.hashCode());

    }

    @Test
    public void test5() {
        String[] arr = new String[] { "AA", "BB", "CC" };
        Collection list = Arrays.asList(arr);
        System.out.println(list);

        List list1 = Arrays.asList("AA", "BB", "CC", "DD");
        System.out.println(list1);

    }

    @Test
    public void test6() {
        Integer[] arr = new Integer[] { 1, 2, 3 };
        List list = Arrays.asList(arr);
        System.out.println(list.size());    // 3
        System.out.println(list);

        int[] arr1 = new int[] { 1, 2, 3 };
        List list1 = Arrays.asList(arr1);
        System.out.println(list1.size());   // 1
        System.out.println(list1);

    }

}

```
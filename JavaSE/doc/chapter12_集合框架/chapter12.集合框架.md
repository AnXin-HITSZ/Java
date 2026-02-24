# 第十二章：集合框架

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 12 章](./images/第3阶段：Java高级应用-第12章.png "第 3 阶段：Java 高级应用 - 第 12 章")

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

## 三、`Iterator`（迭代器）接口

### 3.1 `Iterator` 接口

在程序开发中，经常需要遍历集合中的所有元素；针对这种需求，JDK 专门提供了一个接口 `java.util.Iterator`。`Iterator` 接口也是 Java 集合中的一员，但它与 `Collection`、`Map` 接口有所不同。
* `Collection` 接口与 `Map` 接口主要用于存储元素。
* `Iterator`，被称为迭代器接口，本身并不提供存储对象的能力，主要用于遍历 `Collection` 中的元素。

`Collection` 接口继承了 `java.lang.Iterable` 接口，该接口有一个 `iterator()` 方法，那么所有实现了 `Collection` 接口的集合类都有一个 `iterator()` 方法，用以返回一个实现了 `Iterator` 接口的对象。
* `public Iterator iterator()`：获取集合对应的迭代器，用来遍历集合中的元素。
* 集合对象每次调用 `iterator()` 方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。

`Iterator` 接口的常用方法如下：
* `public E next()`：返回迭代的下一个元素。
* `public boolean hasNext()`：如果仍有元素可以迭代，则返回 `true`。

获取迭代器（`Iterator`）对象：
```java
Iterator iterator = coll.iterator();
```

实现遍历：
```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());    // next()：1. 指针下移；2. 将下移以后集合位置上的元素返回
}
```

> 注意：
>
> 在调用 `it.next()` 方法之前必须要调用 `it.hasNext()` 进行检测；若不调用，且下一条记录无效，直接调用 `it.next()` 会抛出 `NoSuchElementException` 异常。

示例代码：
```java
/* IteratorTest.java */

package com.anxin_hitsz_02.iterator;

import com.anxin_hitsz_01.collection.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * ClassName: IteratorTest
 * Package: com.anxin_hitsz_02.iterator
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 13:14
 * @Version 1.0
 */
public class IteratorTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 获取迭代器对象
        Iterator iterator = coll.iterator();
//        System.out.println(iterator.getClass());

        // 方式 1：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());

//        System.out.println(iterator.next());    // 如果超出了集合中元素的个数，会报 NoSuchElementException 异常

        // 方式 2：
//        for (int i = 0; i < coll.size(); i++) {
//            System.out.println(iterator.next());
//        }

        // 方式 3：推荐
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("BB");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 方式 1：错误的遍历
//        Iterator iterator = coll.iterator();
//
//        while ((iterator.next()) != null) {
//            System.out.println(iterator.next());
//        }

        // 方式 2：错误的遍历
        // 每次调用 coll.iterator()，都会得到一个新的迭代器对象
//        while (coll.iterator().hasNext()) {
//            System.out.println(coll.iterator().next());
//        }

    }

}

```

### 3.2 迭代器的执行原理

`Iterator` 迭代器对象在遍历集合时，内部采用指针的方式来跟踪集合中的元素，接下来通过一个图例以演示 `Iterator` 对象迭代元素的过程：
![Iterator 对象迭代元素的过程示意图](./images/image-20220407235130988.png "Iterator 对象迭代元素的过程示意图")

若要使用 `Iterator` 迭代器删除元素，`java.util.Iterator` 迭代器中又一个方法 `void remove()`：
```java
Iterator iter = coll.iterator();    // 回到起点
while (iter.hasNext()) {
    Object obj = iter.next();
    if (obj.equals("Tom")) {
        iter.remove();
    }
}
```

> 注意：
> * `Iterator` 可以删除集合的元素，但是遍历过程中是通过迭代器对象的 `remove()` 方法删除集合的元素，而不是调用集合对象的 `remove()` 方法。
> * 如果还未调用 `next()` 或在上一次调用 `next()` 方法之后已经调用了 `remove()` 方法，再调用 `remove()` 方法都会报 `IllegalStateException` 异常。

在 JDK 8.0 时，`Collection` 接口有了 `removeIf` 方法，即可以根据条件删除。

### 3.3 `foreach` 循环

`foreach` 循环（也称增强 `for` 循环）是 JDK 5.0 中定义的一个高级 `for` 循环，专门用来遍历数组和集合的。

foreach 循环的语法格式：
```java
for (要遍历的集合或数组元素的数据类型 局部变量 : 要遍历的Collection集合或数组变量) {
    // 操作局部变量的输出操作
}
// 此处的局部变量即为临时变量，可以自定义命名
```

示例代码：
```java
/* ForTest.java */

package com.anxin_hitsz_02.iterator;

import com.anxin_hitsz_01.collection.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName: ForTest
 * Package: com.anxin_hitsz_02.iterator
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 13:30
 * @Version 1.0
 */
public class ForTest {
    @Test
    public void test() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("BB");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        for (Object obj : coll) {
            System.out.println(obj);
        }

    }

    @Test
    public void test2() {
        int[] arr = new int[] { 1, 2, 3, 4 };

        for (int i : arr) {
            System.out.println(i);
        }

    }

    @Test
    public void test3() {
        String[] arr = new String[] { "AA", "BB", "CC", "DD" };

        for (String s : arr) {
            System.out.println(s);
        }

    }

}

```

对于集合的遍历，增强 `for` 循环的内部原理其实是个 `Iterator` 迭代器。如下图所示：
![增强 for 循环的内部原理示意图](./images/image-20220128010114124.png "增强 for 循环的内部原理示意图")

它用于遍历 `Collection` 和数组。通常只进行元素的遍历操作，建议不要在遍历的过程中对集合元素进行增删操作。

> 注意：
>
> 增强 `for` 循环的执行过程中，是将集合或数组中的元素依次赋值给临时变量；注意，循环体中对临时变量的修改，可能不会导致原有集合或数组中元素的修改。

## 四、`Collection` 子接口 1：`List`

### 4.1 `List` 接口特点

鉴于 Java 中数组用来存储数据的局限性，我们通常使用 `java.util.List` 来替代数组（称为“动态”数组）。

`List` 集合类中**元素有序**、且**可重复**，集合中的每个元素都有其对应的顺序索引。

JDK API 中 `List` 接口的实现类常用的有：`ArrayList`、`LinkedList` 和 `Vector`。

###  4.2 `List` 接口方法

`List` 除了从 `Collection` 集合继承的方法外，`List` 集合里添加了一些**根据索引**来操作集合元素的方法。

插入元素：
* `void add(int index, Object ele)`：在 `index` 位置插入 `ele` 元素。
* `boolean addAll(int index, Collection eles)`：从 `index` 位置开始将 `eles` 中的所有元素添加进来。

获取元素：
* `Object get(int index)`：获取指定 `index` 位置的元素。
* `List subList(int fromIndex, int toIndex)`：返回从 `fromIndex` 到 `toIndex` 位置的子集合。

获取元素索引：
* `int indexOf(Object obj)`：返回 `obj` 在集合中首次出现的位置。
* `int lastIndexOf(Object obj)`：返回 `obj` 在当前集合中末次出现的位置。

删除和替换元素：
* `Object remove(int index)`：移除指定 `index` 位置的元素，并返回此元素。
* `Object set(int index, Object ele)`：设置指定 `index` 位置的元素为 `ele`。

遍历：
* `iterator()`：使用迭代器进行遍历。
* 增强 `for` 循环。
* 一般的 `for` 循环。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_03.list;

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


/* ListTest.java */

package com.anxin_hitsz_03.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: ListTest
 * Package: com.anxin_hitsz_03.list
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 14:55
 * @Version 1.0
 */
public class ListTest {
    @Test
    public void test1() {
        List list = new ArrayList();

        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(new Person("Tom", 12));

        System.out.println(list.toString());

        // add(int index, Object ele)
        list.add(2, "CC");
        System.out.println(list);

        // addAll(int index, Collection eles)
        List list1 = Arrays.asList(1, 2, 3);

        list.addAll(1, list1);

//        list.add(1, list1); // 将 list1 整体作为一个元素，插入到索引 1 的位置
        System.out.println(list);

    }

    @Test
    public void test2() {
        List list = new ArrayList();
        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(2);    // 自动装箱
        list.add(new Person("Tom", 12));

        // 删除索引 2 的元素
//        list.remove(2);
//        System.out.println(list);
//        System.out.println(list.get(2));

        // 删除数据 2
        list.remove(Integer.valueOf(2));
        System.out.println(list);

    }

    @Test
    public void test3() {
        List list = new ArrayList();
        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(2);    // 自动装箱
        list.add(new Person("Tom", 12));

        // 遍历方式 1：使用迭代器
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        // 遍历方式 2：增强 for 循环
//        for (Object obj : list) {
//            System.out.println(obj);
//        }

        // 遍历方式 3：一般的 for 循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}

```

> 注意：
>
> 在 JavaSE 中 `List` 名称的类型有两个，一个是 `java.util.List` 集合接口，一个是 `java.awt.List` 图形界面的组件；注意不要导错包。

### 4.3 `List` 接口主要实现类：`ArrayList`

`ArrayList` 是 `List` 接口的**主要实现类**，是线程不安全的，效率高。

本质上，`ArrayList` 是对象引用的一个“变长”数组；底层使用 `Object[]` 数组存储。

`Arrays.asList(...)` 方法返回的 `List` 集合，既不是 `ArrayList` 实例，也不是 `Vector` 实例。`Arrays.asList(...)` 返回值是一个固定长度的 `List` 集合。

在添加数据、查找数据时，效率较高；在插入、删除数据时，效率较低。

### 4.4 `List` 的实现类之二：`LinkedList`

对于对集合中的数据进行频繁的插入或删除元素的操作，建议使用 `LinkedList` 类，效率较高。这是由底层采用链表（双向链表）结构存储数据决定的。

在插入、删除数据时，效率较高；在添加数据、查找数据时，效率较低。

特有方法：
* `void addFirst(Object obj)`。
* `void addLast(Object obj)`。
* `Object getFirst()`。
* `Object getLast()`。
* `Object removeFirst()`。
* `Object removeLast()`。

### 4.5 `List` 的实现类之三：`Vector`

`Vector` 是一个**古老**的集合，JDK 1.0 就有了。大多数操作与 `ArrayList` 相同，区别之处在于 `Vector` 是**线程安全**的，效率低。

底层使用 `Object[]` 数组存储。

在各种 `List` 中，最好把 `ArrayList` 作为默认选择；当插入、删除频繁时，使用 `LinkedList`；`Vector` 总是比 `ArrayList` 慢，所以尽量避免使用。

特有方法：
* `void addElement(Object obj)`。
* `void insertElementAt(Object obj, int index)`。
* `void setElementAt(Object obj, int index)`。
* `void removeElement(Object obj)`。
* `void removeAllElements()`。

### 4.6 练习

**练习 1：**
> 题目：
>
> 键盘录入学生信息，保存到集合 `List` 中。
> 1. 定义学生类，属性为姓名、年龄，提供必要的 `getter`、`setter` 方法、构造器、`toString()` 方法、`euqals()` 方法。
> 2. 使用 `ArrayList` 集合，保存录入的多个学生对象。
> 3. 循环录入的方式：`1` 为继续录入，`0` 为结束录入。
> 4. 录入结束后，用 `foreach` 遍历集合。

示例代码：
```java
/* Student.java */

package com.anxin_hitsz_03.list.exer1;

import java.util.Objects;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_03.list.exer1
 * Description:
 *      学生类
 * @Author AnXin
 * @Create 2026/2/12 15:51
 * @Version 1.0
 */
public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(name);
//        result = 31 * result + age;
//        return result;
//    }

}


/* StudentTest.java */

package com.anxin_hitsz_03.list.exer1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_03.list.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 15:52
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List list = new ArrayList();

        System.out.println("请录入学生信息：");

        // 通过循环的方式，添加多个学生信息
        while (true) {
            System.out.println("1：继续录入；0：结束录入");
            int selection = scan.nextInt();

            if (selection == 0) {
                break;
            }

            System.out.print("请输入学生的姓名：");
            String name = scan.next();
            System.out.print("请输入学生的年龄：");
            int age = scan.nextInt();

            Student s = new Student(name, age);

            list.add(s);
        }

        // 遍历集合中的学生信息
        System.out.println("遍历学生信息：");
        for (Object s : list) {
            Student stu = (Student) s;
            System.out.println(stu.toString());
        }


        scan.close();
    }
}

```

**练习 2：**
> 题目：
>
> 定义方法 `public static int listTest(Collection list, String s)` 统计集合中指定元素出现的次数。
> 1. 创建集合，集合存放随机生成的 30 个小写字母。
> 2. 用 `listTest` 统计，`a`、`b`、`c`、`x` 元素的出现次数。

示例代码：
```java
/* ListTest.java */

package com.anxin_hitsz_03.list.exer2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName: ListTest
 * Package: com.anxin_hitsz_03.list.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 16:02
 * @Version 1.0
 */
public class ListTest {
    public static void main(String[] args) {

        // 需求 1：随机生成 30 个字符，存放在 ArrayList 中
        ArrayList list = new ArrayList();

        for (int i = 0; i < 30; i++) {
            // 'a' - 'z'：[97, 122]
            list.add((char)(Math.random() * (122 - 97 + 1) + 97) + "");
        }

        System.out.println(list);

        int aCount = listTest(list, "a");
        int bCount = listTest(list, "b");
        int cCount = listTest(list, "c");
        int xCount = listTest(list, "x");

        System.out.println("a：" + aCount);
        System.out.println("b：" + bCount);
        System.out.println("c：" + cCount);
        System.out.println("x：" + xCount);

    }

    // 需求 2：遍历 ArrayList，查找指定的元素出现的次数
    public static int listTest(Collection list, String s) {
        int count = 0;
        for (Object obj : list) {
            if (s.equals(obj)) {
                count++;
            }
        }

        return count;
    }
}

```

## 五、`Collection` 子接口 2：`Set`

### 5.1 `Set` 接口概述

`Set` 接口是 `Collection` 的子接口，`Set` 接口相较于 `Collection` 接口没有提供额外的方法。

`Set` 存储无序的、不可重复的数据（类似于集合），主要用于过滤重复数据。

`Set` 集合不允许包含相同的元素，如果试着把两个相同的元素加入同一个 `Set` 集合中，则添加操作失败。

`Set` 集合支持的遍历方式和 `Collection` 集合一样：`foreach` 和 `Iterator`。

`Set` 的常用实现类有：`HashSet`、`TreeSet`、`LinkedHashSet`。

### 5.2 `Set` 主要实现类：`HashSet`

#### 5.2.1 `HashSet` 概述

`HashSet` 是 `Set` 接口的主要实现类，大多数时候时候 `Set` 集合时都使用这个实现类。

`HashSet` 底层使用的是 `HashMap`，即使用 `数组 + 单向链表 + 红黑树` 结构进行存储（JDK 8 中）。

`HashSet` 按 `Hash` 算法来存储集合中的元素，因此具有很好的存储、查找、删除性能。

`HashSet` 具有以下**特点**：
* 不能保证元素的排列顺序。
* `HashSet` 不是线程安全的。
* 集合元素可以是 `null`。

`HashSet` 集合**判断两个元素相等的标准**：两个对象通过 `hashCode()` 方法得到的哈希值相等，并且两个对象的 `euqals()` 方法返回值为 `true`。

对于存放在 `Set` 容器中的对象，**对应的类一定要重写 `hashCode()` 和 `equals(Object obj)` 方法**，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”。

`Set` 中无序性、不可重复性的理解，以下以 `HashSet` 及其子类为例进行说明：
* **无序性**：`HashSet` 集合中元素的无序性，不等同于随机性；添加元素的顺序和遍历元素的顺序不一致，这并不是所谓的“无序性”。这里的无序性与添加的元素的位置有关，不像 `ArrayList` 一样是依次紧密排列的；具体来说，我们在添加每一个元素到数组中时，具体的存储位置是由元素的 `hashCode()` 调用后返回的 `hash` 值决定的，导致在数组中每个元素不是依次紧密存放的（即此位置不是依次排列的），因此表现出一定的无序性。
* **不可重复性**：添加到 `Set` 中的元素是不能相同的；比较的标准需要判断 `hashCode()` 得到的哈希值以及 `equals()` 得到的 `boolean` 型的结果，哈希值相同且 `equals()` 返回 `true`，则认为元素是相同的。

#### 5.2.2 `HashSet` 中添加元素的过程

`HashSet` 中添加元素的步骤如下：
1. 当向 `HashSet` 集合中存入一个元素时，`HashSet` 会调用该对象的 `hashCode()` 方法得到该对象的 `hashCode` 值，然后根据 `hashCode` 值，通过某个散列函数决定该对象在 `hashSet` 底层数组中的存储位置。
2. 如果要在数组中存储的位置上没有元素，则直接添加成功。
3. 如果要在数组中存储的位置上有元素，则继续比较：
   * 如果两个元素的 `hashCode` 值不相等，则添加成功。
   * 如果两个元素的 `hashCode` 值相等，则会继续调用 `equals()` 方法：
     * 如果 `equals()` 方法结果为 `false`，则添加成功；
     * 如果 `equals()` 方法结果为 `true`，则添加失败。

> 注意：
> * 第 2 步添加成功，元素会保存在底层数组中。
> * 第 3 步两种添加成功的操作，由于该底层数组的位置已经有元素了，则会通过**链表**的方式继续链接、存储。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_04.set;

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

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + age;
        return result;
    }

}


/* SetTest.java */

package com.anxin_hitsz_04.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ClassName: SetTest
 * Package: com.anxin_hitsz_04.set
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 17:03
 * @Version 1.0
 */
public class SetTest {
    @Test
    public void test1() {
        Set set = new HashSet();

        set.add("AA");
        set.add(123);
        set.add("BB");
        set.add(new Person("Tom", 12));
        set.add(new Person("Tom", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        System.out.println(set.contains(new Person("Tom", 12)));

    }

    @Test
    public void test2() {
        Set set = new LinkedHashSet();

        set.add("AA");
        set.add(123);
        set.add("BB");
        set.add(new Person("Tom", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}

```

添加到 `HashSet` / `LinkedHashSet` 中元素的要求：
* 要求元素所在的类要重写两个方法：`equals()` 和 `hashCode()`。
* 同时，要求 `equals()` 和 `hashCode()` 要保持一致性！我们只需要在 IDEA 中自动生成两个方法的重写即可，即能保证两个方法的一致性。

#### 5.2.3 重写 `hashCode()` 方法的基本原则

在程序运行时，同一个对象多次调用 `hashCode()` 方法应该返回相同的值。

当两个对象的 `equals()` 方法比较返回 `true` 时，这两个对象的 `hashCode()` 方法的返回值也应相等。

对象中用作 `equals()` 方法比较的 `Field`，都应该用来计算 `hashCode` 值。

> 注意：
>
> 如果两个元素的 `euqals()` 方法返回 `true`，但它们的 `hashCode()` 返回值不相等，`HashSet` 将会把它们存储在不同的位置，但依然可以添加成功。

#### 5.2.4 重写 `equals()` 方法的基本原则

重写 `equals()` 方法的时候一般都需要同时复写 `hashCode()` 方法。通常参与计算 `hashCode` 的对象的属性也应该参与到 `equals()` 中进行计算。

推荐：开发中直接调用 Eclipse / IDEA 里的快捷键自动重写 `equals()` 方法和 `hashCode()` 方法即可。

### 5.3 `Set` 实现类之二：`LinkedHashSet`

`LinkedHashSet` 是 `HashSet` 的子类，不允许集合元素重复。

`LinkedHashSet` 在现有的 `数组 + 单向链表 + 红黑树` 结构的基础上，又添加了一组双向链表，用于记录添加元素的先后顺序；即：我们可以按照添加元素的顺序实现遍历，便于频繁的查询操作。

`LinkedHashSet` 根据元素的 `hashCode` 值来决定元素的存储位置，但它同时使用**双向链表**维护元素的次序，这使得元素看起来是以**添加顺序**保存的。

`LinkedHashSet` **插入性能略低于** `HashSet`，但在**迭代访问** `Set` 里的全部元素时有很好的性能。

`LinkedHashSet` 底层结构：
![LinkedHashSet 底层结构示意图](./images/image-20220408235936404.png "LinkedHashSet 底层结构示意图")

### 5.4 `Set` 实现类之三：`TreeSet`

#### 5.4.1 `TreeSet` 概述

`TreeSet` 是 `SortedSet` 接口的实现类，`TreeSet` 可以按照添加的元素的指定的属性的大小顺序进行遍历。

`TreeSet` 底层使用 `红黑树` 结构存储数据，可以按照添加的元素的指定的属性的大小顺序进行遍历。

新增的方法如下：
* `Comparator comparator()`。
* `Object first()`。
* `Object last()`。
* `Object lower(Object e)`。
* `Object higher(Object e)`。
* `SortedSet subSet(fromElement, toElement)`。
* `SortedSet headSet(toElement)`。
* `SortedSet tailSet(fromElement)`。

`TreeSet` 特点：不允许重复，实现排序（自然排序或定制排序）。

`TreeSet` 两种排序方法：**自然排序**和**定制排序**。默认情况下，`TreeSet` 采用自然排序。
* **自然排序**：`TreeSet` 会调用集合元素的 `compareTo(Object obj)` 方法来比较元素之间的大小关系，然后将集合元素按升序（默认情况）排列。
  * 如果试图把一个对象添加到 `TreeSet` 时，则该对象的类必须实现 `Comparable` 接口。
  * 实现 `Comparable` 的类必须实现 `compareTo(Object obj)` 方法，两个对象即通过 `compareTo(Object obj)` 方法的返回值来比较大小。
* **定制排序**：如果元素所属的类没有实现 `Comparable` 接口、或不希望按照升序（默认情况）的方式排列元素、或希望按照其它属性大小进行排序，则考虑使用定制排序。定制排序通过 `Comparator` 接口来实现，需要重写 `compare(T o1, T o2)` 方法。
  * 利用 `int compare(T o1, T o2)` 方法比较 `o1` 和 `o2` 的大小：如果方法返回正整数，则表示 `o1` 大于 `o2`；如果方法返回 `0`，则表示 `o1` 与 `o2` 相等；如果方法返回负整数，则表示 `o1` 小于 `o2`。
  * 要实现定制排序，需要将实现 `Comparator` 接口的实例作为形参传递给`TreeSet` 的构造器。

因为只有相同类的两个实例才会比较大小，所以向 `TreeSet` 中添加的应该是**同一个类的对象**。

对于 `TreeSet` 集合而言，它判断两个对象是否相等的唯一标准是两个对象通过 `compareTo(Object obj)` 或 `compare(Object o1, Object o2)` 方法比较返回值，返回值为 `0` 则认为两个对象相等。

向 `TreeSet` 中添加的元素的要求：
* 要求添加到 `TreeSet` 中的元素必须是同一个类型的对象，否则会报 `ClassCastException` 异常。
* 添加的元素需要考虑排序：
  1. 自然排序；
  2. 定制排序。

判断数据是否相同的标准：
* 不再是考虑 `hashCode()` 和 `equals()` 方法了，也就意味着添加到 `TreeSet` 中的元素所在的类不需要重写 `hashCode()` 和 `equals()` 方法了。
* 比较元素大小的或比较元素是否相等的标准就是考虑自然排序或定制排序中，`compareTo()` 或 `compare()` 的返回值。如果 `compareTo()` 或 `compare()` 的返回值为 `0`，则认为两个对象是相等的；由于 `TreeSet` 中不能存放相同的元素，则后一个相等的元素就不能添加到 `TreeSet` 中。

示例代码：
```java
/* User.java */

package com.anxin_hitsz_04.set;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_04.set
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:29
 * @Version 1.0
 */
public class User implements Comparable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        System.out.println("User equals() ...");
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//        return age == user.age && Objects.equals(name, user.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(name);
//        result = 31 * result + age;
//        return result;
//    }

    /*
    * 比如：按照年龄从小到大排序
    * */
//    @Override
//    public int compareTo(Object o) {
//        if (this == o) {
//            return 0;
//        }
//
//        if (o instanceof User) {
//            User u = (User) o;
//            return this.age - u.age;
//        }
//
//        throw new RuntimeException("类型不匹配");
//    }

    /*
    * 比如：先比较年龄从小到大排列；如果年龄相同，则继续比较姓名，从大到小
    * */
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }

        if (o instanceof User) {
            User u = (User) o;
            int value =  this.age - u.age;
            if (value != 0) {
                return value;
            }
            return -this.name.compareTo(u.name);
        }

        throw new RuntimeException("类型不匹配");
    }

}


/* TreeSetTest.java */

package com.anxin_hitsz_04.set;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ClassName: TreeSetTest
 * Package: com.anxin_hitsz_04.set
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:23
 * @Version 1.0
 */
public class TreeSetTest {
    /*
    * 自然排序
    * */
    @Test
    public void test1() {
        TreeSet set = new TreeSet();

        set.add("AA");
        set.add("BB");
        set.add("CC");
        set.add("DD");
        set.add("EE");
        set.add("FF");

//        set.add(123); // 会报 ClassCastException 异常

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    /*
    * 自然排序
    * */
    @Test
    public void test2() {
        TreeSet set = new TreeSet();

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        set.add(u1);
        set.add(u2);
        set.add(u3);
        set.add(u4);
        set.add(u5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    /*
    * 定制排序
    * */
    @Test
    public void test3() {
        Comparator comparator = new Comparator() {
            /*
            * 按照姓名从小到大排列；如果姓名相同，继续比较 age，按照从大到小排列
            * */
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;

                    int value = u1.getName().compareTo(u2.getName());
                    if (value != 0) {
                        return value;
                    }
                    return -(u1.getAge() - u2.getAge());
                }

                throw new RuntimeException("类型不匹配");
            }
        };

        TreeSet set = new TreeSet(comparator);

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        set.add(u1);
        set.add(u2);
        set.add(u3);
        set.add(u4);
        set.add(u5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}

```

### 5.5 练习

**练习 1：**
> 题目：
>
> 定义方法如下：`public static List duplicateList(List list)`。
>
> 要求：
> 1. 参数 `List` 中只存放 `Integer` 的对象。
> 2. 在 `List` 内去除重复数字值，尽量简单。

示例代码：
```java
/* Exer01.java */

package com.anxin_hitsz_04.set.exer1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: Exer01
 * Package: com.anxin_hitsz_04.set.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:51
 * @Version 1.0
 */
public class Exer01 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(34);
        list.add(34);
        list.add(34);
        list.add(22);
        list.add(22);
        list.add(22);
        list.add(45);
        list.add(45);
        list.add(45);

        List newList = duplicateList(list);
        System.out.println(newList);

    }

    public static List duplicateList(List list) {
        // 方式 1：
//        HashSet set = new HashSet();
//        for (Object obj : list) {
//            set.add(obj);
//        }
//
//        List list1 = new ArrayList();
//        for (Object obj : set) {
//            list1.add(obj);
//        }
//
//        return list1;

        // 方式 2：
        HashSet set = new HashSet(list);
        List list1 = new ArrayList(set);
        return list1;

    }

}

```

**练习 2：**
> 题目：
>
> 编写一个程序，获取 10 个 1 至 20 的随机数，要求随机数不能重复；并把最终的随机数输出到控制台。

示例代码：
```java
/* Exer02.java */

package com.anxin_hitsz_04.set.exer2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_04.set.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:58
 * @Version 1.0
 */
public class Exer02 {
    public static void main(String[] args) {

        Set set = new HashSet();

        while (set.size() < 10) {
            int random = (int)(Math.random() * (20 - 1 + 1) + 1);
            set.add(random);
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}

```

## 六、`Map` 接口

Java 提供了专门的集合框架用来存储映射（即一一对应）关系的对象，即 `java.util.Map` 接口。

`Map` 及其实现类对比：
* `java.util.Map`：存储一对一对的数据（`key - value` 键值对，类似于函数 $(x_1, y_1), (x_2, y_2) \rightarrow y = f(x)$）。
  * `HashMap`：主要实现类；线程不安全的，效率高。可以添加 `null` 的 `key` 和 `value` 值。底层使用 `数组 + 单向链表 + 红黑树` 结构存储（JDK 8）。
    * `LinkedHashMap`：是 `HashMap` 的子类。在 `HashMap` 使用的数据结构的基础上，增加了一对双向链表，用于记录添加的元素的先后顺序，进而我们在遍历元素时，就可以按照添加的顺序显示。开发中，对于频繁的遍历操作，建议使用此类。
  * `TreeMap`：底层使用 `红黑树` 存储。可以按照添加的 `key - value` 中的 `key` 元素的指定的属性的大小顺序进行遍历。需要考虑使用 1. 自然排序 2. 定制排序。
  * `Hashtable`：古老实现类；线程安全的，效率低。不可以添加 `null` 的 `key` 或 `value` 值。底层使用 `数组 + 单向链表` 结构存储（JDK 8）。
    * `Properties`：其 `key` 和 `value` 都是 `String` 类型。常用来处理属性文件。

> 注意：
>
> `HashMap` 的底层实现：
> 1. `new HashMap()`。
> 2. `put(key, value)`。

示例代码：
```java
/* MapTest.java */

package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: MapTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 19:52
 * @Version 1.0
 */
public class MapTest {
    @Test
    public void test1() {
        Map map = new HashMap();

        map.put(null, null);
        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

    @Test
    public void test2() {
        Map map = new Hashtable();

//        map.put(null, 123);

        map.put("AA", null);

        System.out.println(map);

    }

    @Test
    public void test3() {
        LinkedHashMap map = new LinkedHashMap();

        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

}

```

### 6.1 `Map` 接口概述

`Map` 与 `Collection` 并列存在，用于保存具有**映射关系**的数据：`key - value`。
* `Collection` 集合称为单列集合，元素是孤立存在的。
* `Map` 集合称为双列集合，元素是成对存在的。

`Map` 中的 `key` 和 `value` 都可以是任何引用类型的数据，但常用 `String` 类作为 `Map` 的“键”。

`Map` 接口的常用实现类：`HashMap`、`LinkedHashMap`、`TreeMap` 和 `Properties`。其中 `HashMap` 是 `Map` 接口使用频率最高的实现类。

![Map 接口及其继承关系](./images/image-20220409001015034.png "Map 接口及其继承关系")

### 6.2 `Map` 中 `key - value` 特点

这里主要以 `HashMap` 为例说明。`HashMap` 中存储的 `key`、`value` 的特点如下：
* `Map` 中的 **`key` 用 `Set` 来存放**，**不允许重复**；即同一个 `Map` 对象所对应的类，须重写 `hashCode()` 方法和 `equals()` 方法。
* `key` 和 `value` 之间存在单向一对一关系，即通过指定的 `key` 总能找到唯一的、确定的 `value`，**不同 `key` 对应的 `value` 可以重复**。`value` 所在的类要重写 `equals()` 方法。
* `key` 和 `value` 构成一个 `entry`，所有的 `entry` 彼此之间是**无序的**、**不可重复的**。

![Map 中 key 和 value 构成一个 entry](./images/image-20220514190412763.png "Map 中 key 和 value 构成一个 entry")

`HashMap` 中元素的特点：
* `HashMap` 中的所有的 `key` 彼此之间是不可重复的、无序的，所有的 `key` 就构成一个 `Set` 集合。（即：`key` 所在的类要重写 `hashCode()` 方法和 `equals()` 方法。）
* `HashMap` 中的所有的 `value` 彼此之间是可重复的、无序的，所有的 `value` 就构成一个 `Collection` 集合。（即：`value` 所在的类要重写 `equals()` 方法。）
* `HashMap` 中的一个 `key - value`，就构成了一个 `entry`。
* `HashMap` 中的所有的 `entry` 彼此之间是不可重复的、无序的，所有的 `entry` 就构成了一个 `Set` 集合。

### 6.3  `Map` 接口的常用方法

添加、修改操作：
* `Object put(Object key, Object value)`：将指定 `key - value` 添加到（或修改）当前 `map` 对象中。
* `void putAll(Map m)`：将 `m` 中的所有 `key - value` 对存放到当前 `map` 中。

删除操作：
* `Object remove(Object key)`：移除指定 `key` 的 `key - value` 对，并返回 `value`。
* `void clear()`：清空当前 `map` 中的所有数据。

元素查询的操作：
* `Object get(Object key)`：获取指定 `key` 对应的 `value`。
* `boolean containsKey(Object key)`：是否包含指定的 `key`。
* `boolean containsValue(Object value)`：是否包含指定的 `value`。
* `int size()`：返回 `map` 中 `key - value` 对的个数。
* `boolean isEmpty()`：判断当前 `map` 是否为空。
* `boolean equals(Object obj)`：判断当前 `map` 和参数对象 `obj` 是否相等。

元视图操作的方法：
* `Set keySet()`：返回所有 `key` 构成的 `Set` 集合。
* `Collection values()`：返回所有 `value` 构成的 `Collection` 集合。
* `Set entrySet()`：返回所有 `key - value` 对构成的 `Set` 集合。

遍历：
* 遍历 `key` 集：`Set keySet()`：返回所有 `key` 构成的 `Set` 集合。
* 遍历 `value` 集：`Collection values()`：返回所有 `value` 构成的 `Collection` 集合。
* 遍历 `entry` 集：`Set entrySet()`：返回所有 `key - value` 对构成的 `Set` 集合。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_05.map;

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

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + age;
        return result;
    }

}


/* MapTest.java */

package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: MapTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 19:52
 * @Version 1.0
 */
public class MapTest {

    /*
    * 测试 Map 中的实现类
    * */

    @Test
    public void test1() {
        Map map = new HashMap();

        map.put(null, null);
        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

    @Test
    public void test2() {
        Map map = new Hashtable();

//        map.put(null, 123);

        map.put("AA", null);

        System.out.println(map);

    }

    @Test
    public void test3() {
        LinkedHashMap map = new LinkedHashMap();

        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

    /*
    * 测试 Map 中的常用方法
    * */

    @Test
    public void test4() {
        HashMap map = new HashMap();

        // 添加：Object put(Object key, Object value)
        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        System.out.println(map);
        // int size()
        System.out.println(map.size());

        // Object remove(Object key)
        Object value = map.remove("AA");
        System.out.println(value);
        System.out.println(map);

        // 修改：Object put(Object key, Object value)
        Object oldValue =  map.put("BB", 99);
        System.out.println(oldValue);   // 78
        System.out.println(map);

        // Object get(Object key)
        Object value1 = map.get(67);
        System.out.println(value1);

    }

    // Map 的遍历操作
    @Test
    public void test5() {
        HashMap map = new HashMap();

        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        // 遍历 key 集：Set keySet()
        Set keySet = map.keySet();
        // 使用迭代器
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key);
        }

        // 遍历 value 集：Collection values()
        // 方式 1：推荐
//        Collection values = map.values();
//        // 使用增强 for
//        for (Object obj : values) {
//            System.out.println(obj);
//        }
        // 方式 2：
//        Set keySet1 = map.keySet();
//        for (Object key : keySet1) {
//            Object value = map.get(key);
//            System.out.println(value);
//        }

    }

    @Test
    public void test6() {
        HashMap map = new HashMap();

        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        // 方式 1：遍历 entry 集：Set entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            // 方法 1：
//            System.out.println(iterator.next());
            // 方法 2：
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 方式 2：遍历 entry 集：keySet()、get(key)
//        Set keySet = map.keySet();
//        for (Object key : keySet) {
//            System.out.println(key + " -> " + map.get(key));
//        }

    }

}

```

### 6.4 `Map` 的主要实现类：`HashMap`

`HashMap` 是 `Map` 接口使用频率最高的实现类。

`HashMap` 是线程不安全的。允许添加 `null` 键和 `null` 值。

存储数据采用的哈希表结构，底层使用 `一维数组 + 单向链表 + 红黑树` 进行 `key - value` 数据的存储。与 `HashSet` 一样，元素的存取顺序不能保证一致。

`HashMap` **判断两个 `key` 相等的标准**是：两个 `key` 的 `hashCode` 值相等，通过 `equals()` 方法返回 `true`。

`HashMap` **判断两个 `value` 相等的标准**是：两个 `value` 通过 `equals()` 方法返回 `true`。

### 6.5 Map 实现类之二：`LinkedHashMap`

`LinkedHashMap` 是 `HashMap` 的子类。

存储数据采用 `哈希表结构 + 链表结构`，在 `HashMap` 存储结构的基础上，使用了一对**双向链表**来**记录添加元素的先后顺序**，可以保证遍历元素时，与添加的顺序一致。

通过哈希表结构可以保证键的唯一、不重复，需要键所在类重写 `hashCode()` 方法、`equals()` 方法。

### 6.5 Map 实现类之三：`TreeMap`

`TreeMap` 存储 `key - value` 对时，需要根据 `key - value` 对进行排序。

`TreeMap` 可以保证所有的 `key - value` 对处于**有序状态**。

`TreeMap` 底层使用 `红黑树` 结构存储数据。

`TreeMap` 可以按照添加的 `key - value` 中的 `key` 元素的指定的属性的大小顺序进行遍历；需要考虑使用 1. 自然排序 2. 定制排序。

> 要求：向 `TreeMap` 中添加的 `key` 必须是同一个类型的对象。

`TreeMap` 的 `key` 的排序：
* **自然排序**：`TreeMap` 的所有的 `key` 必须实现 `Comparable` 接口，而且所有的 `key` 应该是同一个类的对象，否则将会抛出 `ClassCastException` 异常。
* **定制排序**：创建 `TreeMap` 时，构造器传入一个 `Comparator` 对象，该对象负责对 `TreeMap` 中的所有 `key` 进行排序；此时不需要 `Map` 的 `key` 实现 `comparable` 接口。

`TreeMap` 判断两个 `key` 相等的标准：两个 `key` 通过 `compareTo()` 方法或者 `compare()` 方法返回 `0`。

示例代码：
```java
/* User.java */

package com.anxin_hitsz_05.map;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_04.set
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:29
 * @Version 1.0
 */
public class User implements Comparable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        System.out.println("User equals() ...");
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//        return age == user.age && Objects.equals(name, user.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(name);
//        result = 31 * result + age;
//        return result;
//    }

    /*
    * 比如：按照年龄从小到大排序
    * */
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }

        if (o instanceof User) {
            User u = (User) o;
            return this.age - u.age;
        }

        throw new RuntimeException("类型不匹配");
    }

    /*
    * 比如：先比较年龄从小到大排列；如果年龄相同，则继续比较姓名，从大到小
    * */
//    @Override
//    public int compareTo(Object o) {
//        if (this == o) {
//            return 0;
//        }
//
//        if (o instanceof User) {
//            User u = (User) o;
//            int value =  this.age - u.age;
//            if (value != 0) {
//                return value;
//            }
//            return -this.name.compareTo(u.name);
//        }
//
//        throw new RuntimeException("类型不匹配");
//    }

}


/* TreeMapTest.java */

package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

/**
 * ClassName: TreeMapTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 16:24
 * @Version 1.0
 */
public class TreeMapTest {

    /*
    * 自然排序
    * */
    @Test
    public void test1() {
        TreeMap map = new TreeMap();

        map.put("AA", 89);
        map.put("BB", 78);
        map.put("CC", new Date());
        map.put("DD", 78);

//        map.put(67, 78);  // 报错，因为 key 的类型（Integer）与之前的 key 的类型（String）不一致

        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            System.out.println(entry);
        }

    }

    @Test
    public void test2() {
        TreeMap map = new TreeMap();

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        map.put(u1, 78);
        map.put(u2, 76);
        map.put(u3, 88);
        map.put(u4, 45);
        map.put(u5, 99);

//        Set entrySet = map.entrySet();
//        for (Object entry : entrySet) {
//            System.out.println(entry);
//        }

//        System.out.println(map.containsKey(new User("Maria", 33)));

    }

    /*
    * 定制排序
    * */
    @Test
    public void test3() {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;

                    int value = u1.getName().compareTo(u2.getName());
                    if (value != 0) {
                        return value;
                    }
                    return u1.getAge() - u2.getAge();
                }

                throw new RuntimeException("类型不匹配");
            }
        };

        TreeMap map = new TreeMap(comparator);

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        map.put(u1, 78);
        map.put(u2, 76);
        map.put(u3, 88);
        map.put(u4, 45);
        map.put(u5, 99);

        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            System.out.println(entry);
        }

    }

}

```

### 6.6 `Map` 实现类之四：`Hashtable`

`Hashtable` 是 `Map` 接口的**古老实现类**，JDK 1.0 狐疑提供了。不同于 `HashMap`，`Hashtable` 是线程安全的。

`Hashtable` 实现原理和 `HashMap` 相同，功能相同；底层都使用 `哈希表` 结构（`数组 + 单向链表`），查询速度快。

与 `HashMap` 一样，`Hashtable` 也不能保证其中 `key - value` 对的顺序。

`Hashtable` 判断两个 `key` 相等、两个 `value` 相等的标准，与 `HashMap` 一致。

与 `HashMap` 不同，`Hashtable` 不允许使用 `null` 作为 `key` 或 `value`。

> 面试题：
>
> `Hashtable` 和 `HashMap` 的区别：
>
> `HashMap`：底层是一个哈希表（JDK 7：`数组 + 链表`；JDK 8：`数组 + 链表 + 红黑树`），是一个线程不安全的集合，执行效率高。
> `Hashtable`：底层也是一个哈希表（`数组 + 链表`），是一个线程安全的集合，执行效率低。
>
> `HashMap` 集合：可以存储 `null` 的键、`null` 的值。
> `Hashtable` 集合：不能存储 `null` 的键、`null` 的值。
>
> `Hashtable` 和 `Vector` 集合一样，在 JDK 1.2 版本之后被更先进的集合（`HashMap`、`ArrayList`）取代了。所以 `HashMap` 是 `Map` 的主要实现类，`Hashtable` 是 `Map` 的古老实现类。
>
> `Hashtable` 的子类 `Properties`（配置文件）依然活跃在历史舞台。
>
> `Properties` 集合是一个唯一和 IO 流相结合的集合。

### 6.7 Map 实现类之五：`Properties`

`Properties` 类是 `Hashtable` 的子类，该对象用于处理属性文件。

由于属性文件里的 `key`、`value` 都是字符串类型，所以 `Properties` 中要求 `key` 和 `value` 都是字符串类型。

存取数据时，建议使用 `setProperty(String key, String value)` 方法和 `getProperty(String key)` 方法。

示例代码：
```java
/* info.properties */

name=汤姆
password=abc123


/* PropertiesTest.java */

package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ClassName: PropertiesTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 16:44
 * @Version 1.0
 */
public class PropertiesTest {
    @Test
    public void test() throws IOException { // 注意：因为涉及到流大的操作，为了确保流能关闭，建议使用 try - catch - finally
        // 方式 1：数据和代码耦合度高；如果修改的话，需要重新编译代码、打包发布，繁琐
        // 数据
//        String name = "Tom";
//        String password = "abc123";

        // 代码：用于操作 name、password 的代码
        // ...

        // 方式 2：将数据封装到具体的配置文件中，在程序中读取配置文件中的信息；实现了
        //          数据和代码的解耦：由于我们没有修改代码，就省去了重新编译和打包的过程
        File file = new File("info.properties");    // 注意：要提前创建好
//        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);

        Properties pros = new Properties();
        pros.load(fis); // 加载流中的文件中的数据

        // 读取数据
        String name = pros.getProperty("name");
        String pwd = pros.getProperty("password");

        System.out.println(name + ":" + pwd);

        fis.close();

    }

//    public static void main(String[] args) {
//        File file = new File("info.properties");
////        System.out.println(file.getAbsolutePath());
//    }

}

```

### 6.8 练习

**练习 1：**
> 题目：
>
> 添加你喜欢的歌手以及你喜欢 TA 唱过的歌曲，并遍历。

示例代码：
```java
/* SingerTest.java */

package com.anxin_hitsz_05.map.exer1;

import java.util.*;

/**
 * ClassName: SingerTest
 * Package: com.anxin_hitsz_05.map.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 16:58
 * @Version 1.0
 */
public class SingerTest {
    public static void main(String[] args) {
        HashMap singers = new HashMap();
        // 添加 1 个歌手和其歌曲
        String singer1 = "周杰伦";

        ArrayList songs1 = new ArrayList();
        songs1.add("夜曲");
        songs1.add("晴天");
        songs1.add("七里香");
        songs1.add("发如雪");
        songs1.add("屋顶");
        songs1.add("青花瓷");

        singers.put(singer1, songs1);

        // 再添加 1 个歌手和其歌曲
        String singer2 = "林俊杰";
        ArrayList songs2 = new ArrayList();
        songs2.add("江南");
        songs2.add("曹操");
        songs2.add("小酒窝");
        songs2.add("可惜没如果");

        singers.put(singer2, songs2);

        Set entrySet = singers.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("歌手：" + entry.getKey());
            System.out.println("歌曲有：" + entry.getValue());
        }

    }

}

```

**练习 2：**
> 题目 - 二级联动：
>
> 将省份和城市的名称保存在集合中；当用户选择省份以后，二级联动，显示对应省份的地级市供用户选择。
>
> 提示：如果输入的省份不正确，需要重新输入；如果输入的城市不正确，需要重新输入。

示例代码：
```java
/* CityMapTest.java */

package com.anxin_hitsz_05.map.exer2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName: CityMapTest
 * Package: com.anxin_hitsz_05.map.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 17:07
 * @Version 1.0
 */
public class CityMapTest {
    public static void main(String[] args) {
        // 1. 获取 Map，并遍历 Map 中的所有 key
        Map map = CityMap.model;

        Set provinces = map.keySet();
        for (Object province : provinces) {
            System.out.print(province + "\t\t");
        }

        // 2. 根据提示，从键盘获取省份值，判断此省份是否存在：
        //      如果存在，遍历其 value 中的各个城市
        //      如果不存在，提示用户重新输入
        Scanner scan = new Scanner(System.in);
        String[] cities;
        while (true) {
            System.out.println("\n请选择你所在的省份：");
            String province = scan.next();
            // 获取省份对应的各个城市构成的 String[]
            cities = (String[]) map.get(province);

            if (cities == null || cities.length == 0) {
                System.out.println("你输入的省份有误，请重新输入");
            } else {
                break;  // 意味着用户输入的省份是存在的，则跳出当前循环
            }
        }
        for (int i = 0; i < cities.length; i++) {
            System.out.print(cities[i] + "\t\t");
        }
        System.out.println();

        // 3. 根据提示，从键盘获取城市，遍历各个城市构成的 String[]，判断输入的城市是否存在于此数组中：
        //      如果存在，信息等级完毕
        //      如果不存在，提示用户重新输入
        l:while (true) {
            System.out.println("请选择你所在的城市：");
            String city = scan.next();
            // 方式 1：
//            for (int i = 0; i < cities.length; i++) {
//                if (city.equals(cities[i])) {
//                    System.out.println("信息登记完毕");
//                    break l;
//                }
//            }
//
//            System.out.println("输入的城市有误，请重新输入");

            // 方式 2:
            if (containsCity(cities, city)) {
                System.out.println("信息登记完毕");
                break;
            }

            System.out.println("输入的城市有误，请重新输入");
        }


        scan.close();

    }

    // 第 2 种方式处理城市是否存在
    public static boolean containsCity(String[] cities, String city) {
        for (int i = 0; i < cities.length; i++) {
            if (city.equals(cities[i])) {
                return true;
            }
        }

        return false;
    }
}

class CityMap {

    public static Map model = new HashMap();

    static {
        model.put("北京", new String[] {"北京"});
        model.put("辽宁", new String[] {"沈阳","盘锦","铁岭","丹东","大连","锦州","营口"});
        model.put("吉林", new String[] {"长春","延边","吉林","白山","白城","四平","松原"});
        model.put("河北", new String[] {"承德","沧州","邯郸","邢台","唐山","保定","石家庄"});
        model.put("河南", new String[] {"郑州","许昌","开封","洛阳","商丘","南阳","新乡"});
        model.put("山东", new String[] {"济南","青岛","日照","临沂","泰安","聊城","德州"});
    }

}

```

## 七、`Collections` 工具类

参考操作数组的工具类 `Arrays`，`Collections` 是一个操作 `Set`、`List` 和 `Map` 等集合的工具类。

### 7.1 常用方法

`Collections` 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法（均为 `static` 方法）。

排序操作：
* `reverse(List)`：反转 `List` 中元素的顺序。
* `shuffle(List)`：对 `List` 集合元素进行随机排序。
* `sort(List)`：根据元素的自然顺序对指定 `List` 集合元素按升序排序。
* `sort(List, Comparator)`：根据指定的 `Comparator` 产生的顺序对 `List` 集合元素进行排序。
* `swap(List, int, int)`：将指定 `list` 集合中的 `i` 处元素和 `j` 处元素进行交换。

查找：
* `Object max(Collection)`：根据元素的自然顺序，返回给定集合中的最大元素。
* `Object max(Collection, Comparator)`：根据 `Comparator` 指定的顺序，返回给定集合中的最大元素。

> 注意：
>
> 默认以集合排序后序列最右侧元素为该集合中的最大元素。

* `Object min(Collection)`：根据元素的自然顺序，返回给定集合中的最小元素。
* `Object min(Collection, Comparator)`：根据 `Comparator` 指定的顺序，返回给定集合中的最小元素。

> 注意：
>
> 默认以集合排序后序列最左侧元素为该集合中的最小元素。

* `int binarySearch(List list, T key)`：在 `List` 集合中查找某个元素的下标，但是 `List` 的元素必须是 `T` 或 `T` 的子类对象，而且必须是可比较大小的，即支持自然排序的；而且集合也事先必须是有序的，否则结果不确定。
* `int binarySearch(List list, T key, Comparator c)`：在 `List` 集合中查找某个元素的下标，但是 `List` 的元素必须是 `T` 或 `T` 的子类对象，而且集合也事先必须是按照 `c` 比较器规则进行排序过的，否则结果不确定。
* `int frequency(Collection c, Object o)`：返回指定集合中指定元素的出现次数。

复制、替换：
* `void copy(List dest, List src)`：将 `src` 中的内容复制到 `dest` 中。

> 注意：
>
> 在 `void copy(List dest, List src)` 方法中，需要确保 `dest.size() >= src.size()`。

* `boolean replaceAll(List list, Object oldVal, Object newVal)`：使用新值替换 `List` 对象的所有旧值。
* 提供了多个 `unmodifiableXxx()` 方法，该方法返回指定 `Xxx` 的不可修改的视图。

添加：
* `boolean addAll(Collection c, T ... elements)`：将所有指定元素添加到指定 `Collection` 中。

同步：
* `Collections` 类中提供了多个 `synchronizedXxx()` 方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题：
![Collections 类中提供的多个 synchronizedXxx() 方法](./images/image-20220409003002526.png "Collections 类中提供的多个 synchronizedXxx() 方法")

> 面试题：
>
> 区分 `Collection` 和 `Collections`：
> * `Collection`：集合框架中的用于存储一个一个元素的接口，又分为 `List` 和 `Set` 等子接口。
> * `Collections`：用于操作集合框架的一个工具类，此时的集合框架包括 `Set`、`List`、`Map`。

### 7.2 举例

示例代码：
```java
/* CollectionsTest.java */

package com.anxin_hitsz_06.collections;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: CollectionsTest
 * Package: com.anxin_hitsz_06.collections
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 18:13
 * @Version 1.0
 */
public class CollectionsTest {

    /*
    * 排序操作
    * */

    @Test
    public void test1() {
        List list = Arrays.asList(45, 43, 65, 6, 43, 2, 32, 45, 56, 34, 23);
        System.out.println(list);
        // reverse(List)：反转 List 中元素的顺序
//        Collections.reverse(list);

        // shuffle(List)：对 List 集合元素进行随机排序
//        Collections.shuffle(list);

        // sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
//        Collections.sort(list);

        // sort(List, Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
        Collections.sort(list,  new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;

//                    return i1 - i2;
                    return -(i1.intValue() - i2.intValue());
                }

                throw new RuntimeException("类型不匹配");
            }
        });


        System.out.println(list);

    }

    /*
    * 查找
    * */

    @Test
    public void test2() {
        List list = Arrays.asList(45, 43, 65, 6, 43, 2, 32, 45, 56, 34, 23);
        System.out.println(list);

        Object max =  Collections.max(list);

        Object max1 = Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;

//                    return i1 - i2;
                    return -(i1.intValue() - i2.intValue());
                }

                throw new RuntimeException("类型不匹配");
            }
        });

        System.out.println(max);
        System.out.println(max1);

        int count = Collections.frequency(list, 45);
        System.out.println("45 出现了 " + count + " 次");

    }

    /*
    * 复制、替换
    * */

    @Test
    public void test3() {
        List src = Arrays.asList(45, 43, 65, 6, 43, 2, 32, 45, 56, 34, 23);

        // void copy(List dest, List src)：将 src 中的内容复制到 dest 中
        // 错误的写法：
//        List dest = new ArrayList();
        // 正确的写法：
        List dest = Arrays.asList(new Object[src.size()]);

        Collections.copy(dest, src);

        System.out.println(dest);

    }

    @Test
    public void test4() {
        // 提供了多个 unmodifiableXxx() 方法，该方法返回指定 Xxx 的不可修改的视图

        List list1 = new ArrayList();
        // list1 可以写入数据
        list1.add(34);
        list1.add(12);
        list1.add(45);

        List list2 = Collections.unmodifiableList(list1);
        // 此时的 list2 只能读，不能写
        list2.add("AA");    // 不能写
        System.out.println(list2.get(0));   // 34

    }

    /*
    * 同步
    * */

    @Test
    public void test5() {
        // Collections 类中提供了多个 synchronizedXxx() 方法
        List list1 = new ArrayList();
        // 返回的 list2 就是线程安全的
        List list2 = Collections.synchronizedList(list1);
        list2.add(123);

        HashMap map1 = new HashMap();
        // 返回的 map2 就是线程安全的
        Map map2 = Collections.synchronizedMap(map1);

    }

}

```

### 7.3 练习

**练习：**
> 题目：
>
> 模拟斗地主洗牌和发牌，牌没有排序。
>
> 提示：不要忘记大王、小王。
> 
> 初始化数据结构，声明如下：
> ```java
> String[] num = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
> String[] color = { "方片♦", "梅花♣", "红桃♥", "黑桃♠" };
> ArrayList poker = new ArrayList();
> ```
>
> 以上为题目描述。

示例代码：
```java
/* PokerTest.java */

package com.anxin_hitsz_06.collections.exer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ClassName: PokerTest
 * Package: com.anxin_hitsz_06.collections.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 18:42
 * @Version 1.0
 */
public class PokerTest {
    public static void main(String[] args) {

        // 1. 组成一副扑克牌
        String[] num = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] color = { "方片♦", "梅花♣", "红桃♥", "黑桃♠" };
        ArrayList poker = new ArrayList();

        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < num.length; j++) {
                poker.add(color[i] + " " + num[j]);
            }
        }

        // 添加大小王
        poker.add("小王");
        poker.add("大王");

        // 2. 洗牌
        Collections.shuffle(poker);

        // 3. 发牌
        // 3.1 创建 3 个角色和 1 个底牌对应的 4 个 ArrayList
        ArrayList tom = new ArrayList();
        ArrayList jerry = new ArrayList();
        ArrayList me = new ArrayList();
        ArrayList lastCards = new ArrayList();

        for (int i = 0; i < poker.size(); i++) {
            if (i >= poker.size() - 3) {
                lastCards.add(poker.get(i));
            } else if (i % 3 == 0) {
                tom.add(poker.get(i));
            } else if (i % 3 == 1) {
                jerry.add(poker.get(i));
            } else if (i % 3 == 2) {
                me.add(poker.get(i));
            }
        }

        // 3.2 遍历显示 4 个 ArrayList
        System.out.println("Tom：");
        System.out.println(tom);

        System.out.println("Jerry：");
        System.out.println(jerry);

        System.out.println("Me：");
        System.out.println(me);

        System.out.println("底牌：");
        System.out.println(lastCards);

    }
}

```
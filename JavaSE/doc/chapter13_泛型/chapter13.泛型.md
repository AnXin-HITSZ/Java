# 第十三章：泛型（Generic）

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 13 章](./images/第3阶段：Java高级应用-第13章.png "第 3 阶段：Java 高级应用 - 第 13 章")

## 一、泛型概述

> Java 中的泛型，类似于生活场景中的**标签**。

### 1.1 泛型的引入

> 在集合中使用泛型之前可能存在的问题：
> 1. 问题 1：类型不安全；例如 `add()` 的参数是 `Object` 类型，意味着任何类型的对象都可以添加成功。
> 2. 问题 2：需要使用强转操作，繁琐；还可能导致 `ClassCastException` 异常。

在 Java 中，我们在声明方法时，当在完成方法功能时如果有**未知的数据**需要参与，这些未知的数据需要在调用方法时才能确定，那么我们把这样的数据通过**形参**表示。在方法体中，用这个形参名来代表那个未知的数据，而调用者在调用时，对应地传入**实参**就可以了。

![Java 中方法的声明示例](./images/1563414367674.png "Java 中方法的声明示例")

受以上启发，JDK 1.5 设计了泛型的概念。泛型即为“**类型参数**”，这个类型参数在声明它的类、接口或方法中，代表未知的某种通用类型。

> 泛型，是程序中出现的不确定的类型。
>
> 以集合来举例：把一个集合中的内容限制为一个特定的数据类型，这就是 generic 背后的核心思想。

**举例 1：**

集合类在设计阶段 / 声明阶段不能确定这个容器到底实际存的是什么类型的对象，所以**在 JDK 5.0 之前只能把元素类型设计为 `Object`，JDK 5.0 时 Java 引入了“参数化类型（Parameterized type）”的概念，允许我们在创建集合时指定集合元素的类型**。比如：`List<String>`，这表明该 `List` 只能保存字符串类型的对象。

使用集合存储数据时，除了元素的类型不确定，其他部分使确定的（例如关于这个元素如何保存、如何管理等）。

**举例 2：**

`java.lang.Comparable` 接口和 `java.util.Comparator` 接口是用于比较对象大小的接口。这两个接口只是限定了当一个对象大于另一个对象时返回正整数、小于返回负整数、等于返回 `0`，但是并不确定是什么类型的对象比较大小。JDK 5.0 之前只能用 `Object` 类型表示，使用时既麻烦又不安全；因此，JDK 5.0 给它们增加了泛型。

![Comparable 的泛型表示](./images/image-20220923154058074.png "Comparable 的泛型表示")

![Comparator 的泛型表示](./images/image-20220923154426871.png "Comparator 的泛型表示")

其中 `<T>` 就是类型参数，即泛型。

> 所谓泛型，就是允许在定义类、接口时通过一个**标识**表示类中某个**属性的类型**或者是某个方法的**返回值或参数的类型**。这个类型参数将在使用时（例如：继承或实现这个接口、创建对象或调用方法时）确定（即传入实际的类型参数，也称为类型实参）。

## 二、使用泛型举例

自从 JDK 5.0 引入泛型的概念之后，对之前核心类库中的 API 做了很大的修改，例如JDK 5.0 改写了集合框架中的全部接口类、`java.lang.Comparable` 接口、`java.util.Comparator` 接口、`Class` 类等；为这些接口、类增加了泛型支持，从而可以在声明变量、创建对象时传入类型实参。

### 2.1 集合中使用泛型

#### 2.1.1 举例

集合中没有使用泛型时：
![集合中没有使用泛型时](./images/image-20220411001522636.png "集合中没有使用泛型时")

集合中使用泛型时：
![集合中使用泛型时](./images/image-20220411001549747.png "集合中使用泛型时")

> Java 泛型可以保证如果程序在编译时没有发出警告，运行时就不会产生 `ClassCastException` 异常。即：把不安全的因素在编译期间就排除了，而不是运行期；既然通过了编译，那么类型一定是符合要求的，就避免了类型转换。
>
> 同时，代码更加简洁、健壮。
>
> **把一个集合中的内容限制为一个特定的数据类型，这就是 generic 背后的核心思想。**

示例代码：
```java
/* CollectionMapTest.java */

package com.anxin_hitsz_01.use;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: CollectionMapTest
 * Package: com.anxin_hitsz_01.use
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 14:39
 * @Version 1.0
 */
public class CollectionMapTest {

    // 体会集合中使用泛型前的场景
    @Test
    public void test1() {
        List list = new ArrayList();
        list.add(67);
        list.add(78);
        list.add(76);
        list.add(99);

        // 1. 问题 1：类型不安全；因为 add() 的参数是 Object 类型，意味着任何类型的对象都可以添加成功
//        list.add("AA");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            // 2. 问题 2：需要使用强转操作，繁琐；还有可能导致 ClassCastException 异常
            Integer i = (Integer) iterator.next();
            int score = i;

            System.out.println(score);
        }

    }

    // 在集合中使用泛型的例子
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<Integer>();

        list.add(78);
        list.add(76);
        list.add(66);
        list.add(99);

        // 编译报错，保证类型的安全
//        list.add("AA");

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            // 因为添加的都是 Integer 类型，避免了强转操作
            Integer i = iterator.next();
            int score = i;

            System.out.println(score);
        }

    }

    /*
    * 泛型在 Map 中使用的例子
    * */
    @Test
    public void test3() {
//        HashMap<String, Integer> map = new HashMap<String, Integer>();

        // jdk 7 的新特性
        HashMap<String, Integer> map = new HashMap<>(); // 类型推断

        map.put("Tom", 67);
        map.put("Jerry", 87);
        map.put("Rose", 99);

//        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

        var entrySet = map.entrySet();
        var iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " -> " + value);
        }

    }
}

```

#### 2.1.2 练习

**练习 1：**

题目：
1. 定义一个 `Employee` 类，该类包括 `private` 成员变量 `name`、`age`、`birthday`，其中 `birthday` 为 `MyDate` 类的对象；并为每一个属性定义 `getter`、`setter` 方法，并重写 `toString` 方法输出 `name`、`age`、`birthday`。
2. `MyDate` 类包含 `private` 成员变量 `year`、`month`、`day`，并为每一个属性定义 `getter`、`setter` 方法。
3. 创建该类的 5 个对象，并把这些对象放入 `TreeSet` 集合中（`TreeSet` 需使用泛型来定义）。
4. 分别按以下两种方式对集合中的元素进行排序，并遍历输出：
   1. 使 `Employee` 实现 `Comparable` 接口，并按 `name` 排序。
   2. 创建 `TreeSet` 时传入 `Comparator` 对象，按生日日期的先后排序。

示例代码：
```java
/* MyDate.java */

package com.anxin_hitsz_01.use.exer1;

/**
 * ClassName: MyDate
 * Package: com.anxin_hitsz_01.use.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:01
 * @Version 1.0
 */
public class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }

    // 按照生日从小到大排列
    @Override
    public int compareTo(MyDate o) {
        int yearDistinct = this.getYear() - o.getYear();
        if (yearDistinct != 0) {
            return yearDistinct;
        }

        int monthDistinct = this.getMonth() - o.getMonth();
        if (monthDistinct != 0) {
            return monthDistinct;
        }

        return this.getDay() - o.getDay();

    }

}


/* Employee.java */

package com.anxin_hitsz_01.use.exer1;

/**
 * ClassName: Employee
 * Package: com.anxin_hitsz_01.use.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:01
 * @Version 1.0
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=[" + birthday +
                "]}";
    }

    // 按照 name 从低到高排序
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }

}


/* EmployeeTest.java */

package com.anxin_hitsz_01.use.exer1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ClassName: EmployeeTest
 * Package: com.anxin_hitsz_01.use.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:04
 * @Version 1.0
 */
public class EmployeeTest {

    // 需求 1：使 Employee 实现 Comparable 接口，并按 name 排序
    @Test
    public void test1() {

        TreeSet<Employee> set = new TreeSet<>();

        Employee e1 = new Employee("HanMeimei", 18, new MyDate(1998, 12, 21));
        Employee e2 = new Employee("LiLei", 20, new MyDate(1996, 11, 21));
        Employee e3 = new Employee("LiHua", 21, new MyDate(2000, 9, 12));
        Employee e4 = new Employee("ZhangLei", 19, new MyDate(1997, 5, 31));
        Employee e5 = new Employee("ZhangYi", 23, new MyDate(2001, 11, 2));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        // 遍历
        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee);
        }

    }

    // 需求 2：创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序
    @Test
    public void test2() {

        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            public int compare(Employee o1, Employee o2) {

                // 错误的写法：
//                return o1.getBirthday().toString().compareTo(o2.getBirthday().toString());

                // 正确的写法 1：
//                int yearDistinct = o1.getBirthday().getYear() - o2.getBirthday().getYear();
//                if (yearDistinct != 0) {
//                    return yearDistinct;
//                }
//
//                int monthDistinct = o1.getBirthday().getMonth() - o2.getBirthday().getMonth();
//                if (monthDistinct != 0) {
//                    return monthDistinct;
//                }
//
//                return o1.getBirthday().getDay() - o2.getBirthday().getDay();

                // 正确的写法 2：
                return o1.getBirthday().compareTo(o2.getBirthday());

            }
        };

        TreeSet<Employee> set = new TreeSet<>(comparator);

        Employee e1 = new Employee("HanMeimei", 18, new MyDate(1998, 12, 21));
        Employee e2 = new Employee("LiLei", 20, new MyDate(1996, 11, 21));
        Employee e3 = new Employee("LiHua", 21, new MyDate(2000, 9, 12));
        Employee e4 = new Employee("ZhangLei", 19, new MyDate(1996, 5, 31));
        Employee e5 = new Employee("ZhangYi", 23, new MyDate(2000, 9, 2));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        // 遍历
        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee);
        }

    }
}

```

**练习 2：**

题目：
1. 创建一个 `ArrayList` 集合对象，并指定泛型为 `<Integer>`。
2. 添加 5 个 `[0, 100)` 以内的随机整数到集合中。
3. 使用 `foreach` 遍历输出 5 个整数。
4. 使用集合的 `removeIf` 方法删除偶数，为 `Predicate` 接口指定泛型 `<Integer>`。
5. 再使用 `Iterator` 迭代器输出剩下的元素，为 `Iterator` 接口指定泛型 `<Integer>`。

示例代码：
```java
/* Exer02.java */

package com.anxin_hitsz_01.use.exer2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_01.use.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:42
 * @Version 1.0
 */
public class Exer02 {
    public static void main(String[] args) {
        // 1. 创建一个 ArrayList 集合对象，并指定泛型为 <Integer>
        ArrayList<Integer> list = new ArrayList<>();

        // 2. 添加 5 个 [0, 100) 以内的随机整数到集合中
        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * (99 - 0 + 1));
            list.add(random);
        }

        // 3. 使用 foreach 遍历输出 5 个整数
        for (Integer value : list) {
            System.out.println(value);
        }

        // 4. 使用集合的 removeIf 方法删除偶数，为 Predicate 接口指定泛型 <Integer>
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value % 2 == 0;
            }
        });

        System.out.println();

        // 5. 再使用 Iterator 迭代器输出剩下的元素，为 Iterator 接口指定泛型 <Integer>
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }

    }

}

```

### 2.2 相关使用说明

在创建集合对象的时候，可以指明泛型的类型。具体格式如下所示：
```java
List<Integer> list = new ArrayList<Integer>();
```

JDK 7.0 时，有新特性，可以简写为：
```java
List<Integer> list = new ArrayList<>(); // 类型推断
```

> 注意：
>
> JDK 7.0 新特性：
> ```java
> ArrayList<String> list = new ArrayList<>(); // 类型推断
> ```
>
> 后续版本的新特性：
```java
Comparator<Employee> comparator = new Comparator<>() { ... };   // 类型推断
```

泛型，也称为泛型参数，即参数的类型，只能使用引用数据类型进行赋值；不能使用基本数据类型，可以使用包装类替换。

集合声明时，声明泛型参数；在使用集合时，可以具体指明泛型的类型。一旦指明，类或接口内部，凡是使用泛型参数的位置，都指定为具体的参数类型；如果没有指明的话，看做是 `Object` 类型。

> 总结：
>
> 集合框架在声明接口和其实现类时，使用了泛型（JDK 5.0）。在实例化集合对象时，如果没有使用泛型，则认为操作的是 `Object` 类型的数据；如果使用了泛型，则需要指明泛型的具体类型，一旦指明了泛型的具体类型，则在集合的相关的方法中，凡是使用类的泛型的位置，都替换为具体的泛型类型。

## 三、自定义泛型结构

### 3.1 泛型的基础说明

#### 3.1.1 泛型的语法形式

`<类型>` 这种语法形式就叫泛型。

`<类型>` 的形式我们称为类型参数，这里的“类型”习惯上使用 `T` 表示，是 Type 的缩写，即 `<T>`。

`<T>` 代表未知的数据类型，我们可以指定为 `<String>`、`<Integer>`、`<Circle>` 等。
* 类比方法的参数的概念，我们把 `<T>` 称为类型形参，将 `<Circle>` 称为类型实参，有助于我们理解泛型。

这里的 `T`，可以替换成 `K`、`V` 等任意字母。

#### 3.1.2 类型变量 `<T>` 的声明位置

声明类或接口时，在类名或接口名后面声明泛型类型，我们把这样的类或接口称为**泛型类**或**泛型接口**。

语法格式：
```java
[修饰符] class 类名<类型变量列表> [extends 父类] [implements 接口们] {
    ...
}

[修饰符] interface 接口名<类型变量列表> [implements 接口们] {
    ...
}

// 例如：
public class ArrayList<E> {
    ...
}
public interface Map<K, V> {
    ...
}
```

声明方法时，在 `[修饰符]` 与返回值类型之间声明类型变量，我们把声明了类型变量的方法，称为泛型方法。

语法格式：
```java
[修饰符] <类型变量列表> 返回值类型 方法名([形参列表]) [throws 异常列表] {
    ...
}

// 例如：java.util.Arrays 类中的
public static <T> List<T> asList(T ... a) {
    ...
}
```

### 3.2 自定义泛型类或泛型接口

当我们在类或接口中定义某个成员时，该成员的相关类型是不确定的，而这个类型需要在使用这个类或接口时才可以确定，那么我们可以使用泛型类、泛型接口。

自定义泛型类的语法格式：
```java
class A<T> {
    ...
}
```

自定义泛型接口的语法形式：
```java
interface B<T1, T2> {
    ...
}
```

#### 3.2.1 说明

我们在声明完自定义泛型类以后，可以在类的内部（比如：属性、方法、构造器中）使用类的泛型。

我们在创建自定义泛型类的对象时，可以指明泛型参数类型。一旦指明，内部凡是使用类的泛型参数的位置，都具体化为指定的类的泛型类型。

如果在创建自定义泛型类的对象时，没有指明泛型参数类型，那么泛型将被擦除，泛型对应的类型均按照 `Object` 处理，但不等价于 `Object`。

> 经验：泛型要使用一路都用；要不用，一路都不要用。

泛型的指定中必须使用引用数据类型；不能使用基本数据类型，此时只能使用包装类替换。

除创建泛型类对象外，子类继承泛型类时、实现类实现泛型接口时，也可以确定泛型结构中的泛型参数。比如 `SubOrder2`。

如果我们在给泛型类提供子类时，子类也不确定泛型的类型，则可以继续使用泛型参数。比如 `SubOrder3`。

我们还可以在现有的父类的泛型参数的基础上，新增泛型参数。比如 `SubOrder4`、`SubOrder5`。

#### 3.2.2 注意事项

泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：`<E1, E2, E3>`。

JDK 7.0 开始，泛型的简化操作：
```java
ArrayList<Fruit> flist = new ArrayList<>();
```

如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。

不能使用 `new E[]`，但是可以使用如下方式：
```java
E[] elements = (E[]) new Object[capacity];
```
> 参考：`ArrayList` 源码中声明 `Object[] elementData`，而非泛型参数类型数组。

在类 / 接口上声明的泛型，在本类或本接口中即代表某种类型，但不可以在静态方法中使用类的泛型。

异常类不能是带泛型的。

### 3.3 自定义泛型方法

如果我们定义类、接口时没有使用 `<泛型参数>`，但是某个方法形参类型不确定时，这个方法可以单独定义 `<泛型参数>`。

语法格式：
```java
权限修饰符 <T> 返回值类型 方法名(形参列表) {    // 通常在形参列表或返回值类型的位置会出现泛型参数 T
    ...
}
```

示例代码：
```java
public <E> E method(E e) {
    ...
}
```

#### 3.3.1 说明

泛型方法的格式：
```java
[访问权限] <泛型> 返回值类型 方法名([泛型标识 参数名称]) [抛出的异常] {
    ...
}
```

方法也可以被泛型化，与其所在的类是否是泛型类没有关系。

泛型方法中的泛型参数在方法被调用时确定。

泛型方法可以根据需要，声明为 `static` 的。

> 注意：
> * 声明泛型方法时，一定要添加泛型参数 `<T>`。
> * 泛型参数在方法调用时，指明其具体的类型。
> * 泛型方法可以根据需要声明为 `static` 的。
> * 泛型方法所属的类是否是一个泛型类，都可以。

判断某一方法是否为泛型方法的技巧：观察该方法签名的返回值类型前是否有泛型说明 `<泛型>`，若有则为泛型方法，否则不是泛型方法。

### 3.4 举例

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 16:55
 * @Version 1.0
 */
public class Person {
}


/* Order.java */

package com.anxin_hitsz_02.selfdefine;

import java.util.ArrayList;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 17:00
 * @Version 1.0
 */
public class Order<T> {

    // 声明了类的泛型参数以后，就可以在类的内部使用此泛型参数

    T t;
    int orderId;

//    static T t1;

    public Order() {
    }

    public Order(T t, int orderId) {
        this.t = t;
        this.orderId = orderId;
    }

    // 如下的两个方法不是泛型方法
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "t=" + t +
                ", orderId=" + orderId +
                '}';
    }

    // 不可以在静态方法中使用类的泛型
//    public static void method1() {
//        System.out.println("t: " + t);
//
//    }

    // 自定义泛型方法
    public <E> E method(E e) {
        return null;
    }

    // 定义泛型方法，将 E[] 数组元素添加到对应类型的 ArrayList 中，并返回
    public <E> ArrayList<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }

        return list;
    }

}


/* MyException.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: MyException
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 17:35
 * @Version 1.0
 */
//public class MyException<T> extends Exception {
//    // 细节略
//}


/* SubOrder1.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder1
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder1 不是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:10
 * @Version 1.0
 */
public class SubOrder1 extends Order {
}


/* SubOrder2.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder2
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder2 不是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:13
 * @Version 1.0
 */
public class SubOrder2 extends Order<Integer> {

//    public <E> ArrayList<E> copyFromArrayToList(E[] arr) {
//        ArrayList<E> list = new ArrayList<>();
//        for (E e : arr) {
//            list.add(e);
//        }
//
//        return list;
//    }

}


/* SubOrder3.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder3
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder3 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:16
 * @Version 1.0
 */
public class SubOrder3<T> extends Order<T> {

    public void show(T t) {
        System.out.println(t);
    }

}


/* SubOrder4.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder4
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder4 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:18
 * @Version 1.0
 */
public class SubOrder4<E> extends Order<Integer> {

    E e;

    public SubOrder4() {
    }

    public SubOrder4(E e) {
        this.e = e;
    }

    public SubOrder4(Integer integer, int orderId, E e) {
        super(integer, orderId);
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

}


/* SubOrder5.java */

package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder5
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder5 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:22
 * @Version 1.0
 */
public class SubOrder5<T, E> extends Order<T> {
    E e;

    public SubOrder5() {
    }

    public SubOrder5(T t, int orderId, E e) {
        super(t, orderId);
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

}


/* GenericTest.java */

package com.anxin_hitsz_02.selfdefine;

import org.junit.Test;

import java.util.ArrayList;

/**
 * ClassName: GenericTest
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 16:56
 * @Version 1.0
 */
public class GenericTest {

    @Test
    public void test1() {
        Person p1 = new Person();

//        Person<String> p2 = new Person<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("AA");
//        list.add(123);

        ArrayList list1 = new ArrayList();  // 向下兼容，jdk 5.0 之前集合是没有声明为泛型的
        list1.add(123);
        list1.add("AA");

    }

    // 测试自定义的泛型类
    @Test
    public void test2() {
        // 实例化时，就可以指明类的泛型参数的类型
        Order order = new Order();  // 不等价于 Order<Object> order3 = new Order<>();
        Object obj = order.getT();

        // 泛型参数在指明时，是不可以使用基本数据类型的！但是可以使用包装类替代基本数据类型。
//        order<int> order1 = new Order<>();
        // 在实例化时，可以指明类的泛型参数的具体类型！一旦指明了泛型的类型，则在泛型类中凡是使用泛型
        // 参数的位置，都替换为指定的类型。
        Order<Integer> order2 = new Order<Integer>();
        Integer t = order2.getT();

        Order<String> order3 = new Order<>();
        order3.setT("AA");

    }

    // 测试 Order 的子类
    @Test
    public void test3() {
        // 实例化 SubOrder1
        SubOrder1 sub1 = new SubOrder1();
        Object t = sub1.getT();

//        SubOrder1<Integer> sub1 = new SubOrder1<>();  // 因为 SubOrder1 不是泛型类，此处编译错误

    }

    @Test
    public void test4() {
        SubOrder2 sub1 = new SubOrder2();
        Integer t = sub1.getT();


        SubOrder3<String> sub2 = new SubOrder3<>();
        String t1 = sub2.getT();
        sub2.show("AA");

        SubOrder4<String> sub3 = new SubOrder4<>();
        Integer t2 = sub3.getT();
        String e = sub3.getE();

        SubOrder5<String, Integer> sub4 = new SubOrder5<>();
        String t3 = sub4.getT();
        Integer e1 = sub4.getE();

    }

    // 测试泛型方法的使用
    @Test
    public void test5() {
        Order<String> order1 = new Order<>();

        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5 };
        ArrayList<Integer> list = order1.copyFromArrayToList(arr);
        for (Integer i : list) {
            System.out.println(i);
        }

    }

}

```

### 3.5 应用

示例代码：
```java
/* DAO.java */

package com.anxin_hitsz_02.selfdefine.apply;

import java.util.List;

/**
 * ClassName: DAO
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *      DAO：data（base） access object，内部封装了操作数据库相关表的增删改查操作（CRUD）。
 *
 * @Author AnXin
 * @Create 2026/2/24 19:51
 * @Version 1.0
 */
public class DAO<T> {

    // 增
    public void insert(T bean) {
        // 通过相应的 sql 语句，将 bean 对象的属性值写入到数据表中
    }

    // 删
    public T deleteById(int id) {
        // 略
        return null;
    }

    // 改
    public void update(int id, T bean) {
        // 略
    }

    // 查
    // 查询一条记录
    public T queryForInstance(int id) {
        // 略
        return null;
    }
    // 查询多条记录构成的集合
    public List<T> queryForList(int id) {
        // 略
        return null;
    }

    // 定义泛型方法
    // 比如：查询表中的记录数（E：Long 类型）
    // 比如：查询表中最大的生日（E：Date 类型）
    public <E> E getValue(String sql) {
        // 略
        return null;
    }

}


/* Customer.java */

package com.anxin_hitsz_02.selfdefine.apply;

import java.sql.Date;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 *      ORM（object relational mapping）思想
 *      数据库中的一个表与 Java 中的一个类对应
 *      表中的一条记录与 Java 类的一个对象对应
 *      表中的一个字段（或列）与 Java 类的一个属性（或字段）对应
 *
 * @Author AnXin
 * @Create 2026/2/24 19:54
 * @Version 1.0
 */
public class Customer {
    int id;
    String name;
    String email;
    Date birth;

}


/* CustomerDAO.java */

package com.anxin_hitsz_02.selfdefine.apply;

/**
 * ClassName: CustomerDAO
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:03
 * @Version 1.0
 */
public class CustomerDAO extends DAO<Customer> {
}


/* Order.java */

package com.anxin_hitsz_02.selfdefine.apply;

import java.sql.Date;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 19:57
 * @Version 1.0
 */
public class Order {
    int orderId;
    String orderName;
    Date orderDate;

}


/* OrderDAO.java */

package com.anxin_hitsz_02.selfdefine.apply;

/**
 * ClassName: OrderDAO
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:06
 * @Version 1.0
 */
public class OrderDAO extends DAO<Order> {
}


/* DAOTest.java */

package com.anxin_hitsz_02.selfdefine.apply;

import org.junit.Test;

import java.util.List;

/**
 * ClassName: DAOTest
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:04
 * @Version 1.0
 */
public class DAOTest {
    @Test
    public void test1() {
        CustomerDAO dao1 = new CustomerDAO();

        dao1.insert(new Customer());

        Customer customer = dao1.queryForInstance(1);

    }

    @Test
    public void test2() {
        OrderDAO dao1 = new OrderDAO();
        dao1.insert(new Order());

        List<Order> list = dao1.queryForList(1);

    }
}

```

### 3.6 练习

**练习 1：**

题目：
> 定义一个泛型类 `DAO<T>`，在其中定义一个 `Map` 成员变量，`Map` 的键为 `String` 类型，值为 `T` 类型。
> 分别创建以下方法：
> * `public void save(String id, T entity)`：保存 `T` 类型的对象到 `Map` 成员变量中。
> * `public T get(String id)`：从 `Map` 中获取 `id` 对应的对象。
> * `public void update(String id, T entity)`：替换 `Map` 中 `key` 为 `id` 的内容，改为 `entity` 对象。
> * `public List<T> list()`：返回 `Map` 中存放的所有 `T` 对象。
> * `public void delete(String id)`：删除指定 `id` 对象。
>
> 定义一个 `User` 类，该类包含 `private` 成员变量 `int id`、`int age`、`String name`。
>
> 定义一个测试类，创建 `DAO` 类的对象，分别调用其 `save()`、`get()`、`update()`、`list()`、`delete()`  方法来操作 `User` 对象，并使用 `Junit` 单元测试类进行测试。

示例代码：
```java
/* User.java */

package com.anxin_hitsz_02.selfdefine.exer1;

import java.util.Objects;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_02.selfdefine.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:21
 * @Version 1.0
 */
public class User {
    private int id;
    private int age;
    private String name;

    public User() {
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

}


/* DAO.java */

package com.anxin_hitsz_02.selfdefine.exer1;

import java.util.*;

/**
 * ClassName: DAO
 * Package: com.anxin_hitsz_02.selfdefine.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:12
 * @Version 1.0
 */
public class DAO<T> {
    Map<String, T> map;

    {
        map = new HashMap<>();
    }

    public void save(String id, T entity) { // 保存 T 类型的对象到 Map 成员变量中
        if (!map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    public T get(String id) {   // 从 Map 中获取 id 对应的对象
        return map.get(id);
    }

    public void update(String id, T entity) {   // 替换 Map 中 key 为 id 的内容，改为 entity 对象
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    public List<T> list() { // 返回 Map 中存放的所有 T 对象
        // 错误的：
//        Collection<T> values = map.values();
//        System.out.println(values.getClass());
//        return (List<T>)values; // 报 ClassCastException 异常

        // 正确的 - 方式 1：
//        Collection<T> values = map.values();
//        ArrayList<T> list = new ArrayList<>();
//        list.addAll(values);
//
//        return list;

        // 正确的 - 方式 2：
        Collection<T> values = map.values();
        ArrayList<T> list = new ArrayList<>(values);

        return list;
    }

    public void delete(String id) { // 删除指定 id 对象
        map.remove(id);
    }

}


/* DAOTest.java */

package com.anxin_hitsz_02.selfdefine.exer1;

import java.util.List;

/**
 * ClassName: DAOTest
 * Package: com.anxin_hitsz_02.selfdefine.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:23
 * @Version 1.0
 */
public class DAOTest {
    public static void main(String[] args) {

        DAO<User> dao = new DAO<>();

        dao.save("1001", new User(1, 33, "A"));
        dao.save("1002", new User(1, 23, "B"));

        dao.update("1002", new User(3, 26, "C"));

        dao.delete("1002");

        List<User> list = dao.list();
        for (User u : list) {
            System.out.println(u);
        }

    }
}

```

**练习 2：**

题目：
1. 编写一个泛型方法 `public static <E> void method1(E[] e, int a, int b)`，实现任意引用类型数组指定位置元素交换。
2. 编写一个泛型方法 `public static <E> void method2(E[] e)`，接收一个任意引用类型的数组，并反转数组中的所有元素。

示例代码：
```java
/* Exer02.java */

package com.anxin_hitsz_02.selfdefine.exer2;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_02.selfdefine.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:28
 * @Version 1.0
 */
public class Exer02 {
    // 编写一个泛型方法，实现任意引用类型数组指定位置元素交换
    public static <E> void method1(E[] e, int a, int b) {
        E temp = e[a];
        e[a] = e[b];
        e[b] = temp;
    }

    @Test
    public void test1() {
        String[] arr = new String[] { "AA", "BB", "CC" };
        method1(arr, 0, 2);

        System.out.println(Arrays.toString(arr));
    }

    // 编写一个泛型方法，接收一个任意引用类型的数组，并反转数组中的所有元素
    public static <E> void method2(E[] e) {
        for (int i = 0, j = e.length - 1; i < j; i++, j--) {
            E temp = e[i];
            e[i] = e[j];
            e[j] = temp;
        }
    }

    @Test
    public void test2() {
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6 };
        method2(arr);

        System.out.println(Arrays.toString(arr));
    }
}

```

**练习 3：**

题目：
> 我们要声明一个学生类，该类包含姓名、成绩；而此时学生的成绩类型不确定，因为语文老师希望成绩是 `"优秀"`、`"良好"`、`"及格"`、`"不及格"`，数学老师希望成绩是 `89.5`、`65.0`、……，英语老师希望成绩是 `'A'`、`'B'`、`'C'`、`'D'`、`'E'`。那么我们在设计这个学生类时，就可以使用泛型。

示例代码：
```java
/* Student.java */

package com.anxin_hitsz_02.selfdefine.exer3;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_02.selfdefine.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:33
 * @Version 1.0
 */
public class Student<T> {
    private String name;
    private T score;    // 成绩

    public Student() {
    }

    public Student(String name, T score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

}


/* StudentTest.java */

package com.anxin_hitsz_02.selfdefine.exer3;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_02.selfdefine.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:35
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {

        /*
        * 语文老师希望成绩是 "优秀"、"良好"、"及格"、"不及格"
        * 数学老师希望成绩是 89.5、65.0、……
        * 英语老师希望成绩是 'A'、'B'、'C'、'D'、'E'
        * */

        Student<String> s1 = new Student<>("Tom", "良好");

        Student<Double> s2 = new Student<>("Jerry", 87.5);  // 87.5 自动装箱

        Student<Character> s3 = new Student<>("Rose", 'A'); // 'A' 自动装箱

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

    }
}

```

## 四、泛型在继承上的体现

如果 `B` 是 `A` 的一个子类型（子类或者子接口），而 `G` 是具有泛型声明的类或接口，`G<B>` 并不是 `G<A>` 的子类型！

比如：`String` 是 `Object` 的子类，但是 `List<String>` 并不是 `List<Object>` 的子类。

![泛型在继承上的体现 - 示例](./images/image-20220411003422259.png "泛型在继承上的体现 - 示例")

> 总结：
> 
> 类 `SuperA` 是类 `A` 的父类，则 `G<SuperA>` 与 `G<A>` 的关系：`G<SuperA>` 和 `G<A>` 是并列的两个类，没有任何子父类的关系。
> * 比如：`ArrayList<Object>`、`ArrayList<String>` 没有关系。
>
> 类 `SuperA` 是类 `A` 的父类或接口，`SuperA<G>` 与 `A<G>` 的关系：`SuperA<G>` 与 `A<G>` 有继承或实现的关系；即 `A<G>` 的实例可以赋值给 `SuperA<G>` 类型的引用（或变量）。
> * 比如：`List<String>` 与 `ArrayList<String>`。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:34
 * @Version 1.0
 */
public class Person<T> {
    String name;
    T t;

}


/* Father.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Father
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 22:07
 * @Version 1.0
 */
public class Father {
}


/* Son.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Son
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 22:07
 * @Version 1.0
 */
public class Son extends Father {
}


/* GenericTest.java */

package com.anxin_hitsz_03.more;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:30
 * @Version 1.0
 */
public class GenericTest {
    @Test
    public void test1() {
        // 1.
        Object obj = null;
        String str = "AA";

        obj = str;  // 基于继承性的多态的使用

        // 2.
        Object[] arr = null;
        String[] arr1 = null;

        arr = arr1; // 基于继承性的多态的使用

    }

    /*
    * 类 SuperA 是类 A 的父类，则 G<SuperA> 与 G<A> 的关系：
    *   G<SuperA> 和 G<A> 是并列的两个类，没有任何子父类的关系。
    * */
    @Test
    public void test2() {
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

//        list1 = list2;
        /*
        * 反证法：
        * 假设 list1 = list2 是可以的。则根据以下推理即可得出矛盾：
        * 1. list2.add("AA");
        * 2. list1.add(123);
        * 3. String str = list2.get(1);  // 相当于取出的 123 赋值给了 str，是错误的。
        *
        * */

        method(list1);
//        method(list2);  // 错误的

    }

    @Test
    public void test3() {
        Person<Object> per = null;
        Person<String> per1 = null;
//        per = per1;

    }

    public void method(ArrayList<Object> list) {

    }

    /*
    * 类 SuperA 是类 A 的父类或接口，SuperA<G> 与 A<G> 的关系：
    *   SuperA<G> 与 A<G> 有继承或实现的关系；即 A<G> 的实例可以赋值给 SuperA<G> 类型的引用（或变量）。
    * */
    @Test
    public void test4() {
        List<String> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

        list1 = list2;

        list1.add("AA");

        method(list2);

    }

    public void method(List<String> list) {

    }

}


/* GenericTest1.java */

package com.anxin_hitsz_03.more;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest1
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:51
 * @Version 1.0
 */
public class GenericTest1 {

    /*
    * 测试：通配符 ? 的使用
    * */

    @Test
    public void test1() {

        List<?> list = null;
        List<Object> list1 = null;
        List<String> list2 = null;

        list = list1;
        list = list2;

        method(list1);
        method(list2);

    }

    public void method(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }

//        list.add("AA");   // 错误的
    }

    @Test
    public void test2() {
        List<?> list = null;

        List<String> list1 = new ArrayList<>();
        list1.add("AA");

        list = list1;

        // 读取数据（以集合为例说明）
        Object obj = list.get(0);
        System.out.println(obj);

        // 写入数据（以集合为例说明）
        // 写入数据，操作失败
//        list.add("BB");
        // 特例：可以将 null 写入集合中
        list.add(null);

    }

    /*
    * 测试：有限制条件的通配符的使用
    * * ? extends A：
    * * ? super A：
    *
    * */

    @Test
    public void test3() {
        List<? extends Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

//        list = list1;
        list = list2;
        list = list3;
    }

    /*
    * 针对于 ? extends A 的读写
    * */
    @Test
    public void test3_1() {
        List<? extends Father> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        // 读取数据：可以的
        Father father = list.get(0);

        // 写入数据：不可以的；例外：null
        list.add(null);
//        list.add(new Father());
//        list.add(new Son());

    }

    @Test
    public void test4() {
        List<? super Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        list = list1;
        list = list2;
//        list = list3;
    }

    @Test
    public void test4_1() {
        List<? super Father> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        // 读取数据：可以的
        Object object = list.get(0);

        // 写入数据：可以将 Father 及其子类的对象添加进来
        list.add(null);
//        list.add(new Object());
        list.add(new Father());
        list.add(new Son());
    }

}

```

## 五、通配符的使用

当我们声明一个变量 / 形参时，这个变量 / 形参的类型是一个泛型类或泛型接口，例如 `Comparator<T>` 类型；但是我们仍然无法确定这个泛型类或泛型接口的类型变量 `<T>` 的具体类型，此时我们考虑使用类型通配符 `?`。

示例代码：
```java
/* Person.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:34
 * @Version 1.0
 */
public class Person<T> {
    String name;
    T t;

}


/* Father.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Father
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 22:07
 * @Version 1.0
 */
public class Father {
}


/* Son.java */

package com.anxin_hitsz_03.more;

/**
 * ClassName: Son
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 22:07
 * @Version 1.0
 */
public class Son extends Father {
}


/* GenericTest.java */

package com.anxin_hitsz_03.more;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:30
 * @Version 1.0
 */
public class GenericTest {
    @Test
    public void test1() {
        // 1.
        Object obj = null;
        String str = "AA";

        obj = str;  // 基于继承性的多态的使用

        // 2.
        Object[] arr = null;
        String[] arr1 = null;

        arr = arr1; // 基于继承性的多态的使用

    }

    /*
    * 类 SuperA 是类 A 的父类，则 G<SuperA> 与 G<A> 的关系：
    *   G<SuperA> 和 G<A> 是并列的两个类，没有任何子父类的关系。
    * */
    @Test
    public void test2() {
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

//        list1 = list2;
        /*
        * 反证法：
        * 假设 list1 = list2 是可以的。则根据以下推理即可得出矛盾：
        * 1. list2.add("AA");
        * 2. list1.add(123);
        * 3. String str = list2.get(1);  // 相当于取出的 123 赋值给了 str，是错误的。
        *
        * */

        method(list1);
//        method(list2);  // 错误的

    }

    @Test
    public void test3() {
        Person<Object> per = null;
        Person<String> per1 = null;
//        per = per1;

    }

    public void method(ArrayList<Object> list) {

    }

    /*
    * 类 SuperA 是类 A 的父类或接口，SuperA<G> 与 A<G> 的关系：
    *   SuperA<G> 与 A<G> 有继承或实现的关系；即 A<G> 的实例可以赋值给 SuperA<G> 类型的引用（或变量）。
    * */
    @Test
    public void test4() {
        List<String> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

        list1 = list2;

        list1.add("AA");

        method(list2);

    }

    public void method(List<String> list) {

    }

}


/* GenericTest1.java */

package com.anxin_hitsz_03.more;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest1
 * Package: com.anxin_hitsz_03.more
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 21:51
 * @Version 1.0
 */
public class GenericTest1 {

    /*
    * 测试：通配符 ? 的使用
    * */

    @Test
    public void test1() {

        List<?> list = null;
        List<Object> list1 = null;
        List<String> list2 = null;

        list = list1;
        list = list2;

        method(list1);
        method(list2);

    }

    public void method(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }

//        list.add("AA");   // 错误的
    }

    @Test
    public void test2() {
        List<?> list = null;

        List<String> list1 = new ArrayList<>();
        list1.add("AA");

        list = list1;

        // 读取数据（以集合为例说明）
        Object obj = list.get(0);
        System.out.println(obj);

        // 写入数据（以集合为例说明）
        // 写入数据，操作失败
//        list.add("BB");
        // 特例：可以将 null 写入集合中
        list.add(null);

    }

    /*
    * 测试：有限制条件的通配符的使用
    * * ? extends A：
    * * ? super A：
    *
    * */

    @Test
    public void test3() {
        List<? extends Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

//        list = list1;
        list = list2;
        list = list3;
    }

    /*
    * 针对于 ? extends A 的读写
    * */
    @Test
    public void test3_1() {
        List<? extends Father> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        // 读取数据：可以的
        Father father = list.get(0);

        // 写入数据：不可以的；例外：null
        list.add(null);
//        list.add(new Father());
//        list.add(new Son());

    }

    @Test
    public void test4() {
        List<? super Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        list = list1;
        list = list2;
//        list = list3;
    }

    @Test
    public void test4_1() {
        List<? super Father> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        // 读取数据：可以的
        Object object = list.get(0);

        // 写入数据：可以将 Father 及其子类的对象添加进来
        list.add(null);
//        list.add(new Object());
        list.add(new Father());
        list.add(new Son());
    }

}

```

### 5.1 通配符的理解

使用类型通配符：`?`。

`G<?>` 可以看做是 `G<A>` 类型的父类，即可以将 `G<A>` 的对象赋值给 `G<?>` 类型的引用（或变量）。

比如：`List<?>`、`Map<?, ?>`、`List<?>` 是 `List<String>`、`List<Object>` 等各种泛型 `List` 的父类。

举例：`ArrayList<?>`。

### 5.2 通配符的读与写

读写数据的特点（以集合 `ArrayList<?>` 为例说明）：
* 读取数据：允许的，读取的值的类型为 `Object` 类型。
* 写入数据：不允许的。
  * 特例：写入 `null` 值。

#### 5.2.1 写操作

将任意元素加入到其中不是类型安全的：
```java
Collection<?> c = new ArrayList<String>();

c.add(new Object());    // 编译时错误
```

因为我们不知道 `c` 的元素类型，我们不能向其中添加对象。`add()` 方法有类型参数 `E` 作为集合的元素类型，我们传给 `add()` 方法的任何参数都必须是一个未知类型的子类；因为我们不知道那是什么类型，所以我们无法传任何东西进去。

唯一可以插入的元素是 `null`，因为它是所有引用类型的默认值。

#### 5.2.2 读操作

另一方面，读取 `List<?>` 的对象 `list` 中的元素时，永远是安全的，因为不管 `list` 的真实类型是什么，它包含的都是 `Object`。

### 5.3 使用注意点

**注意点 1：** 编译错误 - 不能用在泛型方法声明上，返回值类型前面 `<>` 不能使用 `?`。

示例代码：
```java
public static <?> void test(ArrayList<?> list) {
    ...
}
```

**注意点 2：** 编译错误 - 不能用在泛型类的声明上。

示例代码：
```java
class GenericTypeClass<?> {
    ...
}
```

**注意点 3：** 编译错误 - 不能用在创建对象上，下例中右边属于创建集合对象。

示例代码：
```java
ArrayList<?> list2 = new ArrayList<?>();
```

### 5.4 有限制的通配符

#### 5.4.1 有限制条件的通配符的理解

`<?>` 允许所有泛型的引用调用。

通配符指定上限：`<? extends 类/接口>`。
* 使用时指定的类型必须是继承某个类或者实现某个接口，即 `<=`。

通配符指定下限：`<? super 类/接口>`。
* 使用时指定的类型必须是操作的类或接口，或者是操作的类的父类或接口的父接口，即 `>=`。

> 即：
> * `List<? extends A>`：可以将 `List<A>` 或 `List<B>` 赋值给 `List<? extends A>`，其中 `B` 类是 `A` 类的子类。
> * `List<? super A>`：可以将 `List<A>` 或 `List<B>` 赋值给 `List<? extends A>`，其中 `B` 类是 `A` 类的父类。

说明：
```java
?   // (无穷小, 无穷大)

<? extends Number>  // (无穷小, Number])
// 只允许泛型为 Number 及 Number 子类的引用调用

<? super Number>    // [Number, 无穷大)
// 只允许泛型为 Number 及 Number 父类的引用调用

<? extends Comparable>
// 只允许泛型为实现 Comparable 接口的实现类的引用调用
```

#### 5.4.2 有限制条件的通配符的读与写

技巧：开发中，遇到了带限制条件的通配符，在赋值时，如果没报错，那就正常使用；如果报错了，则该写法错误，需要修改。
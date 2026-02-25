# 第十四章：数据结构与集合源码

**目录：**

[TOC]

---

本章专题与脉络：
![第 3 阶段：Java 高级应用 - 第 14 章](./images/第3阶段：Java高级应用-第14章.png "第 3 阶段：Java 高级应用 - 第 14 章")

## 一、数据结构剖析

简单来说，数据结构就是一种程序设计优化的方法论，研究数据的**逻辑结构**和**物理结构**以及它们之间互相关系，并对这种结构定义相应的**运算**，**目的是加快程序的执行速度、减少内存占用的空间**。

具体研究对象如下。

### 1.1 研究对象一：数据间逻辑关系

数据的逻辑结构指反映数据元素之间的逻辑关系，而与数据的存储无关，是独立于计算机的。

* **集合结构**：数据结构中的元素之间除了“**同属一个集合**”的相互关系外，别无其他关系；集合元素之间没有逻辑关系。
* **线性结构**：数据结构中的元素存在**一对一**的相互关系，例如排队；结构中必须存在唯一的首元素和唯一的为元素。体现为：一维数组、链表、栈、队列。
* **树形结构**：数据结构中的元素存在**一对多**的相互关系，例如家谱、文件系统、组织架构。
* **图形结构**：数据结构中的元素存在**多对多**的相互关系，例如全国铁路图、地铁图。

![数据间四种逻辑关系示意图](./images/image-20220824011022664.png "数据间四种逻辑关系示意图")

### 1.2 研究对象二：数据的存储结构（或物理结构）

数据的物理结构 / 存储结构包括**数据元素的表示**和**关系的表示**。数据的存储结构是逻辑结构用计算机语言的实现，它依赖于计算机语言。

> 注意：
>
> 开发中，我们更习惯于以如下的方式理解存储结构：
> * 线性表（一对一关系）：一维数组、单向链表、双向链表、栈、队列。
> * 树（一对多关系）：各种树；比如二叉树、B+ 树。
> * 图（多对多关系）。
> * 哈希表：比如 HashMap、HashSet。

**结构 1：顺序结构**

顺序结构就是使用一组连续的存储单元依次存储逻辑上相邻的各个元素。

优点：只需要申请存放数据本身的内存空间即可，支持下标访问，也可以实现随机访问。

缺点：必须静态分配连续空间，内存空间的利用率比较低；插入或删除可能需要移动大量元素，效率比较低。

![顺序结构的代表 - 数组](./images/image-20220521100746910.png "顺序结构的代表 - 数组")

**结构 2：链式结构**

不使用连续的存储空间存放结构的元素，而是为每一个元素构造一个结点，结点中除了存放数据本身以外，还需要存放指向下一个结点的指针。

优点：不采用连续的存储空间导致内存空间利用率比较高，克服顺序存储结构中预知元素个数的缺点；插入或删除元素时，不需要移动大量的元素。

缺点：需要额外的空间来表达数据之间的逻辑关系，不支持下标访问和随机访问。

![链式结构的代表 - 链表](./images/image-20220521103734742.png "链式结构的代表 - 链表")

**结构 3：索引结构**

除建立存储结点信息外，还建立附加的**索引表**来记录每个元素结点的地址。索引表由若干索引项组成，索引项的一般形式是：`(关键字, 地址)`。

优点：用结点的索引号来确定结点存储地址，检索速度快。

缺点：增加了附加的索引表，会占用较多的存储空间；在增加和删除数据时要修改索引表，因而会花费较多的时间。

![索引结构示例](./images/image-20220521115200921.png "索引结构示例")

**结构 4：散列结构**

根据元素的关键字直接计算出该元素的存储地址，又称为 Hash 存储。

优点：检索、增加和删除结点的操作都很快。

缺点：不支持排序，一般比用线性表存储需要更多的空间，并且记录的关键字不能重复。

![散列结构示例](./images/image-20220521115734571.png "散列结构示例")

### 1.3 研究对象三：运算结构

施加在数据上的运算包括运算的定义和实现。运算的定义是针对逻辑结构的，指出运算的功能；运算的实现是针对存储结构的，指出运算的具体操作步骤。

* 分配资源、建立结构、释放资源；
* 插入和删除；
* 获取和遍历；
* 修改和排序。

### 1.4 小结

![小结 - 数据结构](./images/数据结构.png "小结 - 数据结构")

## 二、常见存储结构之：一维数组

### 2.1 数组的特点

在 Java 中，数组是用来存放同一种数据类型的集合，注意只能存放同一种数据类型。

```java
// 只声明了类型和长度
数据类型[] 数组名称 = new 数据类型[数组长度];

// 声明了类型，初始化赋值，大小由元素个数决定
数据类型[] 数组名称 = { 数组元素 1, 数组元素 2, ... }
```

例如整型数组与对象数组。

物理结构特点：
* 申请内存时，一次申请一大段连续的空间，一旦申请到了，内存就固定了。
* 不能动态扩展，插入快，删除和查找慢。
* 存储特点：所有数据存储在这个连续的空间中，数组中的每一个元素都是一个具体的数据（或对象），所有数据都紧密排布，不能有间隔。

具体如下如所示：
![数据结构 - 一维数组](./images/数据结构-一维数组.png "数据结构 - 一维数组")

### 2.2 自定义数组

见《第五章：数组》。

## 三、常见存储结构之：链表

### 3.1 链表的特点

逻辑结构：线性结构。

物理结构：不要求连续的存储空间。

存储特点：链表由一系列结点 node（链表中每一个元素称为结点）组成，结点可以在代码执行过程中动态创建；每个结点包括两个部分，一个是存储数据元素的**数据域**，另一个是存储下一个结点地址的**指针域**。

链表中的基本单位是结点（Node）。

![链表的存储特点示意图](./images/image-20220511113744772-1661445988505.png "链表的存储特点示意图")

常见的链表结构有如下的形式：
![常见的链表结构形式](./images/1563448858180-1661445988506.png "常见的链表结构形式")

链表小结：
![数据结构 - 链表](./images/数据结构-链表.png "数据结构 - 链表")

### 3.2 自定义链表

#### 3.2.1 自定义单向链表

![单向链表结构示意图](./images/image-20221028195106363.png "单向链表结构示意图")

语法格式：
```java
// 构造结点：
class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
    }
}

// 创建对象：
Node node1 = new Node("AA");
Node node2 = new Node("BB");
node1.next = node2;
```

#### 3.2.2 自定义双向链表

![双向链表结构示意图](./images/image-20220514165707977-1661448081075.png "双向链表结构示意图")

语法格式：
```java
// 构造结点：
class Node {
    Node prev;
    Object data;
    Node next;

    public Node(Object data) {
        this.data  = data;
    }

    public Node(Node prev, Object data, Node next) {
        this.prev = prev;
        this.data  = data;
        this.next = next;
    }
}

// 创建对象：
Node node1 = new Node(null, "AA", null);
Node node2 = new Node(node1, "BB", null);
Node node3 = new Node(node2, "CC", null);
node1.next = node2;
node2.next = node3;
```

## 四、常见存储结构之：栈

### 4.1 栈的特点

栈（Stack）又称为堆栈或堆叠，是限制仅在表的一端进行插入和删除运算的线性表。

栈按照 **先进后出（FILO - first in last out 或 LIFO - last in first out）** 的原则存储数据，先进入的数据被压入栈底，最后的数据在栈顶。每次删除（退栈）的总是删除当前栈中最后插入（进栈）的元素，而最先插入的是被放在栈的底部，要到最后才能删除。

栈属于抽象数据类型（ADT）。

核心类库中的栈结构有 `Stack` 和 `LinkedList`。
* `Stack` 就是顺序栈，它是 `Vector` 的子类。
* `LinkedList` 是链式栈。

体现栈结构的操作方法：
* `peek()` 方法：查看栈顶元素，不弹出。
* `pop()` 方法：弹出栈。
* `push(E e)` 方法：压入栈。

时间复杂度：
* 索引：$O(n)$。
* 搜索：$O(n)$。
* 插入：$O(1)$。
* 移除：$O(1)$。

栈结构与相关操作图示：
![栈结构与相关操作图示](./images/image-20220826010258638.png "栈结构与相关操作图示")

栈小结：
![数据结构 - 栈](./images/数据结构-栈.png "数据结构 - 栈")

### 4.2 栈的构建

可以使用数组或链表来构建栈。

数组实现栈的语法格式：
```java
// 数组实现栈
class Stack {
    Object[] values;
    int size;   // 记录存储的元素的个数

    public Stack(int length) {
        values = new Object[length];
    }

    // 入栈
    public void push(Object ele) {
        if (size >= values.length) {
            throw new RuntimeException("栈空间已满，入栈失败");
        }

        values[size] = ele;
        size++;
    }

    // 出栈
    public Object pop() {
        if (size <= 0) {
            throw new RuntimeException("栈空间已空，出栈失败");
        }

        Object obj = values[size - 1];
        values[size - 1] = null;
        size--;
        return obj;
    }
}
```

## 五、常见存储结构之：队列

### 5.1 队列的特点

队列（Queue）是只允许在一端进行插入、而在另一端进行删除的运算受限的线性表。

队列是逻辑结构，其物理结构可以是数组，也可以是链表。

队列的修改原则：队列的修改是依 **先进先出（FIFO - first in first out）** 的原则进行的。新来的成员总是加入队尾（即不允许“加塞”），每次离开的成员总是队列头上的（不允许中途离队）—— 即当前“最老的”成员离队。

队列属于抽象数据类型（ADT）。

队列结构与相关操作图示：
![队列结构与相关操作图示](./images/image-20220826010241172.png "队列结构与相关操作图示")

队列小结：
![数据结构 - 队列](./images/数据结构-队列.png "数据结构 - 队列")

### 5.2 队列的构建

可以使用数组或链表来构建队列。

数组实现队列的语法格式：
```java
// 数组实现队列
class Queue {
    Object[] values;
    int size;   // 记录存储的元素的个数

    public Queue(int length) {
        values = new Object[length];
    }

    // 添加元素
    public void add(Object ele) {
        if (size >= values.length) {
            throw new RuntimeException("队列已满，添加失败");
        }

        values[size] = ele;
        size++;
    }

    // 获取元素
    public Object get() {
        if (size <= 0) {
            throw new RuntimeException("队列已空，获取失败");
        }

        Object obj = values[0];

        // 数据前移
        for (int i = 0; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        // 最后一个元素置空
        values[size - 1] = null;

        size--;
        
        return obj;
    }
}
```

## 六、常见存储结构之：树与二叉树

### 6.1 树的理解

![自然界的树与计算机世界的树](./images/image-20220521111904272.png "自然界的树与计算机世界的树")

专有名词解释：
* **结点**：树中的数据元素都称之为结点。
* **根结点**：最上面的结点称之为根，一棵树只有一个根且由根发展而来；从另外一个角度来说，每个结点都可以认为是其子树的根。
* **父结点**：结点的上层结点；如上图中，结点 `K` 的父结点是 `E`、结点 `L` 的父结点是 `G`。
* **子结点**：结点的下层结点；如上图中，结点 `E` 的子结点是 `K` 结点、结点 `G` 的子结点是 `L` 结点。
* **兄弟结点**：具有相同父结点的结点称为兄弟结点；如上图中，结点 `F`、`G`、`H` 互为兄弟结点。
* **结点的度数**：每个结点所拥有的子树的个数称之为结点的度；如上图中，结点 `B` 的度为 3。
* **树叶**：度数为 0 的结点，也叫作终端结点；如上图中，结点 `D`、`K`、`F`、`L`、`H`、`I`、`J` 都是树叶。
* **非终端结点（或分支结点）**：树叶以外的结点，或度数不为 0 的结点；如上图中，根以及结点 `A`、`B`、`C`、`E`、`G` 都是。
* **树的深度（或高度）**：树中结点的最大层次数；如上图中，树的深度为 4。
* **结点的层数**：从根结点到树中某结点所经路径上的分支数称为该结点的层数，根结点的层数规定为 1，其余结点的层数等于其父亲结点的层数加 1。
* **同代**：在同一棵树中具有相同层数的结点。

### 6.2 二叉树的基本概念

二叉树（Binary Tree）是树形结构的一个重要类型。二叉树特点是每个结点最多只能有两棵子树，且有左右之分。许多实际问题抽象出来的数据结构往往是二叉树形式，二叉树的存储结构及其算法都较为简单，因此二叉树显得特别重要。

![二叉树结构示意图](./images/1563449427345-1661447692846.png "二叉树结构示意图")

语法格式 1：
```java
// 构造结点：
class TreeNode {
    TreeNode left;
    Object data;
    TreeNode right;

    public TreeNode(Object data) {
        this.data = data;
    }

    public TreeNode(TreeNode left, Object data, TreeNode right) {
        this.left = left;
        this.data = data;
        this.right  = right;
    }
}

// 创建对象：
TreeNode node1 = new TreeNode(null, "AA", null);
TreeNode leftNode = new TreeNode(null, "BB", null);
TreeNode rightNode = new TreeNode(null, "CC", null);
node1.left = leftNode;
node1.right = rightNode;
```

语法格式 2：
```java
// 构造结点：
class TreeNode {
    TreeNode parent;
    TreeNode left;
    Object data;
    TreeNode right;

    public TreeNode(Object data) {
        this.data = data;
    }

    public TreeNode(TreeNode left, Object data, TreeNode right) {
        this.left = left;
        this.data = data;
        this.right  = right;
    }

    public TreeNode(TreeNode parent, TreeNode left, Object data, TreeNode right) {
        this.parent = parent;
        this.left = left;
        this.data = data;
        this.right  = right;
    }
}

// 创建对象：
TreeNode node1 = new TreeNode(null, null, "AA", null);
TreeNode leftNode = new TreeNode(node1, null, "BB", null);
TreeNode rightNode = new TreeNode(node1, null, "CC", null);
node1.left = leftNode;
node1.right = rightNode;
```

### 6.3 二叉树的遍历

**前序遍历 - 中左右（根左右）：**

即先访问根结点，再前序遍历左子树，最后再前序遍历右子树。前序遍历运算访问二叉树各结点是以根、左、右的顺序进行访问的。

**中序遍历 - 左中右（左根右）：**

即先中序遍历左子树，然后再访问根结点，最后再中序遍历右子树。中序遍历运算访问二叉树各结点是以左、根、右的顺序进行访问的。

**后序遍历 - 左右中（左右根）：**

即先后序遍历左子树，然后再后序遍历右子树，最后访问根结点。后序遍历运算访问二叉树各结点是以左、右、根的顺序进行访问的。

遍历如下二叉树：
![二叉树的遍历 - 示例](./images/1574575739236-1661447692846.png "二叉树的遍历 - 示例")
* 前序遍历：`A`、`B`、`D`、`H`、`I`、`E`、`C`、`F`、`G`。
* 中序遍历：`H`、`D`、`I`、`B`、`E`、`A`、`F`、`C`、`G`。
* 后序遍历：`H`、`I`、`D`、`E`、`B`、`F`、`G`、`C`、`A`。

### 6.4 经典二叉树

![各类经典二叉树的关系](./images/image-20220521153016348.png "各类经典二叉树的关系")

**满二叉树：**

除最后一层无任何子结点外，每一层上的所有结点都有两个子结点的二叉树。第 $n$ 层的结点数是 $2^{n - 1}$，总的结点个数是 $2^n - 1$。

![满二叉树结构示意图](./images/1574575163883-1661447692846.png "满二叉树结构示意图")

**完全二叉树：**

叶结点只能出现在最底层的两层，且最底层叶结点均处于次底层叶结点的左侧。

![完全二叉树结构示意图](./images/1574575180247-1661447692846.png "完全二叉树结构示意图")

**二叉排序 / 查找 / 搜索树：**

即为 BST（Binary Search / Sort Tree）。满足如下性质：
1. 若它的左子树不为空，则左子树上所有结点的值均小于它的根结点的值。
2. 若它的右子树不为空，则右子树上所有结点的值均大于它的根结点的值。
3. 它的左、右子树也分别为二叉排序 / 查找 / 搜索树。

![二叉排序 / 查找 / 搜索树结构示意图](./images/image-20220521145208018.png "二叉排序 / 查找 / 搜索树结构示意图")

> 注意：
>
> 对二叉查找树进行中序遍历，得到有序集合，便于检索。

**平衡二叉树：**

即为 AVL（Self-balancing Binary Search Tree）。平衡二叉树首先是二叉排序树，此外具有以下性质：
1. 它是一棵空树或它的左右两个子树的高度差的绝对值不超过 1。
2. 并且左右两个子树也都是一棵平衡二叉树。
3. 不要求非叶结点都有两个子结点。

> 注意：
>
> 平衡二叉树的目的是为了减少二叉查找树的层次，提高查找速度。平衡二叉树的常用实现右红黑树、AVL、替罪羊树、Treap、伸展树等。

![平衡二叉树结构示意图](./images/image-20220521150151219.png "平衡二叉树结构示意图")

**红黑树：**

即 Red-Black Tree。红黑树的每个结点上都有存储位表示结点的颜色，可以是红（Red）或黑（Black）。

红黑树是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，它是在 1972 年由 Rudolf Bayer 发明的。红黑树是复杂的，但它的操作有着**良好的最坏情况运行时间**，并且**在实践中是高效的**：它可以在 $O(\log n)$ 时间内做查找、插入和删除操作，这里的 $n$ 是树中元素的数目。

红黑树的特性：
* 每个结点是红色或者黑色。
* 根结点是黑色。
* 每个叶子结点（`NIL`）是黑色（注意：这里叶子结点，是指为空 —— `NIL` 或 `NULL` —— 的叶子结点）。
* 每个红色结点的两个子结点都是黑色的（从每个叶子到根的所有路径上不能有两个连续的红色结点）。
* 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点（确保没有一条路径会比其他路径长出 2 倍）。

![红黑树结构示意图](./images/红黑树-1661447692846.jpeg "红黑树结构示意图")

当我们插入或删除结点时，可能会破坏已有的红黑树，使得它不满足以上 5 个要求；那么此时就需要进行处理，使得它继续满足以上的 5 个要求：
1. **recolor**：将某个结点变红或变黑。
2. **rotation**：将红黑树某些结点分支进行旋转（左旋或右旋）。

![插入或删除结点时对红黑树的处理情况](./images/image-20221208212053079.png "插入或删除结点时对红黑树的处理情况")

> 注意：
>
> 红黑树可以通过红色结点和黑色结点尽可能地保证二叉树的平衡，主要用其存储有序的数据，其时间复杂度是 $O(\log N)$，效率非常之高。

### 6.5 二叉树及其结点的表示

普通二叉树：
```java
public class BinaryTree<E> {
    private TreeNode root;  // 二叉树的根结点
    private int total;  // 结点总个数

    private class TreeNode {
        // 至少有以下几个部分
        TreeNode parent;
        TreeNode left;
        E data;
        TreeNode right;

        public TreeNode(TreeNode parent, TreeNode left, E data, TreeNode right) {
            this.parent = parent;
            this.left = left;
            this.data = data;
            this.right  = right;
        }
    }
}
```

TreeMap 红黑树：
```java
public class TreeMap<K, V> {
    private transient Entry<K, V> root;
    private transient int size = 0;

    static final class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
}
```

## 七、List 接口分析

启示与开发建议：
1. `Vector` 基本不使用了。
2. `ArrayList` 底层使用数组结构，查找和添加（尾部添加）操作效率高，时间复杂度为 $O(1)$；删除和插入操作效率低，时间复杂度为 $O(n)$。`LinkedList` 底层使用双向链表结构，删除和插入操作效率高，时间复杂度为 $O(1)$；查找和添加（尾部添加）操作效率低，时间复杂度为 $O(n)$（注意：有可能添加操作是 $O(1)$！）。
3. 在选择了 `ArrayList` 的前提下：`new ArrayList()` 底层创建长度为 10 的数组；`new ArrayList(int capacity)` 底层创建指定 `capacity` 长度的数组。开发中，如果可以大体确认数组的长度，则推荐使用 `new ArrayList(int capacity)` 这个构造器，避免了底层的扩容、复制数组的操作。

### 7.1 List 接口特点

List 集合所有的元素是以一种**线性方式**存储的。例如，存元素的顺序是 `11`、`22`、`33`，那么集合中元素的存储就是按照 `11`、`22`、`33` 的顺序完成的。

List 是一个元素**存取有序**的集合，即元素的存入顺序和取出顺序有保证。

List 是一个**带有索引**的集合，通过索引就可以精确地操作集合中的元素（与数组的索引是一个道理）。

集合中可以有**重复**的元素，通过元素的 `equals` 方法，来比较是否为重复的元素。

> 注意：
>
> List 集合关心元素是否有序，而不关心是否重复。

List 接口的主要实现类：
* `ArrayList`：动态数组。
* `Vector`：动态数组。
* `LinkedList`：双向链表。
* `Stack`：栈。

### 7.2 动态数组 `ArrayList` 与 `Vector`

Java 的 List 接口的实现类中有两个动态数组的实现：`ArrayList` 和 `Vector`。

#### 7.2.1 `ArrayList` 与 `Vector` 的区别

`ArrayList` 的特点：
* 实现了 List 接口，存储有序的、可以重复的数据。
* 底层使用 `Object[]` 数组存储。
* 线程不安全的。

`Vector` 的特点：
* 实现了 List 接口，存储有序的、可以重复的数据。
* 底层使用 `Object[]` 数组存储。
* 线程安全的。

`ArrayList` 和 `Vector` 的底层物理结构都是数组，我们称为动态数组。

`ArrayList` 是新版的动态数组，线程不安全，效率高；`Vector` 是旧版的动态数组，线程安全，效率低。

动态数组的扩容机制不同：`ArrayList` 默认扩容为原来的 1.5 倍，`Vector` 默认扩容增加为原来的 2 倍。

数组的初始化容量不同：如果在构建 `ArrayList` 与 `Vector` 的集合对象时没有显式指定初始化容量，那么 `Vector` 的内部数组的初始容量默认为 10；而 `ArrayList` 在 JDK 6.0 及之前的版本也是 10，JDK 8.0 之后的版本 `ArrayList` 初始化长度为 0 的空数组，之后在添加第一个元素时，再创建长度为 10 的数组。原因：
* 使用的时候再创建数组，避免浪费。因为很多方法的返回值是 `ArrayList` 类型，需要返回一个 `ArrayList` 的对象。例如，后期从数据库查询对象的方法，返回值很多就是 `ArrayList`；有可能要查询的数据不存在，要么返回 `null`，要么返回一个没有元素的 `ArrayList` 对象。

#### 7.2.2 `ArrayList` 部分源码分析

**`ArrayList` 之 JDK 7 源码剖析：**

![ArrayList 之 JDK 7 源码剖析](./images/01-ArrayList之JDK7源码剖析.jpg "ArrayList 之 JDK 7 源码剖析")

示例代码（以 jdk1.7.0_07 为例）：
```java
// 如下代码的执行，底层会初始化数组，数组的长度为 10：Object[] elementData = new Object[10];
ArrayList<String> list = new ArrayList<>();

list.add("AA"); // elementData[0] = "AA";
list.add("BB"); // elementData[1] = "BB";
...
// 当要添加第 11 个元素的时候，底层的 elementData 数组已满，则需要扩容；默认扩容为原来长度的 1.5 倍，并将原有数组中的元素复制到新的数组中。
```

**`ArrayList` 之 JDK 8 源码剖析：**

![ArrayList 之 JDK 8 源码剖析](./images/02-ArrayList之JDK8源码剖析.jpg "ArrayList 之 JDK 8 源码剖析")

示例代码（以 jdk1.8.0_271 为例）：
```java
// 如下代码的执行，底层会初始化数组，即：Object[] elementData = new Object[] {};
ArrayList<String> list = new ArrayList<>();

list.add("AA"); // 首次添加元素时，会初始化数组 elementData = new Object[10]; elementData[0] = "AA";
list.add("BB"); // elementData[1] = "BB";
...
// 当要添加第 11 个元素的时候，底层的 elementData 数组已满，则需要扩容；默认扩容为原来长度的 1.5 倍，并将原有数组中的元素复制到新的数组中。
```

> 小结：
>
> jdk1.7.0_07 版本中，`ArrayList` 类似于饿汉式；jdk1.8.0_271 版本中，`ArrayList` 类似于懒汉式。

#### 7.2.3 `ArrayList` 相关方法图示

`ArrayList` 采用数组作为底层实现：
![ArrayList 采用数组作为底层实现](./images/image-20221029112037297.png "ArrayList 采用数组作为底层实现")

`ArrayList` 自动扩容过程：
![ArrayList 自动扩容过程](./images/image-20221029112107691.png "ArrayList 自动扩容过程")

`ArrayList` 的 `add(E e)` 方法：
![ArrayList 的 add(E e) 方法](./images/image-20221029112129161.png "ArrayList 的 add(E e) 方法")

`ArrayList` 的 `add(int index, E e)` 方法：
![ArrayList 的 add(int index, E e) 方法](./images/image-20221029112157007.png "ArrayList 的 add(int index, E e) 方法")

#### 7.2.4 `Vector` 部分源码分析

`Vector` 之 JDK 8 源码剖析：

![Vector 之 JDK 8 源码剖析](./images/03-Vector之JDK8源码剖析.jpg "Vector 之 JDK 8 源码剖析")

示例代码：
```java
Vector v = new Vector();    // 底层初始化数组，长度为 10：Object elementData = new Object[10];
v.add("AA");    // elementData[0] = "AA";
v.add("BB");    // elementData[1] = "BB";
...
// 当添加第 11 个元素时，需要扩容；默认扩容为原来的 2 倍。
```

### 7.3 链表 `LinkedList`

Java 中有双链表的实现 —— `LinkedList`，它是 List 接口的实现类。

`LinkedList` 是一个**双向链表**，如图所示：
![LinkedList 的底层实现 - 双向链表](./images/image-20220514165707977-1661448081075.png "LinkedList 的底层实现 - 双向链表")

`LinkedList` 的特点：
* 实现了 List 接口，存储有序的、可以重复的数据。
* 底层使用双向链表存储。
* 线程不安全的。

#### 7.3.1 链表与动态数组的区别

动态数组底层的物理结构是数组，因此根据索引访问的效率非常高。但是非末尾位置的插入和删除效率不高，因为涉及到移动元素；另外添加操作时涉及到扩容问题，就会增加时空消耗。

链表底层的物理结构是链表，因此根据索引访问的效率不高，即查找元素慢。但是插入和删除不需要移动元素，只需要修改前后元素的指向关系即可，所以插入、删除元素快；而且链表的添加不会涉及到扩容问题。

#### 7.3.2 `LinkedList` 源码分析

`LinkedList` 之 JDK 8 源码剖析：

![LinkedList 之 JDK 8 源码剖析](./images/04-LinkedList之JDK8源码剖析.jpg "LinkedList 之 JDK 8 源码剖析")

示例代码：
```java
LinkedList<String> list = new LinkedList<>();
list.add("AA"); // 将 "AA" 封装到一个 Node 对象 1 中，list 对象的属性 first、last 都指向此 Node 对象
list.add("BB"); // 将 "BB" 封装到一个 Node 对象 2 中，对象 1 和对象 2 构成一个双向链表，同时 last 指向 Node 对象 2
...
// 因为 LinkedList 使用的是双向链表，不需要考虑扩容问题。
```

`LinkedList` 内部声明：
```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

> 注意：
>
> LinkedList 是否存在扩容问题？不存在！

#### 7.3.3 `LinkedList` 相关方法图示

只有 1 个元素的 `LinkedList`：
![只有 1 个元素的 LinkedList](./images/image-20221029134437888.png "只有 1 个元素的 LinkedList")

包含 4 个元素的 `LinkedList`：
![包含 4 个元素的 LinkedList](./images/image-20221029134534198.png "包含 4 个元素的 LinkedList")

`add(E e)` 方法：
![add(E e) 方法](./images/image-20221029135013377.png "add(E e) 方法")

`add(int index, E e)` 方法：
![add(int index, E e) 方法](./images/image-20221029135045120.png "add(int index, E e) 方法")

`remove(Object obj)` 方法：
![remove(Object obj) 方法](./images/image-20221029134721089.png "remove(Object obj) 方法")

`remove(int index)` 方法：
![remove(int index) 方法](./images/image-20221029134807613.png "remove(int index) 方法")

## 八、Map 接口分析

### 8.1 哈希表的物理结构

`HashMap` 和 `Hashtable` 底层都是哈希表（也称散列表），其中维护了一个长度为 **2 的幂次方** 的 `Entry` 类型的数组 `table`，数组的每一个索引位置被称为一个桶（bucket），向其中添加的映射关系 `(key, value)` 最终都被封装为一个 `Map.Entry` 类型的对象，放到某个 `table[index]` 桶中。

使用数组的目的是查询和添加的效率高，可以根据索引直接定位到某个 `table[index]`。

![Java 8 HashMap 结构示意图](./images/image-20221029144811305.png "Java 8 HashMap 结构示意图")

### 8.2 `HashMap` 源码剖析
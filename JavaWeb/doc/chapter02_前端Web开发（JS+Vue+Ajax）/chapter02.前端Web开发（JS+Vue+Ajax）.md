# 第二章：前端 Web 开发（JS + Vue + Ajax）

**目录：**

[TOC]

---

**介绍：**

在前面的课程中，我们已经学习了 HTML、CSS 的基础内容。我们知道 HTML 负责网页的结构，而 CSS 负责的是网页的表现；而要想让网页具备一定的交互效果、具有一定的动作行为，还得通过 JavaScript 来实现。

本章，我们就来讲解 JavaScript，这门语言会让我们的页面能够和用户进行交互。

**那么，什么是 JavaScript 呢？**

**JavaScript**（简称：**JS**）是一门跨平台、面向对象的脚本语言，是用来控制网页行为的，可以实现人机交互效果。

JavaScript 和 Java 是完全不同的语言，不论是概念还是设计；但是基础语法类似。

组成：
* ECMAScript：规定了 JS 基础语法核心知识，包括变量、数据类型、流程控制、函数、对象等。
* BOM：浏览器对象模型，用于操作浏览器本身；如：页面弹窗、地址栏操作、关闭窗口等。
* DOM：文档对象模型，用于操作 HTML 文档；如：改变标签内的内容、改变标签内字体样式等。

> 备注：ECMA 国际（前身为欧洲计算机制造商协会）制定了标准化的脚本程序设计语言 ECMAScript，这种语言得到广泛应用。而 JavaScript 是遵守 ECMAScript 的标准的（ES2024 是最新版本）。

## 一、JS 核心语法

### 1.1 JS 引入方式

同样，JS 代码也是书写在 HTML 中的。那么 HTML 中如何引入 JS 代码呢？

HTML 中引入 JS 代码主要通过下面的 2 种引入方式。

**第一种方式 - 内部脚本：**

内部脚本方式将 JS 代码定义在 HTML 页面中。
* JavaScript 代码必须位于 `<script></script>` 标签之间。
* 在 HTML 文档中，**可以在任意地方，放置任意数量的 `<script></script>`**。
* **一般会把脚本置于 <body> 元素的底部，可改善显示速度。**

示例代码：
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS 引入方式</title>
</head>
<body>

    <script>
        alert('Hello JS')
    </script>
</body>
</html>
```

**第二种方式 - 外部脚本：**

外部脚本方式将 JS 代码定义在外部 JS 文件中，然后引入到 HTML 页面中。
* 外部 JS 文件中，只包含 JS 代码，不包含 `<script>` 标签。
* 引入外部 JS 的 `<script>` 标签，必须是双标签。

示例代码：
* 在 js 目录下，定义一个 JS 文件 demo.js，在文件中编写 JS 代码。如下所示：
    ```javascript
    alert('Hello JS')
    ```
* 在 HTML 文件中，通过 <script></script> 引入 JS 文件 demo.js，如下：
    ```html
    <script src="js/demo.js"></script>
    ```

> 注意：
> 1. demo.js 中只有 JS 代码，没有 `<script>` 标签。
> 2. 通过 `<script></script>` 标签引入外部 JS 文件时，标签不能自闭合；如：`<script src="js/demo.js" />`。

JS 书写规范：
* 结束符：每行 JS 代码，结尾以分号结尾，而结尾的分号可有可无。（建议在一个项目中保持一致，要么全部都加，要么全部都不加。）
* 注释：单行注释、多行注释的写法，与 Java 中一致。

示例代码：

```html
<!-- 01.JS-引入方式.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-引入方式</title>
</head>
<body>

  <!-- <script>
    // 1. 内部脚本
    alert('Hello JS');
  </script> -->

  <!-- 2. 外部脚本 -->
  <script src="js/demo.js"></script>
</body>
</html>
```

```javascript
/* demo.js */

alert('Hello JavaScript');
```

### 1.2 JS 基础语法

#### 1.2.1 输出语句

在 JS 中有 3 种输出语句，分别是：
| api | 描述 |
| :--: | :--: |
| `window.alert(...)` | 警告框 |
| `document.write(...)` | 在 HTML 输出内容 |
| `console.log(...)` | 写入浏览器控制台 |

示例代码：
```html
<!-- 02.JS-基础语法.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-基础语法</title>
</head>
<body>
  

  <script>
    // 1. 声明变量
    // let a = 10;
    // a = "Hello";
    // a = true;

    // alert(a); // 弹出框

    // 2. 声明常量
    const PI = 3.14;
    // PI = 5.0;

    console.log(PI);  // 输出到控制台
    // document.write(PI); // 输出到 body 区域（不常用）
  </script>
</body>
</html>
```

#### 1.2.2 变量与常量

##### 1.2.2.1 变量

接下来，我们再来讲解 JS 中的变量。

在 JS 中，变量的声明和 Java 中还是不同的。
* JS 中主要通过 `let` 关键字来声明变量的。
* JS 是一门弱类型语言，变量是可以存放不同类型的值的。
* 变量名需要遵循如下规则：
  * 组成字符可以是任何字母、数字、下划线（`_`）或美元符号（`$`），且数字不能开头。
  * 变量名严格区分大小写；如：`name` 和 `Name` 是不同的变量。
  * 不能使用关键字作为变量名；如：`let`、`if`、`for` 等。

变量的声明示例如下所示：
```html
<script>
    // 变量
    let a = 20;
    a = "Hello";
    alert(a);
</script>
```

在上述的示例中，我们会看到，变量 `a` 既可以存数字，又可以存字符串，因为 JS 是弱类型语言。

> 注意：
>
> 在早期的 JS 中，声明变量还可以使用 var 关键字来声明。例如：
> ```html
> <body>
> 
>     <scrip>
>         // var 声明变量
>         var name = "A";
>         name = "B";
>         alert(name);
> 
>         var name = "C";
>         alert(name);
>     </script>
> </body>
> ```
>
> 打开浏览器运行之后，我们会发现，可以正常执行：第一次弹出 `B`，第二次弹出 `C`。我们看到 `name` 变量重复声明了，但是如果使用 `var` 关键字，是没有问题的，可以重复声明。
>
> `var` 声明的变量还有一些其他不严谨的地方，这里就不再一一列举了。所以这个声明变量的关键字并不严谨，**因此不推荐使用**。

#### 1.2.2.2 常量

在 JS 中，如果声明一个常量，需要使用 `const` 关键字。一旦声明，常量的值就不能改变（不可以重新赋值）。

如下所示：
```html
<body>

    <script>
        // 常量
        const PI = 3.14;
        PI = 3.15;
        alert(PI);
    </script>
</body>
```

浏览器打开之后，会报如下错误：
![常量的值无法改变（不可以重新赋值）](./images/02_常量的值无法改变（不可以重新赋值）.png "常量的值无法改变（不可以重新赋值）")

该错误就表示，常量不可以被重新分配值。

##### 1.2.2.3 举例

示例代码：
```html
<!-- 02.JS-基础语法.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-基础语法</title>
</head>
<body>
  

  <script>
    // 1. 声明变量
    // let a = 10;
    // a = "Hello";
    // a = true;

    // alert(a); // 弹出框

    // 2. 声明常量
    const PI = 3.14;
    // PI = 5.0;

    console.log(PI);  // 输出到控制台
    // document.write(PI); // 输出到 body 区域（不常用）
  </script>
</body>
</html>
```

#### 1.2.3 数据类型

虽然 JS 是弱数据类型的语言，但是 JS 中也存在数据类型。

JS 中的数据类型分为：原始数据类型和引用数据类型。

原始数据类型主要包含以下几种类型：
| 数据类型 | 描述 |
| :--: | :--: |
| `number` | 数字（整数、小数、NaN（Not a Number）） |
| `string` | 字符串，单引号（`'...'`）、双引号（`"..."`）、反引号（\``...`\`）皆可，正常使用推荐**单引号** |
| `boolean` | 布尔，`true`、`false` |
| `null` | 对象为空；JavaScript 是大小写敏感的，因此 `null`、`Null`、`NULL` 是完全不同的 |
| `undefined` | 当声明的变量未初始化时，该变量的默认值是 `undefined` |

使用 `typeof` 关键字可以返回变量的数据类型。

对于字符串类型的数据，除了可以使用双引号（`"..."`）、单引号（`'...'`）以外，还可以使用反引号（\``...`\`）。而使用反引号引起来的字符串，也称为**模板字符串**。
* 模板字符串的使用场景：拼接字符串和变量。
* 模板字符串的语法：
  * \``...`\`：反引号（英文输入模式下，键盘 tab 键上方波浪线 ~ 的键）。
  * 内容拼接时，使用 `${ }` 来引用变量。

示例代码：
```html
<!-- 03.JS-数据类型.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-数据类型</title>
</head>
<body>
  

  <script>
    //1. 数据类型
    // alert(typeof 10); // number
    // alert(typeof 1.5);  // number
    
    // alert(typeof true); // boolean
    // alert(typeof false);  // boolean

    // alert(typeof "Hello");  // string
    // alert(typeof 'JS'); // string
    // alert(typeof `JavaScript`); // string

    // alert(typeof null); // null ? -> object

    // let a ;
    // alert(typeof a);  // undefined

    // 2. 模板字符串 - 简化字符串拼接
    let name = 'Tom';
    let age = 18;

    console.log('我是 ' + name + '，我今年 ' + age + ' 岁');
    console.log(`我是 ${name}，我今年 ${age} 岁`);
    
    
  </script>
</body>
</html>
```

#### 1.2.4 函数

**函数（function）**是被设计用来执行特定任务的代码块，方便程序的封装复用。我们学习函数，主要就是学习 JS 中函数的定义及调用的语法。

##### 1.2.4.1 方式一

语法格式：
```javascript
function 函数名(参数1, 参数2, ...) {
  // 要执行的代码
}
```

因为 JavaScript 是弱数据类型的语言，所以有如下几点需要注意：
* 形参不需要声明类型，并且 JS 中不管什么类型都是 `let` 去声明，加上也没有意义。
* 返回值也不需要声明类型，直接 `return` 即可。

示例代码：
```javascript
function add(a, b) {
  return a + b;
}
```

如果要调用上述的函数 `add`，可以使用 `函数名称(实际参数列表)`：
```javascript
let result = add(10, 20);
alert(result);
```

我们在调用 `add` 函数时，再添加 2 个参数，修改代码如下：
```javascript
var result = add(10, 20, 30, 40);
alert(result);
```

浏览器打开，发现没有错误，并且依然弹出 `30`，这是为什么呢？

因为在 JavaScript 中，函数的调用只需要名称正确即可，可以不管参数列表。如上述案例，`10` 传递给了变量 `a`，`20` 传递给了变量 `b`，而 `30` 和 `40` 没有变量接受，但是不影响函数的正常调用。

> 注意：
>
> 由于 JS 是弱类型语言，形参、返回值都不需要指定类型。在调用函数时，实参个数与形参个数可以不一致，但是**建议一致**。

##### 1.2.4.2 方式二

刚才我们定义函数，是为函数指定了一个名字。那我们也可以不为函数指定名字，这一类的函数我们称之为**匿名函数**。那么，接下来的方式二就来介绍一下匿名函数的定义和调用。

匿名函数定义可以通过两种方式：**函数表达式** 和 **箭头函数**。

* 示例一（函数表达式）：
```javascript
var add = function (a, b) {
  return a + b;
}
```

* 示例二（**箭头函数**）：
```javascript
var add = (a, b) => {
  return a + b;
}
```

上述匿名函数声明好了之后，是将这个函数赋值给了 `add` 变量，那么我们就可以直接通过 `add` 函数直接调用。调用代码如下：
```javascript
let result = add(10, 20);
alert(result);
```

而箭头函数这种形式，在现在的前端开发中用得会更多一些。

示例代码：
```html
<!-- 04.JS-函数.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-函数</title>
</head>
<body>
  

  <script>
    // 1. 函数的定义及调用 - 具名函数
    // function add(a, b) {
    //   return a + b;
    // }

    // let result = add(10, 20, 50);
    // console.log(result);


    // 2. 函数定义及调用 - 匿名函数
    // 2.1 函数表达式
    // let add = function(a, b) {
    //   return a + b;
    // }

    // let result = add(100, 200);
    // console.log(result);

    // 2.2 箭头函数
    let add = (a, b) => {
      return a + b;
    }

    let result = add(1000, 2000);
    console.log(result);


  </script>
</body>
</html>
```

##### 1.2.4.3 自定义对象

在 JavaScript 中自定义对象特别简单，其语法格式如下：
```javascript
let 对象名 = {
  属性名1: 属性值1,
  属性名2: 属性值2,
  属性名3: 属性值3,
  方法名称: function(形参列表) {
    ...
  }
};
```

其中，上述函数定义的语法可以简化成如下格式：
```javascript
let 对象名 = {
  属性名1: 属性值1,
  属性名2: 属性值2,
  属性名3: 属性值3,
  方法名称() {
    ...
  }
}
```

我们可以通过如下语法调用属性：
```javascript
对象名.属性名
```

通过如下语法调用函数：
```javascript
对象名.方法名()
```

示例代码：
```html
<!-- 05.JS-自定义对象.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-函数</title>
</head>
<body>
  

  <script>
    // 1. 自定义对象

    // let user = {
    //   name: 'Tom',
    //   age: 18,
    //   gender: '男',
    //   sing: function() {
    //     alert(this.name + ' 悠悠地唱着最炫的民族风~');
    //   }
    // };

    let user = {
      name: 'Tom',
      age: 18,
      gender: '男',
      sing() {
        alert(this.name + ' 悠悠地唱着最炫的民族风~');
      }
    };

    // let user = {
    //   name: 'Tom',
    //   age: 18,
    //   gender: '男',
    //   sing: () => { // 注意：在箭头函数中，this 并不指向当前对象 - 指向的是当前对象的父级（不推荐）
    //     alert(this + ' 悠悠地唱着最炫的民族风~');
    //   }
    // };

    // 2. 调用对象属性 / 方法
    alert(user.name);
    user.sing();

  </script>
</body>
</html>
```

> 注意：
>
> 在定义对象中的方法时，尽量不要使用箭头函数；因为在箭头函数中，this 并不指向当前对象，指向的是当前对象的父级。

##### 1.2.4.4 JSON

JSON：**J**ava**S**cript **O**bject **N**otation，JavaScript 对象标记法。JSON 是通过 JS 对象标记法书写的**文本**，其格式如下：
```javascript
{
  "key":value,
  "key":value,
  "key":value
}
```

其中，`key` 必须使用引号并且是双引号标记，`value` 可以是任意数据类型。

由于语法简单，层级结构鲜明，现多用于作为**数据载体**，在网络中进行数据传输。

![JSON 作为数据载体在网络中进行数据传输](./images/02_JSON作为数据载体在网络中进行数据传输.png "JSON 作为数据载体在网络中进行数据传输")

示例代码：
```html
<!-- 05.JS-自定义对象.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-函数</title>
</head>
<body>
  

  <script>
    // 1. 自定义对象

    // let user = {
    //   name: 'Tom',
    //   age: 18,
    //   gender: '男',
    //   sing: function() {
    //     alert(this.name + ' 悠悠地唱着最炫的民族风~');
    //   }
    // };

    let user = {
      name: 'Tom',
      age: 18,
      gender: '男',
      sing() {
        alert(this.name + ' 悠悠地唱着最炫的民族风~');
      }
    };

    // let user = {
    //   name: 'Tom',
    //   age: 18,
    //   gender: '男',
    //   sing: () => { // 注意：在箭头函数中，this 并不指向当前对象 - 指向的是当前对象的父级（不推荐）
    //     alert(this + ' 悠悠地唱着最炫的民族风~');
    //   }
    // };

    // 2. 调用对象属性 / 方法
    // alert(user.name);
    // user.sing();

    // 3. JSON - JS 对象标记法
    let person = {
      name: 'itcast',
      age: 18,
      gender: '男'
    };
    alert(JSON.stringify(person));  // JS 对象 -> JSON 字符串

    let personJson = '{"name": "heima", "age": 18}';
    alert(JSON.parse(personJson).name);

  </script>
</body>
</html>
```

API 说明：
* `JSON.stringify(...)`：作用就是将 JS 对象转换为 JSON 格式的字符串。
* `JSON.parse(...)`：作用就是将 JSON 格式的字符串，转换为 JS 对象。

#### 1.2.5 流程控制

在 JS 中，当然也存在对应的流程控制语句。常见的流程控制语句如下：
* `if ... else if ... else ...`。
* `switch`。
* `for`。
* `while`。
* `do ... while`。

而 JS 中的流程控制语句与 Java 中的流程控制语句的作用、执行机制都是一样的，此处不再赘述。

#### 1.2.6 JS DOM

##### 1.2.6.1 DOM 介绍

DOM（Document Object Model）：文档对象模型，也就是 JavaScript 将 HTML 文档的各个组成部分封装为对象。

DOM 其实我们并不陌生，之前在学习 XML 时就接触过；只不过 XML 文档中的标签需要我们写代码解析，而 HTML 文档是浏览器解析。

封装的对象分为：
* Document：整个文档对象，即整个 HTML 页面被封装为 Document 对象。
* Element：元素对象，即每一个标签都会被封装成一个元素对象。
* Attribute：属性对象，即每一个标签的属性都会被封装成一个属性对象。
* Text：文本对象，即每一对标签之间的文本内容都会被封装成一个文本对象。
* Comment：注释对象，即 HTML 文档中的注释会被封装成注释对象。

如下图，左边是 HTML 文档内容，右边是 DOM 树：
![HTML 文档内容（左图）与 DOM 树（右图）](./images/02_HTML文档内容（左图）与DOM树（右图）.png "HTML 文档内容（左图）与 DOM 树（右图）")

那么我们学习 DOM 技术有什么用呢？主要作用如下：
* 改变  HTML 元素的内容；
* 改变 HTML 元素的样式（CSS）；
* 对 HTML DOM 事件作出反应；
* 添加和删除 HTML 元素。

##### 1.2.6.2 DOM 操作

DOM 的核心思想：将网页的内容当做对象来处理，标签的所有属性在该对象上都可以找到；并且修改这个对象的属性，就会自动映射到标签身上。

document 对象：
* 网页中所有内容都封装在 document 对象中。
* 它提供的属性和方法都是用来访问和操作网页内容的；如：`document.write(...)`。

DOM 操作步骤：
1. 获取要操作的 DOM 元素对象。
2. 操作 DOM 对象的属性或方法（查阅文档）。

> 注意：
>
> 在查阅关于操作 DOM 对象的属性或方法的文档时，推荐 W3School 文档：[W3School 文档](https://www.w3school.com.cn/)。

我们可以通过如下两种方式来获取 DOM 元素：
* 根据 CSS 选择器来获取 DOM 元素，获取到匹配到的第一个元素：`document.querySelectoe('CSS 选择器');`。
* 根据 CSS 选择器来获取 DOM 元素，获取匹配到的所有元素：`document.querySelectorAll('CSS 选择器');`。

> 注意：
>
> 获取到的所有元素，会封装到一个 `NodeList` 结点集合中，是一个伪数组（有长度、有索引的数组，但没有 `push`、`pop` 等数组方法）。

示例代码：
```html
<!-- 07.JS-DOM.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-DOM</title>
</head>
<body>

  <h1 id="title1">11111</h1>
  <h1>22222</h1>
  <h1>33333</h1>

  <script>
    // 1. 修改第一个 h1 标签中的文本内容
    // 1.1 获取 DOM 对象
    // let h1 = document.querySelector('#title1');
    // let h1 = document.querySelector('h1');  // 获取第一个 h1 标签

    let hs = document.querySelectorAll('h1');

    // 1.2 调用 DOM 对象中的属性或方法
    // h1.innerHTML = '修改后的文本内容';
    hs[0].innerHTML = '修改后的文本内容';
  </script>
</body>
</html>
```

> 注意：
>
> 在早期的 JS 中，我们也可以通过如下方法获取 DOM 元素：
> * `document.getElementById(...)`：根据 `id` 属性值获取，返回单个 Element 对象。
> * `document.getElementsByTagName(...)`：根据标签名称获取，返回 Element 对象数组。
> * `document.getElementsByName()`：根据 `name` 属性值获取，返回 Element 对象数组。
> * `document.getElementsByClassName()`：根据 `class` 属性值获取，返回 Element 对象数组。

## 二、JS 事件监听

### 2.1 事件介绍

什么是事件呢？HTML 事件是发生在 HTML 元素上的“事情”。例如：
* 按钮被点击。
* 鼠标移到元素上。
* 输入框失去焦点。
* 按下键盘按键。
* ……

而我们可以给这些事件绑定函数，当事件触发时，可以自动地完成对应的功能，这就是事件监听。

JS 事件是 JS 非常重要的一部分，接下来我们进行事件的学习。那么我们对于 JavaScript 事件需要学习哪些内容呢？我们得知道有哪些常用事件，然后我们得学会如何给事件绑定函数。

所以主要围绕 2 点来学习：①. 事件监听、②. 常用事件。

### 2.2 事件监听语法

JS 事件监听的语法格式：
```javascript
事件源.addEventListener('事件类型', 事件触发执行的函数);
```

在上述的语法中包含三个要素：
* 事件源：哪个 DOM 元素触发了事件，要获取 DOM 元素。
* 事件类型：用什么方式触发；比如：鼠标单击 `click`，鼠标经过 `mouseover`。
* 事件触发执行的函数：要做什么事。

JavaScript 对于事件的绑定还提供了另外 2 种方式（早期版本）：

1). 通过 HTML 标签中的事件属性进行绑定

通过 HTML 标签中的 `on事件` 属性进行绑定。

例如一个按钮，我们对于按钮可以绑定单击事件，可以借助标签的 `onclick` 属性，属性值指向一个函数。

示例代码：
```html
<input type="button" id="btn1" value="点我一下试试1" onclick="on()">

<script>
  function on() {
    alert('按钮 1 被点击了 ……');
  };
</script>
```

2). 通过 DOM 中 Element 元素的事件属性进行绑定

依据我们学习过的 DOM 的知识点，我们知道 HTML 中的标签被加载成 Element 对象，所以我们也可以通过 Element 对象的属性来操作标签的属性。

语法格式如下：
```javascript
事件源.on事件 = function() {
  ...
}
```

例如一个按钮，我们对于按钮可以绑定单击事件，可以通过 DOM 元素的属性，为其做事件绑定。

示例代码：
```html
<body>
  <input type="button" id="btn2" value="点我一下试试2">
  <script>
    document.querySelector('#btn2').onclick = function() {
      alert("按钮 2 被点击了 ……");
    };
  </script>
</body>
```

整体代码如下：
```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS-事件-事件绑定</title>
</head>

<body>
  <input type="button" id="btn1" value="事件绑定1">
  <input type="button" id="btn2" value="事件绑定2">
  <script>
    document.querySelector("#btn1").addEventListener('click', () => {
      alert("按钮 1 被点击了 ……");
    })

    document.querySelector('#btn2').onclick = function() {
      alert("按钮 2 被点击了 ……");
    }
  </script>
</body>
</html>
```

> 注意：
>
> `addEventListener` 与 `on` 事件 区别：
> * `on` 方式 会被覆盖，`addEventListener` 方式 可以绑定多次，拥有更多特性，推荐使用 `addEventListener`。

示例代码：
```html
<!-- 08.JS-事件监听.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS事件</title>
</head>
<body>
  
  <input type="button" id="btn1" value="点我一下试试1">
  <input type="button" id="btn2" value="点我一下试试2">

  <script>
    // 事件监听 - addEventListener（可以多次绑定同一事件）
    document.querySelector('#btn1').addEventListener('click', function() {
      console.log('试试就试试~~');
    });
    document.querySelector('#btn1').addEventListener('click', function() {
      console.log('试试就试试22~~');
    });

    // 事件绑定 - 早期写法 - onclick（如果多次绑定同一事件，覆盖）
    document.querySelector('#btn2').onclick = () => {
      console.log('试试就试试~~');
    };
    document.querySelector('#btn2').onclick = () => {
      console.log('试试就试试22~~');
    };
  </script>
</body>
</html>
```

### 2.3 隔行换色案例

**需求：** 实现鼠标移入数据行时，背景色改为 `#f2e2e2`；鼠标移出时，再将背景色改为白色。

**效果：**
![隔行换色案例效果](./images/02_隔行换色案例效果.gif "隔行换色案例效果")

示例代码：
```html
<!-- 09.JS-案例-员工列表.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button">查询</button>
            <button type="button">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr>
                    <td>令狐冲</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲"></td>
                    <td>讲师</td>
                    <td>2021-06-15</td>
                    <td>2024-09-16 15:30</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>任盈盈</td>
                    <td>女</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈"></td>
                    <td>咨询师</td>
                    <td>2021-07-20</td>
                    <td>2024-09-17 09:00</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>向问天</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="向问天"></td>
                    <td>班主任</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>任我行</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任我行"></td>
                    <td>教研主管</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>田伯光</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="田伯光"></td>
                    <td>班主任</td>
                    <td>2021-06-15</td>
                    <td>2024-09-16 15:30</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>不戒</td>
                    <td>女</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="不戒"></td>
                    <td>班主任</td>
                    <td>2021-07-20</td>
                    <td>2024-09-17 09:00</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>左冷禅</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="左冷禅"></td>
                    <td>班主任</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>定逸</td>
                    <td>女</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="定逸"></td>
                    <td>班主任</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>东方兄弟</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="东方兄弟"></td>
                    <td>讲师</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>金庸</td>
                    <td>男</td>
                    <td><img class="avatar" src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="金庸"></td>
                    <td>咨询师</td>
                    <td>2021-05-01</td>
                    <td>2024-09-15 17:45</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>

    <script>
        // 需求：通过 JS 为上述的表格中数据行添加事件监听
        // 实现：鼠标进入后，背景色需要设置为 #f2e2e2；鼠标离开后，背景色需要设置为 #fff
        // （注意：需要使用 JS 新版本的语法）

        // 1. 获取所有的 tr 标签
        let trs = document.querySelectorAll('tr');

        // 2. 为每一个 tr 标签添加事件监听
        for (let i = 0; i < trs.length; i++) {
            trs[i].addEventListener('mouseenter', function() {  // mouseenter：鼠标进入
                this/* 或：trs[i] */.style.backgroundColor = '#f2e2e2';
            });

            trs[i].addEventListener('mouseleave', function() {  // mouseleave：鼠标离开
                this/* 或：trs[i] */.style.backgroundColor = '#fff';
            });
        }
    </script>
</body>
</html>
```

### 2.4 常见事件

上面案例中使用到了事件 `click`、`mouseenter`、`mouseleave`，那么都有哪些事件类型供我们使用呢？

以下为一些比较常用的事件属性：
![常用事件属性](./images/02_常用事件属性.png "常用事件属性")

示例代码：
```html
<!-- 10.JS-常见事件.html -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-事件-常见事件</title>
</head>

<body>
    <form action="" style="text-align: center;">
        <input type="text" name="username" id="username">
        <input type="text" name="age" id="age">
        <input id="b1" type="submit" value="提交">
        <input id="b2" type="button" value="单击事件">
    </form>

    <br><br><br>

    <table width="800px" border="1" cellspacing="0" align="center">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>分数</th>
            <th>评语</th>
        </tr>
        <tr align="center">
            <td>001</td>
            <td>张三</td>
            <td>90</td>
            <td>很优秀</td>
        </tr>
        <tr align="center" id="last">
            <td>002</td>
            <td>李四</td>
            <td>92</td>
            <td>优秀</td>
        </tr>
    </table>

    
    <script>
        // click: 鼠标点击事件
        document.querySelector('#b2').addEventListener('click', () => {
            console.log("我被点击了...");
        })

        // mouseenter: 鼠标移入
        document.querySelector('#last').addEventListener('mouseenter', () => {
            console.log("鼠标移入了...");
        })

        // mouseleave: 鼠标移出
        document.querySelector('#last').addEventListener('mouseleave', () => {
            console.log("鼠标移出了...");
        })

        // keydown: 某个键盘的键被按下
        document.querySelector('#username').addEventListener('keydown', () => {
            console.log("键盘被按下了...");
        })

        // keyup: 某个键盘的键被抬起
        document.querySelector('#username').addEventListener('keyup', () => {
            console.log("键盘被抬起了...");
        })

        // blur: 元素失去焦点事件
        document.querySelector('#age').addEventListener('blur', () => {
            console.log("失去焦点...");
        })

        // focus: 元素获得焦点事件
        document.querySelector('#age').addEventListener('focus', () => {
            console.log("获得焦点...");
        })

        // input: 用户输入时触发
        document.querySelector('#age').addEventListener('input', () => {
            console.log("用户输入时触发...");
        })

        // submit: 提交表单事件
        document.querySelector('form').addEventListener('submit', () => {
            alert("表单被提交了...");
        })
    </script>
</body>

</html>
```

### 2.5 优化 - JS 模块化

在 《2.4 常见事件》的示例代码 10.JS-常见事件.html 中，存在**复用**与**维护**较差的问题：
![优化前代码存在问题的区域](./images/02_优化前代码存在问题的区域.png "优化前代码存在问题的区域")

因此，通过 JS 模块化（`export` 与 `import`）对上述程序进行优化：

```javascript
/* ./js/utils.js */

export function printLog(msg){
  console.log(msg);
}
```

```javascript
/* ./js/eventDemo.js */

import { printLog } from "./utils.js";

//click: 鼠标点击事件
document.querySelector('#b2').addEventListener('click', () => {
    printLog("我被点击了...");
})

//mouseenter: 鼠标移入
document.querySelector('#last').addEventListener('mouseenter', () => {
    printLog("鼠标移入了...");
})

//mouseleave: 鼠标移出
document.querySelector('#last').addEventListener('mouseleave', () => {
    printLog("鼠标移出了...");
})

//keydown: 某个键盘的键被按下
document.querySelector('#username').addEventListener('keydown', () => {
    printLog("键盘被按下了...");
})

//keyup: 某个键盘的键被抬起
document.querySelector('#username').addEventListener('keyup', () => {
    printLog("键盘被抬起了...");
})

//blur: 失去焦点事件
document.querySelector('#age').addEventListener('blur', () => {
    printLog("失去焦点...");
})

//focus: 元素获得焦点
document.querySelector('#age').addEventListener('focus', () => {
    printLog("获得焦点...");
})

//input: 用户输入时触发
document.querySelector('#age').addEventListener('input', () => {
  printLog("用户输入时触发...");
})

//submit: 提交表单事件
document.querySelector('form').addEventListener('submit', () => {
    alert("表单被提交了...");
})
```

```html
<!-- ./11.JS-常见事件（优化） -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-事件-常见事件</title>
</head>

<body>
    <form action="" style="text-align: center;">
        <input type="text" name="username" id="username">
        <input type="text" name="age" id="age">
        <input id="b1" type="submit" value="提交">
        <input id="b2" type="button" value="单击事件">
    </form>

    <br><br><br>

    <table width="800px" border="1" cellspacing="0" align="center">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>分数</th>
            <th>评语</th>
        </tr>
        <tr align="center">
            <td>001</td>
            <td>张三</td>
            <td>90</td>
            <td>很优秀</td>
        </tr>
        <tr align="center" id="last">
            <td>002</td>
            <td>李四</td>
            <td>92</td>
            <td>优秀</td>
        </tr>
    </table>
    <!-- type="module"：模块化 JS -->
    <script src="./js/eventDemo.js" type="module"></script>
</body>

</html>
```

## 三、Vue 介绍

> 前面，我们已经学习了前端网页开发的三剑客：HTML、CSS、JS。通过这三种技术，我们就可以开发出一个网页程序了。但是如果我们使用原生的 JS 来处理界面的交互行为，开发效率是比较低的；而在现在的企业项目开发中，一般会借助于 Vue 这样的 JS 框架来简化操作、提高开发效率。
>
> 那么接下来，我们就来学习 Vue 这个框架。

### 3.1 概述

Vue（读音 /vjuː /，类似于 view），是一款用于**构建用户界面**的**渐进式**的 JavaScript **框架**。

Vue 官方网站：[Vue 官方网站](https://cn.vuejs.org "Vue 官方网站")。

关键词：**构建用户界面**、**渐进式**、**框架**。

#### 3.1.1 关键词 1：构建用户界面

构建用户界面是指，在 Vue 中，可以基于数据渲染出用户看到的界面。那这句话是什么意思呢？我们来举一个例子，比如将来服务器端返回给前端的原始数据如下所示：
```JSON
userList: [
  {"id": 1, "name": "谢逊", "image": "1.jpg", "gender": 1, "job": "班主任"},
  {"id": 2, "name": "韦一笑", "image": "2.jpg", "gender": 1, "job": "班主任"}
]
```

上面的这些原始数据，用户是看不懂的；而我们开发人员可以使用 Vue 中提供的操作，将原始数据遍历、解析出来，从而渲染呈现出用户所能看懂的界面。如下所示：
![原始数据经遍历、解析后渲染呈现出用户所能看懂的界面](./images/02_原始数据经遍历、解析后渲染呈现出用户所能看懂的界面.png "原始数据经遍历、解析后渲染呈现出用户所能看懂的界面")

那么这个过程就是基于数据渲染出用户看到的界面，也就是所谓的“**构建用户界面**”。

#### 3.1.2 关键词 2：渐进式

渐进式中的“渐进”，字面意思就是“循序渐进”。Vue 生态中的语法是非常多的，比如声明式渲染、组件系统、客户端路由（VueRouter）、状态管理（Vuex、Pinia）、构建工具（Webpack、Vite）等等。

![Vue 生态中的语法](./images/2_Vue生态中的语法.png "Vue 生态中的语法")

所谓“渐进”，指的是我们使用 Vue 框架时，不需要把所有的组件、语法全部学习完毕才可以使用 Vue，而是即学即用。比如：
* 我们学习了声明式渲染，就可以使用 Vue 来构建用户界面了。
* 我们再学习了组件系统，就可以使用 Vue 中的组件，从而来复用了。
* 我们再学习了路由 VueRouter，就可以使用 Vue 中的路由功能了。

也就是说，并不需要全部学习完毕，我们就可以直接使用 Vue 进行开发以简化操作、提高效率了。Vue 是一个框架，但其实也是一个生态。

那由此呢，也就引出了 Vue 中两种常见的开发模式：
* 基于 Vue 提供的核心包，完成项目局部模块的改造。
* 基于 Vue 提供的核心包、插件进行工程化开发，也就是做整站开发。

上面的这两种 Vue 的使用形式，我们都会学习。今天，我们先来学习第一种方式，也就是使用 Vue 来完成局部模块改造。

#### 3.1.3 关键词 3：框架

框架：就是一套完整的项目解决方案，用于快速构建项目。这是我们接触的第一个框架。在我们后面的学习中，我们还会学习很多的 Java 语言中的框架；通过这些框架，我们就可以来快速开发 Java 项目，提高开发效率。

优点：大大提升前端项目的开发效率。
缺点：需要理解记忆框架的使用规则（参照官网）。

接下来，就要正式进入 Vue 的学习。本章主要讲解以下几个方面：
1. Vue 快速入门。
2. Vue 常用指令。
3. Ajax。
4. Vue 生命周期。

### 3.2 入门程序

#### 3.2.1 需求

在入门程序中，最终我们需要将准备的数据 `message` 的值，基于 Vue 渲染展示在页面中。

最终呈现的形式如下：
![Vue 入门程序最终呈现形式](./images/02_Vue入门程序最终呈现形式.png "Vue 入门程序最终呈现形式")

#### 3.2.2 步骤

##### 3.2.2.1 准备工作

准备一个 HTML 文件，并在其中引入 Vue 模块（参考官方文档，复制过来即可）。

> 注意：
>
> 模块化的 JS 在引入时，需要设置 `type="module"`。

创建 Vue 程序的应用实例，控制视图的元素。

准备元素（`div`），交给 Vue 控制。

![Vue 入门程序 - 准备工作](./images/02_Vue入门程序-准备工作.png "Vue 入门程序 - 准备工作")

这是三步准备工作，是我们使用 Vue 时都需要做的，是固定步骤。这样我们就搭建好了一个基本的 Vue 的结构了。

##### 3.2.2.2 数据驱动视图

准备数据：在创建 Vue 应用实例的时候，传入了一个 JS 对象；在这个 JS 对象中，我们要定义一个 `data` 方法，这个 `data` 方法的返回值就是 Vue 中的数据。

通过插值表达式渲染界面；插值表达式的写法：`{{...}}`。

![Vue 入门程序 - 数据驱动视图](./images/02_Vue入门程序-数据驱动视图.png "Vue 入门程序 - 数据驱动视图")

#### 3.2.3 实现

示例代码：
```html
<!-- 12.Vue-快速入门.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vue-快速入门</title>
</head>
<body>

  <div id="app">
    <h1>{{message}}</h1>
    <h1>{{count}}</h1>
  </div>


  <script type="module">
    import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js';

    createApp({
      data() {
        return {
          message: 'Hello Vue',
          count: 100
        };
      }
    }).mount('#app');
  </script>
</body>
</html>
```

在上述入门程序编写时，需要注意这么几点：
* Vue 中定义数据，必须通过 `data` 方法来定义；`data` 方法返回值是一个对象，在这个对象中定义数据。
* 插值表达式中编写的变量，一定是 Vue 中定义的数据；如果插值表达式中编写了一个变量，但是在 Vue 中未定义，将会报错。
* Vue 应用实例接管的区域是 `'#app'`，超出这个范围，就不受 Vue 控制了；所以 Vue 的插值表达式，一定写在 `<div id="app">...</div>` 的里面。

## 四、Vue 指令

### 4.1 概述

**指令：** 指的是 HTML 标签上带有 `v-` 前缀的特殊属性。不同指令具有不同含义，可以实现不同的功能。例如：`v-if`、`v-for` ……

**形式：**
```html
<p v-xxx="...">...</p>
```

**常见指令：**
| 指令 | 作用 |
| :--: | :--: |
| `v-for` | 列表渲染，遍历容器的元素或者对象的属性 |
| `v-bind` | 为 HTML 标签绑定属性值；如设置 `href`、CSS 样式等 |
| `v-if` / `v-else-if` / `v-else` | 条件性的渲染某元素，判定为 `true` 时渲染，否则不渲染 |
| `v-show` | 根据条件展示某元素，区别在于切换的是 `display` 属性的值 |
| `v-model` | 在表单元素上创建双向数据绑定 |
| `v-on` | 为 HTML 标签绑定事件 |

### 4.2 案例

#### 4.2.1 基本实现

需求：员工列表数据渲染展示。

![员工列表数据渲染展示](./images/02_员工列表数据渲染展示.png "员工列表数据渲染展示")

示例代码：
```html
<!-- 13.Vue-案例-员工列表（常用指令）.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" v-model="searchForm.name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender" v-model="searchForm.gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position" v-model="searchForm.job">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button" v-on:click="search">查询</button>
            <button type="button" @click="clear">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr v-for="(e, index) in empList" :key="e.id">
                    <td>{{index + 1}}</td>
                    <td>{{e.name}}</td>
                    <td>{{e.gender == 1 ? '男' : '女'}}</td>
                    <!-- 插值表达式是不能出现在标签内部的 -->
                    <td><img class="avatar" v-bind:src="e.image" :alt="e.name"></td>
                    
                    <!-- v-if: 控制元素的显示与隐藏 -->
                    <td>
                        <span v-if="e.job == 1">班主任</span>
                        <span v-else-if="e.job == 2">讲师</span>
                        <span v-else-if="e.job == 3">学工主管</span>
                        <span v-else-if="e.job == 4">教研主管</span>
                        <span v-else-if="e.job == 5">咨询师</span>
                        <span v-else>其他</span>
                    </td>

                    <!-- v-show: 控制元素的显示与隐藏 -->
                    <!-- <td>
                        <span v-show="e.job == 1">班主任</span>
                        <span v-show="e.job == 2">讲师</span>
                        <span v-show="e.job == 3">学工主管</span>
                        <span v-show="e.job == 4">教研主管</span>
                        <span v-show="e.job == 5">咨询师</span>
                    </td> -->
                    <td>{{e.entrydate}}</td>
                    <td>{{e.updatetime}}</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>
    

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

      createApp({
        data() {
          return {
            searchForm: {   // 封装用户输入的查询条件
                name: '',
                gender: '',
                job: ''
            },
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-09-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2024-09-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2024-09-01T00:00:00"
              }
            ]
          }
        },
        // 方法
        methods: {
            search() {
                // 将搜索条件输出到控制台
                console.log(this.searchForm);
            },
            clear() {
                // 清空表单项数据
                this.searchForm = {name:'', gender:'', job:''};
            }
        }
      }).mount('#container');
    </script>

</body>
</html>
```

> 注意：
>
> 在 JavaScript 中，方括号 `[]` 表示数组，大括号 `{}` 表示对象。

在上述代码中，用到了 Vue 中提供的指令和插值表达式。接下来，我们就来详细剖析一下 Vue 中指令的用法。

#### 4.2.2 指令详解

##### 4.2.2.1 `v-for`

作用：列表渲染，遍历容器的元素或者对象的属性。

语法：
```html
<tr v-for="(item, index) in items" :key="item.id">{{item}}</tr>
```

参数：
* `items` 为遍历的数组。
* `item` 为遍历出来的元素。
* `index` 为索引 / 下标，从 `0` 开始；可以省略，省略 `index` 语法：`v-for = "item in items"`。

`key`：
* 作用：给元素添加的唯一标识，便于 Vue 进行列表项的正确排列复用，提升渲染性能。
* 推荐使用 `id` 作为 `key`（唯一），不推荐使用 `index` 作为 `key`（会变化，不对应）。

> 注意：遍历的数组，必须在 `data` 中定义；要想让哪个标签循环展示多次，就在哪个标签上使用 `v-for` 指令。

##### 4.2.2.2 `v-bind`

作用：动态为 HTML 标签绑定属性值；如设置 href、src、style 样式等。

语法：`v-bind:属性名="属性值"`；示例代码：
```html
<img v-bind:src="item.image" width="30px">
```

简化：`:属性名="属性值"`；示例代码：
```html
<img :src="item.image" width="30px">
```

> 注意：
>
> 动态地为标签的属性绑定值，不能使用插值表达式，而应该使用 `v-bind` 指令。且 `v-bind` 所绑定的数据，必须在 `data` 中定义，或基于 `data` 中定义的数据而来。

##### 4.2.2.3 `v-if` 与 `v-show`

作用：这两类指令，都是用来控制元素的显示与隐藏的。

###### 4.2.2.3.1 `v-if`

语法：`v-if="表达式"`；表达式值为 `true` 则显示，值为 `false` 则隐藏。

原理：基于条件判断，来控制创建或移除元素结点（条件渲染）。

场景：要么显示，要么不显示，且不频繁切换的场景。

其他：可以配合 `v-else-if` / `v-else` 进行链式调用条件判断。

示例代码：
```html
<!-- 基于 v-if / v-else-if / v-else 指令来展示职位这一列 -->
<td>
  <span v-if="emp.job === '1'">班主任</span>
  <span v-else-if="emp.job === '2'">讲师</span>
  <span v-else-if="emp.job === '3'">学工主管</span>
  <span v-else-if="emp.job === '4'">教研主管</span>
  <span v-else-if="emp.job === '5'">咨询师</span>
  <span v-else>其他</span>
</td>
```

> 注意：`v-else-if` 必须出现在 `v-if` 之后，可以出现多个；`v-else` 必须出现在 `v-if` / `v-else-if` 之后。

通过浏览器的开发者工具，我们可以看到，如果使用 `v-if` 指令来渲染展示，确实是根据条件判断是否渲染这个元素结点，条件成立才会渲染。查看结果如下：
![使用 v-if 指令渲染展示结果](./images/02_使用v-if指令渲染展示结果.png "使用 v-if 指令渲染展示结果")

###### 4.2.2.3.2 `v-show`

语法：`v-show="表达式"`，表达式值为 `true` 则显示，值为 `false` 则隐藏。

原理：基于 CSS 样式 `display` 来控制显示与隐藏。

场景：频繁切换显示或隐藏的场景。

示例代码：
```html
<!-- 基于 v-show 指令来展示职位这一列 -->
<td>
  <span v-show="emp.job === '1'">班主任</span>
  <span v-show="emp.job === '2'">讲师</span>
  <span v-show="emp.job === '3'">学工主管</span>
  <span v-show="emp.job === '4'">教研主管</span>
  <span v-show="emp.job === '5'">咨询师</span>
</td>
```

通过浏览器的开发者工具，我们可以看到，如果使用 `v-show` 指令来渲染展示，所有元素都会渲染，只不过是通过控制 `display` 这个 CSS 样式来决定元素是展示还是隐藏。查看结果如下：
![使用 v-show 指令渲染展示结果](./images/02_使用v-show指令渲染展示结果.png "使用 v-show 指令渲染展示结果")

##### 4.2.2.4 `v-model`

作用：在表单元素上使用，**双向数据绑定**；可以方便地**获取**或**设置**表单项数据。

语法：`v-model="变量名"`。

这里的双向数据绑定，是指 Vue 中的数据变化，会影响视图中的数据展示；视图中的输入的数据变化，也会影响 Vue 的数据模型。

![使用 v-model 指令获取或设置表单项数据](./images/02_使用v-model指令获取或设置表单项数据.png "使用 v-model 指令获取或设置表单项数据")

> 注意：
>
> `v-model` 中绑定的变量，必须在 `data` 中定义。

为员工列表案例的搜索栏的表单项，绑定数据：
```html
<!-- 13.Vue-案例-员工列表（常用指令）.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        {{searchForm}}
        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" v-model="searchForm.name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender" v-model="searchForm.gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position" v-model="searchForm.job">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button">查询</button>
            <button type="button">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr v-for="(e, index) in empList" :key="e.id">
                    <td>{{index + 1}}</td>
                    <td>{{e.name}}</td>
                    <td>{{e.gender == 1 ? '男' : '女'}}</td>
                    <!-- 插值表达式是不能出现在标签内部的 -->
                    <td><img class="avatar" v-bind:src="e.image" :alt="e.name"></td>
                    
                    <!-- v-if: 控制元素的显示与隐藏 -->
                    <td>
                        <span v-if="e.job == 1">班主任</span>
                        <span v-else-if="e.job == 2">讲师</span>
                        <span v-else-if="e.job == 3">学工主管</span>
                        <span v-else-if="e.job == 4">教研主管</span>
                        <span v-else-if="e.job == 5">咨询师</span>
                        <span v-else>其他</span>
                    </td>

                    <!-- v-show: 控制元素的显示与隐藏 -->
                    <!-- <td>
                        <span v-show="e.job == 1">班主任</span>
                        <span v-show="e.job == 2">讲师</span>
                        <span v-show="e.job == 3">学工主管</span>
                        <span v-show="e.job == 4">教研主管</span>
                        <span v-show="e.job == 5">咨询师</span>
                    </td> -->
                    <td>{{e.entrydate}}</td>
                    <td>{{e.updatetime}}</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>
    

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

      createApp({
        data() {
          return {
            searchForm: {   // 封装用户输入的查询条件
                name: '',
                gender: '',
                job: ''
            },
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-09-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2024-09-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2024-09-01T00:00:00"
              }
            ]
          }
        }
      }).mount('#container');
    </script>

</body>
</html>
```

##### 4.2.2.5 `v-on`

作用：为 HTML 标签绑定事件（添加事件监听）。

语法：
* `v-on:事件名="方法名"`；示例代码：
  ```html
  <input type="button" value="点我一下试试" v-on:click="handle">
  ```
  * 简写为：`@事件名="..."`；示例代码：
    ```html
    <input type="button" value="点我一下试试" @click="handle">
    ```

这里的 `handle` 函数，就需要在 Vue 应用实例创建的时候创建出来，在 `methods` 定义：
![methods 与在其中定义的 handle 函数](./images/02_methods与在其中定义的handle函数.png "methods 与在其中定义的 handle 函数")

为员工列表案例的搜索栏中的“查询”和“清空”按钮绑定事件：
```html
<!-- 13.Vue-案例-员工列表（常用指令）.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" v-model="searchForm.name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender" v-model="searchForm.gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position" v-model="searchForm.job">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button" v-on:click="search">查询</button>
            <button type="button" @click="clear">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr v-for="(e, index) in empList" :key="e.id">
                    <td>{{index + 1}}</td>
                    <td>{{e.name}}</td>
                    <td>{{e.gender == 1 ? '男' : '女'}}</td>
                    <!-- 插值表达式是不能出现在标签内部的 -->
                    <td><img class="avatar" v-bind:src="e.image" :alt="e.name"></td>
                    
                    <!-- v-if: 控制元素的显示与隐藏 -->
                    <td>
                        <span v-if="e.job == 1">班主任</span>
                        <span v-else-if="e.job == 2">讲师</span>
                        <span v-else-if="e.job == 3">学工主管</span>
                        <span v-else-if="e.job == 4">教研主管</span>
                        <span v-else-if="e.job == 5">咨询师</span>
                        <span v-else>其他</span>
                    </td>

                    <!-- v-show: 控制元素的显示与隐藏 -->
                    <!-- <td>
                        <span v-show="e.job == 1">班主任</span>
                        <span v-show="e.job == 2">讲师</span>
                        <span v-show="e.job == 3">学工主管</span>
                        <span v-show="e.job == 4">教研主管</span>
                        <span v-show="e.job == 5">咨询师</span>
                    </td> -->
                    <td>{{e.entrydate}}</td>
                    <td>{{e.updatetime}}</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>
    

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

      createApp({
        data() {
          return {
            searchForm: {   // 封装用户输入的查询条件
                name: '',
                gender: '',
                job: ''
            },
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-09-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2024-09-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2024-09-01T00:00:00"
              }
            ]
          }
        },
        // 方法
        methods: {
            search() {
                // 将搜索条件输出到控制台
                console.log(this.searchForm);
            },
            clear() {
                // 清空表单项数据
                this.searchForm = {name:'', gender:'', job:''};
            }
        }
      }).mount('#container');
    </script>

</body>
</html>
```

> 注意：`methods` 函数中的 `this` 指向 Vue 实例，可以通过 `this` 获取到 `data` 中定义的数据。

## 五、Ajax

### 5.1 概述

我们前端页面中的数据，如下图所示的表格中的员工信息，应该来自于后台；而我们的后台和前端是互不影响的 2 个程序，那么我们前端应该如何从后台获取数据呢？因为是 2 个程序，所以必须设计到 2 个程序的交互，因此这就需要用到我们接下来学习的 Ajax 技术。

![前端页面中的数据展示 - 员工信息](./images/02_前端页面中的数据展示-员工信息.png "前端页面中的数据展示 - 员工信息")

**Ajax**：全称 **A**synchronous **J**avaScript **A**nd **X**ML，异步的 JavaScript 和 XML。其作用有如下 2 点：
* 与服务器进行数据交换：通过 Ajax 可以给服务器发送请求，并获取服务器响应的数据。
* 异步交互：可以在**不重新加载整个页面**的情况下与服务器交换数据并**更新部分网页**的技术；如：搜索联想、用户名是否可用的校验等等。

我们详细解释一下 Ajax 技术的 2 个作用：
* 与服务器进行数据交互：前端资源被浏览器解析，但是前端页面上缺少数据，前端可以通过 Ajax 技术，向后台服务器发起请求；后台服务器接受到前端的请求，从数据库中获取前端需要的资源，然后响应给前端；前端再通过我们学习的 Vue 技术，可以将数据展示到页面上，这样用户就能看到完整的页面了。此处可以对比 JavaSE 中的网络编程技术来理解。
* 异步交互：当我们在百度搜索 Java 时，显示的联想数据是通过 Ajax 请求从后台服务器得到的；在整个过程中，我们的 Ajax 请求不会导致整个百度页面的重新加载，并且只针对搜索栏这局部模块的数据进行了数据的更新，不会对整个页面的其他地方进行数据的更新；这样就大大提升了页面的加载速度，用户体验更好。

> **XML**（E**x**tensible **M**arkup **L**anguage）：可扩展标记语言，本质是一种数据格式，可以用来存储复杂的数据结构。

### 5.2 同步与异步

针对于上述 Ajax 的局部刷新功能是因为 Ajax 请求是异步的，与之对应的有同步请求。接下来我们介绍一下异步请求和同步请求的区别。

* **同步请求**发送过程如下图所示：

![同步请求发送过程示意图](./images/02_同步请求发送过程示意图.png "同步请求发送过程示意图")

浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面不能做其他的操作；只能等到服务器响应结束后，浏览器页面才能继续做其他的操作。

* **异步请求**发送过程如下图所示：

![异步请求发送过程示意图](./images/02_异步请求发送过程示意图.png "异步请求发送过程示意图")

浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面还可以做其他的操作。

### 5.3 Axios

使用原生的 Ajax 请求的代码编写起来还是比较繁琐的，所以接下来我们学习一门更加简单的发送 Ajax 请求的技术 Axios。Axios 是对原生的 Ajax 进行封装，以简化书写。

Axios 官网：[Axios 官网](https://www.axios-http.cn "Axios 官网")。

#### 5.3.1 入门程序

Axios 的使用比较简单，主要分为 2 步：

1). 引入 Axios 文件
```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

2). 使用 Axios 发送请求，并获取响应结果
```javascript
axios({
  method: 'GET',
  url: 'https://web-server.itheima.net/emps/list'
}).then((result) => { // 成功回调函数
  console.log(result.data);
}).catch((err) => { // 失败回调函数
  alert(err);
});
```

其中：
* `method`：请求方式，`GET` / `POST`。
* `url`：请求路径。
* `data`：请求数据（当请求方式为 `POST` 时可使用）。
* `params`：发送请求时携带的 url 参数；如：`...?key=val`。

> 注意：在使用 Axios 时，在 `axios` 之后，输入 `thenc` 会自动生成成功及失败回调函数结构。

示例代码：
```html
<!-- 14.Axios-入门.html -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajax-Axios</title>
</head>
<body>
    
    <input type="button" value="获取数据GET" id="btnGet">
    <input type="button" value="操作数据POST" id="btnPost">

    <script src="js/axios.js"></script>
    <script>
        // 发送 GET 请求
        document.querySelector('#btnGet').addEventListener('click', () => {
            // Axios 发起异步请求
            axios({
                url: 'https://mock.apifox.cn/m1/3083103-0-default/emps/list',
                method: 'GET'
            }).then((result) => {   // 成功回调函数
                console.log(result.data);
            }).catch((err) => {
                console.log(err);   // 失败回调函数
            })
        });
        
        // 发送 POST 请求
        document.querySelector('#btnPost').addEventListener('click', () => {
            // Axios 发起异步请求
            axios({
                url: 'https://mock.apifox.cn/m1/3083103-0-default/emps/update',
                method: 'POST',
                data: 'id=1'    // POST 请求方式，请求体
            }).then((result) => {   // 成功回调函数
                console.log(result.data);
            }).catch((err) => {
                console.log(err);   // 失败回调函数
            })
        });
    </script>
</body>
</html>
```

#### 5.3.2 请求方法别名

Axios 还针对不同的请求，提供了别名方式的 API，具体格式如下：
```javascript
axios.请求方式(url[, data[, config]])
```

具体如下：
| 方法 | 描述 |
| :--: | :--: |
| `axios.get(url[, config])` | 发送 get 请求 |
| `axios.delete(url[, config])` | 发送 delete 请求 |
| `axios.post(url[, data[, config]])` | 发送 post 请求 |
| `axios.put(url[, data[, config]])` | 发送 put 请求 |

我们目前只关注 get 和 post 请求，所以在上述的入门案例中，我们可以将 get 请求代码改写成如下：
```javascript
axios.get('https://mock.apifox.cn/m1/3083103-0-default/emps/list').then((result) => {
    console.log(result.data);
});
```

post 请求改写成如下：
```javascript
axios.post('https://mock.apifox.cn/m1/3083103-0-default/emps/update', 'id=1').then((result) => {
    console.log(result.data);
});
```

示例代码：
```html
<!-- 15.Axios-请求方式别名.html -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajax-Axios</title>
</head>
<body>
    
    <input type="button" value="获取数据GET" id="btnGet">
    <input type="button" value="操作数据POST" id="btnPost">

    <script src="js/axios.js"></script>
    <script>
        //发送GET请求
        document.querySelector('#btnGet').addEventListener('click', () => {
            axios.get('https://mock.apifox.cn/m1/3083103-0-default/emps/list').then((result) => {
                console.log(result.data);
            });
            console.log('==========================');
        });
        
        //发送POST请求
        document.querySelector('#btnPost').addEventListener('click', () => {
            axios.post('https://mock.apifox.cn/m1/3083103-0-default/emps/update', 'id=1').then((result) => {
                console.log(result.data);
            });
        });
    </script>
</body>
</html>
```

### 5.4 案例 - 异步获取数据

需求：基于 Axios 动态加载员工列表数据。

具体代码实现如下：
```html
<!-- 16.Vue-案例-员工列表（异步交互）.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" v-model="searchForm.name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender" v-model="searchForm.gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position" v-model="searchForm.job">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button" v-on:click="search">查询</button>
            <button type="button" @click="clear">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr v-for="(e, index) in empList" :key="e.id">
                    <td>{{index + 1}}</td>
                    <td>{{e.name}}</td>
                    <td>{{e.gender == 1 ? '男' : '女'}}</td>
                    <!-- 插值表达式是不能出现在标签内部的 -->
                    <td><img class="avatar" v-bind:src="e.image" :alt="e.name"></td>
                    
                    <!-- v-if: 控制元素的显示与隐藏 -->
                    <td>
                        <span v-if="e.job == 1">班主任</span>
                        <span v-else-if="e.job == 2">讲师</span>
                        <span v-else-if="e.job == 3">学工主管</span>
                        <span v-else-if="e.job == 4">教研主管</span>
                        <span v-else-if="e.job == 5">咨询师</span>
                        <span v-else>其他</span>
                    </td>

                    <!-- v-show: 控制元素的显示与隐藏 -->
                    <!-- <td>
                        <span v-show="e.job == 1">班主任</span>
                        <span v-show="e.job == 2">讲师</span>
                        <span v-show="e.job == 3">学工主管</span>
                        <span v-show="e.job == 4">教研主管</span>
                        <span v-show="e.job == 5">咨询师</span>
                    </td> -->
                    <td>{{e.entrydate}}</td>
                    <td>{{e.updatetime}}</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>
    
    <script src="js/axios.js"></script>
    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

      createApp({
        data() {
          return {
            searchForm: {   // 封装用户输入的查询条件
                name: '',
                gender: '',
                job: ''
            },
            empList: []
          }
        },
        // 方法
        methods: {
            async search() {
                // 发送 Ajax 请求，获取数据
                // axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`).then((result) => {
                //     this.empList = result.data.data;
                // });
                // console.log('=================================');

                let result = await axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`);
                this.empList = result.data.data;
            },
            clear() {
                // 清空表单项数据
                this.searchForm = {name:'', gender:'', job:''};
                this.search();
            }
        }
      }).mount('#container');
    </script>

</body>
</html>
```

> 注意：
>
> 上述实现代码存在问题：当页面首次加载出来时，表格中的数据并不会随之一起加载出来；而是需要手动点击“查询”按钮之后，表格中的数据才会被加载出来。该问题将于《六、Vue 生命周期》中予以解决。

如果使用 Axios 中提供的 `.then(function() {...}).catch(function() {...})` 这种回调函数的写法，会使得代码的可读性和维护性变差。而为了解决这个问题，我们可以使用两个关键字，分别是：`async`、`await`。

可以通过 `async`、`await` 可以让异步变为同步操作。`async` 用来声明一个异步方法，`await` 用来等待异步任务执行。

> 注意：
>
> `await` 关键字只在 `async` 函数内有效，`await` 关键字取代 `then` 函数，等待获取到请求成功的结果值。

代码修改前：
```javascript
search() {
  // 基于 Axios 发送异步请求，请求 https://web-server.itheima.net/emps/list，根据条件查询员工列表
  axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`).then(res => {
    this.empList = res.data.data;
  })
},
```

代码修改后：
```javascript
async search() {
  // 基于 Axios 发送异步请求，请求 https://web-server.itheima.net/emps/list，根据条件查询员工列表
  const result = await axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`);
  this.empList = result.data.data;
},
```

修改后，代码就变成同步操作了，一行一行地从前往后执行。在前端项目开发中，经常使用这两个关键字配合，使得代码地可读性和可维护性变高。

## 六、Vue 生命周期

### 6.1 介绍

Vue 的生命周期：指的是 Vue 对象从创建到销毁的过程。

Vue 的生命周期包含 8 个阶段，每触发一个生命周期事件，会自动执行一个生命周期方法，这些生命周期方法也被称为钩子方法。其完整的生命周期如下表所示：
| 状态 | 阶段周期 |
| :--: | :--: |
| beforeCreate | 创建前 |
| created | 创建后 |
| beforeMount | 挂载前 |
| mounted | 挂载完成 |
| beforeUpdate | 更新前 |
| update | 更新后 |
| beforeDestroy | 销毁前 |
| destroyed | 销毁后 |

下图是 Vue 官网提供的从创建 Vue 到销毁 Vue 对象的整个过程及各个阶段对应的钩子函数：
![从创建 Vue 到销毁 Vue 对象的整个过程及各个阶段对应的钩子函数](./images/02_从创建Vue到销毁Vue对象的整个过程及各个阶段对应的钩子函数.png "从创建 Vue 到销毁 Vue 对象的整个过程及各个阶段对应的钩子函数")

其中我们需要重点关注的是 mounted，其他的我们了解即可。

mounted：挂载完成，Vue 初始化成功，HTML 页面渲染成功。**以后我们一般用于页面初始化自动地 Ajax 请求后台数据。**

### 6.2 案例完善

要想在页面加载完毕就查询出员工列表，可以在 `mounted` 钩子函数中发送异步请求查询员工数据。

具体代码如下：
```html
<!-- 16.Vue-案例-员工列表（异步交互）.html -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Tlias智能学习辅助系统</title>
    <style>
        /* 导航栏样式 */
        .navbar {
            background-color: #b5b3b3; /* 灰色背景 */
            
            display: flex; /* flex弹性布局 */
            justify-content: space-between; /* 左右对齐 */

            padding: 10px; /* 内边距 */
            align-items: center; /* 垂直居中 */
        }
        .navbar h1 {
            margin: 0; /* 移除默认的上下外边距 */
            font-weight: bold; /* 加粗 */
            color: white;
            /* 设置字体为楷体 */
            font-family: "楷体";
        }
        .navbar a {
            color: white; /* 链接颜色为白色 */
            text-decoration: none; /* 移除下划线 */
        }

        /* 搜索表单样式 */
        .search-form {
            display: flex;
            flex-wrap: nowrap;
            align-items: center;
            gap: 10px; /* 控件之间的间距 */
            margin: 20px 0;
        }
        .search-form input[type="text"], .search-form select {
            padding: 5px; /* 输入框内边距 */
            width: 260px; /* 宽度 */
        }
        .search-form button {
            padding: 5px 15px; /* 按钮内边距 */
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd; /* 边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: center; /* 左对齐 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .avatar {
            width: 30px;
            height: 30px;
        }

        /* 页脚样式 */
        .footer {
            background-color: #b5b3b3; /* 灰色背景 */
            color: white; /* 白色文字 */
            text-align: center; /* 居中文本 */
            padding: 10px 0; /* 上下内边距 */
            margin-top: 30px;
        }

        #container {
            width: 80%; /* 宽度为80% */
            margin: 0 auto; /* 水平居中 */
        }
    </style>
</head>
<body>
    <div id="container">
        <!-- 顶部导航栏 -->
        <div class="navbar">
            <h1>Tlias智能学习辅助系统</h1>
            <a href="#">退出登录</a>
        </div>

        <!-- 搜索表单区域 -->
        <form class="search-form" action="/search" method="post">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" v-model="searchForm.name" placeholder="请输入姓名">

            <label for="gender">性别：</label>
            <select id="gender" name="gender" v-model="searchForm.gender">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>

            <label for="position">职位：</label>
            <select id="position" name="position" v-model="searchForm.job">
                <option value=""></option>
                <option value="1">班主任</option>
                <option value="2">讲师</option>
                <option value="3">学工主管</option>
                <option value="4">教研主管</option>
                <option value="5">咨询师</option>
            </select>

            <button type="button" v-on:click="search">查询</button>
            <button type="button" @click="clear">清空</button>
        </form>

        <!-- 表格展示区 -->
        <table>
            <!-- 表头 -->
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>入职日期</th>
                    <th>最后操作时间</th>
                    <th>操作</th>
                </tr>
            </thead>

            <!-- 表格主体内容 -->
            <tbody>
                <tr v-for="(e, index) in empList" :key="e.id">
                    <td>{{index + 1}}</td>
                    <td>{{e.name}}</td>
                    <td>{{e.gender == 1 ? '男' : '女'}}</td>
                    <!-- 插值表达式是不能出现在标签内部的 -->
                    <td><img class="avatar" v-bind:src="e.image" :alt="e.name"></td>
                    
                    <!-- v-if: 控制元素的显示与隐藏 -->
                    <td>
                        <span v-if="e.job == 1">班主任</span>
                        <span v-else-if="e.job == 2">讲师</span>
                        <span v-else-if="e.job == 3">学工主管</span>
                        <span v-else-if="e.job == 4">教研主管</span>
                        <span v-else-if="e.job == 5">咨询师</span>
                        <span v-else>其他</span>
                    </td>

                    <!-- v-show: 控制元素的显示与隐藏 -->
                    <!-- <td>
                        <span v-show="e.job == 1">班主任</span>
                        <span v-show="e.job == 2">讲师</span>
                        <span v-show="e.job == 3">学工主管</span>
                        <span v-show="e.job == 4">教研主管</span>
                        <span v-show="e.job == 5">咨询师</span>
                    </td> -->
                    <td>{{e.entrydate}}</td>
                    <td>{{e.updatetime}}</td>
                    <td class="action-buttons">
                        <button type="button">编辑</button>
                        <button type="button">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 页脚版权区域 -->
        <footer class="footer">
            <p>江苏传智播客教育科技股份有限公司</p>
            <p>版权所有 Copyright 2006-2024 All Rights Reserved</p>
        </footer>
    </div>
    
    <script src="js/axios.js"></script>
    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

      createApp({
        data() {
          return {
            searchForm: {   // 封装用户输入的查询条件
                name: '',
                gender: '',
                job: ''
            },
            empList: []
          }
        },
        // 方法
        methods: {
            async search() {
                // 发送 Ajax 请求，获取数据
                // axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`).then((result) => {
                //     this.empList = result.data.data;
                // });
                // console.log('=================================');

                let result = await axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`);
                this.empList = result.data.data;
            },
            clear() {
                // 清空表单项数据
                this.searchForm = {name:'', gender:'', job:''};
                this.search();
            }
        },
        // 钩子函数
        mounted() {
            // 页面加载完成之后，发送 Ajax 请求，获取数据
            this.search();
        },
      }).mount('#container');
    </script>

</body>
</html>
```

到此，员工列表查询的功能我们就已经完成了。关于 Vue 的其他高级用法，我们将在后面的前端 Web 实战中来详细讲解。

## 七、附录

Chrome 浏览器插件地址：[Chrome 浏览器插件地址](https://chrome.zzzmh.cn/#/index "Chrome 浏览器插件地址")。
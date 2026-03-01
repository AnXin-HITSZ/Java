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
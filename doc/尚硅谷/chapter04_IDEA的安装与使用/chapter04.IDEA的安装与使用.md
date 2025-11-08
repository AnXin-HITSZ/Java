# 第四章：IDEA 的安装与使用

**目录：**

[TOC]

---

本章专题与脉络：
![第 1 阶段：Java 基本语法 - 第 04 章](./images/第1阶段：Java基本语法-第04章.png "第 1 阶段：Java 基本语法 - 第 04 章")

为什么选择 IDEA？
![针对 Java 开发者的调研结论](./images/image-20221018105217845.png "针对 Java 开发者的调研结论")
![针对 Java 开发 IDE/编辑器 的调研结果](./images/2022-10-18_11-00-29.jpg "针对 Java 开发 IDE/编辑器 的调研结果")

> 注：
>
> JetBrains 官方说明：尽管我们采取了多种措施确保受访者的代表性，但结果可能会略微偏向 JetBrains 产品的用户，因为这些用户更有可能参加调查。

此外，2022 年某美国软件开发商在对近千名专业的Java开发者调研后，发布了《2022年Java开发者生产力报告》。报告提到：JetBrains 的 IntelliJ IDEA是最受欢迎的 Java IDE，占 48%，其次是 Eclipse，占 24%，Visual Studio Code 占 18%。

本着"工欲善其事必先利其器"的精神，本章从 IDEA 的介绍、安装、设置入手，讲解 IDEA 中项目的创建、快捷键与模板的使用、断点调试、常用插件等。

## 一、认识 IntelliJ IDEA

### 1.1 JetBrains 公司介绍

IDEA，是 JetBrains（https://www.jetbrains.com/）公司的产品；该公司成立于 2000 年，总部位于捷克的布拉格，致力于为开发者打造最高效智能的开发工具。
![JetBrains 公司旗下部分产品](./images/1576217376761.png "JetBrains 公司旗下部分产品")

公司旗下还有其它产品，比如：

* WebStorm：用于开发 JavaScript、HTML5、CSS3 等前端技术；
* PyCharm：用于开发 python；
* PhpStorm：用于开发 PHP；
* RubyMine：用于开发 Ruby/Rails；
* AppCode：用于开发 Objective - C/Swift；
* CLion：用于开发 C/C++；
* DataGrip：用于开发数据库和 SQL；
* Rider：用于开发.NET；
* GoLand：用于开发 Go。

用于开发 Android 的 Android Studio，也是 Google 基于 IDEA 社区版进行迭代的。

![IntelliJ IDEA 产品](./images/1593264471351.png "IntelliJ IDEA 产品")

### 1.2 IntelliJ IDEA 介绍

IDEA，全称 IntelliJ IDEA，是 Java 语言的集成开发环境，目前已经（基本）代替了 Eclipse 的使用。IDEA 在业界被公认为是最好的 Java 开发工具（之一），因其功能强悍、设置人性化而深受 Java、大数据、移动端程序员的喜爱。

> IntelliJ IDEA 在 2015 年的官网上这样介绍自己：
>
> Excel at enterprise, mobile and web development with Java, Scala and Groovy,with all the latest modern technologies and frameworks available out of thebox.

![IntelliJ IDEA 宣传标语](./images/image-20221018104714861.png "IntelliJ IDEA 宣传标语")

### 1.3 IDEA 的主要优势（vs Eclipse）

功能强大：
* 强大的整合能力，比如：Git、Maven、Spring 等：
![IDEA 内置的工具和支持的框架](./images/内置的工具和支持的框架.png "IDEA 内置的工具和支持的框架")
* 开箱即用的体验（集成版本控制系统、多语言支持的框架随时可用，无需额外安装插件）。

符合人体工程学：
* 高度智能（快速的智能代码补全、实时代码分析、可靠的重构工具）：
![IDEA 高度智能](./images/image-20221018104821144.png "IDEA 高度智能")
* 提示功能的快速、便捷、范围广：
![IDEA 提示功能 1](./images/clip_imrage002.jpg "IDEA 提示功能 1")
![IDEA 提示功能 2](./images/image-20221018104942633.png "IDEA 提示功能 2")
* 好用的快捷键和代码模板；
* 精准搜索。

### 1.4 IDEA 的下载

下载网址：[下载网址](https://www.jetbrains.com/idea/download/#section=windows)。

IDEA 分为两个版本：旗舰版（Ultimate）和社区版（Community）：
![IDEA 版本分类](./images/image-20220606191620253.png "IDEA 版本分类")

两个不同版本的详细对比，可以参照[官网](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)。

IDEA 的大版本每年迭代一次，大版本下的小版本（如：2022.x）迭代时间不固定，一般每年 3 个小版本。

官网提供了详细的[使用文档](https://www.jetbrains.com/help/idea/meet-intellij-idea.html)。

## 二、卸载与安装

### 2.1 卸载过程

这里以卸载 2022.1.2 版本为例说明。在 控制面板 找到 卸载程序：
![控制面板 下的 卸载程序](./images/image-20221019120032582.png "")

右键点击或左键双击 IntelliJ IDEA 2022.1.2 进行卸载：
![卸载 IDEA](./images/image-20221019121258294.png "卸载 IDEA")

如果需要保留下述数据，就不要打 √；如果想彻底删除 IDEA 所有数据，那就打上 √。如下所示：
![选择是否彻底删除 IDEA 所有数据](./images/image-20221019122135368.png "选择是否彻底删除 IDEA 所有数据")

卸载过程：
![卸载过程 1](./images/image-20221019122323575.png "卸载过程 1")
![卸载过程 2](./images/image-20221019122349195.png "卸载过程 2")

软件卸载完以后，还需要删除其它几个位置的残留：
![残留文件位置 1](./images/image-20221019161147552.png "残留文件位置 1")
![残留文件位置 2](./images/image-20221019161226843.png "残留文件位置 2")

### 2.2 安装前的准备

![安装要求](./images/image-20221018110944516.png "安装要求")

安装要求如下：
* 64 位 Microsoft Windows 11、10、8；
* 最低 2 GB 可用 RAM，推荐 8 GB 系统总 RAM；
* 2.5 GB 硬盘空间，推荐 SSD；
* 最低屏幕分辨率 1024x768。

从安装上来看，IntelliJ IDEA 对硬件的要求似乎不是很高。可是在实际开发中并不是这样的，因为 IntelliJ IDEA 执行时会有大量的缓存、索引文件；所以如果你正在使用 Eclipse / MyEclipse，想通过 IntelliJ IDEA 来解决计算机的卡、慢等问题，这基本上是不可能的，本质上你应该对自己的硬件设备进行升级。

### 2.3 安装过程

1. 下载完安装包，双击直接安装：
![下载好的安装包](./images/image-20220606191800719.png "下载好的安装包")
2. 欢迎安装：
![IDEA 安装程序欢迎界面](./images/image-20220606191825728.png "IDEA 安装程序欢迎界面")
3. 是否删除电脑上低版本的 IDEA（如果有，可以选择忽略）：
![是否选择删除电脑上低版本的 IDEA](./images/image-20220606191838180.png "是否选择删除电脑上低版本的 IDEA")
    * 如果电脑上有低版本的IDEA，可以选择删除或保留；
    * 这里没有卸载旧版本，如果需要卸载，记得勾选下面的保留旧的设置和配置。
4. 选择安装目录：
![选择 IDEA 安装目录](./images/image-20220606191942308.png "选择 IDEA 安装目录")
    * 选择安装目录，目录中要避免中文和空格：
        ![IDEA 安装目录中要避免中文和空格](./images/image-20220606192004081.png "IDEA 安装目录中要避免中文和空格")
5. 创建桌面快捷图标等：
![勾选“创建桌面快捷图标”等](./images/image-20220606192053432.png "勾选“创建桌面快捷图标”等")
    * 确认是否与 `.java`、`.groovy`、`.kt` 格式文件进行关联，这里建议不关联。
6. 在【开始】菜单新建一个文件夹（这里需要确认文件夹的名称），来管理IDEA的相关内容：
![新建文件夹以管理 IDEA 的相关内容](./images/image-20220606192126130.png "新建文件夹以管理 IDEA 的相关内容")
![IDEA 安装等待过程](./images/image-20220606192135684.png "IDEA 安装等待过程")
7. 完成安装：
![完成 IDEA 安装](./images/image-20220606192148284.png "完成 IDEA 安装")

重启以后（建议重启后再使用），即可使用。

### 2.4 注册

首先需要在 JetBrains 官网注册账号；以在校大学生（或硕博生）的身份，可在 JetBrains 官网申请 IntelliJ IDEA 的免费使用额度。

## 三、HelloWorld 的实现

### 3.1 新建 Project - Class

选择“New Project”：
![选择“New Project”](./images/image-20221019174051967.png "选择“New Project”")

指定工程名、使用的 JDK 版本等信息，如下所示：
![指定工程名、使用的 JDK 版本等信息](./images/image-20221019174355370.png "指定工程名、使用的 JDK 版本等信息")

接着创建 Java 类：
![创建 Java 类](./images/image-20221019174505876.png "创建 Java 类")
![为新创建的 Java 类命名](./images/image-20221019174551606.png "为新创建的 Java 类命名")

### 3.2 编写代码

编写 HelloWorld 代码如下：
```java
/*
 * 这是多行注释
 * */


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        // 这是单行注释
        System.out.println("helloworld!");
        System.out.println("hello");

//        Math.random()

        Scanner scan = new Scanner(System.in);

        Map map = new HashMap();

        Date date = null;
    }

    public void method() {

    }
}

```

### 3.3 运行

运行流程如下所示：
![单击以运行](./images/image-20221019174716442.png "单击以运行")
![运行结果展示](./images/image-20221019174801370.png "运行结果展示")

## 四、JDK 相关设置

### 4.1 项目的 JDK 设置

设置路径：File -> Project Structure ... -> Platform Settings -> SDKs。

路径寻找如下：
![File -> Project Structure ...](./images/image-20221019174847921.png "")
![设置 JDK](./images/image-20221019175030852.png "设置 JDK")

> 注意：
> * SDKs 全称是 Software Development Kit，这里一定是选择 JDK 的安装根目录，而不是 JRE 的目录。
> * 这里还可以从本地添加多个 JDK，使用“+”即可实现。

### 4.2 out 目录和编译版本

设置路径：File -> Project Structure ... -> Project Settings -> Project。

详细设置如下：
![out 目录和编译版本的详细设置](./images/image-20221019175358200.png "out 目录和编译版本的详细设置")

## 五、详细设置

## 六、工程与模块管理

### 6.1 IDEA 项目结构

IDEA 项目的层级关系：project（工程）- module（模块）- package（包）- class（类）。

具体而言：
* 一个 project 中可以创建多个 module；
* 一个 module 中可以创建多个 package；
* 一个 package 中可以创建多个 class。

> 注意：这些结构的细分，是为了方便管理功能代码。

### 6.2 Project 和 Module 的概念

在 IntelliJ IDEA 中，提出了 Project 和 Module 这两个概念：
![IntelliJ IDEA 中 Project 和 Module 的概念示意图](./images/image-20220523014358169.png "IntelliJ IDEA 中 Project 和 Module 的概念示意图")

在 IntelliJ IDEA 中 Project 是最顶级的结构单元，然后就是 Module。目前，主流的大型项目结构基本都是多 Module 的结构，这类项目一般是按功能划分的，比如 user-core-module、user-facade-module 和 user-hessian-module 等等，模块之间彼此可以相互依赖，有着不可分割的业务关系。因此，对于一个 Project 来说：
* 当为单 Module 项目的时候，这个单独的 Module 实际上就是一个 Project；
* 当为多 Module 项目的时候，多个模块处于同一个 Project 之中，此时彼此之间具有互相依赖的关联关系；
* 当然多个模块没有建立依赖关系的话，也可以作为单独一个“小项目”运行。

### 6.3 Module 和 Package

在一个 module 下，可以声明多个包（package），一般命名规范如下：
1. 不要有中文；
2. 不要以数字开头；
3. 给包取名时一般都是公司域名倒着写，而且都是小写：
    * 比如：尚硅谷的网址是 www.atguigu.com，那么我们的 package 包名应该写成 `com.atguigu.子名字`。

### 6.4 创建 Module

建议创建“Empty 空工程”，然后创建多模块，每一个模块可以独立运行，相当于一个小项目。JavaSE 阶段不涉及到模块之间的依赖，后期再学习模块之间的依赖。

步骤：
1. 选择创建模块：
![选择创建模块](./images/1655167625885.png "选择创建模块")
2. 选择模块类型，这里选择创建 Java 模块，给模块命名，确定存放位置：
![选择模块类型](./images/1659191966074.png "选择模块类型")
3. 模块声明在工程下面：
![模块声明位置](./images/1659192028623.png "模块声明位置")

### 6.5 删除模块

步骤：
1. 移除模块：
![选择要删除的模块](./images/1659192150052.png "选择要删除的模块")
![确认移除模块](./images/1659192180062.png "确认移除模块")
2. 彻底删除模块：
![彻底删除模块](./images/1659192241224.png "彻底删除模块")

## 七、企业真题

**1. 开发中你接触过的开发工具都有哪些？**
答：
* IDEA。

**2. 谈谈你对 Eclipse 和 IDEA 使用上的感受？**
答：
* Eclipse 不够人性化。
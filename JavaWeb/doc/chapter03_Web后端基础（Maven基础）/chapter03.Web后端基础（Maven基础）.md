# 第三章：Web 后端基础（Maven 基础）

**目录：**

[TOC]

---

> 课程内容：
> * 初识 Maven。
> * Maven 概述。
>   * Maven 模型；
>   * Maven 仓库介绍；
>   * Maven 安装与配置。
> * IDEA 集成 Maven。
> * 依赖管理。
> * 单元测试。

## 一、初识 Maven

### 1.1 介绍

![Maven](./images/03_Maven.PNG "Maven")

Maven 是一款用于管理和构建 Java 项目的工具，是 Apache 旗下的一个开源项目。

> Apache 软件基金会，成立于 1999 年 7 月，是目前世界上最大的最受欢迎的开源软件基金会，也是一个专门为支持开源项目而生的非盈利性组织。
>
> 开源项目：[Apache 开源项目](https://www.apache.org/index.html#projects-list "Apache 开源项目")。

我们之前在 JavaSE 阶段，没有使用 Maven，依然可以构建 Java 项目。我们为什么现在还要学习 Maven 呢？那么接下来，我们就来聊聊 Maven 的作用。

### 1.2 Maven 的作用

![Maven 的作用](./images/03_Maven的作用.PNG "Maven 的作用")

#### 1.2.1 依赖管理

方便快捷地管理项目依赖的资源（jar 包），避免版本冲突问题。

1). 使用 Maven 前

我们项目中要想使用某一个 jar 包，就需要把这个 jar 包从官方网站下载下来，然后再导入到项目中。然后在这个项目中，就可以使用这个 jar 包了。

![使用 Maven 前对 jar 包的管理](./images/03_使用Maven前对jar包的管理.PNG "使用 Maven 前对 jar 包的管理")

2). 使用 Maven 后

当使用 Maven 进行项目依赖（jar 包）管理，则可以很方便地解决这个问题。我们只需要在 Maven 项目的 pom.xml 文件中，添加一段如下图所示的配置即可实现：
![使用 Maven 后对 jar 包的管理](./images/03_使用Maven后对jar包的管理.PNG "使用 Maven 后对 jar 包的管理")

在 Maven 项目的配置文件中，加入上面这么一段配置信息之后，Maven 会自动地根据配置信息的描述，去下载对应的依赖；然后在项目中，就可以直接使用了。

#### 1.2.2 项目构建

Maven 还提供了标准化的跨平台的自动化构建方式。

![项目代码的处理操作](./images/03_项目代码的处理操作.PNG "项目代码的处理操作")

如上图所示，我们开发了一套系统，代码需要经过编译、测试、打包、发布等过程，这些操作是所有项目中都需要做的，如果需要反复进行就显得特别麻烦；而 Maven 提供了一套简单的命令来完成项目构建。

![通过 Maven 提供的一套简单的命令来完成项目构建](./images/03_通过Maven提供的一套简单的命令来完成项目构建.png "通过 Maven 提供的一套简单的命令来完成项目构建")

通过 Maven 中的命令，就可以很方便地完成项目的编译（compile）、测试（test）、打包（package）、发布（deploy）等操作。

而且这些操作都是跨平台的。也就是说，无论是 Windows 系统，还是 Linux 系统，还是 Mac 系统，这些命令都是支持的。

#### 1.2.3 统一项目结构

Maven 还提供了标准、统一的项目结构。

1). 未使用 Maven

由于 Java 的开发工具有很多，除了大家熟悉的 IDEA 以外，还有像早期的 Eclipse、MyEclipse。而不同的开发工具创建出来的 Java 项目的目录结构是存在差异的，那这就会出现一个问题：Eclipse 创建的 Java 项目，并不能直接导入 IDEA 中；IDEA 创建的 Java 项目，也没有办法直接导入到 Eclipse 中。

![未使用 Maven 导致的项目结构差异问题](./images/03_未使用Maven导致的项目结构差异问题.png "未使用 Maven 导致的项目结构差异问题")

2). 使用 Maven

而如果我们使用了 Maven 这一款项目构建工具，它给我们提供了一套标准的 Java 项目目录。如下所示：
![Maven 提供的一套标准的 Java 项目目录](./images/03_Maven提供的一套标准的Java项目目录.png "Maven 提供的一套标准的 Java 项目目录")

也就意味着，无论我们使用的是什么开发工具，只要是基于 Maven 构建的 Java 项目，最终的目录结构都是相同的，如下图所示：
![基于 Maven 构建的 Java 项目的目录结构](./images/03_基于Maven构建的Java项目的目录结构.png "基于 Maven 构建的 Java 项目的目录结构")

那这样呢，我们使用 Eclipse、MyEclipse、IDEA 创建的 Maven 项目，就可以在各个开发工具直接导入使用了，更加方便、快捷。

而在上面的 Maven 项目的目录结构中，main 目录下存放的是项目的源代码，test 目录下存放的是项目的测试代码。而无论是在 main 还是在 test 下，都有两个目录：一个是 java，用来存放源代码文件；另一个是 resource，用来存放配置文件。

总结 - 什么是 Maven：**Maven 就是一款管理和构建 Java 项目的工具。**

Maven 的内容讲解分为两个部分：Maven 核心和 Maven 进阶。

今天，我们先来讲解 Maven 核心部分的内容。在 Web 开发的最后，我们再来讲解 Maven 进阶部分的内容。

## 二、Maven 概述

### 2.1 Maven 介绍

Apache Maven 是一个项目管理和构建工具，它基于项目对象模型（Project Object Model，简称：POM）的概念，通过一小段描述信息来管理项目的构建、报告和文档。

Maven 官网：[Maven 官网](https://maven.apache.org/ "Maven 官网")。

Maven 的作用：
1. 方便的依赖管理。
2. 统一的项目结构。
3. 标准的项目构建流程。

### 2.2 Maven 模型

* 项目对象模型（Project Object Model）。
* 依赖管理模型（Dependency）。
* 构建生命周期 / 阶段（Build lifecycle & phases）。

1). 构建生命周期 / 阶段（Build lifecycle & phases）

![Maven - 构建生命周期或阶段](./images/03_Maven-构建生命周期或阶段.png "Maven - 构建生命周期或阶段")

以上图中紫色框起来的部分，就是用来完成标准化构建流程。当我们需要编译，Maven 提供了一个编译插件供我们使用；当我们需要打包，Maven 就提供了一个打包插件供我们使用等。

2). 项目对象模型（Project Object Model）

![Maven - 项目对象模型](./images/03_Maven-项目对象模型.png "Maven - 项目对象模型")

以上图中紫色框起来的部分属于项目对象模型，就是将我们自己的项目抽象成一个对象模型，有自己专属的坐标。如下图所示是一个 Maven 项目：
![Maven 项目示例](./images/03_Maven项目示例.png "Maven 项目示例")

> 坐标，就是资源（jar 包）的唯一标识，通过坐标可以定位到所需资源（jar 包）位置。
>
> 坐标的组成部分：
> * groupId：组织名。
> * arfitactId：模块名。
> * version：版本号。

3). 依赖管理模型（Dependency）

![Maven - 依赖管理模型](./images/03_Maven-依赖管理模型.png "Maven - 依赖管理模型")

以上图中紫色框起来的部分属于依赖管理模型，是使用坐标来描述当前项目依赖哪些第三方 jar 包。

![Maven - 使用坐标来描述当前项目依赖哪些第三方 jar 包](./images/03_Maven-使用坐标来描述当前项目依赖哪些第三方jar包.png "Maven - 使用坐标来描述当前项目依赖哪些第三方 jar 包")

之前我们项目中需要 jar 包时，直接就把 jar 包复制到项目下的 lib 目录；而现在我们只需要在 pom.xml 中配置依赖的配置文件即可。而这个依赖对应的 jar 包其实就在我们本地电脑上的 Maven 仓库中。

如下图为示例本地的 Maven 仓库中的 jar 文件：
![示例本地的 Maven 仓库中的 jar 文件](./images/03_示例本地的Maven仓库中的jar文件.png "示例本地的 Maven 仓库中的 jar 文件")

### 2.3 Maven 仓库

仓库：用于存储资源，管理各种 jar 包。

> **仓库的本质**就是一个目录（文件夹），这个目录被用来存储开发中所有依赖（就是 jar 包）和插件。

Maven 仓库分为：
* 本地仓库：自己计算机上的一个目录（用来存储 jar 包）。
* 中央仓库：由 Maven 团队维护的全球唯一的仓库；仓库地址：[中央仓库地址](https://repo1.maven.org/maven2/ "中央仓库地址")。
* 远程仓库（私服）：一般由公司团队搭建的私有仓库。

![Maven 仓库](./images/03_Maven仓库.png "Maven 仓库")

当项目中使用坐标引入对应依赖 jar 包后：
1. 首先会查找本地仓库中是否有对应的 jar 包。
    * 如果有，则在项目中直接引用。
    * 如果没有，则去中央仓库中下载对应的 jar 包到本地仓库。
2. 如果还可以搭建远程仓库（私服），将来 jar 包的查找顺序则变为：本地仓库 -> 远程仓库 -> 中央仓库。

### 2.4 Maven 安装

开始使用 Maven，首先要进行 Maven 的下载与安装。

##### 2.4.1 下载

下载地址：[Maven 下载地址](https://maven.apache.org/download.cgi "Maven 下载地址")。

##### 2.4.2 安装步骤

Maven 安装配置步骤：
1. 解压安装。
2. 配置仓库。
3. 配置阿里云私服。
4. 配置 Maven 环境变量。

1). 解压安装

解压缩后的目录结构如下：
![Maven - 解压缩后的目录结构](./images/03_Maven-解压缩后的目录结构.png "Maven - 解压缩后的目录结构")

* bin 目录：存放的是可执行命令（mvn 命令，重点关注）。
* conf 目录：存放 Maven 的配置文件（settings.xml 配置文件后期需要修改）。
* lib 目录：存放 Maven 依赖的 jar 包（Maven 也是使用 Java 开发的，所以它也依赖其他的 jar 包）。

2). 配置本地仓库

1. 在自己计算机上新建一个目录（本地仓库，用来存储 jar 包）。

![Maven - 新建目录](./images/03_Maven-新建目录.png "Maven - 新建目录")

2. 进入到 conf 目录下修改 settings.xml 配置文件。
    1. 打开 settings.xml 文件，定位到 53 行。
    2. 复制 `<localRepository>` 标签，粘贴到注释的外面（55 行）。
    3. 复制之前新建的用来存储 jar 包的路径，替换掉 `<localRepository>` 标签体内容。

![Maven - 修改配置文件](./images/03_Maven-修改配置文件.png "Maven - 修改配置文件")

3). 配置阿里云私服

由于中央仓库在国外，所以下载 jar 包速度可能比较慢。而阿里公司提供了一个远程仓库，里面基本也都有开源项目的 jar 包。

进入到 conf 目录下修改 settings.xml 配置文件：
1. 打开 settings.xml 文件，定位到 160 行左右。
2. 在 `<mirrors>` 标签下为其添加子标签 `<mirror>`，内容如下：
    ```xml
    <mirror>
        <id>alimaven</id>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
    ```

注意配置的位置，在 `<mirrors> ... </mirrors>` 中间添加配置。如下图所示：
![Maven - 添加配置](./images/03_Maven-添加配置.png "Maven - 添加配置")

4). 配置环境变量

**Maven 环境变量的配置类似于 JDK 环境变量的配置一样。**

1. 在系统变量处新建一个变量 MAVEN_HOME；MAVEN_HOME 环境变量的值，设置为 Maven 的解压安装目录。

![新建变量 MAVEN_HOME](./images/03_新建变量MAVEN_HOME.png "新建变量 MAVEN_HOME")

2. 在 Path 中进行配置；PATH 环境变量的值，设置为：`%MAVEN_HOME%\bin`。

![配置 PATH 环境变量的值](./images/03_配置PATH环境变量的值.png "配置 PATH 环境变量的值")

3. 打开 DOS 命令提示符进行验证。

命令为 `mvn -v`。

5). 配置关联的 JDK 版本（可选）

进入到 conf 目录下修改 settings.xml 配置文件，在 `<profiles> ... </profiles>` 中增加如下配置：
```xml
<profile>
        <id>jdk-17</id>
        <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>17</jdk>
        </activation>
        <properties>
                <maven.compiler.source>17</maven.compiler.source>
                <maven.compiler.target>17</maven.compiler.target>
                <maven.compiler.compilerVersion>17</maven.compiler.compilerVersion>
        </properties>
</profile>
```

## 三、IDEA 集成 Maven

我们要想在 IDEA 中使用 Maven 进行项目构建，就需要在 IDEA 中集成 Maven；那么就需要在 IDEA 中配置与 Maven 的关联。

### 3.1 创建 Maven 项目

#### 3.1.1 全局设置

1). 进入 IDEA 的欢迎界面

选择 IDEA 中 File -> Close Project -> Customize -> All settings。

![全局设置 - 1 - 1](./images/03_全局设置-1-1.gif "全局设置 - 1 - 1")

![全局设置 - 1 - 2](./images/03_全局设置-1-2.png "全局设置 - 1 - 2")

2). 打开 All settings，选择 Build, Execution, Deployment -> Build Tools -> Maven

![全局设置 - 2](./images/03_全局设置-2.png "全局设置 - 2")

这里还需要在 Maven 下的 Runner 中配置 JDK 版本。

3). 配置工程的编译版本

![全局设置 - 3](./images/03_全局设置-3.png "全局设置 - 3")

> 注意：
>
> Project bytecode version 默认为 Same as language level。

这里所设置的 Maven 的环境信息，并未指定任何一个 Project，此时设置的信息就属于全局配置信息。以后，我们再创建 Project，默认就是使用我们全局配置的信息。

#### 3.1.2 创建项目

1). 创建一个空项目，并为之命名

![创建项目 - 1](./images/03_创建项目-1.png "创建项目 - 1")

2). 创建好项目之后，进入项目中，设置 JDK 的版本号

选择小齿轮，选择 Project Structure：
![创建项目 - 2](./images/03_创建项目-2.png "创建项目 - 2")

3). 创建模块，选择 Java 语言，选择 Maven，并填写模块的基本信息

![创建项目 - 3 - 1](./images/03_创建项目-3-1.png "创建项目 - 3 - 1")

![创建项目 - 3 - 2](./images/03_创建项目-3-2.png "创建项目 - 3 - 2")

4). 在 Maven 项目中，创建类，并运行

![创建项目 - 4](./images/03_创建项目-4.png "创建项目 - 4")

> Maven 项目的目录结构：
> ```
> maven-project01
>     |--- src（源代码目录和测试代码目录）
>         |--- main（源代码目录）
>             |--- java（源代码 Java 文件目录）
>             |--- resources（源代码配置文件目录）
>         |--- test（测试代码目录）
>             |--- java（测试代码 Java 目录）
>             |--- resources（测试代码配置文件目录）
>     |--- target（编译、打包生成文件存放目录）
> ```
>
> 以上为 Maven 项目的目录结构。

#### 3.1.3 pom 文件详解

POM（Project Object Model）：指的是项目对象模型，用来描述当前的 Maven 项目。

使用 pom.xml 文件来描述当前项目。pom.xml 文件如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- POM模型版本 -->
    <modelVersion>4.0.0</modelVersion>

    <!-- 当前项目坐标 -->
    <groupId>com.itheima</groupId>
    <artifactId>maven-project01</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <!-- 项目的JDK版本及编码 -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

pom 文件详解：
* `<project>`：pom 文件的根标签，表示当前 Maven 项目。
* `<modelVersion>`：声明项目描述遵循哪一个 POM 模型版本。
  * 虽然模型本身的版本很少改变，但它仍然是必不可少的；目前 POM 模型版本是 4.0.0。
* 坐标：
  * `<groupId>`、`<artifactId>`、`<version>`。
  * 定位项目在本地仓库中的位置，由以上三个标签组成一个坐标。
* `<maven.compiler.source>`：编译 JDK 的版本。
* `<maven.compiler.target>`：运行 JDK 的版本。
* `<project.build.sourceEncoding>`：设置项目的字符集。

### 3.2 Maven 坐标

什么是坐标？
* Maven 中的坐标是**资源（jar）的唯一标识**，通过该坐标可以唯一定位资源位置。
* 使用坐标来定义项目或引入项目中需要的依赖。

Maven 坐标主要组成：
* `groupId`：定义当前 Maven 项目隶属组织名称（通常是域名反写）。
* `artifactId`：定义当前 Maven 项目名称（通常是模块名称）。
* `version`：定义当前项目版本号。
  * `SNAPSHOT`：功能不稳定、尚处于开发中的版本，即快照版本。
  * `RELEASE`：功能趋于稳定、当前更新停止，可以用于发行的版本。

如下图就是使用坐标表示一个项目：
![使用坐标表示一个项目](./images/03_使用坐标表示一个项目.png "使用坐标表示一个项目")

> 注意：
> * 上面所说的的资源可以是插件、依赖、当前项目。
> * 我们的项目如果被其他的项目依赖时，也是需要坐标来引入的。

### 3.3 导入 Maven 项目

在 IDEA 中导入 Maven 项目，有两种方式。

> 注意：
>
> 建议将要导入的 Maven 源代码项目复制到当前自己的项目目录下，再执行以下两种方式的导入操作。

1). 方式一：File -> Project Structure -> Modules -> Import Module -> 选择 Maven 项目的 pom.xml。

![在 IDEA 中导入 Maven 项目 - 方式一](./images/03_在IDEA中导入Maven项目-方式一.png "在 IDEA 中导入 Maven 项目 - 方式一")

2). 方式二：Maven 面板 -> + (Add Maven Projects) -> 选择 Maven 项目的 pom.xml。

![在 IDEA 中导入 Maven 项目 - 方式二](./images/03_在IDEA中导入Maven项目-方式二.png "在 IDEA 中导入 Maven 项目 - 方式二")

## 四、依赖管理

### 4.1 依赖配置

#### 4.1.1 基本配置

依赖：指当前项目中运行所需要的 jar 包；一个项目中可以引入多个依赖。

例如：在当前
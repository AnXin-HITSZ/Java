# 第四章：Web 后端基础（基础知识）

**目录：**

[TOC]

---

> 课程内容：
> * SpringBootWeb 入门。
> * HTTP 协议。
> * SpringBootWeb 案例。
> * 分层解耦。

前端页面资料可以部署在服务器上，然后打开浏览器就可以直接访问服务器上部署的前端页面了。

![前端页面资料的部署与访问](./images/04_前端页面资料的部署与访问.PNG "前端页面资料的部署与访问")

而像 HTML、CSS、JS 以及图片、音频、视频等这些资源，我们都称为**静态资源**。所谓静态资源，就是指在服务器上存储的不会改变的数据，通常不会根据用户的请求而变化。

与静态资源对应的还有一类资源，就是动态资源。所谓动态资源，就是指在服务器端上存储的、会根据用户请求和其他数据动态生成的，内容可能会在每次请求时都发生变化，例如 Servlet、JSP等（负责逻辑处理）。而 Servlet、JSP 这些技术现在早都被企业淘汰了，现在在企业项目开发中，都是直接基于 Spring 框架来构建动态资源。

![动态资源与静态资源](./images/04_动态资源与静态资源.PNG "动态资源与静态资源")

而对于我们 Java 程序开发的动态资源来说，我们通常会将这些动态资源部署在 Tomcat 这样的 Web 服务器中运行。而浏览器与服务器在通信的时候，基本都是基于 HTTP 协议的。

![动态资源的部署](./images/04_动态资源的部署.PNG "动态资源的部署")

以上所描述的这种 **浏览器 / 服务器** 的架构模式，我们称之为：**BS 架构**。
* BS 架构：Browser / Server，浏览器  / 服务器架构模式。客户端只需要浏览器，应用程序的逻辑和数据都存储在服务端。
* CS 架构：Client / Server，客户端 / 服务器架构模式。需要单独开发维护客户端。

## 一、SpringBootWeb 入门

接下来，我们就要来讲解现在企业开发的主流技术 SpringBoot，并基于 SpringBoot 进行 Web 程序的开发。

### 1.1 概述

在正式学习 SpringBoot 之前，我们要先来了解一下什么是 Spring。

Spring 官网：[Spring 官网](https://spring.io "Spring 官网")。

Spring 家族旗下的技术中，最基础、最核心的是 SpringFramework；其他的 Spring 家族的技术，都是基于 SpringFramework 的。SpringFramework 中提供很多使用功能，如：依赖注入、事务管理、Web 开发支持、数据访问、消息服务等等。

![SpringFramework 作为 Spring 家族旗下技术中最基础、最核心的技术](./images/04_SpringFramework作为Spring家族旗下技术中最基础、最核心的技术.png "SpringFramework 作为 Spring 家族旗下技术中最基础、最核心的技术")

Spring 官方推荐我们从 SpringBoot 项目开始学习，通过 SpringBoot 可以帮助我们快速构建应用程序。

SpringBoot 可以帮助我们非常快速地构建应用程序、简化开发、提高效率。而直接基于 SpringBoot 进行项目构建和开发，不仅是 Spring 官方推荐地方式，也是现在企业开发的主流。

### 1.2 入门程序

#### 1.2.1 需求

需求：基于 SpringBoot 的方式开发一个 Web 应用，浏览器发起请求 `/hello` 后，给浏览器返回字符串 “`Hello xxx ~`”。

![入门程序 - 需求](./images/04_入门程序-需求.png "入门程序 - 需求")

#### 1.2.2 开发步骤

第 1 步：创建 SpringBoot 工程，并勾选 Web 开发相关依赖。
第 2 步：定义 `HelloController` 类，添加方法 `hello`，并添加注解。

1). 创建 SpringBoot 工程（需要联网）

基于 Spring 官方骨架，创建 SpringBoot 工程：
![开发步骤 - 创建 SpringBoot 工程](./images/04_开发步骤-创建SpringBoot工程.png "开发步骤 - 创建 SpringBoot 工程")

基本信息描述完毕之后，勾选 Web 开发相关依赖：
![开发步骤 - 勾选 Web 开发相关依赖](./images/04_开发步骤-勾选Web开发相关依赖.png "开发步骤 - 勾选 Web 开发相关依赖")

> 注意：
>
> SpringBoot 官方提供的脚手架，里面只能够选择 SpringBoot 的几个最新的版本；如果要选择其他相对低一点的版本，可以在 SpringBoot 项目创建完毕之后，修改项目的 pom.xml 文件中的版本号。

点击 Create 之后，就会联网创建这个 SpringBoot 工程。

创建好之后，结构如下：
![SpringBoot 工程结构](./images/04_SpringBoot工程结构.png "SpringBoot 工程结构")

2). 定义 `HelloController` 类，添加方法 `hello`，并添加注解

在 com.xxx（例如：com.itheima；取决于你自己的域名及包名）这个包下新建一个类 `HelloController`：
![开发步骤 - 新建类](./images/04_开发步骤-新建类.png "开发步骤 - 新建类")

`HelloController` 中的内容，具体如下：
```java
package com.itheima;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 标识当前类是一个请求处理类
public class HelloController {

    @RequestMapping("/hello")   // 标识请求路径
    public String hello(String name){
        System.out.println("HelloController ... hello: " + name);
        return "Hello " + name;
    }

}
```

3). 运行测试

运行 SpringBoot 自动生成的引导类（标识有 `@SpringBootApplication` 注解的类）：
![开发步骤 - 运行引导类](./images/04_开发步骤-运行引导类.png "开发步骤 - 运行引导类")

打开浏览器，输入 `http://localhost:8080/hello?name=itheima`：
![开发步骤 - 运行测试](./images/04_开发步骤-运行测试.png "开发步骤 - 运行测试")

#### 1.2.3 常见问题

在联网基于 Spring 的脚手架创建 SpringBoot 项目，偶尔可能会因为网络的原因，无法链接到 SpringBoot 的脚手架网站。此时会出现如下现象：
![由于网络原因无法链接到 SpringBoot 的脚手架网站](./images/04_由于网络原因无法链接到SpringBoot的脚手架网站.png "由于网络原因无法链接到 SpringBoot 的脚手架网站")

此时可以使用阿里云提供的脚手架，网址为：[阿里云提供的脚手架 - 网址](https://start.aliyun.com "阿里云提供的脚手架 - 网址")。如下图所示：
![使用阿里云提供的脚手架](./images/04_使用阿里云提供的脚手架.png "使用阿里云提供的脚手架")

然后按照项目创建的向导，一步一步地创建项目即可。

### 1.3 入门解析

在上面，我们已经完成了 SpringBootWeb 的入门程序，并且测试通过。在入门程序中，我们发现，我们只需要一个 `main` 方法就可以将 Web 应用启动起来，然后就可以打开浏览器访问了。

那么接下来，我们需要明确两个问题。

1). 为什么一个 `main` 方法就可以将 Web 应用启动了？

![入门程序 - 项目结构](./images/04_入门程序-项目结构.png "入门程序 - 项目结构")

因为我们在创建 SpringBoot 项目的时候，选择了 Web 开发的起步依赖 spring-boot-starter-web。而 spring-boot-starter-web 依赖又依赖了 spring-boot-starter-tomcat，由于 Maven 的依赖传递特性，那么在我们创建的 SpringBoot 项目中也就已经有了 tomcat 的依赖，这个其实就是 SpringBoot 中内嵌的 tomcat。

![SpringBoot 项目中的依赖](./images/04_SpringBoot项目中的依赖.png "SpringBoot 项目中的依赖")

而我们运行引导类中的 `main` 方法，其实启动的就是 SpringBoot 中内嵌的 Tomcat 服务器；而我们所开发的项目，也会自动地部署在该 Tomcat 服务器中，并占用 8080 端口号。

![SpringBoot 内嵌 Tomcat](./images/04_SpringBoot内嵌Tomcat.png "SpringBoot 内嵌 Tomcat")

> **起步依赖：**
> * 一种为开发者提供简化配置和集成的机制，使得构建 Spring 应用程序更加轻松。起步依赖本质上是一组预定义的依赖项集合，它们一起提供了在特定场景下开发 Spring 应用所需的所有库和配置。
>   * spring-boot-starter-web：包含了 Web 应用开发所需要的常见依赖。
>   * spring-boot-starter-test：包含了单元测试所需要的常见依赖。
> * 官方提供的 starter：[官方提供的 starter](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/#using.build-systems.starters "官方提供的 starter")。

## 二、HTTP 协议

### 2.1 HTTP 概述

#### 2.1.1 介绍

![HTTP 协议应用场景 - 请求与响应](./images/04_HTTP协议应用场景-请求与响应.png "HTTP 协议应用场景 - 请求与响应")

**HTTP**：**H**yper **T**ext **T**ransfer **P**rotocol（超文本传输协议），规定了浏览器与服务器之间数据传输的规则。
* HTTP 是互联网上应用最为广泛的一种网络协议。
* HTTP 协议要求：浏览器在向服务器发送请求数据时，或是服务器在向浏览器发送响应数据时，都必须按照固定的格式进行数据传输。

如果想知道 HTTP 协议的数据传输格式有哪些，可以打开浏览器，通过按键 `F12` 打开开发者工具，点击 Network（网络）来查看：
![浏览器 - 开发者工具 - Network（网络）](./images/04_浏览器-开发者工具-Network（网络）.png "浏览器 - 开发者工具 - Network（网络）")

浏览器向服务器进行请求时，服务器按照固定的格式进行解析：
![服务器对请求进行解析](./images/04_服务器对请求进行解析.png "服务器对请求进行解析")

服务器向浏览器进行响应时，浏览器按照固定的格式进行解析：
![浏览器对响应进行解析](./images/04_浏览器对响应进行解析.png "浏览器对响应进行解析")

而我们学习 HTTP 协议，就是来学习请求和响应数据的具体格式内容。

#### 2.1.2 特点

HTTP 协议的特点：
* 基于 **TCP** 协议：面向连接，安全。

> TCP 是一种面向连接的（建立连接之前是需要经过三次握手）、可靠的、基于字节流的传输层通信协议，在数据传输方面更安全。

* 基于 **请求 - 响应** 模型：一次请求对应一次响应（先请求后响应）。

> 请求和响应是一一对应关系；没有请求，就没有响应。

* HTTP 协议是无状态协议：对于数据没有记忆能力，每次请求 - 响应都是独立的。

> 无状态指的是客户端发送 HTTP 请求给服务端之后，服务端根据请求响应数据；响应完后，不会记录任何信息。
> * 缺点：多次请求间不能共享数据。
> * 优点：速度快。
>
> 请求之间无法共享数据会引发问题。
> 
> 如京东购物，加入购物车和去购物车结算是两次请求。由于 HTTP 协议的无状态特性，加入购物车请求响应结束后，并未记录加入购物车的是何商品；发起去购物车结算的请求后，因为无法获取哪些商品加入了购物车，会导致此次请求无法正确展示数据。
>
> 具体使用的时候，我们发现京东是可以正常展示数据的。原因是 Java 早已考虑到这个问题，并提出了使用会话技术（Cookie、Session）来解决这个问题。

HTTP 协议规定了请求和响应数据的格式，那具体的格式是什么呢？

HTTP 协议又分为：**请求协议**和**响应协议**。我们分别详细剖析这两种协议的具体格式。

### 2.2 HTTP 请求协议

#### 2.2.1 介绍

**请求协议：** 浏览器将数据以请求格式发送到服务器，包括：**请求行**、**请求头**、**请求体**。

**GET 方式的请求协议：**

![GET 方式的请求协议](./images/04_GET方式的请求协议.png "GET 方式的请求协议")

请求行（上图中红色部分）：HTTP 请求中的第一行数据，由 请求方式、资源路径、协议/版本 组成（之间使用空格分隔）。
* 请求方式：GET。
* 资源路径：`/brand/findAll?name=OPPO&status=1`。
  * 请求路径：`/brand/findAll`。
  * 请求参数：`name=OPPO&status=1`。
    * 请求参数是以 `key=value` 形式出现。
    * 多个请求参数之间使用 `&` 连接。
  * 请求路径和请求参数之间使用 `?` 连接。
* 协议/版本：HTTP/1.1。

请求头（上图中黄色部分）：第二行开始，上图黄色部分内容就是请求头，格式为 `key: value` 形式。
* HTTP 是个无状态的协议，所以在请求头设置浏览器的一些自身信息和想要响应的形式，这样服务器在收到信息后，就可以知道是谁、想要干什么了。
* 常见的 HTTP 请求头有：
    | 请求头 | 含义 |
    | :--: | :--: |
    | Host | 表示请求的主机名 |
    | User-Agent | 浏览器版本；例如：Chrome 浏览器的标识类似 `Mozilla/5.0 ... Chrome/79`，IE 浏览器的标识类似 `Mozilla/5.0 (Windows NT ...)like Gecko` |
    | Accept | 表示浏览器能接收的资源类型；例如：`text/*`、`image/*` 或者 `*/*`（表示所有） |
    | Accept-Language | 表示浏览器偏好的语言，服务器可以据此返回不同语言的网页 |
    | Accept-Encoding | 表示浏览器可以支持的压缩类型；例如：`gzip`、`deflate` 等 |
    | Content-Type | 请求主体的数据类型 |
    | Content-Length | 数据主体的大小（单位：字节） |

> 举例说明：服务端可以根据请求头中的内容来获取客户端的相关信息；有了这些信息，服务端就可以处理不同的业务需求。
>
> 例如：
> * 不同浏览器解析 HTML 和 CSS 标签的结果会有不一致，所以就会导致相同的代码在不同的浏览器会出现不同的效果。
> * 服务端根据客户端请求头中的数据获取到客户端的浏览器类型，就可以根据不同的浏览器设置不同的代码，来达到一致的效果。（这就是我们常说的浏览器兼容问题。）

请求体：存储请求参数。
* GET 请求的请求参数在请求行中，故不需要设置请求体。

**POST 方式的请求协议：**

![POST 方式的请求协议](./images/04_POST方式的请求协议.png "POST 方式的请求协议")

请求行（上图中红色部分）：包含请求方式、资源路径、协议/版本。
* 请求方式：POST。
* 资源路径：`/brand`。
* 协议/版本：HTTP/1.1。

请求头（上图中黄色部分）。

请求体（上图中绿色部分）：存储请求参数。
* 请求体和请求头之间是有一个空行隔开（作用：用于标记请求头结束）。

GET 请求和 POST 请求的区别：
| 区别方式 | GET 请求 | POST 请求 |
| :--: | :--: | :--: |
| 请求参数 | 请求参数在请求行中<br>例如：`/brand/findAll?name=OPPO&status=1` | 请求参数在请求体中 |
| 请求参数长度 | 请求参数长度有限制（浏览器不同，限制也不同） | 请求参数长度没有限制 |
| 安全性 | 安全性低<br>原因：请求参数暴露在浏览器地址栏中 | 安全性相对高 |

#### 2.2.2 获取请求数据

Web 服务器（Tomcat）对 HTTP 协议的请求数据进行解析，并进行了封装（HttpServletRequest），并在调用 Controller 方法的时候传递给了该方法。这样，就使得程序员不必直接对协议进行操作，让 Web 开发更加便捷。

![Tomcat 对 HTTP 协议的请求数据进行解析与封装](./images/04_Tomcat对HTTP协议的请求数据进行解析与封装.png "Tomcat 对 HTTP 协议的请求数据进行解析与封装")

示例代码：
```java
/* RequestController.java */

package com.anxin_hitsz;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: RequestController
 * Package: com.anxin_hitsz
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 17:46
 * @Version 1.0
 */
@RestController
public class RequestController {

    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        // 1. 获取请求方式
        String method = request.getMethod();    // GET
        System.out.println("请求方式：" + method);

        // 2. 获取请求 url 地址
        String url = request.getRequestURI().toString();    // http://localhost:8080/request
        System.out.println("请求 url 地址：" + url);
        String uri = request.getRequestURI();   // /request
        System.out.println("请求 uri 地址：" + uri);

        // 3. 获取请求协议
        String protocol = request.getProtocol();    // HTTP/1.1
        System.out.println("请求协议：" + protocol);

        // 4. 获取请求参数 - name、age
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name：" + name + "；age：" + age);

        // 5. 获取请求头 - Accept
        String accept = request.getHeader("Accept");
        System.out.println("Accept：" + accept);

        return "OK";
    }

}

```
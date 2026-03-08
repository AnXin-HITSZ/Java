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

//        int i = 1 / 0;    // 响应状态码：500

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

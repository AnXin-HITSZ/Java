package com.anxin_hitsz;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * ClassName: ResponseController
 * Package: com.anxin_hitsz
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 19:55
 * @Version 1.0
 */
@RestController
public class ResponseController {

    /**
     * 方式一：HttpServletResponse 设置响应数据
     */
    @RequestMapping("/response1")
    public void response1(HttpServletResponse response) throws IOException {
        // 1. 设置响应状态码
        response.setStatus(401);

        // 2. 设置响应头
        response.setHeader("name", "itheima");

        // 3. 设置响应体
        response.getWriter().write("<h1>hello response</h1>");

    }

    /**
     * 方式二：ResponseEntity -> Spring 中提供的方式
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2() {
        return ResponseEntity
                .status(401)   // 响应状态码
                .header("name", "javaweb-ai")   // 响应头
                .body("<h1>hello response</h1>");   // 响应体
    }

}

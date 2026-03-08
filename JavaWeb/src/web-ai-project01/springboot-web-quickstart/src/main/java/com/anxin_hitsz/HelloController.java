package com.anxin_hitsz;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Package: com.anxin_hitsz
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/4 16:15
 * @Version 1.0
 */
@RestController // 表示当前类是一个请求处理类
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("name : " + name);
        return "Hello " + name + " ~";
    }

}

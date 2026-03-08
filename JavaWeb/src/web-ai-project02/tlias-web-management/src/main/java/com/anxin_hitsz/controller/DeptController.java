package com.anxin_hitsz.controller;

import com.anxin_hitsz.pojo.Dept;
import com.anxin_hitsz.pojo.Result;
import com.anxin_hitsz.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: DeptController
 * Package: com.anxin_hitsz.controller
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/8 13:23
 * @Version 1.0
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
//    @RequestMapping(value = "/depts", method = RequestMethod.GET) // method：指定请求方式
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据 ID 删除部门 - 简单参数接收：方式一（HttpServletRequest 获取请求参数）
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//
//        System.out.println("根据 ID 删除部门：" + id);
//        return Result.success();
//    }

    /**
     * 根据 ID 删除部门 - 简单参数接收：方式二（通过 @RequestParam 注解获取请求参数）
     * 注意事项：一旦声明了 @RequestParam，该参数在请求时必须传递，如果不传递将会报错（默认 required 为 true）
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
//        System.out.println("根据 ID 删除部门：" + deptId);
//        return Result.success();
//    }

    /**
     * 根据 ID 删除部门 - 简单参数接收：方式三（省略 @RequestParam - 前端传递的请求参数名与服务端方法形参名一致）
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("根据 ID 删除部门：" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门：" + dept);
        deptService.add(dept);
        return Result.success();
    }

}

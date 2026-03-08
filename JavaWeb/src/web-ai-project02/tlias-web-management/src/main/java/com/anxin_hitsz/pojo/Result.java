package com.anxin_hitsz.pojo;

import lombok.Data;

/**
 * ClassName: Result
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/8 13:17
 * @Version 1.0
 */

/**
 * 后端统一返回结果
 */
@Data
public class Result {

    private Integer code;   // 编码：1 - 成功；0 - 失败
    private String msg; // 错误信息
    private Object data;    // 数据

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}

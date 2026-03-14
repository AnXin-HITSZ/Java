package com.anxin_hitsz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: JobOption
 * Package: com.anxin_hitsz.pojo
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/14 13:04
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {

    private List jobList;  // 职位列表
    private List dataList;  // 数据列表

}

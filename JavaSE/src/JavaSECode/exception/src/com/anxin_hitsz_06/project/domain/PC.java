package com.anxin_hitsz_06.project.domain;

/**
 * ClassName: PC
 * Package: com.anxin_hitsz_06.project.domain
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 19:08
 * @Version 1.0
 */
public class PC implements Equipment {
    private String model;   // 机器的型号
    private String display; // 显示器名称

    public PC() {
    }

    public PC(String model, String sidpaly) {
        this.model = model;
        this.display = sidpaly;
    }

    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }

}

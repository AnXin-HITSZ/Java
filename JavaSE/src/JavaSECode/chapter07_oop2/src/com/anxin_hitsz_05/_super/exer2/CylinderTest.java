package com.anxin_hitsz_05._super.exer2;

/**
 * ClassName: CylinderTest
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:10
 * @Version 1.0
 */
public class CylinderTest {
    public static void main(String[] args) {

        Cylinder cy =  new Cylinder();

        cy.setRadius(2.3);
        cy.setLength(1.4);

        System.out.println("圆柱的体积为：" + cy.findVolume());

        System.out.println("圆柱的表面积：" + cy.findArea());

    }
}

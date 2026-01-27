package com.anxin_hitsz_05._super.exer1;

/**
 * ClassName: Kids
 * Package: com.anxin_hitsz_03._extends.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 15:59
 * @Version 1.0
 */
public class Kids extends ManKind {
    private int yearsOld;

    public Kids() {

    }

    public Kids(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public Kids(int sex, int salary, int yearsOld) {
        this.yearsOld = yearsOld;
        setSex(sex);
        setSalary(salary);
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void printAge() {
        System.out.println("I am " + yearsOld + " years old.");
    }

    public void employeed() {
        super.employeed();
        System.out.println("but Kids should study and no job.");
    }
}

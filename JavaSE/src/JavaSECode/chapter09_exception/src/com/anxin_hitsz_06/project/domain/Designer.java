package com.anxin_hitsz_06.project.domain;

/**
 * ClassName: Designer
 * Package: com.anxin_hitsz_06.project.domain
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 19:19
 * @Version 1.0
 */
public class Designer extends Programmer {
    private double bonus;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return getDetails() + "\t设计师\t" + getStatus() + "\t"
                + getBonus() + "\t\t\t" + getEquipment().getDescription();
    }

    public String getDetailsForTeam() {
        return getBasicDetailsForTeam() + "设计师\t" + getBonus();
    }
}

package com.anxin_hitsz_08._interface.exer3;

/**
 * ClassName: VehicleTest
 * Package: com.anxin_hitsz_08._interface.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:26
 * @Version 1.0
 */
public class VehicleTest {
    public static void main(String[] args) {

        Developer developer = new Developer();

        // 创建三个交通工具，保存在数组中
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Bicycle("捷安特", "红色");
        vehicles[1] = new ElectricVehicle("雅迪", "蓝色");
        vehicles[2] = new Car("奔驰", "黑色", "沪Au888");

        for (int i = 0; i < vehicles.length; i++) {
            developer.takingVehicle(vehicles[i]);

            if (vehicles[i] instanceof IPower) {
                ((IPower) vehicles[i]).power();
            }
        }

    }
}

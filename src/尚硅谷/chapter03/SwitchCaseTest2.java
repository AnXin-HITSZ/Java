import java.util.Scanner;

public class SwitchCaseTest2 {
    public static void main(String[] args) {
        // 1. 使用 Scanner，从键盘获取 2023 年的 month、day
        Scanner input = new Scanner(System.in);

        System.out.println("请输入 2023 年的月份：");
        int month = input.nextInt();    // 阻塞式方法

        System.out.println("请输入 2023 年的天：");
        int day = input.nextInt();    // 阻塞式方法

        // 假设用户输入的数据是合法的；后期我们在开发中，使用正则表达式进行校验

        // 2. 使用 switch - case 实现分支结构
        int sumDays = 0;    // 记录总天数
        switch (month) {
            case 12:
                sumDays += 30;  // 30：11 月份的总天数
            case 11:
                sumDays += 31;  // 31：10 月份的总天数
            case 10:
                sumDays += 30;  // 30：9 月份的总天数
            case 9:
                sumDays += 31;  // 31：8 月份的总天数
            case 8:
                sumDays += 31;  // 31：7 月份的总天数
            case 7:
                sumDays += 30;  // 30：6 月份的总天数
            case 6:
                sumDays += 31;  // 31：5 月份的总天数
            case 5:
                sumDays += 30;  // 30：4 月份的总天数
            case 4:
                sumDays += 31;  // 31：3 月份的总天数
            case 3:
                sumDays += 28;  // 28：2 月份的总天数
            case 2:
                sumDays += 31;  // 31：1 月份的总天数
            case 1:
                sumDays += day;
                break;
        }

        System.out.println("2023 年" + month + "月" + day + "日是当前的第" + sumDays + "天");

        input.close();  // 为了防止内存泄漏
    }
}
import java.util.Scanner;
class DoWhileTest1 {
    public static void main(String[] args) {
        // 1. 定义 balance 的变量，记录账户余额
        double balance = 0.0;

        boolean flag = true;    // 控制循环的结束

        Scanner scan = new Scanner(System.in);  // 实例化 Scanner

        // 2. 生成 ATM 取款的界面
        do {
            // 2. 生成 ATM 取款的界面
            System.out.println("========= ATM =========");
            System.out.println("  1、存款");
            System.out.println("  2、取款");
            System.out.println("  3、显示余额");
            System.out.println("  4、退出");
            System.out.println("请选择 (1 - 4)：");

            // 3. 使用 Scanner 获取用户的选择
            int selection = scan.nextInt();

            switch (selection) {
                // 4. 根据用户的选择，决定执行存款、取款、显示余额、退出的操作
                case 1:
                    System.out.println("请输入存款的金额：");
                    double money1 = scan.nextDouble();
                    if (money1 > 0) {
                        balance += money1;
                    }
                    break;
                case 2:
                    System.out.println("请输入取款的金额：");
                    double money2 = scan.nextDouble();
                    if (money2 > 0 && money2 <= balance) {
                        balance -= money2;
                    } else {
                        System.out.println("输入的数据有误或余额不足");
                    }
                    break;
                case 3:
                    System.out.println("账户余额为：" + balance);
                    break;
                case 4:
                    flag = false;
                    System.out.println("感谢使用，欢迎下次光临 ^_^");
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    //break;
            }
        } while (flag);

        // 关闭资源
        scan.close();
    }
}
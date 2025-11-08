import java.util.Scanner;
class ForWhileTest1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int positiveCount = 0;  // 记录正数的个数
        int negativeCount = 0;  // 记录负数的个数

        while (true) {
            System.out.println("请输入一个整数（输入为 0 时结束程序）：");
            int num = scan.nextInt();   // 获取用户输入的整数

            if (num > 0) {  // 正数
                positiveCount++;
            } else if (num < 0) {   // 负数
                negativeCount++;
            } else {    // 零
                System.out.println("程序结束");
                break;
            }
        }

        System.out.println("正数的个数为：" + positiveCount);
        System.out.println("负数的个数为：" + negativeCount);

        scan.close();
    }
}

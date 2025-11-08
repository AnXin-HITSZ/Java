import java.util.Scanner;
class WhileTest1 {
    public static void main(String[] args) {
        // 1. 生成一个 [1, 100] 范围的随机整数
        int random = (int)(Math.random() * 100) + 1;

        // 2. 使用 Scanner，从键盘获取数据
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入 1 - 100 范围的一个整数：");
        int guess = scan.nextInt();

        // 3. 声明一个变量，记录猜的次数
        int guessCount = 1;

        // 4. 使用循环结构，进行多次循环的对比和获取数据
        while (random != guess) {
            if (guess > random) {
                System.out.println("你输入的数据大了");
            } else if (guess < random) {
                System.out.println("你输入的数据小了");
            }// else {
            //    break;
            //}

            System.out.println("请输入 1 - 100 范围的一个整数：");
            guess = scan.nextInt();
            guessCount++;
        }

        // 能结束循环，就意味着 random 和 guess 相等了
        System.out.println("恭喜你！猜对了！");
        System.out.println("共猜了 " + guessCount + " 次");

        scan.close();
    }
}
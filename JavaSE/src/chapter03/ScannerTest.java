// 步骤 1：导包 import java.util.Scanner;
import java.util.Scanner;
class ScannerTest {
    public static void main(String[] args) {
        // 步骤 2：提供（或创建）一个 Scanner 类的实例
        Scanner scan = new Scanner(System.in);  // System.in 默认代表键盘输入
        System.out.println("欢迎光临 你来我往 交友网");
        // 步骤 3：根据提示，调用 Scanner 类的方法，获取不同类型的变量
        System.out.print("请输入你的网名：");
        String name = scan.next();
        System.out.print("请输入你的年龄：");
        int age = scan.nextInt();
        System.out.print("请输入你的体重：");
        double weight = scan.nextDouble();
        System.out.print("你是否单身（单身 true，不单身 false）：");
        boolean isSingle = scan.nextBoolean();
        System.out.print("请输入你的性别：（男 \\ 女）");
        char gender = scan.next().charAt(0);    // 先按照字符串接收，然后再取字符串的第一个字符（下标为 0）

        System.out.println("网名：" + name + "，年龄：" + age + "，体重：" + weight + "，是否单身：" + isSingle + "，性别：" + gender);
        System.out.println("注册完成，欢迎继续进入体验！");

        // 步骤 4：关闭资源，调用 Scanner 类的 close()
        scan.close();
    }
}
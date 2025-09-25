/*
测试运算符的使用 5：位运算符
*/
class BitTest {
    public static void main(String[] args) {
        int num1= 7;
        System.out.println("num1 << 1：" + (num1 << 1));
        System.out.println("num1 << 2：" + (num1 << 2));
        System.out.println("num1 << 3：" + (num1 << 3));
        System.out.println("num1 << 3：" + (num1 << 28));
        System.out.println("num1 << 3：" + (num1 << 29));   // 过犹不及

        int num2= -7;
        System.out.println("num2 << 1：" + (num2 << 1));
        System.out.println("num2 << 2：" + (num2 << 2));
        System.out.println("num2 << 3：" + (num2 << 3));

        System.out.println(~9);
        System.out.println(~-10);
    }
}
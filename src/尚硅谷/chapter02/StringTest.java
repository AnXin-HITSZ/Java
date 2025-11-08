/*
基本数据类型与 String 的运算
*/
class StringTest {
    public static void main(String[] args) {
        String str1 = "Hello World!";
        System.out.println(str1);

        // 测试连接运算
        int num1 = 10;
        boolean b1 = true;
        String str4 = "hello";

        System.out.println(str4 + b1);

        String str5 = str4 + b1;
        String str6 = str4 + b1 + num1;
        System.out.println(str6);   // hellotrue10

        String s = "123";
        int num = Integer.parseInt(s);
        System.out.println(num + 1);

        System.out.println('*' + '\t' + "*"); // 输出：51*
        System.out.println('*' + '\t' + '*'); // 输出：93
    }
}
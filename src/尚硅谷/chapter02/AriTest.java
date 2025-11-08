/*
测试运算符的使用 1：算数运算符的使用
*/
class AriTest {
    public static void main(String[] args) {
        /* 除法：/ */
        int m1 = 12;
        int n1 = 5;
        int k1 = m1 / n1;
        System.out.println(k1); // 2
        System.out.println(m1 / n1 * n1);   // 10

        /* 取模（或取余）：% */
        int i1 = 12;
        int j1 = 5;
        System.out.println(i1 % j1);    // 2
        // 开发中，经常用来判断某个数 num1 能整除另外一个数 num2：num1 % num2 == 0
        // 比如：判断 num1 是否是偶数：num1 % 2 == 0

        // 结论：取模以后，结果与被模数的符号相同
        int i2 = -12;
        int j2 = 5;
        System.out.println(i2 % j2);    // -2

        int i3 = 12;
        int j3 = -5;
        System.out.println(i3 % j3);    // 2

        int i4 = -12;
        int j4 = -5;
        System.out.println(i4 % j4);    // -2
    }
}
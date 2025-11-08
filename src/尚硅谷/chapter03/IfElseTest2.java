/*
测试 if - else 的嵌套使用

案例：
*/
class IfElseTest2 {
    public static void main(String[] args) {
        int num1 = 30;
        int num2= 21;
        int num3 = 44;

        if (num1 >= num2) {
            if (num3 >= num1) {
                System.out.println(num2 + "，" + num1 + "，" + num3);
            } else if (num3 <= num2) {
                System.out.println(num3 + "，" + num2 + "，" + num1);
            } else {
                System.out.println(num2 + "，" + num3 + "，" + num1);
            }
        } else {    // num1 < num2
            if (num3 >= num2) {
                System.out.println(num1 + "，" + num2 + "，" + num3);
            } else if (num3 <= num1) {
                System.out.println(num3 + "，" + num1 + "，" + num2);
            } else {
                System.out.println(num1 + "，" + num3 + "，" + num2);
            }
        }
    }
}

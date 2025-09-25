/*
测试运算符的使用 4：逻辑运算符
*/
class LogicTest {
    public static void main(String[] args) {
        boolean b3 = true;
        int num3 = 10;
        if (b3 | (num3++ > 0)) {
        System.out.println("床前明月光");
        } else {
        System.out.println("我叫郭德纲");
        }
        System.out.println("num3 = " + num3); // 11

        boolean b4 = true;
        int num4 = 10;
        if (b4 || (num4++ > 0)) {
        System.out.println("床前明月光");
        } else {
        System.out.println("我叫郭德纲");
        }
        System.out.println("num4 = " + num4); // 10
    }
}
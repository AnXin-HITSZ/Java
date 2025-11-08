/*
如何获取一个随机数？
*/
class RandomTest {
    public static void main(String[] args) {
        // 获取一个 [0.0, 1.0) 范围的随机 double 型值
        double d1 = Math.random();
        System.out.println("d1 = " + d1);

        // 需求 1：获取一个 [0, 100] 范围的随机整数
        int num1 = (int)(Math.random() * 101); // [0.0, 1.0) -> [0.0, 101.0) -> [0, 100]
        System.out.println("num1 = " + num1);

        // 需求 2：获取一个 [1, 100] 范围的随机整数
        int num2 = (int)(Math.random() * 100) + 1; // [0.0, 1.0) -> [0.0, 100.0) -> [0, 99] -> [1, 100]
        System.out.println("num2 = " + num2);
    }
}
/*
1. 定义类 CompareLogicExer
2. 定义 main 方法
3. 定义一个 int 类型变量 a、变量 b，都赋值为 20
4. 定义 boolean 类型变量 bo1，判断 ++a 是否被 3 整除，并且 a++ 是否被 7 整除，将结果赋值给 bo1
5. 输出 a 的值，bo1 的值
6. 定义 boolean 类型变量 bo2，判断 b++ 是否被 3 整除，并且 ++b 是否被 7 整除，将结果赋值给 bo2
7. 输出 b 的值，bo2 的值
*/
public class LogicExer {
    public static void main(String[] args) {
        int a, b;
        a = b = 20;

        boolean bo1 = (++a % 3 == 0) && (a++ % 7 == 0);

        System.out.println("a = " + a + "，bo1 = " + bo1);

        boolean bo2 = (b++ % 3 == 0) && (++b % 7 == 0);

        System.out.println("b = " + b + "，bo2 = " + bo2);
    }
}
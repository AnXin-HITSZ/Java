/*
测试变量的基本使用
*/
public class VariableTest {
    public static void main(String[] args) {

        // 定义变量的方式 1：
        char gender;    // 过程 1：变量的声明
        gender = '男';  // 过程 2：变量的赋值（或初始化）

        gender = '女';

        // 定义变量的方式 2（声明与初始化合并）：
        int age = 10;

        System.out.println(age);
        System.out.println("age = " + age);
    }
}

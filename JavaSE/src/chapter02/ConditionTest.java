/*
测试运算符的使用 6：条件运算符
*/
class ConditionTest {
    public static void main(String[] args) {
        String info = (2 > 1)? "表达式 1" : "表达式 2";
        System.out.println(info); // 表达式 1
        
        double result = (2 > 1)? 1 : 2.0;
        System.out.println(result); // 1.0
    }
}
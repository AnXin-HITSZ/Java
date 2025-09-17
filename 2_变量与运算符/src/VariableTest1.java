/*
测试整型和浮点型变量的使用
*/
class VariableTest1 {
    public static void main(String[] args) {
        // 1. 测试整型变量的使用
        // byte（1 字节 = 8 bit）\ short（2 字节）\ int（4 字节）\ long（8 字节）

        byte b1 = 12;
        byte b2 = 127;
        // 编译不通过。因为超出了 byte 的存储范围
        // byte b3 = 128;

        short s1 = 1234;

        int i1 = 123234123;
        // （1）声明 long 类型变量时，需要提供后缀。后缀为 'l' 或 'L'
        long l1 = 123123123L;

        // （2）开发中，大家定义整型变量时，没有特殊情况的话，通常都声明为 int 类型。

        // 2. 测试浮点类型变量的使用
        // float \ double
        double d1 = 12.3;
        // （1）声明 float 类型变量时，需要提供后缀。后缀为 'f' 或 'F'
        float f1 = 12.3f;
        System.out.println("f1 = " + f1);

        // （2）开发中，大家定义浮点型变量时，没有特殊情况的话，通常都声明为 double 类型，因为精度更高。

        // （3）float 类型表数范围要大于 long 类型的表数范围，但是精度不高。

        // 测试浮点型变量的精度
        System.out.println(0.1 + 0.2);

        float ff1 = 123123123f;
        float ff2 = ff1 + 1;
        System.out.println(ff1);
        System.out.println(ff2);
        System.out.println(ff1 == ff2);
        
    }
}
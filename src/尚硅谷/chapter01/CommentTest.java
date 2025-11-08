/*
这是多行注释。

我们可以声明多行注释的信息！


1. Java 中的注释的种类：
单行注释、多行注释、文档注释（Java 特有）。

2. 单行注释、多行注释的作用：
（1）对程序中的代码进行解释说明；
（2）对程序进行调试。

3. 注意：
（1）单行注释和多行注释中声明的信息，不参与编译；换句话说，编译以后生成的字节码文件中不包含单行注释和多行注释中的信息；
（2）多行注释不能嵌套使用。

4. 文档注释：
文档注释内容可以被 JDK 提供的工具 javadoc 所解析，生成一套以网页文件形式体现的该程序的说明文档。

*/
/**
这是我的第一个 Java 程序。很开森！^_^

@author AnXin
@version 1.0

*/
public class CommentTest{
    /**
    这是 main() 方法。格式是固定的。（文档注释）
    */
    /*
    这是 main() 方法。格式是固定的。（多行注释）
    */
    public static void main(String[] args){
        // 这是输出语句
        System.out.println("hello, world!!");
        // System.out.println("hello, world!!");
    }
}
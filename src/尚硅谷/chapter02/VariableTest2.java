/*
测试字符类型和布尔类型的使用
*/
class VariableTest2 {
    public static void main(String[] args) {
        // 1. 字符类型：char（2 字节）

        // 表示形式 1：使用一对 '' 表示，内部有且仅有一个字符
        char c1 = 'a';
        char c2 = '中';
        char c3 = '1';
        char c4 = '%';
        char c5 = 'γ';

        // 编译不通过
        // char c6 = '';
        // char c7 = 'ab;'

        // 表示形式 2：直接使用 Unicode 值来表示字符型常量：'\uXXXX'
    }
}
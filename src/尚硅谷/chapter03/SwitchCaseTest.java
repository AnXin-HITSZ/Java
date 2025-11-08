/*
分支结构之 switch - case 的使用
*/
class SwitchCaseTest {
    public static void main(String[] args) {
        int num = 1;
        switch (num) {
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
                break;  // 结束当前的 switch - case 结构
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            default:
                System.out.println("other");
                break;
        }

        // 另例
        String season = "summer";
        switch (season) {
            case "spring":
                System.out.println("春暖花开");
                break;
            case "summer":
                System.out.println("夏日炎炎");
                break;
            case "autumn":
                System.out.println("秋高气爽");
                break;
            case "winter":
                System.out.println("冬雪皑皑");
                break;
            default:
                System.out.println("季节输入有误");
                break;
        }

        // 错误的例子：编译不通过
        /*
        int number = 20;
        switch (number) {
            case number > 0:
                System.out.println("正数");
                break;
            case number < 0:
                System.out.println("负数");
                break;
            default:
                System.out.println("零");
                break;
        }
        */
    }
}
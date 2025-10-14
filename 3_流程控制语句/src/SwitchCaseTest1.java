class SwitchCaseTest1 {
    public static void main(String[] args) {
        // 定义一个学生成绩的变量
        int score = 78;

        // 根据需求，进行分支
        switch (score / 60) {
            case 0:
                System.out.println("不及格");
                break;
            case 1:
                System.out.println("及格");
                break;
            default:
                System.out.println("成绩输入有误");
                break;
        }
    }
}
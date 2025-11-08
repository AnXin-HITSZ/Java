/*
岳小鹏参加Java考试，他和父亲岳不群达成承诺：
如果：
成绩为100分时，奖励一辆跑车；
成绩为(80，99]时，奖励一辆山地自行车；
当成绩为[60,80]时，奖励环球影城一日游；
其它时，胖揍一顿。

说明：默认成绩是在[0,100]范围内
*/
public class IfElseTest1 {
    public static void main(String[] args) {
        int score = 61;
        if (score == 100) {
            System.out.println("奖励一辆跑车");
        } else if (score > 80 && score <= 99) {
            System.out.println("奖励一辆山地自行车");
        } else if (score >= 60 && score <= 80) {
            System.out.println("奖励环球影城一日游");
        } else {
            System.out.println("胖揍一顿");
        }
    }
}

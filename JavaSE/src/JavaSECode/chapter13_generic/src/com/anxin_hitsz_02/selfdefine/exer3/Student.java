package com.anxin_hitsz_02.selfdefine.exer3;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_02.selfdefine.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:33
 * @Version 1.0
 */
public class Student<T> {
    private String name;
    private T score;    // 成绩

    public Student() {
    }

    public Student(String name, T score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

}

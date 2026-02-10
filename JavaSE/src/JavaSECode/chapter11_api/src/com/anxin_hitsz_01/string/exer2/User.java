package com.anxin_hitsz_01.string.exer2;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_01.string.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 15:23
 * @Version 1.0
 */
public class User {
    private String name;
    private String password;    // 密码

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name + "-" + password;
    }

}

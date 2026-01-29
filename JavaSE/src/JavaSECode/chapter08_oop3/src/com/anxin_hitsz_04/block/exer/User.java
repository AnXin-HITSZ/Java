package com.anxin_hitsz_04.block.exer;

/**
 * ClassName: User
 * Package: com.anxin_hitsz_04.block.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 19:53
 * @Version 1.0
 */
public class User {
    private String userName;
    private String password;
    private long registrationTime;  // 注册时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public User() {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
        userName = System.currentTimeMillis() + "";
        password = "123456";
    }

    public User(String userName, String password) {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();  // 获取系统当前时间（距离 1970-1-1 00:00:00 到现在的毫秒数）
        this.userName = userName;
        this.password = password;
    }

    public String getInfo() {
        return "用户名：" + userName + "，密码：" + password + "，注册时间：" + registrationTime;
    }
}

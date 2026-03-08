package com.anxin_hitsz;

import com.anxin_hitsz.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * ClassName: JdbcTest
 * Package: com.anxin_hitsz
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/6 17:49
 * @Version 1.0
 */
public class JdbcTest {

    /**
     * JDBC 入门程序
     */
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. 获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "AnXin517985!";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. 获取 SQL 语句执行对象
        Statement statement = connection.createStatement();

        // 4. 执行 SQL
        int cnt = statement.executeUpdate("update user set age = 25 where id = 1");   // DML
        System.out.println("SQL 执行完毕影响的记录数为：" + cnt);

        // 5. 释放资源
        statement.close();
        connection.close();

    }

    @Test
    public void testSelect() {
        String URL = "jdbc:mysql://localhost:3306/web01";
        String USER = "root";
        String PASSWORD = "AnXin517985!";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;    // 封装查询返回的结果

        try {
            // 1. 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 打开链接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 执行查询
            String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?";    // 预编译 SQL
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "daqiao");
            stmt.setString(2, "123456");

            rs = stmt.executeQuery();

            // 4. 处理结果集
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                System.out.println(user);   // 使用 Lombok 的 @Data 自动生成的 toString 方法
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}

package com.anxin_hitsz_02.selfdefine.exer1;

import java.util.List;

/**
 * ClassName: DAOTest
 * Package: com.anxin_hitsz_02.selfdefine.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:23
 * @Version 1.0
 */
public class DAOTest {
    public static void main(String[] args) {

        DAO<User> dao = new DAO<>();

        dao.save("1001", new User(1, 33, "A"));
        dao.save("1002", new User(1, 23, "B"));

        dao.update("1002", new User(3, 26, "C"));

        dao.delete("1002");

        List<User> list = dao.list();
        for (User u : list) {
            System.out.println(u);
        }

    }
}

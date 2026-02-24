package com.anxin_hitsz_02.selfdefine.apply;

import org.junit.Test;

import java.util.List;

/**
 * ClassName: DAOTest
 * Package: com.anxin_hitsz_02.selfdefine.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:04
 * @Version 1.0
 */
public class DAOTest {
    @Test
    public void test1() {
        CustomerDAO dao1 = new CustomerDAO();

        dao1.insert(new Customer());

        Customer customer = dao1.queryForInstance(1);

    }

    @Test
    public void test2() {
        OrderDAO dao1 = new OrderDAO();
        dao1.insert(new Order());

        List<Order> list = dao1.queryForList(1);

    }
}

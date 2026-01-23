package com.anxin_hitsz_02.project;

/**
 * ClassName: CustomerList
 * Package: com.anxin_hitsz_02.project
 * Description:
 *      CustomerList 为 Customer 对象的管理模块，内部使用数组管理一组 Customer 对象
 * @Author AnXin
 * @Create 2026/1/23 20:57
 * @Version 1.0
 */
public class CustomerList {

    private Customer[] customers;   // 用来保存客户对象的数组
    private int total;  // 记录已保存客户对象的数量

    /**
     * 用途：构造器，用来初始化 customers 数组
     * @param totalCustomer 指定 customers 数组的最大空间
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }


    /**
     * 用途：将参数 customer 添加组中最后一个客户对象记录之后
     * @param customer 指定要添加的客户对象
     * @return 添加成功返回 true；false 表示数组已满，无法添加
     */
    public boolean addCustomer(Customer customer) {
        if (total < customers.length) {
            customers[total] = customer;
            total++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用途：用参数 cust 替换数组中由 index 指定的对象
     * @param index 指定所替换对象在数组中的位置，从 0 开始
     * @param cust 指定替换的新客户对象
     * @return 替换成功返回 true；false 表示索引无效，无法替换
     */
    public boolean replaceCustomer(int index, Customer cust) {
        if (index >= 0 && index < total) {
            customers[index] = cust;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用途：从数组中删除参数 index 指定索引位置的客户对象记录
     * @param index 指定所删除对象在数组中的索引位置，从 0 开始
     * @return 删除成功返回 true；false 表示索引无效，无法删除
     */
    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            return false;
        }

        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }

        // 将有效数据的最后一个位置置空
//        customers[total - 1] = null;
//        total--;
        // 或：
        customers[--total] = null;

        return true;
    }

    /**
     * 用途：返回数组中记录的所有客户对象
     * @return Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同
     */
    public Customer[] getAllCustomers() {
        // 错误的：
//        return customers;
        // 正确的：
        Customer[] custs = new Customer[total];
        for (int i = 0; i < custs.length; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }

    /**
     * 用途：返回参数 index 指定索引位置的客户对象记录
     * @param index 指定所要获取的客户在数组中的索引位置，从 0 开始
     * @return 封装了客户信息的 Customer 对象
     */
    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) {
            return null;
        } else {
            return customers[index];
        }
    }

    /**
     * 获取客户列表中客户的数量
     * @return tital
     */
    public int getTotal() {
        return total;
    }
}

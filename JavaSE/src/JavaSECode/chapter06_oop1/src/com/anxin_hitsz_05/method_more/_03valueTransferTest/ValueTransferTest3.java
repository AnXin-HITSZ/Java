package com.anxin_hitsz_05.method_more._03valueTransferTest;

/**
 * ClassName: ValueTransferTest3
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 11:03
 * @Version 1.0
 */
public class ValueTransferTest3 {
    public static void main(String[] args) {
        Data data =  new Data();
        ValueTransferTest3 test = new ValueTransferTest3();
        data.m = 10;
        data.n = 20;

        System.out.println("m = " + data.m + ", n = " + data.n);

        // 操作 1：
//        int temp = data.m;
//        data.m = data.n;
//        data.n = temp;

        // 操作 2：
        test.swap(data);
        System.out.println("m = " + data.m + ", n = " + data.n);
    }

    public void swap(Data data) {
        int temp = data.m;
        data.m = data.n;
        data.n = temp;
    }
}

class Data {
    int m;
    int n;
}

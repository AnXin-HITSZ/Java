package com.anxin_hitsz_07.encapsulation.exer2;

/**
 * ClassName: BookTest
 * Package: com.anxin_hitsz_07.encapsulation.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 13:15
 * @Version 1.0
 */
public class BookTest {
    public static void main(String[] args) {

        Book book1 = new Book();

        book1.setBookName("剑指 Java");
        book1.setAuthor("尚硅谷教育");
        book1.setPrice(180.0);

        System.out.println(book1.showInfo());

    }
}

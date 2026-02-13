package com.anxin_hitsz_05.map.exer1;

import java.util.*;

/**
 * ClassName: SingerTest
 * Package: com.anxin_hitsz_05.map.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 16:58
 * @Version 1.0
 */
public class SingerTest {
    public static void main(String[] args) {
        HashMap singers = new HashMap();
        // 添加 1 个歌手和其歌曲
        String singer1 = "周杰伦";

        ArrayList songs1 = new ArrayList();
        songs1.add("夜曲");
        songs1.add("晴天");
        songs1.add("七里香");
        songs1.add("发如雪");
        songs1.add("屋顶");
        songs1.add("青花瓷");

        singers.put(singer1, songs1);

        // 再添加 1 个歌手和其歌曲
        String singer2 = "林俊杰";
        ArrayList songs2 = new ArrayList();
        songs2.add("江南");
        songs2.add("曹操");
        songs2.add("小酒窝");
        songs2.add("可惜没如果");

        singers.put(singer2, songs2);

        Set entrySet = singers.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("歌手：" + entry.getKey());
            System.out.println("歌曲有：" + entry.getValue());
        }

    }

}

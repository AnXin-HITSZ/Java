package com.anxin_hitsz;

import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * ClassName: UUIDTest
 * Package: com.anxin_hitsz
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/12 14:17
 * @Version 1.0
 */
public class UUIDTest {

    @Test
    public void testUuid() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

}

package com.anxin_hitsz.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: AliyunOSSProperties
 * Package: com.anxin_hitsz.utils
 * Description:
 *
 * @Author AnXin
 * @Create 2026/3/12 20:23
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}

package com.configure;

import com.aliyun.oss.ClientConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(prefix = AliyunOssProperties.PREFIX)
public class AliyunOssProperties extends ClientConfiguration {

    public static final String PREFIX = "aliyun.oss";

    private String endpoint;

    private String accessKeyId, accessKeySecret;

    private String defaultBucketName;

}

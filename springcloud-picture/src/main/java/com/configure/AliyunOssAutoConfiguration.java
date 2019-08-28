package com.configure;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(OSSClient.class)
@ConditionalOnProperty(prefix = AliyunOssProperties.PREFIX, name = "access-key-id")
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunOssAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public OSSClient ossClient(AliyunOssProperties properties) {
        return new OSSClient(
            properties.getEndpoint(),
            new DefaultCredentialProvider(properties.getAccessKeyId(), properties.getAccessKeySecret()),
            properties
        );
    }

}

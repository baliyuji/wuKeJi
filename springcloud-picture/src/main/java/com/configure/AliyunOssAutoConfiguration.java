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

/*
@Configuration
从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
*/

/*  
@ConditionalOnClass
 这句代码可以标注在类上面，表示该类下面的所有@Bean都会启用配置，也可以标注在方法上面，只是对该方法启用配置。
spring框架还提供了很多@Condition给我们用，
@ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
@ConditionalOnNotWebApplication（不是web应用）
*/

/*
@ConditionalOnProperty
通过@ConditionalOnProperty来控制Configuration是否生效
通过其两个属性name以及havingValue来实现的，其中name用来从application.properties中读取某个属性值。
如果该值为空，则返回false;
如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
如果返回值为false，则该configuration不生效；为true则生效。
*/


package com.configure;

import org.springframework.beans.factory.annotation.Autowired;

public class DefaultObjectKeySerializer extends ObjectKeySerializer {

    @Autowired
    public DefaultObjectKeySerializer(AliyunOssProperties properties) {
        super(properties.getEndpoint(), properties.getDefaultBucketName());
    }

}

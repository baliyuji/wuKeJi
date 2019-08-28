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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getDefaultBucketName() {
		return defaultBucketName;
	}

	public void setDefaultBucketName(String defaultBucketName) {
		this.defaultBucketName = defaultBucketName;
	}

	public static String getPrefix() {
		return PREFIX;
	}

}

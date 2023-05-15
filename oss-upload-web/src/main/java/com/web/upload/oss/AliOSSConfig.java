package com.web.upload.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucket;

    @Bean
    public OSSClient initOSSClient() {
        return (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}

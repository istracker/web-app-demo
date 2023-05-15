package com.web.upload.repository.model.bo;


import lombok.Data;

@Data
public class AliyunOssSts {
    private String expiration;
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String statusCode;
}
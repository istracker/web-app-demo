package com.web.upload;

import com.aliyun.oss.*;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import java.net.URL;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws Throwable {
        // 以华东1（杭州）的外网Endpoint为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shengzhen.aliyuncs.com";
        // 从STS服务获取的临时访问密钥（AccessKey ID和AccessKey Secret）。
        String accessKeyId = "LTAI5t7Mb933vtv6r38uG86P";
        String accessKeySecret = "P5e8RozYwvew8mmRq12bQYb0AfqMBc";
        // 从STS服务获取的安全令牌（SecurityToken）。
        String securityToken = "yourSecurityToken";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "sg-mall";
        // 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "exampleobject.txt";

        // 从STS服务获取临时访问凭证后，您可以通过临时访问密钥和安全令牌生成OSSClient。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);

        try {
            // 创建请求。
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectName);
            // 设置HttpMethod为PUT。
            generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
            // 添加用户自定义元信息。
            generatePresignedUrlRequest.addUserMetadata("author", "baymax");
            // 设置ContentType。
            generatePresignedUrlRequest.setContentType("application/txt");
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            generatePresignedUrlRequest.setExpiration(expiration);
            // 生成签名URL。
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println(url);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
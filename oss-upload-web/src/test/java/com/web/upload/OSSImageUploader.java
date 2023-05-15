package com.web.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class OSSImageUploader {

    // 阿里云OSS的Endpoint、AccessKey和SecretKey等信息
    private static final String ENDPOINT = "";
    private static final String ACCESS_KEY_ID = "";
    private static final String ACCESS_KEY_SECRET = "";
    private static final String BUCKET_NAME = "sg-mall";

    public static void main(String[] args) {

        // 读取文件的路径信息

        File file = new File("F:\\OneDrive\\图片\\AI绘画\\OIG.jpg");

        // 创建OSS客户端对象，并上传文件到指定的Bucket中
        OSS ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            InputStream is = new FileInputStream(file);
            PutObjectResult result = ossClient.putObject(BUCKET_NAME, "images/" + file.getName(), is);
            is.close();
            System.out.println("File uploaded to OSS: " + result.getETag());
        } catch (Exception ex) {
            System.err.println("Failed to oss-upload-web file: " + ex.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}

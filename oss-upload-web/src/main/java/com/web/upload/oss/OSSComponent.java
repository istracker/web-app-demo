package com.web.upload.oss;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.web.upload.repository.model.bo.AliyunOssSts;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Lcp
 * @version 1.0
 * @date 2023/5/15 14:31
 */
@Component
public class OSSComponent {

    @Autowired
    private OSSClient ossClient;
    @Autowired
    private AliOSSConfig config;

    /**
     * 获取OSS Web直传所需临时签证
     *
     * @return
     */
    public AliyunOssSts getSignature() {
        String REGION_CN_SHANGHAI = "cn-shenzhen";
        // 当前 STS API 版本
        String STS_API_VERSION = "2015-04-01";
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
        String accessKeyId = config.getAccessKeyId();//
        String accessKeySecret = config.getAccessKeySecret();
        String roleArn = "torvalds@1717764109960180.onaliyun.com";
        ProtocolType protocolType = ProtocolType.HTTPS;
        AliyunOssSts aliyunOssSts = new AliyunOssSts();
        try {

            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_SHANGHAI,accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest req = new AssumeRoleRequest();
            req.setVersion(STS_API_VERSION);
            req.setProtocol(protocolType);
            req.setRoleArn(roleArn);
            req.setRoleSessionName("torvalds");
            req.setPolicy(null);
            // 发起请求，并得到response
            final AssumeRoleResponse resp = client.getAcsResponse(req);
            aliyunOssSts.setExpiration(resp.getCredentials().getExpiration());
            aliyunOssSts.setAccessKeyId(resp.getCredentials().getAccessKeyId());
            aliyunOssSts.setAccessKeySecret(resp.getCredentials().getAccessKeySecret());
            aliyunOssSts.setSecurityToken(resp.getCredentials().getSecurityToken());
            aliyunOssSts.setStatusCode("200");
        }catch (ClientException e){
            aliyunOssSts.setStatusCode("500");
            e.printStackTrace();
        }catch (Exception ee){
            ee.printStackTrace();
        }
        return aliyunOssSts;
    }

}

package com.lm.lmliving.commodity.controller;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.FileInputStream;
import java.io.InputStream;
import com.lm.lmliving.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
public class TestController {
    // 普通方式上传
    // @RequestMapping("/test")
    // public R testUpload() throws Exception{
    //     // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    //     String endpoint = "oss-cn-beijing.aliyuncs.com";
    //     // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
    //     //EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
    //     String accessKeyId = "LTAI5tLHkdCJEaAnAtsTAeB8";
    //     String accessKeySecret = "li91AhHVgVDjovqYkdfRiqG9MKyH7e";
    //     // 填写Bucket名称，例如examplebucket。
    //     String bucketName = "lmliving-10000";
    //     // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
    //     String objectName = "yuan.png";
    //     // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
    //     // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
    //     String filePath= "D:\\桌面\\QQ截图20210722121350.png";
    //
    //     // 创建OSSClient实例。
    //     OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    //
    //     try {
    //         InputStream inputStream = new FileInputStream(filePath);
    //         // 创建PutObjectRequest对象。
    //         PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
    //         // 创建PutObject请求。
    //         PutObjectResult result = ossClient.putObject(putObjectRequest);
    //     } catch (OSSException oe) {
    //         System.out.println("Caught an OSSException, which means your request made it to OSS, "
    //                 + "but was rejected with an error response for some reason.");
    //         System.out.println("Error Message:" + oe.getErrorMessage());
    //         System.out.println("Error Code:" + oe.getErrorCode());
    //         System.out.println("Request ID:" + oe.getRequestId());
    //         System.out.println("Host ID:" + oe.getHostId());
    //     } catch (ClientException ce) {
    //         System.out.println("Caught an ClientException, which means the client encountered "
    //                 + "a serious internal problem while trying to communicate with OSS, "
    //                 + "such as not being able to access the network.");
    //         System.out.println("Error Message:" + ce.getMessage());
    //     } finally {
    //         if (ossClient != null) {
    //             ossClient.shutdown();
    //         }
    //     }
    //     return null;
    // }
    // 装配OssClient
    @Resource
    private OSSClient ossClient;
    // 用spring-cloud-starter-alicloud-oss方式上传文件
    @RequestMapping("/test2")
    public R testUpload2() throws Exception{
        InputStream inputStream = new FileInputStream("D:\\桌面\\QQ图片20211207191904.jpg");
        ossClient.putObject("lmliving-10000","2.png",inputStream);
        ossClient.shutdown();
        return R.ok("上传成功");
    }
}

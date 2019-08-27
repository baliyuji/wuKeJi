package com.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

@Component
public class AliyunOSSUtil {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class); 

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public static String upload(File file){  
 //       logger.info("=========>OSS文件上传开始："+file.getName());  
//        String endpoint="oss-us-west-1.aliyuncs.com";  
//        String accessKeyId="LTAIdsPzHK3f2fAy";  
//        String accessKeySecret="ooE55Ns6l3LCmwuQ0FVKGgaAiBVV6M";  
//        String bucketName="wkj-test";  
//        String fileHost="james/";  
        
        String endpoint="oss-cn-beijing.aliyuncs.com";  
        String accessKeyId="LTAIdsPzHK3f2fAy";  
        String accessKeySecret="ooE55Ns6l3LCmwuQ0FVKGgaAiBVV6M";  
        String bucketName="wkj-local";  
        String fileHost="james/";
        String bjUrl = "https://wkj-local.oss-cn-beijing.aliyuncs.com/";
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String dateStr = format.format(new Date());  
        if(null == file){  
            return null;  
        }  
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);  
        try {  
            //容器不存在，就创建  
            if(! ossClient.doesBucketExist(bucketName)){  
                ossClient.createBucket(bucketName);  
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);  
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);  
                ossClient.createBucket(createBucketRequest);  
            }  
            //创建文件路径  
            String fileUrl = fileHost + dateStr +  "/" + file.getName();
            //上传文件  
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));  
            //设置权限 这里是公开读  
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);  
            if(null != result){
            	fileUrl = bjUrl + fileUrl;
                return fileUrl;  
            }  
        }catch (OSSException oe){  
            logger.error(oe.getMessage());  
        }catch (ClientException ce){  
            logger.error(ce.getMessage());  
        }finally {  
            //关闭  
            ossClient.shutdown();  
        }  
        return null;  
    }  
}

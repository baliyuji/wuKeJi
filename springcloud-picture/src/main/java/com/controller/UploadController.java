package com.controller;

import com.aliyun.oss.OSSClient;
import com.configure.AliyunOssProperties;
import com.util.Reply;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Controller("pictureUpload")
@RequestMapping("/upload")
public class UploadController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private static final String filePath = "james/";

    private AliyunOssProperties uploadProperties;

    @Autowired
    public void setUploadProperties(AliyunOssProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }

    private OSSClient ossClient;

    @Value("${aliyun.oss.upload-root-url}")
    private String uploadRootUrl;

    @Autowired
    public void setOssClient(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    @PostMapping(value = "/uploadOss")
    @ResponseBody
    public Reply uploadBlog(@RequestParam MultipartFile[] file) throws IOException {

        StringBuilder sb = new StringBuilder();
        if (file == null || file.length == 0)
            return Reply.fail();

        for (MultipartFile f : file) {
            logger.info("upload file size: " + f.getSize());

            InputStream inputStream = f.getInputStream();
            int a = Objects.requireNonNull(f.getOriginalFilename()).lastIndexOf(".");
            String fileName = filePath + System.currentTimeMillis() + f.getOriginalFilename().substring(a);
            ossClient.putObject(uploadProperties.getDefaultBucketName(), fileName, inputStream);
            sb.append(uploadRootUrl).append(fileName).append(",");
        }

        return Reply.success().data(sb.substring(0, sb.length() - 1).split(","));

    }

}

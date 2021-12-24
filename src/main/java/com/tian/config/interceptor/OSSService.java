package com.tian.config.interceptor;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.tian.config.OSSConfiguration;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian.service
 * @ className:      OSSService
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/23 13:25
 * @ version:    1.0
 */
@Service
public class OSSService {
    @Autowired
    private OSSConfiguration ossConfiguration;

    @Autowired
    private OSS ossClient;
    private SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");

    //上传文件
    public String uploadFile(MultipartFile file, String storagePath) {
        String fileName = "";
        try {
            fileName = UUID.randomUUID().toString();
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            fileName = storagePath + "/" + ft.format(new Date()) + "/" + fileName + file.getName().substring(file.getName().lastIndexOf("."));
            // 上传文件
            ossClient.putObject(ossConfiguration.getBucketName(), fileName, inputStream, objectMetadata);
        } catch (IOException e) {
            System.out.println("Error occurred: {}" + e.getMessage());//TODO
//            log.error("Error occurred: {}", e.getMessage(), e);
        }
        return fileName;
    }

    //获取文件列表
    public List<String> listObjects() {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossConfiguration.getBucketName()).withMaxKeys(200);
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        return objectSummaries.stream().map(OSSObjectSummary::getKey).collect(Collectors.toList());
    }

    //判断文件是否存在
    public boolean doesObjectExist(String fileName) {
        try {
            if (Strings.isEmpty(fileName)) {
                System.out.println("文件名不能为空");
//                log.error("文件名不能为空");TODO
                return false;
            } else {
                return ossClient.doesObjectExist(ossConfiguration.getBucketName(), fileName);
            }
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    //下载文件
    public void exportFile(OutputStream os, String objectName) {
        OSSObject ossObject = ossClient.getObject(ossConfiguration.getBucketName(), objectName);
        // 读取文件内容
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght;
        try {
            while ((lenght = in.read(buffer)) != -1) {
                out.write(buffer, 0, lenght);
            }
            out.flush();
        } catch (IOException e) {
            System.out.println("Error occurred: {}" + e.getMessage());//TODO
//            log.error("Error occurred: {}", e.getMessage(), e);
        }
    }

    //删除文件
    public void deleteFile(String fileName) {
        try {
            ossClient.deleteObject(ossConfiguration.getBucketName(), fileName);
        } catch (Exception e) {
            System.out.println("Error occurred: {}" + e.getMessage());//TODO
//            log.error("Error occurred: {}", e.getMessage(), e);
        }
    }

    //查看URL
    public String getSingeNatureUrl(String filename, int expSeconds) {
        Date expiration = new Date(System.currentTimeMillis() + expSeconds * 1000);
        URL url = ossClient.generatePresignedUrl(ossConfiguration.getBucketName(), filename, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}

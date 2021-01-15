package com.yyg.cn.utils;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
@Component
public class MioioUtil {
    public static String endPoint;
    public static String accessKey;
    public static String secretKey;
    public static String bucketName;
    public static MinioClient minioClient;

//    static {
//        try {
//            minioClient = new MinioClient(endPoint, accessKey, secretKey);
//        } catch (InvalidEndpointException e) {
//            e.printStackTrace();
//        } catch (InvalidPortException e) {
//            e.printStackTrace();
//        }
//    }
    public static void createBucket() {

        boolean flag = false;
        try {
            minioClient = new MinioClient(endPoint, accessKey, secretKey);
            flag = minioClient.bucketExists(bucketName);
            if (!flag) {
                minioClient.makeBucket(bucketName);
            }
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        }
    }
    @Value("${minio_endPoint}")
    public static void setEndPoint(String endPoint) {
        MioioUtil.endPoint = endPoint;
    }
    @Value("${minio_AccessKey}")
    public static void setAccessKey(String accessKey) {
        MioioUtil.accessKey = accessKey;
    }
    @Value("${minio_SecretKey}")
    public static void setSecretKey(String secretKey) {
        MioioUtil.secretKey = secretKey;
    }
    @Value("${minio_bucketName}")
    public static void setBucketName(String bucketName) {
        MioioUtil.bucketName = bucketName;
    }
}

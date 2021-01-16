package com.yyg.cn.utils;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class MioioUtil {
    private static String endPoint;
    private static String accessKey;
    private static String secretKey;
    private static String bucketName;
    public static MinioClient minioClient;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            endPoint = properties.getProperty("minio_endPoint");
            accessKey = properties.getProperty("minio_AccessKey");
            secretKey = properties.getProperty("minio_SecretKey");
            bucketName = properties.getProperty("minio_bucketName");
            minioClient = new MinioClient(endPoint, accessKey, secretKey);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createBucket() {
        boolean flag = false;
        try {
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
        }
    }
}

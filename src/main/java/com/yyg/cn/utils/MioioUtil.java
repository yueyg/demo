package com.yyg.cn.utils;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MioioUtil {
    private static String ENDPOINT = "http://127.0.0.1:9000";
//    @Value(value = "${minio.AccessKey}")
    private static String ACCESSKEY = "minioadmin";
//    @Value(value = "${minio.SecretKey}")
    private static String SECRETKEY = "minioadmin";
    private static String BUCKETNAME = "yyg-data";
    private static MinioClient minioClient;

    public static void main(String[] args) {
       createBucket();
    }
    static {
        try {
            minioClient = new MinioClient(ENDPOINT,ACCESSKEY,SECRETKEY);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }
    public static void createBucket() {
        boolean flag = false;
        try {
            flag = minioClient.bucketExists(BUCKETNAME);
            if (!flag) {
                minioClient.makeBucket(BUCKETNAME);
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

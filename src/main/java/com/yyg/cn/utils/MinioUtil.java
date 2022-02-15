package com.yyg.cn.utils;

import com.google.api.client.util.IOUtils;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Minio工具类
 */
public class MinioUtil {
    private static String endPoint;
    private static String accessKey;
    private static String secretKey;
    private static String bucketName;
    public static MinioClient minioClient;
    private static final String CONTENT_TYPE_VIDEO = "video/video";
    private static final String CONTENT_TYPE_IMAGE = "video/image";
    private static final String CONTENT_TYPE_FILE = "video/file";
    private static final String CONTENT_TYPE_PDF = "video/pdf";
    public static final String[] imgArray = {"jpg","bmp","png","tif","pcx","tga","exif","fpx","svg","psd","webp"};
    public static final String[] videoArray = {"avi","mp4","mpg","mov","flv","asf","mpeg","mpe","3gp","mkv","rm","rmvb","wnv","mp4","wmv","audio"};

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            endPoint = properties.getProperty("minio_endPoint");
            accessKey = properties.getProperty("minio_AccessKey");
            secretKey = properties.getProperty("minio_SecretKey");
            bucketName = properties.getProperty("minio_bucketName");
            if (minioClient == null) {
                minioClient = new MinioClient(endPoint, accessKey, secretKey);
            }

        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建桶
     */
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
    /**
     * 获取MinioClient
     *
     * @return MinioClient
     */
    public static MinioClient getMinioClient() {
        return minioClient;
    }

    /**
     * 上传文件
     * @param multipartFile     文件
     * @param modelName         模块名称
     * @param contentType       文件类型
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidArgumentException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    private static Map<String,String> uploadFile(MultipartFile multipartFile, String modelName, String contentType) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        Map<String,String> result = new HashMap<>();
        createBucket();
        String fileType;
        if (CONTENT_TYPE_IMAGE.equals(contentType)) {
            fileType = "image";
        } else if (CONTENT_TYPE_VIDEO.equals(contentType)) {
            fileType = "video";
        } else {
            fileType = "file";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String originalFileName = multipartFile.getOriginalFilename();
        StringBuilder fileNameBuilder = new StringBuilder(modelName+"/");
        fileNameBuilder.append(simpleDateFormat.format(new Date()) + "/" + fileType);
        fileNameBuilder.append(UUID.randomUUID().toString().replace("-",""));
        fileNameBuilder.append("." + originalFileName.substring(originalFileName.lastIndexOf(".")+1));
        String fileName = fileNameBuilder.toString();
        minioClient.putObject(bucketName,fileName,multipartFile.getInputStream(),contentType);
        result.put("fileName",originalFileName);
        result.put("fileUrl",minioClient.getObjectUrl(bucketName,fileName));
        return result;
    }

    /**
     *
     * 上传文件
     * @param multipartFile     文件
     * @param modelName         模块名称
     * @param contentType       文件类型
     * @param fileUrl           文件路径
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidArgumentException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    private static Map<String,String> uploadFile(MultipartFile multipartFile,String modelName,String contentType,String fileUrl) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        Map<String,String> result = new HashMap<>();
        createBucket();
        String fileType;
        if (CONTENT_TYPE_IMAGE.equals(contentType)) {
            fileType = "image";
        } else if (CONTENT_TYPE_VIDEO.equals(contentType)) {
            fileType = "video";
        } else {
            fileType = "file";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = fileUrl.substring(fileUrl.indexOf(modelName));
        minioClient.putObject(bucketName,fileName,multipartFile.getInputStream(),contentType);
        result.put("fileName",originalFileName);
        result.put("fileUrl",minioClient.getObjectUrl(bucketName,fileName));
        return result;
    }
    /**
     * 下载文件
     * @param response
     * @param fileName
     */
    public static void downloadFile(HttpServletResponse response, String fileName) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        downloadFile(response,bucketName,fileName);
    }
    /**
     * 下载文件
     * @param response
     * @param fileName
     */
    public static void downloadFile(HttpServletResponse response,String bucketName,String fileName) {
        try {
            InputStream inputStream = minioClient.getObject(bucketName,fileName);
            IOUtils.copy(inputStream,response.getOutputStream());
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
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除文件
     * @param fileName
     */
    public static void removeFile(String fileName) {
        try {
            minioClient.removeObject(bucketName,fileName.split(bucketName)[1]);
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
        }
    }
    /**
     * 从minIO获取文件流
     *
     * @param bucketName 桶名称
     * @param fileName   文件名
     * @return 文件流
     */
    public static InputStream getMinIOInputStream(String bucketName, String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(bucketName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
    /**
     * 获取下载文件流
     *
     * @param bucketName 桶名
     * @param fileUrl    文件地址
     */
    public static InputStream getDownloadIO(String bucketName, String fileUrl) throws Exception {
        InputStream minIOInputStream = getMinIOInputStream(bucketName, fileUrl);
        return minIOInputStream;
    }
    /**
     * 获取下载文件流
     *
     * @param fileUrl 文件地址
     */
    public static InputStream getDownloadIO(String fileUrl) throws Exception {
        InputStream minIOInputStream = getMinIOInputStream(bucketName, fileUrl);
        return minIOInputStream;
    }
    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream 输入流
     * @return 字节数组
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}

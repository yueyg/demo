package com.yyg.cn.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    @Value("${minio_endPoint}")
    private String endPoint;
    public void show() {
        System.out.println(this.endPoint);
    }
}

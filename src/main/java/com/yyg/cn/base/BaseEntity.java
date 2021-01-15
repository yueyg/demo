package com.yyg.cn.base;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.util.IdGenerator;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    private String id;
}

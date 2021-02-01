package com.yyg.cn.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data
public class BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建日期
     */
    private LocalDate createDate;

    /**
     * 最近更新人ID
     */
    private Long updateUserId;

    /**
     * 最近更新日期
     */
    private LocalDate updateDate;
}

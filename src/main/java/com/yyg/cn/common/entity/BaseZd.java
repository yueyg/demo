package com.yyg.cn.common.entity;

import java.time.LocalDate;
import com.yyg.cn.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yueyg
 * @since 2021-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseZd extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public BaseZd() {
    }

    public BaseZd(String dm, String mc, Integer zt, String fldm, Integer sxh) {
        this.dm = dm;
        this.mc = mc;
        this.zt = zt;
        this.fldm = fldm;
        this.sxh = sxh;
    }

    /**
     * 代码
     */
    private String dm;

    /**
     * 名称
     */
    private String mc;

    /**
     * 状态（0：无效，1：有效）
     */
    private Integer zt;

    /**
     * 分类代码
     */
    private String fldm;

    /**
     * 顺序号
     */
    private Integer sxh;




}

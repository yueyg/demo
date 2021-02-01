package com.yyg.cn.common.service;

import com.yyg.cn.common.entity.BaseZd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yueyg
 * @since 2021-01-31
 */
public interface IBaseZdService extends IService<BaseZd> {
    /**
     * 根据分类代码获取集合
     * @param fldm
     * @return
     */
    List<BaseZd> selectListByFldm(String fldm);

    /**
     * 根据分类代码、代码获取对象
     * @param dm
     * @param fldm
     * @return
     */
    BaseZd selectOneByDmAndFldm(String dm,String fldm);

    /**
     * 根据字典名称、代码获取对象
     * @param mc
     * @param fldm
     * @return
     */
    BaseZd selectOneByMcAndFldm(String mc,String fldm);

}

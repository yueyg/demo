package com.yyg.cn.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yyg.cn.common.entity.BaseZd;
import com.yyg.cn.common.mapper.BaseZdMapper;
import com.yyg.cn.common.service.IBaseZdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueyg
 * @since 2021-01-31
 */
@Service
public class BaseZdServiceImpl extends ServiceImpl<BaseZdMapper, BaseZd> implements IBaseZdService {
//    @Autowired
//    private BaseZdMapper baseZdMapper;

    @Override
    public List<BaseZd> selectListByFldm(String fldm) {
        return list(new QueryWrapper<BaseZd>().eq("fldm",fldm));
    }

    @Override
    public BaseZd selectOneByDmAndFldm(String dm, String fldm) {
        return getOne(new QueryWrapper<BaseZd>().eq("dm",dm).eq("fldm",fldm));
    }

    @Override
    public BaseZd selectOneByMcAndFldm(String mc, String fldm) {
        return getOne(new QueryWrapper<BaseZd>().eq("mc",mc).eq("fldm",fldm));
    }
}

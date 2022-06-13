package com.yyg.cn.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.cn.common.entity.DataAuthority;
import com.yyg.cn.common.entity.User;
import com.yyg.cn.common.mapper.DataAuthorityMapper;
import com.yyg.cn.common.mapper.UserMapper;
import com.yyg.cn.common.service.IDataAuthorityService;
import com.yyg.cn.common.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueyg
 * @since 2021-01-13
 */
@Service
public class DataAuthorityServiceImpl extends ServiceImpl<DataAuthorityMapper, DataAuthority> implements IDataAuthorityService {

}

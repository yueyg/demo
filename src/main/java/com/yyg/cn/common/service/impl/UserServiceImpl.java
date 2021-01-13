package com.yyg.cn.common.service.impl;

import com.yyg.cn.common.entity.User;
import com.yyg.cn.common.mapper.UserMapper;
import com.yyg.cn.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

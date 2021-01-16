package com.yyg.cn.common.controller;


import com.yyg.cn.base.BaseController;
import com.yyg.cn.common.service.IUserService;
import com.yyg.cn.utils.CommonUtil;
import com.yyg.cn.utils.MioioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yueyg
 * @since 2021-01-13
 */
@RestController
@RequestMapping("/common/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private CommonUtil commonUtil;
    @GetMapping("/show")
    public void show() {
     MioioUtil.createBucket();

    }

}

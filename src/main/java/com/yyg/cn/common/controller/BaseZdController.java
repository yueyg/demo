package com.yyg.cn.common.controller;


import com.yyg.cn.common.entity.BaseZd;
import com.yyg.cn.common.service.IBaseZdService;
import com.yyg.cn.utils.MioioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.yyg.cn.base.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yueyg
 * @since 2021-01-31
 */
@RestController
@RequestMapping("/common/base-zd")
public class BaseZdController extends BaseController {
    @Autowired
    private IBaseZdService baseZdService;
    @GetMapping("/show")
    public void show() {
        List<BaseZd> baseZdList = new ArrayList<>();
        baseZdList.add(new BaseZd("1","是",1,"SF",0));
        baseZdList.add(new BaseZd("0","否",1,"SF",1));
        baseZdService.saveBatch(baseZdList);
    }


}

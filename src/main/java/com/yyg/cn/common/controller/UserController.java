package com.yyg.cn.common.controller;


import com.yyg.cn.base.BaseController;
import com.yyg.cn.common.entity.User;
import com.yyg.cn.common.service.IUserService;
import com.yyg.cn.utils.MioioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/show")
    public void show() {
//        List<User> users = userService.list();
//        for (User user : users) {
//            System.out.println(user.getName());
//        }
        User user = new User();
        user.setName("abc");
        user.setAge(3);
        userService.save(user);
    }

}

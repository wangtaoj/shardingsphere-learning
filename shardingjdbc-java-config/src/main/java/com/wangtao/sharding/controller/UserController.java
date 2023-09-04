package com.wangtao.sharding.controller;

import com.wangtao.sharding.entity.User;
import com.wangtao.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangtao
 * Created at 2023/9/3 00:31
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping("/detail")
    public User detailUser(Long userId) {
        return userService.detailUser(userId);
    }
}

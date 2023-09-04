package com.wangtao.sharding.service;

import com.wangtao.sharding.entity.User;
import com.wangtao.sharding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangtao
 * Created at 2023/9/3 00:31
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> listUser() {
        return userMapper.selectList(null);
    }

    public User detailUser(Long userId) {
        return userMapper.selectById(userId);
    }
}

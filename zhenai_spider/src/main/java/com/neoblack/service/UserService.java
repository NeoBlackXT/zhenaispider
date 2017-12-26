package com.neoblack.service;

import com.neoblack.mapper.UserMapper;
import com.neoblack.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Neo on 2017/4/11.
 */
@Service
public class UserService extends BaseService<User> {
    @Autowired
    private UserMapper userMapper;


    public List<User> queryUserLimit(Integer l1,Integer l2){
        List<User> users = userMapper.queryUserLimit(l1, l2);
        return users;
    }


}

package com.neoblack.service;

import com.neoblack.mapper.ErrorIdMapper;
import com.neoblack.mapper.UserMapper;
import com.neoblack.pojo.ErrorId;
import com.neoblack.pojo.User;
import com.neoblack.utils.SpiderUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RabbitUserService extends BaseService<User> {
    private static final Logger log = Logger.getLogger(RabbitUserService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ErrorIdMapper errorIdMapper;

    //插入数据库
    public void saveUser(Long userId) {
        try {
            User user = SpiderUtils.getUser(userId);
            user.setUserid(Long.parseLong("001" + userId));
            user.setCreated(new Date());
            user.setUpdated(user.getCreated());
            userMapper.insertSelective(user);
        } catch (Exception e) {
            try {
                log.error("获取用户详细信息失败：userId=" + userId + ",错误信息：" + e.getMessage());
                ErrorId errorId = new ErrorId();
                errorId.setUserid(userId);
                errorIdMapper.insert(errorId);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Integer queryUser(Long userId) {

        User user = new User();
        user.setUserid(Long.parseLong("001" + userId));
        int i = userMapper.selectCount(user);
        return i;
    }

    public void insertNotExistToErrorUserId(Long userId) {
        if (queryUser(userId) == 0) {
            saveUser(userId);
        }
    }

    public void saveUserId01(Long userId) {
        saveUser(userId);
    }

    public void saveUserId02(Long userId) {
        saveUser(userId);
    }

    public void saveUserId03(Long userId) {
        saveUser(userId);
    }

    public void saveUserId04(Long userId) {
        saveUser(userId);
    }

    public void saveUserId05(Long userId) {
        saveUser(userId);
    }

    public void saveUserId06(Long userId) {
        saveUser(userId);
    }

    public void saveUserId07(Long userId) {
        saveUser(userId);
    }

    public void saveUserId08(Long userId) {
        saveUser(userId);
    }

    public void saveUserId09(Long userId) {
        saveUser(userId);
    }

    public void saveUserId10(Long userId) {
        saveUser(userId);
    }

    public void saveUserId11(Long userId) {
        saveUser(userId);
    }

    public void saveUserId12(Long userId) {
        saveUser(userId);
    }

    public void saveUserId13(Long userId) {
        saveUser(userId);
    }

    public void saveUserId14(Long userId) {
        saveUser(userId);
    }

    public void saveUserId15(Long userId) {
        saveUser(userId);
    }

    public void saveUserId16(Long userId) {
        saveUser(userId);
    }

    public void saveUserId17(Long userId) {
        saveUser(userId);
    }

    public void saveUserId18(Long userId) {
        saveUser(userId);
    }

    public void saveUserId19(Long userId) {
        saveUser(userId);
    }

    public void saveUserId20(Long userId) {
        saveUser(userId);
    }

    public void saveUserId21(Long userId) {
        saveUser(userId);
    }

    public void saveUserId22(Long userId) {
        saveUser(userId);
    }

    public void saveUserId23(Long userId) {
        saveUser(userId);
    }

    public void saveUserId24(Long userId) {
        saveUser(userId);
    }

    public void saveUserId25(Long userId) {
        saveUser(userId);
    }

    public void saveUserId26(Long userId) {
        saveUser(userId);
    }

    public void saveUserId27(Long userId) {
        saveUser(userId);
    }

    public void saveUserId28(Long userId) {
        saveUser(userId);
    }

    public void saveUserId29(Long userId) {
        saveUser(userId);
    }

    public void saveUserId30(Long userId) {
        saveUser(userId);
    }


    public void saveUserFromQuery(User user) {
        saveUserFromQueryInstance(user);

    }
    private void saveUserFromQueryInstance(User user) {
        try {
            userMapper.insertSelective(user);
        } catch (Exception e) {
//            ErrorId errorId = new ErrorId();
//            errorId.setUserid(user.getUserid());
//            try {
//                errorIdMapper.insert(errorId);
//            } catch (Exception e1) {
//                e.printStackTrace();
//            }
            log.error("转存失败："+user.toString()+"\r\n"+ e.getMessage());
        }
        return;
    }

}

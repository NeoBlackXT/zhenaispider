package com.neoblack.mapper;


import com.neoblack.common.mapper.SysMapper;
import com.neoblack.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends SysMapper<User> {
    List<User> queryUserLimit(@Param("l1") Integer l1, @Param("l2") Integer l2);

}

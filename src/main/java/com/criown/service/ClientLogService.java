package com.criown.service;

import com.criown.entity.ClientLog;
import org.apache.ibatis.annotations.Param;

public interface ClientLogService {

    //验证
    String selectUserpwdByUserid(Integer userid);
    Integer selectUseridByUsername(String username);


    //修改
    int updateUsernameAndUserpwdByUserid( String username,  String userpwd,int userid);
    int updateUserpwdByUserid(@Param("userpwd") String userpwd, @Param("userid") Integer userid);

    //删除
    int deleteByUserid(@Param("userid") int userid);

    //添加
    int insertAll(ClientLog clientLog);
}

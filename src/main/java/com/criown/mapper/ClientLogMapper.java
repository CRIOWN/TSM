package com.criown.mapper;
import java.util.List;

import com.criown.entity.Admin;
import com.criown.entity.Client;
import org.apache.ibatis.annotations.Param;

import com.criown.entity.ClientLog;

/**
* @author STY
* @description 针对表【client_log】的数据库操作Mapper
* @createDate 2023-03-02 23:16:39
* @Entity com.criown.entity.ClientLog
*/
public interface ClientLogMapper {

    //验证
    String selectUserpwdByUserid(@Param("userid") Integer userid);

     Integer selectUseridByUsername(@Param("username") String username);


    //修改
    int updateUsernameAndUserpwdByUserid(@Param("username") String username, @Param("userpwd") String userpwd, @Param("userid") int userid);

    int updateUserpwdByUserid(@Param("userpwd") String userpwd, @Param("userid") Integer userid);

    //删除
    int deleteByUserid(@Param("userid") int userid);

    //添加
    int insertAll(ClientLog clientLog);


}





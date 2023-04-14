package com.criown.service;

import com.criown.entity.AdminLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminLogService {

    //删除 id
    int delByUserid(int id);


    //改
    int updateUsernameAndUserpwdByUserid(String username,String userpwd,int userid);

    //插
    int insertAll(AdminLog adminLog);

    //查
    List<AdminLog> selectUseridByUserid(int id);

    AdminLog selectByUserid(int id);

    //验证
   String  selectUserpwdByUserid(Integer  id);
   Integer selectUseridByUsername(String username);

    //修改密码
    int updateUserpwdByUserid(@Param("userpwd") String userpwd, @Param("userid") Integer userid);


}

package com.criown.service;

import com.criown.entity.StaffLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffLogService {

    //验证
    String selectUserpwdByUserid(Integer userid);
    Integer selectUseridByUsername(String username);

    //查找
    List<StaffLog> selectUseridByUserid( int userid);


    //s删除
    int delByUserid(int userid);

    //修改
    int updateUsernameAndUserpwdByUserid(String username,String userpwd, Integer userid);
    int updateUserpwdByUserid(String userpwd, Integer userid);


    //添加
    int insertAll(StaffLog staffLog);


}

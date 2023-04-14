package com.criown.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.criown.entity.StaffLog;

/**
* @author STY
* @description 针对表【staff_log】的数据库操作Mapper
* @createDate 2023-03-03 11:03:12
* @Entity com.criown.entity.StaffLog
*/
public interface StaffLogMapper {

    //验证
    String selectUserpwdByUserid(@Param("userid") Integer userid);
    Integer selectUseridByUsername(@Param("username") String username);

    //查找
    List<StaffLog> selectUseridByUserid(@Param("userid") int userid);


    //s删除
    int delByUserid(@Param("userid") int userid);

    //修改
    int updateUsernameAndUserpwdByUserid(@Param("username") String username, @Param("userpwd") String userpwd, @Param("userid") Integer userid);

    int updateUserpwdByUserid(@Param("userpwd") String userpwd, @Param("userid") Integer userid);
    //添加
    int insertAll(@Param("staffLog") StaffLog staffLog);

}





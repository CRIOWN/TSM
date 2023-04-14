package com.criown.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.criown.entity.AdminLog;

/**
* @author STY
* @description 针对表【admin_log】的数据库操作Mapper
* @createDate 2023-03-02 14:36:29
* @Entity com.criown.entity.AdminLog
*/
public interface AdminLogMapper {

    //验证
    String selectUserpwdByUserid(@Param("userid") Integer userid);
    Integer selectUseridByUsername(@Param("username") String username);

    //删除byid
    int delByUserid(@Param("userid") int userid);

    //查找byid
    List<AdminLog> selectUseridByUserid(@Param("userid") int userid);

    AdminLog selectByUserid(@Param("userid") int userid);

    //添加
    int insertAll(AdminLog adminLog);

    //修改byid
    int updateUsernameAndUserpwdByUserid(@Param("username") String username, @Param("userpwd") String userpwd, @Param("userid") int userid);


    //修改密码
    int updateUserpwdByUserid(@Param("userpwd") String userpwd, @Param("userid") Integer userid);
}





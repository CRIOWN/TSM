package com.criown.service;

import com.criown.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    //查询
    List<Admin> selectAll();

    int updateNameAndSexAndLocalAndPhoneAndDetailByUserid(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("phone") Integer phone, @Param("detail") String detail, @Param("userid") Integer userid);

}

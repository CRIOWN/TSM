package com.criown.mapper;
import java.util.List;

import com.criown.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
* @author STY
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2023-03-12 10:55:38
* @Entity com.criown.entity.Admin
*/
public interface AdminMapper {
    //查询所有 表单显示

    List<Admin> selectAll();

    List<Admin> getAllByQuery(@Param("name") String name, @Param("sex") String sex,  @Param("local") String local);

    int updateNameAndSexAndLocalAndPhoneAndDetailByUserid(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("phone") Integer phone, @Param("detail") String detail, @Param("userid") Integer userid);

}





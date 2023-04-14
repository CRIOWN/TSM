package com.criown.service;

import com.criown.entity.Staff;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StaffService{

    //查询全部
    List<Staff> selectAll();

    //动态条件查询
    List<Staff> getAllByQuery(@Param("name") String name, @Param("sex") String sex, @Param("career") String career, @Param("local") String local);

    //add
    int addAll(String  name,String  sex,String local,int number,String career,String detail);

//    多选id删除
    int  delById(List list);

    int delByIdSingle(Integer id);

    int updateAddById(@Param("number") Integer number, @Param("career") String career, @Param("detail") String detail, @Param("local") String local, @Param("name") String name, @Param("sex") String sex, @Param("oldId") Integer oldId);
    int updateNameAndSexAndLocalAndNumberAndCareerAndDetailById(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("number") Integer number, @Param("career") String career, @Param("detail") String detail, @Param("id") Integer id);
    int updateNameAndSexAndLocalAndNumberAndDetailById(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("number") Integer number, @Param("detail") String detail, @Param("id") Integer id);


}

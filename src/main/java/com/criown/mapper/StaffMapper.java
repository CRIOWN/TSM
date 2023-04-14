package com.criown.mapper;
import java.util.List;

import com.criown.entity.Client;
import com.criown.entity.Staff;
import org.apache.ibatis.annotations.Param;

/**
* @author STY
* @description 针对表【staff】的数据库操作Mapper
* @createDate 2023-03-12 16:07:43
* @Entity com.criown.entity.Staff
*/
public interface StaffMapper {


    //全选
    List<Staff> selectAll();

    //增加
    int addAll( @Param("name")String name,@Param("sex") String sex,@Param("local") String local,@Param("number") int number,@Param("career") String career,@Param("detail") String detail);

    //查询
    List<Staff> getAllByQuery(@Param("name") String name, @Param("sex") String sex,@Param("career") String career, @Param("local") String local);

    //id删除
    int  delById(@Param("list")List<Integer> list);

    int delByIdSingle(@Param("id") Integer id);

    //编辑
    int updateAddById(@Param("number") Integer number, @Param("career") String career, @Param("detail") String detail, @Param("local") String local, @Param("name") String name, @Param("sex") String sex, @Param("oldId") Integer oldId);

    int updateNameAndSexAndLocalAndNumberAndDetailById(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("number") Integer number, @Param("detail") String detail, @Param("id") Integer id);
    int updateNameAndSexAndLocalAndNumberAndCareerAndDetailById(@Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("number") Integer number, @Param("career") String career, @Param("detail") String detail, @Param("id") Integer id);
}





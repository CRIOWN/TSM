package com.criown.mapper;
import java.util.List;


import com.criown.entity.Client;
import org.apache.ibatis.annotations.Param;

/**
* @author STY
* @description 针对表【client】的数据库操作Mapper
* @createDate 2023-03-12 10:55:58
* @Entity com.criown.entity.Client
*/
public interface ClientMapper {

    List<Client> selectAll();

    List<Client> getAllByQuery(@Param("name") String name, @Param("sex") String sex, @Param("local") String local);

    int addAll(@Param("name")String name,@Param("sex") String sex,@Param("local") String local,@Param("number") int number,@Param("detail") String detail);

    int  delById(@Param("list")List<Integer> list);

    int delByIdSingle(@Param("id") Integer id);

    int updateAllById( @Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("detail") String detail, @Param("number") Integer number, @Param("oldId") Integer oldId);



}





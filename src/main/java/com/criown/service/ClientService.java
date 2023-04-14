package com.criown.service;

import com.criown.entity.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientService {

    List<Client> selectAll();

    List<Client> getAllByQuery( String name,String sex,String local);

    int addAll(String name,String sex,String local,int number,String detail);

    int  delById(List list);

    int delByIdSingle(Integer id);

    int updateAllById( @Param("name") String name, @Param("sex") String sex, @Param("local") String local, @Param("detail") String detail, @Param("number") Integer number, @Param("oldId") Integer oldId);

}

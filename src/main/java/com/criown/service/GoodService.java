package com.criown.service;

import com.criown.entity.Good;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoodService {

    List<Good> selectAll();

    List<Good> selectAllById( Integer id);

    int delByList( List<Integer> list);

    int updateById(Integer id, Integer clientid,Integer start,Integer end,Date sendtime, Date recetime,String detail);

    int addAll(Integer clientid, Integer start,Integer end, Date sendtime, Date recetime,String detail);

    List<Good> searchAllByFrom(Integer start);
    List<Good> searchAllByEnd( Integer end);

    List<Good> searchAllByClientid(Integer clientid);

    List<Good> selectAllByIdAndClientid(@Param("id") Integer id, @Param("clientid") Integer clientid);

    int updateSendtimeAndRecetimeById(Date sendtime, Date recetime, Integer id);

    Integer selectEndById(@Param("id") Integer id);

    Integer selectStartById(@Param("id") Integer id);


}

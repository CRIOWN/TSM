package com.criown.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.criown.entity.Good;

import javax.annotation.Nullable;

/**
* @author STY
* @description 针对表【good】的数据库操作Mapper
* @createDate 2023-03-19 17:27:45
* @Entity com.criown.entity.Good
*/
public interface GoodMapper {

    List<Good> selectAll();

    List<Good> selectAllById(@Param("id") Integer id);

    int delByList(@Param("list") List<Integer> list);

    int updateById(@Param("id") Integer id,@Param("clientid") Integer clientid,@Param("start") Integer start,@Param("end") Integer end,@Param("sendtime") Date sendtime,@Param("recetime") Date recetime,@Param("detail") String detail);

    int addAll(@Param("clientid") Integer clientid,@Param("start") Integer start,@Param("end") Integer end,@Param("sendtime") Date sendtime,@Param("recetime") Date recetime,@Param("detail") String detail);

    List<Good> searchAllByFrom(@Param("start") Integer start);

    List<Good> searchAllByEnd(@Param("end") Integer end);

    List<Good> searchAllByClientid(@Param("clientid") Integer clientid);

    List<Good> selectAllByIdAndClientid(@Param("id") @Nullable Integer id, @Param("clientid") Integer clientid);

    int updateSendtimeAndRecetimeById(@Param("sendtime") Date sendtime, @Param("recetime") Date recetime, @Param("id") Integer id);

    Integer selectEndById(@Param("id") Integer id);

    Integer selectStartById(@Param("id") Integer id);

}





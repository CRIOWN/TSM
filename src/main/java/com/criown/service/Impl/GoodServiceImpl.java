package com.criown.service.Impl;

import com.criown.entity.Good;
import com.criown.mapper.GoodMapper;
import com.criown.service.GoodService;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GoodServiceImpl implements GoodService {
    private GoodMapper goodMapper;

    public void setGoodMapper(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public List<Good> selectAll() {
        return goodMapper.selectAll();
    }

    @Override
    public List<Good> selectAllById(Integer id) {
        return goodMapper.selectAllById(id);
    }

    @Override
    public int delByList( List<Integer> list) {
        return goodMapper.delByList(list);
    }

    @Override
    public int updateById(Integer id, Integer clientid,Integer start,Integer end,Date sendtime, Date recetime,String detail) {
        return goodMapper.updateById(id, clientid, start, end, sendtime, recetime, detail);
    }

    @Override
    public int addAll(Integer clientid, Integer start, Integer end, Date sendtime, Date recetime, String detail) {
        return goodMapper.addAll(clientid, start, end, sendtime, recetime, detail);
    }

    @Override
    public List<Good> searchAllByFrom(Integer start) {
        return goodMapper.searchAllByFrom(start);
    }

    @Override
    public List<Good> searchAllByClientid(Integer clientid) {
        return goodMapper.searchAllByClientid(clientid);
    }

    @Override
    public List<Good> selectAllByIdAndClientid(Integer id, Integer clientid) {
//        System.out.println("==serImpl==");
//        System.out.println("id="+id+"::clientid"+clientid);
//        System.out.println("==serImpl==");
        return goodMapper.selectAllByIdAndClientid(id, clientid);
    }

    @Override
    public List<Good> searchAllByEnd(Integer end) {
        return goodMapper.searchAllByEnd(end);
    }

    @Override
    public int updateSendtimeAndRecetimeById(Date sendtime, Date recetime, Integer id) {
        return goodMapper.updateSendtimeAndRecetimeById(sendtime, recetime, id);
    }

    @Override
    public Integer selectEndById(Integer id) {
        return goodMapper.selectEndById(id);
    }

    @Override
    public Integer selectStartById(Integer id) {
        return goodMapper.selectStartById(id);
    }
}

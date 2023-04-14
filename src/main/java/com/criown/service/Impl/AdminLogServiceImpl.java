package com.criown.service.Impl;

import com.criown.entity.AdminLog;
import com.criown.mapper.AdminLogMapper;
import com.criown.service.AdminLogService;

import java.util.List;

public class AdminLogServiceImpl implements AdminLogService {

    private AdminLogMapper adminLogMapper;

    public void setAdminLogMapper(AdminLogMapper adminLogMapper) {
        this.adminLogMapper = adminLogMapper;
    }

    @Override
    public int delByUserid(int id) {
       return adminLogMapper.delByUserid(id);
    }

    //要改
    @Override
    public int updateUsernameAndUserpwdByUserid(String username,String userpwd,int userid) {
        return adminLogMapper.updateUsernameAndUserpwdByUserid(username,userpwd,userid);
    }

    @Override
    public int insertAll(AdminLog adminLog) {
        return adminLogMapper.insertAll(adminLog) ;
    }

    @Override
    public List<AdminLog> selectUseridByUserid(int id) {
        return adminLogMapper.selectUseridByUserid(id);
    }

    @Override
    public Integer selectUseridByUsername(String username) {
        return adminLogMapper.selectUseridByUsername(username);
    }

    @Override
    public String selectUserpwdByUserid(Integer id) {
        return adminLogMapper.selectUserpwdByUserid(id);
    }

    @Override
    public AdminLog selectByUserid(int id) {
        return adminLogMapper.selectByUserid(id);
    }

    @Override
    public int updateUserpwdByUserid(String userpwd, Integer userid) {
        return adminLogMapper.updateUserpwdByUserid(userpwd,userid);
    }


}

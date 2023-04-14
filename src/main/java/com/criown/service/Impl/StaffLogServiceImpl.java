package com.criown.service.Impl;

import com.criown.entity.StaffLog;
import com.criown.mapper.StaffLogMapper;
import com.criown.service.StaffLogService;

import java.util.List;

public class StaffLogServiceImpl implements StaffLogService {
    public StaffLogMapper staffLogMapper;

    public void setStaffLogMapper(StaffLogMapper staffLogMapper) {
        this.staffLogMapper = staffLogMapper;
    }

    @Override
    public String selectUserpwdByUserid(Integer userid) {
        return staffLogMapper.selectUserpwdByUserid(userid);
    }

    @Override
    public List<StaffLog> selectUseridByUserid(int userid) {
        return staffLogMapper.selectUseridByUserid(userid);
    }

    @Override
    public Integer selectUseridByUsername(String username) {
        return staffLogMapper.selectUseridByUsername(username);
    }

    @Override
    public int delByUserid(int userid) {
        return staffLogMapper.delByUserid(userid);
    }

    @Override
    public int updateUsernameAndUserpwdByUserid(String username, String userpwd, Integer userid) {
        return staffLogMapper.updateUsernameAndUserpwdByUserid(username,userpwd,userid);
    }

    @Override
    public int insertAll(StaffLog staffLog) {
        return staffLogMapper.insertAll(staffLog);
    }

    @Override
    public int updateUserpwdByUserid(String userpwd, Integer userid) {
        return staffLogMapper.updateUserpwdByUserid(userpwd, userid);
    }
}

package com.criown.service.Impl;

import com.criown.entity.ClientLog;
import com.criown.mapper.ClientLogMapper;
import com.criown.service.ClientLogService;


public class ClientLogServiceImpl implements ClientLogService {
    private ClientLogMapper clientLogMapper;

    public void setClientLogMapper(ClientLogMapper clientLogMapper) {
        this.clientLogMapper = clientLogMapper;
    }

    @Override
    public String selectUserpwdByUserid(Integer userid) {
        return clientLogMapper.selectUserpwdByUserid(userid);
    }

    @Override
    public int updateUsernameAndUserpwdByUserid(String username, String userpwd, int userid) {
        return clientLogMapper.updateUsernameAndUserpwdByUserid(username,userpwd,userid);
    }

    @Override
    public int deleteByUserid(int userid) {
        return clientLogMapper.deleteByUserid(userid);
    }

    @Override
    public int insertAll(ClientLog clientLog) {
        return clientLogMapper.insertAll(clientLog);
    }

    @Override
    public Integer selectUseridByUsername(String username) {
        return clientLogMapper.selectUseridByUsername(username);
    }

    @Override
    public int updateUserpwdByUserid(String userpwd, Integer userid) {
        return clientLogMapper.updateUserpwdByUserid(userpwd, userid);
    }
}

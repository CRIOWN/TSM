package com.criown.service.Impl;

import com.criown.entity.Client;
import com.criown.mapper.AdminLogMapper;
import com.criown.mapper.AdminMapper;
import com.criown.mapper.ClientMapper;
import com.criown.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientMapper clientMapper;

    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public List<Client> selectAll() {
        return clientMapper.selectAll();
    }

    @Override
    public List<Client> getAllByQuery(String name,String sex,String local) {
       return clientMapper.getAllByQuery(name, sex, local);
    }

    @Override
    public int addAll(String name, String sex, String local, int number, String detail) {
        return clientMapper.addAll(name, sex, local, number, detail);
    }

    @Override
    public int delById(List list) {
        return clientMapper.delById(list);
    }

    @Override
    public int delByIdSingle(Integer id) {
        return clientMapper.delByIdSingle(id);
    }

    @Override
    public int updateAllById( String name, String sex, String local, String detail, Integer number, Integer oldId) {
        return clientMapper.updateAllById(name, sex, local, detail, number, oldId);
    }
}

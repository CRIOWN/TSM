package com.criown.service.Impl;

import com.criown.entity.Admin;
import com.criown.mapper.AdminMapper;
import com.criown.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectAll();
    }

    @Override
    public int updateNameAndSexAndLocalAndPhoneAndDetailByUserid(String name, String sex, String local, Integer phone, String detail, Integer userid) {
        return adminMapper.updateNameAndSexAndLocalAndPhoneAndDetailByUserid(name, sex, local, phone, detail, userid);
    }
}

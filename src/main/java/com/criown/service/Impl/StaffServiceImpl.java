package com.criown.service.Impl;


import com.criown.entity.Staff;
import com.criown.mapper.StaffMapper;
import com.criown.service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    public StaffMapper staffMapper;

    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public List<Staff> selectAll() {
        return staffMapper.selectAll();
    }

    @Override
    public List<Staff> getAllByQuery(String name, String sex, String career, String local) {
        return staffMapper.getAllByQuery(name, sex, career, local);
    }

    @Override
    public int addAll(String name, String sex, String local, int number, String career, String detail) {
        return staffMapper.addAll(name, sex, local, number, career, detail);
    }

    @Override
    public int delById(List list) {
        return staffMapper.delById(list);
    }

    @Override
    public int delByIdSingle(Integer id) {
        return staffMapper.delByIdSingle(id);
    }

    @Override
    public int updateAddById(Integer number, String career, String detail, String local, String name, String sex, Integer oldId) {
        return staffMapper.updateAddById(number, career, detail, local, name, sex, oldId);
    }

    @Override
    public int updateNameAndSexAndLocalAndNumberAndCareerAndDetailById(String name, String sex, String local, Integer number, String career, String detail, Integer id) {
        return staffMapper.updateNameAndSexAndLocalAndNumberAndCareerAndDetailById(name, sex, local, number, career, detail, id);
    }

    @Override
    public int updateNameAndSexAndLocalAndNumberAndDetailById(String name, String sex, String local, Integer number, String detail, Integer id) {
        return staffMapper.updateNameAndSexAndLocalAndNumberAndDetailById(name, sex, local, number, detail, id);
    }
}

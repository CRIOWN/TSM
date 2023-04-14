package com.criown.service.Impl;

import com.criown.entity.Car;
import com.criown.mapper.CarMapper;
import com.criown.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private CarMapper carMapper;

    public void setCarMapper(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> selectAll() {
        return carMapper.selectAll();
    }
}

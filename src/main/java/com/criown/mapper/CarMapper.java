package com.criown.mapper;
import java.util.List;

import com.criown.entity.Car;

/**
* @author STY
* @description 针对表【car】的数据库操作Mapper
* @createDate 2023-04-08 14:54:36
* @Entity com.criown.entity.Car
*/
public interface CarMapper {
    List<Car> selectAll();

}





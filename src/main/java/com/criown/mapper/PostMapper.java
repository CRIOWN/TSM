package com.criown.mapper;
import java.util.List;

import com.criown.entity.Post;

/**
* @author STY
* @description 针对表【post】的数据库操作Mapper
* @createDate 2023-04-07 15:02:35
* @Entity com.criown.entity.Post
*/
public interface PostMapper {

    List<Post> selectAll();

}





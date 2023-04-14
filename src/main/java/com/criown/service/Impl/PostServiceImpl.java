package com.criown.service.Impl;

import com.criown.entity.Post;
import com.criown.mapper.PostMapper;
import com.criown.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private PostMapper postMapper;

    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> selectAll() {
        return postMapper.selectAll();
    }
}

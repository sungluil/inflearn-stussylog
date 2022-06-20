package com.example.blog2022.repository;

import com.example.blog2022.domain.Post;
import com.example.blog2022.vo.PageDto;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PageDto pageDto);
}

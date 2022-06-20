package com.example.blog2022.repository;

import com.example.blog2022.domain.Post;
import com.example.blog2022.domain.QPost;
import com.example.blog2022.vo.PageDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PageDto pageDto) {
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(pageDto.getSize())
                .offset(pageDto.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}

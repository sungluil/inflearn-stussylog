package com.example.blog2022.repository;

import com.example.blog2022.domain.Post;
import com.example.blog2022.domain.QPost;
import com.example.blog2022.vo.PageDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PageDto pageDto) {
        log.info("pageDto={}", pageDto);
        log.info("offset={}", pageDto.getOffset());
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(pageDto.getSize())
                .offset(pageDto.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}

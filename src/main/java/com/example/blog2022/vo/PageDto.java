package com.example.blog2022.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder @Getter
public class PageDto {
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    public long getOffset() {
        return (long) (page - 1) * size;
    }
}

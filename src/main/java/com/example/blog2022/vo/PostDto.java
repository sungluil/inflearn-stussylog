package com.example.blog2022.vo;

import com.example.blog2022.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @ToString
public class PostDto {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int age;

    public PostDto(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.age = post.getAge();
    }


    @Builder
    public PostDto(Long id, String name, int age) {
        this.id = id;
        this.name = name.substring(0, Math.min(name.length(), 10));//둘중에 작은값을 가져오기때문에 10자이상이면 10, 10보다작으면 문자열길이만큼 가져옴
        this.age = age;
    }
}

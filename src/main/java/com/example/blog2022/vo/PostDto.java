package com.example.blog2022.vo;

import com.example.blog2022.domain.Post;
import com.example.blog2022.exception.BaseExceptionType;
import com.example.blog2022.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @ToString @Setter
public class PostDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String age;

    public PostDto(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.age = post.getAge();
    }

    @Builder
    public PostDto(Long id, String name, String age) {
        this.id = id;
        this.name = name.substring(0, Math.min(name.length(), 10));//둘중에 작은값을 가져오기때문에 10자이상이면 10, 10보다작으면 문자열길이만큼 가져옴
        this.age = age;
    }

    public void valid() {
        if ("길동".equals(this.name)) {
            throw new InvalidRequest(BaseExceptionType.ERROR_404);
        }
    }
}

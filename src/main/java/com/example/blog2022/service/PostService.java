package com.example.blog2022.service;

import com.example.blog2022.domain.Post;
import com.example.blog2022.exception.PostNotFound;
import com.example.blog2022.repository.PostRepository;
import com.example.blog2022.vo.PageDto;
import com.example.blog2022.vo.PostDto;
import com.example.blog2022.vo.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public void save(PostDto postDto) {
        Post post = Post.builder()
                .name(postDto.getName())
                .age(postDto.getAge())
                .build();
        postRepository.save(post).from();
    }

    public PostDto get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new PostNotFound());

        return PostDto.builder()
                .id(post.getId())
                .name(post.getName())
                .age(post.getAge())
                .build();
    }

    public List<PostRequest> getList(PageDto pageDto) {
        //Pageable pageable = PageRequest.of(page, 10, DESC, "id");
        //return postRepository.findAll(pageable).stream().map(PostDto::new).collect(Collectors.toList());
        return postRepository.getList(pageDto).stream().map(PostRequest::new).collect(Collectors.toList());
    }

    public void multiSave() {
        List<Post> collect = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder().name("name" + i).age(i).build())
                .collect(Collectors.toList());
        postRepository.saveAll(collect);
    }
}

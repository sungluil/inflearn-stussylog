package com.example.blog2022.service;

import com.example.blog2022.domain.Post;
import com.example.blog2022.exception.BaseExceptionType;
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
    public PostDto save(PostDto postDto) {
        Post post = Post.builder()
                .name(postDto.getName())
                .age(postDto.getAge())
                .build();

        return new PostDto(postRepository.save(post));
    }

    public PostDto get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new PostNotFound(BaseExceptionType.ERROR_400));

        return new PostDto(post);
    }

    public List<PostRequest> getList(PageDto pageDto) {
        //Pageable pageable = PageRequest.of(page, 10, DESC, "id");
        //return postRepository.findAll(pageable).stream().map(PostDto::new).collect(Collectors.toList());
        return postRepository.getList(pageDto).stream().map(PostRequest::new).collect(Collectors.toList());
    }

    public void multiSave() {
        List<Post> collect = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder().name("name" + i).age(""+i).build())
                .collect(Collectors.toList());
        postRepository.saveAll(collect);
    }

    public PostDto update(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new PostNotFound(BaseExceptionType.ERROR_400));

        post.setName(postDto.getName());
        post.setAge(postDto.getAge());
        return postRepository.save(post).from();
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new PostNotFound(BaseExceptionType.ERROR_400));

        postRepository.delete(post);
    }
}

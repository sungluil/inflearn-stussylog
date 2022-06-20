package com.example.blog2022.controller;

import com.example.blog2022.service.PostService;
import com.example.blog2022.vo.PageDto;
import com.example.blog2022.vo.PostDto;
import com.example.blog2022.vo.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Map<String, String> post(@RequestBody @Valid PostDto postDto, Errors errors) {
        Map<String, String> errorMap = new HashMap<>();
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return errorMap;
        }
        log.info("postDto = {}", postDto);
        return new HashMap<>();
    }

    @PostMapping("/post1")
    public String post1() {
        return "hello world";
    }

    @PostMapping("/post2")
    public Map<String, String> post2(@RequestBody @Valid PostDto postDto) {
        log.info("postDto = {}", postDto);
        postService.save(postDto);
        return new HashMap<>();
    }

    @GetMapping("/post/{postId}")
    public PostDto get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }

    @PostMapping("/posts")
    public List<PostRequest> getList(@ModelAttribute PageDto pageDto) {
        log.info("pageDto={}", pageDto);

        // 20개 저장
        postService.multiSave();

        return postService.getList(pageDto);
    }
}

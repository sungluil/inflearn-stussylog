package com.example.blog2022.controller;

import com.example.blog2022.service.PostService;
import com.example.blog2022.vo.PageDto;
import com.example.blog2022.vo.PostDto;
import com.example.blog2022.vo.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String index() {
        return "hello world";
    }

//    @PostMapping("/post")
//    public Map<String, String> post(@RequestBody @Valid PostDto postDto, Errors errors) {
//        Map<String, String> errorMap = new HashMap<>();
//        if (errors.hasErrors()) {
//            for (FieldError error : errors.getFieldErrors()) {
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//            return errorMap;
//        }
//        log.info("postDto = {}", postDto);
//        return new HashMap<>();
//    }

    @PostMapping("/post")
    public PostDto post(@RequestBody @Valid PostDto postDto) {
        postDto.valid();
        log.info("postDto = {}", postDto);
        return postService.save(postDto);
    }

    @GetMapping("/post/{postId}")
    public PostDto get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }

    @PatchMapping("/post/{postId}")
    public PostDto update( @PathVariable(name = "postId") Long id, @RequestBody @Valid PostDto postDto) {
        return postService.update(id, postDto);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable(name = "postId") Long id) {
        postService.delete(id);
    }

    @GetMapping("/post/add")
    public void add() {
        postService.multiSave();
    }

    @GetMapping("/posts")
    public List<PostRequest> getList(@RequestParam(value = "page") int pageNo) {
        log.info("pageNo={}", pageNo);
        return postService.getList(new PageDto(pageNo, 10));
    }
//    @PostMapping("/posts")
//    public List<PostRequest> getList(@ModelAttribute PageDto pageDto) {
//        log.info("pageDto={}", pageDto);
//
//        // 20개 저장
//        postService.multiSave();
//
//        return postService.getList(pageDto);
//    }
}

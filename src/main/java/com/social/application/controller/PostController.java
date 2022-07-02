package com.social.application.controller;

import com.social.application.model.PostDto;
import com.social.domain.model.Post;
import com.social.useCase.PostInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostInteractor postInteractor;

    @Autowired
    public PostController(PostInteractor postInteractor) {
        this.postInteractor = postInteractor;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Post> posts = postInteractor.findAll();
        List<PostDto> postDtoList = posts.stream().map(PostDto::fromModel).collect(Collectors.toList());
        return postDtoList.isEmpty() ?
                new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(postInteractor.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePost(@ModelAttribute PostDto post) throws IOException {
        Post postModel = post.toModel();
        return new ResponseEntity<>(postInteractor.save(postModel), HttpStatus.OK);
    }

    @GetMapping("/author/{userId}")
    public ResponseEntity<?> getByAuthor(@PathVariable Long userId) {
        List<Post> posts = postInteractor.findAllByAuthor(userId);
        List<PostDto> postDtoList = posts.stream().map(PostDto::fromModel).collect(Collectors.toList());
        return postDtoList.isEmpty() ?
                new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}

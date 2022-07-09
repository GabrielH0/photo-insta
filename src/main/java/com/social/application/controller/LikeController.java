package com.social.application.controller;

import com.social.domain.model.Post;
import com.social.useCase.LikeInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeInteractor likeInteractor;

    @Autowired
    public LikeController(LikeInteractor likeInteractor) {
        this.likeInteractor = likeInteractor;
    }

    @PostMapping("/like")
    public ResponseEntity<Post> likePost(@RequestParam Long userId, Long postId) {
        return new ResponseEntity(likeInteractor.likePost(postId, userId), HttpStatus.OK);
    }

    @PostMapping("/dislike")
    public ResponseEntity<Post> dislikePost(@RequestParam Long userId, Long postId) {
        return new ResponseEntity(likeInteractor.dislikePost(postId, userId), HttpStatus.OK);
    }
}

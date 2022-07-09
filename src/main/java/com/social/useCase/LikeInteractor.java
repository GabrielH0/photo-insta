package com.social.useCase;

import com.social.domain.adapter.LikeRepository;
import com.social.domain.adapter.PostRepository;
import com.social.domain.adapter.UserRepository;
import com.social.domain.model.Like;
import com.social.domain.model.Post;
import com.social.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeInteractor {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public LikeInteractor(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public Post likePost(Long postId, Long userId) {
        User user = userRepository.findById(userId);
        Post post = postRepository.findById(postId);
        Like like = post.likePost(user);
        likeRepository.save(like);
        return post;
    }

    public Post dislikePost(Long postId, Long userId) {
        Like like = likeRepository.findByUserAndPost(userId, postId);
        Post post = postRepository.findById(postId);
        post.dislikePost(like);
        likeRepository.delete(like.getId());
        return post;
    }
}

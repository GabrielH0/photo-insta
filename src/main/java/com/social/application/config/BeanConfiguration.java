package com.social.application.config;

import com.social.domain.adapter.LikeRepository;
import com.social.domain.adapter.PostRepository;
import com.social.domain.adapter.UserRepository;
import com.social.infra.amazonS3.AmazonS3Storage;
import com.social.useCase.LikeInteractor;
import com.social.useCase.PostInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PostInteractor postInteractor(PostRepository postRepository, AmazonS3Storage amazonS3Storage) {
        return new PostInteractor(postRepository, amazonS3Storage);
    }

    @Bean
    public LikeInteractor likeInteractor(PostRepository postRepository, UserRepository userRepository,
                                         LikeRepository likeRepository) {
        return new LikeInteractor(postRepository, userRepository, likeRepository);
    }
}
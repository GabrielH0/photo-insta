package com.social.application.config;

import com.social.domain.adapter.PostRepository;
import com.social.infra.database.amazonS3.AmazonS3Storage;
import com.social.useCase.PostInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PostInteractor postInteractor(PostRepository postRepository, AmazonS3Storage amazonS3Storage) {
        return new PostInteractor(postRepository, amazonS3Storage);
    }
}

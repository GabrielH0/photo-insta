package com.social.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Like {

    private String id;
    private User user;
    private LocalDateTime liked_at;
    private Post post;
}

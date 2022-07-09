package com.social.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Post {

    private Long id;
    private User author;
    private String description;
    private Coordinates coordinates;
    private ImageFile image;
    private List<Like> likes;

    public Like likePost(User user) {
        if (likes == null ){
            likes = new ArrayList<>();
        }
        Like like = Like.builder().user(user).liked_at(LocalDateTime.now()).post(this).build();
        likes.add(like);
        return like;
    }

    public void dislikePost(Like like) {
        likes.remove(like);
    }

}

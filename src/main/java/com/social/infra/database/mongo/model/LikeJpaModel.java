package com.social.infra.database.mongo.model;

import com.social.domain.model.Like;
import com.social.domain.model.Post;
import com.social.domain.model.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@Document(collection = "like")
public class LikeJpaModel {

    @Id
    private final String id;
    private final LocalDateTime liked_at;
    private final Long userId;
    private final Long postId;

    public Like toModel() {
        return Like.builder()
                .id(id)
                .user(User.builder().id(userId).build())
                .liked_at(liked_at)
                .post(Post.builder().id(postId).build())
                .build();
    }

    public static LikeJpaModel fromModel(Like like) {
        return LikeJpaModel.builder()
                .id(like.getId())
                .liked_at(like.getLiked_at())
                .userId(like.getUser().getId())
                .postId(like.getPost().getId())
                .build();
    }
}

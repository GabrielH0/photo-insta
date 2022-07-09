package com.social.domain.adapter;

import com.social.domain.model.Like;

public interface LikeRepository extends BaseRepository<Like>{
    Like findByUserAndPost(Long userId, Long postId);

    void delete(String likeId);
}

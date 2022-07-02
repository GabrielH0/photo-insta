package com.social.domain.adapter;

import com.social.domain.model.Post;

import java.util.List;

public interface PostRepository extends BaseRepository<Post> {

    List<Post> findByAuthor(Long userId);
}

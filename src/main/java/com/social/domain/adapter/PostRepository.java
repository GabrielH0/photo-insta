package com.social.domain.adapter;

import com.social.domain.model.Post;
import com.social.domain.model.User;

import java.util.List;

public interface PostRepository extends BaseRepository<Post> {

    List<Post> findByAuthor(User author);
}

package com.social.useCase;

import com.social.domain.adapter.ObjectStorage;
import com.social.domain.adapter.PostRepository;
import com.social.domain.model.Post;
import com.social.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostInteractor implements BaseUseCase<Post> {

    private PostRepository postRepository;
    private ObjectStorage objectStorage;

    @Autowired
    public PostInteractor(PostRepository postRepository, ObjectStorage objectStorage) {
        this.postRepository = postRepository;
        this.objectStorage = objectStorage;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        posts.forEach(post-> post.getImage().setContent(objectStorage.getObject(post.getImage().getUniqueName())));
        return posts;
    }

    @Override
    public Post save(Post post) {
        try {
            objectStorage.putObject(post.getImage());
            return postRepository.save(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Post> findAllByAuthor(Long userId) {
        List<Post> posts = postRepository.findByAuthor(userId);
        posts.forEach(post-> post.getImage().setContent(objectStorage.getObject(post.getImage().getUniqueName())));
        return posts;
    }
}

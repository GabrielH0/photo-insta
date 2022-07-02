package com.social.infra.database.repository;

import com.social.domain.adapter.PostRepository;
import com.social.domain.model.Post;
import com.social.domain.model.User;
import com.social.infra.database.model.PostJpaModel;
import com.social.infra.database.model.UserJpaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface PostJpaRepository extends CrudRepository<PostJpaModel, Long> {

    @Query("select post, file from PostJpaModel post left join S3File file on post.image = file")
    List<PostJpaModel> findAll();

    @Query("select post, file from PostJpaModel post left join S3File file on post.image = file" +
            " where post.author.id = :authorId")
    List<PostJpaModel> findAllByAuthorId(Long authorId);

    @Repository
    class PostPostgresRepository implements PostRepository {

        private final PostJpaRepository postJpaRepository;

        @Autowired
        public PostPostgresRepository(PostJpaRepository postJpaRepository) {
            this.postJpaRepository = postJpaRepository;
        }

        @Override
        public Post findById(Long id) {
            Optional<PostJpaModel> optional = postJpaRepository.findById(id);
            return optional.map(PostJpaModel::toModel).orElse(null);
        }

        @Override
        public List<Post> findAll() {
            return postJpaRepository.findAll().stream().map(PostJpaModel::toModel).collect(Collectors.toList());
        }

        @Override
        public Post save(Post post) {
            return postJpaRepository.save(PostJpaModel.fromModel(post)).toModel();
        }

        @Override
        public List<Post> findByAuthor(Long userId) {
            return postJpaRepository.findAllByAuthorId(userId).stream().map(PostJpaModel::toModel)
                    .collect(Collectors.toList());
        }
    }
}

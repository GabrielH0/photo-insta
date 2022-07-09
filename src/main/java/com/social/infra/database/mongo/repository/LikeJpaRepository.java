package com.social.infra.database.mongo.repository;

import com.social.domain.adapter.LikeRepository;
import com.social.domain.model.Like;
import com.social.infra.database.mongo.model.LikeJpaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface LikeJpaRepository extends MongoRepository<LikeJpaModel, Long> {

    @Query("{'userId' : ?0, 'postId': ?1}")
    Optional<LikeJpaModel> findByUseridAndPostId(Long userId, Long postId);

    @Repository
    class MongoLikeRepository implements LikeRepository {

        private final LikeJpaRepository likeJpaRepository;

        @Autowired
        public MongoLikeRepository(LikeJpaRepository likeJpaRepository) {
            this.likeJpaRepository = likeJpaRepository;
        }

        @Override
        public com.social.domain.model.Like findById(Long id) {
            Optional<LikeJpaModel> likeOptional = likeJpaRepository.findById(id);
            return likeOptional.map(LikeJpaModel::toModel).orElse(null);
        }

        @Override
        public List<com.social.domain.model.Like> findAll() {
            return likeJpaRepository.findAll().stream()
                    .map(LikeJpaModel::toModel)
                    .collect(Collectors.toList());
        }

        @Override
        public com.social.domain.model.Like save(com.social.domain.model.Like like) {
            return likeJpaRepository.save(LikeJpaModel.fromModel(like)).toModel();
        }

        @Override
        public Like findByUserAndPost(Long userId, Long postId) {
            return likeJpaRepository.findByUseridAndPostId(userId, postId).map(LikeJpaModel::toModel).orElse(null);
        }

        @Override
        public void delete(String likeId) {
            likeJpaRepository.delete(LikeJpaModel.builder().id(likeId).build());
        }
    }
}

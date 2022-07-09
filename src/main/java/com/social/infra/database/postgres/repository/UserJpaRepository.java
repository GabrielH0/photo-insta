package com.social.infra.database.postgres.repository;

import com.social.domain.adapter.UserRepository;
import com.social.domain.model.User;
import com.social.infra.database.postgres.model.UserJpaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface UserJpaRepository extends CrudRepository<UserJpaModel, Long> {

    List<UserJpaModel> findAll();

    @Repository
    class UserPostgresRepository implements UserRepository {

        private UserJpaRepository userJpaRepository;

        public UserPostgresRepository(UserJpaRepository userJpaRepository) {
            this.userJpaRepository = userJpaRepository;
        }

        @Override
        public User findById(Long id) {
            return userJpaRepository.findById(id).map(UserJpaModel::toModel).orElse(null);
        }

        @Override
        public List<User> findAll() {
            return userJpaRepository.findAll().stream()
                    .map(UserJpaModel::toModel)
                    .collect(Collectors.toList());
        }

        @Override
        public User save(User user) {
            return userJpaRepository.save(UserJpaModel.fromModel(user)).toModel();
        }
    }
}

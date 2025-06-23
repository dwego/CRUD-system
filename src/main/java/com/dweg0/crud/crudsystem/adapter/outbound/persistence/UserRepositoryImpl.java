package com.dweg0.crud.crudsystem.adapter.outbound.persistence;

import com.dweg0.crud.crudsystem.core.domain.User;
import com.dweg0.crud.crudsystem.core.usecase.UserRepository;
import com.dweg0.crud.crudsystem.adapter.outbound.persistence.repository.UserJpaRepository;
import com.dweg0.crud.crudsystem.adapter.outbound.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public void save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        jpaRepository.save(entity);
    }

    public void delete(User user) {
        jpaRepository.deleteById(user.getId());
    }

    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(UserEntity::toDomain);
    }

    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserEntity::toDomain);
    }

}

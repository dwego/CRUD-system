package com.dweg0.crud.crudsystem.adapter.outbound.persistence.repository;

import com.dweg0.crud.crudsystem.adapter.outbound.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}

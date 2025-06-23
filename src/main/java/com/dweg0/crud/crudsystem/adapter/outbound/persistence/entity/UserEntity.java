package com.dweg0.crud.crudsystem.adapter.outbound.persistence.entity;

import com.dweg0.crud.crudsystem.core.domain.Email;
import com.dweg0.crud.crudsystem.core.domain.Password;
import com.dweg0.crud.crudsystem.core.domain.Post;
import com.dweg0.crud.crudsystem.core.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String bio;
    @Column(nullable = false)
    private String avatarUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();


    public static UserEntity fromDomain(User user) {
        List<PostEntity> postEntities = user.getPosts() != null
                ? user.getPosts().stream()
                .map(PostEntity::fromDomain)
                .toList()
                : new ArrayList<>();

        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail().value(),
                user.getPassword().value(),
                user.getBio(),
                user.getAvatarUrl(),
                postEntities
        );
    }

    public User toDomain() {
        List<Post> domainPosts = posts != null
                ? posts.stream()
                .map(PostEntity::toDomain)
                .toList()
                : new ArrayList<>();

        return User.builder()
                .id(id)
                .username(username)
                .name(name)
                .email(new Email(email))
                .password(new Password(password))
                .bio(bio)
                .avatarUrl(avatarUrl)
                .posts(domainPosts)
                .build();
    }
}
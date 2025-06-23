package com.dweg0.crud.crudsystem.adapter.outbound.persistence.entity;

import com.dweg0.crud.crudsystem.core.domain.Post;
import com.dweg0.crud.crudsystem.core.domain.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    private UUID id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag")
    )
    private List<TagEntity> tags;

    private String contentMD;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static PostEntity fromDomain(Post post) {
        List<TagEntity> tagEntities = post.getTags().stream()
                .map(TagEntity::fromDomain)
                .collect(Collectors.toList());

        UserEntity userEntity = UserEntity.fromDomain(post.getUser());

        return new PostEntity(
                post.getId(),
                post.getTitle(),
                tagEntities,
                post.getContentMD(),
                userEntity
        );
    }

    public Post toDomain() {
        List<Tag> domainTags = tags.stream()
                .map(TagEntity::toDomain)
                .collect(Collectors.toList());

        return new Post(
                user.toDomain(),
                title,
                contentMD,
                domainTags
        );
    }
}

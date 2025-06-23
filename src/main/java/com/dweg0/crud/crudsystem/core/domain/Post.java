package com.dweg0.crud.crudsystem.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Post {
    private UUID id;
    private User user;
    private String title;
    private String contentMD;
    private List<Tag> tags = new ArrayList<>();

    public Post(User user, String title, String contentMD, List<Tag> tags) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.title = title;
        this.contentMD = contentMD;
        this.tags = tags;
    }

    public void updatePost(Post post) {
        this.title = post.getTitle();
        this.contentMD = post.getContentMD();
        this.tags = post.getTags();
    }
}
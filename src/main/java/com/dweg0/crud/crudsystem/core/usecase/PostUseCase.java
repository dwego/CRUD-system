package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.model.Post;
import com.dweg0.crud.crudsystem.core.domain.model.Tag;
import com.dweg0.crud.crudsystem.core.domain.model.User;

import java.util.List;

public class PostUseCase {
    public Post createPost(User user, String title, String contentMD, List<Tag> tags) {
        return new Post(user, title, contentMD, tags);
    }
}

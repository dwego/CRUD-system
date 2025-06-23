package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.Post;
import com.dweg0.crud.crudsystem.core.domain.User;

import java.util.List;
import java.util.UUID;

public interface PostRepository {
    void save(Post post);
    void delete(Post post);
    List<Post> findByUser(User user);

    Post findPostById(UUID id);
}

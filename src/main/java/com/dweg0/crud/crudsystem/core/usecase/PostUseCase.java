package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.Post;
import com.dweg0.crud.crudsystem.core.domain.Tag;
import com.dweg0.crud.crudsystem.core.domain.User;
import com.dweg0.crud.crudsystem.core.exception.PostNotFoundException;

import java.util.List;
import java.util.UUID;

public class PostUseCase {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostUseCase(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(User user, String title, String contentMD, List<Tag> tags) {
        Post newPost = new Post(user, title, contentMD, tags);
        user.addPost(newPost);
        saveEntities(newPost, user);
        return newPost;
    }

    public void deletePost(User currentUser, Post postToDelete) {
        currentUser.removePost(postToDelete);
        postRepository.delete(postToDelete);
        userRepository.save(currentUser);
    }

    public void updatePost(User currentUser, Post modifiedPost) {
        Post existingPost = findAndValidatePost(modifiedPost.getId());
        existingPost.updatePost(modifiedPost);
        currentUser.updatePost(existingPost);
        saveEntities(existingPost, currentUser);
    }

    private void saveEntities(Post post, User user) {
        postRepository.save(post);
        userRepository.save(user);
    }

    private Post findAndValidatePost(UUID postId) {
        Post post = postRepository.findPostById(postId);
        if (post == null) {
            throw new PostNotFoundException(postId);
        }
        return post;
    }
}
package com.dweg0.crud.crudsystem.core.domain;

import com.dweg0.crud.crudsystem.core.exception.PostNotFoundException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class User {

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Builder.Default
    private String avatarUrl = "https://cdn.jsdelivr.net/gh/alohe/avatars/png/3d_4.png";
    private String username;
    private String name;
    private Email email;
    private Password password;

    @Builder.Default
    private String bio = "";

    private List<Post> posts = new ArrayList<>();

    public void addPost(final Post post) {
        posts.add(post);
    }

    public void removePost(final Post post) {
        posts.remove(post);
    }

    public void updateUser(User newUser) {
        if (newUser == null) throw new IllegalArgumentException("This modified user cannot be null");
        this.username = newUser.getUsername();
        this.name = newUser.getName();
        this.email = newUser.getEmail();
        this.password = newUser.getPassword();
        this.bio = newUser.getBio();
        this.avatarUrl = newUser.getAvatarUrl();
    }

    public void updatePost(final Post modifiedPost) {
        validatePost(modifiedPost);
        int postIndex = findPostIndexById(modifiedPost.getId());
        if (postIndex == -1) {
            throw new PostNotFoundException();
        }
        posts.set(postIndex, modifiedPost);
    }

    private void validatePost(final Post post) {
        if (post == null || post.getId() == null) {
            throw new IllegalArgumentException("Post or Post ID cannot be null");
        }
    }

    private int findPostIndexById(final java.util.UUID postId) {
        for (int i = 0; i < posts.size(); i++) {
            if (postId.equals(posts.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "User: " +
                "name: '" + name + '\'' +
                ", email: '" + email + '\'';
    }
}
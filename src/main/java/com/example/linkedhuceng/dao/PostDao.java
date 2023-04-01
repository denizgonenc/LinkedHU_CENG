package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostDao {

    void addPost(Post post);
    void addComment(Post post, Comment comment);
    List<Post> getAllPost();
    List<Comment> getAllComment();
    Optional<Post> getPostById(UUID id);
    void deletePostById(UUID id);
    void deleteCommentById(UUID id);
    void updatePost(Post post);



}

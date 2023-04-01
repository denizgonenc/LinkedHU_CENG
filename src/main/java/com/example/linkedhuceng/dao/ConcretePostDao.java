package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.repository.CommentRepository;
import com.example.linkedhuceng.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository  // a type of component
public class ConcretePostDao implements PostDao{
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ConcretePostDao(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void addComment(Post post, Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(UUID id) {
        return postRepository.findById(id);
    }

    @Override
    public void deletePostById(UUID id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteCommentById(UUID id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

}

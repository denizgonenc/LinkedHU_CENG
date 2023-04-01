package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.relation.Like;
import com.example.linkedhuceng.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDao {
    private final LikeRepository likeRepository;

    @Autowired
    public LikeDao(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like findLikeByUserAndPost(User user, Post post){
        return likeRepository.findLikeByUserAndPost(user, post);
    }

    public void saveLike(Like like){
        likeRepository.save(like);
    }

    public void deleteLike(Like like){
        this.likeRepository.delete(like);
    }
}

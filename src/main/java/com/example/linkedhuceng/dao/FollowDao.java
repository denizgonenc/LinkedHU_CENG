package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.relation.Follow;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDao {
    private final FollowRepository followRepository;

    @Autowired
    public FollowDao(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public boolean isFollowing(User from, User to){
        Follow check = followRepository.findFollowByFromAndTo(from, to);
        return (check != null);
    }

    public void followUser(User from, User to){
        Follow follow = new Follow(from, to);
        followRepository.save(follow);

    }

    public void unfollowUser(User from, User to){
        Follow follow = followRepository.findFollowByFromAndTo(from, to);
        followRepository.delete(follow);
    }

}

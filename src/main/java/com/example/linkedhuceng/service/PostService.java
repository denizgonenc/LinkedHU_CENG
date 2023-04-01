package com.example.linkedhuceng.service;

import com.example.linkedhuceng.dao.LikeDao;
import com.example.linkedhuceng.dao.NotificationDao;
import com.example.linkedhuceng.dao.PostDao;
import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.Notification;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.relation.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // it was Service in the video
public class PostService {
    private final PostDao postDao;
    private final LikeDao likeDao;
    public final NotificationDao notificationDao;

    @Autowired
    public PostService(PostDao postDao, LikeDao likeDao, NotificationDao notificationDao) {
        this.postDao = postDao;
        this.likeDao = likeDao;
        this.notificationDao = notificationDao;
    }

    public void addPost(Post post){
        postDao.addPost(post);
    }

    public List<Post> getAllPosts(){
        return postDao.getAllPost();
    }

    public Optional<Post> getPostById(UUID id){
        return postDao.getPostById(id);
    }

    public void deletePost(UUID id){
        postDao.deletePostById(id);
    }

    public void deleteComment(UUID id){
        postDao.deleteCommentById(id);
    }

    public void updatePost(Post post){
        postDao.updatePost(post);
    }

    public void addComment(Post post, Comment comment){
        postDao.addComment(post, comment);
    }

    public List<Comment> getAllComment(){
        return postDao.getAllComment();
    }
    public void likePost(User user, Post post){
        Like like = new Like(user, post);
        likeDao.saveLike(like);
    }

    public void addNotification(Notification notification){
        if(notification.getType().equals("1") || notification.getType().equals("2") ||
                (notificationDao.findNotificationByFromToType(notification.getFrom(), notification.getTo(), notification.getType()) == null)) {
            notificationDao.saveNotification(notification);
        }
    }

    public void unlikePost(User user, Post post){
        Like like = likeDao.findLikeByUserAndPost(user, post);
        likeDao.deleteLike(like);
    }

    public boolean isPostLikedByUser(User user, Post post){
        return (likeDao.findLikeByUserAndPost(user, post) != null);
    }

    public List<Post> filterFeed(User user){
        List<User> blockedUsers = user.getBlockedUsers();
        return getAllPosts()
                .stream()
                .filter(post -> !blockedUsers.contains(post.getOwner()))
                .filter(post -> post.getGroup() == null)
                .collect(Collectors.toList());
    }
}


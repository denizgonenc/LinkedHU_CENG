package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.Group;
import com.example.linkedhuceng.entity.GroupPost;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.relation.Membership;
import com.example.linkedhuceng.repository.GroupPostRepository;
import com.example.linkedhuceng.repository.GroupRepository;
import com.example.linkedhuceng.repository.MembershipRepository;
import com.example.linkedhuceng.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class GroupDao {
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final PostRepository feedRepository;

    @Autowired
    public GroupDao(GroupRepository groupRepository, MembershipRepository membershipRepository, PostRepository feedRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.feedRepository = feedRepository;
    }

    public void addGroup(Group group){
        groupRepository.save(group);
    }

    public Group findGroupById(UUID id){
        return groupRepository.findById(id).orElseThrow();
    }

    public void saveMembership(Membership membership){
        membershipRepository.save(membership);
    }
    public void removeMember(User user, Group group){
        Membership membership = membershipRepository.findMembership(user, group);
        membershipRepository.delete(membership);
    }

    public boolean isUserMember(User user, Group group){
        Membership membership = membershipRepository.findMembership(user, group);
        return (membership != null);
    }

    public void deleteGroupById(UUID id) {
        groupRepository.deleteById(id);
    }

    public void addPost(Post post) {
        feedRepository.save(post);
    }

    public Post getPostById(UUID id) {
        return feedRepository.findById(id).orElseThrow();
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }
}

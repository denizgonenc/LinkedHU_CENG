package com.example.linkedhuceng.service;

import com.example.linkedhuceng.dao.GroupDao;
import com.example.linkedhuceng.entity.*;
import com.example.linkedhuceng.entity.relation.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GroupService {
    private final GroupDao groupDao;
    private final FileStorageService fileStorageService;

    @Autowired
    public GroupService(GroupDao groupDao, FileStorageService fileStorageService) {
        this.groupDao = groupDao;
        this.fileStorageService = fileStorageService;
    }

    public Group createGroup(User user, String name, String description, MultipartFile gpMulti, MultipartFile coverMulti) throws IOException {
        File cover;
        File gp;

        if(!coverMulti.isEmpty()){
            String fileName = StringUtils.cleanPath(coverMulti.getOriginalFilename());
            cover = new File(fileName, coverMulti.getContentType(), coverMulti.getBytes());
        }
        else{
            java.io.File coverFile = new java.io.File("src/main/webapp/resources/static/img/default-cover-page.png");
            byte[] coverFileContent = Files.readAllBytes(coverFile.toPath());
            cover = new File("commonCover", "image/png", coverFileContent);
        }
        fileStorageService.saveFile(cover);

        if(!gpMulti.isEmpty()){
            String fileName = StringUtils.cleanPath(gpMulti.getOriginalFilename());
            gp = new File(fileName, gpMulti.getContentType(), gpMulti.getBytes());
            fileStorageService.saveFile(gp);
        }
        else{
            java.io.File ppFile = new java.io.File("src/main/webapp/resources/static/img/user-image.png");
            byte[] ppFileContent = Files.readAllBytes(ppFile.toPath());
            gp = new File("commonPp", "image/png", ppFileContent);
        }
        fileStorageService.saveFile(gp);

        Group group = new Group(user, name, description, gp, cover);
        groupDao.addGroup(group);
        addMemberToGroup(user, group);
        return group;
    }

    public void updateGroup(Group group){
        groupDao.saveGroup(group);
    }

    public Group getGroupById(UUID id){
        return groupDao.findGroupById(id);
    }

    public void addMemberToGroup(User user, Group group){
        Membership membership = new Membership(user, group);
        groupDao.saveMembership(membership);
    }

    public boolean isUserMember(User user, Group group) {
        return groupDao.isUserMember(user, group);
    }

    public void removeMember(User user, Group group) {
        groupDao.removeMember(user, group);
    }

    public void deleteGroupById(UUID id) {
        groupDao.deleteGroupById(id);
    }

    public void addPost(Post post) {
        groupDao.addPost(post);
    }

    public Post getPostById(UUID id){
        return groupDao.getPostById(id);
    }

    public List<Group> getAllGroups(){
        return groupDao.getAllGroups();
    }

    @Transactional
    public void sharePostWithFile(String content, MultipartFile file, User user, Group group) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File newFile = new File(fileName, file.getContentType(), file.getBytes());
        Post post = new Post(content, user, newFile, group);

        fileStorageService.saveFile(newFile);
        groupDao.addPost(post);
    }
}

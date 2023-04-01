package com.example.linkedhuceng.service;

import com.example.linkedhuceng.entity.File;
import com.example.linkedhuceng.entity.Group;
import com.example.linkedhuceng.entity.GroupPost;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    private final FileRepository fileRepository;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public FileStorageService(FileRepository fileRepository, PostService postService, UserService userService) {
        this.fileRepository = fileRepository;
        this.postService = postService;
        this.userService = userService;
    }

    @Transactional
    public void sharePostWithFile(String content, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File newFile = new File(fileName, file.getContentType(), file.getBytes());
        Post post = new Post(content, userService.getCurrentUser(), newFile);

        fileRepository.save(newFile);
        postService.addPost(post);
    }

    @Transactional
    public void saveFile(File file){
        fileRepository.save((file));
    }

    public File getFile(UUID id) {
        return fileRepository.findById(id).orElseThrow();
    }
}
package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.*;
import com.example.linkedhuceng.service.FileStorageService;
import com.example.linkedhuceng.service.GroupService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GroupController {
    private final UserService userService;
    private final GroupService groupService;
    private final FileStorageService fileStorageService;

    @Autowired
    public GroupController(UserService userService, GroupService groupService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.groupService = groupService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/create-group")
    public String viewCreateGroup(ModelMap modelMap){
        modelMap.addAttribute("user", userService.getCurrentUser());
        modelMap.addAttribute("groups", userService.getCurrentUser().getGroups());
        return "create_group";
    }

    @PostMapping("create-group/create")
    public String createGroup(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("gp") MultipartFile gpMulti,
                              @RequestParam("cover") MultipartFile coverMulti) throws IOException {

        Group group = groupService.createGroup(userService.getCurrentUser(), name, description, gpMulti, coverMulti);
        return "redirect:/group/" + group.getId();
    }

    @GetMapping("/group/{id}")
    public String viewGroup(@PathVariable("id") UUID groupId, ModelMap modelMap){
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        boolean isFounder = (user == group.getFounder());

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("group", group);
        modelMap.addAttribute("posts", group.getGroupFeed());
        modelMap.addAttribute("memberCount", group.getMembers().size());
        modelMap.addAttribute("members", group.getMembers());
        modelMap.addAttribute("groups", user.getGroups());
        modelMap.addAttribute("isFounder", isFounder);
        modelMap.addAttribute("isMember", groupService.isUserMember(user, group));

        return "group";
    }

    @GetMapping("/group/{id}/delete")
    public String deleteGroup(@PathVariable("id") UUID groupId){
        groupService.deleteGroupById(groupId);
        return "redirect:/home";
    }

    @GetMapping("/group/{id}/report")
    public String reportGroup(@PathVariable("id") UUID groupId){
        Group group = groupService.getGroupById(groupId);
        group.report();
        groupService.updateGroup(group);
        return "redirect:/group/" + groupId;
    }

    // POST
    @GetMapping("/group/{id}/add-member")
    public String addMember(@PathVariable("id") UUID id){
        Group group = groupService.getGroupById(id);
        User user = userService.getCurrentUser();
        groupService.addMemberToGroup(user, group);
        return "redirect:/group/" + id;
    }

    @GetMapping("/group/{id}/remove-member")
    public String removeMember(@PathVariable("id") UUID id){
        Group group = groupService.getGroupById(id);
        User user = userService.getCurrentUser();
        groupService.removeMember(user, group);
        return "redirect:/group/" + id;
    }

    @PostMapping("/group/{id}/add-post")
    public String addPostToGroup(@PathVariable("id") UUID id,
                          @RequestParam("content") String content,
                          @RequestParam(value = "file", required = false)MultipartFile file,
                          @RequestParam(value = "image", required = false)MultipartFile image,
                          @RequestParam(value = "video", required = false)MultipartFile video) throws IOException {
        Group group = groupService.getGroupById(id);
        User user = userService.getCurrentUser();

        if(!Objects.equals(file.getOriginalFilename(), "")){
            groupService.sharePostWithFile(content, file, user, group);
        }
        else if(!Objects.equals(image.getOriginalFilename(), "")){
            groupService.sharePostWithFile(content, image, user, group);
        }
        else if(!Objects.equals(video.getOriginalFilename(), "")){
            groupService.sharePostWithFile(content, video, user, group);
        }
        else{
            Post post = new Post(content, user, group);
            groupService.addPost(post);
        }
        return "redirect:/group/" + id;
    }

    @GetMapping("group/display/{id}")
    public void showImageOnPost(@PathVariable("id") UUID id, HttpServletResponse response)
            throws ServletException, IOException {
        Post post = groupService.getPostById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(post.getFile().getData());
        response.getOutputStream().close();
    }
    @GetMapping("group/displayVideo/{id}")
    public void showVideoOnPost(@PathVariable("id") UUID id, HttpServletResponse response)
            throws ServletException, IOException {
        Post post = groupService.getPostById(id);
        response.setContentType("video/*");
        response.getOutputStream().write(post.getFile().getData());
        response.getOutputStream().close();
    }
    @GetMapping("group/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") UUID id) {
        // Load file from database
        File file = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    // display methods
    @GetMapping(value = {"displayGp/{id}", "../../displayGp/{id}"})
    public void showGp(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {;
        Group group = groupService.getGroupById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(group.getGp().getData());
        response.getOutputStream().close();
    }

    @GetMapping(value = {"displayGroupCover/{id}", "../../displayGroupCover/{id}"})
    public void showGroupCover(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException{
        Group group = groupService.getGroupById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(group.getCover().getData());
        response.getOutputStream().close();
    }
}

package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.File;
import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.Notification;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.service.FileStorageService;
import com.example.linkedhuceng.service.PostService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class HomeProfileController {
    private final FileStorageService fileStorageService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public HomeProfileController(UserService userService, PostService postService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.postService = postService;
        this.fileStorageService = fileStorageService;
    }

    // TODO delete prints
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(ModelMap modelMap){
        modelMap.put("user", userService.getCurrentUser());
        List<Post> postList = postService.filterFeed(userService.getCurrentUser());
        Collections.sort(postList, (a, b) -> a.getPublishDate().isBefore(b.getPublishDate()) ? 1 : b.getPublishDate().isBefore(a.getPublishDate()) ? -1 : 0);
        modelMap.put("posts", postList);
        modelMap.put("memberships", userService.getCurrentUser().getGroups());
        return "home";
    }

    @GetMapping("home/display/{id}")
    public void showImageOnPost(@PathVariable("id") UUID id, HttpServletResponse response, Optional<Post> post)
            throws ServletException, IOException {
        post = postService.getPostById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(post.get().getFile().getData());
        response.getOutputStream().close();
    }
    @GetMapping("home/displayVideo/{id}")
    public void showVideoOnPost(@PathVariable("id") UUID id, HttpServletResponse response, Optional<Post> post)
            throws ServletException, IOException {
        post = postService.getPostById(id);
        response.setContentType("video/*");
        response.getOutputStream().write(post.get().getFile().getData());
        response.getOutputStream().close();
    }

    @GetMapping(value = {"displayPp/{id}", "../../displayPp/{id}"})
    public void showPp(@PathVariable("id") UUID id, HttpServletResponse response, Optional<User> user) throws ServletException, IOException {
        user = userService.getUserById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(user.get().getPp().getData());
        response.getOutputStream().close();
    }

    @GetMapping(value = {"displayCover/{id}", "../../displayCover/{id}"})
    public void showCover(@PathVariable("id") UUID id, HttpServletResponse response, Optional<User> user) throws ServletException, IOException{
        user = userService.getUserById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(user.get().getCover().getData());
        response.getOutputStream().close();
    }

    @GetMapping("home/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") UUID id) {
        // Load file from database
        File file = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @GetMapping("/fetchNotifications")
    @ResponseBody
    public List<String[]> fetchOwnedComments(){
        System.out.println("in fetchNotifications"); // delete

        User currentUser = userService.getCurrentUser();

        List<Notification> notifications = new ArrayList<>(postService.notificationDao.getAllNotification());
        Collections.sort(notifications, (a, b) -> a.getTime().isBefore(b.getTime()) ? 1 : b.getTime().isBefore(a.getTime()) ? -1 : 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        List<String[]> response = new ArrayList<>();
        for(Notification n: notifications){
            if(n.getTo().equals(currentUser.getId())){
                String[] field = {n.getContent(), n.getTime().format(formatter)};
                response.add(field);
            }
        }
        System.out.println("notifications in fetchNotifications: " + response);
        return response;
    }

    @GetMapping("/profile/{username}")
    public String viewUserProfile(@PathVariable("username") String username, ModelMap modelMap){
        User profileUser = userService.getUserByUsername(username).orElseThrow();
        User currentUser = userService.getCurrentUser();

        boolean isSameUser = Objects.equals(profileUser.getUsername(), currentUser.getUsername());
        boolean isFollowing = userService.isFollowing(currentUser, profileUser);
        boolean isBlocked = currentUser.getBlockedUsers().contains(profileUser);

        System.out.println(profileUser.getFollowers());
        System.out.println(profileUser.getFollowing());
        modelMap.addAttribute("user", profileUser);
        modelMap.addAttribute("followerCount", profileUser.getFollowers().size());
        modelMap.addAttribute("followedCount", profileUser.getFollowing().size());
        modelMap.addAttribute("isSameUser", isSameUser);
        modelMap.addAttribute("isFollowing", isFollowing);
        modelMap.addAttribute("isBlocked", isBlocked);

        return "profile";
    }

    @GetMapping("/profile/{username}/follow")
    public String followUser(@PathVariable("username") String username, HttpServletRequest request){
        User userToFollow = userService.getUserByUsername(username).orElseThrow();
        User currentUser = userService.getCurrentUser();
        userService.followUser(currentUser, userToFollow);

        if(!currentUser.getId().equals(userToFollow.getId())) {
            postService.addNotification(new Notification(currentUser.getId(), currentUser.getFirstName() + " " + currentUser.getLastName(), userToFollow.getId(), "1"));
        }

        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/profile/{username}/unfollow")
    public String unfollowUser(@PathVariable("username") String username, HttpServletRequest request){
        User userToUnfollow = userService.getUserByUsername(username).orElseThrow();
        User currentUser = userService.getCurrentUser();
        userService.unfollowUser(currentUser, userToUnfollow);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/profile/{username}/block")
    public String blockUser(@PathVariable("username") String username, HttpServletRequest request){
        User userToBlock = userService.getUserByUsername(username).orElseThrow();
        User currentUser = userService.getCurrentUser();
        currentUser.blockUser(userToBlock);
        userService.updateUser(currentUser);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/profile/{username}/unblock")
    public String unblockUser(@PathVariable("username") String username, HttpServletRequest request){
        User userToUnblock = userService.getUserByUsername(username).orElseThrow();
        User currentUser = userService.getCurrentUser();
        currentUser.unblockUser(userToUnblock);
        userService.updateUser(currentUser);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/profile/{username}/report")
    public String reportUser(@PathVariable("username") String username, HttpServletRequest request){
        User user = userService.getUserByUsername(username).orElseThrow();
        user.report();
        userService.updateUser(user);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }
}

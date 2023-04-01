package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.Comment;
import com.example.linkedhuceng.entity.File;
import com.example.linkedhuceng.entity.Notification;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.service.FileStorageService;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.service.PostService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PostController {
    private final FileStorageService fileStorageService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(FileStorageService fileStorageService, PostService postService, UserService userService) {
        this.fileStorageService = fileStorageService;
        this.postService = postService;
        this.userService = userService;
    }

    // Post POST + redirect + GET
    @PostMapping(value = "/home")
    public String addPost(@RequestParam("content") String content,
                          @RequestParam(value = "file", required = false)MultipartFile file,
                          @RequestParam(value = "image", required = false)MultipartFile image,
                          @RequestParam(value = "video", required = false)MultipartFile video) throws IOException {

        if(!Objects.equals(file.getOriginalFilename(), "")){
            fileStorageService.sharePostWithFile(content, file);
        }
        else if(!Objects.equals(image.getOriginalFilename(), "")){
            fileStorageService.sharePostWithFile(content, image);
        }
        else if(!Objects.equals(video.getOriginalFilename(), "")){
            fileStorageService.sharePostWithFile(content, video);
        }
        else{
            Post post = new Post(content, userService.getCurrentUser());
            postService.addPost(post);
        }
        return "redirect:/home";
    }

    @GetMapping(value = "/post/{id}")
    public String viewPost(@PathVariable("id") UUID id, ModelMap modelMap){
        Post post = postService.getPostById(id).orElse(null);
        User currentUser = userService.getCurrentUser();
        // check if post owner is same as logged in user
        boolean isSameUser = Objects.equals(userService.getCurrentUser().getUsername(), post.getOwner().getUsername());
        boolean isFollowing = userService.isFollowing(userService.getCurrentUser(), post.getOwner());
        boolean isBlocked = userService.getCurrentUser().getBlockedUsers().contains(post.getOwner());
        boolean isPostLiked = postService.isPostLikedByUser(userService.getCurrentUser(), post);
        boolean isPostFaved = userService.isPostFaved(userService.getCurrentUser(), post);

        modelMap.addAttribute("post", post);
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("comments", post.getComments());
        System.out.print(post.getLikers());
        System.out.print(post.getLikeCount());
        modelMap.addAttribute("likeCount", post.getLikeCount());
        modelMap.addAttribute("isSameUser", isSameUser);
        modelMap.addAttribute("isFollowing", isFollowing);
        modelMap.addAttribute("isBlocked", isBlocked);
        modelMap.addAttribute("isPostLiked", isPostLiked);
        modelMap.addAttribute("isPostFaved", isPostFaved);

        return "post";
    }

    @GetMapping("/fetchOwnedComments/{postId}")
    @ResponseBody
    public List<UUID> fetchOwnedComments(@PathVariable UUID postId){

        System.out.println("in fetchOwnedComment: " + postId); // delete

        Post post = postService.getPostById(postId).orElse(null);
        List<Comment> comments = new ArrayList<>(post.getComments());
        List<UUID> response = new ArrayList<>();
        for(Comment c: comments){
            if(c.getUser().getId().equals(userService.getCurrentUser().getId())){
                response.add(c.getId());
            }
        }
        System.out.println("comments in fetchOwnedComments: " + response);
        return response;
    }


    // DELETE
    @GetMapping(value = "/post/{id}/delete")
    public String deletePost(@PathVariable("id") UUID id){
        postService.deletePost(id);
        return "redirect:/home";
    }

    // PUT
    @PostMapping(value = "/post/{id}/edit", params = {"updatedContent"})
    public String updatePost(@PathVariable("id") UUID id, @RequestParam("updatedContent") String content,
                             HttpServletRequest request){
        Post post = postService.getPostById(id).orElseThrow();
        post.setContent(content);
        postService.updatePost(post);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    // POST
    @GetMapping(value = "/post/{id}/report")
    public String reportPost(@PathVariable("id") UUID id, HttpServletRequest request){
        Post post = postService.getPostById(id).orElseThrow();
        post.report();
        postService.updatePost(post);

        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @PostMapping(value = "/post/{id}", params = {"comment_content"})
    public String addComment(@PathVariable("id") UUID postId, @RequestParam("comment_content") String commentContent){
        Post post = postService.getPostById(postId).orElseThrow();
        User user = userService.getCurrentUser();
        Comment comment = new Comment(post, user, commentContent);
        postService.addComment(post, comment);

        if(!user.getId().equals(post.getOwner().getId())) {
            postService.addNotification(new Notification(user.getId(), user.getFirstName() + " " + user.getLastName(), post.getOwner().getId(), "2"));
        }

        return "redirect:/post/" + postId;
    }

    // DELETE
    @GetMapping(value = "/post/{postId}/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId){
        postService.deleteComment(commentId);
        return "redirect:/post/" + postId;
    }

    // POST
    @GetMapping(value = "/post/{id}/like")
    public String likePost(@PathVariable("id") UUID postId){
        Post post = postService.getPostById(postId).orElseThrow();
        User user = userService.getCurrentUser();
        postService.likePost(user, post);
        if(!user.getId().equals(post.getOwner().getId())) {
            postService.addNotification(new Notification(user.getId(), user.getFirstName() + " " + user.getLastName(), post.getOwner().getId(), post.getId().toString()));
        }
        return "redirect:/post/" + postId;
    }

    // DELETE / POST
    @GetMapping(value = "/post/{id}/unlike")
    public String unlikePost(@PathVariable("id") UUID postId){
        Post post = postService.getPostById(postId).orElseThrow();
        postService.unlikePost(userService.getCurrentUser(), post);
        return "redirect:/post/" + postId;
    }

    // POST
    @GetMapping(value = "/post/{id}/favorite")
    public String favPost(@PathVariable("id") UUID postId) {
        Post post = postService.getPostById(postId).orElseThrow();
        userService.favPost(userService.getCurrentUser(), post);
        return "redirect:/post/" + postId;
    }

    // DELETE / POST
    @GetMapping(value = "/post/{id}/unfavorite")
    public String unfavPost(@PathVariable("id") UUID postId){
        Post post = postService.getPostById(postId).orElseThrow();
        userService.unfavPost(userService.getCurrentUser(), post);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/favorited-posts")
    public String viewFavoritedPosts(ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getCurrentUser());
        modelMap.addAttribute("posts", userService.getCurrentUser().getFavouritePosts());
        return "favorited_posts";
    }

    @GetMapping("post/{id}/display")
    void showImageOnPost(@PathVariable("id") UUID id, HttpServletResponse response, Optional<Post> post)
            throws ServletException, IOException {
        post = postService.getPostById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(post.get().getFile().getData());
        response.getOutputStream().close();
    }

    @GetMapping("post/{id}/displayVideo")
    public void showVideoOnPost(@PathVariable("id") UUID id, HttpServletResponse response, Optional<Post> post)
            throws ServletException, IOException {
        post = postService.getPostById(id);
        response.setContentType("video/*");
        response.getOutputStream().write(post.get().getFile().getData());
        response.getOutputStream().close();
    }

    @GetMapping("post/{id}/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        // Load file from database
        Optional<Post> post = postService.getPostById(id);
        File file = fileStorageService.getFile(post.get().getFile().getId());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

}

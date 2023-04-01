package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.Group;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.security.UserRole;
import com.example.linkedhuceng.service.GroupService;
import com.example.linkedhuceng.service.PostService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final UserService userService;
    private final PostService postService;
    private final GroupService groupService;

    @Autowired
    public AdminController(UserService userService, PostService postService, GroupService groupService) {
        this.userService = userService;
        this.postService = postService;
        this.groupService = groupService;
    }

    @GetMapping("/admin/enrollments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String enrollments(ModelMap modelMap){
        List<User> userList = userService.getAllUsers();
        for(int i = userList.size() - 1; i >= 0; i--){
            if(!userList.get(i).getRole().name().equals("NOTSPECIFIED")){
                userList.remove(i);
            }
        }
        modelMap.put("users", userList);
        return "enrollments";
    }

    @RequestMapping(value = "admin/enrollments/makeEnrollment/{id}/{role}")
    public String makeEnrollment(@PathVariable(value = "id")UUID id, @PathVariable(value = "role")String role){
        User user = userService.getUserById(id).orElseThrow();

        if(role.equals("STUDENT")){
            user.setRole(UserRole.STUDENT);
        }
        else if(role.equals("ACADEMICIAN")){
            user.setRole(UserRole.ACADEMICIAN);
        }
        else if(role.equals("GRADUATE")){
            user.setRole(UserRole.GRADUATE);
        }
        userService.updateUser(user);
        return "redirect:/admin/enrollments";
    }

    @GetMapping("/admin/manage-accounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String manageAccounts(ModelMap modelMap){
        List<User> userList = userService.getAllUsers()
                .stream()
                .filter(user -> !user.getRole().name().equals("ADMIN"))
                .collect(Collectors.toList());

        modelMap.put("users", userList);
        return "manage-accounts";
    }


    @RequestMapping(value = "admin/manage-accounts/ban/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String banAccount(@PathVariable(value = "id")UUID id){
        User user = userService.getUserById(id).orElseThrow();
        user.ban();
        userService.updateUser(user);
        return "redirect:/admin/manage-accounts";
    }

    @RequestMapping(value = "admin/manage-accounts/unban/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unbanAccount(@PathVariable(value = "id")UUID id){
        User user = userService.getUserById(id).orElseThrow();
        user.unban();
        userService.updateUser(user);
        return "redirect:/admin/manage-accounts";
    }

    @RequestMapping(value = "admin/manage-accounts/makeEnrollmentOnManage/{id}/{role}")
    public String makeEnrollmentOnManage(@PathVariable(value = "id")UUID id, @PathVariable(value = "role")String role){
        User user = userService.getUserById(id).orElseThrow();

        if(role.equals("STUDENT")){
            user.setRole(UserRole.STUDENT);
        }
        else if(role.equals("ACADEMICIAN")){
            user.setRole(UserRole.ACADEMICIAN);
        }
        else if(role.equals("GRADUATE")){
            user.setRole(UserRole.GRADUATE);
        }
        userService.updateUser(user);
        return "redirect:/admin/manage-accounts";
    }

    @RequestMapping(value = "admin/manage-accounts/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id")UUID id){
        userService.deleteUserById(id);
        return "redirect:/admin/manage-accounts";
    }


    @GetMapping("/admin/reported-accounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String reportedAccounts(ModelMap modelMap){
        List<User> userList = userService.getAllUsers();
        for(int i = userList.size() - 1; i >= 0; i--){
            if(userList.get(i).getReportCount() == 0){
                userList.remove(i);
            }
        }
        modelMap.put("users", userList);
        return "reported-accounts";
    }

    @RequestMapping(value = "admin/reported-accounts/ban/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String banAccountOnAccount(@PathVariable(value = "id")UUID id){
        User user = userService.getUserById(id).orElseThrow();
        user.ban();
        userService.updateUser(user);
        return "redirect:/admin/reported-accounts";
    }

    @RequestMapping(value = "admin/reported-accounts/unban/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unbanAccountOnAccount(@PathVariable(value = "id")UUID id){
        User user = userService.getUserById(id).orElseThrow();
        user.unban();
        userService.updateUser(user);
        return "redirect:/admin/reported-accounts";
    }

    @RequestMapping(value = "admin/reported-accounts/deleteUser/{id}")
    public String deleteUserOnAccount(@PathVariable(value = "id")UUID id){
        userService.deleteUserById(id);
        return "redirect:/admin/reported-accounts";
    }

    @GetMapping("/admin/reported-groups")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String reportedGroups(ModelMap modelMap){
        List<Group> groupList = groupService.getAllGroups();
        for(int i = groupList.size() - 1; i >= 0; i--){
            if(groupList.get(i).getReportCount() == 0){
                groupList.remove(i);
            }
        }
        modelMap.put("groups", groupList);
        return "reported-groups";
    }
    @RequestMapping(value = "admin/reported-groups/deleteGroup/{id}")
    public String deleteGroups(@PathVariable(value = "id")UUID id){
        groupService.deleteGroupById(id);
        return "redirect:/admin/reported-groups";
    }

    @GetMapping("/admin/reported-posts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String reportedPosts(ModelMap modelMap){
        List<Post> postList = postService.getAllPosts();
        for(int i = postList.size() - 1; i >= 0; i--){
            if(postList.get(i).getReportCount() == 0){
                postList.remove(i);
            }
        }
        modelMap.put("posts", postList);
        return "reported-posts";
    }

    @RequestMapping(value = "admin/reported-posts/deletePost/{id}")
    public String deletePostOnPosts(@PathVariable(value = "id")UUID id){
        postService.deletePost(id);
        return "redirect:/admin/reported-posts";
    }
}

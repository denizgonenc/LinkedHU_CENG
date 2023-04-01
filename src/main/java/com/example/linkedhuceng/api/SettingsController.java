package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.File;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.service.FileStorageService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class SettingsController {
    public final UserService userService;
    private final FileStorageService fileStorageService;

    @Autowired
    public SettingsController(UserService userService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/settings")
    public String settings(ModelMap modelMap){
        modelMap.addAttribute("user", userService.getCurrentUser());
        return "settings";
    }

    @PostMapping(value = "/settings", params = {"firstName", "lastName", "countryOrRegion", "city"})
    public String updateSettings(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("countryOrRegion") String countryOrRegion,
                                 @RequestParam(value = "city", required = false) String city){
        User user = userService.getCurrentUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCountryOrRegion(countryOrRegion);
        if (city != null){
            user.setCity(city);
        }

        userService.updateUser(user);
        return "redirect:/profile/" + user.getUsername();
    }

    @GetMapping("/edit-profile")
    public String editProfile(ModelMap modelMap){
        User user = userService.getCurrentUser();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("skills", String.join(" ", user.getSkills()));
        return "edit_profile";
    }


    @PostMapping(value = "/edit-profile", params = {"about", "skills"})
    public String updateProfile(@RequestParam("about") String about,
                                @RequestParam("skills") String skills,
                                @RequestParam(value = "cover", required = false)MultipartFile cover,
                                @RequestParam(value = "pp", required = false)MultipartFile pp) throws IOException {
        User user = userService.getCurrentUser();
        user.setAbout(about);
        ArrayList<String> skillList =  new ArrayList<>();
        Collections.addAll(skillList, skills.split(" "));
        user.setSkills(skillList);


        if(!Objects.equals(cover.getOriginalFilename(), "")){
            String fileName = StringUtils.cleanPath(cover.getOriginalFilename());
            File file = new File(fileName, cover.getContentType(), cover.getBytes());
            fileStorageService.saveFile(file);
            user.setCoverImage(file);
            userService.updateUser(user);
        }
        if(!Objects.equals(pp.getOriginalFilename(), "")){
            String fileName = StringUtils.cleanPath(pp.getOriginalFilename());
            File file = new File(fileName, pp.getContentType(), pp.getBytes());
            fileStorageService.saveFile(file);
            user.setPpImage(file);
            userService.updateUser(user);
        }
        userService.updateUser(user);
        return "redirect:/edit-profile";
    }

    @GetMapping("/account-access")
    public String accountAccess(ModelMap modelMap){
        modelMap.addAttribute("user", userService.getCurrentUser());
        return "account_access";
    }

    @PostMapping(value = "/account-access", params = {"phoneNumber", "linkedinUrl",
            "currentPassword", "newPassword"})
    public String updateAccountAccess(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                      @RequestParam(value = "linkedinUrl", required = false) String linkedinUrl,
                                      @RequestParam(value = "currentPassword", required = false) String currentPassword,
                                      @RequestParam(value = "newPassword", required = false) String newPassword){
        User user = userService.getCurrentUser();

        if (!Objects.equals(phoneNumber, "")) user.setPhoneNumber(phoneNumber);
        if (!Objects.equals(linkedinUrl, "")) user.setLinkedinUrl(linkedinUrl);

        userService.updateUser(user);

        return "redirect:/account-access";
    }

    @GetMapping("/blocked-accounts")
    public String blockedAccounts(ModelMap modelMap){
        modelMap.addAttribute("user", userService.getCurrentUser());
        return "blocked_accounts";
    }

    // TODO TEST
    @PostMapping(value = "/blocked-accounts", params = {"userToUnblock"})
    public String unblockUser(@RequestParam("userToUnblock") User user){
        User currentUser = userService.getCurrentUser();
        currentUser.unblockUser(user);
        userService.updateUser(currentUser);
        return "redirect:/blocked-accounts";
    }
}

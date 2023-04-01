package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.event.RegistrationCompleteEvent;
import com.example.linkedhuceng.model.UserModel;
import com.example.linkedhuceng.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    public RegistrationController(UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.publisher = applicationEventPublisher;
    }

    @GetMapping("/signup")
    public String register(Model model){
        model.addAttribute("user", new UserModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("user") UserModel userModel, final HttpServletRequest request) throws IOException {
        User user = userService.registerUser(userModel);
        if (user == null) return "login"; // TODO username exists message
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "redirect:/verificationSent";
    }

    @GetMapping("/verificationSent")
    public String verificationSent(){
        return "verification";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token, ModelMap modelMap){
        User user = userService.verifyUser(token);
        if (user == null){
            System.out.println("User could not be validated");
            return "login";
        }

        modelMap.addAttribute("user", user);
        return "signupsuccess";
    }

    // construct application url
    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}

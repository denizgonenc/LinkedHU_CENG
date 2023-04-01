package com.example.linkedhuceng.api;

import com.example.linkedhuceng.entity.MessageModel;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.service.MessageService;
import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller  // it is RestController in sourceCode
@CrossOrigin        // UserController
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessageController(UserService userService, MessageService messageService, SimpMessagingTemplate simpMessagingTemplate) {
        this.userService = userService;
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    // This shouldn't include ResponseBody that's why I changed RestController to Controller and added ResponseBody annotation to all other mappings.
    @RequestMapping("/chat")
    public String viewChat(ModelMap modelMap) {

        System.out.println("current user: "+ userService.getCurrentUser().getUsername());
        modelMap.put("user", userService.getCurrentUser());

        return "chat";
    }

    @RequestMapping("/messages")
    public String messages(ModelMap modelMap) {

        modelMap.put("user", userService.getCurrentUser());

        return "messages";
    }

    @MessageMapping("/chat/{to}")
    @ResponseBody
    public void sendMessage(@DestinationVariable String to, MessageModel message){
        message.setReceiver(to);

        System.out.println("handling send message:  " + message);

        //add the message to db.
        messageService.addMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/messages/"+ to, message);
    }


    // UserController
    // no need this anymore but let it stay
    @GetMapping("/registration/{userName}")
    @ResponseBody
    public ResponseEntity<Void> register(@PathVariable String userName){
        System.out.println("handling register user request: "+userName);

//        try{
//            UserStorage.getInstance().addUser(userName);
//        } catch (Exception e){
//            return  ResponseEntity.badRequest().build();
//        }

        System.out.println("handling register user: " + userName + " is over");
        return ResponseEntity.ok().build();
    }


    // TODO return a HashMap that has set of users who has a chat history with userName as keys and List of MessageModels(chat history) as values
    @GetMapping("/fetchAllUsers/{userName}")
    @ResponseBody
    public List<String[]> fetchAllUsers(@PathVariable String userName){

        System.out.println("fetchalla girdi: " + userName); // delete

        List<User> users = new ArrayList<>(userService.getAllUsers());
        List<String[]> response = new ArrayList<>();
        for(User u: users){
            if(!u.getUsername().equals(userName)){
                String[] userInfo = {u.getUsername(), u.getFirstName() + " " + u.getLastName(), lastMessage(u.getUsername())};
                response.add(userInfo);
            }
        }
        System.out.println("users in fetchAllUsers: " + response);
        return response;
    }

    public String lastMessage(String userName){
        List<MessageModel> messages = new ArrayList<>(messageService.getAllMessages());
        Collections.sort(messages, (a, b) -> a.getSentTime().isBefore(b.getSentTime()) ? 1 : b.getSentTime().isBefore(a.getSentTime()) ? -1 : 0);

        for(MessageModel message: messages){
            if(message.getSender().equals(userName) && message.getReceiver().equals(userService.getCurrentUser().getUsername())){
                return message.getMessage();
            }

            else if(message.getSender().equals(userService.getCurrentUser().getUsername()) && message.getReceiver().equals(userName)){
                return message.getMessage();
            }
        }
        return "";
    }

    @GetMapping("/fetchAllMessages/{userName}")
    @ResponseBody
    public List<List<String>> fetchAllMessages(@PathVariable String userName){

        System.out.println("fetchAllMessagesa girdi: " + userName); // delete

        List<MessageModel> messages = new ArrayList<>(messageService.getAllMessages());
        Collections.sort(messages, (a, b) -> a.getSentTime().isBefore(b.getSentTime()) ? -1 : b.getSentTime().isBefore(a.getSentTime()) ? 1 : 0);
        List<List<String>> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("all messages: " + messages);
        // [("0": current user is sender, "1": current user is receiver), content, time)
        for(MessageModel message: messages){
            List<String> fields = new ArrayList<>();
            if(message.getSender().equals(userName) && message.getReceiver().equals(userService.getCurrentUser().getUsername())){
                fields.add("1");
                fields.add(message.getMessage());
                fields.add(message.getSentTime().format(formatter));
                response.add(fields);
            }

            else if(message.getSender().equals(userService.getCurrentUser().getUsername()) && message.getReceiver().equals(userName)){
                fields.add("0");
                fields.add(message.getMessage());
                fields.add(message.getSentTime().format(formatter));
                response.add(fields);
            }
        }

        System.out.println("messages in fetchAllMessages: " + response);
        return response;
    }
}

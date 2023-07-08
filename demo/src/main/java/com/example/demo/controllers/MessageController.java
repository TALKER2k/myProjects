package com.example.demo.controllers;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;
import com.example.demo.services.MessagesService;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@Controller
public class MessageController {

    @Autowired
    private UsersService userService;

    @Autowired
    private MessagesService messagesService;

    @GetMapping()
    public String index(){
        return "index";
    }

    @GetMapping("/sendmessage")
    public String sendMessage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userid", user.getId());
        return "sendmessage";
    }

    @GetMapping("/sendmessage/{userid}")
    public String sendMessageForUser(@PathVariable(value = "userid") Long userid, @AuthenticationPrincipal User user, Model model){
        model.addAttribute("userid", userid);
        model.addAttribute("userMain", user.getId().toString());
        model.addAttribute("messageList", messagesService.getMessageById(user.getId(), userid));
        return "messager";
    }

    @PostMapping("/sendmessage/{userid}")
    public String sendMessageFor(@PathVariable(value = "userid") Long userid, @RequestParam(value = "message") String message, @AuthenticationPrincipal User user){
        messagesService.addNewMessages(userid, user.getId(),message);
        return "redirect:/sendmessage/" + userid;
    }
}

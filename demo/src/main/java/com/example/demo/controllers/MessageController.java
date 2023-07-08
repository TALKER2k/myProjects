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

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

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
    public String sendMessageForUser(@PathVariable(value = "userid") Long userid,
                                     @AuthenticationPrincipal User user, Model model){
        model.addAttribute("userid", userid);
        model.addAttribute("userMain", user.getId().toString());
        model.addAttribute("messageList", messagesService.getMessageById(user.getId(), userid));
        return "messager";
    }

    @PostMapping("/sendmessage/{userid}")
    public String sendMessageFor(@PathVariable(value = "userid") Long userid,
                                 @RequestParam(value = "message") String message,
                                 @AuthenticationPrincipal User user){
        messagesService.addNewMessages(userid, user.getId(),message);
        return "redirect:/sendmessage/" + userid;
    }

    @GetMapping("/download")
    public void downloadMessage(@RequestParam("message") String message,
                                HttpServletResponse response) throws IOException {
        String csvData = generateCsvData(message);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=message.csv");
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(csvData.getBytes());
            outputStream.flush();
        }
    }

    @GetMapping("/downloadAll")
    public void downloadAllMessages(HttpServletResponse response) throws IOException {
        StringBuilder csvData = new StringBuilder();
        List<HashMap<String, List<Message>>> messages = messagesService.getAllList();
        for (HashMap<String, List<Message>> map : messages) {
            for (List<Message> messageList : map.values()) {
                for (Message message : messageList) {
                    csvData.append("id-").append(message.getName()).append(" : ").append(message.getText()).append("\n");
                }
            }
        }
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=all_messages.csv");

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(csvData.toString().getBytes());
            outputStream.flush();
        }
    }

    @GetMapping("/delete/{userid}/{message}")
    public String deleteMessage(@PathVariable(value = "message") String message,
                                @PathVariable(value = "userid") Long userid) {
        messagesService.deleteMessage(message);
        return "redirect:/sendmessage/" + userid;
    }

    @GetMapping("/editMessage/{userid}/{messageText}")
    public String editMessagePage(@PathVariable(value = "messageText") String messageText,
                                  @PathVariable(value = "userid") Long userid, Model model) {
        model.addAttribute("oldMessage", messageText);
        return "editMessage";
    }

    @PostMapping("/edit/{userid}")
    public String edit(@RequestParam("newMessage") String newMessage,
                       @RequestParam("oldMessage") String oldMessage,
                       @PathVariable(value = "userid") Long userid) {
        messagesService.editMessage(oldMessage, newMessage);
        return "redirect:/sendmessage/" + userid;
    }

    @PostMapping("/editMessage/{userid}/{messageText}")
    public String editMessage(@PathVariable(value = "messageText") String messageText,
                              @PathVariable(value = "userid") Long userid) {
        return "/editMessage/" + userid + "/" + messageText;
    }



    private String generateCsvData(String message) {
        return "message\r\n" + message.substring(message.indexOf(":") + 1).trim();
    }

}

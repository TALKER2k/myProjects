package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.User;
import com.geekbrains.demoboot.services.MoneysService;
import com.geekbrains.demoboot.services.UsersService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UsersService userService;
    @Autowired
    private MoneysService moneysService;

    @GetMapping("/admin")
    public String userList(Model model) {
//        moneysService.transactMoney(11L, "RUB", "USD", 500);
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

//    @PostMapping("/admin")
//    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userService.deleteUser(userId);
//        }
//        return "redirect:/admin";
//    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteUsers(@PathVariable(value = "id") Long id)
    {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/transaction/{id}")
    public String transactionMoney(@PathVariable(value = "id") Long id)
    {
        return "redirect:/admin";
    }
    @GetMapping("/user")
    @ResponseBody
    public User userData(@AuthenticationPrincipal User user) {
        return user;
    }
    @GetMapping("/current")
    public String current(Model model){
        model.addAttribute("allMoney", moneysService.findAllMoney());
        return "current";
    }
}

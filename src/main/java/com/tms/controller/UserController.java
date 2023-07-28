package com.tms.controller;

import com.tms.domain.UserInfo;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        List<UserInfo> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("userInfo", new UserInfo());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable Integer id) {
        UserInfo userInfo = userService.getUser(id);
        model.addAttribute("userInfo", userInfo);
        return "edit";
    }

    @PostMapping("/")
    public RedirectView createUser(RedirectAttributes redirectAttributes, @ModelAttribute UserInfo userInfo) {
        userService.createUser(userInfo);
        String message = "Create user " + userInfo.getFirstName() + " " + userInfo.getLastName();
        redirectAttributes.addFlashAttribute("userMessage", message);
        return new RedirectView("/");
    }

    @PostMapping("/{id}")
    public RedirectView updateUser(RedirectAttributes redirectAttributes, @PathVariable Integer id, @ModelAttribute UserInfo userInfo) {
        if (userInfo.getWillDelete()) {
            userService.deleteUserById(id);
        } else {
            userService.updateUser(id, userInfo);
        }
        String message = (userInfo.getWillDelete() ? "Delete" : "Update") + " user" + userInfo.getFirstName();
        redirectAttributes.addFlashAttribute("userMessage", message);
        return new RedirectView("/");
    }
}

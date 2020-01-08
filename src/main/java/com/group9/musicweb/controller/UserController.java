package com.group9.musicweb.controller;

import com.group9.musicweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import com.group9.musicweb.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @GetMapping
    public String user(ModelMap map) {
        String sql = "select * from user";
        List<User> users = jdbcTemplate.query("select * from User", new BeanPropertyRowMapper<User>(User.class));
        map.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPwd(null);
            session.setAttribute("user", user);
            return "user/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误,请重新尝试！");
            System.out.println("***************************************************************");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/user/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String gender,
                           @RequestParam String password,
                           @RequestParam String phone,
                           @RequestParam String email,
                           RedirectAttributes attributes) {
        boolean flag = true;
        System.out.println("**********************");
        if (!userService.isFindUserByUsername(username)) {
            attributes.addFlashAttribute("message", "该用户名已被注册");
            System.out.println("------------------------- ------------------");
            flag = false;
        }
        if (!userService.isFindUserByPhone(phone)) {
            attributes.addFlashAttribute("message", "该手机号被已注册");
            flag = false;
        }
        if (!userService.isFindUserByEmail(email)) {
            attributes.addFlashAttribute("message", "该邮箱已被注册");
            flag = false;
        }
        if(flag){
            User user = new User();
            user.setName(username);
            user.setXb(gender);
            user.setPwd(password);
            user.setPhone(phone);
            user.setEmail(email);
            userService.addUser(user);
            return "user/index";
        }
        else return "redirect:/user/register";
    }

}

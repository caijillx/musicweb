package com.group9.musicweb.controller;

import com.group9.musicweb.entity.Comment;
import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;
import com.group9.musicweb.service.UserlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Date;
import java.util.List;

import com.group9.musicweb.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

class RandomName {

    public static String getRandomName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String houzhui = fileName.substring(index);//获取后缀名
        return UUID.randomUUID().toString().replace("-", "") + houzhui;
    }

}


class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UserlogService userlogService;

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
                        RedirectAttributes attributes,
                        HttpServletRequest request
    ) {
        String ipAddress = IpUtil.getIpAddr(request);
        User user = userService.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            Userlog userlog = new Userlog();
            userlog.setUser(user);
            userlog.setIp(ipAddress);
            userlog.setAdd_time(new Date());
            userlogService.saveUserlog(userlog);
            return "user/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误,请重新尝试！");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/user/login";
    }

    @GetMapping("/user")
    public String userPage(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String username = "None";
        map.addAttribute("user", user);
        return "user/user";
    }

    @PostMapping("/user")
    public String user(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String phone,
                       @RequestParam MultipartFile face,
                       @RequestParam String info,
                       HttpServletRequest request,
                       HttpSession session,
                       RedirectAttributes attributes) throws IOException {
        boolean flag = true;
        User user = (User) session.getAttribute("user");
        if (!userService.isFindUserByUsername(name) && !name.equals(user.getName())) {
            attributes.addFlashAttribute("message", "该用户名被已注册");
            flag = false;
        }
        if (!userService.isFindUserByPhone(phone) && !phone.equals(user.getPhone())) {
            attributes.addFlashAttribute("message", "该手机号被已注册");
            flag = false;
        }
        if (!userService.isFindUserByEmail(email) && !email.equals((user.getEmail()))) {
            attributes.addFlashAttribute("message", "该邮箱已被注册");
            flag = false;
        }
        String filename = RandomName.getRandomName(face.getOriginalFilename());
        if (!face.isEmpty()) {
            // 构建上传文件的存放路径
            String path = ResourceUtils.getURL("classpath:static").getPath();
//            String path = request.getServletContext().getRealPath("/static/users/face/");
            System.out.println("path = " + path);

            // 获取上传的文件名称，并结合存放路径，构建新的文件名称
            String suffixName = filename.substring(filename.lastIndexOf("."));
            System.out.println(suffixName);
            if (!suffixName.equals(".jpg") && !suffixName.equals(".png")) {
                attributes.addFlashAttribute("message", "图片格式只能是jpg或png！");
                flag = false;
            } else {
                File filepath = new File(path + "/users/face/", filename);
                System.out.println(filepath);
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                // 将上传文件保存到目标文件目录
                face.transferTo(filepath);
            }
        }
        if (flag) {
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setInfo(info);
            user.setHeadresaddr(filename);
            userService.saveUser(user);
        }
        return "redirect:/user/user";
    }


    @GetMapping("/pwd")
    public String pwdPage() {
        return "user/pwd";
    }

    @PostMapping("/pwd")
    public String pwd(@RequestParam String oldpwd, @RequestParam String newpwd, HttpSession session, RedirectAttributes attributes) {
        System.out.println("********************************************");
        User user = (User) session.getAttribute("user");
        if (userService.isRightPwd(user, oldpwd)) {
            userService.updatePwd(user, newpwd);
            attributes.addFlashAttribute("p_message", "修改成功！");
        } else {
            attributes.addFlashAttribute("n_message", "密码错误,请重新尝试！");
        }
        return "redirect:/user/pwd";
    }

    @GetMapping("/comment")
    public String commentPage(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Comment> comments = userService.queryComment(user.getId());
        map.addAttribute("comments", comments);
        map.addAttribute("user",user);
        return "user/comments";
    }

    @GetMapping("loginlog")
    public String loginlogPage(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Userlog> userlogs = userlogService.queryUserlog(user.getId());
        map.addAttribute("userlogs", userlogs);
        return "user/loginlog";
    }

    @GetMapping("musiccol")
    public String musiccolPage() {
        return "user/musiccol";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }

    @GetMapping("/play")
    public String playPage() {
        return "user/play";
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
        if (flag) {
            User user = new User();
            user.setName(username);
            user.setXb(gender);
            user.setPwd(password);
            user.setPhone(phone);
            user.setEmail(email);
            userService.addUser(user);
            return "user/index";
        } else return "redirect:/user/register";
    }

}

package top.jisy.docs.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.jisy.docs.config.Hashing;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Hashing hashing;

    @Autowired
    UserService userService;

    @PostMapping("/api/authentication/login")
    public String authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        //1.登录校验
        //2.将token写入cookie，并指定httpOnly为true，防止通过js获取和修改
        if ("admin".equals(username) && "admin".equals(password)) {
            log.info("username: {} password: {} login success", username, password);
            return "true";
        } else {
            log.info("username: {} password: {} login failed", username, password);
            return "false";
        }
    }

    // @GetMapping("/api/userList")
    // public HashMap getUserList(){
    //     return userService.listUsers();
    // }
    @PostMapping("/api/authentication/register")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password) {
        User newUser = new User();
        newUser.setName(username);
        newUser.setMail(email);
        log.info("{}", newUser.toString());
        String hashedPassword = hashing.hashPassword(password);
        newUser.setPassword(hashedPassword);
        userService.registration(newUser);
        return "success";
    }
}
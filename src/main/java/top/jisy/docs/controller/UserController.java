package top.jisy.docs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    // @Autowired
    // UserService userService;

    @PostMapping("/api/authentication/login")
    public String authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        //1.登录校验
        //2.将token写入cookie，并指定httpOnly为true，防止通过js获取和修改
        return "test";
    }
    // @GetMapping("/api/userList")
    // public HashMap getUserList(){
    //     return userService.listUsers();
    // }
}

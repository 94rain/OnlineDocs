package top.jisy.docs.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jisy.docs.config.Hashing;
import top.jisy.docs.config.JwtConfig;
import top.jisy.docs.constant.ResponseParameters;
import top.jisy.docs.entity.ResponseObject;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.UserService;
import top.jisy.docs.util.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api/authentication")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Hashing hashing;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserService userService;

    @Autowired
    SessionUtils sessionUtils;

    @PostMapping("/login")
    public ResponseObject authentication(@RequestBody JSONObject json) {
        String username = json.get("username").toString();
        // String password = json.get("password").toString();
        JSONObject responseJson = new JSONObject();

        String userId = 5 + "";
        String token = jwtConfig.createToken(userId) ;
        if (!StringUtils.isEmpty(token)) {
            responseJson.put("token", token);
            log.debug("{}: Created new session for user '{}'", username);
            return ResponseObject.success(responseJson);
        } else {
            log.info("username: {} login failed", username);
            return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.INVALID_PASSWORD);
        }


        // if (username.equals("hzjsya") || username.equals("admin")) {
        //     log.info("username: {} login success", username);
        //     if (sessionUtils.createSession(JSONObject.toJavaObject(json, User.class))) {
        //         log.debug("{}: Created new session for user '{}'", username);
        //     }
        //     return ResponseObject.success(username);
        // } else {
        //     log.info("username: {} password: {} login failed", username, password);
        //     return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.INVALID_PASSWORD);
        // }
    }

    // @GetMapping("/api/userList")
    // public HashMap getUserList(){
    //     return userService.listUsers();
    // }
    @PostMapping("/register")
    public String doRegister(@RequestBody JSONObject json) {
        User newUser = new User();
        newUser.setName(json.get("username").toString());
        newUser.setMail(json.get("email").toString());
        log.info("{}", newUser.toString());
        String hashedPassword = hashing.hashPassword(json.get("password").toString());
        newUser.setPassword(hashedPassword);
        userService.registration(newUser);
        return "success";
    }

    @PostMapping("/logout")
    public ResponseObject doLogout(@Context HttpServletRequest request) {
        if (!sessionUtils.isLoggedIn()) {
            return ResponseObject.success(ResponseParameters.ALREADY_LOGGED_OUT);
        }

        User user = sessionUtils.getUser();

        log.info("{}: Logged user '{}' out", request.getRequestURI(), user.getName());
        if (sessionUtils.invalidateSession()) {
            log.info("{}: Session of user '{}' was invalidated", request.getRequestURI(), user.getName());
        }

        return ResponseObject.success(ResponseParameters.LOGGED_OUT);
    }
}
package top.jisy.docs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.jisy.docs.config.JwtProperties;
import top.jisy.docs.dao.UserMapper;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.LoginService;
import top.jisy.docs.utils.JwtUtils;

public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private JwtProperties properties;

    @Override
    public String authentication(String username, String password) {

        try{
            //1.调用微服务查询用户信息
            User user = this.userMapper.queryUser(username,password);
            //2.查询结果为空，则直接返回null
            if (user == null){
                return null;
            }
            //3.查询结果不为空，则生成token
            String token = JwtUtils.generateToken(new User(user.getId(), user.getUsername()),
                    properties.getPrivateKey(), properties.getExpire());
            return token;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

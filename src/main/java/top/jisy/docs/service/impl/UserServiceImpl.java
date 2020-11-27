package top.jisy.docs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jisy.docs.dao.UserMapper;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String authentication(String username, String password) {
        return "2333";
        // try {
        //     //1.调用微服务查询用户信息
        //     User user = this.userMapper.queryUser(username, password);
        //     //2.查询结果为空，则直接返回null
        //     if (user == null) {
        //         return null;
        //     }
        //     return user.toString();
        //
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return null;
        // }
    }

    @Override
    public int registration(User user) {
        return userMapper.insertSelective(user);
    }


}

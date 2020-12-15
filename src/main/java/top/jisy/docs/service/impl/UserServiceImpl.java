package top.jisy.docs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jisy.docs.dao.mapper.RepoMapper;
import top.jisy.docs.dao.mapper.UserMapper;
import top.jisy.docs.pojo.Repo;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RepoMapper repoMapper;

    @Override
    public int registration(User user) {
        userMapper.insertSelective(user);

        Repo repo = new Repo();
        repo.setFkUser(user.getId());
        int res = repoMapper.insertSelective(repo);
        return res;
    }

    @Override
    public String authentication(String username, String password) {
        return "null";
        // try {
        //     User user = this.userMapper.queryUser(username, password);
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

    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }




}

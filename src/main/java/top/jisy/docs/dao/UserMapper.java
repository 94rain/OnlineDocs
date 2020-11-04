package top.jisy.docs.dao;

import org.springframework.web.bind.annotation.RequestParam;
import top.jisy.docs.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
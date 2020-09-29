package top.jisy.docs.parent.docs.dao;

import top.jisy.docs.parent.docs.entity.User;
import top.jisy.docs.parent.docs.entity.UserExample;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}

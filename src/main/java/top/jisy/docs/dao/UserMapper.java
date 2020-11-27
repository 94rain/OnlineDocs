package top.jisy.docs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.jisy.docs.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from user where username = #{username} and password = #{password}")
    User queryUser(@Param("username") String username, @Param("password") String password);

    @Select("SELECT u FROM User u WHERE LOWER(u.name)= #{username}")
    User getUserByName(@Param("username") String username);
}
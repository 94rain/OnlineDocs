package top.jisy.docs.service;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.User;

@Component
public interface UserService {
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    String authentication(String username, String password);

    int registration(User user);
}

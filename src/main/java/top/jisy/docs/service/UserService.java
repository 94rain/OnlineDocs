package top.jisy.docs.service;

import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.User;

@Component
public interface UserService {
    /**
     * Login
     *
     * @param username
     * @param password
     * @return
     */
    String authentication(String username, String password);

    /**
     * @param user
     * @return
     */
    int registration(User user);

    User getUserByName(String username);
}

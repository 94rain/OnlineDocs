package top.jisy.docs.service;

public interface UserService {
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    String authentication(String username, String password);
}

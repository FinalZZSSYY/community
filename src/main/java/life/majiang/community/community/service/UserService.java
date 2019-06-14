package life.majiang.community.community.service;

import life.majiang.community.community.pojo.User;

public interface UserService {
    void update(User user);

    void insert(User user);

    int findByAccountId(String accountId);

    User findByToken(String token);
}

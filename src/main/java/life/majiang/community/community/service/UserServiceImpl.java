package life.majiang.community.community.service;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public void update(User user) {
        userMapper.update(user);
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    public int findByAccountId(String accountId) {
       return userMapper.findByAccountId(accountId);
    }

    public User findByToken(String token) {

        return userMapper.findByToken(token);
    }
}

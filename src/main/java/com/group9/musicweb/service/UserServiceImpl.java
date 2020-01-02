package com.group9.musicweb.service;
import com.group9.musicweb.Dao.UserRepository;
import com.group9.musicweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByNameAndPwd(username,password);
        return user;
    }

    @Override
    public boolean isFindUserByUsername(String username) {
        User user = userRepository.findByName(username);
        return user == null;
    }

    @Override
    public boolean isFindUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }

    @Override
    public boolean isFindUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        return user == null;
    }

    @Override
    public void addUser(User user){
        userRepository.save(user);
    }
}

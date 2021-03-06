package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.DebtorDetails;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public User getUserByEmail(String name){
        return userMapper.getUserByEmail(name);
    }
    public void insertUser(User user){
        userMapper.insertUser(user);
    }
    public List<DebtorDetails> getPeopleWhoOweMe(String userEmail) {
        return  userMapper.getPeopleWhoOweMe(userEmail);
    }

}

package com.tms.service;

import com.tms.domain.UserInfo;
import com.tms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    public UserInfo getUser(Integer id) {
        return userRepository.findById(id);
    }

    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setCreatedAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userInfo);
        return userInfo;
    }

    public void updateUser(UserInfo userInfo) {
        userInfo.setUpdatedAt(LocalDateTime.now());
        userRepository.updateUser(userInfo);
    }

    public void deleteUserById(Integer id) {
        userRepository.delete(id);
    }
}

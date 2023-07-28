package com.tms.service;

import com.tms.domain.UserInfo;
import com.tms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private Integer counter = 0;
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

    public void createUser(UserInfo userInfo) {
        userInfo.setId(++counter);
        userRepository.save(userInfo);
    }

    public void updateUser(Integer id, UserInfo userInfo) {
        UserInfo fromDb = getUser(id);
        fromDb.setFirstName(userInfo.getFirstName());
        fromDb.setLastName(userInfo.getLastName());
        fromDb.setRole(userInfo.getRole());
        fromDb.setUpdatedAt(LocalDateTime.now());
        fromDb.setCreatedAt(userInfo.getCreatedAt());
        fromDb.setWillDelete(false);
        userRepository.save(fromDb);
    }

    public void deleteUserById(Integer id) {
        userRepository.delete(id);
    }
}

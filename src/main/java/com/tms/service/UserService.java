package com.tms.service;

import com.tms.domain.UserInfo;
import com.tms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    public Optional<UserInfo> findUserByLastName(String fn) {
        return userRepository.findUserByLastName(fn);
    }

    public Optional<UserInfo> getUser(Integer id) {
        //TODO: Смотрит ли пользователь самого себя (добавить роли), иначе Exception
        return userRepository.findById(id);
    }

    public void createUser(UserInfo userInfo) {
        userInfo.setCreatedAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userInfo); // TODO: write exception
    }

    public void updateUser(UserInfo userInfo) {
        //TODO: Изменяет ли пользователь самого себя (добавить роли), иначе Exception
        userInfo.setUpdatedAt(LocalDateTime.now());
        userRepository.saveAndFlush(userInfo);
    }

    public void deleteUserById(Integer id) {
        //TODO: Удаляет ли пользователь самого себя (добавить роли), иначе Exception
        userRepository.deleteById(id);
    }
}

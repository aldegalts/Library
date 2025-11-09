package com.degaltseva.library.service;

import com.degaltseva.library.entity.UserEntity;
import com.degaltseva.library.entity.enums.Role;

import java.util.List;

/**
 * Сервис для работы с пользователями библиотеки.
 * <p>
 */
public interface UserService {
    UserEntity registerUser(String firstName, String lastName, String email, String phone, String rawPassword, Role role);

    UserEntity findById(Long id);

    UserEntity findByEmail(String email);

    void deleteById(Long id);

    List<UserEntity> getAllUsers();

    UserEntity updateUser(Long id, UserEntity updatedUser);
}

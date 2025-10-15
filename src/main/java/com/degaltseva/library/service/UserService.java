package com.degaltseva.library.service;

import com.degaltseva.library.entity.User;

/**
 * Сервис для работы с пользователями библиотеки.
 * <p>
 */
public interface UserService {
    void createUser(Long id, String login);
    User findById(Long id);
    void deleteById(Long id);
    void updateLogin(Long id, String newLogin);
}

package com.degaltseva.library.service.impl;


import com.degaltseva.library.entity.User;
import com.degaltseva.library.repository.UserCrudRepository;
import com.degaltseva.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с пользователями.
 * <p>
 * Возможности: создание, поиск, удаление и обновление пользователей.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserServiceImpl(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(Long id, String login) {
        User newUser = new User();
        newUser.setId(id);
        newUser.setLogin(login);
        userRepository.create(newUser);
    }

    @Override
    public User findById(Long id) {
        return userRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void updateLogin(Long id, String newLogin) {
        User user = new User();
        user.setId(id);
        user.setLogin(newLogin);
        userRepository.update(user);
    }
}


package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.UserEntity;
import com.degaltseva.library.entity.enums.Role;
import com.degaltseva.library.repository.UserRepository;
import com.degaltseva.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для работы с пользователями.
 * <p>
 * Возможности: создание, поиск, удаление и обновление пользователей.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity registerUser(String firstName, String lastName, String email,
                                   String phone, String rawPassword, Role role) {

        String encodedPassword = passwordEncoder.encode(rawPassword);

        UserEntity user = new UserEntity(firstName, lastName, email, phone, encodedPassword, role);
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByUserEmail(email).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserFirstName(updatedUser.getUserFirstName());
                    user.setUserLastName(updatedUser.getUserLastName());
                    user.setUserEmail(updatedUser.getUserEmail());
                    user.setPhone(updatedUser.getPhone());
                    if (updatedUser.getPassword_hash() != null && !updatedUser.getPassword_hash().isEmpty()) {
                        user.setPassword_hash(passwordEncoder.encode(updatedUser.getPassword_hash()));
                    }
                    user.setRole(updatedUser.getRole());
                    return userRepository.save(user);
                })
                .orElse(null);
    }
}

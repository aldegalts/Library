package com.degaltseva.library.repository.impl;
import java.util.List;
import java.util.Optional;

import com.degaltseva.library.entity.User;
import com.degaltseva.library.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements UserCrudRepository {

    private final List<User> userContainer;

    @Autowired
    public UserRepository(List<User> userContainer) {
        this.userContainer = userContainer;
    }

    @Override
    public void create(User user) {
        userContainer.add(user);
    }

    @Override
    public User read(Long id) {
        Optional<User> user = userContainer.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < userContainer.size(); i++) {
            if (userContainer.get(i).getId().equals(user.getId())) {
                userContainer.set(i, user);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        userContainer.removeIf(u -> u.getId().equals(id));
    }
}

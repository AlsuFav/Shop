package ru.itis.shop.users.repositories;

import ru.itis.shop.users.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersRepository {
    void save(User user);

    List<User> findAll();

    void update(User user);

    void delete(int id);

    Optional<User> findById(int id);
}

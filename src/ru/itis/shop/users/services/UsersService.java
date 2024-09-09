package ru.itis.shop.users.services;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.validators.EmailValidator;
import ru.itis.shop.users.validators.SimpleEmailValidator;

import java.util.UUID;


public class UsersService {

    private final EmailValidator emailValidator;

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository, EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
        this.usersRepository = usersRepository;


    }

    public void register(String name, String email, String password) {
        emailValidator.validate(email);

        User user = new User(name, email, password);

        usersRepository.save(user);
    }
}

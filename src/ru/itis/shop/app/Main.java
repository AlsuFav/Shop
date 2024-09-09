package ru.itis.shop.app;

import ru.itis.shop.users.controllers.UsersUIConsole;
import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.repositories.impl.UsersRepositoryJdbcImpl;
import ru.itis.shop.users.services.UsersService;
import ru.itis.shop.users.validators.EmailValidator;
import ru.itis.shop.users.validators.SimpleEmailValidator;
import ru.itis.shop.util.DriverManagerDataSource;

import javax.sql.DataSource;


public class Main {
    // сборка компонентов системы и клиентский код
    public static void main(String[] args) {
        DataSource dataSource =
                new DriverManagerDataSource("org.postgresql.Driver",
                        "jdbc:postgresql://localhost:5432/javalab_2024_db",
                        "postgres", "qwerty123");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        EmailValidator emailValidator = new SimpleEmailValidator();
        UsersService usersService = new UsersService(usersRepository, emailValidator);
        UsersUIConsole ui = new UsersUIConsole(usersService);

        for(int i = 0; i < 3; i++) {
            ui.printRegistrationMenu();
        }


        System.out.println("\nfindAll() method: \n");
        System.out.println(usersRepository.findAll());


        System.out.println("\nupdate() method: \n");
        usersRepository.update(new User(1, "d", "d@", "qwerty111"));
        System.out.println(usersRepository.findAll());


        System.out.println("\ndelete() method: \n");
        usersRepository.delete(1);
        System.out.println(usersRepository.findAll());


        User nonexistentUser = new User(0, "notExists",
                "notExists", "notExists");

        System.out.println("\nfindById() method with nonexistent id: \n");
        System.out.println(usersRepository.findById(1).orElse(nonexistentUser));

        System.out.println("\nfindById() method: \n");
        System.out.println(usersRepository.findById(2).orElse(nonexistentUser));
    }
}

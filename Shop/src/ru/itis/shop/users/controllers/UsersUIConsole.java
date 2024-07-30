package ru.itis.shop.users.controllers;

import ru.itis.shop.users.services.UsersService;

import java.util.Scanner;


public class UsersUIConsole {

    private final Scanner scanner;

    private final UsersService usersService;

    public UsersUIConsole(UsersService usersService) {
        this.scanner = new Scanner(System.in);
        this.usersService = usersService;
    }

    public void printRegistrationMenu() {
        System.out.println("Введите имя пользователя:");
        String name = scanner.nextLine();
        System.out.println("Введите Email пользователя:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль пользователя:");
        String password = scanner.nextLine();

        usersService.register(name, email, password);
    }
}

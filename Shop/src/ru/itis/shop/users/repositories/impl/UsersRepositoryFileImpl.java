package ru.itis.shop.users.repositories.impl;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryFileImpl implements UsersRepository {
    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        // try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(user.getId() + "|" + user.getName() + "|" + user.getEmail() + "|" + user.getPassword());
            writer.newLine();
        } catch (IOException e) { // перехватываю проверяемое исключение
            throw new IllegalStateException(e); // пробрасываем непроверяемое поверх, чтобы остановить цикл работы программы
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] arr = line.split("\\|");

                int id = Integer.parseInt(arr[0]);
                String name = arr[1];
                String email = arr[2];
                String password = arr[3];

                users.add(new User(id, name, email, password));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

    @Override
    public void update(User user) {
        List<User> users = findAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            boolean found = false;

            for(User currentUser: users) {
                if(!found && currentUser.getId().equals(user.getId())) {
                    writer.write(user.getId() + "|" + user.getName() + "|"
                            + user.getEmail() + "|" + user.getPassword());
                    writer.newLine();

                    found = true;
                }
                else {
                    writer.write(currentUser.getId() + "|" + currentUser.getName() + "|"
                            + currentUser.getEmail() + "|" + currentUser.getPassword());
                    writer.newLine();
                }
            }

            if (!found) {
                throw new IllegalArgumentException("User not found");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(int id) {
        List<User> users = findAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for(User user: users) {
                if(!user.getId().equals(id)) {
                    writer.write(user.getId() + "|" + user.getName() + "|"
                            + user.getEmail() + "|" + user.getPassword());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine())!= null) {
                if (line.startsWith(String.valueOf(id))) {
                    String[] arr = line.split("\\|");

                    String name = arr[1];
                    String email = arr[2];
                    String password = arr[3];

                    return Optional.of(new User(id, name, email, password));
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return Optional.empty();
    }
}

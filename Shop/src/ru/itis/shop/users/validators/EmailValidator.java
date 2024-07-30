package ru.itis.shop.users.validators;


public interface EmailValidator {
    void validate(String email) throws IllegalArgumentException;
}

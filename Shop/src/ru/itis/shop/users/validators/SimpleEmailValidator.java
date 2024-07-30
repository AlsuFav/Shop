package ru.itis.shop.users.validators;


public class SimpleEmailValidator implements EmailValidator {

    @Override
    public void validate(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Incorrect email");
        }
    }
}

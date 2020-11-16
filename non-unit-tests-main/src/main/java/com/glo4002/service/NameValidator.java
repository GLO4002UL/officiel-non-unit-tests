package com.glo4002.service;

public class NameValidator {

    public void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException();
        }
    }
}

package com.glo4002.domain;

public class Friend {

    private final String name;

    public Friend(String name) {
        this.name = name.toUpperCase();
    }

    public String getName() {
        return name;
    }
}

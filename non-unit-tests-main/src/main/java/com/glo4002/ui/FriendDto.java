package com.glo4002.ui;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(Include.NON_NULL)
public class FriendDto {

    private final String name;

    public FriendDto(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

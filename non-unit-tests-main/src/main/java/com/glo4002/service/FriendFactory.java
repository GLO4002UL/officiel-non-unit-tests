package com.glo4002.service;

import com.glo4002.domain.Friend;

public class FriendFactory {

    public Friend createFriend(String name) {
        return new Friend(name);
    }
}

package com.glo4002.service;

import java.util.List;

import com.glo4002.domain.Friend;

public interface FriendRepository {

    void save(Friend friend);
    List<Friend> findAllFriends();
}

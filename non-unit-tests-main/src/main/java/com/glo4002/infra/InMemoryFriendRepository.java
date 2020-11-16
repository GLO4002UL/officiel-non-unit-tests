package com.glo4002.infra;

import java.util.ArrayList;
import java.util.List;

import com.glo4002.domain.Friend;
import com.glo4002.service.FriendRepository;

public class InMemoryFriendRepository implements FriendRepository {

    private final List<Friend> friends = new ArrayList<>();

    @Override
    public void save(Friend friend) {
        friends.add(friend);
    }

    @Override
    public List<Friend> findAllFriends() {
        return friends;
    }
}

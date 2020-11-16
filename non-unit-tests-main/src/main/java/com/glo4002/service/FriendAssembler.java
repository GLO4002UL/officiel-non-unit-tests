package com.glo4002.service;

import java.util.ArrayList;
import java.util.List;

import com.glo4002.domain.Friend;
import com.glo4002.ui.FriendDto;

public class FriendAssembler {

    public List<FriendDto> toFriendsDto(List<Friend> friends) {
        List<FriendDto> friendsDto = new ArrayList<>();
        friends.forEach(friend -> friendsDto.add(toFriendDto(friend)));
        return friendsDto;
    }

    private FriendDto toFriendDto(Friend friend) {
        return new FriendDto(friend.getName().toLowerCase());
    }
}

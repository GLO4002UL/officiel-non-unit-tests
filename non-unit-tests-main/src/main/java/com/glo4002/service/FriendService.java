package com.glo4002.service;

import java.util.List;

import com.glo4002.domain.Friend;
import com.glo4002.ui.FriendDto;

public class FriendService {

    private final NameValidator nameValidator;
    private final FriendFactory friendFactory;
    private final FriendRepository friendRepository;
    private final FriendAssembler friendAssembler;

    public FriendService(NameValidator nameValidator, FriendFactory friendFactory, FriendRepository friendRepository, FriendAssembler friendAssembler) {
        this.nameValidator = nameValidator;
        this.friendFactory = friendFactory;
        this.friendRepository = friendRepository;
        this.friendAssembler = friendAssembler;
    }

    public void makeFriend(String name) {
        nameValidator.validate(name);
        Friend friend = friendFactory.createFriend(name);
        friendRepository.save(friend);
    }

    public List<FriendDto> findAllFriends() {
        List<Friend> friends = friendRepository.findAllFriends();
        return friendAssembler.toFriendsDto(friends);
    }
}

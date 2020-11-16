package com.glo4002.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.glo4002.domain.Friend;
import com.glo4002.ui.FriendDto;

public class FriendServiceIT {

    private static final String A_NAME = "This is a valid name";
    private static final String INVALID_NAME = null;

    private FriendRepository friendRepositoryMock;
    
    private FriendService friendService;

    @BeforeEach
    public void setup() {
        friendRepositoryMock = mock(FriendRepository.class);
        friendService = new FriendService(new NameValidator(), new FriendFactory(), friendRepositoryMock, new FriendAssembler());
    }

    @Test
    public void givenInvalidName_whenMakingFriend_thenNothingIsSaved() {
        assertThrows(InvalidNameException.class, () -> friendService.makeFriend(INVALID_NAME));
        verify(friendRepositoryMock, never()).save(any());
    }

    @Test
    public void givenAName_whenMakingFriend_thenFriendIsSavedWithThatNameUpperCased() {
        friendService.makeFriend(A_NAME);
        verify(friendRepositoryMock).save(argThat(friend -> friend.getName().equals(A_NAME.toUpperCase())));
    }

    @Test
    public void whenFindingAllFriends_thenAllFriendsAreReturnedWithLowerCasedNames() {
        String name1 = "BOB ROSS";
        String name2 = "CHUCK NORRIS";
        String name3 = "DONALD DUCK";
        List<Friend> allFriendNames = Arrays.asList(new Friend(name1), new Friend(name2), new Friend(name3));
        List<String> expectedLowerCasedNames = Arrays.asList(name1.toLowerCase(), name2.toLowerCase(), name3.toLowerCase());
        when(friendRepositoryMock.findAllFriends()).thenReturn(allFriendNames);

        List<FriendDto> friends = friendService.findAllFriends();
        List<String> friendNames = friends.stream().map(FriendDto::getName).collect(Collectors.toList());

        assertEquals(expectedLowerCasedNames, friendNames);
    }
}

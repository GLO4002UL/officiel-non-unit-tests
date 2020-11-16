package com.glo4002.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.glo4002.domain.Friend;
import com.glo4002.infra.InMemoryFriendRepository;
import com.glo4002.ui.FriendDto;

public class FriendRepositoryIT {

    private static final Friend FRIEND1 = new Friend("Alan");
    private static final Friend FRIEND2 = new Friend("Britney");
    private static final Friend FRIEND3 = new Friend("Zyra");
    private static final List<Friend> FRIENDS = Arrays.asList(FRIEND1, FRIEND2, FRIEND3);
    private static final List<FriendDto> EXPECTED_FRIENDS = mock(List.class);

    private NameValidator nameValidatorMock;
    private FriendFactory friendFactoryMock;
    private FriendAssembler friendAssemblerMock;

    private FriendRepository friendRepository;
    private FriendService friendService;

    @BeforeEach
    public void setup() {
        nameValidatorMock = mock(NameValidator.class);
        friendFactoryMock = mock(FriendFactory.class);
        friendAssemblerMock = mock(FriendAssembler.class);

        friendRepository = new InMemoryFriendRepository();
        friendService = new FriendService(nameValidatorMock, friendFactoryMock, friendRepository, friendAssemblerMock);
    }

    @Test
    public void givenFriends_whenFindingAllFriends_thenThoseFriendsAreFound() {
        given(FRIENDS);
        when(friendAssemblerMock.toFriendsDto(FRIENDS)).thenReturn(EXPECTED_FRIENDS);

        List<FriendDto> actualFriends = friendService.findAllFriends();

        assertSame(EXPECTED_FRIENDS, actualFriends);
    }

    private void given(List<Friend> friends) {
        friends.forEach(friend -> friendRepository.save(friend));
    }
}

// 2. b) How would non-unit tests differ when testing an external database persistence?
// It's important to make sure we don't test using the real database. Otherwise, we risk corrupting data used by others.
// Some solutions consists of using an embedded database instead of a real one. It's also common to use a test container to ensure consistency.


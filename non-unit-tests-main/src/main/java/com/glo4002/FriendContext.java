package com.glo4002;

import java.util.Collections;

import com.glo4002.infra.InMemoryFriendRepository;
import com.glo4002.server.FriendServer;
import com.glo4002.service.FriendAssembler;
import com.glo4002.service.FriendFactory;
import com.glo4002.service.FriendService;
import com.glo4002.service.NameValidator;
import com.glo4002.ui.FriendResource;

public class FriendContext {

    private static final int PORT = 8080;

    private final FriendServer server;

    public FriendContext() {
        FriendService friendService = new FriendService(new NameValidator(), new FriendFactory(), new InMemoryFriendRepository(), new FriendAssembler());
        server = new FriendServer(PORT, Collections.singletonList(new FriendResource(friendService)));
    }

    public void start() throws Exception {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server.isRunning()) {
                server.stop();
                server.destroy();
            }
        }
    }
}

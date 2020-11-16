package com.glo4002.ui;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.glo4002.service.FriendService;

@Path("/friends")
public class FriendResource {

    private final FriendService friendService;

    public FriendResource(FriendService friendService) {
        this.friendService = friendService;
    }

    @POST
    @Path("/make")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeFriend(@QueryParam("name") String name) {
        friendService.makeFriend(name);
        return Response.ok().build();
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllFriends() {
        return Response.ok().entity(friendService.findAllFriends()).build();
    }
}

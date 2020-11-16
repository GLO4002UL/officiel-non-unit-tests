package com.glo4002.ui;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.glo4002.service.InvalidNameException;

@Provider
public class CatchInvalidRequestMapper implements ExceptionMapper<InvalidNameException> {

    @Override
    public Response toResponse(InvalidNameException e) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
    }
}

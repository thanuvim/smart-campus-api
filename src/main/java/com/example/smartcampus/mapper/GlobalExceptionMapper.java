package com.example.smartcampus.mapper;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable exception) {

        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Map.of(
                            "error", "Not Found",
                            "message", "The requested resource does not exist.",
                            "status", 404
                    ))
                    .build();
        }

        LOGGER.log(Level.SEVERE, "Unexpected server error", exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(Map.of(
                        "error", "Internal server error",
                        "message", "An unexpected error occurred. Please contact the administrator.",
                        "status", 500
                ))
                .build();
    }
}
package com.chococompany.control.exception;

import io.quarkus.logging.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ChocolateExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        return mapExceptionToResponse(exception);
    }

    private Response mapExceptionToResponse(Exception exception) {

        if (exception instanceof ApiVersionException) {

            Response originalErrorResponse = Response.ok().entity(exception).build();
            return Response.fromResponse(originalErrorResponse)
                    .status(Response.Status.BAD_REQUEST)
                    .entity(exception.getMessage())
                    .build();
        }
        // Use 500 (Internal Server Error) for all other
        else {
            return Response
                    .serverError()
                    .entity("Erro no servidor")
                    .build();
        }
    }
}
package com.webservice.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.webservice.rest.messenger.model.ErrorCode;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>  {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorCode error= new ErrorCode(500, ex.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).type("application/json").build();
	}

}
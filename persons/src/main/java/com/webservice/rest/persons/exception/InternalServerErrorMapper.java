package com.webservice.rest.persons.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.webservice.rest.persons.model.ErrorCode;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<InternalServerError>  {

	@Override
	public Response toResponse(InternalServerError ex) {
		ErrorCode error= new ErrorCode(500, ex.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

}

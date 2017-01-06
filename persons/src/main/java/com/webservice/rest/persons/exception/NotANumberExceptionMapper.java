package com.webservice.rest.persons.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.webservice.rest.persons.model.ErrorCode;

@Provider
public class NotANumberExceptionMapper implements ExceptionMapper<NotANumberException>  {

	@Override
	public Response toResponse(NotANumberException ex) {
		ErrorCode error= new ErrorCode(400, ex.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}
}

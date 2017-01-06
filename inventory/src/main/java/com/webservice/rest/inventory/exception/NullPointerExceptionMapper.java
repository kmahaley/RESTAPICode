package com.webservice.rest.inventory.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.webservice.rest.inventory.model.ErrorCode;

public class NullPointerExceptionMapper implements ExceptionMapper<NullPointerException>  {

	@Override
	public Response toResponse(NullPointerException ex) {
		ErrorCode error= new ErrorCode("Null pointer exception", ex.getMessage());
		return Response.status(Status.SERVICE_UNAVAILABLE).entity(error).build();
	}

}

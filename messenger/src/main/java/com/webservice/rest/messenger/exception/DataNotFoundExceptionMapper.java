package com.webservice.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.webservice.rest.messenger.model.ErrorCode;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>  {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorCode error= new ErrorCode(404, ex.getMessage());
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}

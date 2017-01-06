package com.webservice.rest.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.webservice.rest.messenger.model.Message;
import com.webservice.rest.messenger.service.MessageService;
/**
 * 
 * @author kartik
 * Can annotate each individual methods for consumption or production of the JSON
 * Object or can annotate class for consumes and produces which will make code simpler to read
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms= new MessageService();
	
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size){
		if(year > 0)
			return ms.getAllMessagesForYear(year);
		if (start >= 0 && size > 0)
			return ms.getAllMessagesPaginated(start, size);
		return ms.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") int messageId){
		return ms.getMessage(messageId);
	}
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") int messageId, Message m){
		m.setId(messageId);
		return ms.updateMessage(m);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") int messageId){
		ms.removeMessage(messageId);
	}
	
	
	@POST
	public Response addMessage(Message m, @Context UriInfo uriInfo) {
		Message msg = ms.addMessage(m);
		String msgId = String.valueOf(msg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(msgId).build();
		return Response.created(uri).entity(msg).build();
	}
}

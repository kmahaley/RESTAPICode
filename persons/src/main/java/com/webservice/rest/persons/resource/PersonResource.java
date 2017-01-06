package com.webservice.rest.persons.resource;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.webservice.rest.persons.exception.NotANumberException;
import com.webservice.rest.persons.model.Person;
import com.webservice.rest.persons.service.PersonService;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
	
	PersonService ps=new PersonService("/COMPLETE_FILE_PATH/Downloads/person.txt");
	static Logger log = Logger.getLogger(PersonService.class);

	@GET
	public List<Person> getPersons(){
		return ps.getAllPersons();
	}

	@GET
	@Path("/{personId}")
	public Response getPerson(@PathParam("personId") String personId){
		int id=0;
		try{
			id=Integer.parseInt(personId);
		}catch(NumberFormatException ex){
			log.error("Get, Bad Request : Not a number ->"+personId);
			throw new NotANumberException("Get, Bad Request : Not a number ->"+personId );
			
		}
		
		return Response.ok(ps.getPerson(id)).build();
	}
	
	@POST
	public Response addPerson(Person p, @Context UriInfo uriInfo) {
		Person person = ps.addPerson(p);
		String msgId = String.valueOf(person.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(msgId).build();
		return Response.created(uri).entity(person).build();
	}
	
	@PUT
	@Path("/{personId}")
	public Response updatePerson(@PathParam("personId") String personId, Person p){
		int id=0;
		try{
			id=Integer.parseInt(personId);
		}catch(NumberFormatException ex){
			log.error("Update, Bad Request : Not a number ->"+personId);
			throw new NotANumberException("Update, Bad Request : Not a number "+personId);
		}
		p.setId(id);
		return Response.ok(ps.updatePerson(id,p)).build();
	}
	
	@DELETE
	@Path("/{personId}")
	public Response deletePerson(@PathParam("personId") String personId){
		int id=0;
		try{
			id=Integer.parseInt(personId);
		}catch(NumberFormatException ex){
			log.error("Delete, Bad Request : Not a number ->"+personId);
			throw new NotANumberException("Delete, Bad Request : Not a number ->"+personId);
		}
		ps.removePerson(id);
		return Response.noContent().build();
	}
	
}

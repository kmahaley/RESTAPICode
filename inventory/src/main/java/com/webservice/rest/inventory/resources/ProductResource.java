package com.webservice.rest.inventory.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Response.Status;

import com.webservice.rest.inventory.model.Product;
import com.webservice.rest.inventory.service.ProductService;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	ProductService ps= new ProductService();
	
	@GET
	public List<Product> getProducts(@QueryParam("store") int store, @QueryParam("name") String name){
		if(store > 0 && name !=null){
			ps.getProductsForProductAndStore(store, name);
			return null;
		}
		return ps.getAllProducts();
	}
	
	
	@GET
	@Path("/{sku}")
	public List<Product> getProduct(@PathParam("sku") int sku){
		return ps.getProductBySKU(sku);
	}
	
	@POST
	public Response addProduct(Product p, @Context UriInfo uriInfo) {
		ps.addProduct(p);
		return Response.status(Status.CREATED).entity(p).build();
	}
	
	@PUT
	public Response updateMessage(Product p){
		ps.updateProduct(p.getName(),p.getStore(),p.getCount());
		return Response.status(Status.NO_CONTENT).entity(p).build();
	}
}

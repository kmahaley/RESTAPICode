package com.webservice.rest.messenger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public String getIt() {
        return "Got it!";
    }
}

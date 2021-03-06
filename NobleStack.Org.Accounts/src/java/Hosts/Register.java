/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hosts;

import Implementation.RegisterBLC;
import NobleStack.Org.DataContracts.Accounts.RegisterRequest;
import NobleStack.Org.Utils.Common.Parser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rajesh
 */
@Path("register")
public class Register {

    @Context
    private UriInfo context;
    /**
     * Creates a new instance of GenericResource
     */
    public Register() {
    }

    /**
     * Retrieves representation of an instance of Hosts.Register
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register/hello")
    public String hello() {
        //TODO return proper representation object
        //Register s1 = new Parser<Register>().convert(s, Register.class);.
        return "Hello World";
    }

    /**
     * PUT method for updating or creating an instance of Register
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Path("registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response RegisterUser(String content) {
        Response response = null;
        try {
            RegisterRequest requestContent = new Parser<RegisterRequest>().convertToObject(content, RegisterRequest.class);
            if(requestContent == null){
                throw new IllegalArgumentException("RegisterRequest");
            }
            RegisterBLC registerUserBlc  = new RegisterBLC();
            int userid =  registerUserBlc.RegisterUser(requestContent);
            response = Response.ok(new Parser().convertToJson(userid) , MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return response;
    }
    
        /**
     * PUT method for updating or creating an instance of Register
     * @param content representation for the resource
     */
    @POST
    @Path("registerMessagingToken")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response RegisterMessagingToken(String content) {
        Response response = null;
        try {
            RegisterRequest requestContent = new Parser<RegisterRequest>().convertToObject(content, RegisterRequest.class);
            if (requestContent == null) {
                throw new IllegalArgumentException();
            }
            RegisterBLC registerUserBlc = new RegisterBLC();
            registerUserBlc.RegisterMessagingToken(requestContent);
            response = Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return response;
    }
}

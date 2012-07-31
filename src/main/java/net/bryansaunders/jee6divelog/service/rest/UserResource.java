package net.bryansaunders.jee6divelog.service.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.service.UserAccountService;

/**
 * User Rest API.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    /**
     * User Account Service.
     */
    @Inject
    private UserAccountService userAccountService;

    /**
     * Registers a User.
     * 
     * @param user
     *            User to Register
     * @return Registered User
     */
    @POST
    @Path("/register")
    public Response registerUser(final UserAccount user) {
        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        if (user == null) {
            response = Response.status(Response.Status.BAD_REQUEST).entity("User Object must not be null").build();
        } else {
            UserAccount savedUser = this.userAccountService.createUser(user);
            savedUser.setPassword("***"); // Clear out the Password
            response = Response.ok(savedUser).status(Response.Status.CREATED).build();
        }

        return response;
    }

    /**
     * Gets the User Specified by the Given Username.
     * 
     * @param userName
     *            Username to serach for
     * @return Found user
     */
    @GET
    @Path("/find/{userName}")
    public Response findUser(@PathParam("userName") final String userName) {
        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        final UserAccount foundUser = this.userAccountService.findByUserEmail(userName);
        if (foundUser != null) {
            foundUser.setPassword("***"); // Clear out the Password
            response = Response.ok(foundUser).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity("User Not Found.").build();
        }

        return response;
    }
}

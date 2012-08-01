package net.bryansaunders.jee6divelog.service.rest;

import javax.ejb.EJBException;
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

import org.codehaus.enunciate.jaxrs.TypeHint;

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
     * <ul>
     * <li>Status 200: Successful Registration.</li>
     * <li>Status 401: Error with the Request. Most likely caused by an invalid UserAccount.</li>
     * </ul>
     * 
     * @param user
     *            User to Register
     * @return Registered User
     */
    @POST
    @Path("/register")
    @TypeHint(UserAccount.class)
    public Response registerUser(final UserAccount user) {
        Response response;

        if (user == null) {
            response = Response.status(Response.Status.BAD_REQUEST).entity("User Object must not be null").build();
        } else {
            try {
                final UserAccount savedUser = this.userAccountService.createUser(user);
                savedUser.setPassword("***"); // Clear out the Password
                response = Response.ok(savedUser).status(Response.Status.CREATED).build();
            } catch (final EJBException e) {
                response = Response.status(Response.Status.BAD_REQUEST).entity("JSON Invalid: " + e.getMessage())
                        .build();
            }
        }

        return response;
    }

    /**
     * Gets the User Specified by the Given Username.
     * 
     * <ul>
     * <li>Status 200: User Found.</li>
     * <li>Status 404: User was not found.</li>
     * <li>Status 401: Error with the Request.</li>
     * </ul>
     * 
     * @param userName
     *            Username to serach for
     * @return Found user
     */
    @GET
    @Path("/find/{userName}")
    @TypeHint(UserAccount.class)
    public Response findUser(@PathParam("userName") final String userName) {
        Response response;

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

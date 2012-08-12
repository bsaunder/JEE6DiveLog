package net.bryansaunders.jee6divelog.service.rest;

import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.enumerator.Role;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

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
@RequestScoped
public class UserAccountApi {

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
     * <li>Status 400: Error with the Request. Most likely caused by an invalid UserAccount.</li>
     * </ul>
     * 
     * @param user
     *            User to Register
     * @return Registered User
     */
    @POST
    @Path("register")
    @TypeHint(UserAccount.class)
    public Response registerUser(final UserAccount user) {
        Response response;

        try {
            UserAccount savedUser = this.userAccountService.createUser(user);
            savedUser = SecurityUtils.getCleanUserAccount(savedUser);
            response = Response.ok(savedUser).status(Response.Status.CREATED).build();
        } catch (final EJBException e) {
            response = Response.status(Response.Status.BAD_REQUEST).entity("JSON Invalid: " + e.getMessage()).build();
        }

        return response;
    }

    /**
     * Gets the User Specified by the Given Criteria. If not Criteria Specified, All Results will be returned.
     * 
     * <ul>
     * <li>Status 200: Results Found.</li>
     * <li>Status 400: Results not found.</li>
     * </ul>
     * 
     * @param example
     *            Criteria to search for
     * @return Found users
     */
    @POST
    @Path("find")
    @TypeHint(List.class)
    @HasRole(role = Role.USER)
    public Response findUser(final UserAccount example) {
        Response response = null;

        List<UserAccount> results = this.userAccountService.findByExample(example);
        if (results != null && !results.isEmpty()) {
            response = Response.ok(results).build();
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).entity("No Results Found.").build();
        }

        return response;
    }

    /**
     * Gets all Users.
     * 
     * <ul>
     * <li>Status 200: Users Found.</li>
     * </ul>
     * 
     * @return List of Users
     */
    @GET
    @Path("")
    @TypeHint(List.class)
    @HasRole(role = Role.USER)
    public Response getAllUsers() {
        return Response.status(501).entity("Get All Not Implemented.").build();
    }

    /**
     * Gets the User Specified by the Given Username.
     * 
     * <ul>
     * <li>Status 200: User Found.</li>
     * <li>Status 400: User was not found.</li>
     * </ul>
     * 
     * @param userName
     *            Username to serach for
     * @return Found user
     */
    @GET
    @Path("{userName}")
    @TypeHint(UserAccount.class)
    @HasRole(role = Role.USER)
    public Response getUser(@PathParam("userName") final String userName) {
        return Response.status(501).entity("Get User Not Implemented.").build();
    }

    /**
     * Updates the User Specified by the Given Username.
     * 
     * <ul>
     * <li>Status 200: User Updated.</li>
     * </ul>
     * 
     * @param userName
     *            Username to update
     * @param updatedUserAccount
     *            Updated User Account
     * @return Updated user
     */
    @PUT
    @Path("{userName}")
    @TypeHint(UserAccount.class)
    @HasRole(role = Role.USER)
    public Response updateUser(@PathParam("userName") final String userName, final UserAccount updatedUserAccount) {
        // If not Self, Then Must Have EDIT_USER
        // If Self, Then Must Have EDIT_SELF
        return Response.status(501).entity("Put Not Implemented.").build();
    }

    /**
     * Deletes the User Specified by the Given Username.
     * 
     * <ul>
     * <li>Status 200: User Delete.</li>
     * </ul>
     * 
     * @param userName
     *            Username to delete
     * @return Delete status
     */
    @DELETE
    @Path("{userName}")
    @TypeHint(Boolean.class)
    @HasRole(role = Role.USER)
    public Response deleteUser(@PathParam("userName") final String userName) {
        // If not Self, Then Must Have DELETE_USER
        // If Self, Then Must Have DELETE_SELF
        return Response.status(501).entity("Delete Not Implemented.").build();
    }
}

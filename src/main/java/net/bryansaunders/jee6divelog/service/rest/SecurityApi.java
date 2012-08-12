/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.enumerator.Role;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security REST API.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Path("/security")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SecurityApi {
    
    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(SecurityApi.class);

    /**
     * User Account Service.
     */
    @Inject
    private UserAccountService userAccountService;

    /**
     * User Identity.
     */
    @Inject
    private Identity identity;

    /**
     * User Credentials.
     */
    @Inject
    private Credentials credentials;
    
    /**
     * Identifies the currently logged in REST user.
     * 
     * <ul>
     * <li>Status 202: Login Successful.</li>
     * <li>Status 401: Login Failed.</li>
     * </ul>
     * 
     * @param incomingCredentials
     *            Users Login Credentials
     * @return User API Token
     */
    @POST
    @Path("login")
    @TypeHint(String.class)
    public Response login(final Credentials incomingCredentials) {
        Response response;

        this.credentials.setUsername(incomingCredentials.getUsername());
        this.credentials.setPassword(incomingCredentials.getPassword());
        final boolean loginResult = this.identity.restLogin();
        if (loginResult) {
            final String userName = this.identity.getUsername();
            final String apiKey = this.identity.getApiKey();
            final String token = SecurityUtils.generateRestApiToken(userName, apiKey);
            response = Response.ok(token).status(Response.Status.ACCEPTED).build();
        } else {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return response;
    }

    /**
     * Identifies the currently logged in REST user.
     * 
     * <ul>
     * <li>Status 200: Request Successful.</li>
     * </ul>
     * 
     * @return User Account
     */
    @GET
    @Path("identify")
    @TypeHint(UserAccount.class)
    @HasRole(role = Role.USER)
    public Response identify() {
        UserAccount userAccount = this.identity.createUserAccount();
        userAccount = SecurityUtils.getCleanUserAccount(userAccount);
        this.logger.debug("User Identity: " + userAccount.toString());
        return Response.ok(userAccount).build();
    }

    /**
     * Logout the Current REST User.
     * 
     * <ul>
     * <li>Status 200: Logout Request Successful.</li>
     * </ul>
     * 
     * @return Logout Result
     */
    @POST
    @Path("logout")
    @TypeHint(Boolean.class)
    @HasRole(role = Role.USER)
    public Response logout() {
        final String username = this.identity.getUsername();
        this.identity.logout();
        final boolean result = this.userAccountService.clearApiKey(username);
        return Response.ok(result).build();
    }
}

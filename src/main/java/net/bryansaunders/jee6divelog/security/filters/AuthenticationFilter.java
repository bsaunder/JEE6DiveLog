/**
 * 
 */
package net.bryansaunders.jee6divelog.security.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import net.bryansaunders.jee6divelog.security.Identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication filter that redirects non-logged in users.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class AuthenticationFilter implements Filter {

    /**
     * Identity.
     */
    @Inject
    private Identity identity;

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    /**
     * Redirect non-logged in users to the Login page.
     * 
     * @param request
     *            Servlet Request
     * @param response
     *            Servlet Response
     * @param filterChain
     *            Filter Chain
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     * @throws IOException
     *             Thrown on IO Error
     * @throws ServletException
     *             Thrown on Servlet Error
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
        throws IOException, ServletException {

        if (this.identity == null) {
            throw new ServletException("Identity is null");
        } else {
            if (this.identity.isLoggedIn()) {
                filterChain.doFilter(request, response);
            } else {
                this.logger.info("Unathorized Access Attempt.");
                final HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

}

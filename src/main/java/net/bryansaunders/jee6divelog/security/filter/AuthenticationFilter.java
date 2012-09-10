/**
 * 
 */
package net.bryansaunders.jee6divelog.security.filter;/*
 * #%L
 * BSNet-DiveLog
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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

import org.jboss.logging.Logger;

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
    private Logger logger = Logger.getLogger(AuthenticationFilter.class);

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

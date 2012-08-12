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


import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.filter.AuthenticationFilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Authentication Filter Test.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class AuthenticationFilterTest {

    /**
     * Mock Identity.
     */
    @Mock
    private Identity mockIdentity;

    /**
     * Mock Request.
     */
    @Mock
    private ServletRequest request;

    /**
     * Mock Response.
     */
    @Mock
    private HttpServletResponse response;

    /**
     * Mock FilterChain.
     */
    @Mock
    private FilterChain filterChain;

    /**
     * Authentication Filter with Mocks Injected.
     */
    @InjectMocks
    private AuthenticationFilter authFilter;

    /**
     * Test Setup to Initialize Mocks.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test Redirection.
     * 
     * @throws Exception
     *             Thrown on error.
     */
    @Test
    public void ifLoggedInThenRedirect() throws Exception {
        // given
        when(this.mockIdentity.isLoggedIn()).thenReturn(true);

        // when
        this.authFilter.doFilter(this.request, this.response, this.filterChain);

        // then
        verify(this.filterChain).doFilter(any(ServletRequest.class), any(ServletResponse.class));
    }

    /**
     * Test 401 Error.
     * 
     * @throws Exception
     *             Thrown on error.
     */
    @Test
    public void ifNotLoggedInThenError401() throws Exception {
        // given
        when(this.mockIdentity.isLoggedIn()).thenReturn(false);

        // when
        this.authFilter.doFilter(this.request, this.response, this.filterChain);

        // then
        verify(this.response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * Test Null Identity.
     * 
     * @throws Exception
     *             Thrown on error.
     */
    @Test(expected = ServletException.class)
    public void ifIdentityNullThenException() throws Exception {
        // given
        // Reflection Voodoo to set the Identity Field Null.
        Field identityField = this.authFilter.getClass().getDeclaredField("identity");
        identityField.setAccessible(true);
        identityField.set(this.authFilter, null);

        // when
        this.authFilter.doFilter(this.request, this.response, this.filterChain);

        // given
        fail("ServeletException expected");
    }

}

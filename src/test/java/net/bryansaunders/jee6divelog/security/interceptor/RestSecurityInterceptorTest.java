/**
 * 
 */
package net.bryansaunders.jee6divelog.security.interceptor;

/*
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.service.rest.RestApi;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.specimpl.HttpHeadersImpl;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.HttpResponseCodes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * RestSecurityInterceptor Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class RestSecurityInterceptorTest {

    /**
     * Mock Identity.
     */
    @Mock
    private Identity mockIdentity;

    /**
     * Mock User Account Service.
     */
    @Mock
    private UserAccountService mockAccountService;

    /**
     * REST Security Interceptor.
     */
    @InjectMocks
    private RestSecurityInterceptor interceptor;

    /**
     * User Account.
     */
    private UserAccount userAccount;

    /**
     * Secure Method.
     */
    private Method secureMethod;

    /**
     * Mock HTTP Request.
     */
    private HttpRequest httpRequest;

    /**
     * HTTP Request Headers.
     */
    private HttpHeadersImpl httpHeaders;

    /**
     * Test Setup.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @SuppressWarnings("deprecation")
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        final String requestUrl = "http://localhost:8080/jee6divelog_test/REST/user/find";

        final String date = new Date(2012, 1, 1).toGMTString();
        final String contentType = MediaType.APPLICATION_JSON;
        final String contentMd5 = null;
        final String publicApiKey = SecurityUtils.generateRestApiKey();
        final String privateApiKey = SecurityUtils.generateRestApiKey();
        final String signature = SecurityUtils.generateRestSignature("GET", contentType, contentMd5, date, requestUrl,
                privateApiKey);
        final Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.HOUR, 5);

        final MultivaluedMap<String, String> requestHeaders = new MultivaluedMapImpl<String, String>();
        requestHeaders.add(HttpHeaders.DATE, date);
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);
        requestHeaders.add(RestApi.CONTENT_MD5_HEADER, contentMd5);
        requestHeaders.add(RestApi.PUBLIC_KEY_HEADER, publicApiKey);
        requestHeaders.add(RestApi.SIGNATURE_HEADER, signature);

        this.secureMethod = PermissionInterceptorSandbox.class.getMethod("hasPermissionEditSelfMethod");
        this.httpRequest = MockHttpRequest.get(requestUrl);
        this.httpHeaders = (HttpHeadersImpl) this.httpRequest.getHttpHeaders();
        this.httpHeaders.setRequestHeaders(requestHeaders);

        this.userAccount = new UserAccount();
        this.userAccount.setPublicApiKey(publicApiKey);
        this.userAccount.setPrivateApiKey(privateApiKey);
        this.userAccount.setApiKeyExpiration(expiration.getTime());
        this.userAccount.setFirstName("Test");
        this.userAccount.setLastName("Testerson");
        this.userAccount.setEmail("test@test.com");
        this.userAccount.setPassword("***");
    }

    /**
     * Test preProcess method with Valid Request.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void ifValidThenAuthorized() {
        // given
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        final List<UserAccount> userList = new LinkedList<UserAccount>();
        userList.add(this.userAccount);
        when(this.mockAccountService.findByExample(any(UserAccount.class))).thenReturn(userList);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNull(response);
        verify(this.mockIdentity).setPublicApiKey(any(String.class));
        verify(this.mockIdentity).setPrivateApiKey(any(String.class));
        verify(this.mockIdentity).setApiKeyExpiration(any(Date.class));
        verify(this.mockIdentity).setPermissions(any(Set.class));
        verify(this.mockIdentity).setStatus(anyInt());
        verify(this.mockIdentity).setCredentials(any(Credentials.class));
    }

    /**
     * Test preProcess method with Missing Headers.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifNotSecureThenAuthorized() throws Exception {
        // given
        final Method unsecureMethod = PermissionInterceptorSandbox.class.getMethod("blankMethod");
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(unsecureMethod);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNull(response);
    }

    /**
     * Test preProcess method with Missing Headers.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifHeadersMissingThenBadRequest() throws Exception {
        // given
        this.httpHeaders.setRequestHeaders(new MultivaluedMapImpl<String, String>());
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNotNull(response);
        assertEquals(HttpResponseCodes.SC_BAD_REQUEST, response.getStatus());
    }

    /**
     * Test preProcess method with Invalid User.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifUserInvalidThenUnauthorized() throws Exception {
        // given
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNotNull(response);
        assertEquals(HttpResponseCodes.SC_UNAUTHORIZED, response.getStatus());
    }

    /**
     * Test preProcess method with Expired Token.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifTokenExpiredThenUnauthorized() throws Exception {
        // given
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        this.userAccount.setApiKeyExpiration(new Date(0));
        when(this.mockAccountService.findByUserEmail(any(String.class))).thenReturn(this.userAccount);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNotNull(response);
        assertEquals(HttpResponseCodes.SC_UNAUTHORIZED, response.getStatus());
    }

    /**
     * Test preProcess method with Null Token Expiration.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifTokenExpirationNullThenUnauthorized() throws Exception {
        // given
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        this.userAccount.setApiKeyExpiration(null);
        when(this.mockAccountService.findByUserEmail(any(String.class))).thenReturn(this.userAccount);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNotNull(response);
        assertEquals(HttpResponseCodes.SC_UNAUTHORIZED, response.getStatus());
    }

    /**
     * Test preProcess method with Invalid Token.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifTokenInvalidThenUnauthorized() throws Exception {
        // given
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(this.secureMethod);

        when(this.mockAccountService.findByUserEmail(any(String.class))).thenReturn(this.userAccount);

        // when
        final ServerResponse response = this.interceptor.preProcess(this.httpRequest, resourceMethod);

        // then
        assertNotNull(response);
        assertEquals(HttpResponseCodes.SC_UNAUTHORIZED, response.getStatus());
    }

}

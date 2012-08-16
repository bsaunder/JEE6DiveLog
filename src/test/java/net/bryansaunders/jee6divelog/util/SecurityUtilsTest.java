/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.interceptor.PermissionInterceptorSandbox;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.resteasy.core.ResourceMethod;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SecurityUtilsTest {

    /**
     * Test method for generatePasswordHash.
     */
    @Test
    public void testGeneratePasswordHash() {
        // given
        final String password = "pass123";

        // when
        final String hashedPass = SecurityUtils.generatePasswordHash(password);

        // then
        final String expectedPass = Base64.encodeBase64String(DigestUtils.sha256Hex(password).getBytes());
        assertEquals(expectedPass, hashedPass);
    }

    /**
     * Test Method for Generate REST API Key.
     */
    @Test
    public void testGenerateApiKey() {
        final String apiKey = SecurityUtils.generateRestApiKey();

        assertNotNull(apiKey);
        assertTrue(apiKey.length() >= 32);
        assertTrue(Base64.isBase64(apiKey));
    }

    /**
     * Tests if an Annotation is a Secure Annotation.
     */
    @Test
    public void ifAnnotationSecureThenTrue() {
        Annotation annotation = mock(HasPermission.class);
        assertTrue(SecurityUtils.isAnnotationSecure(annotation));

        annotation = mock(HasPermissions.class);
        assertTrue(SecurityUtils.isAnnotationSecure(annotation));
    }

    /**
     * Tests if a Method is a Secure Annotation.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifMethodSecureThenTrue() throws Exception {
        final Method method = PermissionInterceptorSandbox.class.getMethod("hasPermissionUserMethod");
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(method);

        assertTrue(SecurityUtils.isMethodSecure(resourceMethod));
    }

    /**
     * Tests if a Method is a Secure Annotation.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifMethodNotsecureThenFalse() throws Exception {
        final Method method = PermissionInterceptorSandbox.class.getMethod("blankMethod");
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(method);

        assertFalse(SecurityUtils.isMethodSecure(resourceMethod));
    }

    /**
     * Tests the Generation of REST Signatures.
     */
    @Test
    public void ifSignaturesValidThenPass() {
        final String privateKey = "3435y5#=G-E%#45yq354y35ghW=%YQE%HG3";
        final String httpMethod = HttpMethod.GET;
        final String contentType = MediaType.APPLICATION_JSON;
        final String contendMd5 = "n34g3445g34234345ge";
        final String date = "2012-01-01";
        final String requestUrl = "fsdfsdf34sregfsre";

        final String expected = "EqhLmfdxK9zvETh4jZu5RVMTGLQ=";

        final String result = SecurityUtils.generateRestSignature(httpMethod, contentType, contendMd5, date,
                requestUrl, privateKey);

        assertEquals(expected, result);
    }

}

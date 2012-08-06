/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.annotation.HasRoles;
import net.bryansaunders.jee6divelog.security.interceptor.RoleInterceptorSandbox;

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
     * Test Method for Generate REST API Token.
     */
    @Test
    public void testGenerateToken() {
        // given
        final String username = "test@test.com";
        final String apiKey = SecurityUtils.generateRestApiKey();
        final String combined = username + apiKey;
        final String expctedToken = DigestUtils.sha256Hex(combined);

        // when
        final String token = SecurityUtils.generateRestApiToken(username, apiKey);

        // then
        assertEquals(expctedToken, token);
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

        annotation = mock(HasRole.class);
        assertTrue(SecurityUtils.isAnnotationSecure(annotation));

        annotation = mock(HasRoles.class);
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
        final Method method = RoleInterceptorSandbox.class.getMethod("hasRoleUserMethod");
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
        final Method method = RoleInterceptorSandbox.class.getMethod("blankMethod");
        final ResourceMethod resourceMethod = mock(ResourceMethod.class);
        when(resourceMethod.getMethod()).thenReturn(method);

        assertFalse(SecurityUtils.isMethodSecure(resourceMethod));
    }

}

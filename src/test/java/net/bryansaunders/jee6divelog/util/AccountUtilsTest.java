/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.junit.Test;

/**
 * Tests for Account Utilities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class AccountUtilsTest {

    /**
     * Tests the Clean UserAccount and DiveLogEntity methods.
     */
    @Test
    public void cleanUserAccount() {
        // given
        UserAccount userAccount = new UserAccount();
        final Date date = new Date();

        // when
        userAccount = AccountUtils.getCleanUserAccount(userAccount);

        // then
        assertEquals("***", userAccount.getPassword());
        assertEquals(date.getTime(), userAccount.getCreated().getTime(), 5);
        assertEquals(date.getTime(), userAccount.getUpdated().getTime(), 5);
    }

    /**
     * Test method for createUserAccount().
     */
    @Test
    public void testCreateUserAccount() {
        // given
        final String publicApiKey = "apiKey123445345";
        final String privateApiKey = "apiKey12ssdfs45345";
        final Date expirationDate = new Date();
        final String email = "email";
        final Set<Role> roles = new LinkedHashSet<Role>();
        final Set<Permission> permission = new LinkedHashSet<Permission>();

        final Identity identity = new Identity();
        identity.setPublicApiKey(publicApiKey);
        identity.setPrivateApiKey(privateApiKey);
        identity.setApiKeyExpiration(expirationDate);
        final Credentials credentials = new Credentials();
        credentials.setUsername(email);
        identity.setCredentials(credentials);
        identity.setRoles(roles);
        identity.setPermissions(permission);

        // when
        final UserAccount userAccount = AccountUtils.createUserAccount(identity);

        // then
        assertEquals(publicApiKey, userAccount.getPublicApiKey());
        assertEquals(privateApiKey, userAccount.getPrivateApiKey());
        assertEquals(expirationDate, userAccount.getApiKeyExpiration());
        assertEquals(email, userAccount.getEmail());
        assertEquals(roles, userAccount.getRoles());
        assertEquals(permission, userAccount.getPermissions());
    }

    /**
     * Test method for createCleanUserAccount().
     */
    @Test
    public void testCreateCleanUserAccount() {
        // given
        final String publicApiKey = "apiKey123445345";
        final String privateApiKey = "apiKey12ssdfs45345";
        final Date expirationDate = new Date();
        final String email = "email";
        final Set<Role> roles = new LinkedHashSet<Role>();
        final Set<Permission> permission = new LinkedHashSet<Permission>();

        final Identity identity = new Identity();
        identity.setPublicApiKey(publicApiKey);
        identity.setPrivateApiKey(privateApiKey);
        identity.setApiKeyExpiration(expirationDate);
        final Credentials credentials = new Credentials();
        credentials.setUsername(email);
        identity.setCredentials(credentials);
        identity.setRoles(roles);
        identity.setPermissions(permission);

        // when
        final UserAccount userAccount = AccountUtils.createCleanUserAccount(identity);

        // then
        assertEquals(publicApiKey, userAccount.getPublicApiKey());
        assertEquals(privateApiKey, userAccount.getPrivateApiKey());
        assertEquals(expirationDate, userAccount.getApiKeyExpiration());
        assertEquals(email, userAccount.getEmail());
        assertEquals(roles, userAccount.getRoles());
        assertEquals(permission, userAccount.getPermissions());
        assertEquals("***", userAccount.getPassword());
        assertNotNull(userAccount.getCreated());
        assertNotNull(userAccount.getUpdated());
    }

}

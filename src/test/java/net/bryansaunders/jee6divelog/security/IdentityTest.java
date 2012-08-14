/**
 * 
 */
package net.bryansaunders.jee6divelog.security;

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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class IdentityTest {

    /**
     * Identity.
     */
    private Identity identity;

    /**
     * Mock Credentials.
     */
    private Credentials mockCredentials;

    /**
     * Mock User Account Service.
     */
    private UserAccountService mockAccountService;

    /**
     * Test Setup.
     */
    @Before
    public void setupTest() {
        this.identity = new Identity();

        this.mockCredentials = new Credentials();
        this.mockAccountService = mock(UserAccountService.class);

        this.identity.setCredentials(this.mockCredentials);
        this.identity.setUserAccountService(this.mockAccountService);
    }

    /**
     * Test Entity Creation.
     */
    @Test
    public void testCreation() {
        assertNotNull(this.identity.getPassword());
        assertNotNull(this.identity.getUsername());
        assertFalse(this.identity.isLoggedIn());
    }

    /**
     * Test method for login().
     */
    @Test
    public void ifValidUserThenLogin() {
        // given
        final String username = "admin@test.com";
        final String password = "abcdef1A@";
        final String expectedPassword = SecurityUtils.generatePasswordHash(password);

        final UserAccount userAccount = new UserAccount();
        userAccount.setPassword(expectedPassword);

        final Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        this.identity.setCredentials(credentials);

        when(this.mockAccountService.findByUserEmail(username)).thenReturn(userAccount);

        // when
        final boolean result = this.identity.login();

        // then
        assertEquals(Identity.LOGIN_SUCCESS, result);
        assertTrue(this.identity.isLoggedIn());
        assertNull(this.identity.getPublicApiKey());
        assertNull(this.identity.getPrivateApiKey());
        assertNull(this.identity.getApiKeyExpiration());
    }

    /**
     * Test method for login().
     */
    @Test
    public void ifInvalidUserThenFail() {
        // given
        final String username = "admin@test.com";
        final String password = "abcdef1A@";

        final UserAccount userAccount = new UserAccount();
        userAccount.setPassword("1232532");

        final Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        this.identity.setCredentials(credentials);

        when(this.mockAccountService.findByUserEmail(username)).thenReturn(userAccount);

        // when
        final boolean result = this.identity.login();

        // then
        assertEquals(Identity.LOGIN_FAILURE, result);
        assertFalse(this.identity.isLoggedIn());
    }

    /**
     * Tests logout().
     */
    @Test
    public void ifLoggedInThenLogout() {
        // given
        final String username = "admin@test.com";
        final String password = "abcdef1A@";
        final String expectedPassword = SecurityUtils.generatePasswordHash(password);

        final UserAccount userAccount = new UserAccount();
        userAccount.setPassword(expectedPassword);

        final Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        this.identity.setCredentials(credentials);

        when(this.mockAccountService.findByUserEmail(username)).thenReturn(userAccount);
        this.identity.login();

        // when
        final String result = this.identity.logout();

        // then
        assertEquals(Identity.LOGOUT_SUCCESS, result);
        assertFalse(this.identity.isLoggedIn());
    }

    /**
     * Test method for Credentials.
     */
    @Test
    public void testCredentials() {
        final String newPassword = "Pass";
        final String newUsername = "User";

        final Credentials credentials = new Credentials();
        credentials.setPassword(newPassword);
        credentials.setUsername(newUsername);

        this.identity.setCredentials(credentials);

        assertEquals(newPassword, this.identity.getPassword());
        assertEquals(newUsername, this.identity.getUsername());
    }

    /**
     * Test method for Roles.
     */
    @Test
    public void testRoles() {
        final Set<Role> roleSet = new LinkedHashSet<Role>();
        roleSet.add(Role.ADMIN);

        this.identity.setRoles(roleSet);
        final Set<Role> roles = this.identity.getRoles();

        assertNotNull(roles);
        assertEquals(roleSet, roles);
    }

    /**
     * Test method for Permissions().
     */
    @Test
    public void testPermissions() {
        final Set<Permission> permissionSet = new LinkedHashSet<Permission>();
        permissionSet.add(Permission.EDIT_SELF);

        this.identity.setPermissions(permissionSet);
        final Set<Permission> permissions = this.identity.getPermissions();

        assertNotNull(permissions);
        assertEquals(permissionSet, permissions);
    }

    /**
     * Test method for RememberMe.
     */
    @Test
    public void testRememberMe() {
        this.identity.setRememberMe(false);
        assertFalse(this.identity.isRememberMe());

        this.identity.setRememberMe(true);
        assertTrue(this.identity.isRememberMe());
    }

    /**
     * Test for HasRole Method.
     */
    @Test
    public void ifHasRoleThenTrue() {
        final Set<Role> roleSet = new LinkedHashSet<Role>();
        roleSet.add(Role.ADMIN);
        this.identity.setRoles(roleSet);

        assertTrue(this.identity.hasRole(Role.ADMIN));
    }

    /**
     * Test for HasRole Method.
     */
    @Test
    public void ifRoleMissingThenFalse() {
        this.identity.setRoles(new LinkedHashSet<Role>());

        assertFalse(this.identity.hasRole(Role.ADMIN));
    }

    /**
     * Test for HasPermission Method.
     */
    @Test
    public void ifHasPermissionThenTrue() {
        final Set<Permission> permissionSet = new LinkedHashSet<Permission>();
        permissionSet.add(Permission.EDIT_SELF);
        this.identity.setPermissions(permissionSet);

        assertTrue(this.identity.hasPermission(Permission.EDIT_SELF));
    }

    /**
     * Test for HasPermission Method.
     */
    @Test
    public void ifPermissionMissingThenFalse() {
        this.identity.setPermissions(new LinkedHashSet<Permission>());

        assertFalse(this.identity.hasPermission(Permission.EDIT_SELF));
    }

    /**
     * Test for RestLogin().
     */
    @Test
    public void testRestLogin() {
        // given
        final String username = "admin@test.com";
        final String password = "abcdef1A@";
        final String expectedPassword = SecurityUtils.generatePasswordHash(password);

        final UserAccount userAccount = new UserAccount();
        userAccount.setPassword(expectedPassword);

        final Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        this.identity.setCredentials(credentials);

        when(this.mockAccountService.findByUserEmail(username)).thenReturn(userAccount);

        // when
        final boolean result = this.identity.restLogin();

        // then
        assertEquals(Identity.LOGIN_SUCCESS, result);
        assertTrue(this.identity.isLoggedIn());
        assertNotNull(this.identity.getPublicApiKey());
        assertNotNull(this.identity.getPrivateApiKey());
        assertNotNull(this.identity.getApiKeyExpiration());
    }

}

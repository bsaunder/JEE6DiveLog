/**
 * 
 */
package net.bryansaunders.jee6divelog.service;/*
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import net.bryansaunders.jee6divelog.dao.user.UserAccountDao;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserAccountServiceTest {

    /**
     * Mock User DAO.
     */
    @Mock
    private UserAccountDao mockUserAccountDao;

    /**
     * User Service.
     */
    @InjectMocks
    private UserAccountService userService;

    /**
     * Test Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for createUser().
     */
    @Test
    public void testCreateUser() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");

        when(this.mockUserAccountDao.save(any(UserAccount.class))).thenReturn(validUser);

        // when
        final UserAccount savedUser = this.userService.createUser(validUser);

        // then
        assertNotNull(savedUser);

        final Set<Role> roles = savedUser.getRoles();
        assertNotNull(roles);
        assertTrue(roles.contains(Role.USER));

        assertNotNull(savedUser.getPermissions());
        assertEquals(savedUser, validUser);
    }

    /**
     * Test for findByUsername().
     */
    @Test
    public void ifFoundByUsernameThenRetrieve() {
        // given
        final String email = "test@test.com";

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail(email);
        validUser.setPassword("pass123");

        final List<UserAccount> userAccountList = new LinkedList<UserAccount>();
        userAccountList.add(validUser);

        when(this.mockUserAccountDao.findByExample(any(UserAccount.class))).thenReturn(userAccountList);

        // when
        final UserAccount retrievedUser = this.userService.findByUserEmail(email);

        // then
        assertNotNull(retrievedUser);
        assertEquals(validUser, retrievedUser);
        assertEquals(email, retrievedUser.getEmail());
    }

    /**
     * Test for findByUsername().
     */
    @Test
    public void ifNotFoundByUsernameThenError() {
        // given
        when(this.mockUserAccountDao.findByExample(any(UserAccount.class))).thenThrow(new NoResultException());

        // when
        final UserAccount foundUser = this.userService.findByUserEmail("RandomUser");

        // then
        assertNull(foundUser);
    }

    /**
     * Test for Save User.
     */
    @Test
    public void testSaveUser() {
        // given
        final UserAccount user = new UserAccount();

        when(this.mockUserAccountDao.save(any(UserAccount.class))).thenReturn(user);

        // when
        this.userService.saveUser(user);

        // then
        verify(this.mockUserAccountDao).save(user);
    }

    /**
     * Test for Clear API Key.
     */
    @Test
    public void testClearApiKey() {
        // given
        final String email = "test@test.com";

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail(email);
        validUser.setPassword("pass123");
        validUser.setPublicApiKey("API_KEY");
        validUser.setPrivateApiKey("API_KEY");
        validUser.setApiKeyExpiration(new Date());

        final List<UserAccount> userAccountList = new LinkedList<UserAccount>();
        userAccountList.add(validUser);

        when(this.mockUserAccountDao.findByExample(any(UserAccount.class))).thenReturn(userAccountList);

        // when
        final boolean result = this.userService.clearApiKey(email);

        // then
        assertTrue(result);
        assertNull(validUser.getPublicApiKey());
        assertNull(validUser.getPrivateApiKey());

        final Date expirationDate = validUser.getApiKeyExpiration();
        assertTrue(expirationDate.getTime() < System.currentTimeMillis());
    }

    /**
     * Test for Find By Example.
     */
    @Test
    public void ifExampleMatchesThenGetMatches() {
        // given
        final UserAccount user = new UserAccount();
        final List<UserAccount> userList = new LinkedList<UserAccount>();
        userList.add(user);

        when(this.mockUserAccountDao.findByExample(any(UserAccount.class))).thenReturn(userList);

        // when
        this.userService.findByExample(user);

        // then
        verify(this.mockUserAccountDao).findByExample(user);
    }

    /**
     * Test for Find By Example.
     */
    @Test
    public void ifExampleDoesntMatchThenEmptyList() {
        // given
        final UserAccount user = new UserAccount();

        when(this.mockUserAccountDao.findByExample(any(UserAccount.class))).thenReturn(new LinkedList<UserAccount>());

        // when
        this.userService.findByExample(user);

        // then
        verify(this.mockUserAccountDao).findByExample(user);
    }
    
    /**
     * Test for GetAll.
     */
    @Test
    public void ifUsersThenGetAll() {
        // given
        final UserAccount user = new UserAccount();
        final List<UserAccount> userList = new LinkedList<UserAccount>();
        userList.add(user);

        when(this.mockUserAccountDao.getAll()).thenReturn(userList);

        // when
        final List<UserAccount> resultList = this.userService.getAll();

        // then
        assertNotNull(resultList);
        assertEquals(userList, resultList);
        verify(this.mockUserAccountDao).getAll();
    }
    
    /**
     * Test for GetAll.
     */
    @Test
    public void ifNoUsersThenGetEmptyList() {
        // given
        when(this.mockUserAccountDao.getAll()).thenReturn(new LinkedList<UserAccount>());

        // when
        final List<UserAccount> resultList = this.userService.getAll();

        // then
        assertNotNull(resultList);
        assertTrue(resultList.isEmpty());
        verify(this.mockUserAccountDao).getAll();
    }

}

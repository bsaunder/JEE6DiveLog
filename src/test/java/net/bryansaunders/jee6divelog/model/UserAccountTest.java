/**
 * 
 */
package net.bryansaunders.jee6divelog.model;

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

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserAccountTest {

    /**
     * Internal User.
     */
    private UserAccount user;

    /**
     * Sets up the user for each test.
     */
    @Before
    public void setUpTest() {
        this.user = new UserAccount();
    }

    /**
     * Test method for First Name.
     */
    @Test
    public void testGetFirstName() {
        final String name = "Bryan";
        this.user.setFirstName(name);
        assertEquals(name, this.user.getFirstName());
    }

    /**
     * Test method for Last Name.
     */
    @Test
    public void testGetLastName() {
        final String name = "Saunders";
        this.user.setLastName(name);
        assertEquals(name, this.user.getLastName());
    }

    /**
     * Test method for Country.
     */
    @Test
    public void testGetCountry() {
        final String value = "US";
        this.user.setCountry(value);
        assertEquals(value, this.user.getCountry());
    }

    /**
     * Test method for State.
     */
    @Test
    public void testGetState() {
        final String value = "SC";
        this.user.setState(value);
        assertEquals(value, this.user.getState());
    }

    /**
     * Test method for City.
     */
    @Test
    public void testGetCity() {
        final String value = "Charleston";
        this.user.setCity(value);
        assertEquals(value, this.user.getCity());
    }

    /**
     * Test method for Email.
     */
    @Test
    public void testGetEmail() {
        final String value = "btsaunde@gmail.com";
        this.user.setEmail(value);
        assertEquals(value, this.user.getEmail());
    }

    /**
     * Test method for Password.
     */
    @Test
    public void testGetPassword() {
        final String value = "abc123";
        this.user.setPassword(value);
        assertEquals(value, this.user.getPassword());
    }

    /**
     * Test method for API Keys.
     */
    @Test
    public void testGetApiKeys() {
        final String publicKey = "12346";
        final String privateKey = "3456367";

        this.user.setPublicApiKey(publicKey);
        this.user.setPrivateApiKey(privateKey);
        
        assertEquals(publicKey, this.user.getPublicApiKey());
        assertEquals(privateKey, this.user.getPrivateApiKey());
    }

    /**
     * Test method for API Key Expiration.
     */
    @Test
    public void testGetApiExpirationDate() {
        final Date date = new Date();

        this.user.setApiKeyExpiration(date);
        Date setDate = this.user.getApiKeyExpiration();

        assertNotNull(setDate);
        assertEquals(date.getTime(), setDate.getTime());

        this.user.setApiKeyExpiration(null);
        setDate = this.user.getApiKeyExpiration();

        assertNull(setDate);
    }

}

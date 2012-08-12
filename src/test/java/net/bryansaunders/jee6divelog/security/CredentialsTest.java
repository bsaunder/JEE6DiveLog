/**
 * 
 */
package net.bryansaunders.jee6divelog.security;/*
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


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class CredentialsTest {

    /**
     * Test method for Username.
     */
    @Test
    public void testUsername() {
        final Credentials credentials = new Credentials();
        assertNotNull(credentials.getUsername());
        assertEquals("", credentials.getUsername());
        
        final String username = "Username";
        credentials.setUsername(username);
        assertEquals(username, credentials.getUsername());
    }

    /**
     * Test method for Password.
     */
    @Test
    public void testPassword() {
        final Credentials credentials = new Credentials();
        assertNotNull(credentials.getPassword());
        assertEquals("", credentials.getPassword());
        
        final String password = "Password";
        credentials.setPassword(password);
        assertEquals(password, credentials.getPassword());
    }

    /**
     * Test method for toString().
     */
    @Test
    public void testToString() {
        final Credentials credentials = new Credentials();
        credentials.setPassword("Pass");
        credentials.setUsername("User");
        
        final String expected = "Credentials[username: User, password: Pass]";
        assertEquals(expected, credentials.toString());
    }

}

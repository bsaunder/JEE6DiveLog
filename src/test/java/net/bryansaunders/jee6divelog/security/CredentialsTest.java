/**
 * 
 */
package net.bryansaunders.jee6divelog.security;

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

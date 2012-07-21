/**
 * 
 */
package net.bryansaunders.jee6divelog.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserTest {

    /**
     * Internal User.
     */
    private User user;

    /**
     * Sets up the user for each test.
     */
    @Before
    public void setUpTest() {
        this.user = new User();
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
     * Test method for Salt.
     */
    @Test
    public void testGetSalt() {
        final String value = "salt456";
        this.user.setSalt(value);
        assertEquals(value, this.user.getSalt());
    }

}

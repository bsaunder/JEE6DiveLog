/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import net.bryansaunders.jee6divelog.DefaultDeployment;
import net.bryansaunders.jee6divelog.model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class UserServiceIT {
    
    /**
     * User Service.
     */
    @Inject
    private UserService userService;
    
    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DefaultDeployment.getDefaultDeployment();
    }
    
    /**
     * Tests the Producer was properly injected.
     */
    @Test
    public void testInjection() {
        assertNotNull(this.userService);
    }

    /**
     * Test method for createUser().
     */
    @Test
    public void testCreateUser() {
        // given
        final User validUser = new User();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        // when
        User savedUser = this.userService.createUser(validUser);
        
        // then
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals(savedUser, validUser);
    }
    
    /**
     * Test for findByUsername().
     */
    @Test
    public void ifFoundByUsernameThenRetrieve() {
        // given
        final String email = "test@test.com";
        final User validUser = new User();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail(email);
        validUser.setPassword("pass123");
        this.userService.createUser(validUser);

        // when
        final User retrievedUser = this.userService.findByUsername(email);

        // then
        assertNotNull(retrievedUser);
        assertEquals(email, retrievedUser.getEmail());
        assertEquals(validUser, retrievedUser);
    }

    /**
     * Test for findByUsername().
     */
    @Test(expected = NoResultException.class)
    public void ifNotFoundByUsernameThenError() {
        // given

        // when
        this.userService.findByUsername("RandomUser");

        // then
        fail("Should have thrown EntityNotFoundException");
    }

}

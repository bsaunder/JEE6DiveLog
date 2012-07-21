/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.bryansaunders.jee6divelog.model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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
        return ShrinkWrap.create(WebArchive.class, "jee6divelog_test.war")
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
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

}

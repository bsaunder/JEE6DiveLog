/**
 * 
 */
package net.bryansaunders.jee6divelog.security.enumerator;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

/**
 * Tests for Role Enum.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class RoleTest {

    /**
     * Test method for getPermissions().
     */
    @Test
    public void testGetPermissions() {
        final Set<Permission> permissions = Role.WEB_USER.getPermissions();

        assertTrue(permissions.contains(Permission.REST_ACCESS));
        assertTrue(permissions.contains(Permission.SOAP_ACCESS));
    }

}

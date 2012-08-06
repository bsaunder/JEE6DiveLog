package net.bryansaunders.jee6divelog.security.enumerator;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

/**
 * Tests Permission Enum.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PermissionTest {

    /**
     * Test for Default Permissions.
     */
    @Test
    public void testGetUserDefaults() {
        final Set<Permission> defaultPermissions = Permission.getDefaults(Role.USER);

        assertTrue(defaultPermissions.contains(Permission.EDIT_SELF));
        assertTrue(defaultPermissions.contains(Permission.DELETE_SELF));
    }
    
    /**
     * Test for Default Permissions.
     */
    @Test
    public void testGetAdminDefaults() {
        final Set<Permission> defaultPermissions = Permission.getDefaults(Role.ADMIN);

        assertTrue(defaultPermissions.contains(Permission.EDIT_SELF));
    }

}

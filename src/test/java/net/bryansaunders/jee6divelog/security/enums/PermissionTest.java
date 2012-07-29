package net.bryansaunders.jee6divelog.security.enums;

import static org.junit.Assert.assertTrue;

import java.util.List;

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
    public void testGetDefaults() {
        final List<Permission> defaultPermissions = Permission.getDefaults(Role.USER);

        assertTrue(defaultPermissions.contains(Permission.EDIT_SELF));
        assertTrue(defaultPermissions.contains(Permission.DELETE_SELF));
    }

}

/**
 * 
 */
package net.bryansaunders.jee6divelog.security.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Account Permissions.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public enum Permission {

    /**
     * Edit Self.
     */
    EDIT_SELF(new HashMap<Role, Boolean>() {
        {
            put(Role.USER, true);
            put(Role.ADMIN, true);
        }
    }),

    /**
     * Delete Self.
     */
    DELETE_SELF(new HashMap<Role, Boolean>() {
        {
            put(Role.USER, true);
            put(Role.ADMIN, false);
        }
    });

    /**
     * Default Permission Flag Map.
     */
    private Map<Role, Boolean> defaultPermission;

    /**
     * Default Constructor.
     * 
     * @param defaultPermissions
     *            Map of Default Permissions
     */
    private Permission(final Map<Role, Boolean> defaultPermissions) {
        this.defaultPermission = defaultPermissions;
    }

    /**
     * Get the defaultPermission.
     * 
     * @return the defaultPermission
     */
    public Map<Role, Boolean> getDefaultPermission() {
        return this.defaultPermission;
    }

    /**
     * Get Default Permissions for the Given Role.
     * 
     * @param role
     *            Role to check Permissions for
     * @return List of Permission Objects
     */
    public static List<Permission> getDefaults(final Role role) {
        final List<Permission> defaultPermissions = new LinkedList<Permission>();

        for (final Permission permission : Permission.values()) {
            final Map<Role, Boolean> permissionMap = permission.getDefaultPermission();
            final Boolean value = permissionMap.get(role);
            if (value != null && value) {
                defaultPermissions.add(permission);
            }
        }

        return defaultPermissions;
    }

}

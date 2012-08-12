/**
 * 
 */
package net.bryansaunders.jee6divelog.security.enumerator;/*
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


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Account Permissions.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@SuppressWarnings("serial")
public enum Permission {

    /**
     * Edit Self.
     */
    
    EDIT_SELF(new HashMap<Role, Boolean>() {
        {
            this.put(Role.USER, true);
            this.put(Role.ADMIN, true);
        }
    }),

    /**
     * Delete Self.
     */
    DELETE_SELF(new HashMap<Role, Boolean>() {
        {
            this.put(Role.USER, true);
            this.put(Role.ADMIN, false);
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
    public static Set<Permission> getDefaults(final Role role) {
        final Set<Permission> defaultPermissions = new LinkedHashSet<Permission>();

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

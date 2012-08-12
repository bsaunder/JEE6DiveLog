package net.bryansaunders.jee6divelog.security.interceptor;/*
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


import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;

import org.junit.Ignore;

/**
 * Class used to get Method Objects for Interceptor Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
@HasPermission(permission = Permission.EDIT_SELF)
@HasPermissions(permissions = { Permission.EDIT_SELF, Permission.DELETE_SELF })
public class PermissionInterceptorSandbox {

    /**
     * Method with HasPermission Annotation.
     */
    @HasPermission(permission = Permission.EDIT_SELF)
    public void hasPermissionEditSelfMethod() {

    }

    /**
     * Method with HasPermissions Annotation.
     */
    @HasPermissions(permissions = { Permission.EDIT_SELF, Permission.DELETE_SELF })
    public void hasPermissionsEditSelfDeleteSelfMethod() {

    }

    /**
     * Method with no Annotation.
     */
    public void blankMethod() {

    }
}

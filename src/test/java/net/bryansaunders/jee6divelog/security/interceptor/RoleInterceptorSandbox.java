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


import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.annotation.HasRoles;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.junit.Ignore;

/**
 * Class used to get Method Objects for Interceptor Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
@HasRole(role = Role.USER)
@HasRoles(roles = {Role.USER, Role.ADMIN})
public class RoleInterceptorSandbox {

    /**
     * Method with HasRole Annotation.
     */
    @HasRole(role = Role.USER)
    public void hasRoleUserMethod() {

    }
    
    /**
     * Method with HasRoles Annotation.
     */
    @HasRoles(roles = {Role.USER, Role.ADMIN})
    public void hasRolesUserAdminMethod() {

    }

    /**
     * Method with no Annotation.
     */
    public void blankMethod() {

    }
}

/**
 * 
 */
package net.bryansaunders.jee6divelog.security.enumerator;

/*
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
    EDIT_SELF,

    /**
     * Delete Self.
     */
    DELETE_SELF,
    
    /**
     * Is a User.
     */
    IS_USER,

    /**
     * Has REST API Access.
     */
    REST_ACCESS,

    /**
     * Can View Users.
     */
    VIEW_USERS,

    /**
     * Has SOAP API Access.
     */
    SOAP_ACCESS,

    /**
     * Is an Admin
     */
    IS_ADMIN;

}

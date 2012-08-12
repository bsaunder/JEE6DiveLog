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


import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.bryansaunders.jee6divelog.security.AuthorizationFailedException;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;

/**
 * HasRole Interceptor.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@HasPermissions(permissions = { })
@Interceptor
public class HasPermissionsInterceptor {

    /**
     * Active Identity.
     */
    @Inject
    private Identity identity;

    /**
     * Intercepts the Method Call and Throws an Exception if Access is Not Allowed.
     * 
     * @param ctx
     *            Invocation Context
     * @return Result
     * @throws Exception
     *             Thrown if Access is Denied
     */
    @AroundInvoke
    public Object checkAuthenticaion(final InvocationContext ctx) throws Exception {
        final Method method = ctx.getMethod();
        Permission[] permissions = null;

        // Check For Method Level Annotations
        HasPermissions hasPermissionsAnnotation = method.getAnnotation(HasPermissions.class);
        if (hasPermissionsAnnotation != null) {
            permissions = hasPermissionsAnnotation.permissions();
        }

        // Check For Class Level Annotations
        final Class<?> callingClass = method.getDeclaringClass();
        if (callingClass != null) {
            hasPermissionsAnnotation = callingClass.getAnnotation(HasPermissions.class);
            if (hasPermissionsAnnotation != null) {
                permissions = hasPermissionsAnnotation.permissions();
            }
        }

        // Check Identity
        if (permissions == null) {
            throw new AuthorizationFailedException("Could not Validate Identity, Could not Retrieve Permission.");
        } else {
            for (Permission permission : permissions) {
                if (!this.identity.hasPermission(permission)) {
                    throw new AuthorizationFailedException("Access Denied, Permission " + permission + " Not Found");
                }
            }
        }

        return ctx.proceed();
    }
}

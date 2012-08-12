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
import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

/**
 * HasRole Interceptor.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@HasRole(role = Role.USER)
@Interceptor
public class HasRoleInterceptor {

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
        Role role = null;

        // Check For Method Level Annotations
        HasRole hasRoleAnnotation = method.getAnnotation(HasRole.class);
        if (hasRoleAnnotation != null) {
            role = hasRoleAnnotation.role();
        }

        // Check For Class Level Annotations
        final Class<?> callingClass = method.getDeclaringClass();
        if (callingClass != null) {
            hasRoleAnnotation = callingClass.getAnnotation(HasRole.class);
            if (hasRoleAnnotation != null) {
                role = hasRoleAnnotation.role();
            }
        }

        // Check Identity
        if (role == null) {
            throw new AuthorizationFailedException("Could not Validate Identity, Could not Retrieve Role.");
        } else {
            if (!this.identity.hasRole(role)) {
                throw new AuthorizationFailedException("Access Denied, Role " + role + " Not Found");
            }
        }

        return ctx.proceed();
    }
}

package net.bryansaunders.jee6divelog.security.interceptor;

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
@HasRole
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

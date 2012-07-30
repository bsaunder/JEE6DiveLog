package net.bryansaunders.jee6divelog.security.interceptor;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.bryansaunders.jee6divelog.security.AuthorizationFailedException;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;

/**
 * HasRole Interceptor.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@HasPermission
@Interceptor
public class HasPermissionInterceptor {

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
        Permission permission = null;

        // Check For Method Level Annotations
        HasPermission hasPermissionAnnotation = method.getAnnotation(HasPermission.class);
        if (hasPermissionAnnotation != null) {
            permission = hasPermissionAnnotation.permission();
        }

        // Check For Class Level Annotations
        final Class<?> callingClass = method.getDeclaringClass();
        if (callingClass != null) {
            hasPermissionAnnotation = callingClass.getAnnotation(HasPermission.class);
            if (hasPermissionAnnotation != null) {
                permission = hasPermissionAnnotation.permission();
            }
        }

        // Check Identity
        if (permission == null) {
            throw new AuthorizationFailedException("Could not Validate Identity, Could not Retrieve Permission.");
        } else {
            if (!this.identity.hasPermission(permission)) {
                throw new AuthorizationFailedException("Access Denied, Permission " + permission + " Not Found");
            }
        }

        return ctx.proceed();
    }
}

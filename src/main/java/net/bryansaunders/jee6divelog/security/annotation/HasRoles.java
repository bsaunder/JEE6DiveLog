package net.bryansaunders.jee6divelog.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import net.bryansaunders.jee6divelog.security.enumerator.Role;

/**
 * Makes sure the Active User as the Specified Role.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@InterceptorBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRoles {

    /**
     * Role to Enforce.
     * 
     * @return Role to Enforce.
     */
    @Nonbinding
    Role[] roles() default { };
}

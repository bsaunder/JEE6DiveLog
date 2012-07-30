package net.bryansaunders.jee6divelog.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

import net.bryansaunders.jee6divelog.security.enumerator.Permission;

/**
 * Makes sure the Active User as the Specified Role.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@InterceptorBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermission {

    /**
     * Permission to Enforce.
     * 
     * @return Permission to Enforce.
     */
    Permission permission() default Permission.EDIT_SELF;
}

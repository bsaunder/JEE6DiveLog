package net.bryansaunders.jee6divelog.security.interceptor;

import net.bryansaunders.jee6divelog.security.annotation.HasRole;
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
public class HasRoleInterceptorSandbox {

    /**
     * Method with HasRole Annotation.
     */
    @HasRole(role = Role.USER)
    public void hasRoleUserMethod() {

    }

    /**
     * Method with no Annotation.
     */
    public void blankMethod() {

    }
}

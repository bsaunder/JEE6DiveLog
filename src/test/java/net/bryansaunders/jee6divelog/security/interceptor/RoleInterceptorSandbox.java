package net.bryansaunders.jee6divelog.security.interceptor;

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

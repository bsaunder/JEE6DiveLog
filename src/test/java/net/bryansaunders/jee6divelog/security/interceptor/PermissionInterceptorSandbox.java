package net.bryansaunders.jee6divelog.security.interceptor;

import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;

import org.junit.Ignore;

/**
 * Class used to get Method Objects for Interceptor Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
@HasPermission(permission = Permission.EDIT_SELF)
@HasPermissions(permissions = { Permission.EDIT_SELF, Permission.DELETE_SELF })
public class PermissionInterceptorSandbox {

    /**
     * Method with HasPermission Annotation.
     */
    @HasPermission(permission = Permission.EDIT_SELF)
    public void hasPermissionEditSelfMethod() {

    }

    /**
     * Method with HasPermissions Annotation.
     */
    @HasPermissions(permissions = { Permission.EDIT_SELF, Permission.DELETE_SELF })
    public void hasPermissionsEditSelfDeleteSelfMethod() {

    }

    /**
     * Method with no Annotation.
     */
    public void blankMethod() {

    }
}

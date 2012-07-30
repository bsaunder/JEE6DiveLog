/**
 * 
 */
package net.bryansaunders.jee6divelog.security.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import javax.interceptor.InvocationContext;

import net.bryansaunders.jee6divelog.security.AuthorizationFailedException;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Method.class)
public class HasPermissionsInterceptorTest {

    /**
     * Mock Identity.
     */
    @Mock
    private Identity mockIdentity;

    /**
     * Interceptor with Injected Mocks.
     */
    @InjectMocks
    private HasPermissionsInterceptor interceptor;

    /**
     * Test Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for checkAuthenticaion.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifMethodHasPermissionsThenProceed() throws Exception {
        // given
        when(this.mockIdentity.hasPermission(Permission.EDIT_SELF)).thenReturn(true);
        when(this.mockIdentity.hasPermission(Permission.DELETE_SELF)).thenReturn(true);

        final Method method = PermissionInterceptorSandbox.class.getMethod("hasPermissionsEditSelfDeleteSelfMethod");

        final InvocationContext ic = mock(InvocationContext.class);
        final Object expectedObject = new Object();
        when(ic.proceed()).thenReturn(expectedObject);
        when(ic.getMethod()).thenReturn(method);

        // when
        final Object transactionResult = this.interceptor.checkAuthenticaion(ic);

        // then
        assertEquals(expectedObject, transactionResult);

    }

    /**
     * Test method for checkAuthenticaion.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    public void ifClassHasPermissionsThenProceed() throws Exception {
        // given
        when(this.mockIdentity.hasPermission(Permission.EDIT_SELF)).thenReturn(true);
        when(this.mockIdentity.hasPermission(Permission.DELETE_SELF)).thenReturn(true);

        final Method method = PermissionInterceptorSandbox.class.getMethod("blankMethod");

        final InvocationContext ic = mock(InvocationContext.class);
        final Object expectedObject = new Object();
        when(ic.proceed()).thenReturn(expectedObject);
        when(ic.getMethod()).thenReturn(method);

        // when
        final Object transactionResult = this.interceptor.checkAuthenticaion(ic);

        // then
        assertEquals(expectedObject, transactionResult);
    }

    /**
     * Test method for checkAuthenticaion.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test(expected = AuthorizationFailedException.class)
    public void ifNoAnnotationThenProceed() throws Exception {
        // given
        final Method method = NoInterceptorSandbox.class.getMethod("blankMethod");

        final InvocationContext ic = mock(InvocationContext.class);
        when(ic.getMethod()).thenReturn(method);

        // when
        this.interceptor.checkAuthenticaion(ic);

        // then
        fail("AuthorizationFailedException Expected.");
    }

    /**
     * Test method for checkAuthenticaion.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test(expected = AuthorizationFailedException.class)
    public void ifPermissionsMissingThenException() throws Exception {
        // given
        when(this.mockIdentity.hasPermission(Permission.EDIT_SELF)).thenReturn(false);
        when(this.mockIdentity.hasPermission(Permission.DELETE_SELF)).thenReturn(false);

        final Method method = PermissionInterceptorSandbox.class.getMethod("hasPermissionsEditSelfDeleteSelfMethod");

        final InvocationContext ic = mock(InvocationContext.class);
        when(ic.getMethod()).thenReturn(method);

        // when
        this.interceptor.checkAuthenticaion(ic);

        // then
        fail("AuthorizationFailedException Expected.");
    }

}

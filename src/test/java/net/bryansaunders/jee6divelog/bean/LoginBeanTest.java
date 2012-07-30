/**
 * 
 */
package net.bryansaunders.jee6divelog.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.faces.context.FacesContext;

import net.bryansaunders.jee6divelog.bean.LoginBean;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class LoginBeanTest {

    /**
     * Login Bean.
     */
    private LoginBean loginBean;

    /**
     * Test Setup.
     */
    @Before
    public void setupTest() {
        this.loginBean = new LoginBean();
    }

    /**
     * Tests Valid Login.
     */
    @Test
    public void ifLoginValidThenSuccess() {
        // given
        final String email = "joe@test.com";
        final String pass = "joe123";
        final boolean rememberMe = true;
        
        this.loginBean.setEmail(email);
        this.loginBean.setPassword(pass);
        this.loginBean.setRememberMe(rememberMe);
        
        final Identity identity = mock(Identity.class);
        final Credentials credentials = new Credentials();
        this.loginBean.setIdentity(identity);
        this.loginBean.setCredentials(credentials);
        
        // when
        doCallRealMethod().when(identity).setRememberMe(anyBoolean());
        doCallRealMethod().when(identity).isRememberMe();
        doReturn(Identity.LOGIN_SUCCESS).when(identity).login();
        final String result = this.loginBean.login();
        
        // then
        assertEquals(LoginBean.SUCCESS, result);
        assertEquals(email, credentials.getUsername());
        assertEquals(pass, credentials.getPassword());
        assertEquals(rememberMe, identity.isRememberMe());
    }

    /**
     * Tests Invalid Login.
     */
    @Test
    public void ifLoginInvalidThenFailure() {
        // given
        final String email = "joe@test.com";
        final String pass = "joe123";
        final boolean rememberMe = true;
        
        this.loginBean.setEmail(email);
        this.loginBean.setPassword(pass);
        this.loginBean.setRememberMe(rememberMe);
        
        final Identity identity = mock(Identity.class);
        final Credentials credentials = new Credentials();
        this.loginBean.setIdentity(identity);
        this.loginBean.setCredentials(credentials);
        
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(mock(FacesContext.class));
        
        // when
        doCallRealMethod().when(identity).setRememberMe(anyBoolean());
        doCallRealMethod().when(identity).isRememberMe();
        doReturn(Identity.LOGIN_FAILURE).when(identity).login();
        final String result = this.loginBean.login();
        
        // then
        assertEquals(LoginBean.FAILURE, result);
        assertEquals(email, credentials.getUsername());
        assertEquals(pass, credentials.getPassword());
        assertEquals(rememberMe, identity.isRememberMe());
    }

    /**
     * Test method for LoginError.
     */
    @Test
    public void testSetLoginError() {
        this.loginBean.setLoginError(false);
        assertFalse(this.loginBean.isLoginError());
        
        this.loginBean.setLoginError(true);
        assertTrue(this.loginBean.isLoginError());
    }

    /**
     * Test method for RememberMe.
     */
    @Test
    public void testSetRememberMe() {
        this.loginBean.setRememberMe(false);
        assertFalse(this.loginBean.isRememberMe());
        
        this.loginBean.setRememberMe(true);
        assertTrue(this.loginBean.isRememberMe());
    }

    /**
     * Test method for Email.
     */
    @Test
    public void testSetEmail() {
        final String email = "admin@test.com";
        
        this.loginBean.setEmail(email);
        assertEquals(email, this.loginBean.getEmail());
    }

    /**
     * Test method for Password.
     */
    @Test
    public void testSetPassword() {
        final String password = "pass123";
        
        this.loginBean.setPassword(password);
        assertEquals(password, this.loginBean.getPassword());
    }

}

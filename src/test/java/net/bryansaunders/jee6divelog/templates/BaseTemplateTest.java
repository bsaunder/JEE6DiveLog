/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import static org.jboss.arquillian.ajocado.Graphene.id;
import static org.jboss.arquillian.ajocado.Graphene.waitForHttp;

import java.net.URL;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.locator.IdLocator;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;

/**
 * Base Class for Template Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public class BaseTemplateTest {

    /**
     * Browser Driver.
     */
    @Drone
    protected GrapheneSelenium browser;

    /**
     * Application Path.
     */
    @ArquillianResource
    protected URL applicationPath;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return DeploymentFactory.getTemplateDeployment();
    }

    /**
     * Opens the page at the specified path.
     * 
     * @param pageName
     *            Page to open
     */
    public void openPage(final String pageName) {
        final URL pageUrl = URLUtils.buildUrl(this.applicationPath, pageName);
        this.browser.open(pageUrl);
    }

    /**
     * Logs in the given user. Assumes you are one a page
     * 
     * @param userName
     *            Username
     * @param password
     *            Password
     */
    public void doLogin(final String userName, final String password) {
        this.openPage("login.xhtml");
        
        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");
        
        this.browser.type(userNameField, userName);
        this.browser.type(passwordField, password);
        waitForHttp(this.browser).click(loginButton);
    }
}

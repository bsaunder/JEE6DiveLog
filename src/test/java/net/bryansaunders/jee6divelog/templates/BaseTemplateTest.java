/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import static org.jboss.arquillian.ajocado.Graphene.id;

import java.net.URL;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.ajocado.Graphene;
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
        
        String url = this.browser.getLocation().toString();
        String source = this.browser.getHtmlSource();
        System.out.println("==1==1==1==========");
        System.out.println("URL: " + url);
        System.out.println(source);
        System.out.println("===================");
        
        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginForm = id("loginform");
        
        this.browser.type(userNameField, userName);
        this.browser.type(passwordField, password);
        this.browser.submit(loginForm);
        this.browser.waitForPageToLoad();
        
        url = this.browser.getLocation().toString();
        source = this.browser.getHtmlSource();
        System.out.println("==2==2==2==========");
        System.out.println("URL: " + url);
        System.out.println(source);
        System.out.println("===================");
    }
}

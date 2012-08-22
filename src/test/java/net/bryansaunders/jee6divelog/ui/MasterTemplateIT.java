/**
 * 
 */
package net.bryansaunders.jee6divelog.ui;

import static org.jboss.arquillian.ajocado.Graphene.id;
import static org.jboss.arquillian.ajocado.Graphene.jq;
import static org.jboss.arquillian.ajocado.Graphene.waitForHttp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.locator.IdLocator;
import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for JSF Master Template.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
@UsingDataSet("OneUserAccount-Admin.yml")
public class MasterTemplateIT {

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return DeploymentFactory.getTemplateDeployment();
    }

    /**
     * Empty In-Container Test to Setup the Database.
     */
    @Test
    @InSequence(1)
    public void setupDatabase() {

    }

    /**
     * Test navigation tabs are correct when logged in.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @InSequence(2)
    @RunAsClient
    public void ifNavigationTabsCorrectWhenLoggedInThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "login.xhtml"));

        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");

        browser.type(userNameField, "bryan@test.com");
        browser.type(passwordField, "abcdef1A@");
        waitForHttp(browser).click(loginButton);

        final IdLocator publicTab = id("publicTab");
        final IdLocator privateTab = id("privateTab");

        assertTrue(browser.isElementPresent(publicTab));
        assertTrue(browser.isElementPresent(privateTab));
    }

    /**
     * Test account options are correct when logged in.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @InSequence(3)
    @RunAsClient
    public void ifAccountOptionsCorrectWhenLoggedInThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "login.xhtml"));

        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");

        browser.type(userNameField, "bryan@test.com");
        browser.type(passwordField, "abcdef1A@");
        waitForHttp(browser).click(loginButton);

        final IdLocator register = id("accountOption_register");
        final IdLocator login = id("accountOption_login");
        final IdLocator logout = id("logout:accountOption_logout");
        final IdLocator account = id("accountOption_account");

        assertFalse(browser.isElementPresent(register));
        assertFalse(browser.isElementPresent(login));
        assertTrue(browser.isElementPresent(account));
        assertTrue(browser.isElementPresent(logout));
    }

    /**
     * Test to check the title.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @Ignore
    @RunAsClient
    public void ifTitleCorrectThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "index.html"));

        final String title = browser.getTitle();
        assertTrue(title.startsWith("BSNet DiveLog - "));
    }

    /**
     * Test navigation tabs are correct.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @Ignore
    @RunAsClient
    public void ifNavigationTabsCorrectWhenNotLoggedInThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "index.html"));

        final IdLocator publicTab = id("publicTab");
        final IdLocator privateTab = id("privateTab");

        assertTrue(browser.isElementPresent(publicTab));
        assertFalse(browser.isElementPresent(privateTab));
    }

    /**
     * Test account options are correct.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @Ignore
    @RunAsClient
    public void ifAccountOptionsCorrectWhenNotLoggedInThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "index.html"));

        final IdLocator register = id("accountOption_register");
        final IdLocator login = id("accountOption_login");
        final IdLocator logout = id("accountOption_logout");
        final IdLocator account = id("accountOption_account");

        assertTrue(browser.isElementPresent(register));
        assertTrue(browser.isElementPresent(login));
        assertFalse(browser.isElementPresent(logout));
        assertFalse(browser.isElementPresent(account));
    }

    /**
     * Test to check the footer.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @Ignore
    @RunAsClient
    public void ifFooterCorrectThenPass(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "index.html"));

        // get footer element
        final JQueryLocator footer = jq("footer");
        assertTrue(browser.isElementPresent(footer));

        // get paragraph child element with class center
        final JQueryLocator copyText = footer.getChild(jq("p[class=center]"));
        assertTrue(browser.getText(copyText).contains("© Bryan Saunders 2012. All Rights Reserved."));
    }

}

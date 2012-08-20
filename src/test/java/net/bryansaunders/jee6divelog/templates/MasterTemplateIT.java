/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import static org.jboss.arquillian.ajocado.Graphene.jq;
import static org.jboss.arquillian.ajocado.Graphene.id;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.ajocado.locator.IdLocator;
import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for JSF Master Template.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class MasterTemplateIT extends BaseTemplateTest {

    /**
     * Test Setup.
     */
    @Before
    public void setUp() {
        this.openPage("index.html");
    }

    /**
     * Test to check the title.
     */
    @Test
    public void ifTitleCorrectThenPass() {
        final String title = this.browser.getTitle();
        assertTrue(title.startsWith("BSNet DiveLog - "));
    }

    /**
     * Test navigation tabs are correct.
     */
    @Test
    public void ifNavigationTabsCorrectWhenNotLoggedInThenPass() {
        final IdLocator publicTab = id("publicTab");
        final IdLocator privateTab = id("privateTab");

        assertTrue(this.browser.isElementPresent(publicTab));
        assertFalse(this.browser.isElementPresent(privateTab));
    }

    /**
     * Test account options are correct.
     */
    @Test
    public void ifAccountOptionsCorrectWhenNotLoggedInThenPass() {
        final IdLocator register = id("accountOption_register");
        final IdLocator login = id("accountOption_login");
        final IdLocator logout = id("accountOption_logout");
        final IdLocator account = id("accountOption_account");

        assertTrue(this.browser.isElementPresent(register));
        assertTrue(this.browser.isElementPresent(login));
        assertFalse(this.browser.isElementPresent(logout));
        assertFalse(this.browser.isElementPresent(account));
    }

    /**
     * Test navigation tabs are correct when logged in.
     */
    @Test
    @UsingDataSet("OneUserAccount-Admin.yml")
    public void ifNavigationTabsCorrectWhenLoggedInThenPass() {
        this.doLogin("bryan@test.com", "abcdef1A@");

        final IdLocator publicTab = id("publicTab");
        final IdLocator privateTab = id("privateTab");

        assertTrue(this.browser.isElementPresent(publicTab));
        assertTrue(this.browser.isElementPresent(privateTab));
    }

    /**
     * Test account options are correct when logged in.
     */
    @Test
    @UsingDataSet("OneUserAccount-Admin.yml")
    public void ifAccountOptionsCorrectWhenLoggedInThenPass() {
        this.doLogin("bryan@test.com", "abcdef1A@");

        final IdLocator register = id("accountOption_register");
        final IdLocator login = id("accountOption_login");
        final IdLocator logout = id("accountOption_logout");
        final IdLocator account = id("accountOption_account");

        assertFalse(this.browser.isElementPresent(register));
        assertFalse(this.browser.isElementPresent(login));
        assertTrue(this.browser.isElementPresent(logout));
        assertTrue(this.browser.isElementPresent(account));
    }

    /**
     * Test to check the footer.
     */
    @Test
    public void ifFooterCorrectThenPass() {
        // get footer element
        final JQueryLocator footer = jq("footer");
        assertTrue(this.browser.isElementPresent(footer));

        // get paragraph child element with class center
        final JQueryLocator copyText = footer.getChild(jq("p[class=center]"));
        assertTrue(this.browser.getText(copyText).contains("© Bryan Saunders 2012. All Rights Reserved."));
    }

}

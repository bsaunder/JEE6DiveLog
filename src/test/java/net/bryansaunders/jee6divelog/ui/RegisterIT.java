/**
 * 
 */
package net.bryansaunders.jee6divelog.ui;

import static org.jboss.arquillian.ajocado.Graphene.id;
import static org.jboss.arquillian.ajocado.Graphene.waitForHttp;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.ajocado.dom.Attribute;
import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.locator.IdLocator;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for Registration Page.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class RegisterIT {

    /**
     * First Name Form Field.
     */
    private IdLocator firstName = id("registration:firstName");

    /**
     * Last Name Form Field.
     */
    private IdLocator lastName = id("registration:lastName");

    /**
     * Country Form Field.
     */
    private IdLocator country = id("registration:country");

    /**
     * State Form Field.
     */
    private IdLocator state = id("registration:state");

    /**
     * City Form Field.
     */
    private IdLocator city = id("registration:city");

    /**
     * Email Form Field.
     */
    private IdLocator email = id("registration:email");

    /**
     * Password Form Field.
     */
    private IdLocator password = id("registration:password");

    /**
     * Password Confirmation Form Field.
     */
    private IdLocator confirmationPassword = id("registration:confirmationPassword");

    /**
     * Register Button.
     */
    private IdLocator registerButton = id("registration:register");

    /**
     * Register Button.
     */
    private IdLocator resetButton = id("registration:reset");

    /**
     * Browser Driver.
     */
    @Drone
    private GrapheneSelenium browser;

    /**
     * Application Path.
     */
    @ArquillianResource
    private URL applicationPath;

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
     * Test Setup.
     */
    @Before
    public void setUp() {
        this.browser.open(URLUtils.buildUrl(this.applicationPath, "register.xhtml"));
    }

    /**
     * Test that all fields are Emptied when the Reset button is clicked.
     */
    @Test
    public void ifFieldsPopulatedThenReset() {

        this.setAllFormFields();

        this.browser.click(this.resetButton);

        assertTrue(this.browser.getValue(this.firstName).isEmpty());
        assertTrue(this.browser.getValue(this.lastName).isEmpty());
        assertTrue(this.browser.getValue(this.country).isEmpty());
        assertTrue(this.browser.getValue(this.state).isEmpty());
        assertTrue(this.browser.getValue(this.city).isEmpty());
        assertTrue(this.browser.getValue(this.email).isEmpty());
        assertTrue(this.browser.getValue(this.password).isEmpty());
        assertTrue(this.browser.getValue(this.confirmationPassword).isEmpty());
    }

    /**
     * Test that Registration is Successful when All Fields are Valid.
     */
    @Test
    public void ifAllFieldsValidThenRegister() {
        this.setAllFormFields();

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Thank you for registering."));
    }

    /**
     * Test that Registration is Successful with only required fields.
     */
    @Test
    public void ifRequiredFieldsValidThenRegister() {
        this.setRequiredFormFields();

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Thank you for registering."));
    }

    /**
     * Test Missing Email Error message.
     */
    @Test
    public void ifEmailMissingThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.email, "");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Email is required."));
        assertTrue(this.browser.getAttribute(id("emailControl"), Attribute.CLASS).contains("error"));
    }

    @Ignore
    @Test
    public void ifEmailNotUniqueThenFail() {
        fail("Not Yet Implemented");
    }

    /**
     * Test Error Message is First Name is Missing.
     */
    @Test
    public void ifFirstNameMissingThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.firstName, "");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("First Name is required."));
        assertTrue(this.browser.getAttribute(id("firstNameControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Test Error Message is Last Name is Missing.
     */
    @Test
    public void ifLastNameMissingThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.lastName, "");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Last Name is required."));
        assertTrue(this.browser.getAttribute(id("lastNameControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Tests Error Message for Missing Password.
     */
    @Test
    public void ifPasswordMissingThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.password, "");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Password is required."));
        assertTrue(this.browser.getAttribute(id("passwordControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Tests Error Message for Missing Password Confimration.
     */
    @Test
    public void ifPasswordConfirmationMissingThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.confirmationPassword, "");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Password confirmation is required."));
        assertTrue(this.browser.getAttribute(id("passwordControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Tests Error Message for Invalid Password.
     */
    @Test
    public void isPasswordInvalidThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.password, "asd");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Password does not meet the minimum requirements."));
        assertTrue(this.browser.getAttribute(id("passwordControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Tests Error Message for mismatched Passwords.
     */
    @Test
    public void ifPasswordMismatchThenFail() {
        this.setRequiredFormFields();
        this.browser.type(this.password, "abcdef2A@");

        waitForHttp(this.browser).click(this.registerButton);

        assertTrue(this.browser.isTextPresent("Passwords must match."));
    }

    /**
     * Sets all form fields to valid values.
     */
    private void setAllFormFields() {
        this.setRequiredFormFields();
        this.setOptionalFormFields();
    }

    /**
     * Sets all required form fields to valid values.
     */
    private void setRequiredFormFields() {
        this.browser.type(this.firstName, "Bryan");
        this.browser.type(this.lastName, "Saunders");

        this.browser.type(this.email, "bryan@test.com");
        this.browser.type(this.password, "abcdef1A@");
        this.browser.type(this.confirmationPassword, "abcdef1A@");
    }

    /**
     * Sets all optional form fields to valid values.
     */
    private void setOptionalFormFields() {
        this.browser.type(this.country, "USA");
        this.browser.type(this.state, "SC");
        this.browser.type(this.city, "Charleston");
    }
}

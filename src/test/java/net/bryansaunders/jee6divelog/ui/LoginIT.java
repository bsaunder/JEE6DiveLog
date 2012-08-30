/**
 * 
 */
package net.bryansaunders.jee6divelog.ui;/*
 * #%L
 * BSNet-DiveLog
 * %%
 * Copyright (C) 2012 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for Login Page.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
@UsingDataSet("OneUserAccount-Admin.yml")
public class LoginIT {

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
     * Test logging in with correct information.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @InSequence(2)
    @RunAsClient
    public void ifCredentialsValidThenLogin(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "login.xhtml"));

        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");

        browser.type(userNameField, "bryan@test.com");
        browser.type(passwordField, "abcdef1A@");
        waitForHttp(browser).click(loginButton);

        assertTrue(browser.getTitle().contains("My Account"));
    }

    /**
     * Test logging in with invalid information.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @InSequence(3)
    @RunAsClient
    public void ifPasswordInvalidThenFail(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "login.xhtml"));

        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");

        browser.type(userNameField, "bryan@test.com");
        browser.type(passwordField, "dddfffeee@");
        waitForHttp(browser).click(loginButton);

        assertTrue(browser.getTitle().contains("Login"));
        assertTrue(browser.isTextPresent("Invalid login information."));
        assertTrue(browser.getAttribute(id("passwordControl"), Attribute.CLASS).contains("error"));
    }

    /**
     * Test logging in with invalid information.
     * 
     * @param applicationPath
     *            Application URL
     * @param browser
     *            Selenium Driver
     */
    @Test
    @InSequence(4)
    @RunAsClient
    public void ifUsernameInvalidThenFail(@ArquillianResource final URL applicationPath,
            @Drone final GrapheneSelenium browser) {
        browser.open(URLUtils.buildUrl(applicationPath, "login.xhtml"));

        final IdLocator userNameField = id("loginform:email");
        final IdLocator passwordField = id("loginform:password");
        final IdLocator loginButton = id("loginform:loginButton");

        browser.type(userNameField, "joe@test.com");
        browser.type(passwordField, "abcdef1A@");
        waitForHttp(browser).click(loginButton);

        assertTrue(browser.getTitle().contains("Login"));
        assertTrue(browser.isTextPresent("Invalid login information."));
        assertTrue(browser.getAttribute(id("emailControl"), Attribute.CLASS).contains("error"));
    }
}

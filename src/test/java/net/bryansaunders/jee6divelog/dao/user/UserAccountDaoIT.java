/**
 * 
 */
package net.bryansaunders.jee6divelog.dao.user;/*
 * #%L
 * BSNet-DiveLog
 * $Id:$
 * $HeadURL:$
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


import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import net.bryansaunders.jee6divelog.DeploymentFactory;
import net.bryansaunders.jee6divelog.model.UserAccount;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
public class UserAccountDaoIT {

    /**
     * Generic DAO for Testing.
     */
    @Inject
    private UserAccountDao userDao;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentFactory.getDefaultDeployment();
    }

    /**
     * Test method for save().
     */
    @Test
    @UsingDataSet("Empty.yml")
    @ShouldMatchDataSet("expected/GenericDaoIT-ifNotNullThenSave.yml")
    public void ifNotNullThenSave() {
        // given
        final String pass = "abcdef1A@";

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("bryan@test.com");
        validUser.setCity("Charleston");
        validUser.setState("SC");
        validUser.setCountry("USA");
        validUser.setPassword(pass);

        // when
        final UserAccount savedUser = this.userDao.save(validUser);

        // check the password
        final String hashedEncodedPass = Base64.encodeBase64String(DigestUtils.sha256Hex(pass).getBytes());
        assertEquals(hashedEncodedPass, savedUser.getPassword());
    }

    /**
     * Tests method for save(). Already existing Users should be updated.
     */
    @Test
    @UsingDataSet("OneUserAccount.yml")
    @ShouldMatchDataSet(value = "expected/UserAccountDaoIT-ifExistsThenUpdate.yml", excludeColumns = { "creation",
            "updated", "version" })
    public void ifExistsThenUpdate() {
        // given
        final UserAccount foundUser = this.userDao.get(1);
        foundUser.setVersion(2);
        foundUser.setLastName("Sanders");
        foundUser.setCity("Myrtle Beach");
        foundUser.setState("South Carolina");

        // when
        this.userDao.save(foundUser);

        // then
    }

}

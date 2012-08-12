/**
 * 
 */
package net.bryansaunders.jee6divelog.validation;/*
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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Password Validator.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PasswordValidatorTest {

    /**
     * Password Validator object.
     */
    private PasswordValidator passwordValidator;

    /**
     * Test Setup.
     */
    @Before
    public void initData() {
        this.passwordValidator = new PasswordValidator();
    }

    /**
     * Tests valid passwords.
     */
    @Test
    public void ifPasswordValidThenPass() {
        String[] valid = new String[] { "abcdef1A@", "Abcdef12$" };
        for (String password : valid) {
            assertTrue(password + " Was not a valid password.", this.passwordValidator.isValid(password, null));
        }
    }

    /**
     * Tests invalid passwords.
     */
    @Test
    public void ifPasswordInvalidThenFail() {
        String[] invalid = new String[] { "aB1A@", "abcdef12@", "abcDef12*", "abcdEf$$", "ABCDEF12$", "abc123",
                "password", "" };
        for (String password : invalid) {
            assertFalse(password + " Was not an invalid password.", this.passwordValidator.isValid(password, null));
        }
    }

    /**
     * Tests null passwords.
     */
    @Test
    public void ifPasswordNullThenFail() {
        assertFalse("null Was not an invalid password.", this.passwordValidator.isValid(null, null));

    }

}

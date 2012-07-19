/**
 * 
 */
package net.bryansaunders.jee6divelog.validation;

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

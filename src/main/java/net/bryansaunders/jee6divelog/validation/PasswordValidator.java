/**
 * 
 */
package net.bryansaunders.jee6divelog.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.bryansaunders.jee6divelog.validation.annotation.Password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Password Validation method.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    /**
     * RegEx Pattern for password matching.
     */
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    
    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(PasswordValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Password firstUpper) {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        this.logger.debug("Validating Password: " + value);

        boolean valid = true;
        if (value == null) {
            valid = false;
        } else {
            final Pattern pattern = Pattern.compile(PasswordValidator.PASSWORD_PATTERN);
            final Matcher matcher = pattern.matcher(value);

            valid = matcher.matches();
        }

        return valid;
    }
}

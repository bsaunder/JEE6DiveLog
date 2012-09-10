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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.bryansaunders.jee6divelog.validation.annotation.Password;

import org.jboss.logging.Logger;

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
    private Logger logger = Logger.getLogger(PasswordValidator.class);

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

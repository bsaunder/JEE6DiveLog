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


import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.bryansaunders.jee6divelog.validation.annotation.FieldMatch;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator to check if two fields match.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    
    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(FieldMatchValidator.class);

    /**
     * First Field Name.
     */
    private String firstFieldName;

    /**
     * Second Field Name.
     */
    private String secondFieldName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = false;

        try {
            final Object firstObj = BeanUtils.getProperty(value, this.firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, this.secondFieldName);

            valid = (firstObj == null && secondObj == null) || (firstObj != null && firstObj.equals(secondObj));
            this.logger.debug("Checking Fields for Match: " + this.firstFieldName + "(" + firstObj
                    + ") <> " + this.secondFieldName + "(" + secondObj + ") = " + valid);
        } catch (final NoSuchMethodException e) {
            this.logger.error("Error Validating Fields Match", e);
        } catch (final IllegalAccessException e) {
            this.logger.error("Error Validating Fields Match", e);
        } catch (final InvocationTargetException e) {
            this.logger.error("Error Validating Fields Match", e);
        }

        return valid;
    }

    /**
     * Get First Field Name.
     * 
     * @return the firstFieldName
     */
    public String getFirstFieldName() {
        return this.firstFieldName;
    }

    /**
     * Set First Field Name.
     * 
     * @param newFirstFieldName
     *            the firstFieldName to set
     */
    public void setFirstFieldName(final String newFirstFieldName) {
        this.firstFieldName = newFirstFieldName;
    }

    /**
     * Get Second Field Name.
     * 
     * @return the secondFieldName
     */
    public String getSecondFieldName() {
        return this.secondFieldName;
    }

    /**
     * Set Second Field Name.
     * 
     * @param newSecondFieldName
     *            the secondFieldName to set
     */
    public void setSecondFieldName(final String newSecondFieldName) {
        this.secondFieldName = newSecondFieldName;
    }

}

package net.bryansaunders.jee6divelog.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.bryansaunders.jee6divelog.annotations.FieldMatch;

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

package net.bryansaunders.jee6divelog.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.bryansaunders.jee6divelog.validation.PasswordValidator;

/**
 * Password Constraint Annotation.
 * 
 * Passwords must:
 * <ul>
 * <li>Contain one digit from 0-9</li>
 * <li>Contain one lowercase letter</li>
 * <li>Contain one uppercase character</li>
 * <li>Contain one special character from "@#$%"</li>
 * <li>Be between 6 & 20 characters</li>
 * </ul>
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "{net.bryansaunders.constraints.password}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

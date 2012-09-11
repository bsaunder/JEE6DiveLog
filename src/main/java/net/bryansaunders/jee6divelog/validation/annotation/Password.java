package net.bryansaunders.jee6divelog.validation.annotation;/*
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
 * Passwords must be:
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

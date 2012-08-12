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

import net.bryansaunders.jee6divelog.validation.FieldMatchValidator;

/**
 * Validation annotation to validate that 2 fields have the same value. An array of fields and their matching
 * confirmation fields can be supplied.
 * 
 * Example, compare 1 pair of fields:
 * 
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
 * 
 *                   Example, compare more than 1 pair of fields:
 * @FieldMatch.List({
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
 * @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")})
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
    String message() default "{constraints.fieldmatch}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     * 
     * @see FieldMatch
     */
    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}

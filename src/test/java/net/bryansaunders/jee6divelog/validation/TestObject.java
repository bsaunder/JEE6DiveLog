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


/**
 * Test Class for used for testing Field Match Validator.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TestObject {

    /**
     * Field 1.
     */
    private String field1;

    /**
     * Field 2.
     */
    private String field2;

    /**
     * Set Field 1.
     * 
     * @param newField1
     *            the field1 to set
     */
    public void setField1(final String newField1) {
        this.field1 = newField1;
    }

    /**
     * Set Field 2.
     * 
     * @param newField2
     *            the field2 to set
     */
    public void setField2(final String newField2) {
        this.field2 = newField2;
    }

    /**
     * Get Field 1.
     * 
     * @return the field1
     */
    public String getField1() {
        return this.field1;
    }

    /**
     * Get Field 2.
     * 
     * @return the field2
     */
    public String getField2() {
        return this.field2;
    }

}

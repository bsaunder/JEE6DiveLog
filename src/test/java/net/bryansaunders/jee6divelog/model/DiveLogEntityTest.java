package net.bryansaunders.jee6divelog.model;/*
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


import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Base DiveLog Entity.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class DiveLogEntityTest {

    /**
     * Internal DiveLogEntity.
     */
    private DiveLogEntity entity;

    /**
     * Sets up the user for each test.
     */
    @Before
    public void setUpTest() {
        this.entity = new DiveLogEntity();
    }

    /**
     * Tests the Id.
     */
    @Test
    public void testId() {
        final Integer value = 1;
        this.entity.setId(value);
        assertEquals(value, this.entity.getId());
    }

    /**
     * Tests the Version.
     */
    @Test
    public void testVersion() {
        final Integer value = 1;
        this.entity.setVersion(value);
        assertEquals(value, this.entity.getVersion());
    }

    /**
     * Tests the Created.
     */
    @Test
    public void testCreated() {
        final Date date = new Date();
        this.entity.setCreated(date);
        assertEquals(date, this.entity.getCreated());
    }

    /**
     * Tests the Updated.
     */
    @Test
    public void testUpdated() {
        final Date date = new Date();
        this.entity.setUpdated(date);
        assertEquals(date, this.entity.getUpdated());
    }

    /**
     * Test OnCreate.
     */
    @Test
    public void testOnCreated() {
        final Date date = new Date();

        this.entity.onCreate();
        final Date createdDate = this.entity.getCreated();
        final Date updatedDate = this.entity.getUpdated();

        assertEquals(createdDate, updatedDate);
        assertEquals(date.getTime(), createdDate.getTime());
    }

    /**
     * Test onUpdate.
     */
    @Test
    public void testOnUpdate() {
        final Date date = new Date();

        this.entity.onUpdate();
        final Date updatedDate = this.entity.getUpdated();

        assertEquals(date.getTime(), updatedDate.getTime());
    }

}

package net.bryansaunders.jee6divelog.model;

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

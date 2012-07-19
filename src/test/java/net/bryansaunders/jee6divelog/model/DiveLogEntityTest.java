package net.bryansaunders.jee6divelog.model;

import static org.junit.Assert.assertEquals;

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

}

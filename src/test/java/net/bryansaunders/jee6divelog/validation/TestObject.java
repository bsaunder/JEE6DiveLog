package net.bryansaunders.jee6divelog.validation;

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

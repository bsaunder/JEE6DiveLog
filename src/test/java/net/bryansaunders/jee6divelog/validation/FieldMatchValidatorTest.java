package net.bryansaunders.jee6divelog.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the Field Match Validator.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class FieldMatchValidatorTest {

    /**
     * Field 2 Name.
     */
    private static final String FIELD2 = "field2";

    /**
     * Field 1 Name.
     */
    private static final String FIELD1 = "field1";

    /**
     * Test Validator Construction.
     */
    @Test
    public void testConstruction() {
        final FieldMatchValidator validator = new FieldMatchValidator();
        assertNotNull(validator);

        validator.setFirstFieldName(FIELD1);
        validator.setSecondFieldName(FIELD2);

        assertEquals(FIELD1, validator.getFirstFieldName());
        assertEquals(FIELD2, validator.getSecondFieldName());
    }

    /**
     * Test if Matching Fields pass.
     */
    @Test
    public void ifFieldsMatchThenPass() {
        final FieldMatchValidator validator = new FieldMatchValidator();
        validator.setFirstFieldName(FIELD1);
        validator.setSecondFieldName(FIELD2);

        final TestObject testObject = new TestObject();
        testObject.setField1("sdf");
        testObject.setField2("sdf");

        assertTrue(validator.isValid(testObject, null));
    }

    /**
     * Test if Mismatching Fields fail.
     */
    @Test
    public void ifFieldsDontMatchThenFail() {
        final FieldMatchValidator validator = new FieldMatchValidator();
        validator.setFirstFieldName(FIELD1);
        validator.setSecondFieldName(FIELD2);

        final TestObject testObject = new TestObject();
        testObject.setField1("qwe");
        testObject.setField2("dfg");

        assertFalse(validator.isValid(testObject, null));
    }

    /**
     * Test Failure when Fields don't Exist.
     */
    @Test
    public void ifFieldsDontExistThenFail() {
        final FieldMatchValidator validator = new FieldMatchValidator();
        validator.setFirstFieldName("joe");
        validator.setSecondFieldName("non");

        final TestObject testObject = new TestObject();
        testObject.setField1("sdf");
        testObject.setField2("asd");

        assertFalse(validator.isValid(testObject, null));
    }

    /**
     * Test Failure when Fields don't Exist.
     * 
     * @throws NoSuchMethodException
     *             Reflection Error
     */
    @Test
    public void ifFieldsHaveErrorThenFail() throws NoSuchMethodException {
        final FieldMatchValidator validator = new FieldMatchValidator();
        validator.setFirstFieldName(FIELD1);
        validator.setSecondFieldName(FIELD2);

        final TestObject testObject = new TestObject();
        testObject.setField1("sdfg");
        testObject.setField2("asdd");

        assertFalse(validator.isValid(testObject, null));
    }
}

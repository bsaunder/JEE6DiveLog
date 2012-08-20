/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import static org.junit.Assert.fail;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for Registration Page.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class RegisterTemplateIT extends BaseTemplateTest {

    @Test
    public void ifAllFieldsValidThenRegister(){
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifRequiredFieldsValidThenRegister() {
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifEmailMissingThenFail() {
        fail("Not Yet Implemented");
    }
    
    @Ignore
    @Test
    public void ifEmailNotUniqueThenFail(){
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifFirstNameMissingThenFail() {
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifLastNameMissingThenFail() {
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifPasswordMissingThenFail() {
        fail("Not Yet Implemented");
    }
    
    @Test
    public void ifPasswordMismatchThenFail() {
        fail("Not Yet Implemented");
    }
}

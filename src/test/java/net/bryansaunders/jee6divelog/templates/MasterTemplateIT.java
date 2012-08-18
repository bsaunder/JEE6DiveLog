/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Tests for JSF Master Template.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class MasterTemplateIT extends BaseTemplateTest {

	/**
	 * Test Setup.
	 */
	@Before
	public void setUp() {
		openContext("index.html");
	}

	/**
	 * Test to check the title.
	 */
	@Test
	public void ifTitlePresentThenPass() {
		String title = this.browser.getTitle();
		System.out.println("Title: " + title);
		assertTrue(title.startsWith("BSNet-DiveLog - "));
	}

}

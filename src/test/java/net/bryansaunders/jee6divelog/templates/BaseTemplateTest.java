/**
 * 
 */
package net.bryansaunders.jee6divelog.templates;

import java.net.URL;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;

/**
 * Base Class for Template Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public class BaseTemplateTest {

	@Drone
	protected GrapheneSelenium browser;

	@ArquillianResource
	protected URL applicationPath;

	/**
	 * Creates Arquillian Deployment Container.
	 * 
	 * @return deployment container
	 */
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return DeploymentFactory.getTemplateDeployment();
	}

	/**
	 * Opens the page at the specified path.
	 * 
	 * @param pageName
	 *            Page to open
	 */
	public void openContext(final String pageName) {
		final URL pageUrl = URLUtils.buildUrl(this.applicationPath, pageName);
		this.browser.open(pageUrl);
	}
}

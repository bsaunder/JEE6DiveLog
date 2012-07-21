/**
 * 
 */
package net.bryansaunders.jee6divelog;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;

/**
 * Builds a Default Arquillian Deployment.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public final class DefaultDeployment {

    /**
     * Default Constructor.
     */
    private DefaultDeployment() {
        // Do Nothing
    }

    /**
     * Gets a Default WebArchive Deployment.
     * 
     * @return Default Arquillian Deployment
     */
    public static WebArchive getDefaultDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jee6divelog_test.war")
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
    }
}

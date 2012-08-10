/**
 * 
 */
package net.bryansaunders.jee6divelog;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Ignore;

/**
 * Builds a Default Arquillian Deployment.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public final class DeploymentFactory {

    /**
     * Default Constructor.
     */
    private DeploymentFactory() {
        // Do Nothing
    }

    /**
     * Gets a Default WebArchive Deployment.
     * 
     * @return Default Arquillian Deployment
     */
    public static WebArchive getDefaultDeployment() {        
        return ShrinkWrap.create(WebArchive.class, "jee6divelog_test_default.war")
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addPackages(true, "org.apache.commons.codec")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
    }
    
    /**
     * Gets a Default WebArchive Deployment.
     * 
     * @return Default Arquillian Deployment
     */
    public static WebArchive getRestApiDeployment() {
        final MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");
        
        return ShrinkWrap.create(WebArchive.class, "jee6divelog_test_restapi.war")
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addPackages(true, "org.apache.commons.codec")
                .addAsLibraries(resolver.artifact("com.jayway.restassured:rest-assured:1.6.2").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.codehaus.jackson:jackson-jaxrs:1.9.8").resolveAsFiles())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
    }
}

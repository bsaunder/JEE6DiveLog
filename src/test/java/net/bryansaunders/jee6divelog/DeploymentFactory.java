/**
 * 
 */
package net.bryansaunders.jee6divelog;/*
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


import java.io.File;

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
                .addAsLibraries(resolver.artifact("ch.qos.logback:logback-classic:1.0.6").resolveAsFiles())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }
}

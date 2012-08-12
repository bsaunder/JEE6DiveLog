/**
 * 
 */
package net.bryansaunders.jee6divelog.util;/*
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


import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class ResourcesIT {
    
    /**
     * Entity Manager.
     */
    @Inject
    private EntityManager entityManager;
    
    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentFactory.getDefaultDeployment();
    }

    /**
     * Test EntityManager Injection.
     */
    @Test
    public void testEntityManager() {
        assertNotNull(this.entityManager);
    }

}

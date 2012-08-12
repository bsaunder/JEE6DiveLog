package net.bryansaunders.jee6divelog.dao.user;/*
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


import net.bryansaunders.jee6divelog.dao.GenericDaoImpl;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

/**
 * User DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserAccountDao extends GenericDaoImpl<UserAccount> {

    /**
     * Number of hash iterations.
     */
    public static final int HASH_ITERATIONS = 1024;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAccount save(final UserAccount user) {
        // Encrypt Password
        final Integer id = user.getId();
        if (id == null) {
            final String password = user.getPassword();
            final String hashedEncodedPass = SecurityUtils.generatePasswordHash(password);
            user.setPassword(hashedEncodedPass);
        }

        // Save
        return super.save(user);
    }
}

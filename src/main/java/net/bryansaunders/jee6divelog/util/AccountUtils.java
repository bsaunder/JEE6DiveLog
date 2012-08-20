package net.bryansaunders.jee6divelog.util;/*
 * #%L
 * BSNet-DiveLog
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


import java.util.Date;

import net.bryansaunders.jee6divelog.model.DiveLogEntity;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Identity;

/**
 * User Account Utilities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class AccountUtils {

    /**
     * Default Private Constructor.
     */
    private AccountUtils() {
        // Do Nothing
    }

    /**
     * Generates a UserAccount Object for the Current Identity.
     * 
     * @param identity
     *            Identity to Copy
     * @return UserAccount
     */
    public static UserAccount createUserAccount(final Identity identity) {
        final UserAccount userAccount = new UserAccount();
        userAccount.setEmail(identity.getUsername());
        userAccount.setPublicApiKey(identity.getPublicApiKey());
        userAccount.setPrivateApiKey(identity.getPrivateApiKey());
        userAccount.setApiKeyExpiration(identity.getApiKeyExpiration());
        userAccount.setPermissions(identity.getPermissions());
        return userAccount;
    }

    /**
     * Generates a Cleaned UserAccount Object for the Current Identity.
     * 
     * @param identity
     *            Identity to Copy
     * @return UserAccount
     */
    public static UserAccount createCleanUserAccount(final Identity identity) {
        UserAccount account = AccountUtils.createUserAccount(identity);
        account = AccountUtils.getCleanUserAccount(account);

        return account;
    }
    
    /**
     * Clears the Dates on any DiveLogEntity.
     * 
     * @param <T>
     *            Generic Type
     * @param entity
     *            Entity to Clear Dates on
     * @return Clean DiveLogEntity
     */
    public static <T extends DiveLogEntity> T getCleanEntity(final T entity) {
        entity.setCreated(new Date());
        entity.setUpdated(new Date());
        return entity;
    }

    /**
     * Clears the Dates & Password on any UserAccount.
     * 
     * @param userAccount
     *            UserAccount to Clear
     * @return Clean UserAccount
     */
    public static UserAccount getCleanUserAccount(final UserAccount userAccount) {
        userAccount.setPassword("***");
        return AccountUtils.getCleanEntity(userAccount);
    }
}

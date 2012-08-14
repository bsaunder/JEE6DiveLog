package net.bryansaunders.jee6divelog.util;

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
        userAccount.setRoles(identity.getRoles());
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

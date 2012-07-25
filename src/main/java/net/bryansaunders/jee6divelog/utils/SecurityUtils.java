package net.bryansaunders.jee6divelog.utils;

/**
 * Security Utilities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public final class SecurityUtils {
    
    /**
     * Default Private Constructor.
     */
    private SecurityUtils() {
        // Do Nothing
    }

    /**
     * Generate Password Hash for Application. Uses SHA-256 & Base64 Encoding.
     * 
     * @param passwordToHash String to hash for password
     * @return Hashed password.
     */
    public static String generatePasswordHash(String passwordToHash){       
        final String hashedPass = HashUtils.toSha256(passwordToHash);
        final String hashedEncodedPass = HashUtils.base64Encode(hashedPass);
        
        return hashedEncodedPass;
    }
}

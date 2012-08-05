package net.bryansaunders.jee6divelog.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Security Utilities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class SecurityUtils {

    /**
     * Random Generator.
     */
    private static SecureRandom random = new SecureRandom();

    /**
     * Default Private Constructor.
     */
    private SecurityUtils() {
        // Do Nothing
    }

    /**
     * Generate Password Hash for Application. Uses SHA-256 & Base64 Encoding.
     * 
     * @param passwordToHash
     *            String to hash for password
     * @return Hashed password.
     */
    public static String generatePasswordHash(final String passwordToHash) {
        final String hashedPass = HashUtils.toSha256(passwordToHash);
        final String hashedEncodedPass = HashUtils.base64Encode(hashedPass);

        return hashedEncodedPass;
    }

    /**
     * Generates a Random REST API Key.
     * 
     * @return Random API Key
     */
    public static String generateRestApiKey() {
        final String apiKey = new BigInteger(130, SecurityUtils.random).toString(32);
        final String hashedApiKey = HashUtils.toSha256(apiKey);
        final String hashedEncodedApiKey = HashUtils.base64Encode(hashedApiKey);

        return hashedEncodedApiKey;
    }

    /**
     * Generate REST API Token.
     * 
     * @param username
     *            Username for Token
     * @param apiKey
     *            API Key for Token
     * @return Token
     */
    public static String generateRestApiToken(final String username, final String apiKey) {
        final String combinedString = username + apiKey;
        final String token = HashUtils.toSha256(combinedString);

        return token;
    }
}

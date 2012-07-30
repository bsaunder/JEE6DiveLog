/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Utility Methods for Dealing with Hashing.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class HashUtils {

    /**
     * Default Private Constructor.
     */
    private HashUtils() {
        // Do Nothing
    }

    /**
     * Hashes the string using SHA-256 Algorithim.
     * 
     * @param stringToHash
     *            String to hash
     * @return Hashed String
     */
    public static String toSha256(final String stringToHash) {
        return DigestUtils.sha256Hex(stringToHash);
    }

    /**
     * Encodes a string as Base64.
     * 
     * @param stringToEncode
     *            String to encode
     * @return Encoded String
     */
    public static String base64Encode(final String stringToEncode) {
        return Base64.encodeBase64String(stringToEncode.getBytes());
    }

    /**
     * Decodes a Base64 string.
     * 
     * @param stringToDecode
     *            String to decode
     * @return Decoded String
     */
    public static String base64Decode(final String stringToDecode) {
        return new String(Base64.decodeBase64(stringToDecode));
    }
}

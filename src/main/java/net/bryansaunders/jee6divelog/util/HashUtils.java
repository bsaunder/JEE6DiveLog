/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

/*
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

import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility Methods for Dealing with Hashing.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class HashUtils {
    
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtils.class);

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

    /**
     * Generates a HMAC-SHA1 Hash of the given String using the specified key.
     * 
     * @param stringToHash
     *            String to Hash
     * @param key
     *            Key to Sign the String with
     * @return Hashed String
     */
    public static String toHmacSha1(final String stringToHash, final String key) {
        String result = "";
        try {
            // Get an hmac_sha1 key from the raw key bytes
            final byte[] keyBytes = key.getBytes();
            final SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            final Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            final byte[] rawHmac = mac.doFinal(stringToHash.getBytes());

            // Convert raw bytes to Hex
            final byte[] base64 = Base64.encodeBase64(rawHmac);

            // Covert array of Hex bytes to a String
            result = new String(base64);
        } catch (GeneralSecurityException e) {
            HashUtils.LOGGER.error("An Error Occured Generating an HMAC-SHA1 Hash!", e);
        }

        return result;
    }
}

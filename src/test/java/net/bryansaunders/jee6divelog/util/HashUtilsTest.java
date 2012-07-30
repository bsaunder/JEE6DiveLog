/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

import static org.junit.Assert.assertEquals;

import net.bryansaunders.jee6divelog.util.HashUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class HashUtilsTest {

    /**
     * Tests SHA-256 Hashing.
     */
    @Test
    public void getSha256Hash() {
        final String original = "Hash Me!";
        final String expectedHash = DigestUtils.sha256Hex(original);

        assertEquals(expectedHash, HashUtils.toSha256(original));
    }

    /**
     * Tests Encode/Decode of Base64.
     */
    @Test
    public void testBase64() {
        final String original = "Test String";
        final String expectedBase64 = Base64.encodeBase64String(original.getBytes());

        final String encoded = HashUtils.base64Encode(original);
        assertEquals(expectedBase64, encoded);
        assertEquals(original, HashUtils.base64Decode(encoded));
    }

}

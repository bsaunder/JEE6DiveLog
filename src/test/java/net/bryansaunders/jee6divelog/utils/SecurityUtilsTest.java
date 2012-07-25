/**
 * 
 */
package net.bryansaunders.jee6divelog.utils;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class SecurityUtilsTest {

    /**
     * Test method for {@link net.bryansaunders.jee6divelog.utils.SecurityUtils#generatePasswordHash(java.lang.String)}.
     */
    @Test
    public void testGeneratePasswordHash() {
        // given
        final String password = "pass123";
        
        // when
        final String hashedPass = SecurityUtils.generatePasswordHash(password);
        
        // then
        final String expectedPass = Base64.encodeBase64String(DigestUtils.sha256Hex(password).getBytes());
        assertEquals(expectedPass, hashedPass);
    }

}

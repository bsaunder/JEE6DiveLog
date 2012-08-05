/**
 * 
 */
package net.bryansaunders.jee6divelog.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class SecurityUtilsTest {

    /**
     * Test method for generatePasswordHash.
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
    
    /**
     * Test Method for Generate REST API Key.
     */
    @Test
    public void testGenerateApiKey(){
        fail("Not Yet Implemented");
    }
    
    /**
     * Test Method for Generate REST API Token.
     */
    @Test
    public void testGenerateToken(){
        fail("Not Yet Implemented");
    }

}

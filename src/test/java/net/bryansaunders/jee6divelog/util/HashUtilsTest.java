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

import static org.junit.Assert.assertEquals;

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

    /**
     * Tests HMAC-SHA1 Generation.
     */
    @Test
    public void testHmacSha1() {
        final String privateKey = "3435y5#=G-E%#45yq354y35ghW=%YQE%HG3";
        final String stringToHash = "GET=application/json=n34g3445g34234345ge=2012-01-01=fsdfsdf34sregfsre";
        final String expected = "EqhLmfdxK9zvETh4jZu5RVMTGLQ=";

        final String result = HashUtils.toHmacSha1(stringToHash, privateKey);

        assertEquals(expected, result);
    }

}

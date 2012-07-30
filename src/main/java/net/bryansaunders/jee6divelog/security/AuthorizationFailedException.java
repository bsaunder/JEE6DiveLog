package net.bryansaunders.jee6divelog.security;

/**
 * Exception that is thrown when Authorization Fails.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class AuthorizationFailedException extends Exception {

    /**
     * Default Serial ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default Constructor.
     * 
     * @param message
     *            Error message
     */
    public AuthorizationFailedException(final String message) {
        super(message);
    }

}

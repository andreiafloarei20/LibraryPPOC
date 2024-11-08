package project.exceptions;

/**
 * Used to handle the case of users that enter authors not available in the library system
 */
public class InvalidAuthor extends NullPointerException {
    public InvalidAuthor(String message) {
        super(message);
    }
}

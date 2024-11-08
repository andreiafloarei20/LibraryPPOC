package project.exceptions;

/**
 * USed to handle the case of users entering categories that do not exist in the system
 */
public class InvalidCategory extends NullPointerException {
    public InvalidCategory(String message) {
        super(message);
    }
}

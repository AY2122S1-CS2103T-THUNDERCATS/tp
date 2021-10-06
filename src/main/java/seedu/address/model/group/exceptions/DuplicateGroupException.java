package seedu.address.model.group.exceptions;

/**
 * Signals that the operation will result in duplicate Groups (Groups are considered duplicates if they have the same
 * name).
 */
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Operation would result in duplicate groups");
    }
}
package cms.model.person.exceptions;

/**
 * Signals that the person data violates model-level invariants.
 * For example, a SOC username that is in NUS ID format that does not match the person's NUS ID.
 * This is not allowed by SOC policy as it can lead to confusion.
 */
public class InvalidPersonException extends IllegalArgumentException {

    public InvalidPersonException(String message) {
        super(message);
    }
}

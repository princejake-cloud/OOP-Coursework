/**
 * InvalidShapeException.java
 * Custom UNCHECKED exception thrown when a shape receives an invalid dimension
 * or a non-positive resize factor.
 *
 * DESIGN CHOICE: We extend RuntimeException (unchecked) because invalid
 * dimensions are programming errors, not recoverable I/O conditions.
 * The caller is not forced to declare "throws" on every method, keeping
 * the API clean while still allowing a try-catch in the demo.
 */
public class InvalidShapeException extends RuntimeException {

    // ── Constructor with message ────────────────────────────────────────
    public InvalidShapeException(String message) {
        super(message);
    }

    // ── Constructor with message and cause ──────────────────────────────
    public InvalidShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}

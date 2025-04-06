package seedu.address.model.other;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Other in TeamScape.
 * Guarantees: immutable; name is valid as declared in {@link #isValidOtherName(String)}
 */
public class Other {

    public static final String MESSAGE_CONSTRAINTS = "Others names should not be empty";
    public static final String VALIDATION_REGEX = "(?=.*\\S).*";

    public final String other;

    /**
     * Constructs a {@code Other}.
     *
     * @param other A valid other.
     */
    public Other(String other) {
        requireNonNull(other);
        checkArgument(isValidOtherName(other), MESSAGE_CONSTRAINTS);
        this.other = other;
    }

    /**
     * Returns true if a given string is a valid other name.
     */
    public static boolean isValidOtherName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Other)) {
            return false;
        }

        Other otherOther = (Other) other;
        return this.other.equals(otherOther.other);
    }

    @Override
    public int hashCode() {
        return other.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + other + ']';
    }
}

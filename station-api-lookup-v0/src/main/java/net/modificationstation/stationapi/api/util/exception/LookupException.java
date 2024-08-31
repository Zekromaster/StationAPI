package net.modificationstation.stationapi.api.util.exception;

public class LookupException extends RuntimeException {
    public final Class<?> expected;

    public LookupException(Class<?> expected) {
        super("Expected " + expected);
        this.expected = expected;
    }
}

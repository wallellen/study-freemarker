package edu.alvin.fm.core.utils.Strings;

public final class Strings {

    private Strings() {
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}

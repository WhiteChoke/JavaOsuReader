package dev.osureader.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserPermissions {
    NONE(0),
    NORMAl(1),
    MODERATOR(2),
    SUPPORTER(4),
    FRIEND(8),
    PEPPY(16),
    WORLD_CUP_STUFF(32);

    private final int value;

    UserPermissions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static final Map<Integer, UserPermissions> BY_VALUE = new HashMap<>();

    static {
        for (UserPermissions permissions : values()) {
            BY_VALUE.put(permissions.value, permissions);
        }
    }

    public static UserPermissions fromValue(int value) {
        var permissions = BY_VALUE.get(value);

        if (permissions == null) {
            throw new IllegalArgumentException("Unknown permissions value: " + value);
        }

        return permissions;
    }
}

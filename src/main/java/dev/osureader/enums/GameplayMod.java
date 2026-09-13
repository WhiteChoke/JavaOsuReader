package dev.osureader.enums;

import java.util.HashMap;
import java.util.Map;

public enum GameplayMod {
    STANDARD(0),
    TAIKO(1),
    CATCH(2),
    MANIA(3);

    private final int value;

    private static final Map<Integer, GameplayMod> BY_VALUE = new HashMap<>();

     GameplayMod(int value) {
        this.value = value;
    }

    static {
        for (GameplayMod mod : values()) {
            BY_VALUE.put(mod.value, mod);
        }
    }

    public static GameplayMod fromValue(int value) {
        var mod = BY_VALUE.get(value);

        if (mod == null) {
            throw new IllegalArgumentException("Unknown gameplay mode value: " + value);
        }

        return mod;
    }
}

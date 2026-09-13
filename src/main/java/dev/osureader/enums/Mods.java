package dev.osureader.enums;

import java.util.HashMap;
import java.util.Map;

public enum Mods {
    NONE(0),
    NO_FAIL(1),
    EASY(2),
    TOUCH_DEVICE(4),
    HIDDEN(8),
    HARD_ROCK(16),
    SUDDEN_DEATH(32),
    DOUBLE_TIME(64),
    RELAX(128),
    HALF_TIME(256),
    NIGHTCORE(512),
    FLASHLIGHT(1024),
    AUTOPLAY(2048),
    SPUN_OUT(4096),
    RELAX2(8192),
    PERFECT(16384),
    KEY4(32768),
    KEY5(65536),
    KEY6(131072),
    KEY7(262144),
    KEY8(524288),
    FADE_IN(1048576),
    RANDOM(2097152),
    LAST_MOD(4194304),
    TARGET_PRACTICE(8388608),
    KEY9(16777216),
    COOP(33554432),
    KEY1(67108864),
    KEY3(134217728),
    KEY2(268435456),
    SCORE_V2(536870912),
    MIRROR(1073741824);

    public final int value;

    private static final Map<Integer, Mods> BY_VALUE = new HashMap<>();

    Mods(int value) {
        this.value = value;
    }

    static {
        for (Mods mod : values()) {
            BY_VALUE.put(mod.value, mod);
        }
    }

    public static Mods fromValue(int value) {
        var mod = BY_VALUE.get(value);

        if (mod == null) {
            throw new IllegalArgumentException("Unknown beatmap status value: " + value);
        }

        return mod;
    }
}

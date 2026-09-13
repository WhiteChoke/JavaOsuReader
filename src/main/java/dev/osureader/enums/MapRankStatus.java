package dev.osureader.enums;

import java.util.HashMap;
import java.util.Map;

public enum MapRankStatus {
    UNKNOW(0),
    UNSUBMITTED(1),
    PENDING(2),
    UNUSED(3),
    RANKED(4),
    APPROVED(5),
    QUALIFIED(6),
    LOVED(7);

    private final int value;

    private static final Map<Integer, MapRankStatus> BY_VALUE = new HashMap<>();

    MapRankStatus(int value) {
        this.value = value;
    }

    static {
        for (MapRankStatus status : values()) {
            BY_VALUE.put(status.value, status);
        }
    }

    public static MapRankStatus fromValue(int value) {
        var status = BY_VALUE.get(value);

        if (status == null) {
            throw new IllegalArgumentException("Unknown beatmap status value: " + value);
        }

        return status;
    }
}

package dev.osureader.utils;

import dev.osureader.enums.Mods;

import java.util.ArrayList;
import java.util.List;

public class ModParser {
    public static List<Mods> parse(int modInt) {
        List<Mods> mods = new ArrayList<>();

        if (modInt == 0) {
            return mods;
        }

        for (Mods mod : Mods.values()) {
            if ((modInt & mod.value) != 0) {
                mods.add(mod);
            }
        }

        return mods;
    }
}

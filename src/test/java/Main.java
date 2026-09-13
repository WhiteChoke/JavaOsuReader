import dev.osureader.enums.Mods;
import dev.osureader.model.osuDb.OsuDbModel;
import dev.osureader.reader.OsuDbReader;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path dbPath = Path.of("osu!.db");
        OsuDbReader reader = new OsuDbReader(dbPath);

        OsuDbModel osuDb = reader.read();

        var map = osuDb.beatmaps().getFirst();

        System.out.println(map.stdStarRatings().get(0));
        System.out.println(map.stdStarRatings().get());
    }
}

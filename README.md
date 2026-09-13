# JavaOsuReader

---

A small Java library for reading and parsing binary `.db` files produced by the
standard [osu!](https://osu.ppy.sh) client

The library exposes a simple, extensible reader API: an abstract base class
handles the binary primitives used across osu!'s `.db` formats, while
format-specific readers build on top of it to produce fully-typed model
objects.

You can read more about the structure of `.db` files in the wiki
on the [Legacy database file structure](https://github.com/ppy/osu/wiki/Legacy-database-file-structure)
section on the official osu! GitHub page.

## Usage

```java
import java.nio.file.Path;

Path osuDbPath = Path.of("C:/Games/osu!/osu!.db");

OsuDbReader reader = new OsuDbReader(dbPath);
OsuDbModel osuDb = reader.read();

System.out.println("Client version: " + osuDb.clientVersion());
System.out.println("Beatmaps found: " + osuDb.beatmapNumber());

osuDb.beatmaps().forEach(beatmap ->
        System.out.println(beatmap.artistName() + " - " + beatmap.songTitle())
        );
```

## Limitations

- Currently `presence.db` is not supported.
- The library supports parsing `.db` files where the client version is greater than 20250107.
## Contributing

Issues and pull requests are welcome 

## License
*This project is licensed under the [Apache 2.0](LICENSE).*


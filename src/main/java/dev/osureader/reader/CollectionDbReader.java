package dev.osureader.reader;

import dev.osureader.model.collectionDb.CollectionDbModel;
import dev.osureader.model.collectionDb.CollectionModel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader implementation that parses the collection.db file of the stable osu!
 * client into an {@code CollectionDbModel}.
 * Extends {@code AbstractReader<CollectionDbModel>} and reuses its primitive-reading
 * helpers (strings, booleans, ULEB128 lengths, int-float pairs)
 * to decode the file's binary layout
 */
public class CollectionDbReader extends AbstractReader<CollectionDbModel> {

    /**
     * Creates a new collection.db reader for the specified file path
     * @param filePath the path to the collection.db file to be read
     */
    public CollectionDbReader(Path filePath) {
        super(filePath);
    }

    /**
     * Reads the entire collection.db file into memory and parses
     * it into an {@code CollectionDbModel}
     * @return the parsed {@code CollectionDbModel} representing the contents of the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    @Override
    public CollectionDbModel read() throws IOException {
        byte[] bytes = Files.readAllBytes(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        var collectionBuilder = CollectionDbModel.builder();

        collectionBuilder.setVersion(buffer.getInt());
        int collectionNumber = buffer.getInt();
        collectionBuilder.setCollectionsNumber(collectionNumber);

        List<CollectionModel> collections = new ArrayList<>();

        for (int i = 0; i < collectionNumber; i++) {
            collections.add(readCollection(buffer));
        }

        collectionBuilder.setCollections(collections);
        return collectionBuilder.build();
    }

    /**
     * Reads a single beatmap entry from the buffer
     * and builds the corresponding {@code CollectionModel}
     * @param buffer the buffer to read from
     * @return the parsed {@code CollectionModel}
     */
    private CollectionModel readCollection(ByteBuffer buffer) {
        var builder = CollectionModel.builder();

        builder.setName(readString(buffer));

        int beatmapsNumber = buffer.getInt();
        builder.setBeatmapsNumber(beatmapsNumber);

        List<String> beatmapMD5List = new ArrayList<>(beatmapsNumber);

        for (int i = 0; i < beatmapsNumber; i++) {
            beatmapMD5List.add(readString(buffer));
        }

        builder.setBeatmapsMD5(beatmapMD5List);

        return builder.build();
    }
}

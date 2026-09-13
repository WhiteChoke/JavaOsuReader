package dev.osureader.reader;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.Mods;
import dev.osureader.model.scoresDb.ScoreModel;
import dev.osureader.model.scoresDb.ScoresBeatmapModel;
import dev.osureader.model.scoresDb.ScoresDbModel;
import dev.osureader.utils.ModParser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader implementation that parses the scores.db file of the stable osu!
 * client into an {@code ScoresDbModel}.
 * Extends {@code AbstractReader<ScoresDbModel>} and reuses its primitive-reading
 * helpers (strings, booleans, ULEB128 lengths, int-float pairs)
 * to decode the file's binary layout
 */
public class ScoresDbReader extends AbstractReader<ScoresDbModel> {

    /**
     * Creates a new scores.db reader for the specified file path
     * @param filePath the path to the scores.db file to be read
     */
    public ScoresDbReader(Path filePath) {
        super(filePath);
    }

    /**
     * Reads the entire scores.db file into memory and parses
     * it into an {@code ScoresDbModel}
     * @return the parsed {@code ScoresDbModel} representing the contents of the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    @Override
    public ScoresDbModel read() throws IOException {
        byte[] bytes = Files.readAllBytes(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        var scoresBuilder = ScoresDbModel.builder();

        scoresBuilder.setVersion(buffer.getInt());

        int beatmapCount = buffer.getInt();

        scoresBuilder.setBeatmapNumber(beatmapCount);

        var beatmaps = readScoresBeatmap(beatmapCount, buffer);

        scoresBuilder.setBeatmaps(beatmaps);

        return scoresBuilder.build();
    }

    /**
     * Reads the specified number of beatmap entries from the buffer
     * @param beatmapCount the number of beatmaps to read
     * @param buffer the buffer to read from
     * @return a list of the parsed {@code ScoresBeatmapModel} entries, in file order
     */
    private List<ScoresBeatmapModel> readScoresBeatmap(int beatmapCount, ByteBuffer buffer) {
        List<ScoresBeatmapModel> beatmaps = new ArrayList<>(beatmapCount);

        for  (int i = 0; i < beatmapCount; i++) {
            var beatmapBuilder = ScoresBeatmapModel.builder();
            beatmapBuilder.setBeatmapMD5(readString(buffer));
            int scoresCount = buffer.getInt();
            beatmapBuilder.setScoresNumber(scoresCount);

            List<ScoreModel> scores = new ArrayList<>(scoresCount);

            for (int j = 0; j < scoresCount; j++) {
                scores.add(readScore(buffer));
            }
            beatmapBuilder.setScores(scores);

            beatmaps.add(beatmapBuilder.build());
        }

        return beatmaps;
    }

    /**
     * Reads a single score entry from the buffer
     * and builds the corresponding {@code ScoreModel}
     * @param buffer the buffer to ream from
     * @return the parsed {@code ScoreModel} for this entry
     */
    private ScoreModel readScore(ByteBuffer buffer) {
        var scoreBuilder = ScoreModel.builder();

        scoreBuilder.setGameplayMod(GameplayMod.fromValue(buffer.get()));
        scoreBuilder.setScoreVersion(buffer.getInt());
        scoreBuilder.setBeatmapMD5(readString(buffer));
        scoreBuilder.setPlayerName(readString(buffer));
        scoreBuilder.setReplayMD5(readString(buffer));
        scoreBuilder.setN300(buffer.getShort());
        scoreBuilder.setN100(buffer.getShort());
        scoreBuilder.setN50(buffer.getShort());
        scoreBuilder.setnGekis(buffer.getShort());
        scoreBuilder.setnKatus(buffer.getShort());
        scoreBuilder.setnMiss(buffer.getShort());
        scoreBuilder.setReplayScore(buffer.getInt());
        scoreBuilder.setMaxCombo(buffer.getShort());
        scoreBuilder.setPerfectCombo(readBoolean(buffer));
        var mods = ModParser.parse(buffer.getInt());
        scoreBuilder.setMods(mods);
        readString(buffer);
        scoreBuilder.setReplayTimestamp(buffer.getLong());
        buffer.getInt();
        scoreBuilder.setScoreId(buffer.getLong());
        if (mods.contains(Mods.TARGET_PRACTICE)) {
            scoreBuilder.setAdditionalModInfo(buffer.getDouble());
        }

        return scoreBuilder.build();
    }
}

package dev.osureader.reader;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.Mods;
import dev.osureader.model.replay.ReplayModel;

import dev.osureader.utils.ModParser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReplayReader extends AbstractReader<ReplayModel> {
    /**
     * Creates a new instance of the reader with the specified file path
     *
     * @param filePath the path to the .osr file to be read
     */
    public ReplayReader(Path filePath) {
        super(filePath);
    }

    /**
     * Reads the entire .osr file into memory and parses
     * it into an {@code ReplayModel}
     * @return the parsed {@code ReplayModel} representing the contents of the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    @Override
    public ReplayModel read() throws IOException {
        var replayBuilder = ReplayModel.builder();

        byte[] bytes = Files.readAllBytes(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        replayBuilder.setGameplayMod(GameplayMod.fromValue(buffer.get()));
        replayBuilder.setGameVersion(buffer.getInt());
        replayBuilder.setBeatmapMD5(readString(buffer));
        replayBuilder.setPlayerName(readString(buffer));
        replayBuilder.setReplayMD5(readString(buffer));
        replayBuilder.setN300(buffer.getShort());
        replayBuilder.setN100(buffer.getShort());
        replayBuilder.setN50(buffer.getShort());
        replayBuilder.setnGekis(buffer.getShort());
        replayBuilder.setnKatus(buffer.getShort());
        replayBuilder.setnMiss(buffer.getShort());
        replayBuilder.setTotalScore(buffer.getInt());
        replayBuilder.setMaxCombo(buffer.getShort());
        replayBuilder.setPerfectCombo(readBoolean(buffer));
        var mods = ModParser.parse(buffer.getInt());
        replayBuilder.setMods(mods);
        replayBuilder.setLifeBarGraph(readString(buffer));
        replayBuilder.setTimestamp(buffer.getLong());

        int compressedReplayDataLength = buffer.getInt();
        replayBuilder.setCompressedReplayDataLength(compressedReplayDataLength);

        byte[] compressedReplayData = new byte[compressedReplayDataLength];
        for  (int i = 0; i < compressedReplayDataLength; i++) {
            compressedReplayData[i] = buffer.get();
        }
        replayBuilder.setCompressedReplayData(compressedReplayData);

        replayBuilder.setScoreId(buffer.getLong());
        if (mods.contains(Mods.TARGET_PRACTICE)) {
            replayBuilder.setAdditionalModInfo(buffer.getDouble());
        }

        return replayBuilder.build();
    }
}

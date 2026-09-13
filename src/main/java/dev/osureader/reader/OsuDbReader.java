package dev.osureader.reader;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.MapRankStatus;
import dev.osureader.enums.UserPermissions;
import dev.osureader.model.osuDb.OsuBeatmapModel;
import dev.osureader.model.osuDb.OsuDbModel;
import dev.osureader.model.TimingPointModel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader implementation that parses the osu!.db file of the stable osu!
 * client into an {@code OsuDbModel}.
 * Extends {@code AbstractReader<OsuDbModel>} and reuses its primitive-reading
 * helpers (strings, booleans, ULEB128 lengths, int-float pairs)
 * to decode the file's binary layout
 */
public class OsuDbReader extends AbstractReader<OsuDbModel> {

    /**
     * Creates a new osu!.db reader for the specified file path
     * @param filePath the path to the osu!.db file to be read
     */
    public OsuDbReader(Path filePath) {
        super(filePath);
    }

    /**
     * Reads the entire osu!.db file into memory and parses
     * it into an {@code OsuDbModel}
     * @return the parsed {@code OsuDbModel} representing the contents of the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    @Override
    public OsuDbModel read() throws IOException {
        byte[] dbBytes = Files.readAllBytes(filePath);
        ByteBuffer buffer = ByteBuffer.wrap(dbBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        var osuDbModelBuilder = OsuDbModel.builder();

        osuDbModelBuilder.setClientVersion(buffer.getInt());
        osuDbModelBuilder.setFolderCount(buffer.getInt());
        osuDbModelBuilder.setAccountUnlocked(buffer.get() == 1);
        osuDbModelBuilder.setUnlockAt(buffer.getLong());
        osuDbModelBuilder.setName(readString(buffer));

        int beatmapNumber = buffer.getInt();

        osuDbModelBuilder.setBeatmapNumber(beatmapNumber);

        List<OsuBeatmapModel> beatmaps = new ArrayList<>(beatmapNumber);
        for (int i = 0; i < beatmapNumber; i++) {
            beatmaps.add(readOsuDbBeatmap(buffer));
        }
        osuDbModelBuilder.setBeatmaps(beatmaps);

        var permissions = UserPermissions.fromValue(buffer.getInt());
        osuDbModelBuilder.setUserPermissions(permissions);

        return osuDbModelBuilder.build();
    }

    /**
     * Reads a single beatmap entry from the buffer
     * and builds the corresponding {@code OsuBeatmapModel}
     * @param buffer the buffer positioned at the start of a beatmap entry
     * @return the parsed {@code OsuBeatmapModel} for this entry
     */
    private OsuBeatmapModel readOsuDbBeatmap(ByteBuffer buffer) {
        var dbBeatmapBuilder = OsuBeatmapModel.builder();

        dbBeatmapBuilder.setArtistName(readString(buffer));
        dbBeatmapBuilder.setArtistNameUnicode(readString(buffer));
        dbBeatmapBuilder.setSongTitle(readString(buffer));
        dbBeatmapBuilder.setSongTitleUnicode(readString(buffer));
        dbBeatmapBuilder.setCreatorName(readString(buffer));
        dbBeatmapBuilder.setDifficulty(readString(buffer));
        dbBeatmapBuilder.setAudioFileName(readString(buffer));
        dbBeatmapBuilder.setBeatmapMD5(readString(buffer));
        dbBeatmapBuilder.setFileName(readString(buffer));
        dbBeatmapBuilder.setMapRankStatus(MapRankStatus.fromValue(buffer.get()));
        dbBeatmapBuilder.setHitcirclesNumber(buffer.getShort());
        dbBeatmapBuilder.setSlidersNumber(buffer.getShort());
        dbBeatmapBuilder.setSpinnersNumber(buffer.getShort());
        dbBeatmapBuilder.setLastModificationTime(buffer.getLong());
        dbBeatmapBuilder.setApproachRate(buffer.getFloat());
        dbBeatmapBuilder.setCircleSize(buffer.getFloat());
        dbBeatmapBuilder.setHpDrain(buffer.getFloat());
        dbBeatmapBuilder.setOverallDifficulty(buffer.getFloat());
        dbBeatmapBuilder.setSliderVelocity(buffer.getDouble());
        dbBeatmapBuilder.setStdStarRatings(readIntFloatPair(buffer));
        dbBeatmapBuilder.setTaikoStarRatings(readIntFloatPair(buffer));
        dbBeatmapBuilder.setCatchStarRatings(readIntFloatPair(buffer));
        dbBeatmapBuilder.setManiaStarRatings(readIntFloatPair(buffer));
        dbBeatmapBuilder.setDrainTime(buffer.getInt());
        dbBeatmapBuilder.setTotalTime(buffer.getInt());
        dbBeatmapBuilder.setAudioPreviewTime(buffer.getInt());
        var timingPointCount = buffer.getInt();
        dbBeatmapBuilder.setTimingPoints(readTimingPoints(timingPointCount, buffer));
        dbBeatmapBuilder.setDifficultyId(buffer.getInt());
        dbBeatmapBuilder.setBeatmapId(buffer.getInt());
        dbBeatmapBuilder.setThreadId(buffer.getInt());
        dbBeatmapBuilder.setStandardGrade(buffer.get());
        dbBeatmapBuilder.setTaikoGrade(buffer.get());
        dbBeatmapBuilder.setCatchGrade(buffer.get());
        dbBeatmapBuilder.setManiaGrade(buffer.get());
        dbBeatmapBuilder.setLocalOffset(buffer.getShort());
        dbBeatmapBuilder.setStackLeniency(buffer.getFloat());
        dbBeatmapBuilder.setGameplayMode(GameplayMod.fromValue(buffer.get()));
        dbBeatmapBuilder.setSongSource(readString(buffer));
        dbBeatmapBuilder.setSongTags(readString(buffer));
        dbBeatmapBuilder.setOnlineOffset(buffer.getShort());
        dbBeatmapBuilder.setTitleFont(readString(buffer));
        dbBeatmapBuilder.setUnplayed(readBoolean(buffer));
        dbBeatmapBuilder.setLastPlayedTime(buffer.getLong());
        dbBeatmapBuilder.setOsz2(readBoolean(buffer));
        dbBeatmapBuilder.setFolderName(readString(buffer));
        dbBeatmapBuilder.setLastCheckedTime(buffer.getLong());
        dbBeatmapBuilder.setIgnoreBeatmapSound(readBoolean(buffer));
        dbBeatmapBuilder.setIgnoreBeatmapSkin(readBoolean(buffer));
        dbBeatmapBuilder.setDisableStoryboard(readBoolean(buffer));
        dbBeatmapBuilder.setDisableVideo(readBoolean(buffer));
        dbBeatmapBuilder.setVisualOverride(readBoolean(buffer));
        dbBeatmapBuilder.setLastModificationTime_UNKNOW(buffer.getInt());
        dbBeatmapBuilder.setManiaScrollSpeed(buffer.get());

        return dbBeatmapBuilder.build();
    }

    /**
     * Reads the specified number of timing point entries from the buffer
     * @param pointsCount the number of timing points to read
     * @param buffer the buffer to read from
     * @return a list of the parsed {@code TimingPointModel} entries, in file order
     */
    private List<TimingPointModel> readTimingPoints(int pointsCount, ByteBuffer buffer) {
        List<TimingPointModel> timingPoints = new ArrayList<>(pointsCount);

        for (int i = 0; i < pointsCount; i++) {
            var builder = TimingPointModel.builder();
            builder.setBpm(buffer.getDouble());
            builder.setOffset(buffer.getDouble());
            builder.setIsInherited(readBoolean(buffer));

            timingPoints.add(builder.build());
        }

        return timingPoints;
    }
}

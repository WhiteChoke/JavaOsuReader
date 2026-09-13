package dev.osureader.model.osuDb;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.MapRankStatus;
import dev.osureader.model.TimingPointModel;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public record OsuBeatmapModel(
        String artistName,
        String artistNameUnicode,
        String songTitle,
        String songTitleUnicode,
        String creatorName,
        String difficulty,
        String audioFileName,
        String beatmapMD5,
        String fileName,
        MapRankStatus mapRankStatus,
        short hitcirclesNumber,
        short slidersNumber,
        short spinnersNumber,
        long lastModificationTime,
        float approachRate,
        float circleSize,
        float hpDrain,
        float overallDifficulty,
        double sliderVelocity,
        Map<Integer, Float> stdStarRatings,
        Map<Integer, Float> taikoStarRatings,
        Map<Integer, Float> catchStarRatings,
        Map<Integer, Float> maniaStarRatings,
        int drainTime,
        int totalTime,
        int AudioPreviewTime,
        List<TimingPointModel> timingPoints,
        int difficultyId,
        int beatmapId,
        int threadId,
        byte standardGrade,
        byte taikoGrade,
        byte catchGrade,
        byte maniaGrade,
        short localOffset,
        float stackLeniency,
        GameplayMod gameplayMode,
        String songSource,
        String songTags,
        short onlineOffset,
        String titleFont,
        boolean unplayed,
        long lastPlayedTime,
        boolean osz2,
        String folderName,
        long lastCheckedTime,
        boolean ignoreBeatmapSound,
        boolean ignoreBeatmapSkin,
        boolean disableStoryboard,
        boolean disableVideo,
        boolean visualOverride,
        int lastModificationTime_UNKNOW,
        byte maniaScrollSpeed
) {


    public static Builder builder() {
        return new Builder();
    }

    public OsuBeatmapModel(Builder builder) {
        this(
                builder.artistName,
                builder.artistNameUnicode,
                builder.songTitle,
                builder.songTitleUnicode,
                builder.creatorName,
                builder.difficulty,
                builder.audioFileName,
                builder.beatmapMD5,
                builder.fileName,
                builder.mapRankStatus,
                builder.hitcirclesNumber,
                builder.slidersNumber,
                builder.spinnersNumber,
                builder.lastModificationTime,
                builder.approachRate,
                builder.circleSize,
                builder.hpDrain,
                builder.overallDifficulty,
                builder.sliderVelocity,
                builder.stdStarRatings,
                builder.taikoStarRatings,
                builder.catchStarRatings,
                builder.maniaStarRatings,
                builder.drainTime,
                builder.totalTime,
                builder.audioPreviewTime,
                builder.timingPoints,
                builder.difficultyId,
                builder.beatmapId,
                builder.threadId,
                builder.standardGrade,
                builder.taikoGrade,
                builder.catchGrade,
                builder.maniaGrade,
                builder.localOffset,
                builder.stackLeniency,
                builder.gameplayMode,
                builder.songSource,
                builder.songTags,
                builder.onlineOffset,
                builder.titleFont,
                builder.unplayed,
                builder.lastPlayedTime,
                builder.osz2,
                builder.folderName,
                builder.lastCheckedTime,
                builder.ignoreBeatmapSound,
                builder.ignoreBeatmapSkin,
                builder.disableStoryboard,
                builder.disableVideo,
                builder.visualOverride,
                builder.lastModificationTime_UNKNOW,
                builder.maniaScrollSpeed
        );
    }

    public static class Builder{
        String artistName;
        String artistNameUnicode;
        String songTitle;
        String songTitleUnicode;
        String creatorName;
        String difficulty;
        String audioFileName;
        String beatmapMD5;
        String fileName;
        MapRankStatus mapRankStatus;
        short hitcirclesNumber;
        short slidersNumber;
        short spinnersNumber;
        long lastModificationTime;
        float approachRate;
        float circleSize;
        float hpDrain;
        float overallDifficulty;
        double sliderVelocity;
        Map<Integer, Float> stdStarRatings;
        Map<Integer, Float> taikoStarRatings;
        Map<Integer, Float> catchStarRatings;
        Map<Integer, Float> maniaStarRatings;
        int drainTime;
        int totalTime;
        int audioPreviewTime;
        List<TimingPointModel> timingPoints;
        int difficultyId;
        int beatmapId;
        int threadId;
        byte standardGrade;
        byte taikoGrade;
        byte catchGrade;
        byte maniaGrade;
        short localOffset;
        float stackLeniency;
        GameplayMod gameplayMode;
        String songSource;
        String songTags;
        short onlineOffset;
        String titleFont;
        boolean unplayed;
        long lastPlayedTime;
        boolean osz2;
        String folderName;
        long lastCheckedTime;
        boolean ignoreBeatmapSound;
        boolean ignoreBeatmapSkin;
        boolean disableStoryboard;
        boolean disableVideo;
        boolean visualOverride;
        int lastModificationTime_UNKNOW;
        byte maniaScrollSpeed;

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public void setArtistNameUnicode(String artistNameUnicode) {
            this.artistNameUnicode = artistNameUnicode;
        }

        public void setSongTitle(String songTitle) {
            this.songTitle = songTitle;
            
        }

        public void setSongTitleUnicode(String songTitleUnicode) {
            this.songTitleUnicode = songTitleUnicode;
            
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public void setAudioFileName(String audioFileName) {
            this.audioFileName = audioFileName;
        }

        public void setBeatmapMD5(String beatmapMD5) {
            this.beatmapMD5 = beatmapMD5;
            
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
            
        }

        public void setMapRankStatus(MapRankStatus mapRankStatus) {
            this.mapRankStatus = mapRankStatus;
            
        }

        public void setHitcirclesNumber(short hitcirclesNumber) {
            this.hitcirclesNumber = hitcirclesNumber;
            
        }

        public void setSlidersNumber(short slidersNumber) {
            this.slidersNumber = slidersNumber;
            
        }

        public void setSpinnersNumber(short spinnersNumber) {
            this.spinnersNumber = spinnersNumber;
            
        }

        public void setLastModificationTime(long lastModificationTime) {
            this.lastModificationTime = lastModificationTime;
            
        }

        public void setApproachRate(float approachRate) {
            this.approachRate = approachRate;
            
        }

        public void setCircleSize(float circleSize) {
            this.circleSize = circleSize;
            
        }

        public void setHpDrain(float hpDrain) {
            this.hpDrain = hpDrain;
            
        }

        public void setOverallDifficulty(float overallDifficulty) {
            this.overallDifficulty = overallDifficulty;
            
        }

        public void setSliderVelocity(double sliderVelocity) {
            this.sliderVelocity = sliderVelocity;
            
        }

        public void setStdStarRatings(Map<Integer, Float> stdStarRatings) {
            this.stdStarRatings = stdStarRatings;
            
        }

        public void setTaikoStarRatings(Map<Integer, Float> taikoStarRatings) {
            this.taikoStarRatings = taikoStarRatings;
            
        }

        public void setCatchStarRatings(Map<Integer, Float> catchStarRatings) {
            this.catchStarRatings = catchStarRatings;
            
        }

        public void setManiaStarRatings(Map<Integer, Float> maniaStarRatings) {
            this.maniaStarRatings = maniaStarRatings;
            
        }

        public void setDrainTime(int drainTime) {
            this.drainTime = drainTime;
            
        }

        public void setTotalTime(int totalTime) {
            this.totalTime = totalTime;
            
        }

        public void setAudioPreviewTime(int audioPreviewTime) {
            this.audioPreviewTime = audioPreviewTime;
            
        }

        public void setTimingPoints(List<TimingPointModel> timingPoints) {
            this.timingPoints = timingPoints;
            
        }

        public void setDifficultyId(int difficultyId) {
            this.difficultyId = difficultyId;
            
        }

        public void setBeatmapId(int beatmapId) {
            this.beatmapId = beatmapId;
            
        }

        public void setThreadId(int threadId) {
            this.threadId = threadId;
            
        }

        public void setStandardGrade(byte standardGrade) {
            this.standardGrade = standardGrade;
            
        }

        public void setTaikoGrade(byte taikoGrade) {
            this.taikoGrade = taikoGrade;
            
        }

        public void setCatchGrade(byte catchGrade) {
            this.catchGrade = catchGrade;
            
        }

        public void setManiaGrade(byte maniaGrade) {
            this.maniaGrade = maniaGrade;
            
        }

        public void setLocalOffset(short localOffset) {
            this.localOffset = localOffset;
            
        }

        public void setStackLeniency(float stackLeniency) {
            this.stackLeniency = stackLeniency;
            
        }

        public void setGameplayMode(GameplayMod gameplayMode) {
            this.gameplayMode = gameplayMode;
            
        }

        public void setSongSource(String songSource) {
            this.songSource = songSource;
            
        }

        public void setSongTags(String songTags) {
            this.songTags = songTags;
            
        }

        public void setOnlineOffset(short onlineOffset) {
            this.onlineOffset = onlineOffset;
            
        }

        public void setTitleFont(String titleFont) {
            this.titleFont = titleFont;
            
        }

        public void setUnplayed(boolean unplayed) {
            this.unplayed = unplayed;
            
        }

        public void setLastPlayedTime(long lastPlayedTime) {
            this.lastPlayedTime = lastPlayedTime;
            
        }

        public void setOsz2(boolean osz2) {
            this.osz2 = osz2;
            
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
            
        }

        public void setLastCheckedTime(long lastCheckedTime) {
            this.lastCheckedTime = lastCheckedTime;
            
        }

        public void setIgnoreBeatmapSound(boolean ignoreBeatmapSound) {
            this.ignoreBeatmapSound = ignoreBeatmapSound;
            
        }

        public void setIgnoreBeatmapSkin(boolean ignoreBeatmapSkin) {
            this.ignoreBeatmapSkin = ignoreBeatmapSkin;
            
        }

        public void setDisableStoryboard(boolean disableStoryboard) {
            this.disableStoryboard = disableStoryboard;
            
        }

        public void setDisableVideo(boolean disableVideo) {
            this.disableVideo = disableVideo;
            
        }

        public void setVisualOverride(boolean visualOverride) {
            this.visualOverride = visualOverride;
            
        }

        public void setLastModificationTime_UNKNOW(int lastModificationTime_UNKNOW) {
            this.lastModificationTime_UNKNOW = lastModificationTime_UNKNOW;
            
        }

        public void setManiaScrollSpeed(byte maniaScrollSpeed) {
            this.maniaScrollSpeed = maniaScrollSpeed;
        }

        public OsuBeatmapModel build() {
            return new OsuBeatmapModel(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OsuBeatmapModel.class.getSimpleName() + "[", "]")
                .add("artistName='" + artistName + "'")
                .add("artistNameUnicode='" + artistNameUnicode + "'")
                .add("songTitle='" + songTitle + "'")
                .add("songTitleUnicode='" + songTitleUnicode + "'")
                .add("creatorName='" + creatorName + "'")
                .add("difficulty='" + difficulty + "'")
                .add("audioFileName='" + audioFileName + "'")
                .add("beatmapMD5='" + beatmapMD5 + "'")
                .add("fileName='" + fileName + "'")
                .add("mapRankStatus=" + mapRankStatus)
                .add("hitcirclesNumber=" + hitcirclesNumber)
                .add("slidersNumber=" + slidersNumber)
                .add("spinnersNumber=" + spinnersNumber)
                .add("lastModificationTime=" + lastModificationTime)
                .add("approachRate=" + approachRate)
                .add("circleSize=" + circleSize)
                .add("hpDrain=" + hpDrain)
                .add("overallDifficulty=" + overallDifficulty)
                .add("sliderVelocity=" + sliderVelocity)
                .add("stdStarRatings=" + stdStarRatings)
                .add("taikoStarRatings=" + taikoStarRatings)
                .add("catchStarRatings=" + catchStarRatings)
                .add("maniaStarRatings=" + maniaStarRatings)
                .add("drainTime=" + drainTime)
                .add("totalTime=" + totalTime)
                .add("AudioPreviewTime=" + AudioPreviewTime)
                .add("timingPoints=" + timingPoints)
                .add("difficultyId=" + difficultyId)
                .add("beatmapId=" + beatmapId)
                .add("threadId=" + threadId)
                .add("standardGrade=" + standardGrade)
                .add("taikoGrade=" + taikoGrade)
                .add("catchGrade=" + catchGrade)
                .add("maniaGrade=" + maniaGrade)
                .add("localOffset=" + localOffset)
                .add("stackLeniency=" + stackLeniency)
                .add("gameplayMode=" + gameplayMode)
                .add("songSource='" + songSource + "'")
                .add("songTags='" + songTags + "'")
                .add("onlineOffset=" + onlineOffset)
                .add("titleFont='" + titleFont + "'")
                .add("unplayed=" + unplayed)
                .add("lastPlayedTime=" + lastPlayedTime)
                .add("osz2=" + osz2)
                .add("folderName='" + folderName + "'")
                .add("lastCheckedTime=" + lastCheckedTime)
                .add("ignoreBeatmapSound=" + ignoreBeatmapSound)
                .add("ignoreBeatmapSkin=" + ignoreBeatmapSkin)
                .add("disableStoryboard=" + disableStoryboard)
                .add("disableVideo=" + disableVideo)
                .add("visualOverride=" + visualOverride)
                .add("lastModificationTime_UNKNOW=" + lastModificationTime_UNKNOW)
                .add("maniaScrollSpeed=" + maniaScrollSpeed)
                .toString();
    }
}

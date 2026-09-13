package dev.osureader.model.scoresDb;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.Mods;

import java.util.List;
import java.util.StringJoiner;

public record ScoreModel(
    GameplayMod gameplayMod,
    int scoreVersion,
    String beatmapMD5,
    String playerName,
    String replayMD5,
    short n300,
    short n100,
    short n50,
    short nGekis,
    short nKatus,
    short nMiss,
    int replayScore,
    short maxCombo,
    boolean perfectCombo,
    List<Mods> mods_REDO,
    String EMPTY_STRING,
    Long replayTimestamp,
    int EMPTY_INT,
    long scoreId,
    double additionalModInfo
) {
    public static Builder builder() {
        return new Builder();
    }

    private ScoreModel(Builder builder) {
        this(
                builder.gameplayMod,
                builder.scoreVersion,
                builder.beatmapMD5,
                builder.playerName,
                builder.replayMD5,
                builder.n300,
                builder.n100,
                builder.n50,
                builder.nGekis,
                builder.nKatus,
                builder.nMiss,
                builder.replayScore,
                builder.maxCombo,
                builder.perfectCombo,
                builder.mods,
                builder.EMPTY_STRING,
                builder.replayTimestamp,
                builder.EMPTY_INT,
                builder.scoreId,
                builder.additionalModInfo
        );

    }

    public static class Builder {
        private GameplayMod gameplayMod;
        private int scoreVersion;
        private String beatmapMD5;
        private String playerName;
        private String replayMD5;
        private short n300;
        private short n100;
        private short n50;
        private short nGekis;
        private short nKatus;
        private short nMiss;
        private int replayScore;
        private short maxCombo;
        private boolean perfectCombo;
        private List<Mods> mods;
        private String EMPTY_STRING;
        private Long replayTimestamp;
        private int EMPTY_INT;
        private long scoreId;
        private double additionalModInfo;

        public ScoreModel build() {
            return new ScoreModel(this);
        }

        public void setGameplayMod(GameplayMod gameplayMod) {
            this.gameplayMod = gameplayMod;
        }

        public void setScoreVersion(int scoreVersion) {
            this.scoreVersion = scoreVersion;
        }

        public void setBeatmapMD5(String beatmapMD5) {
            this.beatmapMD5 = beatmapMD5;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public void setReplayMD5(String replayMD5) {
            this.replayMD5 = replayMD5;
        }

        public void setN300(short n300) {
            this.n300 = n300;
        }

        public void setN100(short n100) {
            this.n100 = n100;
        }

        public void setN50(short n50) {
            this.n50 = n50;
        }

        public void setnGekis(short nGekis) {
            this.nGekis = nGekis;
        }

        public void setnKatus(short nKatus) {
            this.nKatus = nKatus;
        }

        public void setnMiss(short nMiss) {
            this.nMiss = nMiss;
        }

        public void setReplayScore(int replayScore) {
            this.replayScore = replayScore;
        }

        public void setMaxCombo(short maxCombo) {
            this.maxCombo = maxCombo;
        }

        public void setPerfectCombo(boolean perfectCombo) {
            this.perfectCombo = perfectCombo;
        }

        public void setMods(List<Mods> mods) {
            this.mods = mods;
        }

        public void setReplayTimestamp(Long replayTimestamp) {
            this.replayTimestamp = replayTimestamp;
        }

        public void setScoreId(long scoreId) {
            this.scoreId = scoreId;
        }

        public void setAdditionalModInfo(double additionalModInfo) {
            this.additionalModInfo = additionalModInfo;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScoreModel.class.getSimpleName() + "[", "]")
                .add("gameplayMod=" + gameplayMod)
                .add("scoreVersion=" + scoreVersion)
                .add("beatmapMD5='" + beatmapMD5 + "'")
                .add("playerName='" + playerName + "'")
                .add("replayMD5='" + replayMD5 + "'")
                .add("n300=" + n300)
                .add("n100=" + n100)
                .add("n50=" + n50)
                .add("nGekis=" + nGekis)
                .add("nKatus=" + nKatus)
                .add("nMiss=" + nMiss)
                .add("replayScore=" + replayScore)
                .add("maxCombo=" + maxCombo)
                .add("perfectCombo=" + perfectCombo)
                .add("mods_REDO=" + mods_REDO)
                .add("EMPTY_STRING='" + EMPTY_STRING + "'")
                .add("replayTimestamp=" + replayTimestamp)
                .add("EMPTY_INT=" + EMPTY_INT)
                .add("scoreId=" + scoreId)
                .add("additionalModInfo=" + additionalModInfo)
                .toString();
    }
}

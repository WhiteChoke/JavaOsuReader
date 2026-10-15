package dev.osureader.model.replay;

import dev.osureader.enums.GameplayMod;
import dev.osureader.enums.Mods;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public record ReplayModel(
        GameplayMod gameplayMod,
        int gameVersion,
        String beatmapMD5,
        String playerName,
        String replayMD5,
        short n300,
        short n100,
        short n50,
        short nGekis,
        short nKatus,
        short nMiss,
        int totalScore,
        short maxCombo,
        boolean perfectCombo,
        List<Mods> mods,
        String lifeBarGraph,
        Long timestamp,
        int compressedReplayDataLength,
        byte[] compressedReplayData,
        long scoreId,
        double additionalModInfo
) {
    public static Builder builder() {
        return new Builder();
    }

    private ReplayModel(Builder builder) {
        this (
                builder.gameplayMod,
                builder.gameVersion,
                builder.beatmapMD5,
                builder.playerName,
                builder.replayMD5,
                builder.n300,
                builder.n100,
                builder.n50,
                builder.nGekis,
                builder.nKatus,
                builder.nMiss,
                builder.totalScore,
                builder.maxCombo,
                builder.perfectCombo,
                builder.mods,
                builder.lifeBarGraph,
                builder.timestamp,
                builder.compressedReplayDataLength,
                builder.compressedReplayData,
                builder.scoreId,
                builder.additionalModInfo
        );
    }

    public static class Builder {
        private GameplayMod gameplayMod;
        private int gameVersion;
        private String beatmapMD5;
        private String playerName;
        private String replayMD5;
        private short n300;
        private short n100;
        private short n50;
        private short nGekis;
        private short nKatus;
        private short nMiss;
        private int totalScore;
        private short maxCombo;
        private boolean perfectCombo;
        private List<Mods> mods;
        private String lifeBarGraph;
        private Long timestamp;
        private int compressedReplayDataLength;
        private byte[] compressedReplayData;
        private long scoreId;
        private double additionalModInfo;

        public void setGameplayMod(GameplayMod gameplayMod) {
            this.gameplayMod = gameplayMod;
        }

        public void setGameVersion(int gameVersion) {
            this.gameVersion = gameVersion;
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

        public void setTotalScore(int totalScore) {
            this.totalScore = totalScore;
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

        public void setLifeBarGraph(String lifeBarGraph) {
            this.lifeBarGraph = lifeBarGraph;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public void setCompressedReplayDataLength(int compressedReplayDataLength) {
            this.compressedReplayDataLength = compressedReplayDataLength;
        }

        public void setCompressedReplayData(byte[] compressedReplayData) {
            this.compressedReplayData = compressedReplayData;
        }

        public void setScoreId(long scoreId) {
            this.scoreId = scoreId;

        }

        public void setAdditionalModInfo(double additionalModInfo) {
            this.additionalModInfo = additionalModInfo;
        }

        public ReplayModel build() {
            return new ReplayModel(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReplayModel.class.getSimpleName() + "[", "]")
                .add("gameplayMod=" + gameplayMod)
                .add("gameVersion=" + gameVersion)
                .add("beatmapMD5='" + beatmapMD5 + "'")
                .add("playerName='" + playerName + "'")
                .add("replayMD5='" + replayMD5 + "'")
                .add("n300=" + n300)
                .add("n100=" + n100)
                .add("n50=" + n50)
                .add("nGekis=" + nGekis)
                .add("nKatus=" + nKatus)
                .add("nMiss=" + nMiss)
                .add("totalScore=" + totalScore)
                .add("maxCombo=" + maxCombo)
                .add("perfectCombo=" + perfectCombo)
                .add("mods=" + mods)
                .add("lifeBarGraph='" + lifeBarGraph + "'")
                .add("timestamp=" + timestamp)
                .add("compressedReplayDataLength=" + compressedReplayDataLength)
                .add("compressedReplayData=" + Arrays.toString(compressedReplayData))
                .add("scoreId=" + scoreId)
                .add("additionalModInfo=" + additionalModInfo)
                .toString();
    }
}

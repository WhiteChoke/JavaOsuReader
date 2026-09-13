package dev.osureader.model.scoresDb;

import java.util.List;
import java.util.StringJoiner;

public record ScoresBeatmapModel(
        String beatmapMD5,
        int scoresNumber,
        List<ScoreModel> scores) {
    private ScoresBeatmapModel(Builder builder) {
        this(
                builder.beatmapMD5,
                builder.scoresNumber,
                builder.scores);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String beatmapMD5;
        private int scoresNumber;
        private List<ScoreModel> scores;

        public ScoresBeatmapModel build() {
            return new ScoresBeatmapModel(this);
        }

        public void setBeatmapMD5(String beatmapMD5) {
            this.beatmapMD5 = beatmapMD5;
        }

        public void setScoresNumber(int scoresNumber) {
            this.scoresNumber = scoresNumber;
        }

        public void setScores(List<ScoreModel> scores) {
            this.scores = scores;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScoresBeatmapModel.class.getSimpleName() + "[", "]")
                .add("beatmapMD5='" + beatmapMD5 + "'")
                .add("scoresNumber=" + scoresNumber)
                .add("scores=" + scores)
                .toString();
    }
}

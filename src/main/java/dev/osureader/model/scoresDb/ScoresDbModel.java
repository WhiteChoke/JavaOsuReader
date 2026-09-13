package dev.osureader.model.scoresDb;

import java.util.List;
import java.util.StringJoiner;

public record ScoresDbModel(
    int version,
    int beatmapNumber,
    List<ScoresBeatmapModel> beatmaps
) {
    private ScoresDbModel(Builder builder) {
        this (
                builder.version,
                builder.beatmapNumber,
                builder.beatmaps
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int version;
        private int beatmapNumber;
        private List<ScoresBeatmapModel> beatmaps;

        public ScoresDbModel build() {
            return new ScoresDbModel(this);
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setBeatmapNumber(int beatmapNumber) {
            this.beatmapNumber = beatmapNumber;
        }

        public void setBeatmaps(List<ScoresBeatmapModel> beatmaps) {
            this.beatmaps = beatmaps;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScoresDbModel.class.getSimpleName() + "[", "]")
                .add("version=" + version)
                .add("beatmapNumber=" + beatmapNumber)
                .add("beatmaps=" + beatmaps)
                .toString();
    }
}

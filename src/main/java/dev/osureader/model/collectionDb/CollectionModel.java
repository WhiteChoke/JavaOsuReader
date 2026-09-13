package dev.osureader.model.collectionDb;

import java.util.List;
import java.util.StringJoiner;

public record CollectionModel(
        String name,
        int beatmapsNumber,
        List<String> beatmapsMD5
) {
    private CollectionModel(Builder builder) {
        this (
                builder.name,
                builder.beatmapsNumber,
                builder.beatmapsMD5
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int beatmapsNumber;
        private List<String> beatmapsMD5;

        public CollectionModel build() {
            return new CollectionModel(this);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBeatmapsNumber(int beatmapsNumber) {
            this.beatmapsNumber = beatmapsNumber;
        }

        public void setBeatmapsMD5(List<String> beatmapsMD5) {
            this.beatmapsMD5 = beatmapsMD5;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CollectionModel.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("beatmapsNumber=" + beatmapsNumber)
                .add("beatmapsMD5=" + beatmapsMD5)
                .toString();
    }
}

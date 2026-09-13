package dev.osureader.model.collectionDb;

import java.util.List;
import java.util.StringJoiner;

public record CollectionDbModel(
        int version,
        int collectionsNumber,
        List<CollectionModel> collections
) {
    private CollectionDbModel(Builder builder) {
        this (
                builder.version,
                builder.collectionsNumber,
                builder.collections
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int version;
        private int collectionsNumber;
        private List<CollectionModel> collections;

        public CollectionDbModel build() {
            return new CollectionDbModel(this);
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setCollectionsNumber(int collectionsNumber) {
            this.collectionsNumber = collectionsNumber;
        }

        public void setCollections(List<CollectionModel> collections) {
            this.collections = collections;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CollectionDbModel.class.getSimpleName() + "[", "]")
                .add("version=" + version)
                .add("collectionsNumber=" + collectionsNumber)
                .add("collections=" + collections)
                .toString();
    }
}

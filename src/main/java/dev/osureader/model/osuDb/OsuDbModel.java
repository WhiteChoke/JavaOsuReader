package dev.osureader.model.osuDb;

import dev.osureader.enums.UserPermissions;

import java.util.List;
import java.util.StringJoiner;

public record OsuDbModel(
        int clientVersion,
        int folderCount,
        boolean isAccountUnlocked,
        long unlockAt,
        String name,
        int beatmapNumber,
        List<OsuBeatmapModel> beatmaps,
        UserPermissions userPermissions
) {
    private OsuDbModel(Builder builder) {
        this(
                builder.clientVersion,
                builder.folderCount,
                builder.isAccountUnlocked,
                builder.unlockAt,
                builder.name,
                builder.beatmapNumber,
                builder.beatmaps,
                builder.userPermissions
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int clientVersion;
        private int folderCount;
        private boolean isAccountUnlocked;
        private long unlockAt;
        private String name;
        private int beatmapNumber;
        private List<OsuBeatmapModel> beatmaps;
        private UserPermissions userPermissions;

        public void setUserPermissions(UserPermissions userPermissions) {
            this.userPermissions = userPermissions;
        }

        public void setBeatmaps(List<OsuBeatmapModel> beatmaps) {
            this.beatmaps = beatmaps;
        }

        public void setBeatmapNumber(int beatmapNumber) {
            this.beatmapNumber = beatmapNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnlockAt(long unlockAt) {
            this.unlockAt = unlockAt;
        }

        public void setAccountUnlocked(boolean accountUnlocked) {
            isAccountUnlocked = accountUnlocked;
        }

        public void setFolderCount(int folderCount) {
            this.folderCount = folderCount;
        }

        public void setClientVersion(int clientVersion) {
            this.clientVersion = clientVersion;
        }

        public OsuDbModel build() {
            return new OsuDbModel(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OsuDbModel.class.getSimpleName() + "[", "]")
                .add("clientVersion=" + clientVersion)
                .add("folderCount=" + folderCount)
                .add("isAccountUnlocked=" + isAccountUnlocked)
                .add("unlockAt=" + unlockAt)
                .add("name='" + name + "'")
                .add("beatmapNumber=" + beatmapNumber)
                .add("beatmaps=" + beatmaps)
                .add("userPermissions=" + userPermissions)
                .toString();
    }
}

package dev.osureader.model;

import java.util.StringJoiner;

public record TimingPointModel (
        double bpm,
        double offset,
        boolean isInherited
){
    private TimingPointModel(Builder builder) {
        this (
                builder.bpm,
                builder.offset,
                builder.isInherited
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private double bpm;
        private double offset;
        private boolean isInherited;

        public void setBpm(double bpm) {
            this.bpm = bpm;
        }

        public void setOffset(double offset) {
            this.offset = offset;
        }

        public void setIsInherited(boolean isInherited) {
            this.isInherited = isInherited;
        }

        public TimingPointModel build() {
            return new TimingPointModel(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TimingPointModel.class.getSimpleName() + "[", "]")
                .add("bpm=" + bpm)
                .add("offset=" + offset)
                .add("isInherited=" + isInherited)
                .toString();
    }
}

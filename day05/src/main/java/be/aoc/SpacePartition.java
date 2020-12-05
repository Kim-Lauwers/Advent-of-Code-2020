package be.aoc;

public final class SpacePartition {
    private final int min;
    private final int max;

    public SpacePartition(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    public final SpacePartition takeLowerHalf() {
        if (max - min == 1) {
            return new SpacePartition(min, min);
        }

        return new SpacePartition(min, roundDownMiddle());
    }

    public final SpacePartition takeUpperHalf() {
        if (max - min == 1) {
            return new SpacePartition(max, max);
        }

        return new SpacePartition(roundUpMiddle(), max);
    }

    public final int middle() {
        return middle(false);
    }

    public final int middle(final boolean roundUp) {
        if (roundUp) {
            return roundUpMiddle();
        } else {
            return roundDownMiddle();
        }
    }

    private int roundUpMiddle() {
        return ((min + max) + 1) / 2;
    }

    private int roundDownMiddle() {
        return (min + max) / 2;
    }

    @Override
    public String toString() {
        return "SpacePartition{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
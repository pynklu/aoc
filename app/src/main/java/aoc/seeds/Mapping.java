package aoc.seeds;

import java.util.Objects;

public class Mapping {
    private final Range src;
    private final Range dest;
    public final long delta;

    public Mapping(long source, long destination, long length) {
        this.src = Range.fromLength(source, length);
        this.dest = Range.fromLength(destination, length);
        this.delta = destination - source;
    }

    public Mapping(Range src, Range dest, long delta) {
        this.src = src;
        this.dest = dest;
        this.delta = delta;
    }

    public Mapping(Range src, Range dest) {
        this(src, dest, dest.start - src.start);
    }


    public boolean canMergeWith(Mapping other) {
        return this.dest.overlapsWith(other.src);
    }

    public static Mapping mergeMappings(Mapping from, Mapping to) {
        Range overlap = Range.fromOverlap(from.dest, to.src);
        Range newSource = overlap.applyDelta(-from.delta);
        Range newDestination = overlap.applyDelta(to.delta);
        return new Mapping(newSource, newDestination, from.delta + to.delta);
    }

    public Mapping mergeWith(Mapping other) {
        return mergeMappings(this, other);
    }

    public boolean canMap(long number) {
        return src.contains(number);
    }

    public Range getSrc() {
        return src;
    }

    public Range getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return src.toString() + " -> " + dest.toString() + "   (length: " + src.length + ", delta: " + delta + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mapping mapping = (Mapping) o;
        return delta == mapping.delta && Objects.equals(src, mapping.src) && Objects.equals(dest, mapping.dest);
    }
}

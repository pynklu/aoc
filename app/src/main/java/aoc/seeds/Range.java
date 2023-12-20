package aoc.seeds;

public class Range {
    final long start;
    final long end;
    final long length;

    private Range(long start, long length, long end) {
        this.start = start;
        this.length = length;
        this.end = end;
    }

    public static Range fromLength(long start, long length) {
        var end = length == Long.MAX_VALUE ? Long.MAX_VALUE : start + length - 1;
        return new Range(start, length, end);
    }

    public static Range fromEnd(long start, long end) {
        var length = end == Long.MAX_VALUE ? Long.MAX_VALUE : end - start + 1;
        return new Range(start, length, end);
    }

    public static Range empty() {
        return new Range(0, 0, 0);
    }

    public Range applyDelta(long delta) {
        return new Range(
                start + delta,
                length,
                end == Long.MAX_VALUE ? end : end + delta
        );
    }

    public boolean overlapsWith(Range other) {
        return this.start <= other.end && other.start <= this.end;
    }

    public static Range fromOverlap(Range r1, Range r2) {
        if (!r1.overlapsWith(r2)) return Range.empty();
        var newStart = Math.max(r1.start, r2.start);

        var r1Infinite = r1.end == Long.MAX_VALUE;
        var r2Infinite = r2.end == Long.MAX_VALUE;
        var bothInfinite = r1Infinite && r2Infinite;
        var newEnd = bothInfinite ? Long.MAX_VALUE :
                        r1Infinite ? r2.end :
                        r2Infinite ? r1.end : Math.min(r1.end, r2.end);

        return Range.fromEnd(newStart, newEnd);
    }

    public boolean contains(long number) {
        return start <= number && number <= end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start && end == range.end && length == range.length;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + (end == Long.MAX_VALUE ? "inf" : end) + "]";
    }
}

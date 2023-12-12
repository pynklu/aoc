package aoc.seeds;

public record FromTo(long from, long to, long length, long delta) {
    public FromTo(long from, long to, long length) {
        this(from, to, length, to-from);
    }
}

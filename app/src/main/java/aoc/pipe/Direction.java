package aoc.pipe;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NONE;

    static Direction getInverse(Direction direction) {
        var out = NONE;
        switch (direction) {
            case NORTH -> out = SOUTH;
            case EAST -> out = WEST;
            case SOUTH -> out = NORTH;
            case WEST -> out = EAST;
        }
        return out;
    }
}

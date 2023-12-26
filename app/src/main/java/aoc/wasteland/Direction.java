package aoc.wasteland;

public enum Direction {
    LEFT(0), RIGHT(1);

    private final int value;
    Direction(int value){ this.value = value; }
    public int value() { return value; }
    public static Direction fromChar(char c) {
        return 'L' == c ? LEFT : RIGHT;
    }
}

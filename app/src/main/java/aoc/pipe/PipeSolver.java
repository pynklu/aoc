package aoc.pipe;

import aoc.Solver;
import static aoc.pipe.Direction.*;

import java.util.ArrayList;
import java.util.List;

public class PipeSolver extends Solver {
    private List<List<Point>> grid;
    private Point startingPoint;
    private final List<PointPair> distances = new ArrayList<>();
    public PipeSolver(String filename) {
        try {
            var lines = this.readFileByLine(filename);
            parseInput(lines);
            determineStartingPipe();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void parseInput(List<String> lines) {
        grid = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            var row = new ArrayList<Point>();
            var line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                var pipe = Pipe.fromSign(line.charAt(x));
                var point = new Point(x, y, pipe);
                row.add(point);
                if('S' == line.charAt(x)) this.startingPoint = point;
            } grid.add(row);
        }
    }

    void determineStartingPipe() {
        var directions = new ArrayList<Direction>();
        var x = startingPoint.x();
        var y = startingPoint.y();
        if(getPoint(x, y-1).pipe().opensTo(SOUTH)) directions.add(NORTH);
        if(getPoint(x+1, y).pipe().opensTo(WEST)) directions.add(EAST);
        if(getPoint(x, y+1).pipe().opensTo(NORTH)) directions.add(SOUTH);
        if(getPoint(x-1, y).pipe().opensTo(EAST)) directions.add(WEST);
        startingPoint = new Point(x, y, new Pipe(directions.get(0), directions.get(1)));
    }

    Point getPoint(int x, int y){
        if(
                x < 0 || y < 0 ||
                x >= grid.get(0).size() ||
                y >= grid.size()
        ) {
            return new Point(-1, -1, new Pipe(NONE, NONE));
        }
        return grid.get(y).get(x);
    }

    public int traverseLoop() {
        var d1 = startingPoint.pipe().direction1();
        var p1 = moveTo(startingPoint, d1);
        var d2 = startingPoint.pipe().direction2();
        var p2 = moveTo(startingPoint, d2);
        var distance = 1;
        distances.add(new PointPair(distance, p1));
        distances.add(new PointPair(distance, p2));
        while(p1 != p2) {
            d1 = getInverse(d1) == p1.pipe().direction1() ? p1.pipe().direction2() : p1.pipe().direction1();
            p1 = moveTo(p1, d1);
            if(p1 == p2) break;
            d2 = getInverse(d2) == p2.pipe().direction1() ? p2.pipe().direction2() : p2.pipe().direction1();
            p2 = moveTo(p2, d2);
            distance++;
            distances.add(new PointPair(distance, p1));
            distances.add(new PointPair(distance, p2));
        }
        return distance;
    }

    Point moveTo(Point src, Direction direction) {
        switch (direction){
            case NORTH -> {
                return getPoint(src.x(), src.y() - 1);
            }
            case EAST -> {
                return getPoint(src.x() + 1, src.y());
            }
            case SOUTH -> {
                return getPoint(src.x(), src.y() + 1);
            }
            case WEST -> {
                return getPoint(src.x() - 1, src.y());
            }
        } throw new IllegalArgumentException();
    }
}

record Pipe(Direction direction1, Direction direction2) {
    static Pipe fromSign(char sign) {
        Pipe out;
        switch (sign) {
            case '|' -> out = new Pipe(NORTH, SOUTH);
            case '-' -> out = new Pipe(EAST, WEST);
            case 'L' -> out = new Pipe(NORTH, EAST);
            case 'J' -> out = new Pipe(NORTH, WEST);
            case '7' -> out = new Pipe(WEST, SOUTH);
            case 'F' -> out = new Pipe(EAST, SOUTH);
            default -> out = new Pipe(NONE, NONE);
        }
        return out;
    }

    public boolean opensTo(Direction direction){
        return direction1 == direction || direction2 == direction;
    }
}
record Point(int x, int y, Pipe pipe){}
record PointPair(int distance, Point p){}

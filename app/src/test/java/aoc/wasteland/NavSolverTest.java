package aoc.wasteland;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;
import static aoc.wasteland.Direction.LEFT;
import static aoc.wasteland.Direction.RIGHT;

import static org.assertj.core.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NavSolverTest {
    NavSolver solver = new NavSolver();;

    @Test
    void parseInput1() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day8mock1.txt");
        solver.parseInput(lines);
        var expected = new Direction[]{ RIGHT, LEFT};
        assertThat(solver.getDirections()).containsExactly(expected);
    }

    @Test
    void parseInput2() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day8mock2.txt");
        solver.parseInput(lines);
        var expected = new Direction[]{ LEFT, LEFT, RIGHT};
        assertThat(solver.getDirections()).containsExactly(expected);
    }

    @Test
    void countSteps1() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day8mock1.txt");
        solver.parseInput(lines);
        assertThat(solver.countSteps()).isEqualTo(2);
    }
    @Test
    void countSteps2() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day8mock2.txt");
        solver.parseInput(lines);
        assertThat(solver.countSteps()).isEqualTo(6);
    }

    @Test
    void countSimultaneousSteps() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day8mock3.txt");
        solver.parseInput(lines);
        assertThat(solver.countSimultaneousSteps()).isEqualTo(6);
    }
}
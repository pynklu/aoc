package aoc.camel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.*;

class CamelCardsSolverTest {
    CamelCardsSolver solver = new CamelCardsSolver();
    @Test
    void countWinnings() throws IOException, URISyntaxException {
        var lines = solver.readFileByLine("day7mock.txt");
        solver.parseInput(lines);
        var winnings = solver.countWinnings();
        assertThat(winnings).isEqualTo(6440);
    }
}
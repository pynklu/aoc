package aoc.races;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RaceSolverTest {
    RaceSolver solver;
    List<String> lines;

    @BeforeAll
    void setUp() throws IOException, URISyntaxException {
        solver = new RaceSolver();
        lines = solver.readFileByLine("day6mock.txt");
        solver.parseInput(lines);
    }

    @Test
    void inputWasParsed() {
        assertThat(solver.races.get(0)).isEqualTo(new BoatRace(7, 9));
        assertThat(solver.races.get(1)).isEqualTo(new BoatRace(15, 40));
        assertThat(solver.races.get(2)).isEqualTo(new BoatRace(30, 200));
    }

    @Test
    void countWinningRaces() {
        var race1Options = solver.countWinningOptions(new BoatRace(7, 9));
        var race2Options = solver.countWinningOptions(new BoatRace(15, 40));
        var race3Options = solver.countWinningOptions(new BoatRace(30, 200));
        assertThat(race1Options).isEqualTo(4);
        assertThat(race2Options).isEqualTo(8);
        assertThat(race3Options).isEqualTo(9);
    }

    @Test
    void multiplyWinningOptions() {
        assertThat(solver.multiplyRaceOptions()).isEqualTo(288);
    }

    @Test
    void countBigRace() {
        assertThat(solver.countWinningOptions(solver.bigRace)).isEqualTo(71503);
    }
}
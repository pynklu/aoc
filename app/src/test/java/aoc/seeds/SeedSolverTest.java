package aoc.seeds;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeedSolverTest {
    SeedSolver solver;
    List<String> lines;

    @BeforeAll
    void setUp() throws IOException, URISyntaxException {
        solver = new SeedSolver();
        lines = solver.readFileByLine("day5mock.txt");
        solver.parseInput(lines);
    }

    @Test
    void testReading() throws IOException, URISyntaxException {
        assertThat(lines).isNotEmpty();
    }

    @Test
    void testParsing() {
        assertThat(solver.seeds).containsExactly(79, 14, 55, 13);
        assertThat(solver.maps).hasSize(7);
        assertThat(solver.maps.get(0))
                .hasSize(2)
                .containsExactly(
                        new FromTo(50,52, 48),
                        new FromTo(98,50, 2)

                );
        assertThat(solver.maps.get(solver.maps.size() - 1))
                .hasSize(2)
                .containsExactly(
                        new FromTo(56, 60, 37),
                        new FromTo(93, 56, 4)
                );
    }

    @Test
    void testSeedResolving() {
        assertThat(solver.findSeedLocation(79)).isEqualTo(82);
        assertThat(solver.findSeedLocation(14)).isEqualTo(43);
        assertThat(solver.findSeedLocation(55)).isEqualTo(86);
        assertThat(solver.findSeedLocation(13)).isEqualTo(35);
    }

    @Test
    void lowestSeedFinder() {
        assertThat(solver.findLowestSeedLocation()).isEqualTo(35);
    }
}
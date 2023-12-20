package aoc.seeds;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
    void testReading() {
        assertThat(lines).isNotEmpty();
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

    @Test
    void mergeMappings() {
        var newMap = solver.mergeMappings(solver.mappings.get(0), solver.mappings.get(1));
        assertThat(newMap).contains(
                new Mapping(Range.fromEnd(0, 14), Range.fromEnd(39, 53)),
                new Mapping(Range.fromEnd(15, 49), Range.fromEnd(0, 34)),
                new Mapping(Range.fromEnd(50, 51), Range.fromEnd(37, 38)),
                new Mapping(Range.fromEnd(52, 97), Range.fromEnd(54, 99)),
                new Mapping(Range.fromEnd(98, 99), Range.fromEnd(35, 36)),
                new Mapping(Range.fromEnd(100, Long.MAX_VALUE), Range.fromEnd(100, Long.MAX_VALUE))
        );
    }

    @Test
    void testRangeOverlap(){
        var r1 = Range.fromEnd(80,100);
        var r2 = Range.fromEnd(75,85);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(80,85));

        r1 = Range.fromEnd(80,100);
        r2 = Range.fromEnd(80,85);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(80,85));

        r1 = Range.fromEnd(80,100);
        r2 = Range.fromEnd(75,100);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(80,100));

        r1 = Range.fromEnd(80,100);
        r2 = Range.fromEnd(75,110);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(80,100));

        r1 = Range.fromEnd(80,90);
        r2 = Range.fromEnd(90,100);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(90,90));

        r1 = Range.fromEnd(80,100);
        r2 = Range.fromEnd(75,80);
        assertThat(r1.overlapsWith(r2)).isTrue();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.fromEnd(80,80));

        r1 = Range.fromEnd(80,90);
        r2 = Range.fromEnd(91,100);
        assertThat(r1.overlapsWith(r2)).isFalse();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.empty());

        r1 = Range.fromEnd(10,15);
        r2 = Range.fromEnd(91,100);
        assertThat(r1.overlapsWith(r2)).isFalse();
        assertThat(Range.fromOverlap(r1,r2)).isEqualTo(Range.empty());
    }
}
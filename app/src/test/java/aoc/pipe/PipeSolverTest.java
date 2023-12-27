package aoc.pipe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PipeSolverTest {

    @Test
    void traverse() {
        PipeSolver s1 = new PipeSolver("day10mock1.txt");
        assertThat(s1.traverseLoop()).isEqualTo(4);

        PipeSolver s2 = new PipeSolver("day10mock2.txt");
        assertThat(s2.traverseLoop()).isEqualTo(8);
    }
}
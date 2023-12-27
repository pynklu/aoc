package aoc.sequence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;


import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SequenceSolverTest {
    SequenceSolver solver;

    @BeforeAll
    void setup() throws IOException, URISyntaxException {
        solver = new SequenceSolver();
        var lines = solver.readFileByLine("day9mock.txt");
        solver.parseInput(lines);
    }
    @Test
    void parseInput() {
        assertThat(solver.histories.get(0)).containsExactly(0,3,6,9,12,15);
        assertThat(solver.histories.get(1)).containsExactly(1,3,6,10,15,21);
        assertThat(solver.histories.get(2)).containsExactly(10,13,16,21,30,45);
    }

    @Test
    void buildDiffArray(){
        var h1_0 = solver.histories.get(0);
        var h1_1 = solver.buildDiffArray(h1_0);
        var h1_2 = solver.buildDiffArray(h1_1);
        assertThat(h1_1).containsExactly(3,3,3,3,3);
        assertThat(h1_2).containsExactly(0,0,0,0);

        var h2_0 = solver.histories.get(1);
        var h2_1 = solver.buildDiffArray(h2_0);
        var h2_2 = solver.buildDiffArray(h2_1);
        var h2_3 = solver.buildDiffArray(h2_2);
        assertThat(h2_1).containsExactly(2,3,4,5,6);
        assertThat(h2_2).containsExactly(1,1,1,1);
        assertThat(h2_3).containsExactly(0,0,0);

        var h3_0 = solver.histories.get(2);
        var h3_1 = solver.buildDiffArray(h3_0);
        var h3_2 = solver.buildDiffArray(h3_1);
        var h3_3 = solver.buildDiffArray(h3_2);
        var h3_4 = solver.buildDiffArray(h3_3);
        assertThat(h3_1).containsExactly(3,3,5,9,15);
        assertThat(h3_2).containsExactly(0,2,4,6);
        assertThat(h3_3).containsExactly(2,2,2);
        assertThat(h3_4).containsExactly(0,0);
    }

    @Test
    void getNextValue(){
        var h1_0 = solver.histories.get(0);
        var h1_1 = solver.buildDiffArray(h1_0);
        var h1_2 = solver.buildDiffArray(h1_1);
        var h2_0 = solver.histories.get(1);
        var h3_0 = solver.histories.get(2);

        assertThat(solver.getNextValue(h1_2)).isEqualTo(0);
        assertThat(solver.getNextValue(h1_1)).isEqualTo(3);
        assertThat(solver.getNextValue(h1_0)).isEqualTo(18);
        assertThat(solver.getNextValue(h2_0)).isEqualTo(28);
        assertThat(solver.getNextValue(h3_0)).isEqualTo(68);
    }

    @Test
    void sumNextValues() {
        assertThat(solver.sumNextValues()).isEqualTo(114);
    }
}
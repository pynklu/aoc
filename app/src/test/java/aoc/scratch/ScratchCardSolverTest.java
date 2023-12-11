package aoc.scratch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ScratchCardSolverTest {
    final List<String> mockInput = List.of(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    );

    @Test
    void testValueCalculator() throws IOException, URISyntaxException {
        ScratchCardSolver solver = new ScratchCardSolver();
        assertThat(solver.parseLine(mockInput.get(0)).getValue()).isEqualTo(8);
        assertThat(solver.parseLine(mockInput.get(1)).getValue()).isEqualTo(2);
        assertThat(solver.parseLine(mockInput.get(2)).getValue()).isEqualTo(2);
        assertThat(solver.parseLine(mockInput.get(3)).getValue()).isEqualTo(1);
        assertThat(solver.parseLine(mockInput.get(4)).getValue()).isEqualTo(0);
        assertThat(solver.parseLine(mockInput.get(5)).getValue()).isEqualTo(0);
        assertThat(solver.countCards(mockInput)).isEqualTo(13);
    }

    @Test
    void testRealGame() {
        ScratchCardSolver solver = new ScratchCardSolver();
        assertThat(solver.playTheRealGame(mockInput)).isEqualTo(30);
    }
}
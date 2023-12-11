package aoc.engine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EngineSchematicSolverTest {
    List<String> mockInput = List.of(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
    );

    @Test
    void testSymbolFinder() {
        EngineSchematicSolver solver = new EngineSchematicSolver();
        List<List<Integer>> positions = mockInput.stream().map(solver::getSymbolsInLine).toList();
        assertThat(positions.get(0)).isEmpty();
        assertThat(positions.get(1)).containsExactly(3);
        assertThat(positions.get(2)).isEmpty();
        assertThat(positions.get(3)).containsExactly(6);
        assertThat(positions.get(4)).containsExactly(3);
        assertThat(positions.get(5)).containsExactly(5);
        assertThat(positions.get(6)).isEmpty();
        assertThat(positions.get(7)).isEmpty();
        assertThat(positions.get(8)).containsExactly(3,5);
        assertThat(positions.get(9)).isEmpty();
    }

    @Test
    void testNumberBuilder() {
        EngineSchematicSolver solver = new EngineSchematicSolver();
        solver.initialiseMatrix(mockInput);
        assertThat(solver.buildFullNumber(0,0)).isEqualTo(467);
        assertThat(solver.buildFullNumber(1,0)).isEqualTo(467);
        assertThat(solver.buildFullNumber(2,0)).isEqualTo(467);

        assertThat(solver.buildFullNumber(5,0)).isEqualTo(114);
        assertThat(solver.buildFullNumber(6,0)).isEqualTo(114);
        assertThat(solver.buildFullNumber(7,0)).isEqualTo(114);

        assertThat(solver.buildFullNumber(7,5)).isEqualTo(58);
        assertThat(solver.buildFullNumber(8,5)).isEqualTo(58);
    }

    @Test
    void testSolver() {
        EngineSchematicSolver solver = new EngineSchematicSolver();
        solver.initialiseMatrix(mockInput);
        int result = solver.solveSchematic();
        assertThat(result).isEqualTo(4361);
    }
}
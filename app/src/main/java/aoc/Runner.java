package aoc;

import aoc.cube.CubeSolver;
import aoc.engine.EngineSchematicSolver;
import aoc.scratch.ScratchCardSolver;
import aoc.seeds.SeedSolver;
import aoc.trebuchet.CalibrationSolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try {
            solveDay5();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solveDay1() throws IOException, URISyntaxException {
        CalibrationSolver solver = new CalibrationSolver();
        System.out.println("solver.getSimpleCalibrationValue() = " + solver.getSimpleCalibrationValue());
        System.out.println("solver.getComplexCalibrationValue() = " + solver.getComplexCalibrationValue());

    }

    private static void solveDay2() throws IOException, URISyntaxException {
        CubeSolver solver = new CubeSolver();
        System.out.println("solver.countValidGamesInFile() = " + solver.countValidGamesInFile());
        System.out.println("solver.countGamePowersInFIle() = " + solver.countGamePowersInFIle());
    }

    private static void solveDay3() throws IOException, URISyntaxException {
        EngineSchematicSolver solver = new EngineSchematicSolver();
        List<String> lines = solver.readFileByLine("day3.txt");
        solver.initialiseMatrix(lines);
        System.out.println("solver.solveSchematic() = " + solver.solveSchematic());
        System.out.println("solver.solveGearRatios() = " + solver.solveGearRatios());
    }

    private static void solveDay4() throws IOException, URISyntaxException {
        ScratchCardSolver solver = new ScratchCardSolver();
        var lines = solver.readFileByLine("day4.txt");
        System.out.println("solver.countCards() = " + solver.countCards(lines));
        System.out.println("solver.playTheRealGame(lines) = " + solver.playTheRealGame(lines));
    }

    private static void solveDay5() throws IOException, URISyntaxException {
        SeedSolver solver = new SeedSolver();
        var lines = solver.readFileByLine("day5.txt");
        solver.parseInput(lines);
        System.out.println("solver = " + solver.findLowestSeedLocation());
    }
}

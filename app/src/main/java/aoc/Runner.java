package aoc;

import aoc.cube.CubeSolver;
import aoc.trebuchet.CalibrationSolver;

import java.io.IOException;
import java.net.URISyntaxException;

public class Runner {
    public static void main(String[] args) {
        try {
            solveDay2();
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
}

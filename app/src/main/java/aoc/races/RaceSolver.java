package aoc.races;

import aoc.Solver;

import java.util.List;
import java.util.stream.IntStream;

public class RaceSolver extends Solver {
    List<BoatRace> races;
    BoatRace bigRace;

    public void parseInput(List<String> lines) {
        var processedLines = lines.stream().map(line -> line.split(":")[1].trim().split("\\s+")).toList();
        var timeStrings = processedLines.get(0);
        var distanceStrings = processedLines.get(1);
        this.races = IntStream.range(0, timeStrings.length)
                .mapToObj(i -> new BoatRace(
                        Integer.parseInt(timeStrings[i]),
                        Integer.parseInt(distanceStrings[i])
                )).toList();

        var bigLines = lines.stream()
                .map(line -> line.split(":")[1].trim().replaceAll("\\s+", ""))
                .mapToDouble(Double::parseDouble).toArray();
        bigRace = new BoatRace(bigLines[0], bigLines[1]);
    }

    public int multiplyRaceOptions() {
        return races.stream().map(this::countWinningOptions).reduce(1, Math::multiplyExact);
    }

    public int countWinningOptions(BoatRace race) {
        var discriminant = getDiscriminant(race);
        if (discriminant <= 0) return 0;

        double carrot1 = (-race.time() + Math.sqrt(discriminant)) / -2;
        double carrot2 = (-race.time() - Math.sqrt(discriminant)) / -2;

        return countIntsBetween(carrot1, carrot2);
    }

    private double getDiscriminant(BoatRace race) {
        double b = race.time();
        double c = race.distance();
        return b * b - 4 * c;
    }

    private int countIntsBetween(double d1, double d2) {
        var smallest = Math.min(d1, d2);
        var largest = Math.max(d1, d2);

        int smallestInt = (int) ((smallest % 1 == 0) ? smallest + 1 : Math.ceil(smallest));
        int largestInt = (int) ((largest % 1 == 0) ? largest - 1 : Math.floor(largest));

        return largestInt - smallestInt + 1;
    }

    public BoatRace getBigRace() {
        return this.bigRace;
    }
}

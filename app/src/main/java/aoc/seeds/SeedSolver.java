package aoc.seeds;

import aoc.Solver;

import java.util.*;

public class SeedSolver extends Solver {
    public LinkedList<List<FromTo>> maps = new LinkedList<>();
    public long[] seeds = new long[0];
    public void parseInput(List<String> lines) {
        this.seeds = getSeeds(lines.get(0));

        var currentMap = new ArrayList<FromTo>();
        for(String line : lines.subList(2, lines.size())) {
            if(!line.isEmpty()) {
                if (Character.isDigit(line.charAt(0))) currentMap.add(getFromTo(line));
            } else {
                currentMap.sort(Comparator.comparing(FromTo::from));
                maps.add(currentMap);
                currentMap = new ArrayList<>();
            }
        }
        currentMap.sort(Comparator.comparing(FromTo::from));
        maps.add(currentMap);
    }

    private long[] getSeeds(String line) {
        var seedStrings = line.split(": ")[1].split(" ");
        return Arrays.stream(seedStrings).mapToLong(Long::parseLong).toArray();
    }

    public FromTo getFromTo(String line) {
        long[] numbers = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
        return new FromTo(numbers[1], numbers[0], numbers[2]);
    }

    public long findSeedLocation(long seed) throws NoSuchElementException {
        var currentNumber = seed;
        for(var map : maps) {
            final long cur = currentNumber;
            var found = map.stream().filter(fromTo -> fromTo.from() <= cur && cur < (fromTo.from() + fromTo.length())).findFirst();
            currentNumber = found.isEmpty() ? currentNumber : currentNumber + found.get().delta();
        }
        return currentNumber;
    }

    public long findLowestSeedLocation(){
        return Arrays.stream(seeds).map(this::findSeedLocation).min().orElse(0);
    }
}

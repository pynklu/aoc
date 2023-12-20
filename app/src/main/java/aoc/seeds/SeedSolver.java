package aoc.seeds;

import aoc.Solver;

import java.util.*;

public class SeedSolver extends Solver {
    public final List<List<Mapping>> mappings = new ArrayList<>();

    private List<Mapping> mergedMappings;
    public long[] seeds = new long[0];
    public List<Range> seedRanges;

    public void parseInput(List<String> lines) {
        this.seeds = getSeeds(lines.get(0));
        this.seedRanges = getSeedRanges(lines.get(0));

        var currentMappings = new ArrayList<Mapping>();
        for (String line : lines.subList(2, lines.size())) {
            if (!line.isEmpty()) {
                if (Character.isDigit(line.charAt(0))) {
                    currentMappings.add(getMapping(line));
                }
            } else {
                var filledOutMappings = addMissingMappings(currentMappings);
                mappings.add(filledOutMappings);
                currentMappings = new ArrayList<>();
            }
        }
        var filledOutMappings = addMissingMappings(currentMappings);
        mappings.add(filledOutMappings);

        this.mergedMappings = mergeALlMappings();
    }

    private long[] getSeeds(String line) {
        var seedStrings = line.split(": ")[1].split(" ");
        return Arrays.stream(seedStrings).mapToLong(Long::parseLong).toArray();
    }

    private List<Range> getSeedRanges(String line) {
        var seeds = getSeeds(line);
        var out = new ArrayList<Range>();
        for (int i = 0; i < seeds.length; i += 2) {
            out.add(Range.fromLength(seeds[i], seeds[i + 1]));
        }
        return out;
    }

    public Mapping getMapping(String line) {
        long[] numbers = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
        return new Mapping(numbers[1], numbers[0], numbers[2]);
    }

    private ArrayList<Mapping> addMissingMappings(ArrayList<Mapping> mappings) {
        mappings.sort(Comparator.comparing(mapping -> mapping.getSrc().start));
        long min = 0;
        var out = new ArrayList<Mapping>();
        for (Mapping mapping : mappings) {
            var fromStart = mapping.getSrc().start;
            if (min < fromStart) {
                out.add(new Mapping(min, min, fromStart - min));
            }
            out.add(mapping);
            min = mapping.getSrc().end + 1;
        }
        out.add(new Mapping(min, min, Long.MAX_VALUE));
        return out;
    }


    public long findSeedLocation(long seed) {
        return mergedMappings.stream()
                .filter(mapping -> mapping.canMap(seed))
                .findAny()
                .map(mapping -> seed + mapping.delta)
                .orElse(seed);
    }

    public long findLowestSeedRangeLocation_bruteforce(Range seedRange) {
        long currentIteration = 0;
        long min = Long.MAX_VALUE;
        long startTime = System.nanoTime();
        for (long i = seedRange.start; i < seedRange.end; i++) {
            min = Math.min(min, findSeedLocation(i));
            currentIteration++;
            if (currentIteration == 100000) {
                long duration = (System.nanoTime() - startTime) / 1000_000_000;
                System.out.println((int) ((currentIteration * 100.0f) / seedRange.length) + "% in " + duration + "s");
                currentIteration = 0;
                startTime = System.nanoTime();
            }
        }
        return min;
    }

    public long findLowestSeedLocation2() {
        var seedMappings = seedRanges.stream().sorted(Comparator.comparing(range -> range.start)).map(range -> new Mapping(range, range)).toList();
        var finalMapping = mergeMappings(seedMappings, mergedMappings);
        finalMapping.sort(Comparator.comparing(mapping -> mapping.getDest().start));
        return finalMapping.getFirst().getDest().start;
    }

    public long findLowestSeedLocation() {
        return Arrays.stream(seeds).map(this::findSeedLocation).min().orElse(0);
    }


    public List<Mapping> mergeMappings(List<Mapping> sources, List<Mapping> destinations) {
        List<Mapping> out = new ArrayList<>();
        for (Mapping source : sources) {
            var merges = destinations.stream().filter(source::canMergeWith).map(source::mergeWith).toList();
            out.addAll(merges);
        }
        return out;
    }

    public List<Mapping> mergeALlMappings() {
        var currentMapping = mappings.get(0);
        for (int i = 1; i < mappings.size(); i++) {
            currentMapping = mergeMappings(currentMapping, mappings.get(i));
        }
        return currentMapping;
    }
}

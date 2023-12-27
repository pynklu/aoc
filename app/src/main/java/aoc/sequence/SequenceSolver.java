package aoc.sequence;

import aoc.Solver;

import java.util.Arrays;
import java.util.List;

public class SequenceSolver extends Solver {
    List<int[]> histories;
    public void parseInput(List<String> lines){
        this.histories = lines.stream()
                .map(s -> Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray())
                .toList();
    }

    public long sumNextValues() {
       return histories.stream().mapToLong(this::getNextValue).sum();
    }

    int getNextValue(int[] array) {
        var diffArray = buildDiffArray(array);
        var delta = isZeroArray(diffArray) ? 0 : getNextValue(diffArray);
        return array[array.length - 1] + delta;
    }

    int[] buildDiffArray(int[] array){
        var out = new int[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            out[i-1] = array[i] - array[i-1];
        }
        return out;
    }

    boolean isZeroArray(int[] array) {
        return Arrays.stream(array).noneMatch(i -> i != 0);
    }
}

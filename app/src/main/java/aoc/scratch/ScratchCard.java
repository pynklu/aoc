package aoc.scratch;

import java.util.List;

public class ScratchCard {
    private final int id;
    private final List<Integer> winningNumbers;
    private final List<Integer> ownNumbers;

    public ScratchCard(int id, List<Integer> winningNumbers, List<Integer> ownNumbers){
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.ownNumbers = ownNumbers;
    }

    public double getValue() {
        long overlapCount = countOverlappingNumbers();
        return overlapCount > 0 ? Math.pow(2, countOverlappingNumbers() - 1) : 0;
    }

    private long countOverlappingNumbers(){
        return ownNumbers.stream().filter(winningNumbers::contains).count();
    }
}

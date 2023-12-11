package aoc.scratch;

import java.util.ArrayList;
import java.util.List;

public class ScratchCard {
    private final int id;
    private final List<Integer> winningNumbers;
    private final List<Integer> ownNumbers;
    private final List<ScratchCard> wonCards = new ArrayList<>();

    public ScratchCard(int id, List<Integer> winningNumbers, List<Integer> ownNumbers){
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.ownNumbers = ownNumbers;
    }

    public double getValue() {
        long overlapCount = countMatchingNumbers();
        return overlapCount > 0 ? Math.pow(2, countMatchingNumbers() - 1) : 0;
    }

    public long countMatchingNumbers(){
        return ownNumbers.stream().filter(winningNumbers::contains).count();
    }

    public void addWonCards(List<ScratchCard> cards){
        wonCards.addAll(cards);
    }

    public int getStackSize(){
        return 1 + wonCards.parallelStream().mapToInt(ScratchCard::getStackSize).sum();
    }
}

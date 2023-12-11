package aoc.scratch;

import aoc.Solver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ScratchCardSolver extends Solver {
    private static final Pattern digitPattern = Pattern.compile("\\D");
    public ScratchCard parseLine(String line){
        var cardAndNumbers = line.split(":");
        var cardId = Integer.parseInt(digitPattern.matcher(cardAndNumbers[0]).replaceAll(""));
        var winningAndOwnNumbers = cardAndNumbers[1].split("\\|");
        return new ScratchCard(
                cardId,
                numberstringToIntArray(winningAndOwnNumbers[0]),
                numberstringToIntArray(winningAndOwnNumbers[1])
        );
    }

    private List<Integer> numberstringToIntArray(String s) {
        return Arrays.stream(s.strip().split("\\s+")).map(Integer::parseInt).toList();
    }

    public double countCards(List<String> inputLines) throws IOException, URISyntaxException {
        return inputLines.stream()
                .map(this::parseLine)
                .mapToDouble(ScratchCard::getValue)
                .sum();
    }

    public int playTheRealGame(List<String> inputLines) {
        var cards = inputLines.stream().map(this::parseLine).toList();
        for (int i = 0; i < cards.size(); i++) {
            var wonAmount = cards.get(i).countMatchingNumbers();
            cards.get(i).addWonCards(cards.subList(i+1, (int) (i+wonAmount+1)));
        } return cards.stream().mapToInt(ScratchCard::getStackSize).sum();
    }
}

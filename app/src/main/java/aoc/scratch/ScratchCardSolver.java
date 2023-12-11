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

    public double countCards() throws IOException, URISyntaxException {
        return readFileByLine("day4.txt").stream()
                .map(this::parseLine)
                .mapToDouble(ScratchCard::getValue)
                .sum();
    }
}

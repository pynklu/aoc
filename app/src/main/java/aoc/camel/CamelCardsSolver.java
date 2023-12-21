package aoc.camel;

import aoc.Solver;

import java.util.List;
import java.util.stream.Collectors;

public class CamelCardsSolver extends Solver {
    List<Hand> hands;
    public void parseInput(List<String> lines) {
        hands = lines.stream()
                .map(line -> line.split(" "))
                .map(split -> new Hand(split[0], Integer.parseInt(split[1])))
                .collect(Collectors.toList());
    }
    
    public long countWinnings() {
        var sum = 0L;
        hands.sort(Hand::compareTo);
        for (int i = 0; i < hands.size(); i++) {
            sum += (long) (i + 1) * hands.get(i).getBid();
        } return sum;
    }
}

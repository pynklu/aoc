package aoc.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public class Game {
    private final int number;
    private final List<Round> rounds = new ArrayList<>();

    public Game(int number){
        this.number = number;
    }

    public void addRound(Round round){ this.rounds.add(round); }

    public List<Round> getRounds() { return this.rounds; }
    public int getNumber() { return this.number; }

    private int getMaxValue(ToIntFunction<Round> getter) {
        return this.rounds.stream().mapToInt(getter).max().orElse(0);
    }

    public boolean isValidGame(){
        return rounds.stream().noneMatch(round -> (round.red() > 12 || round.green() > 13 || round.blue() > 14));
    }

    public int getPower() {
        return getMaxValue(Round::blue) * getMaxValue(Round::red) * getMaxValue(Round::green);
    }
}
